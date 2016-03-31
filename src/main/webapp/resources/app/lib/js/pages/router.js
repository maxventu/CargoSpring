(function() {
    var CargoSpring = angular.module('CargoSpring', ['ngRoute', 'ngCookies', 'ngResource']);


    CargoSpring.roles = {
        sysadmin: "SYS_ADMIN",
        admin: "ADMIN",
        driver: "DRIVER"
    };
    CargoSpring.getValuesFromJSONToArray = function (json) {
        return Object.keys(json).map(function(k) { return json[k] });
    };
    CargoSpring.pages = [
        {path:'index',name:'Home page', roles:CargoSpring.getValuesFromJSONToArray(CargoSpring.roles)},
        {path:'users',name:'Users', roles:[CargoSpring.roles.sysadmin,CargoSpring.roles.admin]}
    ];
    CargoSpring.pagesDir = 'resources/app/pages';
    CargoSpring.path = window.location.pathname;

   


    CargoSpring.config(function($routeProvider,$httpProvider){
        var dir = CargoSpring.pagesDir;
        $routeProvider
            .when('/',{
                templateUrl:dir+'/login/login.html',
                controller:'loginController'
            })
            .when('/error',{
                templateUrl:dir+'/error/error.html',
                controller:'errorController'
            })
            .when('/default', {
                templateUrl: dir+'/default.html',
                controller: 'defaultPageController'
            });
        CargoSpring.pages.forEach(function (p, i, pages) {
            var page=p.path;
            $routeProvider
                .when('/'+page,{
                    templateUrl:dir+'/'+page+'/'+page+'.html',
                    controller:page+'Controller',
                    resolve: {
                        permission: function (authorisationService, $route) {
                            return authorisationService.permissionCheck(p.roles);
                        }
                    }
                });
        });
        $routeProvider.otherwise({redirectTo: '/'});
        $httpProvider.interceptors.push('ServerHttpResponseInterceptor');
    });

    CargoSpring.run(
        function($rootScope,$location,authorisationService){
            $rootScope.authenticated = false;
            $rootScope.usersCountOnPage = 10;
            $rootScope.appPages = CargoSpring.pages;
            $rootScope.appPath = CargoSpring.path;
            $rootScope.getCurrentPage = function () {
                var curPage = {path: '',name:''};
                CargoSpring.pages.forEach(function(page, i, pages){
                    if(('/'+page.path) == $location.path())
                        curPage = {path: page.path,name:page.name}
                });
                return curPage;
            };

            $rootScope.isAuth = function () {
                return authorisationService.permissionModel.isLoaded;
            };

            $rootScope.hasAuthority = function (roles) {
                if (authorisationService.permissionModel.isLoaded) {
                    var pageRoles = authorisationService.permissionModel.roles;
                    var hasAuth = false;
                    for(var i=0;i<roles.length;i++){
                        
                        for(var j=0;j< pageRoles.length;j++){
                            if(roles[i] == pageRoles[j])hasAuth = true;
                        }
                    }
                    return hasAuth;
                } else {
                    return false;
                }
            }
        });


    CargoSpring.factory('authorisationService', function($resource, $q, $rootScope, $location, $http){
        return {
            permissionModel:
            {
                isLoaded:false,
                roles:{}
            },
            permissionCheck: function (roleCollection) {
                var deferred =  $q.defer();
                var parentPointer = this;
                if (this.permissionModel.isLoaded) {
                    this.getPermission(this.permissionModel, roleCollection, deferred);
                } else {
                    var requestGetUserRole = $http({
                        method: "get",
                        url: CargoSpring.path + "role",
                        dataType: 'json',
                        contentType: 'application/json',
                        mimeType: 'application/json'
                    });
                    requestGetUserRole.success(function (data) {
                        console.log("user roles: "+data);
                        if (data!=null) {
                            parentPointer.permissionModel.roles = data;
                            parentPointer.permissionModel.isLoaded = true;
                            parentPointer.getPermission(parentPointer.permissionModel, roleCollection, deferred);
                        } else {
                            $location.path("/");
                            deferred.reject();
                        }
                    });
                    requestGetUserRole.error(function () {
                        $location.path("/error");
                        deferred.reject();
                    })
                }
                return deferred.promise;
            },
            getPermission: function (permissionModel, roleCollection, deferred) {
                var isPassed = false;
                console.log(permissionModel);
                console.log(roleCollection);
                for(var r in permissionModel){
                    if (permissionModel.hasOwnProperty(r)) {
                        angular.forEach(roleCollection, function (role) {
                            if(permissionModel[r]==role){
                                isPassed = true;
                                console.log("current role: "+role+"; checking: "+r+" is passed: "+isPassed);
                            }
                        });
                    }
                }
                if (!isPassed) {
                    $rootScope.authenticated = false;
                    console.log("authenticated: "+$rootScope.authenticated);
                    $location.path("/");
                    deferred.reject();
                } else {
                    $rootScope.authenticated = true;
                    console.log("authenticated: "+$rootScope.authenticated);
                    deferred.resolve();
                }
            }
        }
    });



    CargoSpring.factory('ServerHttpResponseInterceptor', function ($q, $rootScope, $location) {
        return function (promise) {
            return promise.then(
                function (response) {
                    return response;
                },
                function (response) {
                    $rootScope.errorStatus = response.status;
                    $location.path("/error");
                    return $q.reject(response);
                });
        };
    });


})();
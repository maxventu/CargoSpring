(function() {
    var CargoSpring = angular.module('CargoSpring', ['ngRoute']);

    CargoSpring.pages = [
        {path:'index',name:'Home page'},
        {path:'users',name:'Users'}
    ];
    CargoSpring.pagesDir = 'resources/app/pages';
    CargoSpring.path = window.location.pathname;
    CargoSpring.config([
            '$routeProvider',
            function($routeProvider){
                var dir = CargoSpring.pagesDir;
                CargoSpring.pages.forEach(function (p, i, pages) {
                    var page=p.path;
                    $routeProvider
                        .when('/'+page,{templateUrl:dir+'/'+page+'/'+page+'.html',controller:page+'Controller'});
                });
                $routeProvider.when('/',{templateUrl:dir+'/login/login.html',controller:'loginController'});
                $routeProvider.otherwise({redirectTo: '/'});
            }])
        .run(
            function($rootScope,$location){
                $rootScope.usersCountOnPage = 10;
                $rootScope.appPages = CargoSpring.pages;
                $rootScope.getCurrentPage = function () {
                    var curPage = {path: '',name:''};
                    CargoSpring.pages.forEach(function(page, i, pages){
                        if(('/'+page.path) == $location.path())
                            curPage = {path: page.path,name:page.name}
                    });
                    return curPage;
                }
            });
})();
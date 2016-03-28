(function() {
    angular.module("CargoSpring")
        .controller('usersController',function($rootScope, $location, $scope, $http, $routeParams) {
            $scope.pagename = "Users";
           
            var path = window.location.pathname;
            $scope.activepage = "users";
            
            var requestGetUsers = $http({
                method: "get",
                url:  path+"users",
                params: {},
                dataType: 'json',
                contentType: 'application/json',
                mimeType: 'application/json'
            });

            requestGetUsers.success(function (data) {
                $scope.users = data;
            });

            $scope.pages = angular.module('CargoSpring').pages;
        });


})();
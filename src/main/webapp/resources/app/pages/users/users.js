(function() {
    angular.module("CargoSpring")
        .controller('usersController',function($rootScope, $location, $scope, $http, $routeParams) {
            $scope.message = "Here would be a table";
            var path = window.location.pathname;

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
        });


})();
(function() {
    angular.module("CargoSpring")
        .controller('indexController', ['$scope', function ($scope) {
            console.log("index js");
            $scope.message = "Here is index page";
        }])
        .controller('usersController', ['$scope', function ($scope) {
            console.log("users js");
            $scope.message = "Here would be a table";
        }]);
})();
(function() {
    angular.module("CargoSpring")
        .controller('indexController', ['$scope', function ($scope) {
            $scope.activepage = "index";
            $scope.pagename = "Home page";
            $scope.pages = angular.module('CargoSpring').pages;
        }]);
})();
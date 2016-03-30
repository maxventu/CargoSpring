app.controller("errorController", function ($rootScope, $scope) {
    $scope.error = {};
    $scope.error.code = $rootScope.errorStatus;
    $scope.error.message = "Error!";

});
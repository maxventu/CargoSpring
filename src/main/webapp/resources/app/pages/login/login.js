CargoSpring.controller("loginController", function ($scope, $location) {
        var hasError = ($location.search()).error;
        if (hasError) {
            $scope.errorMessage = "Invalid username or password";
        } else {
            $scope.errorMessage = "";
        }
    }
);

CargoSpring.controller("defaultPageController", function($location, $http ){
    var hasSuccess = ($location.search()).success;
    if (hasSuccess) {
        var requestGetUserRole = $http({
            method: "get",
            url: CargoSpring.path + "role",
            dataType: 'json',
            contentType: 'application/json',
            mimeType: 'application/json'
        });

        requestGetUserRole.success(function (data) {
            if (data == "USER") {
                $location.path('/index');
                $location.replace();
            }
            if (data == "ADMIN") {
                $location.path('/users');
                $location.replace();
            }
        });

    }
});
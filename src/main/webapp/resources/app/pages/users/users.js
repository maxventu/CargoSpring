(function() {
angular.module("CargoSpring")
    .controller('usersController',function($rootScope, $location, $scope, $http,PageService, $routeParams) {
        $scope.showUsers = false;
        $scope.showEmpty = false;
        $scope.users = {};

        var requestGetUsers = $http({
            method: "get",
            url:  CargoSpring.path+"users",
            params: {page:0,size:$rootScope.usersCountOnPage},
            dataType: 'json',
            contentType: 'application/json',
            mimeType: 'application/json'
        });

        requestGetUsers.success(function (data) {
            $scope.users = data.content;
            $scope.currentPage = 1;
            $scope.totalElements = data.totalElements;
            $scope.totalPages = PageService.totalPageNumber($rootScope.usersCountOnPage, $scope.totalElements);
            $scope.showUsers = data.content.length != 0;
            $scope.showEmpty = !$scope.showUsers;
        });

        $scope.getUsers = function(pageNumber){
            
            if(pageNumber<=0 || pageNumber>$scope.totalPages) return false;
            var requestGetUsers = $http({
                method: "get",
                url:  CargoSpring.path+"users",
                params: {page:pageNumber-1,size:$rootScope.usersCountOnPage},
                dataType: 'json',
                contentType: 'application/json',
                mimeType: 'application/json'
            });

            requestGetUsers.success(function (data) {
                $scope.users = data.content;
                $scope.currentPage = pageNumber;
                $scope.totalElements = data.totalElements;
                $scope.totalPages = PageService.totalPageNumber($rootScope.usersCountOnPage, $scope.totalElements);
                $scope.showUsers = data.content.length != 0;
                $scope.showEmpty = !$scope.showUsers;
            });
        };

        $scope.getArrayWithNumbers = function(min, max){

            var array = [];
            for(i = min,j=0;i<=max;i++,j++){
                array[j] = i;
            }
            return array;
        };
    });
})();
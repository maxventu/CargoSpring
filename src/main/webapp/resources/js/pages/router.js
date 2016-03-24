(function () {
    var CargoSpring = angular.module('CargoSpring', ['ngRoute']);


    CargoSpring.config(
        function ($routeProvider) {
            $routeProvider
                .when('/', {templateUrl: 'resources/app/pages/index/index.html', controller: 'indexController'})
                .when('/users', {templateUrl: 'resources/app/pages/users/users.html', controller: 'usersController'})
                .otherwise({redirectTo: '/'});
        }).run();
})();
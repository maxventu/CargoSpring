(function() {
var CargoSpring = angular.module('CargoSpring', ['ngRoute']);

CargoSpring.pages = [
    'index',
    'users'
];
CargoSpring.pagesDir = 'resources/app/pages';

CargoSpring.config([
    '$routeProvider',
    function($routeProvider){
        var dir = CargoSpring.pagesDir;
     CargoSpring.pages.forEach(function (page, i, pages) {
     $routeProvider
     .when('/'+page,{templateUrl:dir+'/'+page+'/'+page+'.html',controller:page+'Controller'});
     });
        $routeProvider.otherwise({redirectTo: '/'});
}]).run();
})();

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="en" ng-app="CargoSpring">
<head>

</head>
<body>

<div ng-view></div>
    <p>Enter your Name: <input type = "text" ng-model = "name"></p>
    <p>Hello <span ng-bind = "name"></span>!</p>
</body>

<link rel="stylesheet" href="http://ajax.googleapis.com/ajax/libs/angularjs/1.3.14/angular.min.js">
<link rel="stylesheet" href="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.4/themes/smoothness/jquery-ui.css">
<link rel="stylesheet" href="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.4/jquery-ui.min.js">
<link rel="stylesheet" href="https://ajax.googleapis.com/ajax/libs/angular_material/1.0.5/angular-material.min.js">
</html>
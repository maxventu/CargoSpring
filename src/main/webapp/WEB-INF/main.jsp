<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="en">
<head>

</head>
<body ng-app = "CargoSpring">

<div >
    <div ng-view></div>
</div>
<p><a href = "#/users">View Users</a></p>
<p><a href = "#/index">Blank page</a></p>
<%@include file="imports.html" %>
</body>
</html>
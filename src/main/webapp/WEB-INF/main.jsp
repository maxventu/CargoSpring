<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="en">
<head>
    <%@include file="imports.html" %>
</head>
<body ng-app = "CargoSpring">


<%@include file="header.html" %>

<div >
    <div ng-view></div>
</div>
<h1>{{activepage}}</h1>
<p><a href = "#/users">View Users</a></p>
<p><a href = "#/index">Blank page</a></p>

<%@include file="footer.html" %>
</body>
</html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <title>Title</title>
    <!--Import Google Icon Font-->
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    <!-- Compiled and minified CSS -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.min.css">

    <link href="<spring:url value="/static/css/style.css"/>" rel="stylesheet"/>
    <!--Let browser know website is optimized for mobile-->
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
</head>
<body>

<nav>
    <div class="nav-wrapper">
        <a href="<spring:url value="/"/>" class="brand-logo">Logo</a>
        <ul id="nav-mobile" class="right hide-on-med-and-down">
            <li><a href="<spring:url value="/create"/>">Create</a></li>
            <li><a href="<spring:url value="/h2console"/>">H2 Console</a></li>
        </ul>
    </div>
</nav>

<div class="container">

    <h1>Students</h1>

    <table class="highlight">
        <thead>
        <tr>
            <th>Id</th>
            <th>First Name</th>
            <th>Last Name</th>
            <th>Age</th>
            <th>Group</th>
            <th>Birth date</th>
            <th>Rol</th>
            <th>Edit</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${students}" var="s">
            <tr>
                <td>${s.id}</td>
                <td>${s.firstName}</td>
                <td>${s.lastName}</td>
                <td>${s.age}</td>
                <td>${s.group}</td>
                <td>${s.birthDate}</td>
                <td>${s.typeStudentsCaptain}</td>
                <td>
                    <a href="<spring:url value="/edit/${s.id}"/>">Update</a>
                    <a href="<spring:url value="/delete/${s.id}"/>">Delete</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

    <!-- Compiled and minified JavaScript -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js"></script>
</div>

</body>
</html>

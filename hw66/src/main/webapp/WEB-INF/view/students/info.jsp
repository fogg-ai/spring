<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <title>Student Info</title>
    <link href="<spring:url value="/static/css/style.css"/>" rel="stylesheet"/>
</head>
<body>
<h1>Student Info</h1>

<div>${error}</div>
<div>
    <p>Id: ${student.id}</p>
    <p>First Name: ${student.firstName}</p>
    <p>Last Name: ${student.lastName}</p>
    <p>Age: ${student.age}</p>
</div>
<p>
    <a href="<c:url value="/students"/>">Go to students list</a>
</p>
</body>
</html>

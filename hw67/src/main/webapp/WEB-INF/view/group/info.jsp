<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <title>Student Info</title>
    <link href="<spring:url value="/static/css/style.css"/>" rel="stylesheet"/>
</head>
<body>
<h1>Group Info</h1>

<div>${error}</div>
<div>
    <p>Id: ${groups.id}</p>
    <p>Group Name: ${groups.groupName}</p>
</div>
<p>
    <a href="<c:url value="/group"/>">Go to group list</a>
</p>
</body>
</html>

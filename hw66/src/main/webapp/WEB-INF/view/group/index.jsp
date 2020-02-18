<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <title>Title</title>
    <link href="<spring:url value="/static/css/style.css"/>" rel="stylesheet"/>
</head>
<body>
    <h1>Group</h1>
    <p><a href="<spring:url value="/group/new"/>">Create group</a>
        <a href="<spring:url value="/students"/>">Students List</a></p>
<table>
    <thead>
        <tr>
            <th>Id</th>
            <th>Group Name</th>
            <th>Action</th>
        </tr>
    </thead>
    <tbody>
        <c:forEach var="group" items="${groups}">
            <tr>
                <td>${group.id}</td>
                <td>${group.groupName}</td>
                <td><a href="<spring:url value="/group/delete/${group.id}"/>">Delete</a>
                    <br/>
                    <a href="<spring:url value="/group/update/${group.id}"/>">Update</a></td>
            </tr>
        </c:forEach>
    </tbody>
</table>
</body>
</html>

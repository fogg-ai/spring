<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <title>Title</title>
    <link href="<spring:url value="/static/css/style.css"/>" rel="stylesheet"/>
</head>
<body>
    <h1>Students</h1>
    <p><a href="<spring:url value="/students/new"/>">Create student</a> </p>
<table>
    <thead>
        <tr>
            <th>Id</th>
            <th>First Name</th>
            <th>Last Name</th>
            <th>Age</th>
            <th>Group</th>
            <th>Action</th>
        </tr>
    </thead>
    <tbody>
        <c:forEach var="student" items="${students}">
            <tr>
                <td>${student.id}</td>
                <td>${student.firstName}</td>
                <td>${student.lastName}</td>
                <td>${student.age}</td>
                <td>${student.group}</td>
                <td><a href="<spring:url value="/students/delete/${student.id}"/>">Delete</a>
                    <br/>
                    <a href="<spring:url value="/students/update/${student.id}"/>">Update</a> </td>
            </tr>
        </c:forEach>
    </tbody>
</table>
</body>
</html>

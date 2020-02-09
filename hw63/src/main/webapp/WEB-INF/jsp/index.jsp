<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="org.itstep.servise.ToDoService" isELIgnored="false" %>
<%@ page import="java.util.List" isELIgnored="false" %>
<html>
<head>
    <c:import url="include/header.jsp"/>
    <title>All ToDo</title>
</head>
<body>
<div class="contener">
    <c:import url="include/navigation.jsp"/>
    <table class="allToDo">
        <tr>
            <th>id</th>
            <th>Short Description</th>
            <th>Long Description</th>
            <th>Create Time</th>
            <th>Start time</th>
            <th>End time</th>
            <th>Delete</th>

        </tr>
        <c:forEach var="todo" items='${requestScope.todo}'>
            <tr>
                <td>${todo.id}</td>
                <td>${todo.shortDescription}</td>
                <td>${todo.longDescription}</td>
                <td>${todo.create}</td>
                <td>${todo.start}</td>
                <td>${todo.end}</td>
                <td><a href="/delete?id=${todo.id}">Remove</a></td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>

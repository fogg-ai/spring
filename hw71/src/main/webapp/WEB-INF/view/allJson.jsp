<%@ page contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page import="org.itstep.api.JsonRestController" isELIgnored="false" %>
<%@ page import="java.util.List" isELIgnored="false" %><html>
<head>
    <title>Title</title>

    <style>
        table{
            border-collapse: collapse;

        }
        tr{
            border: 1px solid #ccc;
            padding: 5px 10px;
        }
        td{
            border: 1px solid #ccc;
            padding: 5px 10px;
        }
        th{
            border: 1px solid #ccc;
            padding: 5px 10px;
        }
    </style>
</head>
<body>
    <h1>Home page</h1>
    <table>
        <thead>
            <tr>
                <th>
                    Title
                </th>
                <th>
                    Description
                </th>
                <th>
                    Created
                </th>
                <th>
                    LastUpdate
                </th>
                <th>
                    Priority
                </th>
                <th>
                    Done
                </th>
                <th>
                    Action
                </th>
            </tr>
        </thead>
        <tbody>
        <c:forEach var="todo" items='${requestScope.toDoList}'>
            <tr>
                <td>${todo.title}</td>
                <td>${todo.description}</td>
                <td>${todo.created}</td>
                <td>${todo.lastUpdate}</td>
                <td>${todo.priority}</td>
                <td>${todo.done}</td>
                <td> <a href="<spring:url value="/api/delete?href=${todo.links.getSelf().getHref()}"/>">Delete</a></td>
            </tr>
        </c:forEach>
        <a href="<spring:url value="/"/>">Back</a>
        </tbody>
    </table>
</body>
</html>

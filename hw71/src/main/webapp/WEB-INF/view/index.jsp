<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Json Menu</title>
    <style>
        .block{
            border: 1px solid #ccc;
            height: 30px;
            width: 80px;
            border-radius: 10px;
        }
        a{
            margin-left: 10px;
        }
    </style>
</head>
<body>
    <div class="block">
        <a href="<spring:url value="/api/all"/>">All</a>
        <a href="<spring:url value="/api/add"/>">Add</a>
    </div>
</body>
</html>

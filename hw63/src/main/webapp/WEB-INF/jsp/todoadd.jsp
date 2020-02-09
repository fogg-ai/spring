<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <c:import url="include/header.jsp"/>
    <title>Add ToDo</title>
</head>
<body>
<c:import url="include/navigation.jsp"/>
<table>
    <form action="/add" method="post">
        <tr>
            <td>Short Description:</td>
            <td><input name="shortDescription" required/></td>
        </tr>
        <tr>
            <td>Long Description:</td>
            <td><input name="longDescription"/></td>
        </tr>
        <tr>
            <td>Category Name:</td>
            <td><input name="categoryName"/></td>
        </tr>
        <tr>
            <td>Start business:</td>
            <td><input type="date" name="start"/></td>
        </tr>
        <tr>
            <td>End business:</td>
            <td><input type="date" name="end"/></td>
        </tr>
        <tr>
            <td></td>
            <td> <button type="submit">Добавить</button></td>
        </tr>
    </form>
</table>
</body>
</html>

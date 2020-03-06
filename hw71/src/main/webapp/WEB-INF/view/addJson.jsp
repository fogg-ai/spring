<%@ page contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page import="org.itstep.api.JsonRestController" isELIgnored="false" %>
<%@ page import="java.util.List" isELIgnored="false" %>
<html>
<html>
<head>
    <title>AddJson</title>
    <style>
        div{
            display: inline-block;
            width: 180px;
        }
        .input-wrapper {
            display: inline-block;
            position: relative;
        }
        .input-wrapper:hover:after {
            content: attr(data-title);
            position: absolute;
            left: 90%; top: 50%;
            z-index: 1;
            background: #ffffcc;
            font-size: 11px;
            padding: 5px 10px;
            border: 1px solid #000;
            box-shadow: 5px 5px 8px 0px rgba(119, 119, 119, 0.64);
            -moz-box-shadow: 5px 5px 8px 0px rgba(119, 119, 119, 0.64);
            -webkit-box-shadow: 5px 5px 8px 0px rgba(119, 119, 119, 0.64);
        }
    </style>
</head>
<body>
<form method="post" action="/api/add">

    <div class="input-wrapper" data-title="Для текущей даты оставте поле пустым.Дaту вводить в таком виде '2020-03-06T21:45:17.248'">
        <label for="create">Create Time: </label>
        <input type="date-time" placeholder="2020-03-06T21:45:17.248" id="create" data-schemaformat="date-time" name="created" />
    </div>
    <div class="input-wrapper" data-title="Для текущей даты оставте поле пустым.Дaту вводить в таком виде '2020-03-06T21:45:17.248'">
        <label for="update">Update Time: </label>
        <input type="date-time" placeholder="2020-03-06T21:45:17.248" id="update" data-schemaformat="date-time" name="update"/>
    </div>
    <div>
        <label for="description">Description: </label>
        <input name="description" id="description" required/>
    </div>
    <div>
        <label for="title">Title: </label>
        <input name="title" id="title" required/>
    </div>

    <div>
        <select name="priority">
            <option value="HIGH">HIGH</option>
            <option value="MEDIUM">MEDIUM</option>
            <option value="LOW">LOW</option>
        </select>
        <select name="done">
            <option value="true">True</option>
            <option value="false">False</option>
        </select>
    </div>
    <input type="submit"/>
    </div>
</form>
</body>
</html>

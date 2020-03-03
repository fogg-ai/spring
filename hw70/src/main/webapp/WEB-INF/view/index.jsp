<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link href="<spring:url value="/static/css/style.css"/>" rel="stylesheet"/>
</head>
<body>
    <h1>Home page</h1>
    <table>
        <thead>
            <tr>
                <th>
                    Email
                </th>
                <th>
                    First Name
                </th>
                <th>
                    Last Name
                </th>
                <th>
                    Img
                </th>
            </tr>
        </thead>
        <tbody>
        </tbody>
    </table>
    <script src="<spring:url value="/static/js/personAjax.js"/>"></script>
</body>
</html>

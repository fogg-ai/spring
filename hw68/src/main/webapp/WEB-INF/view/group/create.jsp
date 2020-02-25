<%@ taglib prefix="c" uri="http://www.springframework.org/tags" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Create new student</title>
    <link href="<c:url value="/static/css/style.css"/>" rel="stylesheet"/>
</head>
<body>
    <h1>Create new group</h1>
<form method="post">
    <div>
        <label for="group">First name: </label>
        <input name="groupName" id="group" required/>
    </div>

    <div>
        <input type="submit"/>
    </div>
</form>
</body>
</html>

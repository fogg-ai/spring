<%@ taglib prefix="c" uri="http://www.springframework.org/tags" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Create new student</title>
    <link href="<c:url value="/static/css/style.css"/>" rel="stylesheet"/>
</head>
<body>
    <h1>Create new student</h1>
<form method="post">
    <div>
        <label for="firstName">First name: </label>
        <input name="firstName" id="firstName" required/>
    </div>
    <div>
        <label for="lastName">Last name: </label>
        <input name="lastName" id="lastName" required/>
    </div>
    <div>
        <label for="age">Age: </label>
        <input name="age" id="age" type="number" min="8" max="60" required/>
    </div>

    <div>
        <label for="group">Group: </label>
        <input name="group" id="group" required/>
    </div>

    <div>
        <input type="submit"/>
    </div>
</form>
</body>
</html>

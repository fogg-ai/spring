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
<p><a href="<spring:url value="/group"/>">Group List</a></p>
<table>
    <thead>
    <tr>
        <th>Id</th>
        <th>First Name</th>
        <th>Last Name</th>
        <th>Age</th>
        <th>Group Name</th>
        <th>Action</th>
    </tr>
    </thead>
    <tbody id="tBody">
    </tbody>
</table>

<h1>Create new student</h1>
<form id="formCreateGroup">
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
<h1>Update student</h1>
<form id="formUpdateGroup">
    <div>
        <label for="firstNameUp">First name: </label>
        <input name="firstName" id="firstNameUp" required/>
    </div>
    <div>
        <label for="lastNameUp">Last name: </label>
        <input name="lastName" id="lastNameUp" required/>
    </div>
    <div>
        <label for="ageUp">Age: </label>
        <input name="age" id="ageUp" type="number" min="8" max="60" required/>
    </div>

    <div>
        <label for="groupUp">Group: </label>
        <input name="group" id="groupUp" required/>
    </div>

    <div>
        <input type="submit"/>
    </div>
</form>
<script src="<spring:url value="/static/js/studentsAjax.js"/>">

</script>

</body>
</html>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <title>Title</title>
    <link href="<spring:url value="/static/css/style.css"/>" rel="stylesheet"/>
</head>
<body>
<h1>Group</h1>
<p><a href="<spring:url value="/students"/>">Students List</a></p>
<table>
    <thead>
    <tr>
        <th>Id</th>
        <th>Group Name</th>
        <th>Action</th>
    </tr>
    </thead>
    <tbody id="tBody">
    </tbody>
</table>

<h1>Create new group</h1>
<form id="formCreateGroup">
    <div>
        <label for="group">First name: </label>
        <input name="groupName" id="group" required/>
    </div>
    <div>
        <input type="submit"/>
    </div>
</form>

<h1>Update group</h1>
<form id="formUpdateGroup">
    <div>
        <label for="groupName">First name: </label>
        <input name="groupName" id="groupName" required/>
    </div>
    <div>
        <input type="submit"/>
    </div>
</form>
<script src="<spring:url value="/static/js/groupAjax.js"/>">

</script>
</body>
</html>

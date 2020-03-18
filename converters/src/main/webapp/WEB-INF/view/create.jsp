<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <title>Title</title>
    <link href="<spring:url value="/static/css/style.css"/>" rel="stylesheet"/>
    <!--Import Google Icon Font-->
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    <!-- Compiled and minified CSS -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.min.css">


    <!--Let browser know website is optimized for mobile-->
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
</head>
<body>

<nav>
    <div class="nav-wrapper">
        <a href="<spring:url value="/"/>" class="brand-logo">Logo</a>
        <ul id="nav-mobile" class="right hide-on-med-and-down">
            <li><a href="<spring:url value="/create"/>">Create</a></li>
            <li><a href="<spring:url value="/h2console"/>">H2 Console</a></li>
        </ul>
    </div>
</nav>

<div class="container">

    <h1>Create</h1>

    <div class="row">
        <form:form modelAttribute="student" class="col s12" method="post">
            <form:hidden path="id"/>
            <div class="row">
                <div class="input-field col s6">
                    <form:input path="firstName" required="required" class="validate"/>
                    <form:label path="firstName">First Name</form:label>
                    <form:errors path="firstName" class="helper-text" data-error="wrong" data-success="right"/>
                </div>
                <div class="input-field col s6">
                    <form:input path="lastName" required="required"  class="validate"/>
                    <form:label path="lastName">Last Name</form:label>
                    <form:errors path="lastName" class="helper-text" data-error="wrong" data-success="right"/>
                </div>
            </div>
            <div class="row">
                <div class="input-field col s6">
                    <form:input path="age" required="required" class="validate"/>
                    <form:label path="age">Age</form:label>
                    <form:errors path="age" class="error-text helper-text" data-error="wrong" data-success="right"/>
                </div>
                <div class="input-field col s6">
                    <form:input path="group" required="required" type="text" class="validate"/>
                    <form:label path="group">Group</form:label>
                    <form:errors path="group" class="error-text helper-text" data-error="wrong" data-success="right"/>
                </div>
            </div>
            <div class="row">
                <div class="input-field col s6">
                    <form:input path="birthDate" required="required" type="date" class="validate"/>
                    <form:label path="birthDate">Age</form:label>
                    <form:errors path="birthDate" class="error-text helper-text" data-error="wrong" data-success="right"/>
                </div>
            </div>
            <div class="row">
                <div class="input-field col s6">
                    <form:label for="typeStudentsCaptain2" path="typeStudentsCaptain">Captain</form:label>
                    <form:radiobutton  id="typeStudentsCaptain2" path="typeStudentsCaptain" style="margin-top:20px;opacity: 1;" value="on"/>
                    <form:errors path="typeStudentsCaptain" class="error-text helper-text" data-error="wrong" data-success="right"/>
                </div>
            </div>
            <div class="row">
                <div class="input-field col s6">
                    <form:label for="typeStudentsCaptain1" path="typeStudentsCaptain">Student </form:label>
                    <form:radiobutton  id="typeStudentsCaptain1" path="typeStudentsCaptain" style="margin-top:20px;opacity: 1;" value="off"/>
                    <form:errors path="typeStudentsCaptain" class="error-text helper-text" data-error="wrong" data-success="right"/>
                </div>
            </div>
            <div class="row">
                <div class="input-field col s6">
                    <input type="submit" class="btn" />
                </div>
            </div>
        </form:form>
    </div>

    <!-- Compiled and minified JavaScript -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js"></script>
</div>
</body>
</html>

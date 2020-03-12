<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <title>Title</title>
    <link href="<spring:url value="/static/css/register.css"/>" rel="stylesheet"/>
    <style>
        .error{
            color:red;
        }
        .error-message{
            outline: 1px solid red;
        }
    </style>
</head>
<body>

<spring:url value='/' var="url"/>
<form:form method="post" modelAttribute="registerModel" action="${url}">
    <div>
        <form:label path="login">Login: </form:label>
        <form:input path="login" cssErrorClass="error"/>
        <form:errors path="login" cssClass="error-message" />
    </div>
    <div>
        <form:label path="password">Password: </form:label>
        <form:input path="password" cssErrorClass="error"/>
        <form:errors path="password" cssClass="error-message" />
    </div>
    <div>
        <form:label path="passwordСonfirmation">Password Сonfirmation: </form:label>
        <form:input path="passwordСonfirmation" cssErrorClass="error"/>
        <form:errors path="passwordСonfirmation" cssClass="error-message" />
    </div>

    <div>
        <form:label path="email">Email: </form:label>
        <form:input path="email" cssErrorClass="error"/>
        <form:errors path="email" cssClass="error-message" />
    </div>

    <div>
        <form:label path="phone">phone: </form:label>
        <form:input path="phone" placeholder="0*********" cssErrorClass="error"/>
        <form:errors path="phone" cssClass="error-message" />
    </div>

    <div>
        <form:label path="gender">Gender: </form:label>
        <form:select path="gender"  cssErrorClass="error">
            <form:option value="Male">Male</form:option>
            <form:option value="Female">Female</form:option>
        </form:select>
        <form:errors path="gender" cssClass="error-message" />
    </div>
    <div>
        <input type="submit"/>
    </div>
</form:form>


</body>
</html>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<c:set var="title" value="Students" />
<%@include file="../include/header.jsp"%>

<div class="container">
    <h1>${title}</h1>
    <table class="highlight">
        <thead>
        <tr>
            <th>Id</th>
            <th>First Name</th>
            <th>Last Name</th>
            <th>Group</th>
            <th>Birth date</th>
            <th>Edit</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${students}" var="s">
            <tr>
                <td>${s.id}</td>
                <td>${s.firstName}</td>
                <td>${s.lastName}</td>
                <td>${s.group}</td>
                <td>${s.birthDate}</td>
                <td>
                    <a href="<spring:url value="/students/edit/${s.id}"/>">Update</a>
                    <a href="<spring:url value="/students/delete/${s.id}"/>">Delete</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

    <jsp:include page="../include/scripts.jsp"/>
</div>
<%@include file="../include/footer.jsp"%>

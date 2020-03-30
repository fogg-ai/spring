<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<c:set var="title" value="Group" />
<%@include file="../include/header.jsp"%>

<div class="container">
    <h1>${title}</h1>
    <table class="highlight">
        <thead>
        <tr>
            <th>Id</th>
            <th>Group Name</th>
            <th>Edit</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${groups}" var="s">
            <tr>
                <td>${s.id}</td>
                <td>${s.name}</td>
                <td>
                    <a href="<spring:url value="/groups/edit/${s.id}"/>">Update</a>
                    <a href="<spring:url value="/groups/delete/${s.id}"/>">Delete</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

    <jsp:include page="../include/scripts.jsp"/>
</div>
<%@include file="../include/footer.jsp"%>

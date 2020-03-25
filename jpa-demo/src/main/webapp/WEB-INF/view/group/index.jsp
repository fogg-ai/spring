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
            <th>Name</th>
            <th>Edit</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${groups}" var="g">
            <tr>
                <td>${g.id}</td>
                <td>${g.name}</td>
                <td>
                    <a href="<spring:url value="/groups/edit/${g.id}"/>">Update</a>
                    <a href="<spring:url value="/groups/delete/${g.id}"/>">Delete</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

    <a href="<spring:url value="/groups/create"/>" class="btn-floating btn-large">
        <i class="material-icons">add</i>
    </a>

    <jsp:include page="../include/scripts.jsp"/>
</div>
<%@include file="../include/footer.jsp"%>

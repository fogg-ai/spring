<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<c:set var="title" value="Teachers"/>
<%@include file="../include/header.jsp" %>

<div class="container">
    <h1>${title}</h1>
    <div class="row">
        <form class="col s6 offset-s6">
            <div class="input-field">
                <i class="material-icons prefix">search</i>
                <input id="search" type="search" required name="search" placeholder="Search">
                <i class="material-icons">close</i>
            </div>
        </form>
    </div>
    <div class="row">
        <table class="highlight col s12">
            <thead>
            <tr>
                <th>Id</th>
                <th>Photo</th>
                <th>First Name</th>
                <th>Last Name</th>
                <th>Edit</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${teachersDto}" var="t">
                <tr>
                    <td>${t.id}</td>
                    <td><img src="/uploads/person.jpg" style="width: 50px; border-radius: 100px" alt="photo"/> </td>
                    <td>${t.firstName}</td>
                    <td>${t.lastName}</td>
                    <td>
                        <a class="btn" href="<spring:url value="/teachers/edit/${t.id}"/>">Update
                            <i class="material-icons right">edit</i></a>
                        <a class="btn" href="<spring:url value="/teachers/delete/${t.id}"/>">Delete
                            <i class="material-icons right">delete</i></a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>

    <c:set value="/teachers" var="url"/>
    <jsp:include page="../include/pagination.jsp"/>

    <div class="row">
        <div class="col s1 offset-s11">
            <a href="<spring:url value="/teachers/create"/>" class="btn-floating btn-large">
                <i class="material-icons">add</i>
            </a>
        </div>
    </div>
    <jsp:include page="../include/scripts.jsp"/>
</div>
<%@include file="../include/footer.jsp" %>

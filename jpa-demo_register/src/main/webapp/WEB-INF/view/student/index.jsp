<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<c:set var="title" value="Students"/>
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
                <th>Group</th>
                <th>Birth date</th>
                <secure:authorize access="hasAnyRole('ROLE_ADMIN','ROLE_TEACHER')">
                    <th>Edit</th>
                </secure:authorize>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${students}" var="s">
                <tr>
                    <td>${s.id}</td>
                    <td><img src="/uploads/person.jpg" style="width: 50px; border-radius: 100px" alt="photo"/></td>
                    <td>${s.firstName}</td>
                    <td>${s.lastName}</td>
                    <td>${s.groupName}</td>
                    <td>${s.birthDate}</td>
                    <td>
                        <secure:authorize access="hasAnyRole('ROLE_ADMIN','ROLE_TEACHER')">
                            <a class="btn" href="<spring:url value="/students/edit/${s.id}"/>">Update
                                <i class="material-icons right">edit</i>
                            </a>
                            <a class="btn" href="<spring:url value="/students/delete/${s.id}"/>">Delete
                                <i class="material-icons right">delete</i>
                            </a>
                        </secure:authorize>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>

    <c:set value="/students" var="url"/>
    <jsp:include page="../include/pagination.jsp"/>

    <div class="row">
        <secure:authorize access="hasAnyRole('ROLE_ADMIN','ROLE_TEACHER')">
            <div class="col s1 offset-s11">
                <a href="<spring:url value="/students/create"/>" class="btn-floating btn-large">
                    <i class="material-icons">add</i>
                </a>
            </div>
        </secure:authorize>
    </div>

    <jsp:include page="../include/scripts.jsp"/>
</div>
<%@include file="../include/footer.jsp" %>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<c:set var="title" value="Teachers"/>
<%@include file="../include/header.jsp" %>

<div class="container">
    <h1>${title}</h1>
    <div class="row">
        <form  method="get">
            <div class="row">
                <div class="input-field col s6">
                    <input type="text" name="firstName">First Name</input>
                </div>
                <div class="input-field col s6">
                    <input type="text" name="lastName">Last Name</input>
                </div>
                <div class="input-field col s6">
                    <button type="submit" class="btn">Find<i class="material-icons right">send</i></button>
                </div>
            </div>
        </form>
    </div>
    <div class="row">
        <table class="highlight col s12">
            <thead>
            <tr>
                <th>Id</th>
                <th>First Name</th>
                <th>Last Name</th>
                <th>Edit</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${teachersDto}" var="t">
                <tr>
                    <td>${t.id}</td>
                    <td>${t.firstName}</td>
                    <td>${t.lastName}</td>
                    <td>
                        <a class="btn" href="<spring:url value="/teachers/edit/${t.id}"/>">Update
                            <i class="material-icons right">edit</i></a>
                        <a class="btn" href="<spring:url value="/teachers/delete/${t.id}"/>">Delete
                            <i class="material-icons right">delete</i></a>
                        </a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
    <div class="row">
        <ul class="pagination center-align">
            <c:forEach begin="${page-1}" end="${page+4}" var="i">
                <c:if test="${i != 0}">
                    <c:if test="${i == page}">
                        <li class="active"><a href="?page=${i}&size=${i*5}">${i}</a></li>
                    </c:if>
                    <c:if test="${i != page}">
                        <li class="waves-effect"><a href="?page=${i}&size=${i*5}">${i}</a></li>
                    </c:if>
                </c:if>
            </c:forEach>
        </ul>
    </div>
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

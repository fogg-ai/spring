<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:set var="title" value="Groups"/>
<%@include file="../include/header.jsp" %>


<div class="container">
    <h1>${title}</h1>
    <div class="row">
        <div class="row">
            <form method="get">
                <div class="row">
                    <div class="input-field col s6">
                        <input type="text" name="name">Name</input>
                    </div>
                    <div class="input-field col s6">
                        <button type="submit" class="btn">Find<i class="material-icons right">send</i></button>
                    </div>
                </div>
            </form>
        </div>
        <table class="highlight col s12">
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
                        <a class="btn" href="<spring:url value="/groups/edit/${g.id}"/>">Update
                            <i class="material-icons right">edit</i></a>
                        <a class="btn" href="<spring:url value="/groups/delete/${g.id}"/>">Delete
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

            <c:if test="${page > 1}">
                <li class="waves-effect"><a href="?page=${page-1}&size=${size-5}"><i class="material-icons">chevron_left</i></a>
                </li>
            </c:if>
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
            <li class="waves-effect"><a href="?page=${page+1}&size=${size+5}"><i
                    class="material-icons">chevron_right</i></a></li>
        </ul>
    </div>

    <div class="row">
        <div class="col s1 offset-s11">
            <a href="<spring:url value="/groups/create"/>" class="btn-floating btn-large">
                <i class="material-icons">add</i>
            </a>
        </div>
    </div>

    <jsp:include page="../include/scripts.jsp"/>
</div>
<%@include file="../include/footer.jsp" %>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>

<c:set var="title" value="Create Students" />
<%@include file="../include/header.jsp"%>

<div class="container">

    <h1>${title}</h1>

    <div class="row">
        <form:form modelAttribute="groupDto" class="col s12" method="post">
            <form:hidden path="id"/>
            <div class="row">
                <div class="input-field col s6">
                    <form:input path="name" required="required" class="validate"/>
                    <form:label path="name">Name</form:label>
                    <form:errors path="name" class="helper-text" data-error="wrong" data-success="right"/>
                </div>
                <div class="input-field col s6">
                    <input type="submit" class="btn"/>
                </div>
            </div>
        </form:form>
    </div>

    <jsp:include page="../include/scripts.jsp"/>
</div>

<%@include file="../include/footer.jsp"%>

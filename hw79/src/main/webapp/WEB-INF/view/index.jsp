<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>

<c:set var="title" value="Home" />

<%@include file="./include/header.jsp"%>

<div class="container">
    <h1>${title}</h1>


    <jsp:include page="./include/scripts.jsp"/>
</div>
<%@include file="./include/footer.jsp"%>

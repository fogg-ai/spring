<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="secure" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <title>${title}</title>
    <!--Import Google Icon Font-->
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    <!-- Compiled and minified CSS -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.min.css">

    <link href="<spring:url value="/static/css/style.css"/>" rel="stylesheet"/>
    <!--Let browser know website is optimized for mobile-->
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
</head>
<body>

<nav>
    <div class="nav-wrapper">
        <sec:authorize access="isAuthenticated()" var="authenticated"/>
        <a href="<spring:url value="/"/>" class="brand-logo"><i class="material-icons">school</i> Academy</a>
        <a href="#" data-target="mobile-nav" class="sidenav-trigger"><i class="material-icons">menu</i></a>
        <c:choose>

            <c:when test="${authenticated}">
                <ul id="nav-mobile" class="right hide-on-med-and-down">
                    <li><a href="<spring:url value="/"/>">Home</a></li>
                    <!-- Dropdown Trigger -->
                    <li><a class="dropdown-trigger" href="#!" data-target="dropdown-students">Students<i
                            class="material-icons right">arrow_drop_down</i></a></li>
                    <li><a class="dropdown-trigger" href="#!" data-target="dropdown-groups">Groups<i
                            class="material-icons right">arrow_drop_down</i></a></li>
                    <li><a class="dropdown-trigger" href="#!" data-target="dropdown-teachers">Teachers<i
                            class="material-icons right">arrow_drop_down</i></a></li>
                    <li><a href="<spring:url value="/h2console"/>" target="_blank">H2 Console</a></li>
                    <!-- Modal Trigger -->

                    <li><a class="waves-effect waves-light modal-trigger" href="#modal2">Log out</a></li>
                </ul>
            </c:when>
            <c:otherwise>
                <ul id="nav-mobile" class="right hide-on-med-and-down">
                    <li><a class="waves-effect waves-light modal-trigger" href="#modal1">Sing in</a></li>
                </ul>
                <ul id="nav-mobile" class="right hide-on-med-and-down">
                    <li><a class="waves-effect waves-light modal-trigger" href="<spring:url value="/students/register"/>">Register</a></li>
                </ul>
            </c:otherwise>

        </c:choose>
    </div>

    <c:choose>

        <c:when test="${authenticated}">

                <ul class="sidenav" id="mobile-nav">
                    <li><a href="<spring:url value="/"/>">Home</a></li>
                    <!-- Dropdown Trigger -->
                    <li><a class="dropdown-trigger-mobile" href="#!" data-target="dropdown-students-mobile">Students<i
                            class="material-icons right">arrow_drop_down</i></a></li>
                </ul>

            <ul id="dropdown-students" class="dropdown-content">
                <secure:authorize access="hasAnyRole('USER','ADMIN','TEACHER')">
                    <li><a href="<spring:url value="/students"/>"><i class="material-icons">person</i>List</a></li>
                </secure:authorize>
                <secure:authorize access="hasAnyRole('ADMIN','TEACHER')">

                <li><a href="<spring:url value="/students/create"/>"><i class="material-icons">person_add</i>Add</a>
                </li>
                </secure:authorize>
            </ul>
            <ul id="dropdown-students-mobile" class="dropdown-content">
                <secure:authorize access="hasAnyRole('USER','ADMIN','TEACHER')">
                    <li><a href="<spring:url value="/students"/>"><i class="material-icons">person</i>List</a></li>
                </secure:authorize>
                <secure:authorize access="hasAnyRole('ADMIN','TEACHER')">
                <li><a href="<spring:url value="/students/create"/>"><i class="material-icons">person_add</i>Add</a>

                </li>
                </secure:authorize>
            </ul>

            <ul id="dropdown-groups" class="dropdown-content">
                <secure:authorize access="hasAnyRole('USER','ADMIN','TEACHER')">
                    <li><a href="<spring:url value="/groups"/>"><i class="material-icons">group</i>List</a></li>
                </secure:authorize>
                <secure:authorize access="hasAnyRole('ADMIN','TEACHER')">
                    <li><a href="<spring:url value="/groups/create"/>"><i class="material-icons">group_add</i>Add</a>
                    </li>
                </secure:authorize>
            </ul>

            <ul id="dropdown-teachers" class="dropdown-content">
                <secure:authorize access="hasAnyRole('USER','ADMIN','TEACHER')">
                    <li><a href="<spring:url value="/teachers"/>"><i class="material-icons">group</i>List</a></li>
                </secure:authorize>
                <secure:authorize access="hasRole('ADMIN')">
                <li><a href="<spring:url value="/teachers/create"/>"><i class="material-icons">person_add</i>Add</a>

                </li>
                </secure:authorize>
            </ul>
        </c:when>
    </c:choose>
</nav>


<div id="modal2" class="modal">
    <form action="<spring:url value="/logout"/>" method="POST">
        <div class="modal-content">
            Are you sure you want to go log out?
        </div>
        <div class="modal-footer">
            <a type="submit" href="#!" class="modal-close waves-effect waves-green btn">Cancel</a>
            <button type="submit" href="<spring:url value="/logout"/>" class="waves-effect waves-green btn">Log Out
            </button>
            <secure:csrfInput/>
        </div>
    </form>
</div>
<!-- Modal Structure -->
<div id="modal1" class="modal">
    <form action="<spring:url value="/login"/>" method="POST">
        <div class="modal-content">
            <h4>Sing in</h4>
            <div>
                <label>Username<input name="custom_username" required/></label>
            </div>
            <div>
                <label>Password<input name="custom_password" required type="password"/></label>
                <secure:csrfInput/>
            </div>
            <div>
                    <input type="checkbox" style="opacity: 1;width: 200px;height: 15px;" id="horns" name="rememberMe"/>
                    <label for="horns">Remember?</label>
            </div>
        </div>

        <div class="modal-footer">
            <a type="submit" href="#!" class="modal-close waves-effect waves-green btn">Cancel</a>
            <button type="submit" href="<spring:url value="/login"/>" class="waves-effect waves-green btn">Submit
            </button>
        </div>
    </form>
</div>
<div>
    <c:if test="${param.error == 'notentry'}">
        Repeat the entry, the data is incorrect.
    </c:if>

</div>

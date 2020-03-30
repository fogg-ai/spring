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
        <a href="<spring:url value="/"/>" class="brand-logo"><i class="material-icons">school</i> Academy</a>
        <a href="#" data-target="mobile-nav" class="sidenav-trigger"><i class="material-icons">menu</i></a>
        <ul id="nav-mobile" class="right hide-on-med-and-down">
            <li><a href="<spring:url value="/"/>">Home</a></li>
            <!-- Dropdown Trigger -->
            <li><a class="dropdown-trigger" href="#!" data-target="dropdown-students">Students<i class="material-icons right">arrow_drop_down</i></a></li>
            <li><a class="dropdown-trigger" href="#!" data-target="dropdown-groups">Groups<i class="material-icons right">arrow_drop_down</i></a></li>
            <li><a href="<spring:url value="/h2console"/>" target="_blank">H2 Console</a></li>
        </ul>
    </div>
    <ul class="sidenav" id="mobile-nav">
        <li><a href="<spring:url value="/"/>">Home</a></li>
        <!-- Dropdown Trigger -->
        <li><a class="dropdown-trigger-mobile" href="#!" data-target="dropdown-students-mobile">Students<i class="material-icons right">arrow_drop_down</i></a></li>
    </ul>
    <ul id="dropdown-students" class="dropdown-content">
        <li><a href="<spring:url value="/students"/>"><i class="material-icons">person</i>List</a></li>
        <li><a href="<spring:url value="/students/create"/>"><i class="material-icons">person_add</i>Add</a></li>
    </ul>
    <ul id="dropdown-students-mobile" class="dropdown-content">
        <li><a href="<spring:url value="/students"/>"><i class="material-icons">person</i>List</a></li>
        <li><a href="<spring:url value="/students/create"/>"><i class="material-icons">person_add</i>Add</a></li>
    </ul>

    <ul id="dropdown-groups" class="dropdown-content">
        <li><a href="<spring:url value="/groups"/>"><i class="material-icons">group</i>List</a></li>
        <li><a href="<spring:url value="/groups/create"/>"><i class="material-icons">group_add</i>Add</a></li>
    </ul>

</nav>

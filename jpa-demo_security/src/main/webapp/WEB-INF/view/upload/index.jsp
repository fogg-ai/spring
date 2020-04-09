<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<form method="post" enctype="multipart/form-data">
    <label>
        Name: <input name="name"/>
    </label>
    <br/>
    <input type="hidden" name="MAX_FILE_SIZE" value="5"/>
    <label>
        Image: <input type="file" multiple name="img" accept=".jpg, .jpeg, .png"/>
    </label>
    <br/>
    <input type="submit"/>
</form>
<div>${message}</div>

<table>
    <tr>
        <th>#</th>
        <th>filename</th>
        <th>image</th>
    </tr>
    <c:forEach varStatus="status" var="image" items="${allImages}">
        <tr>
            <td>${status.count}</td>
            <td>${image}</td>
            <td><img src="/uploads/${image}" alt="${image}" width="50"/></td>
        </tr>
    </c:forEach>
</table>

</body>
</html>

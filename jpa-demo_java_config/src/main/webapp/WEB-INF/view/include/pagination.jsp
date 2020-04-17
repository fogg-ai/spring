<%@page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--@elvariable id="page" type="org.springframework.data.domain.Page"--%>
<c:if test="${page.totalPages > 1}">
    <div class="row">
        <ul class="pagination center-align">
            <c:choose>
                <c:when test="${page.hasPrevious()}">
                    <li class="waves-effect">
                        <a href="${url}?page=${page.number-1}&size=${page.size}">
                            <i class="material-icons">chevron_left</i>
                        </a>
                    </li>
                </c:when>
                <c:otherwise>
                    <li class="disabled"><a href="#!"><i class="material-icons">chevron_left</i></a></li>
                </c:otherwise>
            </c:choose>
            <c:forEach var="i" begin="1" end="${page.totalPages}">
                <c:choose>
                    <c:when test="${page.number + 1 == i}">
                        <li class="active"><a href="${url}?page=${i-1}&size=${page.size}">${i}</a></li>
                    </c:when>
                    <c:otherwise>
                        <li class="waves-effect"><a href="${url}?page=${i-1}&size=${page.size}">${i}</a></li>
                    </c:otherwise>
                </c:choose>
            </c:forEach>
            <c:choose>
                <c:when test="${page.hasNext()}">
                    <li class="waves-effect">
                        <a href="${url}?page=${page.number+1}&size=${page.size}">
                            <i class="material-icons">chevron_right</i>
                        </a>
                    </li>
                </c:when>
                <c:otherwise>
                    <li class="disabled"><a href="#!"><i class="material-icons">chevron_right</i></a></li>
                </c:otherwise>
            </c:choose>
        </ul>
    </div>
</c:if>
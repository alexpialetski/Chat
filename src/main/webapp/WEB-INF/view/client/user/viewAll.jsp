<%@ include file="/WEB-INF/view/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/view/jspf/directive/taglib.jspf" %>

<fmt:setLocale value="${sessionScope.lang}"/>

<html lang="${sessionScope.lang}">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title><fmt:message key="title.clientView"/></title>
    <%@ include file="/WEB-INF/view/jspf/head.jspf" %>
</head>
<style>
    h4 {
        text-align: left;
    }
</style>
<body>
<%@ include file="/WEB-INF/view/jspf/header.jspf" %>
<a id="scrollButton"></a>
<%@ include file="/WEB-INF/view/jspf/sideBar.jspf" %>
<%@ include file="/WEB-INF/view/jspf/message.jspf" %>

<div id="container">
    <div class="content">
        <div class="info">
            <c:choose>
                <c:when test="${empty requestScope.clients}">
                    <div>
                        NO clients
                    </div>
                </c:when>
                <c:otherwise>
                    <div>
                        <c:forEach var="client" items="${requestScope.clients}">
                            <div class="subject-field">
                                <a href="?command=viewClient&id=${client.id}">${client.firstName} : ${client.lastName}</a>
                            </div>
                        </c:forEach>
                    </div>
                </c:otherwise>
            </c:choose>
            <c:if test="${empty requestScope.friends}">

            </c:if>
        </div>
    </div>
</div>
<%@ include file="/WEB-INF/view/jspf/footer.jspf" %>
</body>
<script>
    loadMessages();
    $(document).ready(function () {
        $(window).scroll(function () {
            if ($(window).scrollTop() > 10) {
                $('#scrollButton').addClass('show');
            } else {
                $('#scrollButton').removeClass('show');
            }
        });

        $('#scrollButton').on('click', function (e) {
            e.preventDefault();
            $('html, body').animate({scrollTop: 0}, '300');
        });
    });
</script>
</html>
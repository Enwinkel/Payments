<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>

<!doctype html>
<html>
<c:set var="title" value="Страница входа" scope="page"/>
<jsp:include page="/WEB-INF/templates/_head.jsp"/>
<body>
<div class="page-container-responsive">
    <div class="row space-top-8 space-8">
        <div class="col-md-5 col-middle">
            <h1 class="text-jumbo text-ginormous hide-sm">Ой!</h1>
            <h2 class="text-center">Сталася помилка.</h2>
            <h6 class="text-center">Повернутись на <a href="controller?action=login">головну</a></h6>
        </div>
    </div>
</div>
<jsp:include page="/WEB-INF/templates/_scripts.jsp"/>
</body>
</html>
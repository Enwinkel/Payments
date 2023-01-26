<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>

<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<!doctype html>
<html>
<c:set var="title" value="Платіж готоввий" scope="page"/>
<jsp:include page="/WEB-INF/templates/_head.jsp"/>
<body>
<div class="px-4 py-5 my-5 text-center">
    <h1 class="display-5 fw-bold"><fmt:message
            key="account.menu.private_office.modal.insufficient"/></h1>
    <div class="col-lg-6 mx-auto">
        <div class="d-grid gap-2 d-sm-flex justify-content-sm-center">
            <form class="transaction" method="post" action="controller?action=services">
                <button type="submit" class="btn btn-outline-secondary btn-lg px-4"><fmt:message
                        key="account.menu.private_office.modal.back"/></button>
            </form>
        </div>
    </div>
</div>
</body>
</html>

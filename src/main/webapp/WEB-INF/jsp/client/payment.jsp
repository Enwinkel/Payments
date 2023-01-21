<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>

<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<!doctype html>
<html>
<c:set var="title" value="Платіж готоввий" scope="page"/>
<jsp:include page="/WEB-INF/templates/_head.jsp"></jsp:include>
<body>
<div class="px-4 py-5 my-5 text-center">
    <h1 class="display-5 fw-bold"><fmt:message
            key="account.menu.private_office.modal.prepared"/></h1>
    <div class="col-lg-6 mx-auto">
        <p class="lead mb-4">${amount} <fmt:message
                key="account.menu.private_office.hrn"/> ${service_name}</p>
        <div class="d-grid gap-2 d-sm-flex justify-content-sm-center">
            <form class="transaction" method="post" action="controller?action=payment">
                <button type="submit" class="btn btn-primary btn-lg px-4 gap-3"><fmt:message
                        key="account.menu.private_office.modal.done"/></button>
                <input type="hidden" name="done" value="done">
            </form>
            <form class="transaction" method="post" action="controller?action=services">
                <button type="submit" class="btn btn-outline-secondary btn-lg px-4"><fmt:message
                        key="account.menu.private_office.modal.cancel"/></button>
            </form>
        </div>
    </div>
</div>
</body>
</html>

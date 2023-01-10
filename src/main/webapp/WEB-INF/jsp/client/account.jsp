<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>

<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<!doctype html>
<html>
<c:set var="title" value="Страница входа" scope="page"/>
<jsp:include page="/WEB-INF/templates/_head.jsp"></jsp:include>
<body>
<%--<jsp:include page="/WEB-INF/templates/_menu.jsp"></jsp:include>--%>
<jsp:include page="/WEB-INF/templates/_menu_customer.jsp"></jsp:include>
<div class="container">
    <div class="row">
        <div class="col">
            <div class="row">
                <label for="fullName" class="col-3 col-form-label"><fmt:message
                        key="account.menu.private_office.label.full_name"/></label>
                <div class="col">
                    <input type="text" readonly class="form-control-plaintext" id="fullName"
                           value="${fullUser.surname} ${fullUser.firstName} ${fullUser.lastName}">
                </div>
            </div>
            <div class="row">
                <label class="col-3 col-form-label">
                    <fmt:message key="account.menu.private_office.label.status_of_account"/>
                </label>
                <div class="col">
                    <tags:isblocked value="${fullUser.blocked}"/>
                </div>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="col">
            <table class="table table-hover mt-2">
                <thead>
                <tr>
                    <th scope="col"><fmt:message key="table.th.account"/></th>
                    <th scope="col"><fmt:message key="table.th.balance"/></th>
                    <th scope="col"><fmt:message key="table.th.status"/></th>
                    <th scope="col"></th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="tariff" items="${fullUser.tariffs}">
                    <tr>
                        <td>${tariff.name}</td>
                        <td>${tariff.price}</td>
                        <td>${tariff.description}</td>
                        <td>
                            <form action="controller?action=account" method="post">
                                <input type="hidden" name="tariff_id" value="${tariff.id}">
                                <div class="d-flex justify-content-end">
                                    <button type="submit" class="btn btn-outline-secondary btn-sm">
                                        <i class="material-icons">print</i>
                                    </button>
                                </div>
                            </form>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>

</div>
</div>
<jsp:include page="/WEB-INF/templates/_scripts.jsp"></jsp:include>
</body>
</html>
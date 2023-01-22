<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>

<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="ctg" uri="http://tomcat.apache.org/example-taglib" %>
<!doctype html>
<html>
<c:set var="title" value="Гаманець" scope="page"/>
<jsp:include page="/WEB-INF/templates/_head.jsp"></jsp:include>
<body>
<%--<jsp:include page="/WEB-INF/templates/_menu.jsp"></jsp:include>--%>
<jsp:include page="/WEB-INF/templates/_menu_customer.jsp"></jsp:include>
<div class="container">
    <div class="row">
        <div class="col">
            <div class="row">
                <a><ctg:hello role="${(user.roleId == 1) ? 'admin' : 'user'}"/></a>
            </div>
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
    <div class="row d-md-flex justify-content-md-end">
        <div class="d-grid">
            <form action="controller?action=account" method="post">
                <select class="form-select m-4" aria-label="Default select" name="account_sorting"
                        onchange="this.form.submit()">
                    <option value="number_ascending" ${sessionScope.account_sorting == 'number_ascending' ? 'selected' : ''}><fmt:message
                            key="account.menu.private_office.select.number_ascending"/></option>
                    <option value="number_descending" ${sessionScope.account_sorting == 'number_descending' ? 'selected' : ''}><fmt:message
                            key="account.menu.private_office.select.number_descending"/></option>
                    <option value="name_ascending" ${sessionScope.account_sorting == 'name_ascending' ? 'selected' : ''}><fmt:message
                            key="account.menu.private_office.select.name_ascending"/></option>
                    <option value="name_descending" ${sessionScope.account_sorting == 'name_descending' ? 'selected' : ''}><fmt:message
                            key="account.menu.private_office.select.name_descending"/></option>
                    <option value="amount_ascending" ${sessionScope.account_sorting == 'amount_ascending' ? 'selected' : ''}><fmt:message
                            key="account.menu.private_office.select.amount_ascending"/></option>
                    <option value="amount_descending" ${sessionScope.account_sorting == 'amount_descending' ? 'selected' : ''}><fmt:message
                            key="account.menu.private_office.select.amount_descending"/></option>
                </select>
            </form>
        </div>
    </div>
    <div class="row">
        <div class="col">
            <table class="table table-hover mt-2">
                <thead>
                <tr>
                    <th scope="col"><fmt:message key="table.th.account_name"/></th>
                    <th scope="col"><fmt:message key="table.th.account"/></th>
                    <th scope="col"><fmt:message key="table.th.balance"/></th>
                    <th scope="col"><fmt:message key="table.th.status"/></th>
                    <th scope="col"></th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="account" items="${accounts}">
                    <tr>
                        <td>${account.accountName}</td>
                        <td>${account.number}</td>
                        <td>${account.balance} <fmt:message key="account.menu.private_office.hrn"/></td>
                        <td><tags:isblocked value="${account.blocked}"/></td>
                        <td>
                            <form class="form-inline my-2 my-lg-0" method="post" action="controller?action=transactions">
                                <input type="hidden" name="account_id" value="${account.id}">
                                <input type="hidden" name="current_page" value="1">
                                <button class="btn btn-outline-success my-2 my-sm-0" type="submit"><fmt:message key="account.menu.private_office.button.account"/></button>
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
<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>

<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<!doctype html>
<html>
<c:set var="title" value="Транзакции" scope="page"/>
<jsp:include page="/WEB-INF/templates/_head.jsp"></jsp:include>
<body>
<%--<jsp:include page="/WEB-INF/templates/_menu.jsp"></jsp:include>--%>
<jsp:include page="/WEB-INF/templates/_menu_customer.jsp"></jsp:include>
<div class="container">
    <div class="row">
        <div class="col">
            <div class="row">
                <label for="number" class="col-3 col-form-label">
                    <fmt:message key="table.th.account"/>
                </label>
                <div class="col">
                    <input type="text" readonly class="form-control-plaintext" id="number"
                           value="${account.number}">
                </div>
            </div>
            <div class="row">
                <label for="balance" class="col-3 col-form-label">
                    <fmt:message key="table.th.balance"/>
                </label>
                <div class="col">
                    <input type="text" readonly class="form-control-plaintext" id="balance"
                           value="${account.balance} <fmt:message key="account.menu.private_office.hrn"/>">
                </div>
            </div>
            <div class="row">
                <label for="blocked" class="col-3 col-form-label">
                    <fmt:message key="table.th.status"/>
                </label>
                <div class="col" id="blocked">
                    <tags:isblocked value="${fullUser.blocked}"/>
                </div>
                <div class="col">
                    <div class="d-flex justify-content-end">
                        <button type="button"
                                class="btn btn-outline-secondary btn-sm"
                                name="btnLock"
                                data-toggle="modal"
                                data-target="#target">
                            ${fullUser.blocked ? '<i class="material-icons">lock_open</i>' : '<i class="material-icons">lock</i>'}
                        </button>
                    </div>
                </div>
            </div>
            <div>
                <div class="modal fade" id="target${services[0].id}" tabindex="-1" role="dialog"
                     aria-labelledby="accountBalanceModal" aria-hidden="true">
                    <div class="modal-dialog" role="document">
                        <div class="modal-content">
                            <form action="controller?action=block" method="post">
                                <div class="modal-body">
                                    <hr class="mb-4">
                                    <button class="btn btn-dark btn-lg btn-block" type="submit"><fmt:message
                                            key="account.menu.private_office.modal.block"/></button>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
            <table class="table table-striped">
                <thead>
                <tr>
                    <th scope="col">
                        <fmt:message key="table.th.date"/>
                    </th>
                    <th scope="col">
                        <fmt:message key="table.th.operation.type"/>
                    </th>
                    <th scope="col">
                        <fmt:message key="table.th.amount"/>
                    </th>
                    <th scope="col">
                        <fmt:message key="table.th.description"/>
                    </th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="transaction" items="${transactions}">
                    <tr>
                        <td>${transaction.timestamp}</td>
                        <td><tags:iscredit value="${transaction.isCredit()}"/></td>
                        <td>${transaction.amount}</td>
                        <td>${transaction.description}</td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
        <jsp:include page="/WEB-INF/templates/_scripts.jsp"></jsp:include>
</body>
</html>

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
            <table class="table table-hover mt-2">
                <thead>
                <tr>
                    <th scope="col"><fmt:message key="table.th.service"/></th>
                    <th scope="col"><fmt:message key="table.th.description"/></th>
                    <th scope="col"></th>

                </tr>
                </thead>
                <tbody>
                <c:forEach var="service" items="${services}">
                    <tr>
                        <td>${service.name}</td>
                        <td>${service.description}</td>
                        <td>
                            <button type="button" class="btn btn-outline-success my-2 my-sm-0"
                                    data-toggle="modal"
                                    data-target="#target${service.id}">
                                <fmt:message key="account.menu.private_office.button.open"/>
                            </button>
                        </td>
                    </tr>
                </c:forEach>
                <div class="modal fade" id="target${services[0].id}" tabindex="-1" role="dialog"
                     aria-labelledby="accountBalanceModal" aria-hidden="true">
                    <div class="modal-dialog" role="document">
                        <div class="modal-content">
                            <form action="controller?action=services" method="post">
                                <div class="modal-header">
                                    <h5 class="modal-title" id="exampleModalLabel">
                                        ${services[0].name}
                                    </h5>
                                    <button type="button" class="close" data-dismiss="modal"
                                            aria-label="Close">
                                        <span aria-hidden="true">&times;</span>
                                    </button>
                                </div>
                                <div class="col-sm-10">
                                    <label for="recipient-name" class="col-form-label">
                                        <fmt:message
                                                key="account.menu.private_office.modal.my_card"/>
                                    </label>
                                </div>
                                <div class="col-sm-10">
                                    <select class="form-select" aria-label="Default select example" name="account_number">
                                        <option selected>
                                            <fmt:message
                                                    key="account.menu.private_office.choose_your_card"/>
                                        </option>
                                        <c:forEach var="account" items="${accounts}">
                                            <option value="${account.number}">${account.number} ${account.balance} <fmt:message
                                                    key="account.menu.private_office.hrn"/></option>
                                        </c:forEach>
                                    </select>
                                </div>
                                <div class="modal-body">
                                    <div class="row">
                                        <div class="col-sm-10">
                                            <label for="cc-number">
                                                <fmt:message
                                                    key="account.menu.private_office.modal.recipients_card"/>
                                            </label>
                                            <input type="text" class="form-control" id="cc-number" placeholder=""
                                                   required>
                                            <div class="invalid-feedback">
                                                Credit card number is required
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-sm-10">
                                            <label for="recipient-name" class="col-form-label">
                                                <fmt:message
                                                        key="account.menu.private_office.modal.amount"/>
                                            </label>
                                            <input type="number" min="0" minlength="1"
                                                   class="form-control"
                                                   name="amount"
                                                   id="recipient-name" required>
                                        </div>
                                    </div>
                                    <hr class="mb-4">
                                    <button class="btn btn-dark btn-lg btn-block" type="submit"><fmt:message
                                            key="account.menu.private_office.modal.send"/></button>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
                <div class="modal fade" id="target${services[1].id}" tabindex="-1" role="dialog"
                     aria-labelledby="accountBalanceModal" aria-hidden="true">
                    <div class="modal-dialog" role="document">
                        <div class="modal-content">
                            <form action="controller?action=services" method="post">
                                <div class="modal-header">
                                    <h5 class="modal-title" id="exampleModalLabel2">
                                        ${services[1].name}
                                    </h5>
                                    <button type="button" class="close" data-dismiss="modal"
                                            aria-label="Close">
                                        <span aria-hidden="true">&times;</span>
                                    </button>
                                </div>
                                <div class="col-sm-10">
                                    <label for="recipient-name" class="col-form-label">
                                        <fmt:message
                                                key="account.menu.private_office.modal.my_card"/>
                                    </label>
                                </div>
                                <div class="col-sm-10">
                                    <select class="form-select" aria-label="Default select example" name="account_number">
                                        <option selected>
                                            <fmt:message
                                                    key="account.menu.private_office.choose_your_card"/>
                                        </option>
                                        <c:forEach var="account" items="${accounts}">
                                            <option value="${account.number}">${account.number} ${account.balance} <fmt:message
                                                    key="account.menu.private_office.hrn"/></option>
                                        </c:forEach>
                                    </select>
                                </div>
                                <div class="modal-body">
                                    <div class="row">
                                        <div class="col-sm-10">
                                            <label for="cc-number">
                                                <fmt:message
                                                        key="account.menu.private_office.modal.number"/>
                                            </label>
                                            <input type="text" class="form-control" id="phone-number" placeholder=""
                                                   required>
                                            <div class="invalid-feedback">
                                                Phone number is required
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-sm-10">
                                            <label for="recipient-name" class="col-form-label">
                                                <fmt:message
                                                        key="account.menu.private_office.modal.amount"/>
                                            </label>
                                            <input type="number" min="0" minlength="1"
                                                   class="form-control"
                                                   name="amount"
                                                   id="recipient-name2" required>
                                        </div>
                                    </div>
                                    <hr class="mb-4">
                                    <button class="btn btn-dark btn-lg btn-block" type="submit"><fmt:message
                                            key="account.menu.private_office.modal.top_up"/></button>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
                <div class="modal fade" id="target${services[2].id}" tabindex="-1" role="dialog"
                     aria-labelledby="accountBalanceModal" aria-hidden="true">
                    <div class="modal-dialog" role="document">
                        <div class="modal-content">
                            <form action="controller?action=services" method="post">
                                <div class="modal-header">
                                    <h5 class="modal-title" id="exampleModalLabel3">
                                        ${services[2].name}
                                    </h5>
                                    <button type="button" class="close" data-dismiss="modal"
                                            aria-label="Close">
                                        <span aria-hidden="true">&times;</span>
                                    </button>
                                </div>
                                <div class="col-sm-10">
                                    <label for="recipient-name" class="col-form-label">
                                        <fmt:message
                                                key="account.menu.private_office.modal.my_card"/>
                                    </label>
                                </div>
                                <div class="col-sm-10">
                                    <select class="form-select" aria-label="Default select example" name="account_number">
                                        <option selected>
                                            <fmt:message
                                                    key="account.menu.private_office.choose_your_card"/>
                                        </option>
                                        <c:forEach var="account" items="${accounts}">
                                            <option value="${account.number}">${account.number} ${account.balance} <fmt:message
                                                    key="account.menu.private_office.hrn"/></option>
                                        </c:forEach>
                                    </select>
                                </div>
                                <div class="modal-body">
                                    <div class="row">
                                        <div class="col-sm-10">
                                            <label for="cc-number">
                                                <fmt:message
                                                        key="account.menu.private_office.modal.internet_number"/>
                                            </label>
                                            <input type="text" class="form-control" id="internet-number" placeholder=""
                                                   required>
                                            <div class="invalid-feedback">
                                                Account number is required
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-sm-10">
                                            <label for="recipient-name" class="col-form-label">
                                                <fmt:message
                                                        key="account.menu.private_office.modal.amount"/>
                                            </label>
                                            <input type="number" min="0" minlength="1"
                                                   class="form-control"
                                                   name="amount"
                                                   id="recipient-name3" required>
                                        </div>
                                    </div>
                                    <hr class="mb-4">
                                    <button class="btn btn-dark btn-lg btn-block" type="submit"><fmt:message
                                            key="account.menu.private_office.modal.pay"/></button>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
                </tbody>
            </table>
        </div>
    </div>

</div>
</div>
<jsp:include page="/WEB-INF/templates/_scripts.jsp"></jsp:include>
</body>
</html>
<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>

<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<!doctype html>
<html>
<c:set var="title" value="Сервіси" scope="page"/>
<jsp:include page="/WEB-INF/templates/_head.jsp"/>
<head>
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"
            integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj"
            crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"
            integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN"
            crossorigin="anonymous"></script>
    <script script type="text/javascript"
            src="${pageContext.request.contextPath}/resources/js/servicesValidation.js"></script>
    <script>
        var recipientErrorMessage = '<fmt:message key="account.menu.private_office.modal.validation.recipient"/>';
        var amountErrorMessage = '<fmt:message key="account.menu.private_office.modal.validation.amount"/>';
        var phoneNumberErrorMessage = '<fmt:message key="account.menu.private_office.modal.validation.phone_number"/>';
        var internetAccountErrorMessage = '<fmt:message key="account.menu.private_office.modal.validation.internet_account"/>';
    </script>
</head>
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
                            <form action="controller?action=payment" method="post" name="paymentForm"
                                  onsubmit="return validateForm()" novalidate>
                                <div class="modal-header">
                                    <h5 class="modal-title" id="exampleModalLabel">
                                        ${services[0].name}
                                    </h5>
                                    <button type="button" class="close" data-dismiss="modal"
                                            aria-label="Close">
                                        <span aria-hidden="true">&times;</span>
                                    </button>
                                </div>
                                <c:if test="${!user.blocked}">
                                    <div class="col-sm-10">
                                        <label for="amount" class="col-form-label">
                                            <fmt:message
                                                    key="account.menu.private_office.modal.my_card"/>
                                        </label>
                                    </div>
                                    <div class="col-sm-10">
                                        <select class="form-select" aria-label="Default select example"
                                                name="account_number">
                                            <c:forEach var="account" items="${accounts}">
                                                <c:if test="${!account.blocked && account.balanceLong > 0}">
                                                    <option value="${account.number}">${account.accountName}, ${account.balance}
                                                        <fmt:message
                                                                key="account.menu.private_office.hrn"/>
                                                    </option>
                                                </c:if>
                                            </c:forEach>
                                        </select>
                                    </div>
                                    <div class="modal-body">
                                        <div class="row">
                                            <div class="col-sm-10">
                                                <label for="cc_number">
                                                    <fmt:message
                                                            key="account.menu.private_office.modal.recipients_card"/>
                                                </label>
                                                <input type="text" class="form-control" id="cc_number" name="cc_number"
                                                       maxlength="16" required>
                                                <div id="cc_numberError" class="invalid-feedback"></div>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="col-sm-10">
                                                <label for="amount" class="col-form-label">
                                                    <fmt:message
                                                            key="account.menu.private_office.modal.amount"/>
                                                </label>
                                                <input type="number" min="0" minlength="1"
                                                       class="form-control"
                                                       name="amount"
                                                       id="amount" required>
                                                <div id="amountError" class="invalid-feedback"></div>
                                                <input type="hidden" name="index" value="0">
                                            </div>
                                        </div>
                                        <hr class="mb-4">
                                        <button class="btn btn-dark btn-lg btn-block" type="submit"><fmt:message
                                                key="account.menu.private_office.modal.send"/></button>
                                    </div>
                                </c:if>
                                <c:if test="${user.blocked}">
                                    <h2 style="color:red; text-align:center; margin:20px"><fmt:message
                                            key="account.menu.private_office.modal.blocked"/></h2>
                                </c:if>
                            </form>
                        </div>
                    </div>
                </div>
                <div class="modal fade" id="target${services[1].id}" tabindex="-1" role="dialog"
                     aria-labelledby="accountBalanceModal" aria-hidden="true">
                    <div class="modal-dialog" role="document">
                        <div class="modal-content">
                            <form action="controller?action=payment" method="post" name="paymentForm2"
                                  onsubmit="return validateForm2()" novalidate>
                                <div class="modal-header">
                                    <h5 class="modal-title" id="exampleModalLabel2">
                                        ${services[1].name}
                                    </h5>
                                    <button type="button" class="close" data-dismiss="modal"
                                            aria-label="Close">
                                        <span aria-hidden="true">&times;</span>
                                    </button>
                                </div>
                                <c:if test="${!user.blocked}">
                                    <div class="col-sm-10">
                                        <label for="amount" class="col-form-label">
                                            <fmt:message
                                                    key="account.menu.private_office.modal.my_card"/>
                                        </label>
                                    </div>
                                    <div class="col-sm-10">
                                        <select class="form-select" aria-label="Default select example"
                                                name="account_number">
                                            <c:forEach var="account" items="${accounts}">
                                                <c:if test="${!account.blocked && account.balanceLong > 0}">
                                                    <option value="${account.number}">${account.accountName}, ${account.balance}
                                                        <fmt:message
                                                                key="account.menu.private_office.hrn"/>
                                                    </option>
                                                </c:if>
                                            </c:forEach>
                                        </select>
                                    </div>
                                    <div class="modal-body">
                                        <div class="row">
                                            <div class="col-sm-10">
                                                <label for="phone_number">
                                                    <fmt:message
                                                            key="account.menu.private_office.modal.number"/>
                                                </label>
                                                <input type="text" class="form-control" id="phone_number"
                                                       name="phone_number" maxlength="13" value="+380"
                                                       required>
                                                <div id="phone_numberError" class="invalid-feedback"></div>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="col-sm-10">
                                                <label for="amount2" class="col-form-label">
                                                    <fmt:message
                                                            key="account.menu.private_office.modal.amount"/>
                                                </label>
                                                <input type="number" min="0" minlength="1"
                                                       class="form-control"
                                                       name="amount"
                                                       id="amount2" required>
                                                <input type="hidden" name="index" value="1">
                                                <div id="amount2Error" class="invalid-feedback"></div>
                                            </div>
                                        </div>
                                        <hr class="mb-4">
                                        <button class="btn btn-dark btn-lg btn-block" type="submit"><fmt:message
                                                key="account.menu.private_office.modal.top_up"/></button>
                                    </div>
                                </c:if>
                                <c:if test="${user.blocked}">
                                    <h2 style="color:red; text-align:center; margin:20px"><fmt:message
                                            key="account.menu.private_office.modal.blocked"/></h2>
                                </c:if>
                            </form>
                        </div>
                    </div>
                </div>
                <div class="modal fade" id="target${services[2].id}" tabindex="-1" role="dialog"
                     aria-labelledby="accountBalanceModal" aria-hidden="true">
                    <div class="modal-dialog" role="document">
                        <div class="modal-content">
                            <form action="controller?action=payment" method="post" name="paymentForm3"
                                  onsubmit="return validateForm3()"
                                  novalidate>
                                <div class="modal-header">
                                    <h5 class="modal-title" id="exampleModalLabel3">
                                        ${services[2].name}
                                    </h5>
                                    <button type="button" class="close" data-dismiss="modal"
                                            aria-label="Close">
                                        <span aria-hidden="true">&times;</span>
                                    </button>
                                </div>
                                <c:if test="${!user.blocked}">
                                    <div class="col-sm-10">
                                        <label for="amount" class="col-form-label">
                                            <fmt:message
                                                    key="account.menu.private_office.modal.my_card"/>
                                        </label>
                                    </div>
                                    <div class="col-sm-10">
                                        <select class="form-select" aria-label="Default select example"
                                                name="account_number">
                                            <c:forEach var="account" items="${accounts}">
                                                <c:if test="${!account.blocked && account.balanceLong > 0}">
                                                    <option value="${account.number}">${account.accountName}, ${account.balance}
                                                        <fmt:message
                                                                key="account.menu.private_office.hrn"/>
                                                    </option>
                                                </c:if>
                                            </c:forEach>
                                        </select>
                                    </div>
                                    <div class="modal-body">
                                        <div class="row">
                                            <div class="col-sm-10">
                                                <label for="internet_number">
                                                    <fmt:message
                                                            key="account.menu.private_office.modal.internet_number"/>
                                                </label>
                                                <input type="text" class="form-control" id="internet_number"
                                                       name="internet_number" maxlength="30" placeholder="" required>
                                                <div id="internet_numberError" class="invalid-feedback"></div>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="col-sm-10">
                                                <label for="amount3" class="col-form-label">
                                                    <fmt:message
                                                            key="account.menu.private_office.modal.amount"/>
                                                </label>
                                                <input type="number" min="0" minlength="1"
                                                       class="form-control"
                                                       name="amount"
                                                       id="amount3" required>
                                                <input type="hidden" name="index" value="2">
                                                <div id="amount3Error" class="invalid-feedback"></div>
                                            </div>
                                        </div>
                                        <hr class="mb-4">
                                        <button class="btn btn-dark btn-lg btn-block" type="submit"><fmt:message
                                                key="account.menu.private_office.modal.pay"/></button>
                                    </div>
                                </c:if>
                                <c:if test="${user.blocked}">
                                    <h2 style="color:red; text-align:center; margin:20px"><fmt:message
                                            key="account.menu.private_office.modal.blocked"/></h2>
                                </c:if>
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
<jsp:include page="/WEB-INF/templates/_scripts.jsp"/>
</body>
</html>
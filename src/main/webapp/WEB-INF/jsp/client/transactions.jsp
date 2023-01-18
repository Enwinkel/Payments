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
            <div class="row mb-2">
                <label for="number" class="col-3 col-form-label">
                    <fmt:message key="table.th.account"/>
                </label>
                <div class="col">
                    <input type="text" readonly class="form-control-plaintext" id="number"
                           value="${account.number}">
                </div>
            </div>
            <div class="row mb-2">
                <label for="balance" class="col-3 col-form-label">
                    <fmt:message key="table.th.balance"/>
                </label>
                <div class="col">
                    <input type="text" readonly class="form-control-plaintext" id="balance"
                           value="${account.balance} <fmt:message key="account.menu.private_office.hrn"/>">
                </div>
                <div class="col">
                    <div class="d-flex justify-content-end">

                        <button type="button" class="btn btn-outline-secondary btn-sm mr-2"
                                data-toggle="modal"
                                data-target="#accountBalanceModal">
                            <i class="material-icons">account_balance_wallet</i>
                            <fmt:message key="account.menu.private_office.modal.top_up"/>
                        </button>
                    </div>
                    <!-- Modal -->
                    <div class="modal fade" id="accountBalanceModal" tabindex="-1" role="dialog"
                         aria-labelledby="accountBalanceModal" style="display: none;" aria-hidden="true">
                        <div class="modal-dialog" role="document">
                            <div class="modal-content">
                                <form action="controller?action=top_up" method="post">
                                    <div class="modal-header">
                                        <h5 class="modal-title" id="exampleModalLabel">
                                            <fmt:message key="account.menu.private_office.modal.top_up"/>
                                        </h5>
                                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                            <span aria-hidden="true">x</span>
                                        </button>
                                    </div>
                                    <div class="modal-body">
                                        <div class="row">
                                            <div class="col-sm-10">
                                                <label for="recipient-name" class="col-form-label">
                                                    <fmt:message key="account.menu.private_office.modal.amount"/>
                                                </label>
                                                <input type="number" min="0" minlength="1" class="form-control"
                                                       name="amount" id="recipient-name" required="">
                                            </div>
                                            <div class="col-md-6 mb-3">
                                                <label for="cc-number">
                                                    <fmt:message key="account.menu.private_office.modal.number"/>
                                                </label>
                                                <input type="text" class="form-control" id="cc-number" placeholder=""
                                                       required="">
                                                <div class="invalid-feedback">
                                                    Credit card number is required
                                                </div>
                                            </div>
                                            <div class="col-md-6 mb-3">
                                                <label for="cc-name">
                                                    <fmt:message
                                                            key="account.menu.private_office.modal.name_on_the_card"/>
                                                </label>
                                                <input type="text" class="form-control" id="cc-name" placeholder=""
                                                       required="">
                                                <div class="invalid-feedback">
                                                    Name on card is required
                                                </div>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="col-md-3 mb-3">
                                                <label for="cc-expiration">
                                                    <fmt:message key="account.menu.private_office.modal.expiration"/>
                                                </label>
                                                <input type="text" class="form-control" id="cc-expiration"
                                                       placeholder="" required="">
                                                <div class="invalid-feedback">
                                                    Expiration date required
                                                </div>
                                            </div>
                                            <div class="col-md-3 mb-3">
                                                <label for="cc-cvv">CVV</label>
                                                <input type="text" class="form-control" id="cc-cvv" placeholder=""
                                                       required="">
                                                <div class="invalid-feedback">
                                                    Security code required
                                                </div>
                                            </div>
                                        </div>
                                        <hr class="mb-4">
                                        <input type="hidden" name="account_id" value="${account.id}">
                                        <button class="btn btn-dark btn-lg btn-block" type="submit"><fmt:message
                                                key="account.menu.private_office.modal.top_up"/></button>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="row">
                <label for="blocked" class="col-3 col-form-label">
                    <fmt:message key="table.th.status"/>
                </label>
                <div class="col" id="blocked">
                    <tags:isblocked value="${account.blocked}"/>
                </div>
                <div class="col">
                    <div class="d-flex justify-content-end">
                        <button type="button"
                                class="btn btn-outline-secondary btn-sm"
                                name="btnLock"
                                data-toggle="modal"
                                data-target="#target">
                            ${account.blocked ? '<i class="material-icons">lock_open</i>' : '<i class="material-icons">lock</i>'}
                        </button>
                    </div>
                </div>
            </div>
            <div>
                <div class="modal fade" id="target" tabindex="-1" role="dialog"
                     aria-labelledby="accountBalanceModal" aria-hidden="true">
                    <div class="modal-dialog" role="document">
                        <div class="modal-content">
                            <form action="controller?action=block_account" method="post">
                                <div class="modal-header">
                                    <button type="button" class="close" data-dismiss="modal"
                                            aria-label="Close">
                                        <span aria-hidden="true">&times;</span>
                                    </button>
                                </div>
                                <div class="modal-body">
                                    <h5 class="modal-title" id="exampleModalLabel1">
                                        <c:if test="${account.blocked}">
                                            <fmt:message key="profile.modal.button.want_to_unblock"/>
                                        </c:if>
                                        <c:if test="${!account.blocked}">
                                            <fmt:message key="profile.modal.button.want_to_block"/>
                                        </c:if>
                                    </h5>
                                    <hr class="mb-4">
                                    <input type="hidden" name="account_id" value="${account.id}">
                                    <input type="hidden" name="current_page" value="1">
                                    <button class="btn btn-dark btn-lg btn-block" type="submit">
                                        <c:if test="${account.blocked}">
                                            <fmt:message key="account.menu.private_office.modal.send"/>
                                        </c:if>
                                        <c:if test="${!account.blocked}">
                                            <fmt:message key="account.menu.private_office.modal.block"/>
                                        </c:if>
                                    </button>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
            <table class="table table-striped">
                <div class="d-grid d-md-flex justify-content-md-end">
                    <form action="controller?action=transactions" method="post">
                        <select class="form-select m-4" aria-label="Default select" name="account_sorting"
                                onchange="this.form.submit()">
                            <option value="new_to_old"><fmt:message
                                    key="account.menu.private_office.select.new_to_old"/></option>
                            <option value="old_to_new"><fmt:message
                                    key="account.menu.private_office.select.old_to_new"/></option>
                            <option value="amount_increasing"><fmt:message
                                    key="account.menu.private_office.select.amount_increasing"/></option>
                            <option value="amount_decreasing"><fmt:message
                                    key="account.menu.private_office.select.amount_decreasing"/></option>
                        </select>
                    </form>
                </div>

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
            <nav aria-label="Navigation for countries">
                <ul class="pagination">
                    <c:if test="${current_page != 1}">
                        <li class="page-item"><a class="page-link"
                                                 href="controller?action=transactions&current_page=${current_page-1}">Previous</a>
                        </li>
                    </c:if>

                    <c:forEach begin="1" end="${no_of_pages}" var="i">
                        <c:choose>
                            <c:when test="${current_page eq i}">
                                <li class="page-item active"><a class="page-link">
                                        ${i} <span class="sr-only">(current)</span></a>
                                </li>
                            </c:when>
                            <c:otherwise>
                                <li class="page-item"><a class="page-link"
                                                         href="controller?action=transactions&current_page=${i}">${i}</a>
                                </li>
                            </c:otherwise>
                        </c:choose>
                        <input type="hidden" name="tPerPage" value="${records_per_page}">
                        <input type="hidden" name="tPage" value="${i}">
                        <input type="hidden" name="current_page" value="${i}">
                    </c:forEach>

                    <c:if test="${current_page lt no_of_pages}">
                        <li class="page-item"><a class="page-link"
                                                 href="controller?action=transactions&current_page=${current_page+1}">Next</a>
                        </li>
                    </c:if>
                </ul>
            </nav>
        </div>
        <jsp:include page="/WEB-INF/templates/_scripts.jsp"></jsp:include>
</body>
</html>

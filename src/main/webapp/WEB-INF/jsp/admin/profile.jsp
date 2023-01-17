<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>
<!doctype html>
<html>
<%--<c:set var="title" value="Аккаунт" scope="page"/>--%>
<jsp:include page="/WEB-INF/templates/_head.jsp"></jsp:include>
<body>
<jsp:include page="/WEB-INF/templates/_menu_admin.jsp"></jsp:include>
<div class="container">
    <div class="card border-dark p-2 mt-3 mb-3">
        <div class="d-flex justify-content-start">
            <div class="pl-1">
                <button type="submit"
                        class="btn btn-outline-secondary btn-sm"
                        data-toggle="modal"
                        data-target="#editUserModalCenter${fullUser.id}">
                    <i class="material-icons">create</i>
                </button>
                <!-- Modal -->
                <div class="modal fade bd-example-modal-lg"
                     id="editUserModalCenter${fullUser.id}"
                     tabindex="-1"
                     role="dialog"
                     aria-labelledby="editUserModalCenterTitle"
                     aria-hidden="true">
                    <div class="modal-dialog modal-dialog-centered modal-lg"
                         role="document">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h5 class="modal-title"
                                    id="editPhoneModalCenterTitle">
                                    <fmt:message key="profile.modal.header"/>
                                </h5>
                                <button type="button" class="close"
                                        data-dismiss="modal"
                                        aria-label="Close">
                                    <span aria-hidden="true">&times;</span>
                                </button>
                            </div>
                            <div class="modal-body">
                                <form class="mt-2" method="post" action="controller?action=edit_client">
                                    <input type="hidden" name="user_id" value="${fullUser.id}">
                                    <div class="form-row">
                                        <div class="form-group col">
                                            <div class="d-flex justify-content-between">
                                                <div class="flex-fill">
                                                    <label for="surname">
                                                        <fmt:message key="profile.modal.label.surname"/>
                                                    </label>
                                                    <input type="text" class="form-control" id="surname" name="surname"
                                                           placeholder="<fmt:message key="profile.modal.placeholder.surname"/>"
                                                           minlength="2" maxlength="40"
                                                           value="${fullUser.surname}" required>
                                                </div>
                                                <div class="flex-fill pl-1 pr-1">
                                                    <label for="firstName">
                                                        <fmt:message key="profile.modal.label.name"/>
                                                    </label>
                                                    <input type="text" class="form-control" id="firstName"
                                                           name="firstName"
                                                           placeholder="<fmt:message key="profile.modal.placeholder.name"/>"
                                                           minlength="2" maxlength="40"
                                                           value="${fullUser.firstName}" required>
                                                </div>
                                                <div class="flex-fill">
                                                    <label for="lastName">
                                                        <fmt:message key="profile.modal.label.last_name"/>
                                                    </label>
                                                    <input type="text" class="form-control" id="lastName"
                                                           name="lastName"
                                                           placeholder="<fmt:message key="profile.modal.placeholder.last_name"/>"
                                                           minlength="2" maxlength="40"
                                                           value="${fullUser.lastName}" required>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <button type="submit" name="btnChangeUser" class="btn btn-dark">
                                        <fmt:message key="profile.modal.button.save"/>
                                    </button>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="card border-dark p-2 m-0 mt-2">
            <div class="d-flex justify-content-center">
                <p class="text-uppercase font-weight-bold">
                    <fmt:message key="profile.title.profile"/>
                </p>
            </div>
            <div>
                <input type="hidden" name="user_id" value="${fullUser.id}">
                <div class="row">
                    <div class="col">
                        <div class="form-group row">
                            <label for="fullName" class="col-3 col-form-label">
                                <fmt:message key="profile.label.fullname"/>
                            </label>
                            <div class="col-9">
                                <input type="text" readonly class="form-control-plaintext" id="fullName"
                                       value="${fullUser.surname} ${fullUser.firstName} ${fullUser.lastName}">
                            </div>
                        </div>
                        <div class="form-group row">
                            <label for="address" class="col-3 col-form-label">
                                <fmt:message key="profile.label.address"/>
                            </label>
                            <div class="col-9">
                                <input type="text" readonly class="form-control-plaintext" id="address"
                                       value="${fullUser.details.city}, ${fullUser.details.street} ${fullUser.details.home}, ${fullUser.details.apartment}">
                            </div>
                        </div>
                        <div class="form-group row">
                            <label for="login" class="col-3 col-form-label">
                                <fmt:message key="profile.label.login"/>
                            </label>
                            <div class="col-9">
                                <input type="text" readonly class="form-control-plaintext" id="login"
                                       value="${fullUser.login}">
                            </div>
                        </div>
                    </div>
                    <div class="col">
                        <div class="d-flex justify-content-end">
                            <button type="submit" class="btn btn-outline-secondary btn-sm">
                                <i class="material-icons">
                                    print
                                </i>
                            </button>
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
                            <c:forEach var="account" items="${accounts}">
                                <tr>
                                    <td>${account.number}</td>
                                    <td>${account.balance}</td>
                                    <td><tags:isblocked value="${account.blocked}"/></td>
                                    <td>
                                        <form action="controller?action=block_account"
                                              method="post">
                                            <input type="hidden" name="user_id"
                                                   value="${fullUser.id}">
                                            <input type="hidden" name="account_id"
                                                   value="${account.id}">
                                            <input type="hidden" name="admin"
                                                   value="admin"/>
                                            <button type="submit"
                                                    class="btn btn-outline-secondary btn-sm"
                                                    name="btnLock">
                                                    ${account.blocked ? '<i class="material-icons">lock_open</i>' : '<i class="material-icons">lock</i>'}
                                            </button>
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
    </div>
</div>
<jsp:include page="/WEB-INF/templates/_scripts.jsp"></jsp:include>
</body>
</html>

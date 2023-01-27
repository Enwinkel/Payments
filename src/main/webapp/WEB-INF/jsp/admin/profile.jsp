<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>
<!doctype html>
<html>
<%--<c:set var="title" value="Аккаунт" scope="page"/>--%>
<jsp:include page="/WEB-INF/templates/_head.jsp"/>
<body>
<jsp:include page="/WEB-INF/templates/_menu_admin.jsp"/>
<div class="container">
    <div class="card border-dark p-2 mt-3 mb-3">

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
                                               value="profile"/>
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
<jsp:include page="/WEB-INF/templates/_scripts.jsp"/>
</body>
</html>

<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>

<!doctype html>
<html>
<c:set var="title" value="Панель администратора" scope="page"/>
<jsp:include page="/WEB-INF/templates/_head.jsp"/>
<body>
<jsp:include page="/WEB-INF/templates/_menu_admin.jsp"/>
<div class="container">
<%-- Абоненти--%>
<div class="tab-pane fade show active" id="v-pills-users" role="tabpanel"
     aria-labelledby="v-pills-users-tab">
    <div class="tab-content" id="usersTabContent">
        <h4 class="display-5 fw-bold text-center m-3"><fmt:message key="table.th.unblock_request"/></h4>
        <%-- Список абонентів --%>
        <div class="tab-pane fade show active" id="users" role="tabpanel"
             aria-labelledby="internet-tab">
            <table class="table table-striped">
                <thead>
                <tr>
                    <th scope="col"><fmt:message key="table.th.user"/></th>
                    <th scope="col"><fmt:message key="table.th.account_name"/></th>
                    <th scope="col"><fmt:message key="table.th.account"/></th>
                    <th scope="col"><fmt:message key="table.th.status"/></th>
                    <th scope="col"></th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var = "i" begin = "1" end = "${usersReq.size()}">
                    <tr>
                        <td>${usersReq[i - 1].login}</td>
                        <td>${accounts[i - 1].accountName}</td>
                        <td>${accounts[i - 1].number}</td>
                        <td><tags:isblocked value="${accounts[i - 1].blocked}"/></td>
                        <td>
                            <div class="d-flex justify-content-end">
                                <div>
                                    <form action="controller?action=block_account"
                                          method="post">
                                        <input type="hidden" name="user_id"
                                               value="${usersReq[i - 1].id}">
                                        <input type="hidden" name="account_id"
                                               value="${accounts[i - 1].id}">
                                        <input type="hidden" name="admin"
                                               value="requests"/>
                                        <button type="submit"
                                                class="btn btn-outline-secondary btn-sm"
                                                name="btnLock">
                                                <i class="material-icons">lock_open</i>
                                        </button>
                                    </form>
                                </div>
                            </div>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>
</div>
<jsp:include page="/WEB-INF/templates/_scripts.jsp"/>
</body>
</html>

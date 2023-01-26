<%@ taglib prefix="ctg" uri="http://tomcat.apache.org/example-taglib" %>
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
        <div class="row mt-4 mb-2 d-md-flex justify-content-md-end">
            <a><ctg:hello role="${(user.roleId == 1) ? 'admin' : 'user'}"/></a>
        </div>
        <%-- Список абонентів --%>
        <div class="tab-pane fade show active" id="users" role="tabpanel"
             aria-labelledby="internet-tab">
            <table class="table table-striped">
                <thead>
                <tr>
                    <th scope="col">
                        <fmt:message key="table.th.surname"/>
                    </th>
                    <th scope="col">
                        <fmt:message key="table.th.name"/>
                    </th>
                    <th scope="col">
                        <fmt:message key="table.th.middle_name"/>
                    </th>
                    <th scope="col">
                        <fmt:message key="table.th.status"/>
                    </th>
                    <th scope="col"></th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="fullUser" items="${fullUser}">
                    <tr>
                        <td>${fullUser.surname}</td>
                        <td>${fullUser.firstName}</td>
                        <td>${fullUser.lastName}</td>
                        <td><tags:isblocked value="${fullUser.blocked}"/></td>
                        <td>
                            <div class="d-flex justify-content-end">
                                <div>
                                    <form action="controller?action=block_user"
                                          method="post">
                                        <input type="hidden" name="user_id"
                                               value="${fullUser.id}">
                                        <button type="submit"
                                                class="btn btn-outline-secondary btn-sm"
                                                name="btnLock">
                                                ${fullUser.blocked ? '<i class="material-icons">lock_open</i>' : '<i class="material-icons">lock</i>'}
                                        </button>
                                    </form>
                                </div>
                                <div class="ml-1">
                                    <form method="post" action="controller?action=profile">
                                        <input type="hidden" name="user_id"
                                               value="${fullUser.id}">
                                        <button type="submit"
                                                class="btn btn-outline-secondary btn-sm">
                                            <i class="material-icons">info</i>
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

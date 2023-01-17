<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>
<html>
<c:set var="title" value="Логін" scope="page"/>
<jsp:include page="/WEB-INF/templates/_head.jsp"></jsp:include>
<head>
    <link href="https://getbootstrap.com/docs/4.0/dist/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="https://getbootstrap.com/docs/4.0/examples/sign-in/signin.css" rel="stylesheet">
</head>
<body>
<form class="mt-2" method="post" action="controller?action=registration">
    <div class="form-row">
        <div class="form-group col-md-6">
            <label for="surname">
                <fmt:message key="table.th.surname"/>
            </label>
            <input type="text" class="form-control" id="surname" name="surname"
                   placeholder="<fmt:message key="profile.modal.placeholder.surname"/>"
                   minlength="2" maxlength="40" required>
        </div>
        <div class="form-group col-md-6">
            <label for="city">
                <fmt:message key="main.new_user.label.city"/>
            </label>
            <input type="text" class="form-control" id="city" name="city"
                   placeholder="<fmt:message key="main.new_user.placeholder.city"/>"
                   minlength="2" maxlength="40" required>
        </div>
    </div>
    <div class="form-row">
        <div class="form-group col-md-6">
            <label for="firstName">
                <fmt:message key="profile.modal.label.name"/>
            </label>
            <input type="text" class="form-control" id="firstName" name="firstName"
                   placeholder="<fmt:message key="profile.modal.placeholder.name"/>"
                   minlength="2" maxlength="40" required>
        </div>
        <div class="form-group col-md-6">
            <div class="d-flex">
                <div class="flex-fill">
                    <label for="street">
                        <fmt:message key="main.new_user.label.street"/>
                    </label>
                    <input type="text" class="form-control" id="street"
                           name="street"
                           placeholder="<fmt:message key="main.new_user.placeholder.street"/>"
                           minlength="2"
                           maxlength="40" required>
                </div>
                <div class="flex-fill ml-1">
                    <label for="home">
                        <fmt:message key="main.new_user.label.home"/>
                    </label>
                    <input type="text" class="form-control" id="home" name="home"
                           placeholder="<fmt:message key="main.new_user.placeholder.home"/>"
                           minlength="2" maxlength="40" required>
                </div>
                <div class="flex-fill ml-1">
                    <label for="apartment">
                        <fmt:message key="main.new_user.label.apartment"/>
                    </label>
                    <input type="text" class="form-control" id="apartment"
                           name="apartment"
                           placeholder="125а" minlength="2" maxlength="40" required>
                </div>
            </div>
        </div>
    </div>
    <div class="form-row">
        <div class="form-group col-md-6">
            <label for="lastName">
                <fmt:message key="profile.modal.label.last_name"/>
            </label>
            <input type="text" class="form-control" id="lastName" name="lastName"
                   placeholder="<fmt:message key="profile.modal.placeholder.last_name"/>"
                   minlength="2" maxlength="40" required>
        </div>
        <div class="form-group col-md-6">
            <div class="d-flex">
                <div class="flex-fill">
                    <label for="email">
                        <fmt:message key="account.menu.edit.label.email"/>
                    </label>
                    <input type="email" class="form-control" id="email" name="email"
                           placeholder="my_email@gmail.com" minlength="2"
                           maxlength="40" required>
                </div>
                <div class="flex-fill ml-1">
                    <label for="phone">
                        <fmt:message key="main.new_user.placeholder.phone"/>
                    </label>
                    <input type="tel" class="form-control" id="phone" name="phone"
                           value="+380" size="13" maxlength="13" minlength="2"
                           maxlength="40" required
                           placeholder="+380991234567">
                </div>
            </div>
        </div>
    </div>
    <div class="form-row">
        <div class="form-group col-md-6">
            <label for="login">
                <fmt:message key="account.menu.edit.label.login"/>
            </label>
            <input type="text" class="form-control" id="login" name="login"
                   placeholder="<fmt:message key="account.menu.edit.placeholder.login"/>"
                   minlength="2" maxlength="40" required>
        </div>
        <div class="form-group col-md-6">
            <label for="password">
                <fmt:message key="account.menu.edit.label.password"/>
            </label>
            <input type="password" class="form-control" id="password" name="password"
                   placeholder="<fmt:message key="account.menu.edit.placeholder.password"/>"
                   minlength="6" maxlength="40" required>
        </div>
    </div>

    <button type="submit" class="btn btn-dark">
        <fmt:message key="account.menu.edit.button.save"/>
    </button>
</form>
</body>
</html>

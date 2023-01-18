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
    <div class="fixed-top gap-2 m-4 d-md-flex justify-content-md-end">
        <form class="mr-2" method="post" action="controller?action=login">
            <button class="btn btn-primary" type="submit"><fmt:message key="registration.label.login"/></button>
        </form>
    </div>
    <form class="mt-2" method="post" action="controller?action=registration">
        <h1 class="text-center h3 mb-4 font-weight-normal"><fmt:message key="registration.label.title"/></h1>
        <div class="form-row">
            <div class="form-group col-md-6">
                <label for="surname">
                    <fmt:message key="profile.modal.label.surname"/>
                </label>
                <input type="text" class="form-control" id="surname" name="surname"
                       placeholder="<fmt:message key="profile.modal.placeholder.surname"/>"
                       minlength="1" maxlength="40" required>
            </div>
            <div class="form-group col-md-6">
                <label for="city">
                    <fmt:message key="main.new_user.label.city"/>
                </label>
                <input type="text" class="form-control" id="city" name="city"
                       placeholder="<fmt:message key="main.new_user.placeholder.city"/>"
                       minlength="1" maxlength="40" required>
            </div>
        </div>
        <div class="form-row">
            <div class="form-group col-md-6">
                <label for="firstName">
                    <fmt:message key="profile.modal.label.name"/>
                </label>
                <input type="text" class="form-control" id="firstName" name="firstName"
                       placeholder="<fmt:message key="profile.modal.placeholder.name"/>"
                       minlength="1" maxlength="40" required>
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
                               minlength="1"
                               maxlength="40" required>
                    </div>
                    <div class="flex-fill ml-1">
                        <label for="home">
                            <fmt:message key="main.new_user.label.home"/>
                        </label>
                        <input type="text" class="form-control" id="home" name="home"
                               placeholder="<fmt:message key="main.new_user.placeholder.home"/>"
                               minlength="1" maxlength="40" required>
                    </div>
                    <div class="flex-fill ml-1">
                        <label for="apartment">
                            <fmt:message key="main.new_user.label.apartment"/>
                        </label>
                        <input type="text" class="form-control" id="apartment"
                               name="apartment"
                               placeholder="125" minlength="1" maxlength="40" required>
                    </div>
                </div>
            </div>
        </div>
        <div class="form-row">
            <div class="form-group col-md-6">
                <label for="lastName">
                    <fmt:message key="profile.modal.label.middle_name"/>
                </label>
                <input type="text" class="form-control" id="lastName" name="lastName"
                       placeholder="<fmt:message key="profile.modal.placeholder.middle_name"/>"
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
        <div class="form-row mb-4">
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
        <div class="d-grid gap-2 col-4 mx-auto">
            <button class="btn btn-lg btn-primary btn-block" type="submit"><fmt:message
                    key="registration.button.register"/></button>
        </div>
    </form>
</body>
</html>

<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>
<html>
<c:set var="title" value="Логін" scope="page"/>
<jsp:include page="/WEB-INF/templates/_head.jsp"></jsp:include>
<head>
    <link href="https://getbootstrap.com/docs/4.0/dist/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="https://getbootstrap.com/docs/4.0/examples/sign-in/signin.css" rel="stylesheet">

    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"
            integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj"
            crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"
            integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN"
            crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"
            integrity="sha384-B4gt1jrGC7Jh4AgTPSdUtOBvfO8shuf57BaghqFfPlYxofvL8/KUEfYiJOMMV+rV"
            crossorigin="anonymous"></script>
    <script script type="text/javascript"
            src="${pageContext.request.contextPath}/resources/js/registrationValidation.js"></script>
    <script>
        var lastNameErrorMessage = '<fmt:message key="registration.validation.last_name"/>';
        var firstNameErrorMessage = '<fmt:message key="registration.validation.first_name"/>';
        var middleNameErrorMessage = '<fmt:message key="registration.validation.middle_name"/>';
        var cityErrorMessage = '<fmt:message key="registration.validation.city"/>';
        var streetErrorMessage = '<fmt:message key="registration.validation.street"/>';
        var houseErrorMessage = '<fmt:message key="registration.validation.house"/>';
        var apartmentErrorMessage = '<fmt:message key="registration.validation.apartment"/>';
        var loginErrorMessage = '<fmt:message key="registration.validation.login"/>';
        var emailErrorMessage = '<fmt:message key="registration.validation.email"/>';
        var phoneErrorMessage = '<fmt:message key="registration.validation.phone"/>';
        var passwordErrorMessage = '<fmt:message key="registration.validation.password"/>';
    </script>

</head>
<body>
<div class="fixed-top gap-2 m-4 d-md-flex justify-content-md-end">
    <form class="mr-2" method="post" action="controller?action=login">
        <button class="btn btn-primary" type="submit"><fmt:message key="registration.label.login"/></button>
    </form>
</div>
<form name="myForm" class="mt-2 needs-validation" method="post" action="controller?action=registration"
      onsubmit="return validateForm()" novalidate>
    <h1 class="text-center h3 mb-4 font-weight-normal"><fmt:message key="registration.label.title"/></h1>
    <div class="form-row">
        <div class="form-group col-md-6">
            <label for="lastName">
                <fmt:message key="profile.modal.label.last_name"/>
            </label>
            <input type="text" class="form-control" id="lastName" name="lastName"
                   placeholder="<fmt:message key="profile.modal.placeholder.last_name"/>"
                   minlength="1" maxlength="40" required>
            <div id="lastNameError" class="invalid-feedback"></div>
        </div>
        <div class="form-group col-md-6">
            <label for="city">
                <fmt:message key="main.new_user.label.city"/>
            </label>
            <input type="text" class="form-control" id="city" name="city"
                   placeholder="<fmt:message key="main.new_user.placeholder.city"/>"
                   minlength="1" maxlength="40" required>
            <div id="cityError" class="invalid-feedback"></div>
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
            <div id="firstNameError" class="invalid-feedback"></div>
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
                    <div id="streetError" class="invalid-feedback"></div>
                </div>
                <div class="flex-fill ml-1">
                    <label for="house">
                        <fmt:message key="main.new_user.label.house"/>
                    </label>
                    <input type="text" class="form-control" id="house" name="house"
                           placeholder="<fmt:message key="main.new_user.placeholder.house"/>"
                           minlength="1" maxlength="40" required>
                    <div id="houseError" class="invalid-feedback"></div>
                </div>
                <div class="flex-fill ml-1">
                    <label for="apartment">
                        <fmt:message key="main.new_user.label.apartment"/>
                    </label>
                    <input type="text" class="form-control" id="apartment"
                           name="apartment"
                           placeholder="125" minlength="1" maxlength="40" required>
                    <div id="apartmentError" class="invalid-feedback"></div>
                </div>
            </div>
        </div>
    </div>
    <div class="form-row">
        <div class="form-group col-md-6">
            <label for="middleName">
                <fmt:message key="profile.modal.label.middle_name"/>
            </label>
            <input type="text" class="form-control" id="middleName" name="middleName"
                   placeholder="<fmt:message key="profile.modal.placeholder.middle_name"/>"
                   minlength="2" maxlength="40" required>
            <div id="middleNameError" class="invalid-feedback"></div>
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
                    <div id="emailError" class="invalid-feedback"></div>
                </div>
                <div class="flex-fill ml-1">
                    <label for="phone">
                        <fmt:message key="main.new_user.placeholder.phone"/>
                    </label>
                    <input type="tel" class="form-control" id="phone" name="phone"
                           value="+380" size="13" maxlength="13" minlength="2"
                           maxlength="40" required
                           placeholder="+380xxxxxxxxx">
                    <div id="phoneError" class="invalid-feedback"></div>
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
            <div id="loginError" class="invalid-feedback"></div>
        </div>
        <div class="form-group col-md-6">
            <label for="password">
                <fmt:message key="account.menu.edit.label.password"/>
            </label>
            <input type="password" class="form-control" id="password" name="password"
                   placeholder="<fmt:message key="account.menu.edit.placeholder.password"/>"
                   minlength="6" maxlength="40" required>
            <div id="passwordError" class="invalid-feedback"></div>
        </div>
    </div>
    <div class="d-grid gap-2 col-4 mx-auto">
        <button class="btn btn-lg btn-primary btn-block" type="submit"><fmt:message
                key="registration.button.register"/></button>
    </div>
</form>
</body>
</html>

<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>

<!doctype html>
<html>
<c:set var="title" value="Страница входа" scope="page"/>
<jsp:include page="/WEB-INF/templates/_head.jsp"/>
<head>
    <link href="https://getbootstrap.com/docs/4.0/dist/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="https://getbootstrap.com/docs/4.0/examples/sign-in/signin.css" rel="stylesheet">
</head>
<body class="text-center">
<div class="fixed-top gap-2 m-4 d-md-flex justify-content-md-end">
    <form class="mr-2" method="post" action="controller?action=registration">
        <button class="btn btn-primary" type="submit"><fmt:message key="registration.label.title"/></button>
    </form>
</div>
<form class="form-signin" method="post" action="controller?action=login">
    <h1 class="h3 mb-3 font-weight-normal">Payments</h1>
    <label for="login" class="sr-only"><fmt:message key="login.label.login"/> </label>
    <input type="text" id="login" name="login" class="form-control mb-2" placeholder=<fmt:message key="login.placeholder.login"/> required autofocus>
    <label for="password" class="sr-only"><fmt:message key="login.label.password"/></label>
    <input type="password" id="password" name="password" class="form-control mb-3" placeholder="<fmt:message key="login.placeholder.password"/>" required>
    <button class="btn btn-lg btn-primary btn-block" type="submit"><fmt:message key="login.button.login"/></button>
    <p class="mt-5 text-muted">&copy; 2023</p>
</form>
<jsp:include page="/WEB-INF/templates/_scripts.jsp"/>
</body>
</html>
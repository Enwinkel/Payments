<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>
<html>
<c:set var="title" value="Логін" scope="page"/>
<jsp:include page="/WEB-INF/templates/_head.jsp"></jsp:include>
<head>
    <title>Form Example</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js" integrity="sha384-B4gt1jrGC7Jh4AgTPSdUtOBvfO8shuf57BaghqFfPlYxofvL8/KUEfYiJOMMV+rV" crossorigin="anonymous"></script>
    <script>
        function validateForm() {
            var isValid = true;
            var inputs = [
                {
                    name: "name",
                    regex: /^[a-zA-Z ]{2,30}$/,
                    errorMessage: "Name must be between 2 and 30 characters and can only contain letters and spaces"
                },
                {
                    name: "login",
                    regex: /^[a-zA-Z0-9]{5,15}$/,
                    errorMessage: "Login must be between 5 and 15 characters and can only contain letters and numbers"
                },
                {
                    name: "email",
                    regex: /^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+.[a-zA-Z]{2,4}$/,
                    errorMessage: "Invalid email address"
                },
                {
                    name: "phone",
                    regex: /^\d{10}$/,
                    errorMessage: "Invalid phone number. Phone must contain only 10 digits"
                }
            ];

            for (var i = 0; i < inputs.length; i++) {
                var input = document.forms["myForm"][inputs[i].name];
                var errorElement = document.getElementById(inputs[i].name + "Error");

                if (!inputs[i].regex.test(input.value)) {
                    errorElement.innerHTML = inputs[i].errorMessage;
                    input.classList.add("is-invalid");
                    isValid = false;
                } else {
                    input.classList.remove("is-invalid");
                }
            }
            if (isValid) {
                document.getElementById("myForm").classList.add("was-validated");
            }
            return isValid;
        }
    </script>

</head>
<body>
<div class="container">
    <h1>Form Example</h1>
    <form name="myForm" id="myForm" action="submitForm" method="post" onsubmit="return validateForm()" novalidate>
        <div class="form-group">
            <label for="name">Name:</label>
            <input type="text" class="form-control" id="name" name="name" required>
            <div id="nameError" class="invalid-feedback"></div>
        </div>
        <div class="form-group">
            <label for="login">Login:</label>
            <input type="text" class="form-control" id="login" name="login" required>
            <div id="loginError" class="invalid-feedback"></div>
        </div>
        <div class="form-group">
            <label for="email">Email:</label>
            <input type="email" class="form-control" id="email" name="email" required>
            <div id="emailError" class="invalid-feedback"></div>
        </div>
        <div class="form-group">
            <label for="phone">Phone:</label>
            <input type="text" class="form-control" id="phone" name="phone" required>
            <div id="phoneError" class="invalid-feedback"></div>
        </div>
        <button type="submit" class="btn btn-primary">Submit</button>
    </form>
</div>
</body>
</html>
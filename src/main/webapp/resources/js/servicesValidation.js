function validateForm() {
    var isValid = true;
    var inputs = [
        {
            name: "cc_number",
            regex: /^\d{16}$/,
            errorMessage: recipientErrorMessage
        },
        {
            name: "amount",
            regex: /^\d{1,6}$/,
            errorMessage: amountErrorMessage
        }
    ];

    var formName = "paymentForm";

    isValid = validate(inputs, formName, isValid);
    return isValid;
}

function validateForm2() {
    var isValid = true;
    var inputs = [
        {
            name: "phone_number",
            regex: /^\+38\d{10}$/,
            errorMessage: phoneNumberErrorMessage
        },
        {
            name: "amount2",
            regex: /^\d{1,6}$/,
            errorMessage: amountErrorMessage
        }
    ];

    var formName = "paymentForm2";

    isValid = validate(inputs, formName, isValid);
    return isValid;
}

function validateForm3() {
    var isValid = true;
    var inputs = [
        {
            name: "internet_number",
            regex: /^\d{1,30}$/,
            errorMessage: internetAccountErrorMessage
        },
        {
            name: "amount3",
            regex: /^\d{1,6}$/,
            errorMessage: amountErrorMessage
        }
    ];

    var formName = "paymentForm3";

    isValid = validate(inputs, formName, isValid);
    return isValid;
}

function validate(inputs, formName, isValid) {
    for (var i = 0; i < inputs.length; i++) {
        var input = document.forms[formName][inputs[i].name];
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
        document.getElementById(formName).classList.add("was-validated");
    }
    return isValid;
}
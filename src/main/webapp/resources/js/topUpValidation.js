function validateForm() {
    var isValid = true;
    var inputs = [
        {
            name: "amount",
            regex: /^\d{1,6}$/,
            errorMessage: amountErrorMessage
        },
    ];

    for (var i = 0; i < inputs.length; i++) {
        var input = document.forms["topUpForm"][inputs[i].name];
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
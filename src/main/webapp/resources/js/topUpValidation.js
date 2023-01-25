function validateForm() {
    var isValid = true;
    var userInput =
        {
            name: "amount",
            regex: /^\d{1,6}$/,
            errorMessage: amountErrorMessage
        };

    var input = document.forms["topUpForm"][userInput.name];
    var errorElement = document.getElementById(userInput.name + "Error");

    if (!userInput.regex.test(input.value)) {
        errorElement.innerHTML = userInput.errorMessage;
        input.classList.add("is-invalid");
        isValid = false;
    } else {
        input.classList.remove("is-invalid");
    }

    if (isValid) {
        document.getElementById("myForm").classList.add("was-validated");
    }
    return isValid;
}
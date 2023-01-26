function validateForm() {
    var isValid = true;
    var inputs = [
        {
            name: "lastName",
            regex: /^([A-Za-z][A-Za-z\-\']{1,50})|([А-ЯЁIЇҐЄа-яёіїґє][А-ЯЁIЇҐЄа-яёіїґє\-\']{2,30})$/,
            errorMessage: lastNameErrorMessage
        },
        {
            name: "firstName",
            regex: /^([A-Za-z][A-Za-z\-\']{1,50})|([А-ЯЁIЇҐЄа-яёіїґє][А-ЯЁIЇҐЄа-яёіїґє\-\']{2,30})$/,
            errorMessage: firstNameErrorMessage
        },
        {
            name: "middleName",
            regex: /^([A-Za-z][A-Za-z\-\']{1,50})|([А-ЯЁIЇҐЄа-яёіїґє][А-ЯЁIЇҐЄа-яёіїґє\-\']{2,30})$/,
            errorMessage: middleNameErrorMessage
        },
        {
            name: "city",
            regex: /^([A-Za-z][A-Za-z\-\']{1,50})|([А-ЯЁIЇҐЄа-яёіїґє][А-ЯЁIЇҐЄа-яёіїґє\-\']{2,30})$/,
            errorMessage: cityErrorMessage
        },{
            name: "street",
            regex: /^([A-Za-z][A-Za-z\-\']{1,50})|([А-ЯЁIЇҐЄа-яёіїґє][А-ЯЁIЇҐЄа-яёіїґє\-\']{2,30})$/,
            errorMessage: streetErrorMessage
        },{
            name: "house",
            regex: /^[1-9]\d*(?:[ -]?(?:[a-zA-Z]+|[1-9]\d*|[а-яА-Я]+))?$/,
            errorMessage: houseErrorMessage
        },{
            name: "apartment",
            regex: /^([a-zA-Z]|[а-яА-Я])?\d+([a-zA-Z]|[а-яА-Я])?$/,
            errorMessage: apartmentErrorMessage
        },
        {
            name: "login",
            regex: /^[a-zA-Z\d]{5,15}$/,
            errorMessage: loginErrorMessage
        },
        {
            name: "email",
            regex: /^[a-zA-Z\d._-]+@[a-zA-Z\d.-]+.[a-zA-Z]{2,4}$/,
            errorMessage: emailErrorMessage
        },
        {
            name: "phone",
            regex: /^\+38\d{10}$/,
            errorMessage: phoneErrorMessage
        },
        {
            name: "password",
            regex: /.{6,30}$/,
            errorMessage: passwordErrorMessage
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
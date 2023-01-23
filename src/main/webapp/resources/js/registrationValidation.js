function validateForm() {
    var isValid = true;
    var inputs = [
        {
            name: "lastName",
            regex: /^([A-Za-z][A-Za-z\-\']{1,50})|([А-ЯЁIЇҐЄа-яёіїґє][А-ЯЁIЇҐЄа-яёіїґє\-\']{2,30})$/,
            errorMessage: "Last name must be between 2 and 30 characters and can only contain letters and spaces"
        },
        {
            name: "firstName",
            regex: /^([A-Za-z][A-Za-z\-\']{1,50})|([А-ЯЁIЇҐЄа-яёіїґє][А-ЯЁIЇҐЄа-яёіїґє\-\']{2,30})$/,
            errorMessage: "Name must be between 2 and 30 characters and can only contain letters and spaces"
        },
        {
            name: "middleName",
            regex: /^([A-Za-z][A-Za-z\-\']{1,50})|([А-ЯЁIЇҐЄа-яёіїґє][А-ЯЁIЇҐЄа-яёіїґє\-\']{2,30})$/,
            errorMessage: "Middle name must be between 2 and 30 characters and can only contain letters and spaces"
        },
        {
            name: "city",
            regex: /^([A-Za-z][A-Za-z\-\']{1,50})|([А-ЯЁIЇҐЄа-яёіїґє][А-ЯЁIЇҐЄа-яёіїґє\-\']{2,30})$/,
            errorMessage: "Middle name must be between 2 and 30 characters and can only contain letters and spaces"
        },{
            name: "street",
            regex: /^([A-Za-z][A-Za-z\-\']{1,50})|([А-ЯЁIЇҐЄа-яёіїґє][А-ЯЁIЇҐЄа-яёіїґє\-\']{2,30})$/,
            errorMessage: "Street name must be between 2 and 30 characters and can only contain letters and spaces"
        },{
            name: "house",
            regex: /^[1-9]\d*(?:[ -]?(?:[a-zA-Z]+|[1-9]\d*|[а-яА-Я]+))?$/,
            errorMessage: "Invalid house number"
        },{
            name: "apartment",
            regex: /^([a-zA-Z]|[а-яА-Я])?\d+([a-zA-Z]|[а-яА-Я])?$/,
            errorMessage: "Invalid apartment number"
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
            regex: /^\+38\d{10}$/,
            errorMessage: "Invalid phone number. Phone must contain only 10 digits"
        },
        {
            name: "password",
            regex: /.{6,30}$/,
            errorMessage: "Password must be between 6 and 30 characters"
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
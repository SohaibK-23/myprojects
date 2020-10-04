// Validation form for inputs

var username = document.forms['form']['username'];
var password = document.forms['form']['password'];

var emailError = document.getElementById('email_error');
var passError = document.getElementById('pass_error');

email.addEventListener('textInput', email_Verify);
password.addEventListener('textInput', pass_Verify);

function validated()
{
    if(username.value.length < 9){
        username.style.border = "1px solid red";
        emailError.style.display = "block";
        username.focus();
        return false;
    }

    if(password.value.length < 6)
    {
        password.style.border = "1px solid red";
        passError.style.display = "block";
        password.focus();
        return false;
    }
}

function email_Verify()
{
    if(username.value.length >= 8){
        username.style.border = "1px solid silver";
        emailError.style.display = "none";
        return true;
    }
}

function pass_Verify()
{
    if(password.value.length >= 8){
        password.style.border = "1px solid silver";
        passError.style.display = "none";
        return true;
    }
}



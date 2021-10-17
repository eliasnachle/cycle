function registerContratante() {
    var formRegister = new URLSearchParams(new FormData(form_register));
    
    fetch(`/usuarios/register-contratante/${generateKey()}`, {
        method: "POST",
        body: formRegister
    }).then(function (response) {
        if(response.ok) {
            window.location.href = "login.html";
        }else {
            response.text().then(function (erroMessage) {
                console.log(erroMessage);
            });
        }
    })
    return false;
}

function generateKey() {
    var caracter = "AaBbCcDdEeFfGgHhIiJjKkLlMmNnOoPpQqRrSsTtUuVvWwXxYyZz123456789";
    var key = "";
    
    for(i = 1; i <= 30; i++) {
        key += caracter[parseInt(Math.random() * caracter.length)];
        
    }
    return key.toString();
}
const togglePassword = document.querySelector('.section-login__content__form--input > i'),
login = document.querySelector('#ipt_login'),
password = document.querySelector('#ipt_password'),
loginReg = /^([À-úA-z0-9._-]+@[a-z0-9._-]+\.[A-z0-9_-]+)$/,
passwordReg = /^[0-9]$/;

login.addEventListener('keyup', () => {    
    const iptLoginValue = login.value;
    if(iptLoginValue.match(loginReg)){
        ipt_login.style.border = '1.5px solid #25A9EE';        
        ipt_login.style.color = '#25A9EE';
    } else {
        ipt_login.style.border = '1.5px solid #FB4B4B';
        ipt_login.style.color = '#FB4B4B';
    }
});

togglePassword.addEventListener('click', () => {
    if(password.type == 'password'){
        togglePassword.className = 'fa fa-eye toggle-passowrd';
        password.type = 'text';
    } else {
        togglePassword.className = 'fa fa-eye-slash toggle-passowrd';
        password.type = 'password'
    }
});

function authenticateLogin() { 
    submit_button.disabled = true
    if (isGerenteOrSuporte.value == "Gerente") {
        var formLogin = new URLSearchParams(new FormData(form_login));
        fetch("/usuarios/login-contratante", {
            method: "POST",
            body: formLogin
        }).then(resposta => {
            if (resposta.ok) {
                resposta.json().then(json => {

                    sessionStorage.id_user_contratant = json.idUsuarioContratante;
                    sessionStorage.name_user_contratante = json.nomeContratante;
                    sessionStorage.email_user_contratante = json.emailContratante;
                    sessionStorage.cpf_user_contratante = json.cpfContratante;
                    sessionStorage.idPlano_user_contratante = json.idPlano;
                    sessionStorage.pass_user_contratante = json.senhaContratante;
                    submit_button.disabled = false
                    window.location.href = "dashboard-manager/dash-adm-maquina.html";

                });
            } else {
                var erroMessage = "Usuario e/ou senha invalidos"
                console.log(erroMessage);
                login_error_message.innerHTML = erroMessage
                login_error_message.style.display = "block"
                submit_button.disabled = false
            }
        });
        return false;
    } else if (isGerenteOrSuporte.value == "Suporte") {
        var formLogin = new URLSearchParams(new FormData(form_login));
        fetch("/usuarios/login-suporte", {
            method: "POST",
            body: formLogin
        }).then(resposta => {
            if (resposta.ok) {
                resposta.json().then(json => {
                    localStorage.hasSupportSession = true;
                    sessionStorage.idSupportUser = json.idUsuarioSuporte;
                    localStorage.nameUserSupport = json.nomeSuporte;
                    localStorage.idContractorSession = json.idUsuarioContratante;
                    window.location.href = "dashboard-support/dashboard.html";
                });
            } else {
                var erroMessage = "Usuario e/ou senha invalidos"
                console.log(erroMessage);
                login_error_message.innerHTML = erroMessage
                login_error_message.style.display = "block"
                submit_button.disabled = false
            }
        });
        return false;
    } else {
        var errorMessage = "Por favor selecione um tipo de login (Gerente ou Suporte)"
        console.log(errorMessage);
        login_error_message.innerHTML = errorMessage
        login_error_message.style.display = "block"
        submit_button.disabled = false
        return false;
    }
}
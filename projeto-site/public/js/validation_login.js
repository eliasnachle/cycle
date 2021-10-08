const togglePassword = document.querySelector('.section-login__content__form--input > i'),
login = document.querySelector('#ipt_login'),
password = document.querySelector('#ipt_password'),
loginReg = /^([À-úA-z0-9._-]+@[a-z0-9._-]+\.[A-z0-9_-]+)$/,
passwordReg = /^(?=.*[0-9]{1})(?=.*[\W]{1})(?=.*[a-z]{1})[a-zA-Z0-9\W]{6,30}$/;

// Validação Login
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

// Validação Senha
password.addEventListener('keyup', () => {    
    const iptPasswordValue = password.value;
    if(iptPasswordValue.match(passwordReg)){
        password.style.border = '1.5px solid #25A9EE';        
        password.style.color = '#25A9EE';
    } else {
        password.style.border = '1.5px solid #FB4B4B';
        password.style.color = '#FB4B4B';
    }
});

// Toggle Password
togglePassword.addEventListener('click', () => {
    if(password.type == 'password'){
        togglePassword.className = 'fa fa-eye toggle-passowrd';
        password.type = 'text';
    } else {
        togglePassword.className = 'fa fa-eye-slash toggle-passowrd';
        password.type = 'password'
    }
});
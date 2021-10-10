const email = document.querySelector('#ipt_email'),
emailReg  = /^([À-úA-z0-9._-]+@[a-z0-9._-]+\.[A-z0-9_-]+)$/;

// Validação E-mail
email.addEventListener('keyup', () => {    
    const iptEmailValue = email.value;
    if(iptEmailValue.match(emailReg)){
        ipt_email.style.border = '1.5px solid #25A9EE';        
        ipt_email.style.color = '#25A9EE';
    } else {
        ipt_email.style.border = '1.5px solid #FB4B4B';
        ipt_email.style.color = '#FB4B4B';
    }
});
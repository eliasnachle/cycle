function viewPassword()
{
  var passwordInput = document.getElementById('password-field');
  var passStatus = document.getElementById('pass-status');
 
  if (passwordInput.type == 'password'){
    passwordInput.type='text';
    passStatus.className='fa fa-eye';
    
  }
  else{
    passwordInput.type='password';
    passStatus.className='fa fa-eye-slash';
  }
}

const togglePassword = document.querySelector('.section-login_content_form--input > i'),
nome = document.querySelector('#ipt_nome'),
cpf = document.querySelector('#ipt_cpf'),
login = document.querySelector('#ipt_login'),
password = document.querySelector('#password-field'),
loginReg = /^([À-úA-z0-9.-]+@[a-z0-9.-]+\.[A-z0-9_-]+)$/,
passwordReg = /^(?=.[0-9]{1})(?=.[\W]{1})(?=.*[a-z]{1})[a-zA-Z0-9\W]{6,30}$/;
nomeReg = /^[a-z ,.'-]+$/i;
cpfReg = /^\d{3}\.\d{3}\.\d{3}\-\d{2}$/;

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

//Validação Senha
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

//Validação Nome
nome.addEventListener('keyup', () => {    
  const iptNameValue = nome.value;
  if(iptNameValue.match(nomeReg)){
      nome.style.border = '1.5px solid #25A9EE';        
      nome.style.color = '#25A9EE';
  } else {
      nome.style.border = '1.5px solid #FB4B4B';
      nome.style.color = '#FB4B4B';
  }
});

//Validação CPF
function cpfMask(){
  var iptCpf = document.getElementById('ipt_cpf');
  console.log(iptCpf.value)
  if(iptCpf.value.length == 3 || iptCpf.value.length == 7){
    iptCpf.value +='.';
  } else if(iptCpf.value.length == 11){
    iptCpf.value +='-';
  }
}
cpf.addEventListener('keyup', () => {    
  const iptCpfValue = cpf.value;
  if(iptCpfValue.match(cpfReg)){
      cpf.style.border = '1.5px solid #25A9EE';        
      cpf.style.color = '#25A9EE';
  } else {
      cpf.style.border = '1.5px solid #FB4B4B';
      cpf.style.color = '#FB4B4B';
  }
});


function copyTextToClipboard(text) {
  var textArea = document.createElement("textarea");

  textArea.value = text;


  document.body.appendChild(textArea);
  textArea.select();

  try {
    var successful = document.execCommand('copy');
    var msg = successful ? 'successful' : 'unsuccessful';
    console.log('Copying text command was ' + msg);
    alert('Código pix copiado para área de transferência!');
  } catch (err) {
    console.log('Oops, unable to copy');
    window.prompt("Copie para área de transferência: Ctrl+C e tecle Enter", text);
  }

  document.body.removeChild(textArea);
}

// Teste
var copyTest = document.querySelector('.copyTest');
copyTest.addEventListener('click', function(event) {
  copyTextToClipboard('01321441');
});
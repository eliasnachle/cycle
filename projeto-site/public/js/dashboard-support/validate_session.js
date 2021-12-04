const headerUsername = document.getElementById('header_username'),
headerUsernameSession = localStorage.getItem("nameUserSupport");
let nome_usuario = localStorage.nameUserSupport;

function logoff(){
    finalizar_sessao();
    sessionStorage.clear();
    localStorage.clear();
    authenticateLogin();
}

function authenticateLogin(){    
    if(localStorage.getItem('hasSupportSession') == null){
        window.location.pathname = 'login.html';
    } else{
        headerUsername.innerHTML = `Olá, ${headerUsernameSession}`;
    }
}

function callMethods(){
    authenticateLogin();
    getAlerts();
}

function finalizar_sessao() {
    fetch(`/usuarios/sair/${nome_usuario}`, {cache:'no-store'}); 
}

window.addEventListener('load', callMethods)
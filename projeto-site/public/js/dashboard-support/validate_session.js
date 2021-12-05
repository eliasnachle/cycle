const headerUsername = document.getElementById('header_username'),
headerUsernameSession = localStorage.getItem("nameUserSupport");
let nome_usuario = localStorage.nameUserSupport;
let idUsuarioSuporte = sessionStorage.idSupportUser;

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
    validar_sessao();
}

function finalizar_sessao() {
    fetch(`/usuarios/sair/${idUsuarioSuporte}`, {cache:'no-store'}); 
}

function validar_sessao() {
    fetch(`/usuarios/sessao/${idUsuarioSuporte}`, {cache:'no-store'})
    .then(resposta => {
        if (resposta.ok) {
            resposta.text().then(texto => {
                console.log('Sessão :) ', texto);    
            });
        } else {
            console.error('Sessão :.( ');
            logoff();
        } 
    });    
}

window.addEventListener('load', callMethods)
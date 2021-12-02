let login_usuario;
let nome_usuario;
let cpf_usuario;

function verificateAuth(){
    login_usuario = sessionStorage.email_user_contratante;
    nome_usuario = sessionStorage.name_user_contratante;
    cpf_usuario = sessionStorage.cpf_user_contratante;
    idContratante = sessionStorage.id_user_contratant;
    console.log(nome_usuario)
    console.log(idContratante)

    if (login_usuario == undefined)  {
        redirecionar_login();
    } else {
        console.log("teste")

        name_header.innerHTML = nome_usuario;
        // name_user.innerHTML = nome_usuario;
        // email_usuario.innerHTML = login_usuario;
        // cpf_user.innerHTML = cpf_usuario;

        validar_sessao();
    }
}

function validar_sessao() {
    fetch(`/usuarios/sessao/${nome_usuario}`, {cache:'no-store'})
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

function logoff() {
    finalizar_sessao();
    sessionStorage.clear();
    redirectLogin();
}

function redirectLogin(){
    window.location.href = '../login.html'
}

function finalizar_sessao() {
    fetch(`/usuarios/sair/${nome_usuario}`, {cache:'no-store'}); 
}
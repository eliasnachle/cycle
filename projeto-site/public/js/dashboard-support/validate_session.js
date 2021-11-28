const headerUsername = document.getElementById('header_username'),
headerUsernameSession = localStorage.getItem("nameUserSupport");

function logoff(){
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

window.addEventListener('load', authenticateLogin)
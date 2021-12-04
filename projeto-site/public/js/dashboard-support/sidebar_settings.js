let typeModal;
const linkColor = document.querySelectorAll('.sidebar-settings__content--link'),
containerSettings = document.querySelector('.dashboard__container--settings'),
mainSettings = document.querySelector('.main-settings'),
usernameReg = /^[A-z ]{3,15}$/,
passwordReg = /^(?=.*[0-9]{1})(?=.*[\W]{1})(?=.*[a-z]{1})[a-zA-Z0-9\W]{6,30}$/;

function getUserDetails() {
    var idSupportUser = sessionStorage.idSupportUser;
    fetch(`/dashboardSupport/userDetails${idSupportUser}`)
    .then((resposta) => {
        if (resposta.ok) {
            resposta.json().then(function (resposta) {
                console.log(`Dados recebidos: ${JSON.stringify(resposta)}`);                
                function colorLink(){
                    var validateMobile = window.innerWidth <= 768 ? mainSettings.classList.add('main-settings-mobile-actived-aside') : '';    
                    linkColor.forEach(i => i.classList.remove('sidebar-settings__content--actived'));    
                    this.classList.add('sidebar-settings__content--actived');
                    switch(this.id) {
                        case 'sidebar-profile': 
                            typeModal = 'username';
                            validateMobile;
                            profileSection(resposta);
                            break;
                        case 'sidebar-security':
                            typeModal = 'password';
                            validateMobile;   
                            secutirySection(resposta);
                            break;
                    }
                }
                typeModal = 'username';
                profileSection(resposta);
                linkColor.forEach(i => i.addEventListener('click', colorLink));                   
            });
        } else {
            console.error('Nenhum dado encontrado ou erro na API');
        }
    })
    .catch(function (error) {
        console.error(`Erro na obtenção das publicações: ${error.message}`);
    });
}

function profileSection(userDetails){    
    containerSettings.innerHTML = `
    <div class="dashboard__container--settings--title">
    <div class="dashboard__container--settings--title--content">
    <h3>Preferências de Conta</h3>
    <span>Configurações gerais da conta.</span>
    </div>        
    </div>
    <div class="dashboard__container--settings--item">
    <h2>Nome de usuário</h2>
        <span>${userDetails[0].nomeSuporte}</span>    
    <button value="username" onclick="modalEditUsername(this.value)">Editar</button>
    </div>
    <div class="dashboard__container--settings--item">
    <h2>E-mail</h2>
        <span>${userDetails[0].emailSuporte}</span> 
    </div>`;
    sectionsCloseOption();
}

function secutirySection(userDetails){   
    containerSettings.innerHTML = `
    <div class="dashboard__container--settings--title">
        <div class="dashboard__container--settings--title--content">
            <h3>Segurança</h3>
            <span>Alterar Senha.</span>
        </div>
    </div>
    <div class="dashboard__container--settings--item">
        <h2>Senha</h2>
        <span>${userDetails[0].senhaSuporte}</span>
        <button value="password" onclick="modalEditUsername(this.value)">Editar</button>
    </div>`;
    sectionsCloseOption();
}

function sectionsCloseOption(){
    if(window.innerWidth <= 768 ){
        const containerSettingsTitle = document.querySelector('.dashboard__container--settings--title');
        containerSettingsTitle.insertAdjacentHTML('afterbegin' ,`<button onclick="returnSettingsSidebar()"><i class="icon-return"></i></button>`);
    }
}
function returnSettingsSidebar(){
    mainSettings.classList.remove('main-settings-mobile-actived-aside');
}

function modalEditUsername(labelValue){
    typeModal = labelValue;
    let title,
    placeholder,
    icon;
    switch (labelValue){
        case 'username':
            title = 'Editar nome de usuário';
            placeholder = 'Novo nome de usuário';
            icon = 'profile';
            break;
        case 'password':
            title = 'Editar campo de senha';
            placeholder = 'Nova senha';
            icon = 'lock';
            break;
    }
    const modalEdit = document.createElement("section");
    modalEdit.classList.add('overlay__modal');
    modalEdit.innerHTML += `
      <div class="overlay__modal--modal">
        <div class="overlay__modal--modal--content--close" onclick="closeModal()">
            <svg fill="none" height="20" viewBox="0 0 19 19" width="20" xmlns="http://www.w3.org/2000/svg">
            <path clip-rule="evenodd" d="M18.5578 18.5579C17.9682 19.1474 17.0124 19.1474 16.4228 18.5579L0.442158 2.57712C-0.147386 1.98757 -0.147386 1.03171 0.442158 0.442157C1.03171 -0.147388 1.98756 -0.147388 2.57711 0.442171L18.5578 16.4229C19.1474 17.0125 19.1474 17.9683 18.5578 18.5579Z" fill="#161616" fill-rule="evenodd"></path>
            <path clip-rule="evenodd" d="M0.442158 18.5579C-0.147386 17.9683 -0.147386 17.0125 0.442158 16.4229L16.4228 0.442169C17.0124 -0.14739 17.9682 -0.14739 18.5578 0.442169C19.1474 1.03171 19.1474 1.98757 18.5578 2.57711L2.57711 18.5579C1.98756 19.1474 1.03171 19.1474 0.442158 18.5579Z" fill="#161616" fill-rule="evenodd"></path>
            </svg>
        </div>
        <div class="overlay__modal--modal--content">
          <h3>${title}</h3>
          <div class="overlay__modal--modal--content--ipt">
            <i class="icon-${icon}"></i>
            <input type="text" id="ipt_edit_value" placeholder="${placeholder}">
          </div>      
          <button onclick="confirmUpdateValue(this.${typeModal})">Confirmar</button>
          <span id="msg_validate_modal_edit_value"></span>
        </div>
      </div>`;
    body.appendChild(modalEdit);
}

function confirmUpdateValue(){
    const valueIptEditValue = document.getElementById('ipt_edit_value').value;
    switch(typeModal){
        case 'username':                 
            updateUsername(valueIptEditValue);
            localStorage.setItem('nameUserSupport', valueIptEditValue);
            location.reload();
            break;
        case 'password':            
            updatePassword(valueIptEditValue);
            // location.reload();
            break;
    }
}

function updateUsername(valueIptEditValue){
    msgValidate = document.getElementById('msg_validate_modal_edit_value');
    if(valueIptEditValue.match(usernameReg)){
        putUpdateUsername(valueIptEditValue);
        closeModal();
    } else{
       msgValidate.innerHTML = 'Ops! esse usuário é inválido!' 
    }
}

function putUpdateUsername(valueIptEditValue) {
    var idSupportUser = sessionStorage.idSupportUser;
    fetch(`/dashboardSupport/updateUsernameSupport/${idSupportUser}/${valueIptEditValue}`, {
        method : "PUT"
    }).then(() => {
        res.status(204).send('Nome de usuário alterado com sucesso!');
    }).catch(err => {
        console.error(err)
    });
}

function updatePassword(valueIptEditValue){
    msgValidate = document.getElementById('msg_validate_modal_edit_value');
    if(valueIptEditValue.match(passwordReg)){
        putUpdatePassword(valueIptEditValue);
        closeModal();
    } else{
        msgValidate.innerHTML = 'Ops! essa senha é inválida!';
    }    
}

function putUpdatePassword(valueIptEditValue) {
    var idSupportUser = sessionStorage.idSupportUser;
    fetch(`/dashboardSupport/updatePasswordSupport/${idSupportUser}/${valueIptEditValue}`, {
        method : "PUT"
    }).then(() => {
        res.status(204).send('Nome de usuário alterado com sucesso!');
    }).catch(err => {
        console.error(err)
    });
}

function callMethodsSettings(){    
    getUserDetails();
}

window.addEventListener('load', callMethodsSettings);
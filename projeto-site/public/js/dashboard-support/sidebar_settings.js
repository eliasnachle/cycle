const linkColor = document.querySelectorAll('.sidebar-settings__content--link'),
containerSettings = document.querySelector('.dashboard__container--settings'),
mainSettings = document.querySelector('.main-settings');

function colorLink(){
    linkColor.forEach(i => i.classList.remove('sidebar-settings__content--actived'));
    this.classList.add('sidebar-settings__content--actived');
    const validateMobile = window.innerWidth <= 768 ? mainSettings.classList.add('main-settings-mobile-actived-aside') : '';    
    switch(this.id) {
        case 'sidebar-profile': 
            validateMobile
            profileSection();
            break;
        case 'sidebar-security':
            validateMobile    
            secutirySection();
            break;
        case 'sidebar-theme':
            validateMobile
            themeSection();
            break;
        case 'sidebar-access': 
            validateMobile
            accessSection();
            break;
    }
}
linkColor.forEach(i => i.addEventListener('click', colorLink));

function profileSection(){    
    containerSettings.innerHTML = `
    <div class="dashboard__container--settings--title">
        <div class="dashboard__container--settings--title--content">
            <h3>Preferências de Conta</h3>
            <span>Configurações gerais da conta.</span>
        </div>        
    </div>
    <div class="dashboard__container--settings--item">
        <h2>Nome de usuário</h2>
        <span>Jessica</span>
        <button value="username" onclick="modalEditUsername(this.value)">Editar</button>
    </div>
    <div class="dashboard__container--settings--item">
        <h2>E-mail</h2>
        <span>jessica@request.com.br</span>
    </div>
    `;
    sectionsCloseOption();
}

function secutirySection(){
    containerSettings.innerHTML = `
    <div class="dashboard__container--settings--title">
        <div class="dashboard__container--settings--title--content">
            <h3>Segurança</h3>
            <span>Alterar Senha.</span>
        </div>
    </div>
    <div class="dashboard__container--settings--item">
        <h2>Senha</h2>
        <span>******</span>
        <button value="password" onclick="modalEditUsername(this.value)">Editar</button>
    </div>
    `;
    sectionsCloseOption();
}

function themeSection(){
    containerSettings.innerHTML = `
    <div class="dashboard__container--settings--title">
        <div class="dashboard__container--settings--title--content">
            <h3>Aparência</h3>
            <span>Aplicar tema escuro.</span>
        </div>
    </div>
    <div class="dashboard__container--settings--item">
        <h2>Tema Escuro</h2>
        <input type="checkbox" id="switch"/>
        <label for="switch">Toggle</label>
    </div>
    `;
    sectionsCloseOption();
}

function accessSection(){
    containerSettings.innerHTML = `
    <div class="dashboard__container--settings--title">
        <div class="dashboard__container--settings--title--content">
            <h3>Informações de Acesso</h3>
            <span>Unidades de acesso.</span>
        </div>
    </div>
    <div class="dashboard__container--settings--item">
        <h2>Unidades</h2>
        <ul>
            <li>Méqui 1000</li>
            <li>Mc Donalds Pompeia</li>
            <li>Mc Donalds Itaquera</li>
            <li>Mc Donalds Cachoeirinha</li>
            <li>Mc Donalds Pirituba</li>
        </ul>
    </div>
    `;
    sectionsCloseOption();
}

function sectionsCloseOption(){
    if(window.innerWidth <= 768 ){
        const containerSettingsTitle = document.querySelector('.dashboard__container--settings--title');
        containerSettingsTitle.insertAdjacentHTML('afterbegin' ,`
            <button onclick="returnSettingsSidebar()"><i class="icon-return"></i></button>
        `);
    }
}
function returnSettingsSidebar(){
    mainSettings.classList.remove('main-settings-mobile-actived-aside');
}

function modalEditUsername(labelValue){
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
            <input type="text" id="ipt_search_restaurant" placeholder="${placeholder}">
          </div>      
          <button onclick="confirmEditUsername()">Confirmar</button>
          <span id="msg_validate_search_restaurant"></span>
        </div>
      </div>`;
    body.appendChild(modalEdit);
}
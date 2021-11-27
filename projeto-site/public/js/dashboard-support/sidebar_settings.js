const linkColor = document.querySelectorAll('.sidebar-settings__content--link'),
containerSettings = document.querySelector('.dashboard__container--settings');

function colorLink(){
    linkColor.forEach(i => i.classList.remove('sidebar-settings__content--actived'));
    this.classList.add('sidebar-settings__content--actived');   
    switch(this.id) {
        case 'sidebar-profile':
            containerSettings.innerHTML = `
            <div class="dashboard__container--settings--title">
                <h3>Preferências de Conta</h3>
                <span>Configurações gerais da conta.</span>
            </div>
            <div class="dashboard__container--settings--item">
                <h2>Nome de usuário</h2>
                <span>Jessica</span>
                <button onclick="modalEditLabel()">Editar</button>
            </div>
                <div class="dashboard__container--settings--item">
                <h2>E-mail</h2>
                <span>jessica@request.com.br</span>
            </div>
            `;
            break;
        case 'sidebar-security':
            containerSettings.innerHTML = `
            <div class="dashboard__container--settings--title">
                <h3>Segurança</h3>
                <span>Alterar Senha.</span>
            </div>
            <div class="dashboard__container--settings--item">
                <h2>Senha</h2>
                <span>******</span>
                <button onclick="modalEditLabel()">Editar</button>
            </div>
            `;
            break;
        case 'sidebar-theme':
            containerSettings.innerHTML = `
            <div class="dashboard__container--settings--title">
                <h3>Aparência</h3>
                <span>Aplicar tema escuro.</span>
            </div>
            `;
            break;
        case 'sidebar-access':           
            containerSettings.innerHTML = `
            <div class="dashboard__container--settings--title">
                <h3>Informações de Acesso</h3>
                <span>Unidades de acesso.</span>
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
            break;
    }
}

linkColor.forEach(i => i.addEventListener('click', colorLink));

function modalEditLabel(){
    const modalEdit = document.createElement("section");
    modalEdit.classList.add('overlay__modal');
    modalEdit.innerHTML += `
      <div class="overlay__modal--modal">
        <div class="overlay__modal--modal--content">
          <h3>Alterar campo de </h3>
          <div class="overlay__modal--modal--content--ipt">
            <input type="text" id="ipt_search_restaurant" placeholder="Busque pelo nome da unidade">
          </div>      
          <button onclick="searchResraurant()" id="btn_search_restaurant">Confirmar</button>
          <span id="msg_validate_search_restaurant"></span>
        </div>
      </div>`;
    body.appendChild(modalEdit);
}
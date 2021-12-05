var boolean = false;

function listDataAdmin(){
    verificateAuth();
    var dataCard = document.getElementById("data_card");
    dataCard.innerHTML += `
    <div class="data_config">
                <div class="data_line">
                    <h3>Nome</h3>
                    <span>
                        <b id="name_user">${sessionStorage.name_user_contratante}</b>
                        <i class="icon-settings"></i>
                    </span>
                </div>
                <div class="data_line">
                    <h3 id="email">E-mail</h3>
                    <span>
                        <b id="email_usuario">${sessionStorage.email_user_contratante}</b>
                        <i class="icon-settings"></i>
                    </span>
                </div>
                <div class="data_line">
                    <h3>CPF</h3>
                    <span>
                        <b id="cpf_user">${sessionStorage.cpf_user_contratante}</b>
                        <i class="icon-settings"></i>
                    </span>
                </div>
                <div class="data_line">
                    <h3><span id="password">Senha</span>
                        <i class="fa fa-eye-slash toggle-passwrd" onclick="setVisible()"></i>
                    </h3>
                    <span>
                        <b id="pass_user">*********</b>
                        <i  class="icon-settings"></i>
                    </span>
                </div>
            </div>
    `;
}

function listDataAdminMobile(){
    verificateAuth();
    var dataCardMobile = document.getElementById("data_card_mobile");
    dataCardMobile.innerHTML += `
    <div class="data_config">
                <div class="data_line">
                    <h3>Nome</h3>
                    <span>
                        <b id="name_user">${sessionStorage.name_user_contratante}</b>
                        <i class="icon-settings"></i>
                    </span>
                </div>
                <div class="data_line">
                    <h3 id="email">E-mail</h3>
                    <span>
                        <b id="email_usuario">${sessionStorage.email_user_contratante}</b>
                        <i onclick="showUpdateField(email)" class="icon-settings"></i>
                    </span>
                </div>
                <div class="data_line">
                    <h3>CPF</h3>
                    <span>
                        <b id="cpf_user">${sessionStorage.cpf_user_contratante}</b>
                        <i class="icon-settings"></i>
                    </span>
                </div>
                <div class="data_line">
                    <h3><span id="password">Senha</span>
                        <i class="fa fa-eye-slash toggle-passwrd" onclick="setVisible()"></i>
                    </h3>
                    <span>
                        <b id="pass_user">*********</b>
                        <i onclick="showUpdateField(password)" class="icon-settings"></i>
                    </span>
                </div>
            </div>
    `;
}

function setVisible(){
    if(!boolean){
        pass_user.innerHTML = `${sessionStorage.pass_user_contratante}`;
        boolean = true;
    } else {
        pass_user.innerHTML = `*********`;
        boolean = false;
    }
}

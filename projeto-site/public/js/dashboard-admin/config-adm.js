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

function showUpdateField(updateField){
    var id = updateField.id;
    var field = document.querySelector(`#${id}`).textContent;
    console.log(field)
    var background = document.getElementById("modal_data");
    background.style.display = "flex";
    background.innerHTML = `
        <div class="modal-container">
            <h2>Alterar ${field} <span onclick="closeUpdateField()">X</span></h2>
            <div class="modal-content">
                <form class="form" id="form_update">
                    <div class="modal-separator">
                        <input class="content-input" id="input_update"></input>
                    </div>
                    <button id="btn_delete" type="submit" onclick="updateField(${sessionStorage.id_user_contratant}, ${field.toLowerCase})" class="btn_confirm">Confirmar</button>
                </form>
            </div>
        </div>
    `;''
}

function closeUpdateField(){
    var background = document.getElementById("modal_data");
    background.style.display = "none";
}

// function updateField(idContratant, field){

//     if(field == "senha"){
//         updatePassword(idContratant,field);
//     } else {

//     }
    
// }

// function updatePassword(field){

//     var updateField = new URLSearchParams(new FormData(form_update));

//     //update na senha
//     fetch(`/usuarios/update-senha/${idContratant},${field}`, {
//         method: "PUT",
//         body: updateField
//     }).then(() => {
//         console.log('Senha atualizada com sucesso');
//         //document.location.reload(true);
//     }).catch(err => {
//         console.error(err)
//     });

//     return false;
// }

// function updateEmail(){
    
// }
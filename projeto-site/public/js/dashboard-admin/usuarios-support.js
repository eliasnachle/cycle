function listarSuportes(usuariosSuporte) {
    console.log("caiu aqui")
    var supList = document.getElementById("list_sup");
    supList.innerHTML = "";

    list_sup.innerHTML += `
        <h3>Suportes cadastrados - ${usuariosSuporte.length} </h3>
    `;
    for (let i = 0; i < usuariosSuporte.length; i++) {
        var suporte = usuariosSuporte[i];

        var supCard = document.createElement("div");

        supCard.innerHTML += `
        <div class="sup_list">
        <div id="teste" onclick="deleteSupport(this)" class="data_card">
            <i class="icon-profile"></i>
            <div class="card_field">
                <h3>Nome</h3>
                <span>
                    ${suporte.nomeSuporte}
                </span>
            </div>
            <div class="card_field">
                <h3>Email</h3>
                <span>
                    ${suporte.emailSuporte}
                </span>
            </div>
            <div class="card_field">
                <h3>Senha</h3>
                <span>
                    ${suporte.senhaSuporte}
                </span>
            </div>
            <div class="card_field">
                <h3>Contratante</h3>
                <span>
                    ${suporte.nomeContratante}
                </span>
            </div>
        </div>
    </div>
    `;
    supCard.className = "list_sup";
    supList.appendChild(supCard);
    }
}

function listSuppToDelete(usuariosSuporte){
    var supList = document.getElementById("select_delete");
    supList.innerHTML = "";

    if(usuariosSuporte.length == 0){
        var button = document.getElementById('btn_delete');
        button.setAttribute("disabled", true);
        supList.innerHTML += `
        <option value="">Nenhum suporte cadastrado.</option>
        `;
    } else {
        for (let i = 0; i < usuariosSuporte.length; i++) {
            var suporte = usuariosSuporte[i];
            console.log(suporte.idUsuarioSuporte)
    
            supList.innerHTML += `
            <option value="${suporte.idUsuarioSuporte}">${suporte.nomeSuporte}</option>
            `
        }
    }
}

function listMachineToDelete(machines){
    var supList = document.getElementById("select_delete");
    supList.innerHTML = "";

    if(machines.length == 0){
        var button = document.getElementById('btn_delete');
        button.setAttribute("disabled", true);
        supList.innerHTML += `
        <option value="">Nenhuma máquina cadastrada.</option>
        `;
    } else {
        for (let i = 0; i < machines.length; i++) {
            var maquinas = machines[i];
            console.log(maquinas.idMaquina);
            let idMachine = Number(maquinas.idMaquina)
    
            supList.innerHTML += `
            <option value="${idMachine}">${maquinas.apelidoMaquina}</option>
            `
        }
    }
}

function getAllMachines(machineRegistered) {
    console.log("caiu aqui")
    var machineList = document.getElementById("list_machine");
    machineList.innerHTML = "";

    machineList.innerHTML += `
        <h3>Maquinas Cadastradas - ${machineRegistered.length} </h3>
    `;
    for (let i = 0; i < machineRegistered.length; i++) {
        var machine = machineRegistered[i];

        var machineCard = document.createElement("div");

        machineCard.innerHTML += `
        <div class="sup_list">
        <div class="data_card">
            <i class="icon-computer"></i>
            <div class="card_field">
                <h3>Nome Máquina</h3>
                <span>
                    ${machine.apelidoMaquina}
                </span>
            </div>
            <div class="card_field">
                <h3>Modelo de CPU</h3>
                <span>
                    ${machine.modeloCpu}
                </span>
            </div>
            <div class="card_field">
                <h3>RAM Total</h3>
                <span>
                    ${machine.espacoTotalRam}
                </span>
            </div>
        </div>
    </div>
    `;
    machineCard.className = "list_machine";
    machineList.appendChild(machineCard);
    }
}

function getAllSupportsByContratant(){
    verificateAuth();
    console.log("chamou get-all-supports-by-contratant")
    fetch(`/leituras/get-suporte-list/${sessionStorage.id_user_contratant}`)
        .then((resposta) => {
            if(resposta.ok){
                resposta.json().then(function (resposta) {
                    console.log(`Dados Recebidos : ${JSON.stringify(resposta)}`)
                    listarSuportes(resposta);
                    listSuppToDelete(resposta);
                });
            } else {
                console.error("Alguma coisa deu errado");
            }
        })
        .catch(function (error) {
            console.error(`Erro na API: ${error.message}`);
        });
}

function getAllMachinesByContratant(){
    verificateAuth();
    console.log("Chamou getAllMachines");
    fetch(`/leituras/get-machine-list/${sessionStorage.id_user_contratant}`)
        .then((resposta) => {
            if(resposta.ok){
                resposta.json().then(function (resposta) {
                    console.log(JSON.stringify(resposta))
                    getAllMachines(resposta);
                    listMachineToDelete(resposta);
                });
            } else {
                console.error("Fetch went wrong");
            }
        })
        .catch(function (error) {
            console.error(`Erro na API: ${error.message}`);
        });
}

function registerSupport() {
    var formRegisterSupp = new URLSearchParams(new FormData(form_cadastro_suporte));
    
    console.log(sessionStorage.id_user_contratant);
    let idContratante = sessionStorage.id_user_contratant;
    console.log(idContratante);

    fetch(`/usuarios/register-suporte/${idContratante}`, {
        method: "POST",
        body: formRegisterSupp
    }).then(() => {
        console.log('Cadastrado');
        document.location.reload(true);
     }).catch(err => {
       console.error(err)
     });
    return false;
}

function deleteSupport(idSupport){


    console.log(idSupport);
    
    fetch(`/usuarios/delete-suporte/${idSupport}`, {
        method: "DELETE"
    }).then(() => {
        document.location.reload(true);
     }).catch(err => {
       console.error(err)
     });
    alert("deletado")
    return false;
}

async function deleteMachine(idMaquina){

    console.log(idMaquina);
    deleteData(idMaquina);
    
    fetch(`/leituras/delete-machine/${idMaquina}`, {
        method: "DELETE"
    }).then(() => {
        console.log('deletando maquinas');
        document.location.reload(true);
    }).catch(err => {
       console.error(err)
    });
    return false;
}

async function deleteData(idMaquina){

    console.log(idMaquina);
    deleteAlerts(idMaquina);
    
    fetch(`/leituras/delete-data/${idMaquina}`, {
        method: "DELETE"
    }).then(() => {
        console.log('deletando registros');
     }).catch(err => {
       console.error(err)
     });
    return false;
}

async function deleteAlerts(idMaquina){

    console.log(idMaquina);
    
    fetch(`/leituras/delete-register/${idMaquina}`, {
        method: "DELETE"
    }).then(() => {
        console.log('deletando alertas');
     }).catch(err => {
       console.error(err)
     });
    return false;
}

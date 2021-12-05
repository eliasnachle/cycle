function listarSuportes(usuariosSuporte) {
    console.log("caiu aqui")
    var supList = document.getElementById("list_sup");
    var supListMobile = document.getElementById("list_sup_mobile");

    supList.innerHTML = "";
    supListMobile.innerHTML = "";

    list_sup.innerHTML += `
        <h3>Suportes cadastrados - ${usuariosSuporte.length} </h3>
    `;

    list_sup_mobile.innerHTML += `
        <h3>Suportes cadastrados - ${usuariosSuporte.length} </h3>
    `;

    for (let i = 0; i < usuariosSuporte.length; i++) {
        var suporte = usuariosSuporte[i];

        var supCard = document.createElement("div");

        var supCardMobile = document.createElement("div")

        supCard.innerHTML += `
        <table class="rwd-table">
                <tr>
                    <th>Nome</th>
                    <th>Email</th>
                    <th>Contratante</th>
                </tr>
                <tr>
                    <td data-th="Nome">${suporte.nomeSuporte}</td>
                    <td data-th="Email">${suporte.emailSuporte}</td>
                    <td data-th="Contratante">${suporte.nomeContratante}</td>           
                </tr>
            </table>
        `;

        supCardMobile.innerHTML += `
        <table class="rwd-table">
                <tr>
                    <th>Nome</th>
                    <th>Email</th>
                    <th>Contratante</th>
                </tr>
                <tr>
                    <td data-th="Nome">${suporte.nomeSuporte}</td>
                    <td data-th="Email">${suporte.emailSuporte}</td>
                    <td data-th="Contratante">${suporte.nomeContratante}</td>           
                </tr>
            </table>
        `;

        supCard.className = "list_sup";
        supCardMobile.className = "list_sup_mobile";
        supList.appendChild(supCard);
        supListMobile.appendChild(supCardMobile);
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
    var machineList = document.querySelector(".list_machine");
    var machineListMobile = document.querySelector(".list_machine_mobile");

    console.log(machineList)
    
    machineList.innerHTML = "";

    machineList.innerHTML += `
        <h3>Maquinas Cadastradas - ${machineRegistered.length} </h3>

    `;


    for (let i = 0; i < machineRegistered.length; i++) {
        var machine = machineRegistered[i];

        var machineCard = document.createElement("div");

        var machineCardMobile = document.createElement("div");

        machineCard.innerHTML += `
            <table class="rwd-table">
                <tr>
                    <th>Nome Máquina</th>
                    <th>Modelo de CPU</th>
                    <th>RAM Total</th>
                </tr>
                <tr>
                    <td data-th="Nome Máquina">$${machine.apelidoMaquina}</td>
                    <td data-th="Modelo de CPU">$${machine.modeloCpu}</td>
                    <td data-th="RAM Total">$${machine.espacoTotalRam}</td>           
                </tr>
            </table>
        `;

        machineCardMobile.innerHTML += `
        <table class="rwd-table">
        <tr>
            <th>Nome Máquina</th>
            <th>Modelo de CPU</th>
            <th>RAM Total</th>
        </tr>
        <tr>
            <td data-th="Nome Máquina">$${machine.apelidoMaquina}</td>
            <td data-th="Modelo de CPU">$${machine.modeloCpu}</td>
            <td data-th="RAM Total">$${machine.espacoTotalRam}</td>           
        </tr>
    </table>
        `;

        machineCard.className = "list_machine";

        machineList.appendChild(machineCard);
        machineListMobile.appendChild(machineCardMobile);
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

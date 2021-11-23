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
        <div class="data_card">
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

function getAllSupportsByContratant(){
    verificateAuth();
    console.log("chamou get-all-supports-by-contratant")
    fetch(`/leituras/get-suporte-list/${sessionStorage.id_user_contratant}`)
        .then((resposta) => {
            if(resposta.ok){
                resposta.json().then(function (resposta) {
                    console.log(`Dados Recebidos : ${JSON.stringify(resposta)}`)
                    listarSuportes(resposta);
                });
            } else {
                console.error("Alguma coisa deu errado");
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
    }).then(function (response) {
        if(response.ok) {
            console.log("CADASTRADO!")
        } else {
            response.text().then(function (errorMessage) {
                console.log(errorMessage);
            });
        }
    })
    document.location.reload(true);
    return false;
}
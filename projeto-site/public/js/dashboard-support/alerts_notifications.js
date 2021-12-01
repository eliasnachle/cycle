function postAlerts(alerts) {
    let icon;
    const noticationsContent = document.querySelector(".notifications__content");
    noticationsContent.innerHTML = '';
    for (let i = 0; i < alerts.length; i++) {
        const alert = alerts[i],        
        alertCard = document.createElement("div");        
        switch(alert.componenteInstavel){
            case 'CPU':
                icon = 'cpu';
                break;
            case 'Disco':
                icon = 'storage';
                break;
            case 'Memória':  
                icon = 'memory';          
                break;
        }
        alertCard.innerHTML = `
        <figure>
            <i class="icon-${icon}"></i>
        </figure>
        <div class="card__content--description">
            <div class="card-notifications-title">
                <h3>${alert.componenteInstavel}</h3>        
                <span>${alert.dataHoraAlerta}</span>
            </div>
            <span>${alert.descAlerta}Verifique a máquina ${alert.apelidoMaquina}</span>
        </div>
        `;
        alertCard.classList.add('card__content');
        alertCard.value = alert.idAlerta;
        alertCard.setAttribute('onclick', 'removeAlert(value)');
        noticationsContent.appendChild(alertCard);
    }
}

function removeAlert(idCard) {
    putUpdateAlertVisibility(idCard);
}

function putUpdateAlertVisibility(idCard) {
    fetch(`/dashboardSupport/updateAlertVisibility${idCard}`, {
        method: "PUT"
    }).then(() => {
        console.log('Removendo visibilidade do card');
    }).catch(err => {
       console.error(err)
    });
    return false;
}

function getAlerts(idContractorSession) {
    const bell = document.querySelectorAll('.icon-bell')[0];
    var idContractorSession = localStorage.idContractorSession;
    fetch(`/dashboardSupport/alerts${idContractorSession}`)
    .then((resposta) => {
        if (resposta.ok) {
            resposta.json().then(function (resposta) {
                postAlerts(resposta);
                if(resposta.length >= 1) {
                    console.log('tem notificacao');
                    bell.innerHTML += '<i class="icon-bell--actived"></i>';
                } else{
                    console.log('não tem notificacao');
                }
            });
        } else {
            console.error('Nenhum dado encontrado ou erro na API');
        }
    })
    .catch(function (error) {
        console.error(`Erro na obtenção das publicações: ${error.message}`);
    });
}
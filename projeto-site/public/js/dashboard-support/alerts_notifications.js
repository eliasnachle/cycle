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
        alertCard.value = i+1;
        alertCard.setAttribute('onclick', 'teste(value)');
        noticationsContent.appendChild(alertCard);

    }
}

function teste(idCard) {
    console.log(idCard);
}

function getAlerts(idContractorSession) {
    var idContractorSession = localStorage.idContractorSession;
    fetch(`/dashboardSupport/alerts${idContractorSession}`)
    .then((resposta) => {
        if (resposta.ok) {
            resposta.json().then(function (resposta) {
                postAlerts(resposta);
            });
        } else {
            console.error('Nenhum dado encontrado ou erro na API');
        }
    })
    .catch(function (error) {
        console.error(`Erro na obtenção das publicações: ${error.message}`);
    });
}
function postMachines(machines) {
    const dashboardContainerRow = document.querySelector(".dashboard__container--row");
    dashboardContainerRow.innerHTML = '';
    for (let i = 0; i < machines.length; i++) {
        const machine = machines[i],
        machineItem = document.createElement("a");        
        machineItem.innerHTML = `
        <div class="card__content">
          <div class="card__content--title">
            <figure class="figure-icon">
              <i class="icon-${(machine.tipoMaquina).toLowerCase()}"></i>
            </figure>
            <span>${machine.apelidoMaquina}</span>
          </div>
        </div>
        `;
        machineItem.href = 'dashboard-monitor.html';
        dashboardContainerRow.appendChild(machineItem);
    }
}

function getMachines(idContractorSession) {
    var idContractorSession = localStorage.idContractorSession;
    fetch(`/machines/${idContractorSession}`)
    .then((resposta) => {
        if (resposta.ok) {
            resposta.json().then(function (resposta) {
                postMachines(resposta);   
            });
        } else {
            console.error('Nenhum dado encontrado ou erro na API');
        }
    })
    .catch(function (error) {
        console.error(`Erro na obtenção das publicações: ${error.message}`);
    });
}


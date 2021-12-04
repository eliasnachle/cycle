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
              <i class="icon-${((machine.tipoMaquina).toLowerCase()) != "totem"? "computer" : (machine.tipoMaquina).toLowerCase()}"></i>
            </figure>
            <span>${machine.apelidoMaquina}</span>
          </div>
        </div>
        `;        
        machineItem.value = machine.idMaquina;
        machineItem.setAttribute('onclick', 'getIdMachine(this, value)');
        dashboardContainerRow.appendChild(machineItem);
        machineItem.href = 'dashboard-monitor.html';
    }
}

function getIdMachine(idMachine, value) {
    sessionStorage.setItem('idMachine', value);
    sessionStorage.setItem('nameMachine', idMachine.textContent);
}

function getMachines(idContractorSession) {
    var idContractorSession = localStorage.idContractorSession;
    fetch(`/dashboardSupport/${idContractorSession}`)
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

window.addEventListener('load', getMachines)
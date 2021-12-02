const machineName = document.querySelector('.dashboard__container--return > figure:nth-child(2) > span'),
dashboardContainerComponents = document.querySelector('.dashboard__container--components');
let cpuFrequency,
sizePrimaryDisk,
sizeSecondaryDisk,
sizeRam;

function getDiaryUse(idMachine) {
    var idMachine = sessionStorage.idMachine;
    fetch(`/dashboardSupport/realTimeUse${idMachine}`)
    .then((resposta) => {
        if (resposta.ok) {
            resposta.json().then(function (resposta) {
                // console.log(`Dados recebidos: ${JSON.stringify(resposta)}`);
                const dailyRamUsage = document.querySelectorAll('.value-container')[0],
                dailyCpuUsage = document.querySelectorAll('.value-container')[1]
                dailyRamUsageProgressBar = document.querySelectorAll('.circular-progress > svg')[0],
                dailyCpuUsageProgressBar = document.querySelectorAll('.circular-progress > svg')[1];

                let porcentageDailyCpuUsage = ((resposta[0].cpuEmUso/cpuFrequency)*100).toFixed(0),
                porcentageDailyRamUsage = (((sizeRam-resposta[0].espacoLivreRam)/sizeRam)*100).toFixed(0);
                dailyRamUsage.innerHTML = `<h2>${porcentageDailyRamUsage}<span>%</span></h2>`;
                dailyCpuUsage.innerHTML = `<h2>${porcentageDailyCpuUsage}<span>%</span></h2>`;

                dailyCpuUsageProgressBar.innerHTML += `<circle cx="70" cy="70" r="70" style="stroke-dashoffset:calc(440 - (440 *${porcentageDailyCpuUsage}) / 100);"></circle>`;
                dailyRamUsageProgressBar.innerHTML += `<circle cx="70" cy="70" r="70" style="stroke-dashoffset:calc(440 - (440 *${porcentageDailyRamUsage}) / 100);"></circle>`;
            });
        } else {
            console.error('Nenhum dado encontrado ou erro na API');
        }
    })
    .catch(function (error) {
        console.error(`Erro na obtenção das publicações: ${error.message}`);
    });
}

function getDetailMachine(idMachine) {
    var idMachine = sessionStorage.idMachine;
    fetch(`/dashboardSupport/detailMachine${idMachine}`)
    .then((resposta) => {
        if (resposta.ok) {
            resposta.json().then(function (resposta) {
                console.log(`Dados recebidos: ${JSON.stringify(resposta)}`);
                postDetailMachine(resposta);
            });
        } else {
            console.error('Nenhum dado encontrado ou erro na API');
        }
    })
    .catch(function (error) {
        console.error(`Erro na obtenção das publicações: ${error.message}`);
    });
}

function postDetailMachine(detailMachines){
    cpuFrequency = detailMachines[0].cpuFrequencia;
    sizePrimaryDisk = detailMachines[0].espacoTotalDisco1;
    sizeSecondaryDisk = detailMachines[0].espacoTotalDisco2;
    sizeRam = detailMachines[0].espacoTotalRam;
    dashboardContainerConfig = document.querySelector('.dashboard__container--bottom > .dashboard__container--config');
    dashboardContainerConfig.innerHTML = `
    <h3>Configurações</h3>
    <div class="dashboard__container--config--cpu">
      <span>Modelo CPU: <b>${detailMachines[0].modeloCpu}</b></span>
      <span>Clock: <b>${detailMachines[0].cpuFrequencia}</b></span>
    </div>
    <div class="dashboard__container--config--disk">          
      <span>Modelo Disco 1: <b>${detailMachines[0].modeloDisco1}</b></span>
      <span>Capacidade: <b>${detailMachines[0].espacoTotalDisco1}</b></span>
      <span>Modelo Disco 2: <b>${detailMachines[0].modeloDisco2}</b></span>
      <span>Capacidade: <b>${detailMachines[0].espacoTotalDisco2}</b></span>
    </div>
    <div class="dashboard__container--config--memory">          
      <span>Memoria: <b>${detailMachines[0].espacoTotalRam} GB</b></span>
    </div>
    <div class="dashboard__container--config--os">          
      <span>Sistema Operacional: <b>${detailMachines[0].sistemaOperacionalMaquina}</b></span>
    </div>
    `;
}

function postRealTimeUse(idMachine){
    let cpuUsePorcentage = ((idMachine[0].cpuEmUso/cpuFrequency)*100).toFixed(0),
    ramUsePorcentage = (((sizeRam-idMachine[0].espacoLivreRam)/sizeRam)*100).toFixed(0),
    primaryDiskUsePorcentage = (((sizePrimaryDisk-idMachine[0].espacoLivreDisco1)/sizePrimaryDisk)*100).toFixed(0),
    secondaryDiskUsePorcentage = (((sizeSecondaryDisk-idMachine[0].espacoLivreDisco2)/sizeSecondaryDisk)*100).toFixed(0);
    dashboardContainerComponents.innerHTML = `
    <div class="components__card">
        <i class="icon-cpu"></i>
        <h3>CPU</h3>
        <span>Excelente perfomance</span>
        <div class="progressbar">
            <div class="progressbar__use" style="width:${cpuUsePorcentage}%;"></div>
        </div>
        <div class="components__card--use">
            <span>${cpuUsePorcentage}%</span>
            <span>${idMachine[0].cpuEmUso}/${cpuFrequency}GHz</span>
        </div>
    </div>

    <div class="components__card">
        <i class="icon-memory"></i>
        <h3>Memoria</h3>
        <span>Excelente perfomance</span>
        <div class="progressbar">
            <div class="progressbar__use" style="width:${ramUsePorcentage}%;"></div>
        </div>
        <div class="components__card--use">
            <span>${ramUsePorcentage}%</span>
            <span>${idMachine[0].espacoLivreRam}GB/${sizeRam}GB</span>
        </div>
    </div>

    <div class="components__card">
        <i class="icon-storage"></i>
        <h3>Disco 1</h3>
        <span>Excelente perfomance</span>
        <div class="progressbar">
            <div class="progressbar__use" style="width:${primaryDiskUsePorcentage}%;"></div>
        </div>
        <div class="components__card--use">
            <span>${primaryDiskUsePorcentage}%</span>
            <span>${idMachine[0].espacoLivreDisco1}GB/${sizePrimaryDisk}GB</span>
        </div>
    </div>
  `;
  if(idMachine[0].espacoLivreDisco2 != null){
      dashboardContainerComponents.innerHTML += `
      <div class="components__card">
        <i class="icon-storage"></i>
        <h3>Disco 2</h3>
        <span>Excelente perfomance</span>
        <div class="progressbar">
            <div class="progressbar__use" style="width:${secondaryDiskUsePorcentage};"></div>
        </div>
        <div class="components__card--use">
            <span>${secondaryDiskUsePorcentage}%</span>
            <span>${idMachine[0].espacoLivreDisco2}GB/${sizeSecondaryDisk}GB</span>
        </div>
      </div>    
    `;
  } else{
      dashboardContainerComponents.innerHTML +=`
        <div class="components__card">
            <i class="icon-storage"></i>
            <h3>Disco 2</h3>
            <span>Há espaço disponível para um novo disco!</span>
        </div>
        `;
  }
}

function getRealTimeUse(idMachine) {
    var idMachine = sessionStorage.idMachine;
    fetch(`/dashboardSupport/realTimeUse${idMachine}`)
    .then((resposta) => {
        if (resposta.ok) {            
            resposta.json().then(function (resposta) {
                console.log(`Dados recebidos: ${JSON.stringify(resposta)}`);
                postRealTimeUse(resposta);
            });
        } else {
            console.error('Nenhum dado encontrado ou erro na API');
        }
    })
    .catch(function (error) {
        console.error(`Erro na obtenção das publicações: ${error.message}`);
    });
}

function getDetailsDashboardMonitor(){
    machineName.innerHTML = sessionStorage.getItem('nameMachine');
    getDetailMachine();    
    getRealTimeUse();
    getDiaryUse();
}

window.addEventListener('load', getDetailsDashboardMonitor);
setInterval(getDetailsDashboardMonitor, 5000);
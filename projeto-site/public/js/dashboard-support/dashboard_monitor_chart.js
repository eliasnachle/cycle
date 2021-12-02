function getRealChartTimeUse(idMachine) {
    var idMachine = sessionStorage.idMachine;
    fetch(`/dashboardSupport/realChartTimeUse${idMachine}`)
    .then((resposta) => {
        if (resposta.ok) {
            resposta.json().then(function (resposta) {
                console.log(`Dados recebidos: ${JSON.stringify(resposta)}`);
                chartJS(resposta);
            });
        } else {
            console.error('Nenhum dado encontrado ou erro na API');
        }
    })
    .catch(function (error) {
        console.error(`Erro na obtenção das publicações: ${error.message}`);
    });
}

// Gráfico Charts JS
function chartJS(useDetail){
// Dimensões Gráfico
var context = document.querySelector("#chart");

var ctx = document.getElementById("chart").getContext("2d");
var gradient = ctx.createLinearGradient(0, 0, 0, 300);
gradient.addColorStop(1, 'rgba(131,111,255,0.025)');
gradient.addColorStop(0, 'rgba(131,111,255)');

// Configurações Charts
var configuration = {
  type: 'line',
  data: {
    labesl: ["Seg","Ter","Qua","Qui","Sex","Sab","Dom"],
    datasets: [
    {
      label: "Consumo",
      data: [
            useDetail[0].cpuEmUso,
            useDetail[1].cpuEmUso,
            useDetail[2].cpuEmUso,
            useDetail[3].cpuEmUso,
            useDetail[4].cpuEmUso
      ],
      type: 'line',
      borderColor: '#836FFF',
      backgroundColor: gradient,
      borderWidth: 3,
      //Ponto
      pointRadius: 8,
      pointBackgroundColor:  '#836FFF',
      pointBorderWidth: 3,
      // Ponto Hover
      pointHoverRadius: 8,
      pointHoverBackgroundColor: '#836FFF',
      pointHoverBorderColor: '#836FFF'
    }
  ]},
  options: {
    responsive: true,
    tooltips: {
      intersect: true,
      node: "index",
    }
  }
};
var chart = new Chart(context, configuration);
this.lastIndexTemp = 0;
this.time = 0;
}

window.addEventListener('load', getRealChartTimeUse);
setInterval(getRealChartTimeUse, 5000);
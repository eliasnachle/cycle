function getSelectComponent(){
    let selectComponent = document.getElementById('select-component').value;

    switch(selectComponent){
        case 'cpu':
            getRealChartTimeUse(selectComponent);
            break;
        case 'memory':
            getRealChartTimeUse(selectComponent);
            break;
        case 'disk':
            getRealChartTimeUse(selectComponent);
            break;
        default:
            getRealChartTimeUse(selectComponent);
    }
}

function getRealChartTimeUse(component) {
    var idMachine = sessionStorage.idMachine;
    fetch(`/dashboardSupport/realChartTimeUse/${idMachine}/${component}`)
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
            useDetail[0].componenteEmUso,
            useDetail[1].componenteEmUso,
            useDetail[2].componenteEmUso
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
var chart = new Chart(ctx, configuration);
this.lastIndexTemp = 0;
this.time = 0;
}

window.addEventListener('load', getSelectComponent);
setInterval(getSelectComponent(), 5000);  
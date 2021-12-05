var ctx = document.getElementById("chart").getContext("2d");
var gradient = ctx.createLinearGradient(0, 0, 0, 300);
gradient.addColorStop(1, 'rgba(131,111,255,0.025)');
gradient.addColorStop(0, 'rgba(131,111,255)');

let proximaAtualizacao;

var dados = {
    labels: [],
    datasets: [
        {
            label: "Consumo",
            data: [],
            type: 'line',
            borderColor: '#836FFF',
            backgroundColor: gradient,
            borderWidth: 3,
            //Ponto
            pointRadius: 8,
            pointBackgroundColor: '#836FFF',
            pointBorderWidth: 3,
            // Ponto Hover
            pointHoverRadius: 8,
            pointHoverBackgroundColor: '#836FFF',
            pointHoverBorderColor: '#836FFF'
        }
    ]
}

var configuration = {
    type: 'line',
    options: {
        responsive: true,
        tooltips: {
            intersect: true,
            node: "index",
        }
    }
};

function getSelectComponent() {
    let selectComponent = document.getElementById('select-component').value;

    switch (selectComponent) {
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
            break;
    }

}

function getRealChartTimeUse(component) {
    var idMachine = sessionStorage.idMachine;
    fetch(`/dashboardSupport/realChartTimeUse/${idMachine}/${component}`)
        .then((resposta) => {
            if (resposta.ok) {
                resposta.json().then(function (resposta) {
                    console.log(`Dados recebidos: ${JSON.stringify(resposta)}`);
                    updateChart(resposta)
                });
            } else {
                console.error('Nenhum dado encontrado ou erro na API');
            }
        })
        .catch(function (error) {
            console.error(`Erro na obtenção das publicações: ${error.message}`);
        });
}

function updateChart(useDetail) {

    console.log(useDetail[0])
    console.log(useDetail[0].componenteEmUso)
    dados.datasets[0].data.push(useDetail[0].componenteEmUso);
    dados.labels.push(dados.labels.length);

    dados.labels.shift();
    dados.datasets[0].data.shift();

    window.grafico.update();

    proximaAtualizacao = setTimeout(()=> getSelectComponent(), 5000)
}

function generateFirstConfigChart(component) {
    var idMachine = sessionStorage.idMachine;

    if (proximaAtualizacao != undefined){
        clearTimeout(proximaAtualizacao)    
    }

    dados.labels = [];
    dados.datasets[0].data = [];


    fetch(`/dashboardSupport/generateChart/${idMachine}/${component}`)
        .then((resposta) => {
            if (resposta.ok) {
                resposta.json().then(function (resposta) {
                    console.log(`Dados recebidos: ${JSON.stringify(resposta)}`);

                    chartJS(resposta)
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
function chartJS(useDetail) {
    // Dimensões Gráfico
    console.log(useDetail)

    window.grafico = Chart.Line(ctx, {
        data: dados,
        options: configuration
    });

    console.log(dados.datasets[0].data)

    for (var i = 0; i < useDetail.length; i++) {
        var test = useDetail[i]
        dados.labels.push(i);
        dados.datasets[0].data.push(test.componenteEmUso);
    }

    window.grafico.update();

    this.lastIndexTemp = 0;
    this.time = 0;

    getSelectComponent();
}
    // let primeiroGrafico = document.getElementById('chart').getContext('2d');

    // let chart = new Chart(primeiroGrafico,{
    //   type:"bar",
    //   data: {
    //     labels: [2017, 2018, 2019, 2020, 2021],
    //     datasets:[{
    //       label: "teste nome",
    //       data: [300, 400, 421, 302, 500, 100],
    //       backgroundColor: "cyan" 
    //     }]
    //   }
    // });
    let proximaAtualizacao;
    var idRegistro = sessionStorage.getItem('idRegistro');
    var idComputador = sessionStorage.getItem('idMaquina');

    function inicializarPagina() {

      obterDadosGraficoPrimeiraVez(idRegistro, idComputador);
      obterProcessosComputador(idRegistro);

    }

    function obterDadosGraficoPrimeiraVez(idRegistro, idComputador) {


      if (proximaAtualizacao != undefined) {
        clearTimeout(proximaAtualizacao);
      }

      fetch(`/leituras/ultimas/${idRegistro}/${idComputador}`, {
        cache: 'no-store'
      }).then(function (response) {
        if (response.ok) {
          response.json().then(function (resposta) {
            console.log(`Dados recebidos: ${JSON.stringify(resposta)}`);
            resposta.reverse();

            var dados = {

              type: 'line',
              labels: [],
              datasets: [
                {
                  label: 'CPU',
                  data: [],
                  borderColor: ["#0000CD"],
                  fill: false,
                  borderWidth: 1
                },
                {
                  label: 'RAM',
                  data: [],
                  borderColor: ["black"],
                  fill: false,
                  borderWidth: 1
                }
              ],

            };
            for (var i = 0; i < resposta.length; i++) {

              var registro = resposta[i];
              dados.labels.push(registro.momento_grafico);
              dados.datasets[0].data.push(registro.desempenhoCpu);
              // dados.datasets[1].push(registro.espacoDeOcupacaoDisco / 10000000000);
              dados.datasets[1].data.push(registro.desempenhoRam);
            }

            plotarGrafico(idRegistro, idComputador, dados);
          });
        } else {
          console.error('Nenhum dado encontrado ou erro na API');
        }
      })
        .catch(function (error) {
          console.error(`Erro na obtenção dos dados p/ gráfico: ${error.message}`);
        });

    }

    function plotarGrafico(idRegistro, idComputador, dados) {
      console.log('iniciando plotagem do gráfico...');

      var ctx = myChart.getContext('2d');
      window.grafico_linha = Chart.Line(ctx, {
        data: dados,
        options: configurarGraficoMyChart()
      });

      setTimeout(() => atualizarGrafico(idRegistro, idComputador, dados), 3000);

    }

    function atualizarGrafico(idRegistro, idComputador, dados) {

      fetch(`/leituras/tempo-real2/${idRegistro}/${idComputador}`, {
        cache: 'no-store'
      }).then(function (response) {

        if (response.ok) {
          response.json().then(function (novoRegistro) {

            console.log(`Dados recebidos: ${JSON.stringify(novoRegistro)}`);
            // console.log(`Dados atuais do gráfico: ${Array.prototype.toString(dados)}`);

            // tirando e colocando valores no gráfico
            dados.labels.shift(); // apagar o primeiro
            dados.labels.push(novoRegistro.momento_grafico);
            dados.datasets[0].data.shift();
            dados.datasets[1].data.shift();
            // dados.datasets[2].data.shift(); 
            dados.datasets[0].data.push(novoRegistro
              .desempenhoCpu);
            // dados.datasets[1].data.push(novoRegistro
            //     .espacoDeOcupacaoDisco / 10000000000); 
            dados.datasets[1].data.push(novoRegistro
              .desempenhoRam);

            window.grafico_linha.update();

            proximaAtualizacao = setTimeout(() => atualizarGrafico(idRegistro, idComputador, dados), 5000);
          });
        } else {
          console.error('Nenhum dado encontrado ou erro na API');
          proximaAtualizacao = setTimeout(() => atualizarGrafico(idRegistro, idComputador, dados), 5000);
        }
      })
        .catch(function (error) {
          console.error(`Erro na obtenção dos dados p/ gráfico: ${error.message}`);
        });

    }

    function configurarGraficoMyChart() {

      var options = {
        responsive: true,
        scales: {
          yAxe: {

            beginAtZero: true
          }
        }
      };

      return options;

    }
    window.onload = inicializarPagina();

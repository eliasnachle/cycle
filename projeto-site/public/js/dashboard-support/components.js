var idRegistro = sessionStorage.getItem('idRegistro');
var idComputador = sessionStorage.getItem('idMaquina');

var cpu = sessionStorage.getItem('cpuEmUso');
var ram = sessionStorage.getItem('espacoLivreRam');
var disk1 = sessionStorage.getItem('espacoLivreDisco1');
var disk2 = sessionStorage.getItem('espacoLivreDisco2');

function inicializarPagina() {

    obterDadosGraficoPrimeiraVez(idRegistro, idComputador);
    obterProcessosComputador(idRegistro);

  }

function getUsoCPU(){
    var testeCpu = 50;

    //progressbar.innerHTML = `<div class="progressbar__use" style="width: ${testeCpu}%></div>"`;
    bar.style.width = `${testeCpu}%`;
}
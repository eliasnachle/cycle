function getAllRegisters(){
    authenticateLogin();
    fetch(`/leituras/get-registros/${sessionStorage.idSupportUser}`)
        .then((resposta) => {
            if(resposta.ok){
                resposta.json().then(function (resposta) {
                    console.log(`Dados Recebidos : ${JSON.stringify(resposta)}`)
                    listarRegistros(resposta);
                });
            } else {
                console.error("Alguma coisa deu errado");
            }
        })
        .catch(function (error) {
            console.error(`Erro na API: ${error.message}`);
        });
}

function listarRegistros(registros){
    console.log("caiu aqui")
    var table = document.getElementById('tabela');
    console.log(registros.length)
    for(let i = 0; i < registros.length; i++){

        var teste = registros[i];

        table.innerHTML += `
        <tr>
            <td data-th=""><figure class="figure-icon"><i class="icon-memory"></i></figure></td>
            <td data-th="Maquina">${teste.apelidoMaquina} 1</td>
            <td data-th="Componente">${teste.componenteInstavel}</td>
            <td data-th="Status" class="desc_red">${teste.nivelCriticidade}</td>
            <td data-th="Descrição">${teste.descAlerta}</td>
            <td data-th="Data">${teste.dataHoraAlerta}</td>
            <td data-th="Impacto" class="desc_red">Gravissimo</td>
        </tr>
        `
    }
}


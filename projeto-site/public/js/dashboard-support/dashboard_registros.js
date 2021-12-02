function getCriticalRegisters() {
  fetch(`/dashboardSupport/criticalRegisters`)
    .then((resposta) => {
      if (resposta.ok) {
        resposta.json().then(function (resposta) {
          console.log(`Dados recebidos: ${JSON.stringify(resposta)}`);
          putCriticalRegister(resposta);
        });
      } else {
        console.error("Nenhum dado encontrado ou erro na API");
      }
    })
    .catch(function (error) {
      console.error(`Erro na obtenção das publicações: ${error.message}`);
    });
}

function putCriticalRegister(registers) {
  if (window.innerWidth <= 768) {
    const accordion = document.querySelector(".accordion");
    for (let i = 0; i < registers.length; i++) {
      const register = registers[i],
        accordionItem = document.createElement("div");
        accordionItem.innerHTML = `
        <button id="accordion-button-1" aria-expanded="false">
          <span class="accordion-title">
              <div class="flex-table">
                  <figure class="icon-component figure-icon"><i class="icon-memory"></i></figure>
                  <div>
                      <h1>${register.apelidoMaquina}</h1>
                      <p>${register.descAlerta}</p>
                  </div>
              </div>
          </span>
        </button>
        <div class="accordion-content">
        <div class="flex-table">
              <div class="desc-table">
                  <div class="icon-component">
                      <h3>Data</h3>
                      <span class="green">${register.dataHoraAlerta}</span>
                  </div>
              </div>
              <div class="desc-table">
                  <div class="icon-component">
                      <h3>Impacto</h3>
                      <span class="desc_red">${register.nivelCriticidade}</span>
                  </div>
              </div>
          </div>
        </div>`;
        accordionItem.classList.add('accordion-item');
        accordion.appendChild(accordionItem);
    }
  } else {
    const tableWeb = document.querySelector(".rwd-table > tbody");
    for (let i = 0; i < registers.length; i++) {
      const register = registers[i],
        registerCard = document.createElement("tr");
      registerCard.innerHTML = `
            <td data-th=""><figure class="figure-icon"><i class="icon-memory"></i></figure></td>
            <td data-th="Maquina">${register.apelidoMaquina}</td>
            <td data-th="Componente">${register.componenteInstavel}</td>
            <td data-th="Descrição">${register.descAlerta}</td>
            <td data-th="Data">${register.dataHoraAlerta}</td>
            <td data-th="Impacto" class="desc_red">${register.nivelCriticidade}</td>
            `;
      tableWeb.appendChild(registerCard);
    }
  }
}

window.addEventListener("load", getCriticalRegisters);

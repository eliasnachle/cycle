const body = document.getElementsByTagName('BODY')[0],
btnSearchRestaurant = document.querySelector('#btnSearchRestaurant'),
clickHandlerMobileSearchRestaurant = document.querySelector('.mobile-menu__content > button'),
clickHandlerSearchRestaurant = document.querySelector('.header--profile-content > button'),
clickHandlerSignOut = document.querySelector('.sidebar > a'),
clickHandlerSignOutMobile = document.getElementById('logoutMobile'),
resturantReg = /^[0-9]{1,35}$/;

/*----------
Modal Global
----------*/
function addOverflow(){
  body.style.overflow = 'hidden';
}

function closeModal(){
  body.style = '';
  modal = document.querySelector('.overlay__modal');
  modal.parentNode.removeChild(modal);
}

/*--------------
Modal Restaurante
---------------*/
// [clickHandlerSearchRestaurant, clickHandlerMobileSearchRestaurant].map(element => element.addEventListener('click', () => {
//     addOverflow();
//     modalRestaurant();
// }));

// if(localStorage.getItem("idContractorSession") == null){
//     addOverflow();    
//     modalRestaurant();
// }

// function modalRestaurant(){
//   const modalSearchRestaurant = document.createElement("section");
//   modalSearchRestaurant.classList.add('overlay__modal');
//   modalSearchRestaurant.innerHTML += `
//     <div class="overlay__modal--modal">
//       <div class="overlay__modal--modal--content">
//         <img src="../imgs/search_restaurant.svg">
//         <h3>Qual unidade você quer encontrar?</h3>
//         <div class="overlay__modal--modal--content--ipt">
//           <i class="icon-search"></i>
//           <input type="text" id="ipt_search_restaurant" placeholder="Busque pelo id do contratante">
//         </div>      
//         <button onclick="searchResraurant()" id="btn_search_restaurant">Buscar</button>
//         <span id="msg_validate_search_restaurant"></span>
//       </div>
//     </div>`;
//   body.appendChild(modalSearchRestaurant);
//   if(localStorage.getItem("idContractorSession") !== null){
//     const modalContent = document.querySelector('.overlay__modal--modal--content');
//     modalContent.insertAdjacentHTML('beforebegin' ,`
//     <div class="overlay__modal--modal--content--close" onclick="closeModal()">
//       <svg fill="none" height="20" viewBox="0 0 19 19" width="20" xmlns="http://www.w3.org/2000/svg">
//         <path clip-rule="evenodd" d="M18.5578 18.5579C17.9682 19.1474 17.0124 19.1474 16.4228 18.5579L0.442158 2.57712C-0.147386 1.98757 -0.147386 1.03171 0.442158 0.442157C1.03171 -0.147388 1.98756 -0.147388 2.57711 0.442171L18.5578 16.4229C19.1474 17.0125 19.1474 17.9683 18.5578 18.5579Z" fill="#161616" fill-rule="evenodd"></path>
//         <path clip-rule="evenodd" d="M0.442158 18.5579C-0.147386 17.9683 -0.147386 17.0125 0.442158 16.4229L16.4228 0.442169C17.0124 -0.14739 17.9682 -0.14739 18.5578 0.442169C19.1474 1.03171 19.1474 1.98757 18.5578 2.57711L2.57711 18.5579C1.98756 19.1474 1.03171 19.1474 0.442158 18.5579Z" fill="#161616" fill-rule="evenodd"></path>
//       </svg>
//     </div> 
//     `);
//   }
// }
// Validacao pesquisa resturante
// function searchResraurant(){
//     const iptSearchRestaurant = document.querySelector('#ipt_search_restaurant'),
//     iptSearchRestaurantValue = iptSearchRestaurant.value,
//     msgValidate = msg_validate_search_restaurant,
//     searchRestaurantModal = document.querySelector('.overlay__modal');
//     iptSearchRestaurant.value = '';
//     if(iptSearchRestaurantValue.match(resturantReg)){
//         msgValidate.innerHTML = '';
//         localStorage.idContractorSession;
//         localStorage.setItem('idContractorSession', iptSearchRestaurantValue);
//         body.style.overflow = '';
//         searchRestaurantModal.parentNode.removeChild(searchRestaurantModal);
//         getMachines();
//     } else{
//         msgValidate.innerHTML = 'Digite um id válido';
//     }
// }

/*------------
Modal Encerrar
------------*/
[clickHandlerSignOutMobile, clickHandlerSignOut].map(element => element.addEventListener ('click', () => {
  addOverflow();
  const ModalSignOut = document.createElement("section");
  ModalSignOut.classList.add('overlay__modal');
  ModalSignOut.innerHTML += `
  <div class="overlay__modal--modal">
    <div class="overlay__modal--modal--content">
      <h3>Encerrar sessão</h3>
      <div class="overlay__modal--modal--content--text">
        <span>Tem certeza que deseja encerrar?</span>
      </div>
      <button onclick="logoff()" id="btn_search_restaurant">Encerrar</button>
      <button onclick="closeModal()">Cancelar</button>
    </div>
  </div>  
  `;
  body.appendChild(ModalSignOut);
}));

// function hasRestaurantSession(){
//   const dashboardMachinesTab = window.location.pathname;
//   if(localStorage.getItem("idContractorSession") !== null && dashboardMachinesTab == '/dashboard-support/dashboard.html'){
//       getMachines();
//   }
// }

// window.addEventListener("load", hasRestaurantSession);
const body = document.getElementsByTagName('BODY')[0],
btnSearchRestaurant = document.querySelector('#btnSearchRestaurant');
const resturantReg = /^[À-úA-z ]{3,35}$/;

function headerBtnSearchRestaurant(){
    body.style.overflow = 'hidden';
    body.innerHTML += `
    <section class="search__restaurant">
    <div class="search__restaurant--modal">
      <div class="search__restaurant--modal--content">
      <div class="search__restaurant--modal--content--close" onclick="closeSearchRestaurantModal()">
        <svg fill="none" height="20" viewBox="0 0 19 19" width="20" xmlns="http://www.w3.org/2000/svg">
            <path clip-rule="evenodd" d="M18.5578 18.5579C17.9682 19.1474 17.0124 19.1474 16.4228 18.5579L0.442158 2.57712C-0.147386 1.98757 -0.147386 1.03171 0.442158 0.442157C1.03171 -0.147388 1.98756 -0.147388 2.57711 0.442171L18.5578 16.4229C19.1474 17.0125 19.1474 17.9683 18.5578 18.5579Z" fill="#161616" fill-rule="evenodd"></path>
            <path clip-rule="evenodd" d="M0.442158 18.5579C-0.147386 17.9683 -0.147386 17.0125 0.442158 16.4229L16.4228 0.442169C17.0124 -0.14739 17.9682 -0.14739 18.5578 0.442169C19.1474 1.03171 19.1474 1.98757 18.5578 2.57711L2.57711 18.5579C1.98756 19.1474 1.03171 19.1474 0.442158 18.5579Z" fill="#161616" fill-rule="evenodd"></path>
        </svg>
      </div>      
        <img src="../imgs/search_restaurant.svg">
        <h3>Qual unidade você quer encontrar?</h3>
        <div class="search__restaurant--modal--content--ipt">
          <i class="icon-search"></i>
          <input type="text" id="ipt_search_restaurant" placeholder="Busque pelo nome da unidade">
        </div>      
        <button onclick="searchResraurant()" id="btn_search_restaurant">Buscar</button>
        <span id="msg_validate_search_restaurant"></span>
      </div>
    </div>
    </section>`; 
}

function closeSearchRestaurantModal(){
    searchRestaurantModal = document.querySelector('.search__restaurant');
    searchRestaurantModal.parentNode.removeChild(searchRestaurantModal);
}

// Adicionando modal ao site
if(sessionStorage.getItem("restaurantSession") == null){
    body.style.overflow = 'hidden';
    body.innerHTML += `
    <section class="search__restaurant">
    <div class="search__restaurant--modal">
      <div class="search__restaurant--modal--content">
        <img src="../imgs/search_restaurant.svg">
        <h3>Qual unidade você quer encontrar?</h3>
        <div class="search__restaurant--modal--content--ipt">
          <i class="icon-search"></i>
          <input type="text" id="ipt_search_restaurant" placeholder="Busque pelo nome da unidade">
        </div>      
        <button onclick="searchResraurant()" id="btn_search_restaurant">Buscar</button>
        <span id="msg_validate_search_restaurant"></span>
      </div>
    </div>
    </section>`;
}

function searchResraurant(){
    const iptSearchRestaurant = document.querySelector('#ipt_search_restaurant'),
    iptSearchRestaurantValue = iptSearchRestaurant.value,
    msgValidate = msg_validate_search_restaurant,
    searchRestaurantModal = document.querySelector('.search__restaurant');
    console.log(iptSearchRestaurantValue);
    iptSearchRestaurant.value = '';
    if(iptSearchRestaurantValue.match(resturantReg)){
        msgValidate.innerHTML = '';
        // Atribuindo valor do input ao sessionStorage
        sessionStorage.restaurantSession;
        sessionStorage.setItem('restaurantSession', iptSearchRestaurantValue);
        body.style.overflow = '';
        searchRestaurantModal.parentNode.removeChild(searchRestaurantModal);
    } else{
        msgValidate.innerHTML = 'Digite um nome válido';
    }
}

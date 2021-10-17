const sidebarLogo = document.querySelector('.sidebar__content--logo > img'),
mobileMenu = document.querySelector('.mobile-menu'),
notificationsBar = document.querySelector('.notifications'),
iconNotification = document.querySelector('.header--profile-content--icon > i'),
iconNotificationMobile = document.querySelector('.mobile-menu-options > ul > a:nth-child(1)');

// Troca imagem menu
window.addEventListener('resize', () => {
  if(window.innerWidth > 1080){        
    sidebarLogo.src = "../imgs/request.svg";
  } else {
    sidebarLogo.src = "../imgs/small_logo_request.svg";    
  }  
})

// Toggle opções do menu mobile e adiciona overlay  
document.onclick = function(e){
  const mobileMenuOptions = document.querySelector('.mobile-menu-options');
  const notificationsBar = document.querySelector('.notifications');
  if(e.target.id == 'profileMenu'){
    mobileMenuOptions.style.bottom = '0';
    addOverlay();
  } else if(e.target.id == 'notificationHeader' || e.target.id == 'notificationMobile'){
    notificationsBar.style.right = '0';
  } else{
    if(document.querySelector('.overlay') !== null ){
      removeOverlay();
    }
    notificationsBar.style.right = '-200%';
    mobileMenuOptions.style.bottom = '-100%';
  }
}

// Adiciona overlay
function addOverlay(){
  const body = document.getElementsByTagName('BODY')[0];
  body.innerHTML += `<div class="overlay"></div>`;
}
// Remove overlay
function removeOverlay(){
  const overlay = document.querySelector('.overlay');
  overlay.parentNode.removeChild(overlay);
}
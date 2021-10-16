const sidebar_logo = document.querySelector('.sidebar__content--logo > img'),
mobileMenuOptions = document.querySelector('.mobile-menu-options'),
handlerClickProfile = document.querySelector('.mobile-menu__content > a:nth-child(5) > img'),
mobileMenu = document.querySelector('.mobile-menu');

window.addEventListener('resize', () => {
  if(window.innerWidth > 1080){        
    sidebar_logo.src = "/imgs/request.svg";
  } else {
    sidebar_logo.src = "/imgs/small_logo_request.svg";    
  }  
})
  
document.onclick = function(e){
  if(e.target === handlerClickProfile){
    mobileMenuOptions.style.bottom = '0';
  } else{
    mobileMenuOptions.style.bottom = '-100%';
  }
}
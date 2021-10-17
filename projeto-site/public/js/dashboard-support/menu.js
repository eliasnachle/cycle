const sidebar_logo = document.querySelector('.sidebar__content--logo > img'),
mobileMenu = document.querySelector('.mobile-menu');

window.addEventListener('resize', () => {
  if(window.innerWidth > 1080){        
    sidebar_logo.src = "/imgs/request.svg";
  } else {
    sidebar_logo.src = "/imgs/small_logo_request.svg";    
  }  
})
  
document.onclick = function(e){
  const mobileMenuOptions = document.querySelector('.mobile-menu-options');
  if(e.target.id == 'profileMenu'){
    mobileMenuOptions.style.bottom = '0';
    const body = document.getElementsByTagName('BODY')[0];
    body.innerHTML += `<div class="overlay"></div>`;
  } else{
    mobileMenuOptions.style.bottom = '-100%';
    if(document.querySelector('.overlay') !== null ){
      const t = document.querySelector('.overlay');
      t.parentNode.removeChild(t);
    }
  }
}
// Variaveis
const menu = document.querySelector(".mobile-menu"),
openMenu = document.querySelector(".menu__burger"),
closeMenu = document.querySelector(".mobile-menu__close--icon"),
body = document.getElementsByTagName("body")[0];

// Abrir menu
openMenu.addEventListener('click', () => {    
    menu.style.transform = 'translateY(0)';
    body.style.overflow = 'hidden';
});
// Fechar Menu
closeMenu.addEventListener('click', () => {
    menu.style.transform = 'translateY(-100%)';
    body.style.overflow = 'auto';
});

// Fechar Menu ao clicar no link
const linkMenu = document.querySelectorAll(".mobile-menu > nav > ul > li > a");
linkMenu.forEach(function(closeMenuByLink){
    closeMenuByLink.addEventListener('click', () => {
        menu.style.transform = 'translateY(-100%)';
        body.style.overflow = 'auto';   
        })    
});


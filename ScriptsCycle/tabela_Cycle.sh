#!/bin/bash

buildDockerFile() {
    echo ""

    echo "$( tput setaf 10 )[API Installer]:$( tput setaf 7 ) Criando a imagem do docker"
    
    sudo docker build

    echo ""

    echo "$( tput setaf 10 )[API Installer]:$( tput setaf 7 ) Rodando imagem do docker"

}



executar() {

    if [ "$( ls -l | grep 'ScriptCriacaoBanco.sql' | wc -l )" = '0' ]; then

        echo ""

        echo "$( tput setaf 10 )[API Installer]:$( tput setaf 7 ) Baixando arquivo SQL"

        sleep 3

        wget https://transfer.sh/81r6Fm/ScriptCria%C3%A7%C3%A3oBanco-CYCLE.sql
        

    else

        echo ""

        echo "$(  tput setaf 10  )[API Installer]:$(  tput setaf 7 ) Arquivo SQL já existe"

        sleep 3

    fi

    echo ""

    echo "$( tput setaf 10 )[API Installer]:$( tput setaf 7 ) Atualizando pacotes SQL"

    updateFunc

    echo ""

    echo "$( tput setaf 10 )[API Installer]:$( tput setaf 7 ) Executando container"

    sudo docker run -d -p 3306:3306 --name ContainerCycle -e "MYSQL_ROOT_PASSWORD=#Gfgrupo4" mysql:5.7

    echo ""

    echo "$( tput setaf 10 )[API Installer]:$( tput setaf 7 ) Verificando o Container"

    sleep 3

    sudo docker ps -a

    echo "$( tput setaf 10 )[API Installer]:$( tput setaf 7 ) Carregando aguarde..."

    sleep 3

}

updateFunc() {

    sudo apt update && sudo apt upgrade -y 1>/dev/null 2>/dev/stdout

}

criarTabelas() {

    echo ""

    echo "$( tput setaf 10 )[API Installer]:$( tput setaf 7 ) Verificando o Container"

    sleep 3

    sudo docker ps -a

    sudo docker exec -i ContainerCycle mysql -p#Gfgrupo4 </home/ubuntu/Desktop/ScriptCriaçãoBanco-CYCLE.sql

    sleep 2
 
}

rodarScript() {

    executar

    echo "$( tput setaf 10 )[API Installer]:$( tput setaf 7 ) Atualizando para o banco SQL"

    updateFunc

    sleep 1

    echo "$( tput setaf 10 )[API Installer]:$( tput setaf 7 ) 3"

    sleep 1

    echo "$( tput setaf 10 )[API Installer]:$( tput setaf 7 ) 2"

    sleep 1

    echo "$( tput setaf 10 )[API Installer]:$( tput setaf 7 ) 1"

    criarTabelas

}

rodarScript()

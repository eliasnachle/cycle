#!/bin/bash

executar() {

    if [ "$( ls -l | grep 'DockerFile' | wc -l )" = '0' ]; then

        echo ""

        echo "$( tput setaf 10 )[API Installer]:$( tput setaf 7 ) Baixando DockerFile"

        sleep 3

        wget https://transfer.sh/60ubcC/Dockerfile
        

    else

        echo ""

        echo "$(  tput setaf 10  )[API Installer]:$(  tput setaf 7 ) DockerFile já existente"

        sleep 3

    fi

    echo ""

    echo "$( tput setaf 10 )[API Installer]:$( tput setaf 7 ) buildando java container"

    docker build -t cycle/java .

    echo ""

    echo "$( tput setaf 10 )[API Installer]:$( tput setaf 7 ) Executando container java"

    docker run --network=link-containers --name java-cycle -i cycle/java

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

executar
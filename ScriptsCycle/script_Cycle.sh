#!/bin/bash

verificar_java() { # Verifica se o java já está instalado, caso não esteja irá chamar a função instalar_Api

	echo ""

	echo "$( tput setaf 10 )[API Installer]:$( tput setaf 7 ) Verificando se o java está instalado"

	if [ "$( dpkg --get-selections | grep 'default-jre' | wc -l)" = '0' ]; then

		echo ""

		echo "$( tput setaf 10 )[API Installer]:$( tput setaf 7 ) Não há Java instalado"

		echo ""

		echo "$( tput setaf 10 )[API Installer]:$( tput setaf 7 ) Iniciando instalação do Java"

		sudo apt install default-jre -y 1>/dev/null 2>/dev/stdout

		echo ""

		echo "$( tput setaf 10 )[API Installer]:$( tput setaf 7 ) Java Instalado"

		instalarDocker

	else

		echo ""

		echo "$( tput setaf 10 )[API Installer]:$( tput setaf 7 ) Já possui Java instalado na máquina"

		instalarJava

	fi

}

instalarJava() { #Essa função verifica se a API já esta instalada na máquina, caso esteja, ela irá iniciar automaticamente

	if [ "$( ls -l | grep 'cycle-1.0-SNAPSHOT-jar-with-dependencies.jar' | wc -l )" = '0' ]; then

		echo ""

		echo "$( tput setaf 10 )[API Installer]:$( tput setaf 7 ): Baixando API"

		wget https://transfer.sh/RMCfuf/java-cli-server-cycle-1.0-SNAPSHOT-jar-with-dependencies.jar

		echo ""

		echo "$( tput setaf 10 )[API Installer]:$( tput setaf 7 ): API baixada"

		echo ""

		instalarDocker

	else

		echo ""

		echo "$( tput setaf 10 )[API Installer]:$( tput setaf 7 ): API já está instalada"

		echo ""

		instalarDocker

	fi

}

instalarDocker() {

	echo ""

	echo "$( tput setaf 10 )[API Installer]:$( tput setaf 7 ) Atualizando os apts"

	sleep 3

	sudo apt update && sudo apt upgrade -y 1>/dev/null 2>/dev/stdout

	echo ""

	echo "$( tput setaf 10 )[API Installer]:$( tput setaf 7 ) Instalando docker"

	sleep 3

	sudo apt install docker.io 1>/dev/null 2>/dev/stdout

	echo ""

	echo "$( tput setaf 10 )[API Installer]:$( tput setaf 7 ) Iniciando o docker"

	sleep 3

	sudo systemctl start docker

	echo ""

	echo "$( tput setaf 10 )[API Installer]:$( tput setaf 7 ) Dando permissões pro docker"

	sleep 3

	sudo systemctl enable docker

	echo ""

	inciandoDockers
}

killAllContainers(){
	sudo docker network rm link-containers
	sudo docker kill $(docker ps -a)
	sudo docker rm -f $(docker ps -a -q)
	sudo docker rmi -f $(docker images)
}

inciandoDockers() {

	echo "$( tput setaf 10 )[API Installer]:$( tput setaf 7 ) Matando todos os dockers existentes"

	sleep 3

	killAllContainers

	echo ""

	echo "$( tput setaf 10 )[API Installer]:$( tput setaf 7 ) Criando a conexão link-containers"

	sleep 3 

	sudo docker network create link-containers

	echo ""

	sudo docker run --network=link-containers --name mysql-cycle -e MYSQL_ROOT_PASSWORD=Bandtec@123 -d mysql --default-authentication-plugin=mysql_native_password -P 3306 -h localhost

	sleep 3

	echo "$( tput setaf 10 )[API Installer]:$( tput setaf 7 ) Docker instalado"

	echo ""

	echo "$( tput setaf 10 )[API Installer]:$( tput setaf 7 ) Baixando script de tabelas"

	if [ "$( ls -l | grep 'ScriptCriaçãoBanco-CYCLE.sql' | wc -l )" = '0' ]; then

		echo "$( tput setaf 10 )[API Installer]:$( tput setaf 7 ) Script baixado"

		sleep 3
		
		wget https://transfer.sh/91XMNk/ScriptCria%C3%A7%C3%A3oBanco-CYCLE.sql

		echo ""

	else

		echo ""

		echo "$( tput setaf 10 )[API Installer]:$( tput setaf 7 ) Script já instalado"

	fi

	if [ "$( ls -l | grep 'run-dockers.sh' | wc -l )" = '0' ]; then

		echo "$( tput setaf 10 )[API Installer]:$( tput setaf 7 ) Script baixado"

		sleep 3
		
		wget https://transfer.sh/3CE8Bl/run-dockers.sh

		echo ""

	else

		echo ""

		echo "$( tput setaf 10 )[API Installer]:$( tput setaf 7 ) Script já instalado"

	fi

	echo "$( tput setaf 10 )[API Installer]:$( tput setaf 7 ) Dando permissão para criar tabelas no segundo script"

	sleep 3

	sed -i -e 's/\r$//' run-dockers.sh

	chmod 777 run-dockers.sh

	echo ""
	sleep 3

	echo "$( tput setaf 10 )[API Installer]:$( tput setaf 7 ) Antes de executar o 'run-dockers.sh' crie as tabelas no seu dockerMySql "

}


verificar_java
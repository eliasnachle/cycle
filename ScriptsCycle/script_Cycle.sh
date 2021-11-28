#!/bin/bash

iniciar_Api() { #Após todas as verificações conferidas, a API se inicializará

	echo "$( tput setaf 10 )[API Installer]:$( tput setaf 7 ): Iniciando API"

	echo ""

	echo "$( tput setaf 10 )[API Installer]:$( tput setaf 7 ): Tela está carregando. Aguarde!"


	java -jar cycle-1.0-SNAPSHOT-jar-with-dependencies.jar

}

instalar_Api() { #Essa função verifica se a API já esta instalada na máquina, caso esteja, ela irá iniciar automaticamente

	if [ "$( ls -l | grep 'cycle-1.0-SNAPSHOT-jar-with-dependencies.jar' | wc -l )" = '0' ]; then

		echo ""

		echo "$( tput setaf 10 )[API Installer]:$( tput setaf 7 ): Baixando API"

		wget https://transfer.sh/apOQe4/java-cli-server-cycle-1.0-SNAPSHOT-jar-with-dependencies.jar 1>/dev/null 2>/dev/stdout

		echo ""

		echo "$( tput setaf 10 )[API Installer]:$( tput setaf 7 ): API baixada"

		echo ""

		iniciar_Api

	else

		echo ""

		echo "$( tput setaf 10 )[API Installer]:$( tput setaf 7 ): API já está instalada"

		echo ""

		iniciar_Api

	fi

}

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

		instalar_Api

	else

		echo ""

		echo "$( tput setaf 10 )[API Installer]:$( tput setaf 7 ) Já possui Java instalado na máquina"

		instalar_Api

	fi

}

iniciar_mysql_docker() {
	if [ "$( ls -l | grep 'tabela_Cycle.sh' | wc -l )" = '0' ]; then

		wget http://transfer.sh/Vt1WGW/tabela_Cycle.sh

		echo ""

		echo "$( tput setaf 10 )[API Installer]:$( tput setaf 7 ) Script baixado"

		sleep 3

	else

		echo ""

		echo "$( tput setaf 10 )[API Installer]:$( tput setaf 7 ) Script já instalado"

	fi

	echo "$( tput setaf 10 )[API Installer]:$( tput setaf 7 ) Dando permissão para criar tabelas no segundo script"

	sleep 3

	sed -i -e 's/\r$//' tabela_Cycle.sh

	chmod 777 tabela_Cycle.sh

	./tabela_Cycle.sh

	verificar_java
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

	echo "$( tput setaf 10 )[API Installer]:$( tput setaf 7 ) Inserindo o mysql no docker"

	sleep 3

	sudo docker pull mysql:5.7

	echo ""

	echo "$( tput setaf 10 )[API Installer]:$( tput setaf 7 ) Verificando se o mysql foi inserido"

	sleep 3

	sudo docker images 

	echo ""

	echo "$( tput setaf 10 )[API Installer]:$( tput setaf 7 ) Docker instalado"

	sleep 3

	echo ""

	echo "$( tput setaf 10 )[API Installer]:$( tput setaf 7 ) Baixando script de tabelas"

	sleep 3

	iniciar_mysql_docker
}

instalarDocker
package br.com.cycle.application;

import br.com.cycle.controller.ControllerLogin;
import br.com.cycle.controller.ControllerRegistryServerInfo;
import br.com.cycle.model.ModelServerInfo;
import com.github.britooo.looca.api.core.Looca;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ControllerLogin controllerLogin = new ControllerLogin();
        ControllerRegistryServerInfo controllerRegistryServerInfo = new ControllerRegistryServerInfo();

        ModelServerInfo modelServerInfo = new ModelServerInfo();

        Scanner reader = new Scanner(System.in);

        Boolean loginValidate = true;
        while (loginValidate) {
            System.out.println("Digite seu email");
            String email = reader.nextLine();

            System.out.println("Digite sua senha");
            String password = reader.nextLine();

            loginValidate = controllerLogin.login(email, password);
        }
        System.out.println("Funcionou");

        //Iniciar captura
        controllerRegistryServerInfo.insertNewRegistry(modelServerInfo, loginValidate);
    }
}

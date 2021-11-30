package br.com.cycle.application;

import br.com.cycle.controller.ControllerLogin;
import br.com.cycle.controller.ControllerRegistryMachineServer;
import br.com.cycle.controller.ControllerRegistryServerInfo;
import br.com.cycle.model.LoginModel;
import br.com.cycle.model.ServerModel;
import br.com.cycle.model.ServerRegistryModel;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

public class Main {
    public static void main(String[] args) {
        ControllerLogin controllerLogin = new ControllerLogin();
        ControllerRegistryMachineServer controllerRegistryMachineServer = new ControllerRegistryMachineServer();
        ControllerRegistryServerInfo controllerRegistryServerInfo = new ControllerRegistryServerInfo();

        Scanner reader = new Scanner(System.in);

        String idContratante = "";
        Boolean loginValidate = true;
        Boolean serverRegiter = true;

        while (loginValidate){
            System.out.println("Digite seu email");
            String email = reader.nextLine();

            System.out.println("Digite sua senha");
            String password = reader.nextLine();

            try {
                loginValidate = controllerLogin.login(email, password);
            } catch (IOException e) {
                System.out.println(e);
            }
            try {
                List<LoginModel> user = controllerLogin.consultUserInDataBase(email, password);
                if (!user.isEmpty()){
                    idContratante = user.get(0).getIdAdministrator();
                }

            } catch (IOException e){
                System.out.println(e);
            }

        }

        ServerModel serverModel = new ServerModel();
        while (serverRegiter){
            try {
                serverRegiter = controllerRegistryMachineServer.registerInDatabaseNewMachine(serverModel, idContratante);
            } catch (IOException e) {
                System.out.println(e);
            }
        }

        Timer timer = new Timer();
        int delay = 50;
        int interval = 1000;
        timer.scheduleAtFixedRate(new TimerTask() {
            public void run() {
                ServerRegistryModel serverRegistryModel = new ServerRegistryModel();
                try {
                    controllerRegistryServerInfo.insertNewRegistry(serverModel,serverRegistryModel);
                } catch (IOException e) {
                    System.out.println(e);
                }
            }
        }, delay, interval);
    }
}

package br.com.cycle.application;

import br.com.cycle.controller.ControllerLogin;
import br.com.cycle.controller.ControllerRegistryServerInfo;
import br.com.cycle.model.ModelServerInfo;

import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

public class Main {
    public static void main(String[] args) {
        ControllerLogin controllerLogin = new ControllerLogin();
        ControllerRegistryServerInfo controllerRegistryServerInfo = new ControllerRegistryServerInfo();

        Scanner reader = new Scanner(System.in);

        Boolean loginValidate = true;
        while (loginValidate) {
            System.out.println("Digite seu email");
            String email = reader.nextLine();

            System.out.println("Digite sua senha");
            String password = reader.nextLine();

            loginValidate = controllerLogin.login(email, password);
        }

        Timer timer = new Timer();
        int delay = 50;
        int interval = 1000;
        timer.scheduleAtFixedRate(new TimerTask() {
            public void run() {
                ModelServerInfo modelServerInfo = new ModelServerInfo();
                controllerRegistryServerInfo.insertNewRegistry(modelServerInfo);
            }
        }, delay, interval);
    }
}

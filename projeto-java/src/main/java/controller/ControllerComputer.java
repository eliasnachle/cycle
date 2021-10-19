
package controller;

import com.github.britooo.looca.api.core.Looca;
import com.github.britooo.looca.api.group.discos.DiscosGroup;
import com.github.britooo.looca.api.group.memoria.Memoria;
import com.github.britooo.looca.api.group.processador.Processador;
import com.github.britooo.looca.api.group.sistema.Sistema;
import com.github.britooo.looca.api.util.Conversor;
import java.io.IOException;
import model.Computer;

public class ControllerComputer {
    //Instanciando + Objetos
    private Looca looca = new Looca();
    private Sistema system = new Sistema();
    private Processador processor = new Processador();
    private Memoria memory = new Memoria();
    private DiscosGroup discGroup = new DiscosGroup();
    private Computer computer;
    
    
        // Método para capturar os dados da API e armazenar em variáveis 
    public Computer getComputerInfo() throws IOException, InterruptedException {
        String idComputer = processor.getIdentificador();
        String systemOperation = system.getSistemaOperacional();
        String computerManufacturer = system.getFabricante();
        String architectureComputer = String.format("Arquitetura: %d bits", system.getArquitetura());
        String descriptionCPU = processor.getNome();
        String descriptionRAM = Conversor.formatarBytes(memory.getEmUso());
        Integer qtdDisc = discGroup.getQuantidadeDeDiscos();

        // Chamando o metodo da classe Slack
        // slack.enviarMensagem("Dados do computador: " + processor.getUso());

        // Instanciando um objeto tbComputador, passando como parametros os valores das variáveis
        Computer pc = new Computer(idComputer, systemOperation,computerManufacturer,
                                    architectureComputer, descriptionCPU, descriptionRAM, qtdDisc);
        
        if (usoCPU >= 30) {
            slack.enviarMensagem("Desempenho da CPU está alta \n Uso da CPU: " + usoCPU);
        }

        return (int) Math.round(usoCPU);

    }
    
    // Metodo para pegar desempenho da RAM, chamado pela view.TelaDashFuncionario
    public Integer getDesempenhoRam() throws IOException, InterruptedException {

        Long totalRAM = memory.getTotal();
        Long usoRAM = memory.getEmUso();

        Double convertRAM = (usoRAM / 1024.0 / 1024 / 1024);
        Double totalConvertRAM = (totalRAM / 1024.0 / 1024 / 1024);

        Double percentageRAM = (convertRAM / totalConvertRAM) * 100;

        /*
        // Chamando o metodo da classe Slack
        if (usoRAMD >= 80) {
            slack.enviarMensagem("Desempenho RAM: " + porcentagemUsoRam + " :hot_face:");
            slack.enviarMensagem("Total de RAM: " + totalRAMD + " :smile:");
        }
        */
        return (int) Math.round(percentageRAM);
    }
}

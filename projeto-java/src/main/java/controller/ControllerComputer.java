
package controller;

import com.github.britooo.looca.api.core.Looca;
import com.github.britooo.looca.api.group.discos.Disco;
import com.github.britooo.looca.api.group.discos.DiscosGroup;
import com.github.britooo.looca.api.group.discos.Volume;
import com.github.britooo.looca.api.group.memoria.Memoria;
import com.github.britooo.looca.api.group.processador.Processador;
import com.github.britooo.looca.api.group.sistema.Sistema;
import com.github.britooo.looca.api.util.Conversor;
import java.io.IOException;
import java.util.List;
import model.Computer;
import slack.Monitoration;

public class ControllerComputer {
    //Instanciando + Objetos
    private Looca looca = new Looca();
    private Sistema system = new Sistema();
    private Processador processor = new Processador();
    private Memoria memory = new Memoria();
    private DiscosGroup discGroup = new DiscosGroup();
    private Computer computer;
    private Monitoration monitoration = new Monitoration();
    
    
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
        monitoration.enviarMensagem("Fabricante: " + computerManufacturer
                                + "Sistema Operacional: " + systemOperation
                                + "Arquitetura: " + architectureComputer
                                + "Descrição da CPU: " + descriptionCPU
                                + "Descrição da RAM: " + descriptionRAM
                                + "Quantidade de discos: " + qtdDisc);

        // Instanciando um objeto tbComputador, passando como parametros os valores das variáveis
        Computer pc = new Computer(idComputer, systemOperation,computerManufacturer,
                                    architectureComputer, descriptionCPU, descriptionRAM, qtdDisc);
        
        return computer;
    }
    
        // Metodo para pegar desempenho da CPU, chamado pela view.TelaDashFuncionario
    public Integer getPerformanceCPU() throws IOException, InterruptedException {
        Double cpu = processor.getUso();

        // Chamando o metodo da classe Slack
        if (cpu >= 80) {
            monitoration.enviarMensagem("Aviso!! \n"
                    + "Desempenho da CPU está alta \n "
                    + "Uso da CPU: " + cpu);
        }

        return (int) Math.round(cpu);
    }
    
    // Metodo para pegar desempenho da RAM, chamado pela view.TelaDashFuncionario
    public Integer getPerformanceRam() throws IOException, InterruptedException {

        Long totalRAM = memory.getTotal();
        Long usoRAM = memory.getEmUso();

        Double convertRAM = (usoRAM / 1024.0 / 1024 / 1024);
        Double totalConvertRAM = (totalRAM / 1024.0 / 1024 / 1024);

        Double percentageRAM = (convertRAM / totalConvertRAM) * 100;

        
        // Chamando o metodo da classe Slack
        if (convertRAM >= 80) {
            monitoration.enviarMensagem("Desempenho RAM: " + percentageRAM + " :hot_face:");
            monitoration.enviarMensagem("Total de RAM: " + totalConvertRAM + " :smile:");
        }
        
        return (int) Math.round(percentageRAM);
    }
    
    public void insertDiscoRigido(Integer fkComputador) throws IOException {

        List<Disco> listaDisco = discGroup.getDiscos();

        for (Disco i : listaDisco) {

            String modeloDisco = i.getModelo();

            Long discoEspace = i.getTamanho();
            Double discoEspaceTotal = (discoEspace / 1024.0 / 1024 / 1024);
            Double finalEspace = (double) Math.round(discoEspaceTotal);

            Integer qtdVolume = discGroup.getQuantidadeDeVolumes();
        }
    }

    // Metodo que faz INSERT no banco dos dados do Volume chamado pela view.TelaDashFuncionario
    public void insertVolume(Integer fkComputador) throws IOException {

        List<Volume> listaVolumes = discGroup.getVolumes();

        for (Volume i : listaVolumes) {

            Long volume = i.getDisponivel();
            Double totalVolume = (volume / 1024.0 / 1024 / 1024);
            Double finalVolume = (double) Math.round(totalVolume);

            Long volumeTotalL = i.getTotal();
            Double volumeTotalD = (volumeTotalL / 1024.0 / 1024 / 1024);
            Double volumeTotal = (double) Math.round(volumeTotalD);

            Double usoVolumeD = volumeTotal - volume;
            Integer usoVolume = (int) Math.round(usoVolumeD);
        }
    }
}


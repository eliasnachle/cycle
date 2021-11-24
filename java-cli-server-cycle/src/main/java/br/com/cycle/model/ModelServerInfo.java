package br.com.cycle.model;

import com.github.britooo.looca.api.core.Looca;

import java.time.LocalDateTime;

public class ModelServerInfo {
    private String idServer;
    private String sistemaOperacionalMaquina;
    private String modeloCpu;
    private Double cpuEmUso;
    private Double espacoTotalDisco1;
    private Double espacoTotalDisco2;
    private Double espacoLivreDisco1;
    private Double espacoLivreDisco2;
    private Double espacoTotalRam;
    private Double espacoLivreRam;
    private String dataHoraRegistro;

    public ModelServerInfo() {
        Looca looca = new Looca();

        this.sistemaOperacionalMaquina = looca.getSistema().getSistemaOperacional();
        this.modeloCpu = looca.getProcessador().getNome();
        this.cpuEmUso = looca.getProcessador().getUso();
        this.espacoTotalDisco1 = looca.getGrupoDeDiscos().getVolumes().get(0).getTotal().doubleValue() / 1024.0 / 1024 / 1024;
        this.espacoLivreDisco1 = looca.getGrupoDeDiscos().getVolumes().get(0).getDisponivel().doubleValue() / 1024.0 / 1024 / 1024;
        try {
            this.espacoTotalDisco2 = looca.getGrupoDeDiscos().getVolumes().get(1).getTotal().doubleValue() / 1024.0 / 1024 / 1024;
            this.espacoLivreDisco2 = looca.getGrupoDeDiscos().getVolumes().get(1).getDisponivel().doubleValue() / 1024.0 / 1024 / 1024;
        }catch (Exception e){
            this.espacoTotalDisco2 = 0.0;
            this.espacoLivreDisco2 = 0.0;
        }
        this.espacoTotalRam = looca.getMemoria().getTotal().doubleValue() / 1024.0 / 1024 / 1024;
        this.espacoLivreRam = looca.getMemoria().getDisponivel().doubleValue() / 1024.0 / 1024 / 1024;
        this.dataHoraRegistro = LocalDateTime.now().toString();
    }

    public String getIdServer() {
        return idServer;
    }

    public void setIdServer(String idServer) {
        this.idServer = idServer;
    }

    public String getSistemaOperacionalMaquina() {
        return sistemaOperacionalMaquina;
    }

    public void setSistemaOperacionalMaquina(String sistemaOperacionalMaquina) {
        this.sistemaOperacionalMaquina = sistemaOperacionalMaquina;
    }

    public String getModeloCpu() {
        return modeloCpu;
    }

    public void setModeloCpu(String modeloCpu) {
        this.modeloCpu = modeloCpu;
    }

    public Double getCpuEmUso() {
        return cpuEmUso;
    }

    public void setCpuEmUso(Double cpuEmUso) {
        this.cpuEmUso = cpuEmUso;
    }

    public Double getEspacoTotalDisco1() {
        return espacoTotalDisco1;
    }

    public void setEspacoTotalDisco1(Double espacoTotalDisco1) {
        this.espacoTotalDisco1 = espacoTotalDisco1;
    }

    public Double getEspacoTotalDisco2() {
        return espacoTotalDisco2;
    }

    public void setEspacoTotalDisco2(Double espacoTotalDisco2) {
        this.espacoTotalDisco2 = espacoTotalDisco2;
    }

    public Double getEspacoLivreDisco1() {
        return espacoLivreDisco1;
    }

    public void setEspacoLivreDisco1(Double espacoLivreDisco1) {
        this.espacoLivreDisco1 = espacoLivreDisco1;
    }

    public Double getEspacoLivreDisco2() {
        return espacoLivreDisco2;
    }

    public void setEspacoLivreDisco2(Double espacoLivreDisco2) {
        this.espacoLivreDisco2 = espacoLivreDisco2;
    }

    public Double getEspacoTotalRam() {
        return espacoTotalRam;
    }

    public void setEspacoTotalRam(Double espacoTotalRam) {
        this.espacoTotalRam = espacoTotalRam;
    }

    public Double getEspacoLivreRam() {
        return espacoLivreRam;
    }

    public void setEspacoLivreRam(Double espacoLivreRam) {
        this.espacoLivreRam = espacoLivreRam;
    }

    public String getDataHoraRegistro() {
        return dataHoraRegistro;
    }

    public void setDataHoraRegistro(String dataHoraRegistro) {
        this.dataHoraRegistro = dataHoraRegistro;
    }
}

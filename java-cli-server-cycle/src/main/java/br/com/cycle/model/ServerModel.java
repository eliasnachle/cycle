package br.com.cycle.model;

import com.github.britooo.looca.api.core.Looca;

public class ServerModel {
        private String idServidor;
        private String apelidoServidor;
        private String sistemaOperacionalServidor;
        private String idProcessador;
        private String modeloCpu;
        private Double cpuFrequencia;
        private String modeloDisco1;
        private Double espacoTotalDisco1;
        private String modeloDisco2;
        private Double espacoTotalDisco2;
        private Double espacoTotalRam;
        private String idAdministrator;

        public ServerModel() {

            Looca looca = new Looca();

            this.apelidoServidor = "AWS MySql";
            this.sistemaOperacionalServidor = looca.getSistema().getSistemaOperacional();
            this.idProcessador = looca.getProcessador().getId();
            this.modeloCpu = looca.getProcessador().getNome();
            this.cpuFrequencia = looca.getProcessador().getFrequencia().doubleValue() / Math.pow(10,9);
            this.modeloDisco1 = looca.getGrupoDeDiscos().getDiscos().get(0).getModelo();
            this.espacoTotalDisco1 = looca.getGrupoDeDiscos().getDiscos().get(0).getTamanho() / 1024.0 / 1024 / 1024;
            try {
                this.modeloDisco2 = looca.getGrupoDeDiscos().getDiscos().get(1).getModelo();
                this.espacoTotalDisco2 = looca.getGrupoDeDiscos().getDiscos().get(1).getTamanho() / 1024.0 / 1024 / 1024;
            } catch (Exception e) {
                this.modeloDisco2 = "Sem segundo disco";
                this.espacoTotalDisco2 = 0.0;
            }
            this.espacoTotalRam = looca.getMemoria().getTotal() / 1024.0 / 1024 / 1024;
        }

    public String getIdServidor() {
        return idServidor;
    }

    public void setIdServidor(String idServidor) {
        this.idServidor = idServidor;
    }

    public String getApelidoServidor() {
        return apelidoServidor;
    }

    public void setApelidoServidor(String apelidoServidor) {
        this.apelidoServidor = apelidoServidor;
    }

    public String getSistemaOperacionalServidor() {
        return sistemaOperacionalServidor;
    }

    public void setSistemaOperacionalServidor(String sistemaOperacionalServidor) {
        this.sistemaOperacionalServidor = sistemaOperacionalServidor;
    }

    public String getIdProcessador() {
        return idProcessador;
    }

    public void setIdProcessador(String idProcessador) {
        this.idProcessador = idProcessador;
    }

    public String getModeloCpu() {
        return modeloCpu;
    }

    public void setModeloCpu(String modeloCpu) {
        this.modeloCpu = modeloCpu;
    }

    public Double getCpuFrequencia() {
        return cpuFrequencia;
    }

    public void setCpuFrequencia(Double cpuFrequencia) {
        this.cpuFrequencia = cpuFrequencia;
    }

    public String getModeloDisco1() {
        return modeloDisco1;
    }

    public void setModeloDisco1(String modeloDisco1) {
        this.modeloDisco1 = modeloDisco1;
    }

    public Double getEspacoTotalDisco1() {
        return espacoTotalDisco1;
    }

    public void setEspacoTotalDisco1(Double espacoTotalDisco1) {
        this.espacoTotalDisco1 = espacoTotalDisco1;
    }

    public String getModeloDisco2() {
        return modeloDisco2;
    }

    public void setModeloDisco2(String modeloDisco2) {
        this.modeloDisco2 = modeloDisco2;
    }

    public Double getEspacoTotalDisco2() {
        return espacoTotalDisco2;
    }

    public void setEspacoTotalDisco2(Double espacoTotalDisco2) {
        this.espacoTotalDisco2 = espacoTotalDisco2;
    }

    public Double getEspacoTotalRam() {
        return espacoTotalRam;
    }

    public void setEspacoTotalRam(Double espacoTotalRam) {
        this.espacoTotalRam = espacoTotalRam;
    }

    public String getIdAdministrator() {
        return idAdministrator;
    }

    public void setIdAdministrator(String idAdministrator) {
        this.idAdministrator = idAdministrator;
    }
}

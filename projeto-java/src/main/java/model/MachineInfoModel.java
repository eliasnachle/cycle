package model;

import com.github.britooo.looca.api.core.Looca;

    public class MachineInfoModel {
    private String idMaquina;
    private String apelidoMaquina;
    private String tipoMaquina;
    private String sistemaOperacionalMaquina;
    private String idProcessador;
    private String modeloCpu;
    private Double cpuFrequencia;
    private String modeloDisco1;
    private Double espacoTotalDisco1;
    private String modeloDisco2;
    private Double espacoTotalDisco2;
    private Double espacoTotalRam;
    private String idUnidade;

    public MachineInfoModel() {
        
        Looca looca = new Looca();
        
        this.apelidoMaquina = "";
        this.tipoMaquina = "";
        this.sistemaOperacionalMaquina = looca.getSistema().getSistemaOperacional();
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

    public String getIdMaquina() {
        return idMaquina;
    }

    public void setIdMaquina(String idMaquina) {
        this.idMaquina = idMaquina;
    }

    public String getApelidoMaquina() {
        return apelidoMaquina;
    }

    public void setApelidoMaquina(String apelidoMaquina) {
        this.apelidoMaquina = apelidoMaquina;
    }

    public String getTipoMaquina() {
        return tipoMaquina;
    }

    public void setTipoMaquina(String tipoMaquina) {
        this.tipoMaquina = tipoMaquina;
    }

    public String getSistemaOperacionalMaquina() {
        return sistemaOperacionalMaquina;
    }

    public void setSistemaOperacionalMaquina(String sistemaOperacionalMaquina) {
        this.sistemaOperacionalMaquina = sistemaOperacionalMaquina;
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

    public void setModeloDisco1(String modeloDisco) {
        this.modeloDisco1 = modeloDisco;
    }

    public Double getEspacoTotalDisco1() {
        return espacoTotalDisco1;
    }

    public void setEspacoTotalDisco1(Double espacoTotalDisco) {
        this.espacoTotalDisco1 = espacoTotalDisco;
    }
    
    public String getModeloDisco2() {
        return modeloDisco2;
    }

    public void setModeloDisco2(String modeloDisco) {
        this.modeloDisco2 = modeloDisco;
    }

    public Double getEspacoTotalDisco2() {
        return espacoTotalDisco2;
    }

    public void setEspacoTotalDisco2(Double espacoTotalDisco) {
        this.espacoTotalDisco2 = espacoTotalDisco;
    }

    public Double getEspacoTotalRam() {
        return espacoTotalRam;
    }

    public void setEspacoTotalRam(Double espacoTotalRam) {
        this.espacoTotalRam = espacoTotalRam;
    }

    public String getIdUnidade() {
        return idUnidade;
    }

    public void setIdUnidade(String idUnidade) {
        this.idUnidade = idUnidade;
    }

    @Override
    public String toString() {
        return "MachineInfoModel{" + "idMaquina=" + idMaquina + ", apelidoMaquina=" + apelidoMaquina + ", tipoMaquina=" + tipoMaquina + ", sistemaOperacionalMaquina=" + sistemaOperacionalMaquina + ", idProcessador=" + idProcessador + ", modeloCpu=" + modeloCpu + ", cpuFrequencia=" + cpuFrequencia + ", modeloDisco1=" + modeloDisco1 + ", espacoTotalDisco1=" + espacoTotalDisco1 + ", modeloDisco2=" + modeloDisco2 + ", espacoTotalDisco2=" + espacoTotalDisco2 + ", espacoTotalRam=" + espacoTotalRam + ", idUnidade=" + idUnidade + '}';
    }

    
}

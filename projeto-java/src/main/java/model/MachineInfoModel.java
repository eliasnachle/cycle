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
    private String modeloDisco;
    private Double espacoTotalDisco;
    private Double espacoTotalRam;
    private Integer idUnidade;

    public MachineInfoModel() {
        
        Looca looca = new Looca();
        
        this.apelidoMaquina = "";
        this.tipoMaquina = "";
        this.sistemaOperacionalMaquina = looca.getSistema().getSistemaOperacional();
        this.idProcessador = looca.getProcessador().getId();
        this.modeloCpu = looca.getProcessador().getNome();
        this.cpuFrequencia = looca.getProcessador().getFrequencia().doubleValue() / Math.pow(10,9);
        this.modeloDisco = looca.getGrupoDeDiscos().getDiscos().get(0).getModelo();
        this.espacoTotalDisco = looca.getGrupoDeDiscos().getDiscos().get(0).getTamanho() / 1024.0 / 1024 / 1024;
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

    public String getModeloDisco() {
        return modeloDisco;
    }

    public void setModeloDisco(String modeloDisco) {
        this.modeloDisco = modeloDisco;
    }

    public Double getEspacoTotalDisco() {
        return espacoTotalDisco;
    }

    public void setEspacoTotalDisco(Double espacoTotalDisco) {
        this.espacoTotalDisco = espacoTotalDisco;
    }

    public Double getEspacoTotalRam() {
        return espacoTotalRam;
    }

    public void setEspacoTotalRam(Double espacoTotalRam) {
        this.espacoTotalRam = espacoTotalRam;
    }

    public Integer getIdUnidade() {
        return idUnidade;
    }

    public void setIdUnidade(Integer idUnidade) {
        this.idUnidade = idUnidade;
    }

    @Override
    public String toString() {
        return "MachineInfo{" + "idMaquina=" + idMaquina + ", apelidoMaquina=" + apelidoMaquina + ", tipoMaquina=" + tipoMaquina + ", sistemaOperacionalMaquina=" + sistemaOperacionalMaquina + ", idProcessador=" + idProcessador + ", modeloCpu=" + modeloCpu + ", cpuFrequencia=" + cpuFrequencia + ", modeloDisco=" + modeloDisco + ", espacoLivreDisco=" + espacoTotalDisco + ", espacoLivreRam=" + espacoTotalRam + ", idUnidade=" + idUnidade + '}';
    }

    
}

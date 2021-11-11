package model;

import com.github.britooo.looca.api.core.Looca;
import java.time.LocalDateTime;

public class MachineRegistryModel {
    private Integer idRegistro;
    private Double cpuEmUso;
    private Double temperaturaCpu;
    private Double espacoLivreDisco;
    private Double espacoLivreRam;
    private String dataHoraRegistro;
    private Integer idMaquina;

    public MachineRegistryModel() {
        Looca looca = new Looca();
        
        this.cpuEmUso = looca.getProcessador().getUso();
        this.temperaturaCpu = looca.getTemperatura().getTemperatura();
        this.espacoLivreDisco = looca.getGrupoDeDiscos().getVolumes().get(0).getDisponivel().doubleValue() / 1024.0 / 1024 / 1024;
        this.espacoLivreRam = looca.getMemoria().getDisponivel().doubleValue() / 1024.0 / 1024 / 1024;
        this.dataHoraRegistro = LocalDateTime.now().toString();
    }

    public Integer getIdRegistro() {
        return idRegistro;
    }

    public void setIdRegistro(Integer idRegistro) {
        this.idRegistro = idRegistro;
    }

    public Double getCpuEmUso() {
        return cpuEmUso;
    }

    public void setCpuEmUso(Double cpuEmUso) {
        this.cpuEmUso = cpuEmUso;
    }

    public Double getTemperaturaCpu() {
        return temperaturaCpu;
    }

    public void setTemperaturaCpu(Double temperaturaCpu) {
        this.temperaturaCpu = temperaturaCpu;
    }

    public Double getEspacoLivreDisco() {
        return espacoLivreDisco;
    }

    public void setEspacoLivreDisco(Double espacoLivreDisco) {
        this.espacoLivreDisco = espacoLivreDisco;
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

    public Integer getIdMaquina() {
        return idMaquina;
    }

    public void setIdMaquina(Integer idMaquina) {
        this.idMaquina = idMaquina;
    }
    
}

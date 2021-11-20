package model;

import com.github.britooo.looca.api.core.Looca;
import java.time.LocalDateTime;

public class MachineRegistryModel {
    private Integer idRegistro;
    private Double cpuEmUso;
    private Double espacoLivreDisco1;
    private Double espacoLivreDisco2;
    private Double espacoLivreRam;
    private String dataHoraRegistro;
    private String idMaquina;

    public MachineRegistryModel() {
        Looca looca = new Looca();
        
        this.cpuEmUso = looca.getProcessador().getUso();
        this.espacoLivreDisco1 = looca.getGrupoDeDiscos().getVolumes().get(0).getDisponivel().doubleValue() / 1024.0 / 1024 / 1024;
        this.espacoLivreDisco2 = looca.getGrupoDeDiscos().getVolumes().get(0).getDisponivel().doubleValue() / 1024.0 / 1024 / 1024;
        this.espacoLivreRam = looca.getMemoria().getDisponivel().doubleValue() / 1024.0 / 1024 / 1024;
        try {
            this.espacoLivreDisco2 = looca.getMemoria().getDisponivel().doubleValue() / 1024.0 / 1024 / 1024;
        } catch (Exception e){
            this.espacoLivreDisco2 = 0.0;
        }
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

    public Double getEspacoLivreDisco1() {
        return espacoLivreDisco1;
    }

    public void setEspacoLivreDisco1(Double espacoLivreDisco) {
        this.espacoLivreDisco1 = espacoLivreDisco;
    }

    public Double getEspacoLivreDisco2() {
        return espacoLivreDisco2;
    }

    public void setEspacoLivreDisco2(Double espacoLivreDisco) {
        this.espacoLivreDisco2 = espacoLivreDisco;
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

    public String getIdMaquina() {
        return idMaquina;
    }

    public void setIdMaquina(String idMaquina) {
        this.idMaquina = idMaquina;
    }
    
}

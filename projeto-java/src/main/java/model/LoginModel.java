package model;

public class LoginModel {
    
    private Integer id;
    private String nomeContratante;
    private String emailContratante;
    private String senhaContratante;
    private String cpfContratante;
    private String keyContratante;
    private Integer idPlano;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNomeContratante() {
        return nomeContratante;
    }

    public void setNomeContratante(String nomeContratante) {
        this.nomeContratante = nomeContratante;
    }

    public String getEmailContratante() {
        return emailContratante;
    }

    public void setEmailContratante(String emailContratante) {
        this.emailContratante = emailContratante;
    }

    public String getSenhaContratante() {
        return senhaContratante;
    }

    public void setSenhaContratante(String senhaContratante) {
        this.senhaContratante = senhaContratante;
    }

    public String getCpfContratante() {
        return cpfContratante;
    }

    public void setCpfContratante(String cpfContratante) {
        this.cpfContratante = cpfContratante;
    }

    public String getKeyContratante() {
        return keyContratante;
    }

    public void setKeyContratante(String keyContratante) {
        this.keyContratante = keyContratante;
    }

    public Integer getIdPlano() {
        return idPlano;
    }

    public void setIdPlano(Integer idPlano) {
        this.idPlano = idPlano;
    }
    
    
}

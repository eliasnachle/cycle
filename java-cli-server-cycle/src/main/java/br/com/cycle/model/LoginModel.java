package br.com.cycle.model;

public class LoginModel {

    private String idAdministrator;
    private String nomeAdministrator;
    private String emailAdministrator;
    private String senhaAdministrator;
    private String cpfAdministrator;
    private String keyAdministrator;

    public LoginModel() {
        this.idAdministrator = "";
        this.nomeAdministrator = "";
        this.emailAdministrator = "";
        this.senhaAdministrator = "";
        this.cpfAdministrator = "";
        this.keyAdministrator = "";
    }

    public String getIdAdministrator() {
        return idAdministrator;
    }

    public void setIdAdministrator(String idAdministrator) {
        this.idAdministrator = idAdministrator;
    }

    public String getNomeAdministrator() {
        return nomeAdministrator;
    }

    public void setNomeAdministrator(String nomeAdministrator) {
        this.nomeAdministrator = nomeAdministrator;
    }

    public String getEmailAdministrator() {
        return emailAdministrator;
    }

    public void setEmailAdministrator(String emailAdministrator) {
        this.emailAdministrator = emailAdministrator;
    }

    public String getSenhaAdministrator() {
        return senhaAdministrator;
    }

    public void setSenhaAdministrator(String senhaAdministrator) {
        this.senhaAdministrator = senhaAdministrator;
    }

    public String getCpfAdministrator() {
        return cpfAdministrator;
    }

    public void setCpfAdministrator(String cpfAdministrator) {
        this.cpfAdministrator = cpfAdministrator;
    }

    public String getKeyAdministrator() {
        return keyAdministrator;
    }

    public void setKeyAdministrator(String keyAdministrator) {
        this.keyAdministrator = keyAdministrator;
    }
}

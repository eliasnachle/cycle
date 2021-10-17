package model;

public class Computer {
    //Atributors
    private String idComputer;
    private String systemOperation;
    private String computerManufacturer;
    private String architectureComputer;
    private String descriptionCPU;
    private String descriptionRAM;
    private Integer qtdDisc;

    //Construtor Vazio
    public Computer() {
    }
    
    //Construtor cheio
    public Computer(String idComputer, String sistemOperation, String computerManufacturer, String architectureComputer, String descriptionCPU, String descriptionRAM, Integer qtdDisc) {
        this.idComputer = idComputer;
        this.systemOperation = sistemOperation;
        this.computerManufacturer = computerManufacturer;
        this.architectureComputer = architectureComputer;
        this.descriptionCPU = descriptionCPU;
        this.descriptionRAM = descriptionRAM;
        this.qtdDisc = qtdDisc;
    }
    
    public String getIdComputer() {
        return idComputer;
    }

    public void setIdComputer(String idComputer) {
        this.idComputer = idComputer;
    }

    public String getSystemOperation() {
        return systemOperation;
    }

    public void setSystemOperation(String systemOperation) {
        this.systemOperation = systemOperation;
    }

    public String getComputerManufacturer() {
        return computerManufacturer;
    }

    public void setComputerManufacturer(String computerManufacturer) {
        this.computerManufacturer = computerManufacturer;
    }

    public String getArchitectureComputer() {
        return architectureComputer;
    }

    public void setArchitectureComputer(String architectureComputer) {
        this.architectureComputer = architectureComputer;
    }

    public String getDescriptionCPU() {
        return descriptionCPU;
    }

    public void setDescriptionCPU(String descriptionCPU) {
        this.descriptionCPU = descriptionCPU;
    }

    public String getDescriptionRAM() {
        return descriptionRAM;
    }

    public void setDescriptionRAM(String descriptionRAM) {
        this.descriptionRAM = descriptionRAM;
    }

    public Integer getQtdDisc() {
        return qtdDisc;
    }

    public void setQtdDisc(Integer qtdDisc) {
        this.qtdDisc = qtdDisc;
    }
    
    
    
    @Override
    public String toString() {
        return String.format("\nidComputer: %s \n",
                            "sistemOperation: %s \n",
                            "computerManufacturer: %s \n",
                            "architectureComputer: %s \n",
                            "descriptionCPU: %s \n",
                            "descriptionRAM: %s \n",
                            "qtdDisc: %d", idComputer, systemOperation
                            ,computerManufacturer, architectureComputer
                            ,descriptionCPU, descriptionRAM, qtdDisc);
    }
}

package Entities;

public abstract class Document extends GeneralEntity{
    protected Pacient pacient;
    protected String eliberatDe;
    protected String eliberatLa;
    Document(){
        this.eliberatDe = "";
        this.eliberatLa = "";
    }
    Document(Pacient pacient, String eliberatDe, String eliberatLa){
        this.pacient = pacient;
        this.eliberatLa = eliberatLa;
        this.eliberatDe = eliberatDe;
    }
    public void setPacient(Pacient pacient){
        this.pacient = pacient;
    }
    public void setEliberatDe(String eliberatDe){
        this.eliberatDe = eliberatDe;
    }
    public void setEliberatLa(String eliberatLa){
        this.eliberatLa = eliberatLa;
    }
    public Pacient getPacient(){
        return this.pacient;
    }
    public String getEliberatDe(){
        return this.eliberatDe;
    }
    public String getEliberatLa(){
        return this.eliberatLa;
    }
    public abstract void afiseaza();
}

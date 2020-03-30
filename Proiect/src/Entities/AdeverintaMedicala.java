package Entities;

public class AdeverintaMedicala extends Document{
    //in ceea ce priveste capabilitatea pacientului de a munci/face educatie fizica
    private boolean apt; //True sau False
    private String scop;
    AdeverintaMedicala(){
        super();
        this.scop = "";
        this.apt = false;
    }
    AdeverintaMedicala(Pacient pacient, String eliberatDe, String eliberatLa, boolean apt, String scop){
        super(pacient, eliberatDe, eliberatLa);
        this.scop = scop;
        this.apt = apt;
    }
    public void setApt(boolean apt)
    {
        this.apt = apt;
    }
    public void setScop(String scop){
        this.scop = scop;
    }
    public boolean getApt(){
        return this.apt;
    }
    public String getScop(){
        return this.scop;
    }
}

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

    @Override
    public void afiseaza() {}

    public AdeverintaMedicala(Pacient pacient, String eliberatDe, String eliberatLa, boolean apt, String scop){
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

    @Override
    public String toString(){
       String x ="Pacientului:\n " + pacient.toString() + "\ni-a fost eliberata adeverinta medicala de catre: " + this.eliberatDe + " la data de: " + this.eliberatLa + "\ncu scopul: " + this.scop + ".\nAcesta ";
       if(this.apt == true)
           x += "apt pentru scopul mentionat anterior.";
       else
           x+= "nu este apt pentru scopul mentionat mai sus";
       return x;
    }
    public static String[] objectToString(AdeverintaMedicala am){
        return new String[]{Integer.toString(am.id), am.pacient.nume, am.pacient.prenume, am.pacient.dataNasterii, Integer.toString(am.pacient.varsta),
        am.pacient.gen, am.eliberatDe, am.eliberatLa, am.scop,  Boolean.toString(am.apt)};
    }
    public static String[] returnHeader(){
        String[] header;
        header = new String[]{"ID","nume","prenume","dataNasterii","varsta","gen","eliberatDe","eliberatLa","scop","apt"};
        return header;
    }

}

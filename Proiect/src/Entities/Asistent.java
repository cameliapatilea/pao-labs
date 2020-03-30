package Entities;

public class Asistent extends Persoana{
    private String specializare;
    private double oraStart;
    private double oraEnd;
    private boolean ture;
    Asistent(){
        super();
        this.specializare = "";
        this.oraStart = 0;
        this.oraEnd = 0;
        this.ture = false;
    }
    Asistent(String nume, String prenume, String specializare){
        super(nume, prenume);
        this.specializare = specializare;
    }
    Asistent(String nume, String prenume, String dataNasterii, int varsta, String gen, String specializare, double oraStart, double oraEnd, boolean ture){
        super(nume, prenume, dataNasterii, varsta, gen);
        this.specializare = specializare;
        this.oraStart = oraStart;
        this.oraEnd = oraEnd;
        this.ture = ture;
    }
    public void setSpecializare(String specializare){
        this.specializare = specializare;
    }
    public void setOraStart(double oraStart){
        this.oraStart = oraStart;
    }
    public void setOraEnd(double oraEnd){
        this.oraEnd = oraEnd;
    }
    public void setTure(boolean ture){
        this.ture = ture;
    }
    public String getSpecializare(){
        return this.specializare;
    }
    public double getOraStart(){
        return this.oraStart;
    }
    public double getOraEnd(){
        return this.oraEnd;
    }
    public boolean getTure(){
        return this.ture;
    }
}

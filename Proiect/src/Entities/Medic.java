package Entities;

public class Medic extends Persoana {
    private String specializare;
    private double oraStart;
    private double oraEnd;
    private int codParafa; //conventie: codul de parafa nu incepe cu 0 si nu depaseste dimensiunea unui int
    Medic(){
        super();
        this.oraStart = 0;
        this.oraEnd = 0;
        this.specializare = "";
        this.codParafa = 0;

    }
    Medic(String nume, String prenume, String specializare){
        super(nume, prenume);
        this.specializare = specializare;
    }
    Medic(String nume, String prenume, String dataNasterii, int varsta, String gen, String specializare, double oraStart, double oraEnd, int codParafa){
        super(nume, prenume, dataNasterii, varsta, gen);
        this.specializare = specializare;
        this.oraStart = oraStart;
        this.oraEnd = oraEnd;
        this.codParafa = codParafa;
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
    public void setCodParafa(int codParafa){
        this.codParafa = codParafa;
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
    public int getCodParafa(){
        return this.codParafa;
    }
}

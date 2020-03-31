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

    @Override
    public void afiseaza() {

    }

    Medic(String nume, String prenume, String specializare){
        super(nume, prenume);
        this.specializare = specializare;
    }
   public Medic(String nume, String prenume, String dataNasterii, int varsta, String gen, String specializare, double oraStart, double oraEnd, int codParafa){
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

    @Override
    public String toString(){
        String x =  "Nume: " + this.nume + "\n" + "Prenume: " + this.prenume + "\n" + "Varsta: " + this.varsta + "\n" + "Data nasterii: " + this.dataNasterii + "\n" + "Gen: " + this.gen + "\n" + "Specializare: " +
                this.specializare + "\nInterval orar de lucru: " + this.oraStart + "-" + this.oraEnd + "\nCod parafa: " + this.codParafa;

        return  x;
    }
}

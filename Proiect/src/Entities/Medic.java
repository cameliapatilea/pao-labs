package Entities;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Medic extends Persoana {
    private String specializare;
    private double oraStart;
    private double oraEnd;
    private int codParafa; //conventie: codul de parafa nu incepe cu 0 si nu depaseste dimensiunea unui int
    public Medic(){
        super();
        this.oraStart = 0;
        this.oraEnd = 0;
        this.specializare = "";
        this.codParafa = 0;

    }

    @Override
    public void afiseaza() {

    }

    Medic(int ID, String nume, String prenume, String specializare){
        super(ID, nume, prenume);
        this.specializare = specializare;
    }
   public Medic(int ID ,String nume, String prenume, String dataNasterii, int varsta, String gen, String specializare, double oraStart, double oraEnd, int codParafa){
        super(ID, nume, prenume, dataNasterii, varsta, gen);
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
        String x = "ID: " + this.id + "\n" +  "Nume: " + this.nume + "\n" + "Prenume: " + this.prenume + "\n" + "Varsta: " + this.varsta + "\n" + "Data nasterii: " + this.dataNasterii + "\n" + "Gen: " + this.gen + "\n" + "Specializare: " +
                this.specializare + "\nInterval orar de lucru: " + this.oraStart + "-" + this.oraEnd + "\nCod parafa: " + this.codParafa;

        return  x;
    }

    public int compareTo(Medic m) {
        if(this.nume.compareTo(m.nume) == 0){
            if(this.prenume.compareTo(m.prenume) == 0)
            {
                if(this.varsta > m.varsta)
                    return 1;
                else if(this.varsta == m.varsta)
                    return 0;
                else return -1;
            }
            else if(this.prenume.compareTo(m.prenume) > 0)
                return 1;
            else return -1;
        }
        else if(this.nume.compareTo(m.nume) < 0)
            return -1;
        else return 1;
    }
    public static String[] objectToString(Medic m){
        return new String[]{Integer.toString(m.id), m.nume, m.prenume, m.dataNasterii, Integer.toString(m.varsta), m.gen, m.specializare, Double.toString(m.oraStart),
        Double.toString(m.oraEnd), Integer.toString(m.codParafa)};
    }
    public static String[] returnHeader(){
        String[] header;
        header = new String[]{"ID","nume","prenume","dataNasterii","varsta","gen","specializare","oraStart","oraEnd","codParafa"};
        return header;
    }
    public static Medic  getEntityFromList(List<String> p) {

        Medic medic = new Medic(Integer.parseInt(p.get(0)),
                p.get(1), p.get(2), p.get(3), Integer.parseInt(p.get(4)), p.get(5), p.get(6), Double.parseDouble(p.get(7)), Double.parseDouble(p.get(8)), Integer.parseInt(p.get(9)));

        return medic;
    }
    public static List<Medic> getListFromCSV(List<List<String>> matr){
        List<Medic> medici = new ArrayList<>();
        for(int i = 0; i < matr.size(); i++){
            Medic m = Medic.getEntityFromList(matr.get(i));
            medici.add(m);
        }
        return medici;
    }
    public static List<List<String>> listToCSV(List<Medic> medici){
        List<List<String>> matrCSV = new ArrayList<>();
        for(int i = 0;  i < medici.size(); i++)
        {
            String[] arr = medici.get(i).objectToString(medici.get(i));
            matrCSV.add(Arrays.asList(arr));
        }
        return matrCSV;
    }
}

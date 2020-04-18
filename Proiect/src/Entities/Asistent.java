package Entities;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Asistent extends Persoana{
    private String specializare;
    private double oraStart;
    private double oraEnd;
    private boolean ture;
    public Asistent(){
        super();
        this.specializare = "";
        this.oraStart = 0;
        this.oraEnd = 0;
        this.ture = false;
    }

    @Override
    public void afiseaza() {

    }

    public Asistent(int ID, String nume, String prenume, String specializare){
        super(ID,nume, prenume);
        this.specializare = specializare;
    }
    public Asistent(int ID, String nume, String prenume, String dataNasterii, int varsta, String gen, String specializare, double oraStart, double oraEnd, boolean ture){
        super(ID, nume, prenume, dataNasterii, varsta, gen);
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

    @Override
    public String toString(){
        String x ="ID: " + this.ID + "\n" + "Nume: " + this.nume + "\n" + "Prenume: " + this.prenume + "\n" + "Varsta: " + this.varsta + "\n" + "Data nasterii: " + this.dataNasterii + "\n" + "Gen: " + this.gen + "\n" + "Specializare: " +
                this.specializare + "\nInterval orar de lucru: " + this.oraStart + "-" + this.oraEnd + "\nLucreaza in ture: " + this.ture;

        return  x;
    }
    public int compareTo(Asistent asistent) {
        if(this.nume.compareTo(asistent.nume) == 0){
            if(this.prenume.compareTo(asistent.prenume) == 0)
            {
                if(this.varsta > asistent.varsta)
                    return 1;
                else if(this.varsta == asistent.varsta)
                    return 0;
                else return -1;
            }
            else if(this.prenume.compareTo(asistent.prenume) > 0)
                return 1;
            else return -1;
        }
        else if(this.nume.compareTo(asistent.nume) < 0)
            return -1;
        else return 1;
    }

    public static String[] objectToString(Asistent a){
        return new String[]{Integer.toString(a.ID), a.nume, a.prenume, a.dataNasterii, Integer.toString(a.varsta), a.gen, a.specializare, Double.toString(a.oraStart),
                Double.toString(a.oraEnd), Boolean.toString(a.ture)};
    }
    public static String[] returnHeader(){
        String[] header;
        header = new String[]{ "ID","nume","prenume","dataNasterii","varsta","gen","specializare","oraStart","oraEnd","ture"};
        return header;
    }
    public static Asistent getEntityFromList(List<String> p) {

        Asistent asistent = new Asistent(Integer.parseInt(p.get(0)),
                p.get(1), p.get(2), p.get(3), Integer.parseInt(p.get(4)), p.get(5), p.get(6), Double.parseDouble(p.get(7)), Double.parseDouble(p.get(8)), Boolean.parseBoolean(p.get(9)));

        return asistent;
    }
    public static List<Asistent> getListFromCSV(List<List<String>> matr){
        List<Asistent> asistenti = new ArrayList<>();
        for(int i = 0; i < matr.size(); i++){
            Asistent a = Asistent.getEntityFromList(matr.get(i));
            asistenti.add(a);
        }
        return asistenti;
    }
    public static List<List<String>> listToCSV(List<Asistent> asistenti){
        List<List<String>> matrCSV = new ArrayList<>();
        for(int i = 0;  i < asistenti.size(); i++)
        {
            String[] arr = asistenti.get(i).objectToString(asistenti.get(i));
            matrCSV.add(Arrays.asList(arr));
        }
        return matrCSV;
    }
}

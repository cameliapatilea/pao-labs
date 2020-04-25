package Entities;

import Services.Implementations.PacientService;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import Helpers.AuditService;

import static Helpers.AuditService.scriereCSVAudit;


public class Pacient extends Persoana implements Comparable<Pacient>{
    private List<String> afectiuni;
    public Pacient(){
        super();
        this.afectiuni = new ArrayList<String>();
    }

    @Override
    public void afiseaza() {

    }

    public Pacient(int ID, String nume, String prenume, String dataNasterii, int varsta, String gen, List<String> afectiuni){
        super(ID, nume,prenume, dataNasterii, varsta, gen);
        this.afectiuni = new ArrayList<String>();

        this.afectiuni.addAll(afectiuni);
        String comanda = "addAll";
        comanda += " ";
        comanda +=  new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());
        String[] arr = comanda.split("");


    }
    public void setAfectiuni(List<String> afectiuni){


        this.afectiuni.addAll(afectiuni);
    }
    public List<String> getAfectiuni(){

        return this.afectiuni;
    }

    @Override
    public int compareTo(Pacient pacient) {
        if(this.nume.compareTo(pacient.nume) == 0){
            if(this.prenume.compareTo(pacient.prenume) == 0)
            {
                if(this.varsta > pacient.varsta)
                    return 1;
                else if(this.varsta == pacient.varsta)
                    return 0;
                else return -1;
            }
            else if(this.prenume.compareTo(pacient.prenume) > 0)
                return 1;
            else return -1;
        }
        else if(this.nume.compareTo(pacient.nume) < 0)
            return -1;
        else return 1;
    }

    @Override
    public String toString(){
        String x = "ID: "+this.id+ "\n"+ "Nume: " + this.nume + "\n" + "Prenume: " + this.prenume + "\n" + "Varsta: " + this.varsta + "\n" + "Data nasterii: " + this.dataNasterii + "\n" + "Gen: " + this.gen + "\n" + "Afectiuni:";
        for(int i = 0; i < this.afectiuni.size(); i++)
            x+= " "+ afectiuni.get(i) ;
        return  x;
    }
    public static String[] objectToString(Pacient pacient){
        return new String[]{Integer.toString(pacient.id), pacient.getNume(), pacient.getPrenume(), pacient.getDataNasterii(),
                String.valueOf(pacient.getVarsta()), pacient.getGen(), String.join(" ", pacient.getAfectiuni())};
    }
    public static String[] returnHeader(){
        String[] header = new String[]{"ID","nume","prenume","dataNasterii","varsta","gen","afectiuni"};
        return header;
    }
    public static Pacient getEntityFromList(List<String> p) {
        List<String> afectiuni = new ArrayList<>(Arrays.asList(p.get(6)));
        Pacient pacient = new Pacient(Integer.parseInt(p.get(0)),
                p.get(1), p.get(2), p.get(3), Integer.parseInt(p.get(4)), p.get(5), afectiuni);

        return pacient;
    }
    public static List<Pacient> getListFromCSV(List<List<String>> matr){
        List<Pacient> pacienti = new ArrayList<>();
        for(int i = 0; i < matr.size(); i++){
            Pacient p = Pacient.getEntityFromList(matr.get(i));
            pacienti.add(p);
        }
        return pacienti;
    }
    public static List<List<String>> listToCSV(List<Pacient> pacienti){
        List<List<String>> matrCSV = new ArrayList<>();
        for(int i = 0;  i < pacienti.size(); i++)
        {
            String[] arr = pacienti.get(i).objectToString(pacienti.get(i));
            matrCSV.add(Arrays.asList(arr));
        }
        return matrCSV;
    }
}

package Entities;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TrimitereMedicala extends Document{
    private int valabilitate; // in numar de zile, valabil de la data emiterii
    private String scop; // consult, analize, recuperare medicala
    private String catre; //cardiologie, laborator analize
    public TrimitereMedicala(){
        super();
        this.valabilitate = 0;
        this.scop = "";
        this.catre = "";
    }

    @Override
    public void afiseaza() {

    }

    public TrimitereMedicala(Pacient pacient,int valabilitate, String scop, String catre, String eliberatDe, String eliberatLa){
        super(pacient, eliberatDe, eliberatLa);
        this.valabilitate = valabilitate;
        this.scop = scop;
        this.catre = catre;
    }
    public void setValabilitate(int valabilitate){
        this.valabilitate = valabilitate;
    }
    public void setScop(String scop){
        this.scop = scop;
    }
    public void setCatre(String catre){
        this.catre = catre;
    }
    public int getValabilitate(){
        return this.valabilitate;
    }
    public String getScop(){
        return this.scop;
    }
    public String getCatre(){
        return this.catre;
    }

    @Override
    public String toString(){
        String x = "Trimitere medicala eliberata pentru pacientul: \n" + pacient.toString()+
                "\n eliberata de " + this.eliberatDe + " la data de " + this.eliberatLa +
                "\nCu valabiliatate " + this.valabilitate + " zile" + "\n Pentru a servi la: " + this.scop
                + "\nCatre: " + this.catre;
        return x;
    }
    public static String[] objectToString(TrimitereMedicala tm){
        return new String[]{Integer.toString(tm.pacient.id), tm.pacient.nume, tm.pacient.prenume, tm.pacient.dataNasterii, Integer.toString(tm.pacient.varsta),
        tm.pacient.gen,String.join(" ", tm.pacient.getAfectiuni()), Integer.toString(tm.valabilitate), tm.scop, tm.catre, tm.eliberatDe, tm.eliberatLa};
    }
    public static String[] returnHeader(){
        String[] header;
        header = new String[]{"ID","nume","prenume","dataNasterii","varsta","gen","afectiuni", "valabilitate","scop","catre","eliberatDe","eliberatLa"};
        return header;
    }
    public static TrimitereMedicala getEntityFromList(List<String> p) {
        List<String> afectiuni = new ArrayList<>(Arrays.asList(p.get(6)));
        Pacient pacient = new Pacient(Integer.parseInt(p.get(0)),
                p.get(1), p.get(2), p.get(3), Integer.parseInt(p.get(4)), p.get(5), afectiuni);
        TrimitereMedicala tm = new TrimitereMedicala(pacient, Integer.parseInt(p.get(7)), p.get(8), p.get(9), p.get(10), p.get(11));

        return tm;
    }
    public static List<TrimitereMedicala> getListFromCSV(List<List<String>> matr){
        List<TrimitereMedicala> trimiteri = new ArrayList<>();
        for(int i = 0; i < matr.size(); i++){
            TrimitereMedicala tm = TrimitereMedicala.getEntityFromList(matr.get(i));
            trimiteri.add(tm);
        }
        return trimiteri;
    }
    public static List<List<String>> listToCSV(List<TrimitereMedicala> trimiteri){
        List<List<String>> matrCSV = new ArrayList<>();
        for(int i = 0;  i < trimiteri.size(); i++)
        {
            String[] arr = trimiteri.get(i).objectToString(trimiteri.get(i));
            matrCSV.add(Arrays.asList(arr));
        }
        return matrCSV;
    }
}

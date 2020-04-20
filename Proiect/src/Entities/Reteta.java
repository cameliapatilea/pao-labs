package Entities;

import java.security.Key;
import java.util.*;

public class Reteta extends Document {

    private Map<String, Integer> medicamente;
    //se vor retine elemente de tipe(cheie,valoare) - cheia este reprezentata de medicamentul in sine
    //valoarea- de cate ori pe zi se ia acel medicament - odata pe zi. 2 ori/zi maxim 3ori/zi
    public Reteta(){
        super();
        this.medicamente = new HashMap<String, Integer>();
    }

    @Override
    public void afiseaza() {

    }

     public Reteta(Pacient pacient, String eliberatDe, String eliberatLa, Map<String, Integer> medicamente){
        super(pacient, eliberatDe, eliberatLa);
        this.medicamente = new HashMap<String, Integer>();
        this.medicamente.putAll(medicamente);
    }
    public void setMedicamente(Map<String, Integer> medicamente)
    {   //this.medicamente.clear();
        this.medicamente.putAll(medicamente);
    }
    public Map<String, Integer> getMedicamente(){
        return this.medicamente;
    }

    @Override
    public String toString(){
        String x =  this.pacient.toString() + "\n" + "Eliberat de catre: " + this.eliberatDe + ", la data de: " + this.eliberatLa + "\n";

       for(Map.Entry<String,Integer> entry: this.medicamente.entrySet()){
           x += "Medicamentul " + entry.getKey() + " poate fi luat de " + entry.getValue() + " ori pe zi.\n";
       }
        return x;
    }
    public static String parseMapToString(Map<String, Integer> m) {
        String x = "";
        for(Map.Entry<String,Integer> entry:m.entrySet()){
            x += entry.getKey() + " ";
        }
        return x;
    }
    public static String[] objectToString(Reteta r){
        return new String[]{Integer.toString(r.pacient.id), r.pacient.nume, r.pacient.prenume, r.pacient.dataNasterii, Integer.toString(r.pacient.varsta),
                r.pacient.gen,String.join(" ", r.pacient.getAfectiuni()), r.eliberatDe, r.eliberatLa, r.parseMapToString(r.medicamente)};
    }
    public static String[] returnHeader(){
        String[] header;
        header = new String[]{"ID","nume","prenume","dataNasterii","varsta","gen","afectiuni","eliberatDe","eliberatLa","medicamente"};
        return header;
    }

    public static Reteta getEntityFromList(List<String> p) {
        List<String> afectiuni = new ArrayList<>(Arrays.asList(p.get(6)));
        List<String> medicamente = new ArrayList<>(Arrays.asList(p.get(9)));
        Map<String, Integer> listaMedicamente = new HashMap<String, Integer>();
        for(int i = 0; i < medicamente.size(); i++)
        {
         listaMedicamente.put(medicamente.get(i), i + 1);
        }
        Pacient pacient = new Pacient(Integer.parseInt(p.get(0)),
                p.get(1), p.get(2), p.get(3), Integer.parseInt(p.get(4)), p.get(5), afectiuni);
        Reteta reteta = new Reteta(pacient, p.get(7), p.get(8), listaMedicamente);

        return reteta;
    }
    public static List<Reteta> getListFromCSV(List<List<String>> matr){
        List<Reteta> retete = new ArrayList<>();
        for(int i = 0; i < matr.size(); i++){
            Reteta p = Reteta.getEntityFromList(matr.get(i));
            retete.add(p);
        }
        return retete;
    }
    public static List<List<String>> listToCSV(List<Reteta> retete){
        List<List<String>> matrCSV = new ArrayList<>();
        for(int i = 0;  i < retete.size(); i++)
        {
            String[] arr = retete.get(i).objectToString(retete.get(i));
            matrCSV.add(Arrays.asList(arr));
        }
        return matrCSV;
    }
}

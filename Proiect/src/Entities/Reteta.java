package Entities;

import java.security.Key;
import java.util.HashMap;
import java.util.Map;

public class Reteta extends Document {

    private Map<String, Integer> medicamente;
    //se vor retine elemente de tipe(cheie,valoare) - cheia este reprezentata de medicamentul in sine
    //valoarea- de cate ori pe zi se ia acel medicament - odata pe zi. 2 ori/zi maxim 3ori/zi
    Reteta(){
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
        String x = "Reteta este eliberata pentru pacientul " + this.pacient.toString() + "\n" + "De catre: " + this.eliberatDe + ", la data de: " + this.eliberatLa + "\n";

       for(Map.Entry<String,Integer> entry: this.medicamente.entrySet()){
           x += "Medicamentul " + entry.getKey() + " poate fi luat de " + entry.getValue() + " ori pe zi.\n";
       }
        return x;
    }
}

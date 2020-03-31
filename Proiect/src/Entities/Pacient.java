package Entities;

import java.util.ArrayList;
import java.util.List;

public class Pacient extends Persoana implements Comparable<Pacient>{
    private List<String> afectiuni;
    public Pacient(){
        super();
        this.afectiuni = new ArrayList<String>();
    }

    @Override
    public void afiseaza() {

    }

    public Pacient(String nume, String prenume, String dataNasterii, int varsta, String gen, List<String> afectiuni)
    {
        super(nume,prenume, dataNasterii, varsta, gen);
        this.afectiuni = new ArrayList<String>();
        /*for(int i = 0; i < afectiuni.size(); i++)
        {
            this.afectiuni.add(afectiuni.get(i));
        }*/
        this.afectiuni.addAll(afectiuni);
    }
    public void setAfectiuni(List<String> afectiuni){

        //this.afectiuni.clear();

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
        String x =  "Nume: " + this.nume + "\n" + "Prenume: " + this.prenume + "\n" + "Varsta: " + this.varsta + "\n" + "Data nasterii: " + this.dataNasterii + "\n" + "Gen: " + this.gen + "\n" + "Afectiuni:";
        for(int i = 0; i < this.afectiuni.size(); i++)
            x+= " "+ afectiuni.get(i) ;
        return  x;
    }
}

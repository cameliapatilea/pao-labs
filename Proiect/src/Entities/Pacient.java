package Entities;

import java.util.ArrayList;
import java.util.List;

public class Pacient extends Persoana {
    private List<String> afectiuni;
    Pacient(){
        super();
        this.afectiuni = new ArrayList<String>();
    }
    Pacient(String nume, String prenume, String dataNasterii, int varsta, String gen, List<String> afectiuni)
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
        this.afectiuni.addAll(afectiuni);
    }
    public List<String> getAfectiuni(){
        return this.afectiuni;
    }

}

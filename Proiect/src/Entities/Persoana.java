package Entities;

public abstract class Persoana extends GeneralEntity{

    protected String nume;
    protected String prenume;
    protected String dataNasterii;
    protected int varsta;
    protected String gen; // masculin sau feminin
    Persoana(){
        this.nume = "";
        this.prenume = "";
        this.dataNasterii = "";
        this.varsta = 0;
        this.gen = "";
    }
    Persoana(String nume, String prenume){
        this.nume = nume;
        this.prenume = prenume;
    }
    Persoana(String nume, String prenume, String dataNasterii, int varsta, String gen){
        this.nume = nume;
        this.prenume = prenume;
        this.dataNasterii = dataNasterii;
        this.varsta = varsta;
        this.gen = gen;
    }
    public void setNume(String nume){
        this.nume = nume;
    }
    public void setPrenume(String prenume){
        this.prenume = prenume;
    }
    public void setDataNasterii(String dataNasterii){
        this.dataNasterii = dataNasterii;
    }
    public void setVarsta(int varsta){
        this.varsta = varsta;
    }
    public void setGen(String gen){
        this.gen = gen;
    }
    public String getNume(){
        return this.nume;
    }
    public String getPrenume(){
        return this.prenume;
    }
    public String getDataNasterii(){
        return this.dataNasterii;
    }
    public int getVarsta(){
        return this.varsta;
    }
    public String getGen(){
        return this.gen;
    }
    public abstract void afiseaza();
}

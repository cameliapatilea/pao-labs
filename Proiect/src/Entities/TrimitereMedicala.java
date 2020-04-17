package Entities;

public class TrimitereMedicala extends Document{
    private int valabilitate; // in numar de zile, valabil de la data emiterii
    private String scop; // consult, analize, recuperare medicala
    private String catre; //cardiologie, laborator analize
    TrimitereMedicala(){
        super();
        this.valabilitate = 0;
        this.scop = "";
        this.catre = "";
    }

    @Override
    public void afiseaza() {

    }

    public TrimitereMedicala(Pacient pacient, String eliberatDe, String eliberatLa, int valabilitate, String scop, String catre){
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
        return new String[]{Integer.toString(tm.id), tm.pacient.nume, tm.pacient.prenume, tm.pacient.dataNasterii, Integer.toString(tm.pacient.varsta),
        tm.pacient.gen, Integer.toString(tm.valabilitate), tm.scop, tm.catre, tm.eliberatDe, tm.eliberatLa};
    }
    public static String returnHeader(){
        String header;
        header = "ID,nume,prenume,dataNasterii,varsta,gen,valabilitate,scop,catre,eliberatDe,eliberatLa";
        return header;
    }
}

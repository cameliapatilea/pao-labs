package Entities;

public class ConcediuMedical extends Document{
    private int nrZileConcediu; //nu se acorda mai mult de 15 zile de concediu
    private String dataFinal;
    ConcediuMedical(){
        super();
        this.nrZileConcediu = 0;
        this.dataFinal = "";
    }

    @Override
    public void afiseaza() {

    }

    public ConcediuMedical(Pacient pacient, String eliberatDe, String eliberatLa, int nrZileConcediu, String dataFinal){
        super(pacient, eliberatDe, eliberatLa);
        this.nrZileConcediu = nrZileConcediu;
        this.dataFinal = dataFinal;
    }
    public void setNrZileConcediu(int nrZileConcediu){
        this.nrZileConcediu = nrZileConcediu;
    }
    public void setDataFinal(String dataFinal){
        this.dataFinal = dataFinal;
    }
    public int getNrZileConcediu(){
        return this.nrZileConcediu;
    }
    public String getDataFinal(){
        return this.dataFinal;
    }

    @Override
    public String toString(){
        String x = "Concediu medical eliberat de " + this.eliberatDe  + " la data de " + this.eliberatLa+
                "pentru pacientul:\n" + pacient.toString() + "\n" +
                "Au fost acordate " + this.nrZileConcediu + " zile de concediu medical" +
                "\n Cu valabilitate pana la data de " + this.dataFinal;
        return x;
    }
}

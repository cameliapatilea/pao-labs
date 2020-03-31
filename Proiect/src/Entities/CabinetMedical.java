package Entities;

import java.sql.ClientInfoStatus;
import java.util.ArrayList;
import java.util.List;

public class CabinetMedical {
    private List<Pacient> listaPacienti;
    private List<Medic> listaMedici;
    private List<Asistent> listaAsistenti;
    private String adresaCabinet;
    private int oraInceput;
    private int oraStop;

    CabinetMedical(){
        listaMedici = new ArrayList<Medic>();
        listaAsistenti = new ArrayList<Asistent>();
        listaPacienti  = new ArrayList<Pacient>();
        adresaCabinet = "";
        oraInceput = 0;
        oraStop = 0;

    }
    CabinetMedical(List<Pacient> listaPacienti, List<Asistent> listaAsistenti, List<Medic> listaMedici, String adresaCabinet, int oraInceput, int oraStop)
    {
        this.listaPacienti.addAll(listaPacienti);
        this.listaMedici.addAll(listaMedici);
        this.listaAsistenti.addAll(listaAsistenti);
        this.adresaCabinet = adresaCabinet;
        this.oraInceput = oraInceput;
        this.oraStop = oraStop;
    }
    public List<Pacient> getListPacienti()
    {
        return this.listaPacienti;
    }
    public List<Medic> getListaMedici()
    {
        return this.listaMedici;
    }
    public List<Asistent> getListAsistenti()
    {
        return this.listaAsistenti;
    }
    public String getAdresa(){return this.adresaCabinet;}
    public int getOraInceput(){return this.oraInceput;}
    public int getOraStop(){return this.oraStop;}



    public void setListPacienti(List<Pacient> listaPacienti)
    {
        this.listaPacienti.addAll(listaPacienti);
    }
    public void setListaMedici(List<Medic> listaMedici)
    {
       this.listaMedici.addAll(listaMedici);
    }
    public void setListAsistenti(List<Asistent> listaAsistenti)
    {
        this.listaAsistenti.addAll(listaAsistenti);
    }
    public void setAdresa(String adresaCabinet){this.adresaCabinet = adresaCabinet;}
    public void setOraInceput(int oraInceput){this.oraInceput = oraInceput;}
    public void setOraStop(int oraStop){this.oraStop = oraStop;}
}

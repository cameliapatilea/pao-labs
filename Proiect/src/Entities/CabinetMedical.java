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
    public List<Pacient> getListPacienti(List<Pacient> listaPacienti)
    {
        return this.listaPacienti;
    }
    public List<Medic> getListaMedici(List<Medic> listaMedici)
    {
        return this.listaMedici;
    }
    public List<Asistent> getListAsistenti(List<Asistent> listaAsistenti)
    {
        return this.listaAsistenti;
    }
}

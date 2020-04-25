package Services.Implementations;

import Entities.Pacient;
import Entities.TrimitereMedicala;
import Helpers.AuditService;
import Services.Interfaces.TrimitereMedicalaInterface;

import java.text.SimpleDateFormat;
import java.util.List;

public class TrimitereMedicalaService implements TrimitereMedicalaInterface {
    @Override
    public void citesteScrieAudit(String comanda, String timp) {
        List<String> matrice = AuditService.citireCSVAudit("src/excel/audit.csv");
        comanda += " " + timp;
        matrice.add(comanda);
        AuditService.scriereCSVAudit("src/excel/audit.csv", new String[]{"Comanda","Data", "Ora"}, matrice);
    }

    @Override
    public TrimitereMedicala creareTrimitere(Pacient pacient, String eliberatDe, String eliberatLa, int valabilitate, String scop, String catre) {
        String timeStamp = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new java.util.Date());
        citesteScrieAudit("creareTrimitere", timeStamp);
        TrimitereMedicala tm =  new TrimitereMedicala(pacient,  valabilitate, scop, catre, eliberatDe, eliberatLa);
        return tm;
    }

    @Override
    public void afiseazaTrimiteri(List<TrimitereMedicala> trimiteri) {
        String timeStamp = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new java.util.Date());
        citesteScrieAudit("afiseazaTrimiteri", timeStamp);
        for(int i = 0; i < trimiteri.size(); i++)
        {
            System.out.println("Trimiteri medicale: ");
            System.out.println(trimiteri.get(i).toString());
            System.out.println("==================================");
        }
    }

    @Override
    public TrimitereMedicala getFromListById(List<TrimitereMedicala> lista, int id) {
        String timeStamp = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new java.util.Date());
        citesteScrieAudit("getFromListById", timeStamp);
        for(int i = 0; i < lista.size(); i++)
            if(lista.get(i).getId() == id)
                return lista.get(i);

        return null;
    }

    @Override
    public List<TrimitereMedicala> getAllTrimiteri(List<TrimitereMedicala> lista)
    {
        String timeStamp = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new java.util.Date());
        citesteScrieAudit("getFromListById", timeStamp);
        return lista;
    }

    @Override
    public List<TrimitereMedicala> updateScop(int id, List<TrimitereMedicala> trimiteri, String scop) {
        String timeStamp = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new java.util.Date());
        citesteScrieAudit("updateScop", timeStamp);
        TrimitereMedicala tm = new TrimitereMedicala();
        for(int i = 0; i <  trimiteri.size(); i++)
        {
            if(trimiteri.get(i).getPacient().getId() == id)
            {
                trimiteri.get(i).setScop(scop);
            }
            //tm = trimiteri.get(i);
        }

        return trimiteri;
    }

    @Override
    public List<TrimitereMedicala> updateValabilitate(int id, List<TrimitereMedicala> trimiteri, int valabilitate) {
        String timeStamp = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new java.util.Date());
        citesteScrieAudit("updateValabilitate", timeStamp);
        for(int i = 0; i <  trimiteri.size(); i++)
        {
            if(trimiteri.get(i).getPacient().getId() == id)
            {
                trimiteri.get(i).setValabilitate(valabilitate);
            }

        }

        return trimiteri;
    }

    @Override
    public String obtineScop(int id, List<TrimitereMedicala> trimiteri) {
        String timeStamp = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new java.util.Date());
        citesteScrieAudit("obtineScop", timeStamp);
        String scop = "";
        for(int i = 0; i < trimiteri.size(); i++)
        {
            if(trimiteri.get(i).getPacient().getId() == id)
            {
                scop = trimiteri.get(i).getScop();
            }
        }
        return scop;
    }


}

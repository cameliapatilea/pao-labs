package Services.Implementations;

import Entities.AdeverintaMedicala;
import Entities.Pacient;
import Helpers.AuditService;
import Services.Interfaces.AdeverintaMedicalaInterface;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class AdeverintaMedicalaService implements AdeverintaMedicalaInterface {
    @Override
    public void citesteScrieAudit(String comanda, String timp) {
        List<String> matrice = AuditService.citireCSVAudit("src/excel/audit.csv");
        comanda += " " + timp;
        matrice.add(comanda);
        AuditService.scriereCSVAudit("src/excel/audit.csv", new String[]{"Comanda","Data", "Ora"}, matrice);
    }

    @Override
    public AdeverintaMedicala getFromListById(List<AdeverintaMedicala> lista, int id) {
        String timeStamp = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new java.util.Date());
        citesteScrieAudit("getFromListById", timeStamp);
        for(int i = 0; i < lista.size(); i++)
            if(lista.get(i).getId() == id)
                return lista.get(i);

        return null;
    }

    @Override
    public void afiseazaAdeverinte(List<AdeverintaMedicala> adeverinte) {
        String timeStamp = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new java.util.Date());
        citesteScrieAudit("afiseazaAdeverinte", timeStamp);
        for(int i = 0; i < adeverinte.size(); i++)
        {
            System.out.println("Adeverinte medicale: ");
            System.out.println(adeverinte.get(i).toString());
            System.out.println("==================================");
        }
    }

    @Override
    public AdeverintaMedicala creareAdeverinta(Pacient pacient, String eliberatDe, String eliberatLa, boolean apt, String scop) {
        String timeStamp = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new java.util.Date());
        citesteScrieAudit("creareAdeverinte", timeStamp);
       AdeverintaMedicala am = new AdeverintaMedicala(pacient, eliberatDe, eliberatLa, apt, scop);
       return am;
    }

    @Override
    public AdeverintaMedicala updateScop(AdeverintaMedicala am, String scop) {
        String timeStamp = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new java.util.Date());
        citesteScrieAudit("updateScop", timeStamp);
        am.setScop(scop);
        return am;
    }

    @Override
    public String getAdeverinteEliberateLaDataX(List<AdeverintaMedicala> lista, String data) {
        String timeStamp = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new java.util.Date());
        citesteScrieAudit("getAdeverinteEliberateLaDataX", timeStamp);
        List<AdeverintaMedicala> newList = new ArrayList<>();
        for(int i = 0; i <  lista.size(); i++)
        {
            if(lista.get(i).getEliberatLa().compareTo(data) == 0)
                newList.add(lista.get(i));
        }
        String x = "Adeverintele medicale eliberate la data " + data + "sunt";
        for(int i = 0; i < newList.size(); i++)
        {
            x+=newList.get(i).toString() + "\n";
        }
        return x;
    }
}

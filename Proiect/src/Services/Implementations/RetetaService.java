package Services.Implementations;

import Entities.Pacient;
import Entities.Reteta;
import Helpers.AuditService;
import Services.Interfaces.RetetaInterface;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

public class RetetaService implements RetetaInterface {
    @Override
    public void citesteScrieAudit(String comanda, String timp) {
        List<String> matrice = AuditService.citireCSVAudit("src/excel/audit.csv");
        comanda += " " + timp;
        matrice.add(comanda);
        AuditService.scriereCSVAudit("src/excel/audit.csv", new String[]{"Comanda","Data", "Ora"}, matrice);
    }

    @Override
    public Reteta getFromListById(List<Reteta> lista, int id) {
        String timeStamp = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new java.util.Date());
        citesteScrieAudit("getFromListById", timeStamp);
        for(int i = 0; i < lista.size(); i++)
            if(lista.get(i).getId() == id)
                return lista.get(i);

        return null;
    }

    @Override
    public void afiseazaRetete(List<Reteta> retete) {
        String timeStamp = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new java.util.Date());
        citesteScrieAudit("afiseazaRetete", timeStamp);
        for(int i = 0; i < retete.size(); i++)
        {
            System.out.println("Retete: ");
            System.out.println(retete.get(i).toString());
            System.out.println("==================================");
        }
    }

    @Override
    public Reteta creareReteta(Pacient pacient, String eliberatDe, String eliberatLa, Map<String, Integer> medicamente) {
        String timeStamp = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new java.util.Date());
        citesteScrieAudit("creareReteta", timeStamp);
       Reteta r = new Reteta( pacient,eliberatDe,  eliberatLa,medicamente);
       return r;
    }

    @Override
    public Map<String, Integer> getMedicamente(Reteta r)
    {
        String timeStamp = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new java.util.Date());
        citesteScrieAudit("getMedicamente", timeStamp);
        return r.getMedicamente();
    }

    @Override
    public Reteta adaugaMedicament(Reteta r,String medicament, int oriZi) {
        String timeStamp = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new java.util.Date());
        citesteScrieAudit("adaugaMedicament", timeStamp);
        r.getMedicamente().put(medicament, oriZi);
        return r;
    }



}

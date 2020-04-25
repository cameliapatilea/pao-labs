package Services.Implementations;

import Entities.ConcediuMedical;
import Entities.Pacient;
import Helpers.AuditService;
import Services.Interfaces.ConcediuMedicalInterface;
import com.sun.java.browser.plugin2.liveconnect.v1.ConversionDelegate;

import java.text.SimpleDateFormat;
import java.util.List;

public class ConcediuMedicalService implements ConcediuMedicalInterface {
    @Override
    public void citesteScrieAudit(String comanda, String timp) {
        List<String> matrice = AuditService.citireCSVAudit("src/excel/audit.csv");
        comanda += " " + timp;
        matrice.add(comanda);
        AuditService.scriereCSVAudit("src/excel/audit.csv", new String[]{"Comanda","Data", "Ora"}, matrice);
    }

    @Override
    public ConcediuMedical getFromListById(List<ConcediuMedical> lista, int id) {
        String timeStamp = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new java.util.Date());
        citesteScrieAudit("getFromListById", timeStamp);
        for(int i = 0; i < lista.size(); i++)
            if(lista.get(i).getId() == id)
                return lista.get(i);

        return null;
    }

    @Override
    public void afiseazaConcedii(List<ConcediuMedical> concedii) {
        String timeStamp = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new java.util.Date());
        citesteScrieAudit("afiseazConcedii", timeStamp);
        for(int i = 0; i < concedii.size(); i++)
        {
            System.out.println("Concedii medicale: ");
            System.out.println(concedii.get(i).toString());
            System.out.println("==================================");
        }
    }

    @Override
    public ConcediuMedical creareCerereConcediu(Pacient pacient, String eliberatDe, String eliberatLa, int nrZileConcediu, String dataFinal) {
        String timeStamp = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new java.util.Date());
        citesteScrieAudit("creareCerereConcediu", timeStamp);
        ConcediuMedical cm = new ConcediuMedical(pacient, eliberatDe, eliberatLa, nrZileConcediu, dataFinal);
        return cm;
    }

    @Override
    public ConcediuMedical updateNrZile(ConcediuMedical cm, int nrZile, String dataFinal) {
        String timeStamp = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new java.util.Date());
        citesteScrieAudit("updateNrZile", timeStamp);
        cm.setNrZileConcediu(nrZile);
        cm.setDataFinal(dataFinal);
        return cm;
    }

    @Override
    public List<ConcediuMedical> stergereConcediu(List<ConcediuMedical> lista, int indx) {
        String timeStamp = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new java.util.Date());
        citesteScrieAudit("stergereConcediu", timeStamp);
       for(int i = 0; i < lista.size(); i++)
           if(i == indx){
               lista.remove(i);
           }
       return lista;
    }
}

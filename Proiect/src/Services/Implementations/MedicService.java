package Services.Implementations;

import Entities.Medic;
import Helpers.AuditService;
import Services.Interfaces.MedicInterface;

import java.text.SimpleDateFormat;
import java.util.List;

public class MedicService implements MedicInterface {
    @Override
    public void citesteScrieAudit(String comanda, String timp) {
        List<String> matrice = AuditService.citireCSVAudit("src/excel/audit.csv");
        comanda += " " + timp;
        matrice.add(comanda);
        AuditService.scriereCSVAudit("src/excel/audit.csv", new String[]{"Comanda","Data", "Ora"}, matrice);
    }

    @Override
    public Medic getFromListById(List<Medic> lista, int id) {
        String timeStamp = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new java.util.Date());
        citesteScrieAudit("getFromListById", timeStamp);
        for(int i = 0; i < lista.size(); i++)
            if(lista.get(i).getId() == id)
                return lista.get(i);

        return null;
    }

    @Override
    public void afiseazaMedici(List<Medic> medici) {
        String timeStamp = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new java.util.Date());
        citesteScrieAudit("afiseazaMedici", timeStamp);
        for(int i = 0; i < medici.size(); i++)
        {
            System.out.println("Pacient: ");
            System.out.println(medici.get(i).toString());
            System.out.println("==================================");
        }
    }

    @Override
    public Medic creareMedic(int id, String nume, String prenume, String dataNasterii, int varsta, String gen, String specializare, double oraStart, double oraEnd, int codParafa) {
        String timeStamp = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new java.util.Date());
        citesteScrieAudit("creareMedic", timeStamp);
        Medic m = new Medic(id, nume, prenume, dataNasterii, varsta, gen, specializare, oraStart, oraEnd, codParafa);

        return m;
    }

    @Override
    public List<Medic> updateSpecialiare(int id,List<Medic> medici, String specializare) {
        String timeStamp = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new java.util.Date());
        citesteScrieAudit("updateSpecializare", timeStamp);
        for(int i = 0; i < medici.size(); i++)

        {
            if(medici.get(i).getID() == id)
                medici.get(i).setSpecializare(specializare);
        }

        return medici;
    }

    @Override
    public List<Medic> updateVarsta(int id, List<Medic> medici, int varsta, String dataNasterii) {
        String timeStamp = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new java.util.Date());
        citesteScrieAudit("updateVarsta", timeStamp);
        for(int i = 0; i < medici.size(); i++)

        {
            if(medici.get(i).getID() == id)
            {
                medici.get(i).setVarsta(varsta);
                medici.get(i).setDataNasterii(dataNasterii);
            }
        }

        return medici;
    }


    @Override
    public Medic updateNume(Medic m, String nume) {
        String timeStamp = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new java.util.Date());
        citesteScrieAudit("updateNume", timeStamp);
        m.setNume(nume);
        return m;
    }

    @Override
    public String afiseazaSpecializare(String nume, String prenume, List<Medic> medici) {
        String timeStamp = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new java.util.Date());
        citesteScrieAudit("afiseazaSpecializare", timeStamp);
        String x = "";

        for(int i = 0; i <  medici.size(); i++)
            if(medici.get(i).getNume().compareTo(nume) == 0 && medici.get(i).getPrenume().compareTo(prenume) == 0)
                x+= medici.get(i).getSpecializare();
         return x;
    }

    @Override
    public String afiseazaIntervalOrar(String nume, String prenume, List<Medic> medici) {
        String timeStamp = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new java.util.Date());
        citesteScrieAudit("afiseazaIntervalOrar", timeStamp);
        String x = "";
        String y = "";

        for(int i = 0; i <  medici.size(); i++)
            if(medici.get(i).getNume().compareTo(nume) == 0 && medici.get(i).getPrenume().compareTo(prenume) == 0)
            {
                x+= medici.get(i).getOraStart();
                y+= medici.get(i).getOraEnd();
                break;
            }
        return  x + "-" + y;
    }
}

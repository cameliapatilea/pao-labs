package Services.Implementations;

import Entities.Asistent;
import Helpers.AuditService;
import Services.Interfaces.AsistentInterface;

import java.text.SimpleDateFormat;
import java.util.List;

public class AsistentService implements AsistentInterface {
    @Override
    public void citesteScrieAudit(String comanda, String timp) {
        List<String> matrice = AuditService.citireCSVAudit("src/excel/audit.csv");
        comanda += " " + timp;
        matrice.add(comanda);
        AuditService.scriereCSVAudit("src/excel/audit.csv", new String[]{"Comanda","Data", "Ora"}, matrice);
    }

    @Override
    public Asistent getFromListById(List<Asistent> lista, int id) {
        String timeStamp = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new java.util.Date());
        citesteScrieAudit("getFromListById", timeStamp);
        for(int i = 0; i < lista.size(); i++)
            if(lista.get(i).getId() == id)
                return lista.get(i);

        return null;
    }

    @Override
    public void afiseazaAsistenti(List<Asistent> asistenti) {
        String timeStamp = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new java.util.Date());
        citesteScrieAudit("afiseazaAsistenti", timeStamp);
        for(int i = 0; i < asistenti.size(); i++)
        {
            System.out.println("Asistenti: ");
            System.out.println(asistenti.get(i).toString());
            System.out.println("==================================");
        }
    }

    @Override
    public Asistent creareAsistent(int id, String nume, String prenume, String dataNasterii, int varsta, String gen, String specializare, double oraStart, double oraEnd, boolean ture) {
        String timeStamp = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new java.util.Date());
        citesteScrieAudit("creareAsistent", timeStamp);
        Asistent a = new Asistent(id, nume, prenume, dataNasterii, varsta, gen, specializare, oraStart, oraEnd, ture);
       return a;
    }

    @Override
    public Asistent updateVarsta(Asistent a, int varsta) {
        String timeStamp = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new java.util.Date());
        citesteScrieAudit("updateVarsta", timeStamp);
        a.setVarsta(varsta);
        return a;
    }

    @Override
    public List<Asistent> updateSpecializare(int id, List<Asistent> asistenti,  String specializare) {
        String timeStamp = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new java.util.Date());
        citesteScrieAudit("updateSpecializare", timeStamp);
        for(int i = 0; i < asistenti.size(); i++)

        {
            if(asistenti.get(i).getID() == id)
                asistenti.get(i).setSpecializare(specializare);
        }

        return asistenti;
    }

    @Override
    public Asistent updateTure(Asistent a, boolean ture) {
        String timeStamp = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new java.util.Date());
        citesteScrieAudit("updateTure", timeStamp);
        a.setTure(ture);
        return a;
    }

    @Override
    public String afiseazaProgram(String nume, String prenume, List<Asistent> asistenti) {
        String timeStamp = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new java.util.Date());
        citesteScrieAudit("afiseaazaProgram", timeStamp);
        String rasp = "";
        for(int i = 0; i <  asistenti.size(); i++)
            if (asistenti.get(i).getNume().compareTo(nume) == 0 && asistenti.get(i).getPrenume().compareTo(prenume) == 0)
                rasp =  asistenti.get(i).getOraStart() + "-" + asistenti.get(i).getOraEnd();

            return rasp;
    }


    @Override
    public String afiseazaSpecializare(Asistent a)
    {
        String timeStamp = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new java.util.Date());
        citesteScrieAudit("afiseazaSpecializare", timeStamp);
        return a.getSpecializare();
    }

    @Override
    public List<Asistent> adaugaAsistentInLista(Asistent a, List<Asistent> lista) {
        String timeStamp = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new java.util.Date());
        citesteScrieAudit("adaugaAsistentInLista", timeStamp);
       lista.add(a);
       return lista;
    }

    @Override
    public Asistent getAsistentBySpecializare(List<Asistent> lista, String specializare) {
        String timeStamp = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new java.util.Date());
        citesteScrieAudit("getAsistentBySpecializare", timeStamp);
        Asistent x = new Asistent();
        for(int i = 0; i <  lista.size(); i++)
            if(lista.get(i).getSpecializare().compareTo(specializare) == 0){
               x = lista.get(i);
                break;
            }
        return x;
    }
}

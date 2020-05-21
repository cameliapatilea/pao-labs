package Services.Implementations;

import Entities.Asistent;
import Entities.Medic;
import Helpers.AuditService;
import Services.Interfaces.AsistentInterface;
import com.sun.org.apache.xpath.internal.operations.Bool;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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

    @Override
    public List<Asistent> getAllFromDb(Connection connObj) {
        String timeStamp = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new java.util.Date());
        citesteScrieAudit("getAllFromDbAsistenti", timeStamp);
        List<Asistent> listaAsistenti= new ArrayList<>() ;
        try{

            Statement stmt = connObj.createStatement();

            String sql = "SELECT Id, LastName, FirstName,DataNasterii,Varsta, Gen, Specializare, OraStart,OraEnd, Ture FROM Asistenti";
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()){
                //Retrieve by column name
                int id  = rs.getInt("Id");
                String last = rs.getString("LastName");
                String first = rs.getString("FirstName");
                String data = rs.getString("DataNasterii");
                int varsta = rs.getInt("Varsta");
                String gen = rs.getString("Gen");
                String specializare = rs.getString("Specializare");
                Double oraStart = rs.getDouble("OraStart");
                Double oraEnd = rs.getDouble("OraEnd");
                int ture = rs.getInt("Ture");
                boolean tura;
                if(ture ==1)
                    tura = true;
                else tura = false;

                Asistent a = creareAsistent(id, last, first, data, varsta, gen, specializare, oraStart, oraEnd, tura);
                listaAsistenti.add(a);
            }
            rs.close();

        }
        catch(SQLException se){

        }
        return listaAsistenti;
    }

    @Override
    public void adaugaAsistentcDb(Connection connObj, Asistent m) {

    }

    @Override
    public void updateVarstaAsistentDb(Connection connObj, int id, int varsta, String data) {

    }

    @Override
    public void updateSpecializareAsistentDb(Connection connObj, int id, String specializare) {

    }

    @Override
    public void getOrarAsistentDb(Connection connObj, int id) {

    }

    @Override
    public Asistent getAsistentBySpecializareDb(Connection connObj, int specializare) {
        return null;
    }

    @Override
    public void deleteAsistentFromDb(Connection connObj, int id) {

    }
}

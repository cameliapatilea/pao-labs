package Services.Implementations;

import Entities.Asistent;
import Entities.Pacient;
import Entities.Reteta;
import Helpers.AuditService;
import Services.Interfaces.RetetaInterface;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.*;

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

    @Override
    public List<Reteta> getAllFromDb(Connection connObj) {
        String timeStamp = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new java.util.Date());
        citesteScrieAudit("getAllReteteFromDb", timeStamp);
        List<Reteta> listaRetete = new ArrayList<>();
        try {
            Statement stmt = connObj.createStatement();

            String sql = "SELECT Id, LastName, FirstName,DataNasterii,Varsta, Gen, Afectiuni, EliberatDe, EliberatLa, Medicamente FROM Retete";
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                //Retrieve by column name
                int id = rs.getInt("Id");
                String last = rs.getString("LastName");
                String first = rs.getString("FirstName");
                String data = rs.getString("DataNasterii");
                int varsta = rs.getInt("Varsta");
                String gen = rs.getString("Gen");
                String afec = rs.getString("Afectiuni");
                List<String> afectiuni = Arrays.asList(afec.split(" "));
                String eliberatDe = rs.getString("EliberatDe");
                String eliberatLa = rs.getString("EliberatLa");
                Pacient p = new Pacient(id, last, first, data, varsta, gen, afectiuni);
                String medic = rs.getString("Medicamente");
                List<String> stringuriMedicamente = Arrays.asList(medic.split(" "));
                Map< String, Integer> medicamente = new HashMap<>();
                for(int i = 0; i < stringuriMedicamente.size(); i++)
                    medicamente.put(stringuriMedicamente.get(i), i+1);
                //int idPacient = rs.getInt("IdPacient");
                Reteta r = creareReteta( p,eliberatDe, eliberatLa, medicamente);
                listaRetete.add(r);
            }
        }

        catch(SQLException se){
            se.printStackTrace();
        }
        return listaRetete;
    }

    @Override
    public void createRetetaForDb(Connection connObj, Reteta r) {
        String timeStamp = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new java.util.Date());
        citesteScrieAudit("createRetetaForDb", timeStamp);
        try{
            Statement stmt = connObj.createStatement();


            String sql  = "INSERT INTO Retete " + "VALUES(" + r.getId() + ",'" + r.getPacient().getPrenume()+ "','" + r.getPacient().getPrenume() +
                    "','" +  r.getPacient().getDataNasterii()+ "'," +  r.getPacient().getVarsta() + ",'" +  r.getPacient().getGen() + "','" +
                    r.getPacient().getAfectiuni().toString() + "','" +r.getEliberatDe() + "'," +r.getEliberatLa() + ",'" + r.parseMapToString(r.getMedicamente())+" '," + r.getPacient().getID()+ ")";
            stmt.executeUpdate(sql);
        }
        catch(SQLException se){
            se.printStackTrace();
        }

    }

    @Override
    public void deleteRetetaFromDb(Connection connObj, int id) {
        String timeStamp = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new java.util.Date());
        citesteScrieAudit("deleteRetetaFromDb", timeStamp);
        try{
            Statement  stmt = connObj.createStatement();
            String sql = "DELETE FROM Retete " +
                    "WHERE id = " + id;
            stmt.executeUpdate(sql);
            System.out.println("Delete completed");
        }
        catch(SQLException se){
            se.printStackTrace();
        }
    }

    @Override
    public void modificaEliberareRetetaDb(Connection connObj, int id, String data) {
        String timeStamp = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new java.util.Date());
        citesteScrieAudit("modificaEliberareRetetaDb", timeStamp);

        try{
            Statement stmt = connObj.createStatement();
            String sql = "UPDATE Retete " +
                    "SET EliberatLa ='" + data + "' where Id=" + id;
            stmt.executeUpdate(sql);
        }
        catch(SQLException se){
            se.printStackTrace();

        }
    }


}

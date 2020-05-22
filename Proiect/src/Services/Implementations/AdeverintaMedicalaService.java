package Services.Implementations;

import Entities.AdeverintaMedicala;
import Entities.ConcediuMedical;
import Entities.Pacient;
import Helpers.AuditService;
import Services.Interfaces.AdeverintaMedicalaInterface;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
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

    @Override
    public List<AdeverintaMedicala> getAdeverinteFromDb(Connection connObj) {
        String timeStamp = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new java.util.Date());
        citesteScrieAudit("getAdeverinteFromDb", timeStamp);
        List<AdeverintaMedicala> listaAdeverinte = new ArrayList<>();
        try {
            Statement stmt = connObj.createStatement();

            String sql = "SELECT Id, LastName, FirstName,DataNasterii,Varsta, Gen, Afectiuni,EliberatDe, EliberatLa,Scop,Apt, IdPacient  FROM AdeverinteMedicale";
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                //Retrieve by column name
                int id = rs.getInt("Id");
                String last = rs.getString("LastName");
                String first = rs.getString("FirstName");
                String data = rs.getString("DataNasterii");
                int varsta = rs.getInt("Varsta");
                String gen = rs.getString("Gen");


                String eliberatDe = rs.getString("EliberatDe");
                String eliberatLa = rs.getString("EliberatLa");
                String scop = rs.getString("Scop");
                int apt = rs.getInt("Apt");
                boolean APT;
                if (apt == 1)
                    APT  =true;
                else APT = false;

                String afec = rs.getString("Afectiuni");
                List<String> afectiuni = Arrays.asList(afec.split(" "));
                Pacient p = new Pacient(id, last, first, data, varsta, gen, afectiuni);
                AdeverintaMedicala am = creareAdeverinta(p, eliberatDe, eliberatLa, APT, scop);
               listaAdeverinte.add(am);
            }
        }

        catch(SQLException se){
            se.printStackTrace();
        }
        return listaAdeverinte;
    }

    @Override
    public void createAdeverintaDb(Connection connObj, AdeverintaMedicala am) {
        String timeStamp = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new java.util.Date());
        citesteScrieAudit("createAdeverintaDb", timeStamp);
        try{
            Statement stmt = connObj.createStatement();
            int APT;
            if(am.getApt() == true)
                APT = 1;
            else APT = 0;

            String sql  = "INSERT INTO AdeverinteMedicale " + "VALUES(" + am.getPacient().getID()+ ",'" + am.getPacient().getNume()+ "','" + am.getPacient().getPrenume() +
                    "','" +  am.getPacient().getDataNasterii()+ "'," +  am.getPacient().getVarsta() + ",'" +  am.getPacient().getGen() + "','"
                    + am.getEliberatDe() + "','" +am.getEliberatLa() + "','" +am.getScop()+ "'," + APT+ "," + am.getPacient().getID()+ ",'"+ am.getPacient().getAfectiuni().toString()+ "')";
            stmt.executeUpdate(sql);
        }
        catch(SQLException se){
            se.printStackTrace();
        }
    }

    @Override
    public void modificaAptDb(Connection connObj,int id, boolean apt) {
        String timeStamp = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new java.util.Date());
        citesteScrieAudit("modificaAptDb", timeStamp);

        try{
            int APT;
            if(apt == true)
                APT = 1;
            else APT =0;
            Statement stmt = connObj.createStatement();
            String sql = "UPDATE AdeverinteMedicale " +
                    "SET apt =" + APT + " where Id=" + id;
            stmt.executeUpdate(sql);

        }
        catch(SQLException se){
            se.printStackTrace();

        }
    }

    @Override
    public void deleteAdeverintaDb(Connection connObj, int id) {
        String timeStamp = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new java.util.Date());
        citesteScrieAudit("deleteAdeverintaDb", timeStamp);
        try{
            Statement  stmt = connObj.createStatement();
            String sql = "DELETE FROM AdeverinteMedicale " +
                    "WHERE id = " + id;
            stmt.executeUpdate(sql);
            System.out.println("Delete completed");
        }
        catch(SQLException se){
            se.printStackTrace();
        }
    }
}

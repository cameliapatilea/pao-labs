package Services.Implementations;

import Entities.Medic;
import Entities.Pacient;
import Entities.Reteta;
import Entities.TrimitereMedicala;
import Helpers.AuditService;
import Services.Interfaces.TrimitereMedicalaInterface;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.*;

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

    @Override
    public List<TrimitereMedicala> getAllTrimiteriFromDb(Connection connObj) {
        String timeStamp = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new java.util.Date());
        citesteScrieAudit("getAllTrimiteriFromDb", timeStamp);
        List<TrimitereMedicala> listaTrimiteri = new ArrayList<>();
        try {
            Statement stmt = connObj.createStatement();

            String sql = "SELECT Id, LastName, FirstName,DataNasterii,Varsta, Gen, Afectiuni,Valabilitate,Scop, Catre, EliberatDe, EliberatLa, IdPacient  FROM TrimiteriMedicale";
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

                Pacient p = new Pacient(id, last, first, data, varsta, gen, afectiuni);
                int valabilitate = rs.getInt("Valabilitate");
                String scop = rs.getString("Scop");
                String catre = rs.getString("Catre");
                String eliberatDe = rs.getString("EliberatDe");
                String eliberatLa = rs.getString("EliberatLa");
                TrimitereMedicala r = creareTrimitere( p,eliberatDe, eliberatLa,valabilitate, scop, catre);
                listaTrimiteri.add(r);
            }
        }

        catch(SQLException se){
            se.printStackTrace();
        }
        return listaTrimiteri;
    }

    @Override
    public void creareTrimitereDb(Connection connObj, TrimitereMedicala tm) {
        String timeStamp = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new java.util.Date());
        citesteScrieAudit("creareTrimitereDb", timeStamp);
        try{
            Statement stmt = connObj.createStatement();


            String sql  = "INSERT INTO TrimiteriMedicale " + "VALUES(" + tm.getPacient().getID()+ ",'" + tm.getPacient().getNume()+ "','" + tm.getPacient().getPrenume() +
                    "','" +  tm.getPacient().getDataNasterii()+ "'," +  tm.getPacient().getVarsta() + ",'" +  tm.getPacient().getGen() + "','" +
                    tm.getPacient().getAfectiuni().toString() + "'," +tm.getValabilitate() +",'" +tm.getScop() +"','" + tm.getCatre() +"','" + tm.getEliberatDe() + "','" +tm.getEliberatLa() + "'," +  + tm.getPacient().getID()+ ")";
            stmt.executeUpdate(sql);
        }
        catch(SQLException se){
            se.printStackTrace();
        }

    }

    @Override
    public void modificareScopDb(Connection connObj, int id, String scop) {
        String timeStamp = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new java.util.Date());
        citesteScrieAudit("modificareScopDb", timeStamp);

        try{
            Statement stmt = connObj.createStatement();
            String sql = "UPDATE TrimiteriMedicale " +
                    "SET scop ='" + scop + "' where Id=" + id;
            stmt.executeUpdate(sql);
        }
        catch(SQLException se){
            se.printStackTrace();

        }
    }

    @Override
    public void modificareValabilitateDb(Connection connObj, int id, int val) {
        String timeStamp = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new java.util.Date());
        citesteScrieAudit("modificareValabilitateDb", timeStamp);

        try{
            Statement stmt = connObj.createStatement();
            String sql = "UPDATE TrimiteriMedicale " +
                    "SET Valabilitate ='" + val + "' where Id=" + id;
            stmt.executeUpdate(sql);
        }
        catch(SQLException se){
            se.printStackTrace();

        }
    }

    @Override
    public void selectScopDb(Connection connObj, int id) {
        String timeStamp = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new java.util.Date());
        citesteScrieAudit("selectScopDb", timeStamp);
        List<TrimitereMedicala> listaTrimiteri= getAllTrimiteriFromDb(connObj);
        try{
            Statement stmt = connObj.createStatement();
            String sql = "SELECT LastName,FirstName,Scop FROM TrimiteriMedicale" +
                    " WHERE id =" + id;
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()){
                String nume = rs.getString("LastName");
                String prenume = rs.getString("FirstName");
                String scop = rs.getString("Scop");

               System.out.println("Trimiterea medicala pentru pacientul " + nume +" "+ prenume + " a fost emisa cu scopul "+scop);
            }
        }
        catch (SQLException se){
            se.printStackTrace();
        }
    }

    @Override
    public void deleteTrimitereMedicalaDb(Connection connObj, int id) {
        String timeStamp = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new java.util.Date());
        citesteScrieAudit("deleteTrimitereMedicalaDb", timeStamp);
        try{
            Statement  stmt = connObj.createStatement();
            String sql = "DELETE FROM TrimiteriMedicale " +
                    "WHERE id = " + id;
            stmt.executeUpdate(sql);
            System.out.println("Delete completed");
        }
        catch(SQLException se){
            se.printStackTrace();
        }
    }


}

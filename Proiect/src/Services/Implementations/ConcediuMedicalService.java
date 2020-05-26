package Services.Implementations;

import Entities.ConcediuMedical;
import Entities.Pacient;
import Entities.TrimitereMedicala;
import Helpers.AuditService;
import Services.Interfaces.ConcediuMedicalInterface;
import com.sun.java.browser.plugin2.liveconnect.v1.ConversionDelegate;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ConcediuMedicalService implements ConcediuMedicalInterface {
    private class RunnableAudit implements Runnable{
        private  String comanda;
        private  String timp;

        public RunnableAudit(String comanda, String timp){
            this.comanda = comanda;
            this.timp = timp;


        }
        public void run()
        {
            List<String> matrice = AuditService.citireCSVAudit("src/excel/audit.csv");

            Thread.currentThread().setName(comanda);
            String threadName = Thread.currentThread().getName();
            threadName += "Thread";

            comanda += " " + timp;
            comanda += " " + threadName;
            matrice.add(comanda);
            AuditService.scriereCSVAudit("src/excel/audit.csv", new String[]{"Comanda","Data", "Ora", "ThreadName"}, matrice);
        }
    }
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
        Runnable rr = new ConcediuMedicalService.RunnableAudit("getFromListById", timeStamp);
        Thread t = new Thread(rr);
        t.start();
        //citesteScrieAudit("getFromListById", timeStamp);
        for(int i = 0; i < lista.size(); i++)
            if(lista.get(i).getId() == id)
                return lista.get(i);

        return null;
    }

    @Override
    public void afiseazaConcedii(List<ConcediuMedical> concedii) {
        String timeStamp = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new java.util.Date());
       // citesteScrieAudit("afiseazConcedii", timeStamp);
        Runnable rr = new ConcediuMedicalService.RunnableAudit("afiseazaConcedii", timeStamp);
        Thread t = new Thread(rr);
        t.start();
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
        Runnable rr = new ConcediuMedicalService.RunnableAudit("creareCerereConcediu", timeStamp);
        Thread t = new Thread(rr);
        t.start();
        //citesteScrieAudit("creareCerereConcediu", timeStamp);
        ConcediuMedical cm = new ConcediuMedical(pacient, eliberatDe, eliberatLa, nrZileConcediu, dataFinal);
        return cm;
    }

    @Override
    public ConcediuMedical updateNrZile(ConcediuMedical cm, int nrZile, String dataFinal) {
        String timeStamp = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new java.util.Date());
        //citesteScrieAudit("updateNrZile", timeStamp);
        Runnable rr = new ConcediuMedicalService.RunnableAudit("updateNrZile", timeStamp);
        Thread t = new Thread(rr);
        t.start();
        cm.setNrZileConcediu(nrZile);
        cm.setDataFinal(dataFinal);
        return cm;
    }

    @Override
    public List<ConcediuMedical> stergereConcediu(List<ConcediuMedical> lista, int indx) {
        String timeStamp = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new java.util.Date());
        //citesteScrieAudit("stergereConcediu", timeStamp);
        Runnable rr = new ConcediuMedicalService.RunnableAudit("stergereConcediu", timeStamp);
        Thread t = new Thread(rr);
        t.start();
       for(int i = 0; i < lista.size(); i++)
           if(i == indx){
               lista.remove(i);
           }
       return lista;
    }

    @Override
    public List<ConcediuMedical> getConcediiFromDb(Connection connObj) {
        String timeStamp = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new java.util.Date());
        Runnable rr = new ConcediuMedicalService.RunnableAudit("getConcediiFromDb", timeStamp);
        Thread t = new Thread(rr);
        t.start();
       // citesteScrieAudit("getConcediiFromDb", timeStamp);
        List<ConcediuMedical> listaConcedii = new ArrayList<>();
        try {
            Statement stmt = connObj.createStatement();

            String sql = "SELECT Id, LastName, FirstName,DataNasterii,Varsta, Gen, Afectiuni,EliberatDe, EliberatLa,ZileConcediu, DataFinal, IdPacient  FROM ConcediiMedicale";
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
                int zileCncediu = rs.getInt("ZileConcediu");
                String dataFinal = rs.getString("DataFinal");
                String afec = rs.getString("Afectiuni");
                List<String> afectiuni = Arrays.asList(afec.split(" "));
                Pacient p = new Pacient(id, last, first, data, varsta, gen, afectiuni);
                ConcediuMedical cm  = creareCerereConcediu(p, eliberatDe, eliberatLa, zileCncediu, dataFinal);
                listaConcedii.add(cm);
            }
        }

        catch(SQLException se){
            se.printStackTrace();
        }
        return listaConcedii;
    }

    @Override
    public void createConcediuDb(Connection connObj, ConcediuMedical cm) {
        String timeStamp = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new java.util.Date());
        Runnable rr = new ConcediuMedicalService.RunnableAudit("createConcediuDb", timeStamp);
        Thread t = new Thread(rr);
        t.start();
       // citesteScrieAudit("createConcediuDb", timeStamp);
        try{
            Statement stmt = connObj.createStatement();


            String sql  = "INSERT INTO ConcediiMedicale " + "VALUES(" + cm.getPacient().getID()+ ",'" + cm.getPacient().getNume()+ "','" + cm.getPacient().getPrenume() +
                    "','" +  cm.getPacient().getDataNasterii()+ "'," +  cm.getPacient().getVarsta() + ",'" +  cm.getPacient().getGen() + "','"
                     + cm.getEliberatDe() + "','" +cm.getEliberatLa() + "'," + cm.getNrZileConcediu() + ",'"+cm.getDataFinal()+"'," + cm.getPacient().getID()+ ",'"+ cm.getPacient().getAfectiuni().toString()+ "')";
            stmt.executeUpdate(sql);
        }
        catch(SQLException se){
            se.printStackTrace();
        }

    }

    @Override
    public void modificaValabilitateDb(Connection connObj, int id, int val, String dataFinal) {
        String timeStamp = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new java.util.Date());
       // citesteScrieAudit("modificaValabilitateDb", timeStamp);
        Runnable rr = new ConcediuMedicalService.RunnableAudit("modificaValabilitateDb", timeStamp);
        Thread t = new Thread(rr);
        t.start();
        try{
            Statement stmt = connObj.createStatement();
            String sql = "UPDATE ConcediiMedicale " +
                    "SET ZileConcediu =" + val + " where Id=" + id;
            stmt.executeUpdate(sql);
            sql = "UPDATE ConcediiMedicale " +
                    "SET DataFinal ='" + dataFinal + "' where Id=" + id;
            stmt.executeUpdate(sql);
        }
        catch(SQLException se){
            se.printStackTrace();

        }
    }

    @Override
    public void deleteConcediuDb(Connection connObj, int id) {
        String timeStamp = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new java.util.Date());
        //citesteScrieAudit("deleteConcediuDb", timeStamp);
        Runnable rr = new ConcediuMedicalService.RunnableAudit("deleteConcediuDb", timeStamp);
        Thread t = new Thread(rr);
        t.start();
        try{
            Statement  stmt = connObj.createStatement();
            String sql = "DELETE FROM ConcediiMedicale " +
                    "WHERE id = " + id;
            stmt.executeUpdate(sql);
            System.out.println("Delete completed");
        }
        catch(SQLException se){
            se.printStackTrace();
        }
    }
}

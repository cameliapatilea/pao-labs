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
    public Asistent getFromListById(List<Asistent> lista, int id) {
        String timeStamp = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new java.util.Date());
        //citesteScrieAudit("getFromListById", timeStamp);
        Runnable rr = new AsistentService.RunnableAudit("getFromListById", timeStamp);
        Thread t = new Thread(rr);
        t.start();
        for(int i = 0; i < lista.size(); i++)
            if(lista.get(i).getId() == id)
                return lista.get(i);

        return null;
    }

    @Override
    public void afiseazaAsistenti(List<Asistent> asistenti) {
        String timeStamp = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new java.util.Date());
        //citesteScrieAudit("afiseazaAsistenti", timeStamp);
        Runnable rr = new AsistentService.RunnableAudit("afiseazaAsistenti", timeStamp);
        Thread t = new Thread(rr);
        t.start();
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
        //citesteScrieAudit("creareAsistent", timeStamp);
        Runnable rr = new AsistentService.RunnableAudit("creareAsistent", timeStamp);
        Thread t = new Thread(rr);
        t.start();
        Asistent a = new Asistent(id, nume, prenume, dataNasterii, varsta, gen, specializare, oraStart, oraEnd, ture);
       return a;
    }

    @Override
    public Asistent updateVarsta(Asistent a, int varsta) {
        String timeStamp = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new java.util.Date());
       // citesteScrieAudit("updateVarsta", timeStamp);
        Runnable rr = new AsistentService.RunnableAudit("updateVarsta", timeStamp);
        Thread t = new Thread(rr);
        t.start();
        a.setVarsta(varsta);
        return a;
    }

    @Override
    public List<Asistent> updateSpecializare(int id, List<Asistent> asistenti,  String specializare) {
        String timeStamp = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new java.util.Date());
       // citesteScrieAudit("updateSpecializare", timeStamp);
        Runnable rr = new AsistentService.RunnableAudit("updateSpecializare", timeStamp);
        Thread t = new Thread(rr);
        t.start();
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
        //citesteScrieAudit("updateTure", timeStamp);
        Runnable rr = new AsistentService.RunnableAudit("updateTure", timeStamp);
        Thread t = new Thread(rr);
        t.start();
        a.setTure(ture);
        return a;
    }

    @Override
    public String afiseazaProgram(String nume, String prenume, List<Asistent> asistenti) {
        String timeStamp = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new java.util.Date());
        //citesteScrieAudit("afiseaazaProgram", timeStamp);
        Runnable rr = new AsistentService.RunnableAudit("afiseazaProgram", timeStamp);
        Thread t = new Thread(rr);
        t.start();
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
        Runnable rr = new AsistentService.RunnableAudit("afiseazaSpecializare", timeStamp);
        Thread t = new Thread(rr);
        t.start();
        //citesteScrieAudit("afiseazaSpecializare", timeStamp);
        return a.getSpecializare();
    }

    @Override
    public List<Asistent> adaugaAsistentInLista(Asistent a, List<Asistent> lista) {
        String timeStamp = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new java.util.Date());
      //  citesteScrieAudit("adaugaAsistentInLista", timeStamp);
        Runnable rr = new AsistentService.RunnableAudit("adaugaAsistentInLista", timeStamp);
        Thread t = new Thread(rr);
        t.start();
       lista.add(a);
       return lista;
    }

    @Override
    public Asistent getAsistentBySpecializare(List<Asistent> lista, String specializare) {
        String timeStamp = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new java.util.Date());
        //citesteScrieAudit("getAsistentBySpecializare", timeStamp);
        Runnable rr = new AsistentService.RunnableAudit("getAsistentBySpecializare", timeStamp);
        Thread t = new Thread(rr);
        t.start();
        int ok = 0;
        Asistent x = new Asistent();
        for(int i = 0; i <  lista.size(); i++)
            if(lista.get(i).getSpecializare().compareTo(specializare) == 0){
               x = lista.get(i);
               ok = 1;
                break;
            }
        if(ok  == 0) {
            System.out.println("Nu exista niciun asistent cu specializarea ceruta");
        }
        return x;
    }

    @Override
    public List<Asistent> getAllFromDb(Connection connObj) {
        String timeStamp = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new java.util.Date());
        //citesteScrieAudit("getAllFromDbAsistenti", timeStamp);
        Runnable rr = new AsistentService.RunnableAudit("getAllFromDb", timeStamp);
        Thread t = new Thread(rr);
        t.start();
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
                if(ture == 1)
                    tura = true;
                else tura = false;

                Asistent a = creareAsistent(id, last, first, data, varsta, gen, specializare, oraStart, oraEnd, tura);
                listaAsistenti.add(a);
            }
            rs.close();

        }
        catch(SQLException se){
            se.printStackTrace();
        }
        return listaAsistenti;
    }

    @Override
    public void adaugaAsistentcDb(Connection connObj, Asistent a) {
        String timeStamp = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new java.util.Date());
       // citesteScrieAudit("AdaugaAsistentDb", timeStamp);
        Runnable rr = new AsistentService.RunnableAudit("adaugaAsistentcDb", timeStamp);
        Thread t = new Thread(rr);
        t.start();
        try{
            Statement stmt = connObj.createStatement();
            int tura;
            if(a.getTure() == true)
                tura = 1;
            else tura = 0;
            String sql  = "INSERT INTO Asistenti " + "VALUES(" + a.getID() + ",'" + a.getNume() + "','" + a.getPrenume() + "','" + a.getDataNasterii() +
                    "'," + a.getVarsta() + ",'" + a.getGen() + "','" + a.getSpecializare() + "'," + a.getOraStart() + ","  + a.getOraEnd() + "," +tura +")";
            stmt.executeUpdate(sql);
        }
        catch(SQLException se){
            se.printStackTrace();
        }

    }

    @Override
    public void updateVarstaAsistentDb(Connection connObj, int id, int varsta, String data) {
        String timeStamp = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new java.util.Date());
      //  citesteScrieAudit("updateVarstaAsistentDb", timeStamp);
        Runnable rr = new AsistentService.RunnableAudit("updateVarstaAsistentDb", timeStamp);
        Thread t = new Thread(rr);
        t.start();
        try{
            Statement stmt = connObj.createStatement();
            String sql = "UPDATE Asistenti " +
                    "SET Varsta ='" + varsta + "' where Id=" + id;
            stmt.executeUpdate(sql);
            sql = "UPDATE Asistenti " +
                    "SET DataNasterii ='" + data + "' where Id=" + id;
            stmt.executeUpdate(sql);
        }
        catch(SQLException se){
            se.printStackTrace();
        }
    }

    @Override
    public void updateSpecializareAsistentDb(Connection connObj, int id, String specializare) {
        String timeStamp = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new java.util.Date());
      //  citesteScrieAudit("updateSpecializareAsistentDb", timeStamp);
        Runnable rr = new AsistentService.RunnableAudit("updateSpecializareAsistentDb", timeStamp);
        Thread t = new Thread(rr);
        t.start();
        try{
            Statement stmt = connObj.createStatement();
            String sql = "UPDATE Asistenti " +
                    "SET Specializare ='" + specializare + "' where Id=" + id;
            stmt.executeUpdate(sql);
        }
        catch(SQLException se){
            se.printStackTrace();

        }
    }

    @Override
    public void getOrarAsistentDb(Connection connObj, int id) {
        String timeStamp = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new java.util.Date());
        Runnable rr = new AsistentService.RunnableAudit("getOrarAsistentDb", timeStamp);
        Thread t = new Thread(rr);
        t.start();
      //  citesteScrieAudit("getOrarMedicDb", timeStamp);
        List<Asistent> listaAsistenti = getAllFromDb(connObj);
        try{
            Statement stmt = connObj.createStatement();
            String sql = "SELECT LastName,FirstName,OraStart, OraEnd FROM Asistenti" +
                    " WHERE id =" + id;
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()){
                String nume = rs.getString("LastName");
                String prenume = rs.getString("FirstName");
                float oraStart = rs.getFloat("OraStart");
                float oraStop = rs.getFloat("OraEnd");

                String orar =  afiseazaProgram(nume, prenume, listaAsistenti);
                System.out.println("Asistentul " + nume + " " + prenume + " lucreaza in intervalul orar " + orar);
            }
        }
        catch (SQLException se){
            se.printStackTrace();
        }
    }

    @Override
    public Asistent getAsistentBySpecializareDb(Connection connObj, String specializare) {
        String timeStamp = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new java.util.Date());
        Runnable rr = new AsistentService.RunnableAudit("getAsistentBySpecializareDb", timeStamp);
        Thread t = new Thread(rr);
        t.start();
        List<Asistent> listaAsistenti = getAllFromDb(connObj);
        Asistent cautat = getAsistentBySpecializare(listaAsistenti, specializare);
       return cautat;

    }

    @Override
    public void deleteAsistentFromDb(Connection connObj, int id) {
        String timeStamp = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new java.util.Date());
        //citesteScrieAudit("deleteAsistentFromDb", timeStamp);
        Runnable rr = new AsistentService.RunnableAudit("deleteAsistentFromDb", timeStamp);
        Thread t = new Thread(rr);
        t.start();
        try{
            Statement  stmt = connObj.createStatement();
            String sql = "DELETE FROM Asistenti " +
                    "WHERE id = " + id;
            stmt.executeUpdate(sql);
            System.out.println("Delete completed");
        }
        catch(SQLException se){
            se.printStackTrace();
        }
    }
}

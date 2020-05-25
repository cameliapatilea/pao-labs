package Services.Implementations;

import Entities.Medic;
import Entities.Pacient;
import Helpers.AuditService;
import Services.Interfaces.MedicInterface;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class MedicService implements MedicInterface {
    private class RunnableAudit implements Runnable{
        private  String comanda;
        private  String timp;

        public RunnableAudit(String comanda, String timp){
            this.comanda = comanda;
            this.timp = timp;
        }
        public void run()
        {

                Thread.currentThread().setName(comanda);
                String threadName = Thread.currentThread().getName();
                List<String> matrice = AuditService.citireCSVAudit("src/excel/audit.csv");
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
    public Medic getFromListById(List<Medic> lista, int id) {
        String timeStamp = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new java.util.Date());
        //citesteScrieAudit("getFromListById", timeStamp);
        Runnable rr = new MedicService.RunnableAudit("getFromListById", timeStamp);
        Thread t = new Thread(rr);
        t.start();
        for(int i = 0; i < lista.size(); i++)
            if(lista.get(i).getId() == id)
                return lista.get(i);

        return null;
    }

    @Override
    public void afiseazaMedici(List<Medic> medici) {
        String timeStamp = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new java.util.Date());
        //citesteScrieAudit("afiseazaMedici", timeStamp);
        Runnable rr = new MedicService.RunnableAudit("afiseazaMedici", timeStamp);
        Thread t = new Thread(rr);
        t.start();
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
        //citesteScrieAudit("creareMedic", timeStamp);
        Runnable rr = new MedicService.RunnableAudit("creareMedic", timeStamp);
        Thread t = new Thread(rr);
        t.start();
        Medic m = new Medic(id, nume, prenume, dataNasterii, varsta, gen, specializare, oraStart, oraEnd, codParafa);

        return m;
    }

    @Override
    public List<Medic> updateSpecialiare(int id,List<Medic> medici, String specializare) {
        String timeStamp = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new java.util.Date());
        //citesteScrieAudit("updateSpecializare", timeStamp);
        Runnable rr = new MedicService.RunnableAudit("updateSpecialiare", timeStamp);
        Thread t = new Thread(rr);
        t.start();
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
        //citesteScrieAudit("updateVarsta", timeStamp);
        Runnable rr = new MedicService.RunnableAudit("updateVarsta", timeStamp);
        Thread t = new Thread(rr);
        t.start();
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
        //citesteScrieAudit("updateNume", timeStamp);
        Runnable rr = new MedicService.RunnableAudit("updateNume", timeStamp);
        Thread t = new Thread(rr);
        t.start();
        m.setNume(nume);
        return m;
    }

    @Override
    public String afiseazaSpecializare(String nume, String prenume, List<Medic> medici) {
        String timeStamp = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new java.util.Date());
        //citesteScrieAudit("afiseazaSpecializare", timeStamp);
        String x = "";
        Runnable rr = new MedicService.RunnableAudit("afiseazaSpecializare", timeStamp);
        Thread t = new Thread(rr);
        t.start();

        for(int i = 0; i <  medici.size(); i++)
            if(medici.get(i).getNume().compareTo(nume) == 0 && medici.get(i).getPrenume().compareTo(prenume) == 0)
                x+= medici.get(i).getSpecializare();
         return x;
    }

    @Override
    public String afiseazaIntervalOrar(String nume, String prenume, List<Medic> medici) {
        String timeStamp = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new java.util.Date());
        //citesteScrieAudit("afiseazaIntervalOrar", timeStamp);
        Runnable rr = new MedicService.RunnableAudit("afiseazaIntervalOrar", timeStamp);
        Thread t = new Thread(rr);
        t.start();
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

    @Override
    public List<Medic> getAllFromDb(Connection connObj) {
        String timeStamp = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new java.util.Date());
        //citesteScrieAudit("getAllFromDbMedici", timeStamp);
        List<Medic> listaMedici= new ArrayList<>() ;
        Runnable rr = new MedicService.RunnableAudit("getAllFromDb", timeStamp);
        Thread t = new Thread(rr);
        t.start();
        try{

            Statement stmt = connObj.createStatement();

            String sql = "SELECT Id, LastName, FirstName,DataNasterii,Varsta, Gen, Specializare, OraStart,OraEnd, CodParafa FROM Medici";
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
                int CodParafa = rs.getInt("CodParafa");
                Medic m = creareMedic(id, last, first, data, varsta, gen, specializare, oraStart, oraEnd, CodParafa);
                listaMedici.add(m);
            }
            rs.close();

        }
        catch(SQLException se){
            se.printStackTrace();
        }
    return listaMedici;
    }

    @Override
    public void adaugaMedicDb(Connection connObj, Medic m) {
        String timeStamp = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new java.util.Date());
        //citesteScrieAudit("AdaugaMedicDb", timeStamp);
        Runnable rr = new MedicService.RunnableAudit("adaugaMedicDb", timeStamp);
        Thread t = new Thread(rr);
        t.start();
        try{
            Statement stmt = connObj.createStatement();

            String sql  = "INSERT INTO Medici " + "VALUES(" + m.getID() + ",'" + m.getNume() + "','" + m.getPrenume() + "','" + m.getDataNasterii() +
                    "'," + m.getVarsta() + ",'" + m.getGen() + "','" + m.getSpecializare() + "'," + m.getOraStart() + ","  + m.getOraEnd() + "," + m.getCodParafa() +")";
            stmt.executeUpdate(sql);
        }
        catch(SQLException se){
            se.printStackTrace();
        }
    }

    @Override
    public void updateVarstaMedicDb(Connection connObj, int id, int varsta, String data) {
        String timeStamp = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new java.util.Date());
        //citesteScrieAudit("updateVarstaMedicDb", timeStamp);
        Runnable rr = new MedicService.RunnableAudit("updateVarstaMedicDb", timeStamp);
        Thread t = new Thread(rr);
        t.start();
        try{
            Statement stmt = connObj.createStatement();
            String sql = "UPDATE Medici " +
                    "SET Varsta ='" + varsta + "' where Id=" + id;
            stmt.executeUpdate(sql);
            sql = "UPDATE Medici " +
                    "SET DataNasterii ='" + data + "' where Id=" + id;
            stmt.executeUpdate(sql);
        }
        catch(SQLException se){
            se.printStackTrace();
        }

    }

    @Override
    public void updateSpecializareMedicDb(Connection connObj, int id, String specializare) {
        String timeStamp = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new java.util.Date());
        //citesteScrieAudit("updateSpecializareMedicDb", timeStamp);
        Runnable rr = new MedicService.RunnableAudit("updateSpecializareMedicDb", timeStamp);
        Thread t = new Thread(rr);
        t.start();
        try{
            Statement stmt = connObj.createStatement();
            String sql = "UPDATE Medici " +
                    "SET Specializare ='" + specializare + "' where Id=" + id;
            stmt.executeUpdate(sql);
        }
        catch(SQLException se){
            se.printStackTrace();
        }
    }

    @Override
    public void getOrarDb(Connection connObj, int id) {
        String timeStamp = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new java.util.Date());
        //citesteScrieAudit("getOrarMedicDb", timeStamp);
        List<Medic> listaMedici = getAllFromDb(connObj);
        Runnable rr = new MedicService.RunnableAudit("getOrarDb", timeStamp);
        Thread t = new Thread(rr);
        t.start();
        try{
            Statement stmt = connObj.createStatement();
            String sql = "SELECT LastName,FirstName,OraStart, OraEnd FROM Medici" +
                    " WHERE id =" + id;
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()){
                String nume = rs.getString("LastName");
                String prenume = rs.getString("FirstName");
                float oraStart = rs.getFloat("OraStart");
                float oraStop = rs.getFloat("OraEnd");

               String orar =  afiseazaIntervalOrar(nume, prenume, listaMedici);
                System.out.println("Medicul " + nume + " " + prenume + " lucreaza in intervalul orar " + orar);
            }
        }
        catch (SQLException se){
            se.printStackTrace();
        }

    }

    @Override
    public void deleteMedicFromDb(Connection connObj, int id) {
        String timeStamp = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new java.util.Date());
        //citesteScrieAudit("deleteMedicFromDb", timeStamp);
        Runnable rr = new MedicService.RunnableAudit("deleteMedicFromDb", timeStamp);
        Thread t = new Thread(rr);
        t.start();
        try{
           Statement  stmt = connObj.createStatement();
            String sql = "DELETE FROM Medici " +
                    "WHERE id = " + id;
            stmt.executeUpdate(sql);
            System.out.println("Delete completed");
        }
        catch(SQLException se){
            se.printStackTrace();
        }
    }
}

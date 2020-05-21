package Services.Implementations;

import Entities.Pacient;
import Helpers.AuditService;
import Services.Interfaces.PacientInterface;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class PacientService extends GeneralService<Pacient> implements PacientInterface {
    @Override
    public void citesteScrieAudit(String comanda, String timp) {
            List<String> matrice = AuditService.citireCSVAudit("src/excel/audit.csv");
            comanda += " " + timp;
            matrice.add(comanda);
            AuditService.scriereCSVAudit("src/excel/audit.csv", new String[]{"Comanda","Data", "Ora", "ThreadName"}, matrice);
    }

    @Override
    public Pacient getFromListById(List<Pacient> lista, int id) {
        String timeStamp = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new java.util.Date());

        citesteScrieAudit("getFromListById", timeStamp);

        for(int i = 0; i < lista.size(); i++)
            if(lista.get(i).getId() == id)
                return lista.get(i);



        return null;
    }

    @Override
    public List<Pacient> adaugaAfectiune(int id, List<Pacient> pacienti, String afectiune) {
        String timeStamp = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new java.util.Date());
        citesteScrieAudit("adaugaAfectiune", timeStamp);
    for(int i = 0; i < pacienti.size(); i++)
    {
        if(pacienti.get(i).getID() == id)
        {
            pacienti.get(i).getAfectiuni().add(afectiune);
        }
    }
        return pacienti;
    }

    @Override
    public void afiseazaPacienti(List<Pacient> pacienti) {
        String timeStamp = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new java.util.Date());
        citesteScrieAudit("afiseazaPacienti", timeStamp);
        for(int i = 0; i < pacienti.size(); i++)
        {
            System.out.println("Pacient: ");
            System.out.println(pacienti.get(i).toString());
            System.out.println("==================================");
        }
    }

    @Override
    public Pacient adaugaAfectiuni(Pacient pacient, List<String> afectiuni) {
        String timeStamp = new SimpleDateFormat("yyyy/MM/dd HH:mm.:ss").format(new java.util.Date());
        citesteScrieAudit("adaugaAfectiune", timeStamp);
        pacient.getAfectiuni().addAll(afectiuni);


        return pacient;
    }

    @Override
    public Pacient updateAfectiune(Pacient pacient, String afectiuneVeche, String afectiuneNoua) {
        String timeStamp = new SimpleDateFormat("yyyy/MM/dd HH:mm.:ss").format(new java.util.Date());
        citesteScrieAudit("updateAfectiune", timeStamp);
        List<String> afectiuni =  pacient.getAfectiuni();
        for(int i = 0; i < afectiuni.size(); i++)
            if(afectiuni.get(i).toLowerCase().compareTo(afectiuneVeche.toLowerCase()) == 0)
            {
                afectiuni.remove(i);
                afectiuni.add(afectiuneNoua);
            }


        return pacient;
    }

    @Override
    public List<Pacient> stergeAfectiune(int id, List<Pacient> pacienti, String afectiune) {
        String timeStamp = new SimpleDateFormat("yyyy/MM/dd HH:mm.:ss").format(new java.util.Date());
        citesteScrieAudit("stergeAfectiune", timeStamp);
        for(int i = 0; i < pacienti.size(); i++)
        {
            if(pacienti.get(i).getID() == id)
            {
                pacienti.get(i).getAfectiuni().remove(afectiune);
            }
        }



        return pacienti;
    }

    @Override
    public Pacient stergeAfectiuni(Pacient pacient, List<String> afectiuni, boolean stergeTot) {
        String timeStamp = new SimpleDateFormat("yyyy/MM/dd HH:mm.:ss").format(new java.util.Date());
        citesteScrieAudit("adaugaAfectiuni", timeStamp);
       if(stergeTot == true)
           pacient.setAfectiuni(new ArrayList<String>());
       else
       {
           List<String> newAfectiuni = pacient.getAfectiuni();
           for(int i = 0; i <  afectiuni.size(); i++)
               newAfectiuni.remove(afectiuni.get(i));


       }
       return pacient;
    }

    @Override
    public Pacient updateNume(Pacient pacient, String nume) {
        String timeStamp = new SimpleDateFormat("yyyy/MM/dd HH:mm.:ss").format(new java.util.Date());
        citesteScrieAudit("updateNume", timeStamp);
        pacient.setNume(nume);

        return pacient;
    }

    @Override
    public Pacient updateVarsta(Pacient pacient, int varsta) {
        String timeStamp = new SimpleDateFormat("yyyy/MM/dd HH:mm.:ss").format(new java.util.Date());
        citesteScrieAudit("updateVarsta", timeStamp);
        pacient.setVarsta(varsta);

        return pacient;
    }

    @Override
    public List<Pacient> adaugaPacientLaLista(Pacient pacient, List<Pacient> pacienti) {
        String timeStamp = new SimpleDateFormat("yyyy/MM/dd HH:mm.:ss").format(new java.util.Date());
        citesteScrieAudit("adaugaPacientLaLista", timeStamp);
        pacienti.add(pacient);

        return pacienti;

    }

    @Override
    public Pacient crearePacient(int id, String nume, String prenume, String dataNasterii,int varsta, String gen, List<String> afectiuni) {
        String timeStamp = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new java.util.Date());
        citesteScrieAudit("crearePacient", timeStamp);
        Pacient p = new Pacient(id, nume, prenume, dataNasterii, varsta, gen, afectiuni);
        return p;
    }

    @Override
    public List<Pacient> getAllFromDb(Connection connObj) {
        String timeStamp = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new java.util.Date());
        citesteScrieAudit("getAllFromDb", timeStamp);
        List<Pacient> listaPacienti = new ArrayList<>() ;
        try{
            Statement stmt = null;
            stmt = connObj.createStatement();

            String sql = "SELECT Id, LastName, FirstName,DataNasterii,Varsta, Gen, Afectiuni FROM Pacienti";
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()){

                //Retrieve by column name
                int id  = rs.getInt("Id");
                String last = rs.getString("LastName");
                String first = rs.getString("FirstName");
                String data = rs.getString("DataNasterii");
                int varsta = rs.getInt("Varsta");
                String gen = rs.getString("Gen");
                String afect = rs.getString("Afectiuni");
                List<String> afectiuni = Arrays.asList(afect.split(" "));
                //Pacient p =new Pacient(id, last,first, data,varsta,gen, afectiuni);
                Pacient p = crearePacient(id, last,first, data,varsta,gen, afectiuni);
                //Display values
                /*System.out.println("ID: " + id);

                System.out.println("FirstName: " + first);
                System.out.println("LastName: " + last);
                System.out.println("DataNasterii: " + data);
                System.out.println("Varsta: " + varsta);
                System.out.println("Gen: " + gen);
                System.out.println("Afectiuni: " + afect);*/
                listaPacienti.add(p);
            }
            rs.close();
        }
        catch(SQLException se){
            se.printStackTrace();
        }
        return listaPacienti;
    }

    @Override
    public void adaugaPacientDb(Connection connObj, Pacient p) {
        String timeStamp = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new java.util.Date());
        citesteScrieAudit("adaugaPacientDb", timeStamp);
        try{
            Statement stmt = connObj.createStatement();


            String sql  = "INSERT INTO Pacienti " + "VALUES(" + p.getID() + ",'" + p.getNume() + "','" + p.getPrenume() + "','" + p.getDataNasterii()+ "'," + p.getVarsta() + ",'" + p.getGen() + "','" + p.getAfectiuni().toString() + "')";
            stmt.executeUpdate(sql);
        }
        catch(SQLException se){
            se.printStackTrace();
        }
    }

    @Override
    public void updateVarstaPacientDb(Connection connObj, int id, int varsta, String data) {
        String timeStamp = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new java.util.Date());
        citesteScrieAudit("updateVarstaPacientDb", timeStamp);
        try{
            Statement stmt = connObj.createStatement();
            String sql = "UPDATE Pacienti " +
                    "SET Varsta =" + varsta + " where Id=" + id;
            stmt.executeUpdate(sql);
            sql = "UPDATE Pacienti " +
                    "SET DataNasterii ='" + data + "' where Id=" + id;
            stmt.executeUpdate(sql);
        }
        catch(SQLException se){
            se.printStackTrace();
        }
    }

    @Override
    public void deletePacientFromDb(Connection connObj, int id) {
        try{

            Statement stmt = connObj.createStatement();
            String sql = "DELETE FROM Pacienti " +
                    "WHERE id = " + id;
            stmt.executeUpdate(sql);
        }
        catch(SQLException se){
            se.printStackTrace();
        }
    }


}

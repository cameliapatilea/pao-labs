package Services.Implementations;

import Entities.*;
import Helpers.AuditService;
import Services.Interfaces.CabinetMedicalInterface;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CabinetMedicalService implements CabinetMedicalInterface {
    @Override
    public void citesteScrieAudit(String comanda, String timp) {
        List<String> matrice = AuditService.citireCSVAudit("src/excel/audit.csv");
        comanda += " " + timp;
        matrice.add(comanda);
        AuditService.scriereCSVAudit("src/excel/audit.csv", new String[]{"Comanda","Data", "Ora"}, matrice);
    }

    @Override
    public CabinetMedical getFromListById(List<CabinetMedical> lista, int id) {
        String timeStamp = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new java.util.Date());
        citesteScrieAudit("getFromListById", timeStamp);
        for(int i = 0; i < lista.size(); i++)
            if(lista.get(i).getId() == id)
                return lista.get(i);

        return null;
    }

    @Override
    public void afiseazaCabinet(List<CabinetMedical> cm) {
        String timeStamp = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new java.util.Date());
        citesteScrieAudit("afisezaCabinet", timeStamp);
        for(int i = 0; i < cm.size(); i++)
        {
            System.out.println("Cabinet medical: ");
            System.out.println(cm.get(i).toString());
            System.out.println("==================================");
        }
    }

    @Override
    public String getIntervalFunctionare(CabinetMedical cab) {
        String timeStamp = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new java.util.Date());
        citesteScrieAudit("getIntervaFunctionare", timeStamp);
        return "Intervalul orar in care cabinetul este deschis este: " + cab.getOraInceput() + "-" + cab.getOraStop();
    }

    @Override
    public String getAdresaCabinet(CabinetMedical cab)
    {
        String timeStamp = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new java.util.Date());
        citesteScrieAudit("getAdresaCabinet", timeStamp);
        return cab.getAdresa();
    }

    @Override
    public String getMedici(List<Medic> listaMedici) {
        String timeStamp = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new java.util.Date());
        citesteScrieAudit("getMedici", timeStamp);
        String  x = "Lista de medici din cabinet este: \n";
        for(int i = 0; i <  listaMedici.size(); i++)
            x+=listaMedici.get(i).toString() + "\n";
        return x;
    }

    @Override
    public String getAsistenti(List<Asistent> listaAsistenti) {
        String timeStamp = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new java.util.Date());
        citesteScrieAudit("getAsistenti", timeStamp);
        String  x = "Lista de asistenti din cabinet este: \n";
        for(int i = 0; i <  listaAsistenti.size(); i++)
            x+=listaAsistenti.get(i).toString() + "\n";
        return x;
    }

    @Override
    public String getPacienti(List<Pacient> listaPacienti) {
        String timeStamp = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new java.util.Date());
        citesteScrieAudit("getPacienti", timeStamp);
        String  x = "Lista de pacienti din cabinet este: \n";
        for(int i = 0; i <  listaPacienti.size(); i++)
            x+=listaPacienti.get(i).toString() + "\n";
        return x;
    }

    @Override
    public void getDetaliiCabinetFromDb(Connection connObj) {
        String timeStamp = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new java.util.Date());
        citesteScrieAudit("getDetaliiCabinetFromDb", timeStamp);

        try {
            Statement stmt = connObj.createStatement();

            String sql = "SELECT Id,NumarPacienti, NumarMedici, NumarAsistenti, AdresaCabinet, OraInceput, OraStop   FROM CabinetMedical";
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                //Retrieve by column name
                int id = rs.getInt("Id");
                int pacienti = rs.getInt("NumarPacienti");
                int medici = rs.getInt("NumarMedici");
                int asistenti = rs.getInt("NumarAsistenti");
                String adresa = rs.getString("AdresaCabinet");
                int oraInceput = rs.getInt("OraInceput");
                int oraSfarsit = rs.getInt("OraStop");

                System.out.println("Cabinetul medical are " + pacienti + " pacienti, " + medici + " medici, " + asistenti + " asistenti.");
                System.out.println("Adresa la care se afla este " + adresa);
                System.out.println("Intervalul orar in care activeaza este " + oraInceput +"-" + oraSfarsit);
            }
        }

        catch(SQLException se){
            se.printStackTrace();
        }
    }

    @Override
    public void updateStradaDb(Connection connObj, String strada) {

    }
}

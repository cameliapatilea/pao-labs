import Entities.*;
import Helpers.FileHelper;
import Helpers.ReadWriteService;
import Services.Implementations.*;
import Services.Interfaces.PacientInterface;
import com.opencsv.CSVWriter;
import org.omg.Messaging.SYNC_WITH_TRANSPORT;
import com.opencsv.CSVReader;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.*;
import java.util.*;

public class Main{

    //initialize database
    public static Statement stmt = null;
    public static Connection connObj;
    public static String JDBC_URL = "jdbc:sqlserver://javadatabaseserver.database.windows.net:1433;database=JavaProjectDatabase;user=cameliapatilea@javadatabaseserver;password={AdminServerPassword1};encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;";

    public static void getDbConnection(){
        try{
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            connObj = DriverManager.getConnection(JDBC_URL);
            if(connObj != null) {
                DatabaseMetaData metaObj = (DatabaseMetaData) connObj.getMetaData();
                System.out.println("Driver Name?= " + metaObj.getDriverName() + ", Driver Version?= " + metaObj.getDriverVersion() + ", Product Name?= " + metaObj.getDatabaseProductName() + ", Product Version?= " + metaObj.getDatabaseProductVersion());

            }}
            catch(Exception sqlException){
                sqlException.printStackTrace();
            }
    }


    private static final String pacientiPath = FileHelper.getFullPath("src/excel/pacienti.csv");
    private static final String mediciPath = FileHelper.getFullPath("src/excel/medici.csv");
    private static final String asistentiPath = FileHelper.getFullPath("src/excel/asistenti.csv");
    private static final String retetePath = FileHelper.getFullPath("src/excel/retete.csv");
    private static final String adeverintePath = FileHelper.getFullPath("src/excel/adeverintaMedicala.csv");
    private static final String trimiteriPath = FileHelper.getFullPath("src/excel/trimiteriMedicale.csv");
    private static final String concediiPath = FileHelper.getFullPath("src/excel/concediuMedical.csv");


    public  static PacientService pacientService;
    public  static List<String> listapb;
    public static List<List<String>> matrPacienti;
    public static  List<Pacient> pacienti;

    public static MedicService medicService;
    public static List<List<String>> matrMedici;
    public static List<Medic> medici;

    public static List<List<String>> matrAsistenti;
    public static List<Asistent> listaAsistenti;
    public static AsistentService asistentService;

    public static List<List<String>> matrRetete;
    public static RetetaService retetaService;
    public static List<Reteta> listaRetete;

    public static List<List<String>> matrAdeverinte;
    public static List<AdeverintaMedicala> listaAdeverinte;
    public static AdeverintaMedicalaService adeverintaService;

    public static List<List<String>> matrTrimiteri;
    public static List<TrimitereMedicala> listaTrimiteri;
    public static TrimitereMedicalaService trimitereService;

    public static List<List<String>> matrConcedii;
    public static List<ConcediuMedical> listaConcedii;
    public static ConcediuMedicalService concediuService;

    public static List<List<String>> matrCabinet;
    public static List<CabinetMedical> listaCabinet;
    public static CabinetMedicalService cabinetService;


   public static void initializeazaPacienti(){

       pacientService = new PacientService();
       listapb = new ArrayList<String>();
       pacienti = new ArrayList<>();
       Collections.sort(pacienti);
   }
    public static void initializeazaMedici(){
        medicService = new MedicService();
        medici = new ArrayList<>();
    }
    public static void initializeazaAsistenti(){
        listaAsistenti = new ArrayList<Asistent>();
        asistentService = new AsistentService();
    }

    public static void initializeazRetete(){
        listaRetete = new ArrayList<Reteta>();
        retetaService = new RetetaService();
    }
    public static void initializeazaAdeverinte(){
        listaAdeverinte = new ArrayList<AdeverintaMedicala>();
        adeverintaService = new AdeverintaMedicalaService();
    }
    public static void initializeazaTrimiteri(){
       listaTrimiteri = new ArrayList<>();
       trimitereService = new TrimitereMedicalaService();
    }
    public static void initializeazaConcedii(){
       listaConcedii = new ArrayList<>();
       concediuService = new ConcediuMedicalService();
    }
    public static void initializeazaCabinet(){
       listaCabinet = new ArrayList<>();
       cabinetService = new CabinetMedicalService();
    }


    public static void afiseazaMeniu(){
        System.out.println("Bine ati venit. Introduceti una din comenzile de mai jos, in functie de actiunea pe care o doriti.");
        System.out.println("Pentru a accesa datele despre pacienti, intrudceti 1");
        System.out.println("Pentru a accesa datele despre medici, introduceti 2");
        System.out.println("Pentru a accesa datele despre asistenti, introduceti 3");
        System.out.println("Pentru a accesa datele despre retete, introduceti 4");
        System.out.println("Pentru a accesa datele despre trimiterile medicale, introduceti 5");
        System.out.println("Pentru a accesa datele despre concediile medicale, introduceti 6");
        System.out.println("Pentru a accesa datele despre adeverintele medicale, introduceti 7");
        System.out.println("Pentru a accesa datele despre cabinetul medical, introduceti 8");
    }



    public static void main(String[] args) throws IOException {
        getDbConnection();
        Scanner scan = new Scanner(System.in);
        afiseazaMeniu();

        //pacienti - lista locala pentru a extrage datele din csv
        initializeazaPacienti();

        //lista medici - pt a extrage datele din csv
        initializeazaMedici();

        //lista asistenti pt a extrage datele din csv
        initializeazaAsistenti();

        //lista retete- pt extragere date din csv
        initializeazRetete();

        //lista adeverinte medicale
        initializeazaAdeverinte();

        //lista concedii medicale
        initializeazaConcedii();

        //lista trimiteri medicale
        initializeazaTrimiteri();

        //lista cabinet- date din csv
        initializeazaCabinet();

        int x = scan.nextInt();
        while(x!=0) {

            switch (x) {
                case 1: {
                    System.out.println("Bine ati venit la categoria pacienti");
                    System.out.println("Pentru a vedea pacientii deja existenti, introduceti 1");
                    System.out.println("Pentru a adauga un pacient in baza de date, introduceti 2");
                    System.out.println("Pentru a adauaga o afectiune unui pacienti, introduceti 3");
                    System.out.println("Pentru a sterge o afectiune a unui pacient, introdoceti 4");
                    System.out.println("Pentru a face update la varsta unui pacient, introdoceti 5");
                    System.out.println("Pentru a sterge un pacient, introdoceti 6");


                    int y = scan.nextInt();
                    while (y != 0) {
                        switch (y) {
                            case 1: {
                            System.out.println("Lista de pacienti este: ");
                            /*matrPacienti = ReadWriteService.citireCSV(pacientiPath);
                            pacienti = Pacient.getListFromCSV(matrPacienti);
                            pacientService.afiseazaPacienti(pacienti);*/
                            pacientService.afiseazaPacienti(pacientService.getAllFromDb(connObj));

                                break;
                            }
                            case 2:
                            {
                             /*matrPacienti = ReadWriteService.citireCSV(pacientiPath);
                             pacienti = Pacient.getListFromCSV(matrPacienti);*/
                                Pacient p = new Pacient();
                                System.out.println("Pentru a adauga un pacient in baza de date, introduceti urmatoarele date:\n");
                                System.out.println("ID: ");
                                int id = scan.nextInt();
                                System.out.println("Nume: ");
                                String nume = scan.next();
                                System.out.println("Prenume");
                                String prenume = scan.next();
                                System.out.println("Data nasterii, sub forma ll/dd/aaaa");
                                String dataNasterii = scan.next();
                                System.out.println("Varsta: ");
                                int varsta = scan.nextInt();
                                System.out.println("Gen:");
                                String gen = scan.next();
                                int nr;
                                System.out.println("Numarul de afectiuni pe care le are pacientul: ");
                                nr = scan.nextInt();
                                List<String> listaAfectiuni = new ArrayList<String>();
                                for(int i = 0;  i<  nr; i++)
                                {
                                    System.out.println("Introduceti afectiunea: ");
                                    String afect = scan.next();
                                    listaAfectiuni.add(afect);
                                }
                                p = pacientService.crearePacient(id, nume,prenume,dataNasterii,varsta, gen, listaAfectiuni);
                                pacienti.add(p);
                                Collections.sort(pacienti);
                                pacientService.adaugaPacientDb(connObj, p);
                                //ReadWriteService.scriereCSV(pacientiPath, Pacient.returnHeader(), Pacient.listToCSV(pacienti));

                                break;
                            }
                            case 3: {

                                /*matrPacienti = ReadWriteService.citireCSV(pacientiPath);
                                pacienti = Pacient.getListFromCSV(matrPacienti);*/

                                System.out.println("Pentru a adauga o afectiune unui pacient, intai introduceti id-ul si numele pacientului");

                                System.out.println("ID: ");
                                int id = scan.nextInt();
                                System.out.println("Nume: ");
                                String nume = scan.next();
                                System.out.println("Prenume");
                                String prenume = scan.next();
                                List<String> listaNouaAfectiuni = new ArrayList<>();
                                PacientService p = new PacientService();
                                System.out.println("Introduceti afectiunea pe care doriti sa o adaugati");
                                String afect = scan.next();

                                pacienti = p.adaugaAfectiune(id, pacienti, afect);

                                System.out.println("====================================================");

                                ReadWriteService.scriereCSV(pacientiPath, Pacient.returnHeader(), Pacient.listToCSV(pacienti));
                                break;
                            }
                            case 4:
                            {
                                matrPacienti = ReadWriteService.citireCSV(pacientiPath);
                                pacienti = Pacient.getListFromCSV(matrPacienti);

                                System.out.println("Pentru a sterge una din afectiunile unui pacient, introduceti intai datele despre pacient");
                                System.out.println("ID: ");
                                int id = scan.nextInt();
                                System.out.println("Nume: ");
                                String nume = scan.next();
                                System.out.println("Prenume");
                                String prenume = scan.next();

                                System.out.println("Introduceti afectiunea pe care doriti sa o stergeti");
                                String afect = scan.next();

                                pacienti = pacientService.stergeAfectiune(id, pacienti, afect);

                                ReadWriteService.scriereCSV(pacientiPath, Pacient.returnHeader(), Pacient.listToCSV(pacienti));

                                break;
                            }
                            case 5:
                            {
                                System.out.println("Pentru a face update la varsta a unui pacient, introduceti id-ul aferent pacientului");
                                System.out.println("Introduceti id-ul");
                                int id = scan.nextInt();
                                System.out.println("Introduceti data de nastere in formatul ll/dd/yyyy");
                                String data = scan.next();
                                System.out.println("Introduceti noua varsta");
                                int varsta = scan.nextInt();
                                try{
                                    stmt = connObj.createStatement();
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
                                break;
                            }
                            case 6:{
                                try{
                                    System.out.println("Pentru a sterge un pacient din baza de date, introduceti id-ul pacientului");
                                    System.out.println("Introduceti id-ul");
                                    int id = scan.nextInt();
                                    stmt = connObj.createStatement();
                                    String sql = "DELETE FROM Pacienti " +
                                            "WHERE id = " + id;
                                    stmt.executeUpdate(sql);
                                }
                                catch(SQLException se){
                                    se.printStackTrace();
                                }

                            break;
                            }
                        }
                        System.out.println("Pentru a continua interogarile, introduceri una din comenzile mai sus. Daca doriti sa iesiti din aceasta sectiune, introduceti 0.");
                        y = scan.nextInt();
                    }
                }
                break;
                case 2: {
                    System.out.println("Bun venit la categoria medici.");
                    System.out.println("Pentru  a afisa lista de medici, introduceti 1.");
                    System.out.println("Pentru a adauga un medic in lista, introduceti 2");
                    System.out.println("Pentru a actualiza specializarea unui medic, introduceti 3");
                    System.out.println("Pentru a actualiza varsta unui medic, intrduceti 4");
                    System.out.println("Pentru a afisa intervalul orar al unui medic, introduceti 5");
                    System.out.println("Pentru a sterge un medic din baza de date, introduceti 6");

                    int y = scan.nextInt();
                    while (y != 0) {
                        switch (y) {
                            case 1: {
                                try{
                                    System.out.println("Lista de medici existenta in sistem este:");
                                    matrMedici = ReadWriteService.citireCSV(mediciPath);
                                    medici = Medic.getListFromCSV(matrMedici);
                                    /*medicService.afiseazaMedici(medici);*/
                                    stmt = connObj.createStatement();

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


                                        //Display values
                                        System.out.println("ID: " + id);

                                        System.out.println("FirstName: " + first);
                                        System.out.println("LastName: " + last);
                                        System.out.println("DataNasterii: " + data);
                                        System.out.println("Varsta: " + varsta);
                                        System.out.println("Gen: " + gen);
                                        System.out.println("Specializare: " + specializare);
                                        System.out.println("OraStart: " + oraStart);
                                        System.out.println("OraEnd: " + oraEnd);
                                        System.out.println("CodParafa: " +CodParafa);

                                    }
                                    rs.close();

                                }
                                catch(SQLException se){

                                }



                                break;
                            }
                            case 2: {
                                try{
                                    stmt = connObj.createStatement();
                                    matrMedici = ReadWriteService.citireCSV(mediciPath);
                                    medici = Medic.getListFromCSV(matrMedici);

                                    System.out.println("Pentru a adauga un medic la lista introduceti datele despre medic:");
                                    System.out.println("ID: ");
                                    int id = scan.nextInt();
                                    System.out.println("Nume: ");
                                    String nume = scan.next();
                                    System.out.println("Prenume");
                                    String prenume = scan.next();
                                    System.out.println("Data nasterii, sub forma ll/dd/aaaa");
                                    String dataNasterii = scan.next();
                                    System.out.println("Varsta: ");
                                    int varsta = scan.nextInt();
                                    System.out.println("Gen:");
                                    String gen = scan.next();
                                    System.out.println("Ora la care incepe:");
                                    int start = scan.nextInt();
                                    System.out.println("Ora la care se opreste:");
                                    int stop = scan.nextInt();
                                    System.out.println("Specializare");
                                    String spec = scan.next();
                                    System.out.println("Cod parafa(acesta nu va putea incepe cu 0 si nu va avea mai mult de 7 cifre):");
                                    int cod = scan.nextInt();


                                    Medic M = medicService.creareMedic(id, nume, prenume, dataNasterii, varsta, gen, spec, start, stop, cod);
                                    System.out.println("Medicul adaugat: ");
                                    System.out.println(M.toString());
                                    medici.add(M);
                                    String sql  = "INSERT INTO Medici " + "VALUES(" + id + ",'" + nume + "','" + prenume + "','" + dataNasterii + "'," + varsta + ",'" + gen + "','" + spec + "'," + start + ","  + stop + "," + cod +")";
                                    stmt.executeUpdate(sql);
                                }
                                catch(SQLException se){

                                }



                                //ReadWriteService.scriereCSV(mediciPath, Medic.returnHeader(), Medic.listToCSV(medici));

                                break;
                            }
                            case 3: {
                                try{

                                    matrMedici = ReadWriteService.citireCSV(mediciPath);
                                    medici = Medic.getListFromCSV(matrMedici);

                                    System.out.println("Pentru a actualiza specializarea unui medic introduceti datele despre medic:");
                                    System.out.println("ID: ");
                                    int id = scan.nextInt();


                                    System.out.println("Introduceti noua specializare");
                                    String newSpecializare = scan.next();
                                    medici = medicService.updateSpecialiare(id, medici, newSpecializare);
                                    stmt = connObj.createStatement();
                                    String sql = "UPDATE Medici " +
                                            "SET Specializare ='" + newSpecializare + "' where Id=" + id;
                                    stmt.executeUpdate(sql);
                                }
                                catch(SQLException se){

                                }

                                //ReadWriteService.scriereCSV(mediciPath, Medic.returnHeader(), Medic.listToCSV(medici));

                                break;
                            }
                            case 4: {
                                try{
                                    matrMedici = ReadWriteService.citireCSV(mediciPath);
                                    medici = Medic.getListFromCSV(matrMedici);

                                    System.out.println("Pentru a actualiza varsta unui medic introduceti datele despre medic:");
                                    System.out.println("ID: ");
                                    int id = scan.nextInt();


                                    System.out.println("Introduceti noua varsta");
                                    int newVarsta = scan.nextInt();
                                    System.out.println("Introduceti noua data de nastere");
                                    String newDataNasterii = scan.next();
                                    medici = medicService.updateVarsta(id, medici, newVarsta, newDataNasterii);

                                    stmt = connObj.createStatement();
                                    String sql = "UPDATE Medici " +
                                            "SET Varsta =" + newVarsta + " where Id=" + id;
                                    stmt.executeUpdate(sql);
                                    sql = "UPDATE Medici " +
                                            "SET DataNasterii ='" + newDataNasterii + "' where Id=" + id;
                                    stmt.executeUpdate(sql);
                                    ReadWriteService.scriereCSV(mediciPath, Medic.returnHeader(), Medic.listToCSV(medici));
                                }

                                catch(SQLException se){

                                }

                                break;
                            }
                            case 5: {
                                try{
                                    matrMedici = ReadWriteService.citireCSV(mediciPath);
                                    medici = Medic.getListFromCSV(matrMedici);

                                    System.out.println("Pentru a afisa intervalul orar in care este medicul la cabinet, introduceti urmatoarele date:");
                                    System.out.println("ID: ");
                                    int id = scan.nextInt();


                                    /*String intervalOrar = medicService.afiseazaIntervalOrar(nume, prenume, medici);
                                    System.out.println("Medicul: " + nume + " " + prenume + " lucreaza in intervalul orar urmator");
                                    System.out.println(" " + intervalOrar);*/
                                    stmt = connObj.createStatement();
                                    String sql = "SELECT LastName,FirstName,OraStart, OraEnd FROM Medici" +
                                            " WHERE id =" + id;
                                    ResultSet rs = stmt.executeQuery(sql);
                                    while(rs.next()){
                                    String nume = rs.getString("LastName");
                                    String prenume = rs.getString("FirstName");
                                    float oraStart = rs.getFloat("OraStart");
                                    float oraStop = rs.getFloat("OraEnd");
                                        System.out.println("Medicul " + nume + " " + prenume + " lucreaza in intervalul orar " + oraStart + "-" + oraStop);
                                    }
                                }
                                catch (SQLException se){
                                    se.printStackTrace();
                                }

                                break;

                            }
                            case 6:{
                                try{
                                    System.out.println("Pentru a sterge un medic din baza de date, introduceti id-ul medicului");
                                    System.out.println("Introduceti id-ul");
                                    int id = scan.nextInt();
                                    stmt = connObj.createStatement();
                                    String sql = "DELETE FROM Medici " +
                                            "WHERE id = " + id;
                                    stmt.executeUpdate(sql);
                                    System.out.println("Delete completed");
                                }
                                catch(SQLException se){
                                    se.printStackTrace();
                                }
                                break;
                            }
                        }
                        System.out.println("Pentru a continua interogarile, introduceri una din comenzile mai sus. Daca doriti sa iesiti din aceasta sectiune, introduceti 0.");
                        y = scan.nextInt();
                    }

                }
                break;
                case 3: {
                    System.out.println("Bine ati venit in sectiunea de asistenti!");
                    System.out.println("Pentru a afla lista de asistenti, introduceti 1.");
                    System.out.println("Pentru a adauga un asistent la lista, introduceti 2.");
                    System.out.println("Pentru a modifica specializarea unui asistent, introduceti 3.");
                    System.out.println("Pentru a afla programul de lucru al unui asistent, introduceti 4");
                    System.out.println("Pentru a gasi un asistent cu o anumita specializare, introduceti 5.");
                    System.out.println("Pentru a sterge un asistent din baza de date, introduceti 6.");

                    int y = scan.nextInt();
                    while (y != 0) {
                        switch (y) {
                            case 1: {
                            System.out.println("Lista asistentilor deja existenti in sistem este:");
                            matrAsistenti = ReadWriteService.citireCSV(asistentiPath);
                            listaAsistenti = Asistent.getListFromCSV(matrAsistenti);
                            asistentService.afiseazaAsistenti(listaAsistenti);

                            }break;
                            case 2:{
                                matrAsistenti = ReadWriteService.citireCSV(asistentiPath);
                                listaAsistenti = Asistent.getListFromCSV(matrAsistenti);

                                System.out.println("Pentru a adauga un asistent in baza de date, introduceti urmatoarele date:\n");
                                System.out.println("ID: ");
                                int id = scan.nextInt();
                                System.out.println("Nume: ");
                                String nume = scan.next();
                                System.out.println("Prenume");
                                String prenume = scan.next();
                                System.out.println("Data nasterii, sub forma ll/dd/aaaa");
                                String dataNasterii = scan.next();
                                System.out.println("Varsta: ");
                                int varsta = scan.nextInt();
                                System.out.println("Gen:");
                                String gen = scan.next();
                                System.out.println("Specializare:");
                                String spec = scan.next();
                                System.out.println("Ora de inceput:");
                                double start = scan.nextDouble();
                                System.out.println("Ora de sfarsit:");
                                double stop = scan.nextDouble();
                                System.out.println("Lucreaza in ture? se va completa cu true sau false");
                                boolean ture = scan.nextBoolean();

                                Asistent a = new Asistent();
                                a = asistentService.creareAsistent(id, nume, prenume, dataNasterii, varsta, gen, spec, start, stop, ture);
                                listaAsistenti.add(a);

                                ReadWriteService.scriereCSV(asistentiPath, Asistent.returnHeader(), Asistent.listToCSV(listaAsistenti));

                            }break;

                            case 3:{
                                matrAsistenti = ReadWriteService.citireCSV(asistentiPath);
                                listaAsistenti = Asistent.getListFromCSV(matrAsistenti);

                                System.out.println("Pentru a modifica/actualiza specializarea unui asistent, introduceti intai datele despre acesta:");
                                System.out.println("ID: ");
                                int id = scan.nextInt();
                                System.out.println("Nume: ");
                                String nume = scan.next();
                                System.out.println("Prenume");
                                String prenume = scan.next();
                                System.out.println("Introduceti noua specializare");
                                String newSpecializare = scan.next();

                                listaAsistenti = asistentService.updateSpecializare(id, listaAsistenti, newSpecializare);
                                ReadWriteService.scriereCSV(asistentiPath, Asistent.returnHeader(), Asistent.listToCSV(listaAsistenti));


                            }break;
                            case 4:{
                                matrAsistenti = ReadWriteService.citireCSV(asistentiPath);
                                listaAsistenti = Asistent.getListFromCSV(matrAsistenti);

                                System.out.println("Pentru a afisa intervalul orar in care este asistentul la cabinet, introduceti urmatoarele date:");
                                System.out.println("ID: ");
                                int id = scan.nextInt();
                                System.out.println("Nume");
                                String nume = scan.next();
                                System.out.println("Prenume");
                                String prenume = scan.next();

                                String intervalOrar = asistentService.afiseazaProgram(nume, prenume, listaAsistenti);
                                System.out.println("Asistentul  " + nume + " " + prenume + " lucreaza in intervalul orar urmator");
                                System.out.println(intervalOrar);
                            }break;
                            case 5:{
                                matrAsistenti = ReadWriteService.citireCSV(asistentiPath);
                                listaAsistenti = Asistent.getListFromCSV(matrAsistenti);

                                System.out.println("Pentru a gasi un asistent cu o anumita specializare, introduceti numele specializarii:");
                                String spec = scan.next();
                                Asistent a = asistentService.getAsistentBySpecializare(listaAsistenti, spec);
                                System.out.println("Asistentul cu specializarea " + spec + " este:");
                                System.out.println(a.toString());

                            }break;
                        }
                        System.out.println("Pentru a continua interogarile, introduceri una din comenzile mai sus. Daca doriti sa iesiti din aceasta sectiune, introduceti 0.");
                        y = scan.nextInt();
                    }
                }
                    break;
                case 4: {
                    System.out.println("Bine ati venit la categoria Retete. Introduceti una din comenzile de mai jos:");
                    System.out.println("Pentru a afisa lista retetelor deja existente in sistem, introduceti 1");
                    System.out.println("Pentru a adauga o reteta introduceti 2");
                    System.out.println("Pentru a afisa lista de medicamente a unui pacienti, introduceti 3");
                    System.out.println("Pentru a modifica data eliberarii unei etete, introduceti 4");
                    System.out.println("Pentru a sterge o reteta din baza de date, introduceti 5");


                    int y = scan.nextInt();
                    while(y != 0){
                        switch(y){
                            case 1:{
                                System.out.println("Lista de retete existenta este:");
                                matrRetete = ReadWriteService.citireCSV(retetePath);
                                listaRetete = Reteta.getListFromCSV(matrRetete);
                                retetaService.afiseazaRetete(listaRetete);
                            }break;
                            case 2:{
                                System.out.println("Pentru a adauga o reteta in sistem, introduceti urmatoarele date");
                                matrRetete = ReadWriteService.citireCSV(retetePath);
                                listaRetete = Reteta.getListFromCSV(matrRetete);

                                System.out.println("Intai introduceti date despre pacient");

                                System.out.println("ID:");
                                int id = scan.nextInt();
                                System.out.println("Nume: ");
                                String nume = scan.next();
                                System.out.println("Prenume");
                                String prenume = scan.next();
                                System.out.println("Data nasterii, sub forma ll/dd/aaaa");
                                String dataNasterii = scan.next();
                                System.out.println("Varsta: ");
                                int varsta = scan.nextInt();
                                System.out.println("Gen:");
                                String gen = scan.next();
                                int nr;
                                System.out.println("Numarul de afectiuni pe care le are pacientul: ");
                                nr = scan.nextInt();
                                List<String> listaAfectiuni = new ArrayList<String>();
                                for(int i = 0;  i<  nr; i++)
                                {
                                    System.out.println("Introduceti afectiunea: ");
                                    String afect = scan.next();
                                    listaAfectiuni.add(afect);
                                }
                                Pacient pacient = new Pacient();
                                pacient = pacientService.crearePacient(id, nume,prenume,dataNasterii,varsta, gen, listaAfectiuni);
                                System.out.println("Reteta a fost eliberata de ");
                                System.out.println("Nume:");
                                String numeDoctor = scan.next();
                                System.out.println("Prenume:");
                                String prenumeDoctor = scan.next();
                                numeDoctor+= " " + prenumeDoctor;
                                System.out.println("La data de: ");
                                String eliberataLa = scan.next();
                                System.out.println("Numarul de medicamente prescrise: ");
                                int nrMed = scan.nextInt();
                                Map<String, Integer> medicamente = new HashMap<>();
                                List<String> listaMed = new ArrayList<>();
                                for(int i = 0; i < nrMed; i++)
                                {
                                    System.out.println("Introduceti medicamentul: ");
                                    String med = scan.next();
                                    medicamente.put(med, i+1);
                                    listaMed.add(med);
                                }
                                Reteta r = retetaService.creareReteta(pacient, numeDoctor, eliberataLa, medicamente);
                                listaRetete.add(r);
                                ReadWriteService.scriereCSV(retetePath, Reteta.returnHeader(), Reteta.listToCSV(listaRetete));

                            } break;
                            case 3:{
                                matrRetete = ReadWriteService.citireCSV(retetePath);
                                listaRetete = Reteta.getListFromCSV(matrRetete);
                                System.out.println("Pentru a afisa lista de medicamente a unui pacient, introduceti datele despre acesta");
                                System.out.println("ID:");
                                int id = scan.nextInt();
                                System.out.println("Nume: ");
                                String nume = scan.next();
                                System.out.println("Prenume");
                                String prenume = scan.next();
                                Map<String, Integer> medicamente = new HashMap<>();
                                List<String> listaMed = new ArrayList<>();
                                for(int i = 0; i < listaRetete.size(); i++)
                                {
                                    if(listaRetete.get(i).getPacient().getId() == id )
                                        medicamente = retetaService.getMedicamente(listaRetete.get(i));
                                    break;
                                }
                                System.out.println("Medicamentele pentru pacientul " + nume + " " + prenume + " sunt:");
                                System.out.println(Reteta.parseMapToString(medicamente));

                            }break;

                            }
                        System.out.println("Pentru a continua interogarile in aceasta categorie, introduceti una din comenzile mai sus. Daca doriti sa iesiti din aceasta sectiune, introduceti 0.");
                        y = scan.nextInt();
                    }
                    }
                    break;
                case 5: {
                        System.out.println("Bine ati venit la categoria Trimiteri Medicale. Introduceti una din comenzile de mai jos:");
                        System.out.println("Pentru a afisa lista de trimiteri, introduceti 1");
                        System.out.println("Pentru a modifica scopul unei trimiteri, introduceti 2");
                        System.out.println("Pentru a modifica valabilitatea unei trimiteri, introduceti 3");
                        System.out.println("Pentru a adauga o noua trimitere in sistem, introduceti 4");
                        System.out.println("Pentru a obtine scopul unei trimiteri pentru un anumit pacient, introduceti 5");
                        System.out.println("Pentru a sterge o trimitere medicala din baza de date, introduceti 6");
                        int y = scan.nextInt();
                        while( y!= 0){
                            switch(y){
                                case 1:{
                                    System.out.println("Lista de trimiteri medicale este:");
                                    matrTrimiteri = ReadWriteService.citireCSV(trimiteriPath);
                                    listaTrimiteri = TrimitereMedicala.getListFromCSV(matrTrimiteri);
                                    trimitereService.afiseazaTrimiteri(listaTrimiteri);

                                }break;
                                case 2:{
                                    System.out.println("Pentru a updata scopul unei trimiteri, introduceti datele necesare");
                                    System.out.println("ID:");
                                    int id = scan.nextInt();
                                    System.out.println("Nume:");
                                    String nume = scan.next();
                                    System.out.println("Prenume:");
                                    String prenume = scan.next();

                                    matrTrimiteri = ReadWriteService.citireCSV(trimiteriPath);
                                    listaTrimiteri = TrimitereMedicala.getListFromCSV(matrTrimiteri);

                                    System.out.println("Introduceti noul scop al trimiterii:");
                                    String scop = scan.next();

                                    listaTrimiteri = trimitereService.updateScop(id, listaTrimiteri, scop);
                                    ReadWriteService.scriereCSV(trimiteriPath, TrimitereMedicala.returnHeader(), TrimitereMedicala.listToCSV(listaTrimiteri));

                                }break;
                                case 3:{
                                    System.out.println("Pentru a updata valabilitatea unei trimiteri, introduceti datele necesare");
                                    System.out.println("ID:");
                                    int id = scan.nextInt();
                                    System.out.println("Nume:");
                                    String nume = scan.next();
                                    System.out.println("Prenume:");
                                    String prenume = scan.next();

                                    matrTrimiteri = ReadWriteService.citireCSV(trimiteriPath);
                                    listaTrimiteri = TrimitereMedicala.getListFromCSV(matrTrimiteri);

                                    System.out.println("Introduceti noua valabilitate a trimiterii:");
                                    int val = scan.nextInt();

                                    listaTrimiteri = trimitereService.updateValabilitate(id, listaTrimiteri, val);
                                    ReadWriteService.scriereCSV(trimiteriPath, TrimitereMedicala.returnHeader(), TrimitereMedicala.listToCSV(listaTrimiteri));

                                }break;
                                case 4:{
                                    matrTrimiteri = ReadWriteService.citireCSV(trimiteriPath);
                                    listaTrimiteri = TrimitereMedicala.getListFromCSV(matrTrimiteri);

                                    System.out.println("Pentru a adauga o noua trimitere in sistem, introduceti datele necesare");

                                    System.out.println("ID: ");
                                    int id = scan.nextInt();
                                    System.out.println("Nume: ");
                                    String nume = scan.next();
                                    System.out.println("Prenume");
                                    String prenume = scan.next();
                                    System.out.println("Data nasterii, sub forma ll/dd/aaaa");
                                    String dataNasterii = scan.next();
                                    System.out.println("Varsta: ");
                                    int varsta = scan.nextInt();
                                    System.out.println("Gen:");
                                    String gen = scan.next();
                                    int nr;
                                    System.out.println("Numarul de afectiuni pe care le are pacientul: ");
                                    nr = scan.nextInt();
                                    List<String> listaAfectiuni = new ArrayList<String>();
                                    for(int i = 0;  i<  nr; i++)
                                    {
                                        System.out.println("Introduceti afectiunea: ");
                                        String afect = scan.next();
                                        listaAfectiuni.add(afect);
                                    }
                                    Pacient p = pacientService.crearePacient(id, nume,prenume,dataNasterii,varsta, gen, listaAfectiuni);
                                    System.out.println("Valabilitate:");
                                    int val = scan.nextInt();
                                    System.out.println("Scop");
                                    String scop = scan.next();
                                    System.out.println("Catre");
                                    String catre = scan.next();
                                    System.out.println("Eliberate de catre:");
                                    System.out.println("Nume doctor:");
                                    String numeDoc = scan.next();
                                    System.out.println("Prenume doctor");
                                    String prenumeDoc = scan.next();
                                    numeDoc += " " + prenumeDoc;
                                    System.out.println("Eliberat la data de:");
                                    String eliberatLa = scan.next();

                                    TrimitereMedicala tm = trimitereService.creareTrimitere(p, numeDoc, eliberatLa, val, scop, catre);
                                    listaTrimiteri.add(tm);
                                    ReadWriteService.scriereCSV(trimiteriPath, TrimitereMedicala.returnHeader(), TrimitereMedicala.listToCSV(listaTrimiteri));

                                }break;
                                case 5:{
                                    System.out.println("Pentru a afla scopul pentru care a fost emisa o trimitere medicala pe numele unui anumit pacien, introduceti datale despre acesta");
                                    System.out.println("ID:");
                                    int id = scan.nextInt();
                                    System.out.println("Nume:");
                                    String nume = scan.next();
                                    System.out.println("Prenume:");
                                    String prenume = scan.next();

                                    matrTrimiteri = ReadWriteService.citireCSV(trimiteriPath);
                                    listaTrimiteri = TrimitereMedicala.getListFromCSV(matrTrimiteri);

                                    String scop = trimitereService.obtineScop(id, listaTrimiteri);
                                    System.out.println("Trimiterea medicala pentru pacientul " + nume + " " + prenume + " este pentru: " + scop);

                                }break;
                            }
                            System.out.println("Pentru a continua interogarile, introduceri una din comenzile mai sus. Daca doriti sa iesiti din aceasta sectiune, introduceti 0.");
                            y = scan.nextInt();
                        }

                    }
                    break;
                case 6: {
                        System.out.println("Bine ati venit la categoria Concedii Medicale. Introduceti una din comenzile de mai jos:");
                    System.out.println("Pentru a afisa lista de concedii medicale, introduceti 1");
                    System.out.println("Pentru a adauga un nou concediu, introduceti 2");
                    System.out.println("Pentru a modifica valabilitatea unui concediu medical, introduceti 3");

                    System.out.println("Pentru a sterge un concediu medical din baza de date, introduceti 4");
                    }
                case 7: {
                        System.out.println("Bine ati venit la categoria Adeverinte medicale. Introduceti una din comenzile de mai jos:");
                        System.out.println("Pentru a vizualiza adeverintele medicale din baza de date, introduceti 1");
                        System.out.println("Pentru a modifica daca pacientul figureaza apt sau nu, introduceti 2");
                        System.out.println("Pentru a adauga o noua adeverinta medicala, introduceti 3");
                        System.out.println("Pentru a modifica scopul unei adeverinte, introduceti 4");
                        System.out.println("Pentru a sterge o adeverinta medicala din baza de date, introduceti 5");
                    }
                    break;
                case 8: {
                        System.out.println("Bine ati venit la categoria Cabinet Medical. Introduceti una din comenzile de mai jos:");
                        System.out.println("Pentru a vizualiza datele despre cabinetul medical, introduceti 1");
                        System.out.println("Pentru a modifica adresa la care se gaseste cabinetul medical, introduceti 2");
                    }
                    break;
                }
                System.out.println("Daca doriti sa continuati interogarile cu alte categorii, introduceti una din comenzile prezentate mai sus. In caz contrar, introduceti 0.");
                x = scan.nextInt();
            }
        }
    }
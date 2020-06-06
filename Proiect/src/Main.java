import Entities.*;
import Helpers.FileHelper;
import Helpers.ReadWriteService;
import Services.Implementations.*;
import Services.Interfaces.PacientInterface;
import com.opencsv.CSVWriter;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import org.omg.Messaging.SYNC_WITH_TRANSPORT;
import com.opencsv.CSVReader;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.*;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.*;
import java.util.*;
import java.util.List;

import javafx.scene.layout.VBox;

import javax.swing.text.TabExpander;

public class Main extends Application implements EventHandler<ActionEvent> {

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



    public static void main(String[] args) throws IOException  {

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
        launch(args);
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
                                pacientService.updateVarstaPacientDb(connObj, id, varsta, data);
                                break;
                            }
                            case 6:{
                                System.out.println("Pentru a sterge un pacient din baza de date, introduceti id-ul pacientului");
                                System.out.println("Introduceti id-ul");
                                int id = scan.nextInt();
                                pacientService.deletePacientFromDb(connObj, id);

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
                                System.out.println("Lista de medici existenta in sistem este:");
                                matrMedici = ReadWriteService.citireCSV(mediciPath);
                                medici = Medic.getListFromCSV(matrMedici);
                                /*medicService.afiseazaMedici(medici);*/

                                medicService.afiseazaMedici(medicService.getAllFromDb(connObj));

                                break;
                            }
                            case 2: {
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
                                medicService.adaugaMedicDb(connObj, M);
                                ReadWriteService.scriereCSV(mediciPath, Medic.returnHeader(), Medic.listToCSV(medici));

                                break;
                            }
                            case 3: {
                                matrMedici = ReadWriteService.citireCSV(mediciPath);
                                medici = Medic.getListFromCSV(matrMedici);

                                System.out.println("Pentru a actualiza specializarea unui medic introduceti datele despre medic:");
                                System.out.println("ID: ");
                                int id = scan.nextInt();


                                System.out.println("Introduceti noua specializare");
                                String newSpecializare = scan.next();
                                medici = medicService.updateSpecialiare(id, medici, newSpecializare);
                                medicService.updateSpecializareMedicDb(connObj, id, newSpecializare);

                                ReadWriteService.scriereCSV(mediciPath, Medic.returnHeader(), Medic.listToCSV(medici));

                                break;
                            }
                            case 4: {
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
                                medicService.updateVarstaMedicDb(connObj, id,newVarsta, newDataNasterii);
                                break;
                            }
                            case 5: {
                                //matrMedici = ReadWriteService.citireCSV(mediciPath);
                                //medici = Medic.getListFromCSV(matrMedici);

                                System.out.println("Pentru a afisa intervalul orar in care este medicul la cabinet, introduceti urmatoarele date:");
                                System.out.println("ID: ");
                                int id = scan.nextInt();
                                medicService.getOrarDb(connObj, id);

                                break;

                            }
                            case 6:{
                                System.out.println("Pentru a sterge un medic din baza de date, introduceti id-ul medicului");
                                System.out.println("Introduceti id-ul");
                                int id = scan.nextInt();
                               medicService.deleteMedicFromDb(connObj, id);
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
                            asistentService.afiseazaAsistenti(asistentService.getAllFromDb(connObj));



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
                                asistentService.adaugaAsistentcDb(connObj, a);

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
                                asistentService.updateSpecializareAsistentDb(connObj, id, newSpecializare);


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

                                asistentService.getOrarAsistentDb(connObj, id);
                            }break;
                            case 5:{
                                matrAsistenti = ReadWriteService.citireCSV(asistentiPath);
                                listaAsistenti = Asistent.getListFromCSV(matrAsistenti);

                                System.out.println("Pentru a gasi un asistent cu o anumita specializare, introduceti numele specializarii:");
                                String spec = scan.next();
                                //Asistent a = asistentService.getAsistentBySpecializare(listaAsistenti, spec);
                                Asistent a = asistentService.getAsistentBySpecializareDb(connObj, spec);
                                System.out.println("Asistentul cu specializarea " + spec + " este:");
                                System.out.println(a.toString());


                            }break;
                            case 6:{
                                System.out.println("Pentru a sterge un asistent din baza de date introduceti id-ul aferent asistentului");
                                System.out.println("ID: ");
                                int id = scan.nextInt();
                                medicService.deleteMedicFromDb(connObj, id);
                                break;
                            }
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
                    System.out.println("Pentru a modifica data eliberarii unei retete, introduceti 4");
                    System.out.println("Pentru a sterge o reteta din baza de date, introduceti 5");


                    int y = scan.nextInt();
                    while(y != 0){
                        switch(y){
                            case 1:{
                                System.out.println("Lista de retete existenta este:");
                                matrRetete = ReadWriteService.citireCSV(retetePath);
                                listaRetete = Reteta.getListFromCSV(matrRetete);
                                retetaService.afiseazaRetete(retetaService.getAllFromDb(connObj));

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
                                System.out.println("Reteta a fost eliberata de: ");
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
                                retetaService.createRetetaForDb(connObj, r);

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
                            case 4:{
                                System.out.println("Pentru a modifica data eliberarii unei retete, introduceti id-ul reteti si noua data de eliberare:");
                                System.out.println("ID:");
                                int id = scan.nextInt();
                                System.out.println("Noua data de eliberare, sub forma mm/dd/yyyy");
                                String data = scan.next();
                                retetaService.modificaEliberareRetetaDb(connObj, id, data);
                                break;
                            }
                            case 5:{
                                System.out.println("Pentru a sterge o reteta din baza de date, introduceti id-ul retetei respective:");
                                int id = scan.nextInt();
                                retetaService.deleteRetetaFromDb(connObj, id);
                                break;
                            }

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
                                    trimitereService.afiseazaTrimiteri(trimitereService.getAllTrimiteriFromDb(connObj));

                                }break;
                                case 2:{
                                    System.out.println("Pentru a updata scopul unei trimiteri, introduceti datele necesare");
                                    System.out.println("ID:");
                                    int id = scan.nextInt();


                                    matrTrimiteri = ReadWriteService.citireCSV(trimiteriPath);
                                    listaTrimiteri = TrimitereMedicala.getListFromCSV(matrTrimiteri);

                                    System.out.println("Introduceti noul scop al trimiterii:");
                                    String scop = scan.next();

                                    listaTrimiteri = trimitereService.updateScop(id, listaTrimiteri, scop);
                                    ReadWriteService.scriereCSV(trimiteriPath, TrimitereMedicala.returnHeader(), TrimitereMedicala.listToCSV(listaTrimiteri));

                                    trimitereService.modificareScopDb(connObj, id, scop);

                                }break;
                                case 3:{
                                    System.out.println("Pentru a updata valabilitatea unei trimiteri, introduceti datele necesare");
                                    System.out.println("ID:");
                                    int id = scan.nextInt();


                                    matrTrimiteri = ReadWriteService.citireCSV(trimiteriPath);
                                    listaTrimiteri = TrimitereMedicala.getListFromCSV(matrTrimiteri);

                                    System.out.println("Introduceti noua valabilitate a trimiterii:");
                                    int val = scan.nextInt();

                                    listaTrimiteri = trimitereService.updateValabilitate(id, listaTrimiteri, val);
                                    ReadWriteService.scriereCSV(trimiteriPath, TrimitereMedicala.returnHeader(), TrimitereMedicala.listToCSV(listaTrimiteri));

                                    trimitereService.modificareValabilitateDb(connObj, id, val);

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

                                    TrimitereMedicala tm = trimitereService.creareTrimitere( p, numeDoc, eliberatLa, val, scop, catre);
                                    listaTrimiteri.add(tm);
                                    ReadWriteService.scriereCSV(trimiteriPath, TrimitereMedicala.returnHeader(), TrimitereMedicala.listToCSV(listaTrimiteri));

                                    trimitereService.creareTrimitereDb(connObj, tm);

                                }break;
                                case 5:{
                                    System.out.println("Pentru a afla scopul pentru care a fost emisa o trimitere medicala pe numele unui anumit pacien, introduceti datale despre acesta");
                                    System.out.println("ID:");
                                    int id = scan.nextInt();

                                    matrTrimiteri = ReadWriteService.citireCSV(trimiteriPath);
                                    listaTrimiteri = TrimitereMedicala.getListFromCSV(matrTrimiteri);

                                    trimitereService.selectScopDb(connObj, id);


                                }break;
                                case 6:{
                                    System.out.println("Pentru a sterge o trimitere medicala din baza de date, introduceti id-ul trimiterii");
                                    int id = scan.nextInt();
                                    trimitereService.deleteTrimitereMedicalaDb(connObj, id);
                                    break;
                                }
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

                    int y = scan.nextInt();
                    while( y!= 0) {
                        switch (y) {
                            case 1:{
                                System.out.println("Lista de concedii medicale este:");
                                concediuService.afiseazaConcedii(concediuService.getConcediiFromDb(connObj));
                                break;
                            }
                            case 2:{
                                System.out.println("Pentru a adauga un nu concediu medical in sistem, introduceti datele necesare");

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


                                System.out.println("Eliberate de catre:");
                                System.out.println("Nume doctor:");
                                String numeDoc = scan.next();
                                System.out.println("Prenume doctor");
                                String prenumeDoc = scan.next();
                                numeDoc += " " + prenumeDoc;
                                System.out.println("Eliberat la data de:");
                                String eliberatLa = scan.next();
                                System.out.println("Nr zile concediu:");
                                int nrZile = scan.nextInt();
                                System.out.println("Data de final");
                                String DataFinal = scan.next();

                                ConcediuMedical cm = concediuService.creareCerereConcediu(p,numeDoc, eliberatLa, nrZile, DataFinal);
                                concediuService.createConcediuDb(connObj, cm);

                                break;
                            }
                            case 3:{
                                System.out.println("Pentru a modifica valabilitatea unui concediu, introduceti datele necesare:");
                                System.out.println("ID:");
                                int id = scan.nextInt();
                                System.out.println("Numar zile concediu");
                                int nrZile = scan.nextInt();
                                System.out.println("Introduceti noua data de incheiere a concediului");
                                String data = scan.next();

                                concediuService.modificaValabilitateDb(connObj, id, nrZile, data);
                                break;
                            }
                            case 4:{
                                System.out.println("Pentru a sterge un concediu din baza de date, introduceti id-ul concediului");
                                int id = scan.nextInt();
                                concediuService.deleteConcediuDb(connObj, id);
                                break;
                            }

                        }
                        System.out.println("Pentru a continua interogarile, introduceri una din comenzile mai sus. Daca doriti sa iesiti din aceasta sectiune, introduceti 0.");
                        y = scan.nextInt();
                    }break;
                    }
                case 7: {
                        System.out.println("Bine ati venit la categoria Adeverinte medicale. Introduceti una din comenzile de mai jos:");
                        System.out.println("Pentru a vizualiza adeverintele medicale din baza de date, introduceti 1");

                        System.out.println("Pentru a adauga o noua adeverinta medicala, introduceti 2");
                        System.out.println("Pentru a modifica daca pacientul figureaza apt sau nu, introduceti 3");
                        System.out.println("Pentru a sterge o adeverinta medicala din baza de date, introduceti 4");
                    int y = scan.nextInt();
                    while( y!= 0) {
                        switch (y) {
                            case 1:{
                                System.out.println("Adeverintele medicale din baza de date sunt:");
                                adeverintaService.afiseazaAdeverinte(adeverintaService.getAdeverinteFromDb(connObj));
                                break;
                            }
                            case 2:{
                                System.out.println("Pentru a adauga o adeverinta medicala in baza de date, introduceti datele necesare:");
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


                                System.out.println("Eliberate de catre:");
                                System.out.println("Nume doctor:");
                                String numeDoc = scan.next();
                                System.out.println("Prenume doctor");
                                String prenumeDoc = scan.next();
                                numeDoc += " " + prenumeDoc;
                                System.out.println("Eliberat la data de:");
                                String eliberatLa = scan.next();
                                System.out.println("Scop");
                                String scop = scan.next();
                                System.out.println("Apt sau nu, se va completa cu true sau false");
                                boolean apt = scan.nextBoolean();

                                AdeverintaMedicala am = adeverintaService.creareAdeverinta(p, numeDoc, eliberatLa, apt, scop);
                                adeverintaService.createAdeverintaDb(connObj, am);
                                break;
                            }
                            case 3:{
                                System.out.println("Pentru a modifica daca pacientul este apt sau nu pt un anumit scop, introduceti id-ul si daca e apt sau nu");
                                int id = scan.nextInt();
                                System.out.println("Apt sau nu, se va completa cu true sau false");
                                boolean apt = scan.nextBoolean();
                                adeverintaService.modificaAptDb(connObj, id, apt);
                                break;
                            }
                            case 4:{
                                System.out.println("Pentru a strge o adeverinta medicala din baza de date, introduceti id-ul adeverintei:");
                                int id = scan.nextInt();
                                adeverintaService.deleteAdeverintaDb(connObj, id);
                                break;
                            }

                        }
                        System.out.println("Pentru a continua interogarile, introduceri una din comenzile mai sus. Daca doriti sa iesiti din aceasta sectiune, introduceti 0.");
                        y = scan.nextInt();
                    }
                    }
                    break;
                case 8: {
                        System.out.println("Bine ati venit la categoria Cabinet Medical. Introduceti una din comenzile de mai jos:");
                        System.out.println("Pentru a vizualiza datele despre cabinetul medical, introduceti 1");
                        System.out.println("Pentru a modifica adresa la care se gaseste cabinetul medical, introduceti 2");
                    int y = scan.nextInt();
                    while( y!= 0) {
                        switch (y) {
                            case 1:{
                                System.out.println("Datele despre cabinetul medical:");
                                cabinetService.getDetaliiCabinetFromDb(connObj);
                                break;
                            }
                            case 2:{
                                System.out.println("Pentru a modifica adresa la care se gaseste cabinetul medical, introduceti numele strazii");
                                String strada = scan.next();
                                cabinetService.updateStradaDb(connObj, strada);
                                break;
                            }
                        }
                        System.out.println("Pentru a continua interogarile, introduceri una din comenzile mai sus. Daca doriti sa iesiti din aceasta sectiune, introduceti 0.");
                        y = scan.nextInt();
                    }

                    }
                    break;
                }
                System.out.println("Daca doriti sa continuati interogarile cu alte categorii, introduceti una din comenzile prezentate mai sus. In caz contrar, introduceti 0.");
                x = scan.nextInt();
            }
        }
    Button button;
    Button button2;
    Button button3;
    Button button4;
    Button button5;
    Button button6;
    Button button7;
    Button button8;
    Button buttonP;
    Button buttonP2;
    Button buttonP3;
    Button buttonM;
    Button buttonM1;
    Button buttonM2;
    Button buttonCreatePacient;
    Button backToMeniu;
    Button backToMeniu1;
    Button backToMeniu2;
    Button backToMeniu3;
    Button backToMeniu4;
    Button backToMeniu5;
    Button backToMeniu6;
    Button backToMeniu7;
    Button showAsistenti;
    Button showRetete;
    Button showTrimiteri;
    Button showConcedii;

    private final GridPane grid = new GridPane();
    private TableView table = new TableView();
    private TableView tableM = new TableView();
    private TableView tableA = new TableView();
    private TableView tableR = new TableView();
    private TableView tableTM = new TableView();
    private TableView tableCM = new TableView();

    private final ObservableList<Pacient> data =
            FXCollections.observableArrayList(
                    pacientService.getAllFromDb(connObj)

            );
    private final ObservableList<Medic> dataM =
            FXCollections.observableArrayList(
                    medicService.getAllFromDb(connObj)
            );
    private final ObservableList<Medic> dataA =
            FXCollections.observableArrayList(
                    medicService.getAllFromDb(connObj)
            );
    private final ObservableList<Reteta> dataR =
            FXCollections.observableArrayList(
                    retetaService.getAllFromDb(connObj)
            );
    private final ObservableList<TrimitereMedicala> dataTM =
            FXCollections.observableArrayList(
                    trimitereService.getAllTrimiteriFromDb(connObj)
            );
    private final ObservableList<ConcediuMedical> dataCM =
            FXCollections.observableArrayList(
                    concediuService.getConcediiFromDb(connObj)
            );

    @Override
    public void start(Stage primaryStage) throws Exception {
        Stage window;
        button  = new Button();
        button.setText("Pacienti");



        button2 = new Button();
        button2.setText("Medici");


        button3 = new Button();
        button3.setText("Asistenti");
        button4 = new Button();
        button4.setText("Retete");
        button5 = new Button();
        button5.setText("Trimiteri Medicale");
        button6 = new Button();
        button6.setText("Concedii Medicale");
        button7 = new Button();
        button7.setText("Adeverinte Medicale");
        button8 = new Button();
        button8.setText("Cabinet Medical");


        HBox hbox = new HBox(button, button2, button3, button4, button5, button6, button7, button8);
        buttonP = new Button();
        buttonP.setText("Show all pacienti");

        buttonP2 = new Button();
        buttonP2.setText("Add pacient in the database");

        backToMeniu = new Button();
        backToMeniu.setText("Back to Main Menu");
        buttonP3 = new Button();
        buttonP3.setText("Delete Pacient");
        HBox hbox2 = new HBox(backToMeniu, buttonP, buttonP2, buttonP3);

        backToMeniu1 = new Button();
        backToMeniu1.setText("Back to Main Menu");
        backToMeniu2 = new Button();
        backToMeniu2.setText("Back to Main Menu");
        backToMeniu3 = new Button();
        backToMeniu3.setText("Back to Main Menu");
        backToMeniu4 = new Button();
        backToMeniu4.setText("Back to Main Menu");
        backToMeniu5 = new Button();
        backToMeniu5.setText("Back to Main Menu");
        backToMeniu6 = new Button();
        backToMeniu6.setText("Back to Main Menu");
        backToMeniu7 = new Button();
        backToMeniu7.setText("Back to Main Menu");

        buttonM = new Button();
        buttonM.setText("Show all doctors");
        buttonM1 = new Button();
        buttonM1.setText("Update doctor's age");
        buttonM2 = new Button();
        buttonM2.setText("Show working hours");


        HBox hbox3;

        hbox3 = new HBox(backToMeniu1, buttonM, buttonM1, buttonM2);

        showAsistenti = new Button();
        showAsistenti.setText("Back to meniu");
        showRetete = new Button();
        showRetete.setText("Back to meniu");
        showTrimiteri = new Button();
        showTrimiteri.setText("Back to meniu");
        showConcedii = new Button();
        showConcedii.setText("Back to meniu");


        window =  primaryStage;
        Scene scene = new Scene(hbox, 1000,200);
        Scene scene2 = new Scene(hbox2, 1000, 200);
        Scene scene3 = new Scene(hbox3, 1000, 200);
        button.setOnAction(e -> window.setScene(scene2));


        backToMeniu.setOnAction(e->window.setScene(scene));
        button2.setOnAction(e->window.setScene(scene3));
        backToMeniu1.setOnAction(e->window.setScene(scene));
        backToMeniu2.setOnAction(e->window.setScene(scene));
        backToMeniu3.setOnAction(e->window.setScene(scene));
        backToMeniu4.setOnAction(e->window.setScene(scene));
        backToMeniu5.setOnAction(e->window.setScene(scene));
        backToMeniu6.setOnAction(e->window.setScene(scene));
        backToMeniu7.setOnAction(e->window.setScene(scene));

        table.setEditable(true);

        TableColumn id = new TableColumn("Id Pacient");
        id.setMinWidth(100);
        id.setCellValueFactory(
                new PropertyValueFactory<Pacient, String>("id"));

        TableColumn lastNameCol = new TableColumn("Last Name");
        lastNameCol.setMinWidth(100);
        lastNameCol.setCellValueFactory(
                new PropertyValueFactory<Pacient, String>("nume"));

        TableColumn firstNameCol = new TableColumn("First Name");
        firstNameCol.setMinWidth(100);
        firstNameCol.setCellValueFactory(
                new PropertyValueFactory<Pacient, String>("prenume"));

        TableColumn dataNastere = new TableColumn("Data nasterii");
        dataNastere.setMinWidth(100);
        dataNastere.setCellValueFactory(
                new PropertyValueFactory<Pacient, String>("DataNasterii"));

        TableColumn varsta = new TableColumn("Varsta");
        varsta.setMinWidth(50);
        varsta.setCellValueFactory(
                new PropertyValueFactory<Pacient, String>("varsta"));
        TableColumn gen = new TableColumn("Gen");
        gen.setMinWidth(100);
        gen.setCellValueFactory(
                new PropertyValueFactory<Pacient, String>("gen"));
        TableColumn afectiuni = new TableColumn("Afectiuni");
        afectiuni.setMinWidth(200);
        afectiuni.setCellValueFactory(
                new PropertyValueFactory<Pacient, String>("afectiuni"));

        System.out.println(data);

        table.setItems(data);
        table.getColumns().addAll(id, firstNameCol, lastNameCol, dataNastere, varsta, gen, afectiuni);
        final Label label = new Label("List of pacients");

        final VBox vbox = new VBox();
        vbox.setSpacing(5);
        vbox.setPadding(new Insets(20, 0, 0, 10));
        vbox.getChildren().addAll(label, backToMeniu2, table);

        Scene sceneShowPacienti = new Scene(vbox, 1000,475);
        buttonP.setOnAction(e->window.setScene(sceneShowPacienti));


        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10,10,10,10));
        grid.setVgap(5);
        grid.setHgap(5);

        final Label labelCreate = new Label("Create a pacient and add it to the database");
        final VBox vboxCreateP = new VBox();
        vbox.setSpacing(5);
        vbox.setPadding(new Insets(20, 0, 0, 10));
        HBox hb = new HBox();
        hb.setSpacing(5);
        hb.setPadding(new Insets(20,0,0,10));
        Label label1 = new Label("ID:");
        TextField textField1 = new TextField ();
        GridPane.setConstraints(label1, 0,0);
        Label label2 = new Label("Last Name:");
        TextField textField2 = new TextField ();
        GridPane.setConstraints(label2, 0,1);
        Label label3 = new Label("First Name:");
        TextField textField3 = new TextField ();
        GridPane.setConstraints(label3, 1,0);
        Label label4 = new Label("Age:");
        TextField textField4 = new TextField ();
        GridPane.setConstraints(label4, 1,1);

        Label label5 = new Label("Birthday:");
        TextField textField5 = new TextField ();
        GridPane.setConstraints(label5, 2,0);
        Label label6 = new Label("Gendre:");
        TextField textField6 = new TextField ();
        GridPane.setConstraints(label6, 2,1);
        Label label7 = new Label("Afections:");
        TextField textField7 = new TextField ();
        GridPane.setConstraints(label7, 3,0);
        grid.getChildren().addAll( textField1, textField2, textField3, textField4, textField5,  textField6,textField7);

        //hb.getChildren().addAll(labelCreate, backToMeniu,label1, textField1);
          //      hb.getChildren().addAll(label2, textField2, label3, textField3,label4, textField4,label5, textField5, label6, textField6,label7,  textField7);

        Scene sceneCreatePacient = new Scene(grid, 1000, 500);
        buttonP2.setOnAction(e->window.setScene(sceneCreatePacient));
        buttonCreatePacient = new Button();
        buttonCreatePacient.setText("Add pacient");
        buttonCreatePacient.setOnAction(e->window.setTitle("Pacient added"));

        tableM.setEditable(true);
        TableColumn idM = new TableColumn("Id Pacient");
        idM.setMinWidth(100);
        idM.setCellValueFactory(
                new PropertyValueFactory<Medic, String>("id"));

        TableColumn lastNameColM = new TableColumn("Last Name");
        lastNameColM.setMinWidth(100);
        lastNameColM.setCellValueFactory(
                new PropertyValueFactory<Medic, String>("nume"));

        TableColumn firstNameColM = new TableColumn("First Name");
        firstNameCol.setMinWidth(100);
        firstNameColM.setCellValueFactory(
                new PropertyValueFactory<Medic, String>("prenume"));

        TableColumn dataNastereM = new TableColumn("Data nasterii");
        dataNastereM.setMinWidth(100);
        dataNastereM.setCellValueFactory(
                new PropertyValueFactory<Medic, String>("DataNasterii"));

        TableColumn varstaM = new TableColumn("Varsta");
        varstaM.setMinWidth(50);
        varstaM.setCellValueFactory(
                new PropertyValueFactory<Medic, String>("varsta"));
        TableColumn genM = new TableColumn("Gen");
        genM.setMinWidth(100);
        genM.setCellValueFactory(
                new PropertyValueFactory<Medic, String>("gen"));

        TableColumn specializareM = new TableColumn("Specializare");
        specializareM.setMinWidth(100);
        specializareM.setCellValueFactory(
                new PropertyValueFactory<Medic, String>("specializare"));

        TableColumn oraStartM = new TableColumn("ora Start");
        oraStartM.setMinWidth(100);
        oraStartM.setCellValueFactory(
                new PropertyValueFactory<Medic, String>("oraStart"));

        TableColumn oraEndM = new TableColumn("oraEnd");
        oraEndM.setMinWidth(100);
        oraEndM.setCellValueFactory(
                new PropertyValueFactory<Medic, String>("oraEnd"));

        TableColumn codParafaM = new TableColumn("Cod Parafa");
        codParafaM.setMinWidth(100);
        codParafaM.setCellValueFactory(
                new PropertyValueFactory<Medic, String>("codParafa"));

        tableM.setItems(dataM);
        tableM.getColumns().addAll(id, firstNameColM, lastNameColM, dataNastereM, varstaM, genM, specializareM, oraStartM, oraEndM, codParafaM);

        final VBox vboxM = new VBox();
        vboxM.setSpacing(5);
        vboxM.setPadding(new Insets(20, 0, 0, 10));
        final Label labelM = new Label("List of doctors");
        vboxM.getChildren().addAll(labelM,backToMeniu3, tableM);
        Scene sceneShowMedici = new Scene(vboxM, 1000, 200);
        buttonM.setOnAction(e->window.setScene(sceneShowMedici));




        tableA.setEditable(true);
        TableColumn idA = new TableColumn("Id Pacient");
        idA.setMinWidth(100);
        idA.setCellValueFactory(
                new PropertyValueFactory<Asistent, String>("id"));

        TableColumn lastNameColA = new TableColumn("Last Name");
        lastNameColA.setMinWidth(100);
        lastNameColA.setCellValueFactory(
                new PropertyValueFactory<Asistent, String>("nume"));

        TableColumn firstNameColA = new TableColumn("First Name");
        firstNameColA.setMinWidth(100);
        firstNameColA.setCellValueFactory(
                new PropertyValueFactory<Asistent, String>("prenume"));

        TableColumn dataNastereA = new TableColumn("Data nasterii");
        dataNastereA.setMinWidth(100);
        dataNastereA.setCellValueFactory(
                new PropertyValueFactory<Asistent, String>("DataNasterii"));

        TableColumn varstaA = new TableColumn("Varsta");
        varstaA.setMinWidth(50);
        varstaA.setCellValueFactory(
                new PropertyValueFactory<Asistent, String>("varsta"));
        TableColumn genA = new TableColumn("Gen");
        genA.setMinWidth(100);
        genA.setCellValueFactory(
                new PropertyValueFactory<Asistent, String>("gen"));

        TableColumn specializareA = new TableColumn("Specializare");
        specializareA.setMinWidth(100);
        specializareA.setCellValueFactory(
                new PropertyValueFactory<Asistent, String>("specializare"));

        TableColumn oraStartA = new TableColumn("ora Start");
        oraStartA.setMinWidth(100);
        oraStartA.setCellValueFactory(
                new PropertyValueFactory<Asistent, String>("oraStart"));

        TableColumn oraEndA = new TableColumn("oraEnd");
        oraEndA.setMinWidth(100);
        oraEndA.setCellValueFactory(
                new PropertyValueFactory<Asistent, String>("oraEnd"));

        TableColumn ture = new TableColumn("Ture");
        ture.setMinWidth(100);
        ture.setCellValueFactory(
                new PropertyValueFactory<Asistent, String>("ture"));

        tableA.setItems(dataA);
        tableA.getColumns().addAll(idA, firstNameColA, lastNameColA, dataNastereA, varstaA, genA, specializareA, oraStartA, oraEndA, ture);

        final VBox vboxA = new VBox();
        vboxA.setSpacing(5);
        vboxA.setPadding(new Insets(20, 0, 0, 10));
        final Label labelA = new Label("List of asistenti");
        vboxA.getChildren().addAll(labelA,backToMeniu4, tableA);
        Scene sceneShowAsistenti = new Scene(vboxA, 1000, 200);
        button3.setOnAction(e->window.setScene(sceneShowAsistenti));




        tableR.setEditable(true);

        TableColumn pacientR = new TableColumn("Pacient");
        pacientR.setMinWidth(100);
        pacientR.setCellValueFactory(
                new PropertyValueFactory<Reteta, String>("pacient"));



        TableColumn eliberatDeR = new TableColumn("Eliberat De");
        eliberatDeR.setMinWidth(100);
        eliberatDeR.setCellValueFactory(
                new PropertyValueFactory<Reteta, String>("eliberatDe"));

        TableColumn eliberatLaR = new TableColumn("eliberatLa");
        eliberatLaR.setMinWidth(100);
        eliberatLaR.setCellValueFactory(
                new PropertyValueFactory<Reteta, String>("eliberatLa"));

        TableColumn medicamenteR = new TableColumn("Medicamente ");
        medicamenteR.setMinWidth(100);
        medicamenteR.setCellValueFactory(
                new PropertyValueFactory<Reteta, String>("medicamente"));

        tableR.setItems(dataR);
        tableR.getColumns().addAll(pacientR, eliberatDeR, eliberatLaR, medicamenteR);

        final VBox vboxR = new VBox();
        vboxR.setSpacing(5);
        vboxR.setPadding(new Insets(20, 0, 0, 10));
        final Label labelR = new Label("List of retete");
        vboxR.getChildren().addAll(labelR,backToMeniu5, tableR);
        Scene sceneShowRetete = new Scene(vboxR, 1000, 200);
        button4.setOnAction(e->window.setScene(sceneShowRetete));




        tableTM.setEditable(true);

        TableColumn pacientTM = new TableColumn("Pacient");
        pacientTM.setMinWidth(100);
        pacientTM.setCellValueFactory(
                new PropertyValueFactory<TrimitereMedicala, String>("pacient"));

        TableColumn valabilitateTM = new TableColumn("Valabilitate");
        valabilitateTM.setMinWidth(100);
        valabilitateTM.setCellValueFactory(
                new PropertyValueFactory<TrimitereMedicala, String>("valabilitate"));

        TableColumn scopTM = new TableColumn("Scop");
        scopTM.setMinWidth(100);
        scopTM.setCellValueFactory(
                new PropertyValueFactory<TrimitereMedicala, String>("scop"));

        TableColumn catreTM = new TableColumn("Catre");
        catreTM.setMinWidth(100);
        catreTM.setCellValueFactory(
                new PropertyValueFactory<TrimitereMedicala, String>("catre"));

        TableColumn eliberatDeTM = new TableColumn("Eliberat De");
        eliberatDeTM.setMinWidth(100);
        eliberatDeTM.setCellValueFactory(
                new PropertyValueFactory<TrimitereMedicala, String>("eliberatDe"));

        TableColumn eliberatLaTM = new TableColumn("eliberatLa");
        eliberatLaTM.setMinWidth(100);
        eliberatLaTM.setCellValueFactory(
                new PropertyValueFactory<TrimitereMedicala, String>("eliberatLa"));


        tableTM.setItems(dataTM);
        tableTM.getColumns().addAll(pacientTM, valabilitateTM, scopTM, catreTM, eliberatDeTM, eliberatLaTM);

        final VBox vboxTM = new VBox();
        vboxTM.setSpacing(5);
        vboxTM.setPadding(new Insets(20, 0, 0, 10));
        final Label labelTM = new Label("List of Trimiteri medicale");
        vboxTM.getChildren().addAll(labelTM,backToMeniu6, tableTM);
        Scene sceneShowTrimiteri = new Scene(vboxTM, 1000, 200);
        button5.setOnAction(e->window.setScene(sceneShowTrimiteri));



        tableCM.setEditable(true);

        TableColumn pacientCM = new TableColumn("Pacient");
        pacientCM.setMinWidth(100);
        pacientCM.setCellValueFactory(
                new PropertyValueFactory<ConcediuMedical, String>("pacient"));

        TableColumn valabilitateCM = new TableColumn("Numar Zile Concediu");
        valabilitateCM.setMinWidth(100);
        valabilitateCM.setCellValueFactory(
                new PropertyValueFactory<ConcediuMedical, String>("nrZileConcediu"));

        TableColumn dataFinalCM = new TableColumn("dataFinal");
        dataFinalCM.setMinWidth(100);
        dataFinalCM.setCellValueFactory(
                new PropertyValueFactory<ConcediuMedical, String>("dataFinal"));


        TableColumn eliberatDeCM = new TableColumn("Eliberat De");
        eliberatDeCM.setMinWidth(100);
        eliberatDeCM.setCellValueFactory(
                new PropertyValueFactory<ConcediuMedical, String>("eliberatDe"));

        TableColumn eliberatLaCM = new TableColumn("eliberatLa");
        eliberatLaCM.setMinWidth(100);
        eliberatLaCM.setCellValueFactory(
                new PropertyValueFactory<ConcediuMedical, String>("eliberatLa"));


        tableCM.setItems(dataCM);
        tableCM.getColumns().addAll(pacientCM, valabilitateCM,  dataFinalCM, eliberatDeCM, eliberatLaCM);

        final VBox vboxCM = new VBox();
        vboxCM.setSpacing(5);
        vboxCM.setPadding(new Insets(20, 0, 0, 10));
        final Label labelCM = new Label("List of concedii medicale");
        vboxCM.getChildren().addAll(labelCM,backToMeniu7, tableCM);
        Scene sceneShowConcedii = new Scene(vboxCM, 1000, 200);
        button6.setOnAction(e->window.setScene(sceneShowConcedii));



        primaryStage.setScene(scene);
        primaryStage.show();
    }

    @Override
    public void handle(ActionEvent event) {
        if(event.getSource() == button){
            System.out.println("Bine ati venit la categoria pacienti");
            System.out.println("Pentru a vedea pacientii deja existenti, introduceti 1");
            System.out.println("Pentru a adauga un pacient in baza de date, introduceti 2");
            System.out.println("Pentru a adauaga o afectiune unui pacienti, introduceti 3");
            System.out.println("Pentru a sterge o afectiune a unui pacient, introdoceti 4");
            System.out.println("Pentru a face update la varsta unui pacient, introdoceti 5");
            System.out.println("Pentru a sterge un pacient, introdoceti 6");

        }
        if(event.getSource() == buttonP){
            
        }
        if (event.getSource() == buttonCreatePacient) {

        }
    }
}
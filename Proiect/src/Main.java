import Entities.*;
import Helpers.FileHelper;
import Helpers.ReadWriteService;
import Services.Implementations.AsistentService;
import Services.Implementations.MedicService;
import Services.Implementations.PacientService;
import Services.Implementations.RetetaService;
import Services.Interfaces.PacientInterface;
import com.opencsv.CSVWriter;
import org.omg.Messaging.SYNC_WITH_TRANSPORT;
import com.opencsv.CSVReader;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Main{

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

    public static List<Asistent> listaAsistenti;
    public static AsistentService asistentService;


   public static void initializeazaPacienti(){

       pacientService = new PacientService();
       listapb = new ArrayList<String>();
       pacienti = new ArrayList<>();
       Collections.sort(pacienti);

   }

    public static void afiseazaMeniu(){
        System.out.println("Bine ati venit. Introduceti una din comenzile de mai jos, in functie de actiunea pe care o doriti.");
        System.out.println("Pentru a accesa datele despre pacineti, intrudceti 1");
        System.out.println("Pentru a accesa datele despre medici, introduceti 2");
        System.out.println("Pentru a accesa datele despre asistenti, introduceti 3");
        System.out.println("Pentru a accesa datele despre retete, introduceti 4");
        System.out.println("Pentru a accesa datele despre trimiterile medicale, introduceti 5");
        System.out.println("Pentru a accesa datele despre concediile medicale, introduceti 6");
        System.out.println("Pentru a accesa datele despre adeverintele medicale, introduceti 7");
        System.out.println("Pentru a accesa datele despre cabinetul medical, introduceti 8");
    }

    public static void initializeazaMedici(){
        medicService = new MedicService();

    }
    public static void initializeazaAsistenti(){
        listaAsistenti = new ArrayList<Asistent>();
         asistentService = new AsistentService();
   }


    public static void main(String[] args) throws IOException {
        Scanner scan = new Scanner(System.in);
        afiseazaMeniu();


        //pacienti - lista locala pentru a extrage datele din csv
        initializeazaPacienti();



        //lista medici - pt a extrage datele din csv
        initializeazaMedici();


        //lista asistenti pt a extrage datele din csv
        initializeazaAsistenti();


        //lista retete- date din csv
        List<Reteta> listaRetete = new ArrayList<Reteta>();

        //lista adeverinte medicale
        List<AdeverintaMedicala> listaAdeverinte = new ArrayList<AdeverintaMedicala>();

        //lista concedii medicale
        List<ConcediuMedical> listaConcedii = new ArrayList<ConcediuMedical>();

        //lista trimiteri medicale
        List<TrimitereMedicala> listaTrimiteri = new ArrayList<TrimitereMedicala>();


        //citeste comanda
        int x = scan.nextInt();
        while(x!=0) {

            switch (x) {
                case 1: {
                    System.out.println("Bine ati venit la categoria pacienti");
                    System.out.println("Pentru a vedea pacientii deja existenti, introduceti 1");
                    System.out.println("Pentru a adauga un pacient in baza de date, introduceti 2");
                    System.out.println("Pentru a adauaga o afectiune unui pacienti, introduceti 3");
                    System.out.println("Pentru a sterge o afectiune a unui pacient, introdoceti 4");


                    int y = scan.nextInt();
                    while (y != 0) {
                        switch (y) {
                            case 1: {
                                System.out.println("Lista de pacienti este: ");
                            matrPacienti = ReadWriteService.citireCSV(pacientiPath);
                            pacienti = Pacient.getListFromCSV(matrPacienti);
                            pacientService.afiseazaPacienti(pacienti);
                                break;
                            }
                            case 2:
                            {
                             PacientService ps = new PacientService();
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
                             p = ps.crearePacient(id, nume,prenume,dataNasterii,varsta, gen, listaAfectiuni);
                             pacienti.add(p);
                                Collections.sort(pacienti);
                                ReadWriteService.scriereCSV(pacientiPath, Pacient.returnHeader(), Pacient.listToCSV(pacienti));


                                break;
                            }
                            case 3: {
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
                                System.out.println("Pentru a sterge una din afectiunile unui pacient, introduceti intai datele despre pacient");
                                System.out.println("ID: ");
                                int id = scan.nextInt();
                                System.out.println("Nume: ");
                                String nume = scan.next();
                                System.out.println("Prenume");
                                String prenume = scan.next();

                                System.out.println("Introduceti afectiunea pe care doriti sa o stergeti");
                                String afect = scan.next();
                                PacientService ps = new PacientService();
                                pacienti = ps.stergeAfectiune(id, pacienti, afect);

                                ReadWriteService.scriereCSV(pacientiPath, Pacient.returnHeader(), Pacient.listToCSV(pacienti));

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

                    int y = scan.nextInt();
                    while (y != 0) {
                        switch (y) {
                            case 1: {
                             System.out.println("Lista de medici existenta in sistem este:");
                                matrMedici = ReadWriteService.citireCSV(mediciPath);
                                medici = Medic.getListFromCSV(matrMedici);
                               medicService.afiseazaMedici(medici);


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

                                MedicService m = new MedicService();
                                Medic M = m.creareMedic(id, nume, prenume, dataNasterii, varsta, gen, spec, start, stop, cod);
                                System.out.println("Medicul adaugat: ");
                                System.out.println(M.toString());
                                medici.add(M);

                                ReadWriteService.scriereCSV(mediciPath, Medic.returnHeader(), Medic.listToCSV(medici));


                                break;
                            }
                            case 3: {
                                System.out.println("Pentru a actualiza specializarea unui medic introduceti datele despre medic:");
                                System.out.println("ID: ");
                                int id = scan.nextInt();
                                System.out.println("Nume: ");
                                String nume = scan.next();
                                System.out.println("Prenume");
                                String prenume = scan.next();
                                System.out.println("Data nasterii, sub forma dd/ll/aaaa");
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

                                System.out.println("Introduceti noua specializare");
                                String newSpecializare = scan.next();
                                MedicService m = new MedicService();
                                Medic M = m.creareMedic(id,nume, prenume, dataNasterii, varsta, gen, spec, start, stop, cod);
                                M = m.updateSpecialiare(M, newSpecializare);
                                System.out.println("Medic actualizat:");
                                System.out.println(M.toString());
                                break;
                            }
                            case 4: {
                                System.out.println("Pentru a actualiza varsta unui medic introduceti datele despre medic:");
                                System.out.println("ID: ");
                                int id = scan.nextInt();
                                System.out.println("Nume: ");
                                String nume = scan.next();
                                System.out.println("Prenume");
                                String prenume = scan.next();
                                System.out.println("Data nasterii, sub forma dd/ll/aaaa");
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

                                System.out.println("Introduceti noua varsta");
                                int newVarsta = scan.nextInt();
                                MedicService m = new MedicService();
                                Medic M = m.creareMedic(id, nume, prenume, dataNasterii, varsta, gen, spec, start, stop, cod);
                                M = m.updateVarsta(M, newVarsta);
                                System.out.println("Medic actualizat:");
                                System.out.println(M.toString());
                                break;
                            }
                            case 5: {
                                System.out.println("Pentru a afisa intervalul orar in care este medicul la cabinet, introduceti urmatoarele date:");
                                System.out.println("Nume");
                                String nume = scan.next();
                                System.out.println("Prenume");
                                String prenume = scan.next();


                                MedicService m = new MedicService();

                                //String intervalOrar = m.afiseazaIntervalOrar(nume, prenume, matrMedici);
                               // System.out.println(" " + intervalOrar);
                                break;

                            }


                        }
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

                    int y = scan.nextInt();
                    while (y != 0) {
                        switch (y) {
                            case 1: {

                                try (Reader reader = Files.newBufferedReader(Paths.get(asistentiPath));
                                     CSVReader csvReader = new CSVReader(reader);) {
                                    System.out.println("Lista de asistenti deja existenti este: ");
                                    String[] next;
                                    next = csvReader.readNext();
                                    while ((next = csvReader.readNext()) != null) {
                                        System.out.println("ID : " + next[0]);
                                        System.out.println("Nume : " + next[1]);
                                        System.out.println("Prenume : " + next[2]);
                                        System.out.println("Data nasterii : " + next[3]);
                                        System.out.println("Varsta : " + next[4]);
                                        System.out.println("Gen: " + next[5]);
                                        System.out.println("Specializare: " + next[6]);
                                        System.out.println("Ora la care incepe: " + next[7]);
                                        System.out.println("Ora la care termina: " + next[8]);
                                        if(next[9] == "TRUE")
                                            System.out.println("Lucreaza in ture");
                                        else System.out.println("Nu lucreaza in ture");

                                        System.out.println("==========================");

                                        Asistent a = asistentService.creareAsistent(Integer.parseInt(next[0]), next[1], next[2], next[3], Integer.parseInt(next[4]), next[5], next[6], Double.parseDouble(next[7]), Double.parseDouble(next[8]), Boolean.parseBoolean(next[9]));
                                        listaAsistenti.add(a);

                                    }
                                    System.out.println("Lista asistenti dupa citire: ");
                                    /*for(int i = 0; i <  listaAsistenti.size(); i++)
                                    {
                                        System.out.println(listaAsistenti.get(i).toString());
                                        System.out.println("==========================");
                                    }*/
                                }

                                }break;


                        }
                        y = 0;
                    }
                }
                    break;
                    case 4: {
                    /*RetetaService retetaService1  = new RetetaService();
                    RetetaService retetaService2 = new RetetaService();
                    List<String> lista = new ArrayList<String>();
                    lista.add("diabet");
                    lista.add("coronavirus");
                    Pacient p11 =new Pacient("popescu", "andrei", "2/03/1980", 40, "masculin", lista);

                    Map<String,Integer> listaMedicamente  = new HashMap<String, Integer>();
                    listaMedicamente.put("paracetamol", 3);
                    listaMedicamente.put("nurofen", 3);

                    Reteta r1 = retetaService1.creareReteta(p11, "Popescu George", "12/03/2020", listaMedicamente);


                    System.out.println(r1.toString());*/
                    }
                    break;
                    case 5: {
                        System.out.println("Bine ati venit la categoria Concedii Medicale. Introduceti una din comenzile de mai jos:");
                    }
                    break;
                    case 6: {
                        System.out.println("Bine ati venit la categoria Trimiteri Medicale. Introduceti una din comenzile de mai jos:");
                    }
                    case 7: {
                        System.out.println("Bine ati venit la categoria Adeverinte medicale. Introduceti una din comenzile de mai jos:");
                    }
                    break;
                    case 8: {
                        System.out.println("Bine ati venit la categoria Cabinet Medical. Introduceti una din comenzile de mai jos:");
                    }
                    break;

                }
                System.out.println("Daca doriti sa continuati interogarile, introduceti una din comenzile prezentate mai sus. In caz contrar, introduceti 0.");
                x = scan.nextInt();
            }
        }





    }



import Entities.*;
import Helpers.FileHelper;
import Services.Implementations.AsistentService;
import Services.Implementations.MedicService;
import Services.Implementations.PacientService;
import Services.Implementations.RetetaService;
import Services.Interfaces.PacientInterface;
import org.omg.Messaging.SYNC_WITH_TRANSPORT;
import com.opencsv.CSVReader;

import java.io.IOException;
import java.io.Reader;
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

   public  static List<Pacient> listaPacienti;
   public  static PacientService pacientService;
   public  static List<String> listapb;


   public static void initializeazaPacienti(){
       listaPacienti = new ArrayList<Pacient>();
       pacientService = new PacientService();
       listapb = new ArrayList<String>();
       Collections.sort(listaPacienti);
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



    public static void main(String[] args) throws IOException {
        Scanner scan = new Scanner(System.in);
        afiseazaMeniu();


        //pacienti - lista locala pentru a extrage datele din csv
        initializeazaPacienti();



        //lista medici - pt a extrage datele din csv
        MedicService medicService = new MedicService();
        MedicService m1 = new MedicService();
        MedicService m2 = new MedicService();
        MedicService m3 = new MedicService();
        MedicService m4 = new MedicService();
        Medic m11  = m1.creareMedic("popescu", "andrei", "2/03/1980", 40, "masculin", "cardiologie", 9.0, 14.0, 123456789);
        Medic m22 = m2.creareMedic("iordache", "stefan", "30/04/1997", 23, "masculin", "reumatologie", 12, 18, 987654);
        List<Medic> medici = new ArrayList<Medic>();
        medici.add(m11);
        medici.add(m22);


        //lista asistenti pt a extrage datele din csv
        List<Asistent> listaAsistenti = new ArrayList<Asistent>();
        AsistentService a1 = new AsistentService();
        AsistentService a2 = new AsistentService();
        Asistent a = a1.creareAsistent("popescu", "andrei", "2/03/1980", 40, "masculin", "cardiologie", 9.0, 14.0, false);
        Asistent b = a2.creareAsistent("andrei", "george", "30/01/1987", 33, "masculin", "dermatologie", 13, 19, true);

        listaAsistenti = a1.adaugaAsistentInLista(a, listaAsistenti);
        listaAsistenti = a2.adaugaAsistentInLista(b, listaAsistenti);


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
        while(x!=0){

            switch(x){
                case 1: {
                    System.out.println("Bine ati venit la categoria pacienti");
                    System.out.println("Pentru a vedea pacientii deja existenti, introduceti 1");
                    System.out.println("Pentru a adauga un pacient in baza de date, introduceti 2");
                    System.out.println("Pentru a adauaga o afectiune unui pacienti, introduceti 3");
                    System.out.println("Pentru a sterge o afectiune a unui pacient, introdoceti 4");



                    int y = scan.nextInt();
                    while(y!=0){
                        switch(y){
                            case 1:
                            {

                                try(Reader reader = Files.newBufferedReader(Paths.get(pacientiPath));
                                    CSVReader csvReader = new CSVReader(reader);)
                                {
                                    System.out.println("Lista de pacienti este: \n");
                                    String[] next;
                                    next =csvReader.readNext();
                                    while((next =csvReader.readNext())!=null){
                                        System.out.println("ID : " + next[0]);
                                        System.out.println("Nume : " + next[1]);
                                        System.out.println("Prenume : " + next[2]);
                                        System.out.println("Data nasterii : " + next[3]);
                                        System.out.println("Varsta : " + next[4]);
                                        System.out.println("Gen: " + next[5]);
                                        System.out.println("Afectiuni: " + next[6]);
                                        System.out.println("==========================");
                                        listapb = new ArrayList<>(Arrays.asList(next[6].split(" ")));
                                        Pacient p = pacientService.crearePacient(next[1], next[2], next[3], Integer.parseInt(next[4]), next[5], listapb);
                                        listaPacienti.add(p);

                                    }
                                    System.out.println("Pacientii din lista sunt: ");
                                    for(int i = 0; i <  listaPacienti.size(); i++)
                                        System.out.println(listaPacienti.get(i).toString());
                                }


                                break;
                            }

                           /* case 2:
                            {
                             PacientService ps = new PacientService();
                             Pacient p = new Pacient();
                             System.out.println("Pentru a adauga un pacient in baza de date, introduceti urmatoarele date:\n");
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
                             p = ps.crearePacient(nume,prenume,dataNasterii,varsta, gen, listaAfectiuni);
                             pacienti.add(p);
                                Collections.sort(pacienti);
                                break;
                            }*/

                            case 3:
                            {   System.out.println("Pentru a adauga o afectiune unui pacient, intai introduceti date despre pacientul respectiv");

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
                                int nr;
                                System.out.println("Numarul de afectiuni deja existente pe care le are pacientul: ");
                                nr = scan.nextInt();
                                List<String> listaAfectiuni = new ArrayList<String>();
                                for(int i = 0;  i<  nr; i++)
                                {
                                    System.out.println("Introduceti afectiunea deja existenta: ");
                                    String afect = scan.next();
                                    listaAfectiuni.add(afect);
                                }
                                System.out.println("Introduceti afectiunea pe care doriti sa o adaugati");
                                String afect = scan.next();
                                Pacient p = new Pacient(nume, prenume, dataNasterii, varsta, gen, listaAfectiuni);
                                PacientService ps = new PacientService();
                                p = ps.adaugaAfectiune(p, afect);
                                System.out.println();
                                System.out.println(p.toString());
                                break;
                            }
/*
                            case 4:
                            {
                                System.out.println("Pentru a sterge una din afectiunile unui pacient, introduceti intai datele despre pacient");
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
                                int nr;
                                System.out.println("Numarul de afectiuni deja existente pe care le are pacientul: ");
                                nr = scan.nextInt();
                                List<String> listaAfectiuni = new ArrayList<String>();
                                for(int i = 0;  i<  nr; i++)
                                {
                                    System.out.println("Introduceti afectiunea deja existenta: ");
                                    String afect = scan.next();
                                    listaAfectiuni.add(afect);
                                }
                                System.out.println("Introduceti afectiunea pe care doriti sa o stergeti");
                                String afect = scan.next();
                                Pacient p = new Pacient(nume, prenume, dataNasterii, varsta, gen, listaAfectiuni);
                                PacientService ps = new PacientService();
                                p = ps.stergeAfectiune(p, afect);

                                pacienti.add(p);
                                System.out.println();
                                System.out.println(p.toString());
                                break;
                            }*/

                        }
                        System.out.println("Pentru a continua interogarile, introduceri una din comenzile mai sus. Daca doriti sa iesiti din aceasta sectiune, introduceti 0.");
                        y = scan.nextInt();
                    }

                }
                break;
                case 2:{
                    System.out.println("Bun venit la categoria medici.");
                    System.out.println("Pentru  a afisa lista de medici, introduceti 1.");
                    System.out.println("Pentru a adauga un medic in lista, introduceti 2");
                    System.out.println("Pentru a actualiza specializarea unui medic, introduceti 3");
                    System.out.println("Pentru a actualiza varsta unui medic, intrduceti 4");
                    System.out.println("Pentru a afisa intervalul orar al unui medic, introduceti 5");

                    int y = scan.nextInt();
                    while(y!=0){
                        switch(y){
                            case 1:{
                                try(Reader reader = Files.newBufferedReader(Paths.get(mediciPath));
                                    CSVReader csvReader = new CSVReader(reader);)
                                {
                                    System.out.println("Lista de medici este: \n");
                                    String[] next;
                                    next = csvReader.readNext();
                                    while((next =csvReader.readNext())!=null){
                                        System.out.println("ID : " + next[0]);
                                        System.out.println("Nume : " + next[1]);
                                        System.out.println("Prenume : " + next[2]);
                                        System.out.println("Data nasterii : " + next[3]);
                                        System.out.println("Varsta : " + next[4]);
                                        System.out.println("Gen: " + next[5]);
                                        System.out.println("Specializare: " + next[6]);
                                        System.out.println("Ora la care incepe: " + next[7]);
                                        System.out.println("Ora la care inchide: " + next[8]);

                                        System.out.println("Cod parafa: " + next[9]);
                                        System.out.println("==========================");

                                        Medic m = medicService.creareMedic(next[1], next[2], next[3], Integer.parseInt(next[4]), next[5], next[6], Double.parseDouble(next[7]), Double.parseDouble(next[8]), Integer.parseInt(next[9]));
                                        medici.add(m);

                                    }

                                }
                                System.out.println("Lista de pacienti dupa citire este:");
                                for(int i = 0; i < medici.size(); i++)
                                {
                                    System.out.println(medici.get(i).toString());
                                    System.out.println("======================================================");
                                }


                                break;
                            }
                            case 2:
                            {
                                System.out.println("Pentru a adauga un medic la lista introduceti datele despre medic:");
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
                                Medic M = m.creareMedic(nume, prenume, dataNasterii, varsta, gen, spec, start, stop, cod);
                                System.out.println("Medicul adaugat: ");
                                System.out.println(M.toString());

                                medici.add(M);
                                break;
                            }
                            case 3:{
                                System.out.println("Pentru a actualiza specializarea unui medic introduceti datele despre medic:");
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
                                Medic M = m.creareMedic(nume, prenume, dataNasterii, varsta, gen, spec, start, stop, cod);
                                M = m.updateSpecialiare(M, newSpecializare);
                                System.out.println("Medic actualizat:");
                                System.out.println(M.toString());
break;
                            }
                            case 4:
                            { System.out.println("Pentru a actualiza varsta unui medic introduceti datele despre medic:");
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
                                Medic M = m.creareMedic(nume, prenume, dataNasterii, varsta, gen, spec, start, stop, cod);
                                M = m.updateVarsta(M, newVarsta);
                                System.out.println("Medic actualizat:");
                                System.out.println(M.toString());
                                break;
                            }
                            case 5:{
                                System.out.println("Pentru a afisa intervalul orar in care este medicul la cabinet, introduceti urmatoarele date:");
                                System.out.println("Nume");
                                String nume = scan.next();
                                System.out.println("Prenume");
                                String prenume = scan.next();


                                MedicService m = new MedicService();

                                String intervalOrar = m.afiseazaIntervalOrar(nume, prenume, medici);
                                System.out.println(" "+ intervalOrar);
                                break;

                            }


                        }
                        y = scan.nextInt();
                    }

                }
                    break;
                case 3:{
                    System.out.println("Bine ati venit in sectiunea de asistenti!");
                    /*System.out.println("Pentru a afla lista de asistenti, introduceti 1.");
                    System.out.println("Pentru a adauga un asistent la lista, introduceti 2.");
                    System.out.println("Pentru a modifica specializarea unui asistent, introduceti 3.");
                    System.out.println("Pentru a afla programul de lucru al unui asistent, introduceti 4");
                    System.out.println("Pentru a gasi un asistent cu o anumita specializare, introduceti 5.");

                    int y = scan.nextInt();
                    while(y!= 0){
                        switch(y){
                            case 1:
                            {

                                break;
                            }
                        }
                    }

                }
                    break;
                case 4:{
                    RetetaService retetaService1  = new RetetaService();
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
                case 5:{
                }
                    break;
                case 6:{
                }
                case 7:{

                }
                    break;
                case 8:{
                }
                    break;

            }
            System.out.println("Daca doriti sa continuati interogarile, introduceti una din comenzile prezentate mai sus. In caz contrar, introduceti 0.");
            x = scan.nextInt();
        }






    }

}

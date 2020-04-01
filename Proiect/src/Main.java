import Entities.*;
import Services.Implementations.AsistentService;
import Services.Implementations.MedicService;
import Services.Implementations.PacientService;
import Services.Implementations.RetetaService;
import Services.Interfaces.PacientInterface;
import org.omg.Messaging.SYNC_WITH_TRANSPORT;

import java.util.*;

public class Main{
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Bine ati venit. Introduceti una din comenzile de mai jos, in functie de actiunea pe care o doriti.");
        System.out.println("Pentru a accesa datele despre pacineti, intrudceti 1");
        System.out.println("Pentru a accesa datele despre medici, introduceti 2");
        System.out.println("Pentru a accesa datele despre asistenti, introduceti 3");
        System.out.println("Pentru a accesa datele despre retete, introduceti 4");
        System.out.println("Pentru a accesa datele despre trimiterile medicale, introduceti 5");
        System.out.println("Pentru a accesa datele despre concediile medicale, introduceti 6");
        System.out.println("Pentru a accesa datele despre adeverintele medicale, introduceti 7");
        System.out.println("Pentru a accesa datele despre cabinetul medical, introduceti 8");


        //Lista generala de Pacienti si medicamente, populata pentru a avea o "baza de date"
        PacientService pacientService = new PacientService();
        PacientService pacientService1 = new PacientService();
        PacientService pacientService2 = new PacientService();
        PacientService pacientService3 = new PacientService();
        PacientService pacientService4 = new PacientService();

        List<String> listapb = new ArrayList<String>();
        listapb.add("diabet");
        listapb.add("reumatism");
        listapb.add("astm");
        Pacient p1 = pacientService1.crearePacient("popescu", "andrei", "2/03/1980", 40, "masculin", listapb);
        Pacient p2 = pacientService2.crearePacient("popescu", "maria", "5/03/1980", 40, "feminin", listapb);
        Pacient p3 = pacientService3.crearePacient("andrei", "vlad", "2/03/1980", 40, "masculin", listapb);
        Pacient p4 = pacientService4.crearePacient("popescu", "andrei", "2/03/1980", 20, "masculin", listapb);

        List<Pacient> pacienti = new ArrayList<Pacient>();
        pacienti.add(p1);
        pacienti.add(p2);
        pacienti.add(p3);
        pacienti.add(p4);
        Collections.sort(pacienti);



        //lista medici deja initializata
        MedicService m1 = new MedicService();
        MedicService m2 = new MedicService();
        MedicService m3 = new MedicService();
        MedicService m4 = new MedicService();
        Medic m11  = m1.creareMedic("popescu", "andrei", "2/03/1980", 40, "masculin", "cardiologie", 9.0, 14.0, 123456789);
        Medic m22 = m2.creareMedic("iordache", "stefan", "30/04/1997", 23, "masculin", "reumatologie", 12, 18, 987654);
        List<Medic> medici = new ArrayList<Medic>();
        medici.add(m11);
        medici.add(m22);




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
                                System.out.println("Lista de pacienti este: \n");
                                for(int i = 0; i < pacienti.size(); i++)
                                    System.out.println(pacienti.get(i).toString());
                                break;
                            }

                            case 2:
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
                                break;
                            }

                            case 3:
                            {   System.out.println("Pentru a adauga o afectiune unui pacienti, intai introduceti date despre pacientul respectiv");

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

                            case 4:
                            {
                                System.out.println("Pentru a sterge una din afcetiunile unui pacienti, introduceti intai datele despre pacient");
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
                            }

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
                                System.out.println("Lista de medici este: \n");
                                for(int i = 0; i < medici.size(); i++)
                                    System.out.println(medici.get(i).toString());
                                break;
                            }
                            case 2:
                            {
                                System.out.println("Pentru a adauga un medic la lista introduceti datele despre medic:");
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
                    List<Asistent> listaAsistenti = new ArrayList<Asistent>();
                    AsistentService a1 = new AsistentService();
                    AsistentService a2 = new AsistentService();
                    Asistent a = a1.creareAsistent("popescu", "andrei", "2/03/1980", 40, "masculin", "cardiologie", 9.0, 14.0, false);
                    Asistent b = a2.creareAsistent("andrei", "george", "30/01/1987", 33, "masculin", "dermatologie", 13, 19, true);
                    System.out.println(a.toString());

                    a1.updateSpecializare(a, "reumatologie");
                    System.out.println(a.toString());


                    System.out.println();
                    listaAsistenti = a1.adaugaAsistentInLista(a, listaAsistenti);
                    listaAsistenti = a2.adaugaAsistentInLista(b, listaAsistenti);
                    for(int i = 0; i <  listaAsistenti.size(); i++)
                    {System.out.println();
                        System.out.println(listaAsistenti.get(i).toString());}
                    System.out.println();

                    int ok = 0;
                    for(int i = 0; i < listaAsistenti.size(); i++)
                        if(listaAsistenti.get(i).getSpecializare().compareTo("oncologie") == 0)
                        {
                         ok = 1;
                         System.out.println("Asistentul cu specializarea dermatologie este " + listaAsistenti.get(i).getNume() + " " + listaAsistenti.get(i).getPrenume());
                        }
                    if(ok == 0)
                        System.out.println("Nu exista un asistent cu aceasta specializare");

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


                    System.out.println(r1.toString());
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

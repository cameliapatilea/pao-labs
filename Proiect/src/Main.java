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
        int x = scan.nextInt();
        while(x!=0){

            switch(x){
                case 1: {
                    PacientService pacientService = new PacientService();
                    PacientService pacientService1 = new PacientService();
                    PacientService pacientService2 = new PacientService();
                    PacientService pacientService3 = new PacientService();
                    PacientService pacientService4 = new PacientService();

                    List<String> lista = new ArrayList<String>();
                    lista.add("diabet");
                    lista.add("coronavirus");
                    Pacient p1 = pacientService1.crearePacient("popescu", "andrei", "2/03/1980", 40, "masculin", lista);
                    Pacient p2 = pacientService2.crearePacient("popescu", "maria", "5/03/1980", 40, "masculin", lista);
                    Pacient p3 = pacientService3.crearePacient("andrei", "vlad", "2/03/1980", 40, "masculin", lista);
                    Pacient p4 = pacientService4.crearePacient("popescu", "andrei", "2/03/1980", 20, "masculin", lista);


                    System.out.println(p1);
                    System.out.println();

                    p1 = pacientService.adaugaAfectiune(p1, "artrita");

                    System.out.println(p1);
                    System.out.println();

                    p1 = pacientService.stergeAfectiune(p1, "coronavirus");

                    System.out.println(p1);
                    System.out.println();

                    List<Pacient> pacienti = new ArrayList<Pacient>();
                    pacienti.add(p1);
                    pacienti.add(p2);
                    pacienti.add(p3);
                    Collections.sort(pacienti);

                    System.out.println(pacienti);
                    System.out.println();
                }
                break;
                case 2:{
                    MedicService m1 = new MedicService();
                    MedicService m2 = new MedicService();
                    MedicService m3 = new MedicService();
                    MedicService m4 = new MedicService();
                    Medic m11  = m1.creareMedic("popescu", "andrei", "2/03/1980", 40, "masculin", "cardiologie", 9.0, 14.0, 123456789);
                    Medic m22 = m2.creareMedic("iordache", "stefan", "30/04/1997", 23, "masculin", "reumatologie", 12, 18, 987654);
                    System.out.println(m11.toString());
                    System.out.println();
                    System.out.println(m22.toString());
                    m22 = m2.updateSpecialiare(m22, "ortopedie");

                    System.out.println();
                    System.out.println(m22.toString());
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
                    Pacient p1 =new Pacient("popescu", "andrei", "2/03/1980", 40, "masculin", lista);

                    Map<String,Integer> listaMedicamente  = new HashMap<String, Integer>();
                    listaMedicamente.put("paracetamol", 3);
                    listaMedicamente.put("nurofen", 3);

                    Reteta r1 = retetaService1.creareReteta(p1, "Popescu George", "12/03/2020", listaMedicamente);


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

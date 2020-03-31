import Entities.Document;
import Entities.Medic;
import Entities.Pacient;
import Services.Implementations.MedicService;
import Services.Implementations.PacientService;
import Services.Interfaces.PacientInterface;
import org.omg.Messaging.SYNC_WITH_TRANSPORT;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

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
                    Medic m22 = m2.creareMedic("iordache", "stefan", "30/04/1997", 23, "masculin", "prost", 12, 18, 987654);
                    System.out.println(m11.toString());
                    System.out.println();
                    System.out.println(m22.toString());
                    m22 = m2.updateSpecialiare(m22, "ortopedie");

                    System.out.println();
                    System.out.println(m22.toString());
                }
                    break;
                case 3:{

                }
                    break;
                case 4:{
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

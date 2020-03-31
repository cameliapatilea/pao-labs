import Entities.Document;
import Entities.Pacient;
import Services.Implementations.PacientService;
import Services.Interfaces.PacientInterface;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class Main{
    public static void main(String[] args) {
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

}

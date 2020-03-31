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


        List<String> lista = new ArrayList<String>();
        lista.add("diabet");
        lista.add("coronavirus");

        Pacient p = new Pacient("popescu", "andrei", "2/03/1980", 40, "masculin", lista);
        Pacient p1 = new Pacient("popescu", "maria", "5/03/1980", 40, "masculin", lista);
        Pacient p2 = new Pacient("andrei", "vlad", "2/03/1980", 40, "masculin", lista);
        Pacient p3 = new Pacient("popescu", "andrei", "2/03/1980", 20, "masculin", lista);
        System.out.println(p);
        System.out.println();

        p = pacientService.adaugaAfectiune(p, "artrita");

        System.out.println(p);
        System.out.println();

        p = pacientService.stergeAfectiune(p, "coronavirus");

        System.out.println(p);
        System.out.println();

        List<Pacient> pacienti = new ArrayList<Pacient>();
        pacienti.add(p);
        pacienti.add(p1);
        pacienti.add(p2);
        pacienti.add(p3);
        Collections.sort(pacienti);

        System.out.println(pacienti);
        System.out.println();


    }

}

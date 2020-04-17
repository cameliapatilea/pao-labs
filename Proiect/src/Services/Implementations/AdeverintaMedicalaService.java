package Services.Implementations;

import Entities.AdeverintaMedicala;
import Entities.Pacient;
import Services.Interfaces.AdeverintaMedicalaInterface;

import java.util.ArrayList;
import java.util.List;

public class AdeverintaMedicalaService implements AdeverintaMedicalaInterface {
    @Override
    public AdeverintaMedicala getFromListById(List<AdeverintaMedicala> lista, int id) {
        for(int i = 0; i < lista.size(); i++)
            if(lista.get(i).getId() == id)
                return lista.get(i);

        return null;
    }

    @Override
    public void afiseazaAdeverinte(List<AdeverintaMedicala> adeverinte) {
        for(int i = 0; i < adeverinte.size(); i++)
        {
            System.out.println("Adeverinte medicale: ");
            System.out.println(adeverinte.get(i).toString());
            System.out.println("==================================");
        }
    }

    @Override
    public AdeverintaMedicala creareAdeverinta(Pacient pacient, String eliberatDe, String eliberatLa, boolean apt, String scop) {
       AdeverintaMedicala am = new AdeverintaMedicala(pacient, eliberatDe, eliberatLa, apt, scop);
       return am;
    }

    @Override
    public AdeverintaMedicala updateScop(AdeverintaMedicala am, String scop) {
        am.setScop(scop);
        return am;
    }

    @Override
    public String getAdeverinteEliberateLaDataX(List<AdeverintaMedicala> lista, String data) {
        List<AdeverintaMedicala> newList = new ArrayList<>();
        for(int i = 0; i <  lista.size(); i++)
        {
            if(lista.get(i).getEliberatLa().compareTo(data) == 0)
                newList.add(lista.get(i));
        }
        String x = "Adeverintele medicale eliberate la data " + data + "sunt";
        for(int i = 0; i < newList.size(); i++)
        {
            x+=newList.get(i).toString() + "\n";
        }
        return x;
    }
}

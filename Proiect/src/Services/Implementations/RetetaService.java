package Services.Implementations;

import Entities.Pacient;
import Entities.Reteta;
import Services.Interfaces.RetetaInterface;

import java.util.List;
import java.util.Map;

public class RetetaService implements RetetaInterface {
    @Override
    public Reteta getFromListById(List<Reteta> lista, int id) {
        for(int i = 0; i < lista.size(); i++)
            if(lista.get(i).getId() == id)
                return lista.get(i);

        return null;
    }

    @Override
    public Reteta creareReteta(Pacient pacient, String eliberatDe, String eliberatLa, Map<String, Integer> medicamente) {
       Reteta r = new Reteta( pacient,eliberatDe,  eliberatLa,medicamente);
       return r;
    }

    @Override
    public List<String> getMedicamente(Reteta r) {
        return null;
    }

    @Override
    public Reteta adaugaMedicament(List<String> medicamente, String medicament) {
        return null;
    }

    @Override
    public Reteta stergeMedicament(List<String> medicament, int indx, boolean stergeTot) {
        return null;
    }

    @Override
    public List<Reteta> adaugaRetetaInLista(List<Reteta> lista, Reteta r) {
        return null;
    }
}

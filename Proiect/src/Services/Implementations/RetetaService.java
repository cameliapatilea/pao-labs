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
    public Map<String, Integer> getMedicamente(Reteta r) {
        return r.getMedicamente();
    }

    @Override
    public Reteta adaugaMedicament(Reteta r,String medicament, int oriZi) {
        r.getMedicamente().put(medicament, oriZi);
        return r;
    }



}

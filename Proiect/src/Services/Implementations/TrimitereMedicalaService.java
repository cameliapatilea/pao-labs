package Services.Implementations;

import Entities.Pacient;
import Entities.TrimitereMedicala;
import Services.Interfaces.TrimitereMedicalaInterface;

import java.util.List;

public class TrimitereMedicalaService implements TrimitereMedicalaInterface {
    @Override
    public TrimitereMedicala creareTrimitere(Pacient pacient, String eliberatDe, String eliberatLa, int valabilitate, String scop, String catre) {
        TrimitereMedicala tm =  new TrimitereMedicala(pacient, eliberatDe, eliberatLa, valabilitate, scop, catre);
        return tm;
    }

    @Override
    public TrimitereMedicala getFromListById(List<TrimitereMedicala> lista, int id) {
        for(int i = 0; i < lista.size(); i++)
            if(lista.get(i).getId() == id)
                return lista.get(i);

        return null;
    }

    @Override
    public List<TrimitereMedicala> getAllTrimiteri(List<TrimitereMedicala> lista) {
        return lista;
    }

    @Override
    public TrimitereMedicala updateScop(TrimitereMedicala tm, String scop) {
        tm.setScop(scop);
        return tm;
    }

    @Override
    public TrimitereMedicala updateValabilitate(TrimitereMedicala tm, int valabilitate) {
        tm.setValabilitate(valabilitate);
        return tm;
    }
}

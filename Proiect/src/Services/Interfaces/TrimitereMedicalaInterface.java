package Services.Interfaces;

import Entities.Medic;
import Entities.Pacient;
import Entities.TrimitereMedicala;

import java.util.List;

public interface TrimitereMedicalaInterface {
    TrimitereMedicala creareTrimitere(Pacient pacient, String eliberatDe, String eliberatLa, int valabilitate, String scop, String catre);
    TrimitereMedicala getFromListById(List<TrimitereMedicala> lista, int id);
    List<TrimitereMedicala> getAllTrimiteri(List<TrimitereMedicala> lista);
    TrimitereMedicala updateScop(TrimitereMedicala tm, String scop);
    TrimitereMedicala updateValabilitate(TrimitereMedicala tm, int valabilitate);
}

package Services.Interfaces;

import Entities.Medic;
import Entities.Pacient;
import Entities.TrimitereMedicala;

import java.util.List;

public interface TrimitereMedicalaInterface {
    TrimitereMedicala creareTrimitere(Pacient pacient, String eliberatDe, String eliberatLa, int valabilitate, String scop, String catre);
    void afiseazaTrimiteri(List<TrimitereMedicala> trimiteri);
    TrimitereMedicala getFromListById(List<TrimitereMedicala> lista, int id);
    List<TrimitereMedicala> getAllTrimiteri(List<TrimitereMedicala> lista);
    List<TrimitereMedicala> updateScop(int id, List<TrimitereMedicala> trimiteri, String scop);
    List<TrimitereMedicala> updateValabilitate(int id, List<TrimitereMedicala> trimiteri, int valabilitate);
    String obtineScop(int id, List<TrimitereMedicala> trimiteri);
}

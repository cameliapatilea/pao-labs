package Services.Interfaces;

import Entities.Medic;
import Entities.Pacient;
import Entities.TrimitereMedicala;

import java.sql.Connection;
import java.util.List;

public interface TrimitereMedicalaInterface {
    void citesteScrieAudit(String comanda, String timp, String threadName);
    TrimitereMedicala creareTrimitere(Pacient pacient, String eliberatDe, String eliberatLa, int valabilitate, String scop, String catre);
    void afiseazaTrimiteri(List<TrimitereMedicala> trimiteri);
    TrimitereMedicala getFromListById(List<TrimitereMedicala> lista, int id);
    List<TrimitereMedicala> getAllTrimiteriFromDb(Connection connObj);
    List<TrimitereMedicala> updateScop(int id, List<TrimitereMedicala> trimiteri, String scop);
    List<TrimitereMedicala> updateValabilitate(int id, List<TrimitereMedicala> trimiteri, int valabilitate);
    String obtineScop(int id, List<TrimitereMedicala> trimiteri);
    void creareTrimitereDb(Connection connObj, TrimitereMedicala tm);
    void modificareScopDb(Connection connObj, int id, String scop);
    void modificareValabilitateDb(Connection connObj, int id, int val);
    void selectScopDb(Connection connObj, int id);
    void deleteTrimitereMedicalaDb(Connection connObj, int id);

}

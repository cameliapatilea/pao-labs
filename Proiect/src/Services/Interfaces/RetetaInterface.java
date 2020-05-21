package Services.Interfaces;

import Entities.Pacient;
import Entities.Reteta;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

public interface RetetaInterface {
    void citesteScrieAudit(String comanda, String timp);
    Reteta getFromListById(List<Reteta> lista, int id);
    void afiseazaRetete(List<Reteta> retete);
    Reteta creareReteta(Pacient pacient, String eliberatDe, String eliberatLa, Map<String, Integer> medicamente);
    Map<String,Integer> getMedicamente(Reteta r);
    Reteta adaugaMedicament(Reteta r,  String medicament, int oriZi);

    List<Reteta> getAllFromDb(Connection connObj);
    void createRetetaForDb(Connection connObj, Reteta r);
    void deleteRetetaFromDb(Connection connObj, int id);
    void modificaEliberareRetetaDb(Connection connObj, int id, String data);
}

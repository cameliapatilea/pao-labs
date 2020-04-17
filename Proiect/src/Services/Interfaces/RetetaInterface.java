package Services.Interfaces;

import Entities.Pacient;
import Entities.Reteta;

import java.util.List;
import java.util.Map;

public interface RetetaInterface {
    Reteta getFromListById(List<Reteta> lista, int id);
    void afiseazaRetete(List<Reteta> retete);
    Reteta creareReteta(Pacient pacient, String eliberatDe, String eliberatLa, Map<String, Integer> medicamente);
    Map<String,Integer> getMedicamente(Reteta r);
    Reteta adaugaMedicament(Reteta r,  String medicament, int oriZi);


}

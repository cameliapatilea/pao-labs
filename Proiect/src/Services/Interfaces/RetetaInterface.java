package Services.Interfaces;

import Entities.Pacient;
import Entities.Reteta;

import java.util.List;
import java.util.Map;

public interface RetetaInterface {
    Reteta getFromListById(List<Reteta> lista, int id);
    Reteta creareReteta(Pacient pacient, String eliberatDe, String eliberatLa, Map<String, Integer> medicamente);
    List<String> getMedicamente(Reteta r);
    Reteta adaugaMedicament(List<String> medicamente, String medicament);
    Reteta stergeMedicament(List<String> medicament, int indx, boolean stergeTot);
    List<Reteta> adaugaRetetaInLista(List<Reteta> lista, Reteta r);

}

package Services.Interfaces;

import Entities.Medic;

import java.util.List;

public interface MedicInterface {
    void citesteScrieAudit(String comanda, String timp);
    Medic getFromListById(List<Medic> lista, int id);
    void afiseazaMedici(List<Medic> medici);
    Medic creareMedic(int id, String nume, String prenume, String dataNasterii, int varsta, String gen, String specializare, double oraStart, double oraEnd, int codParafa);
    List<Medic> updateSpecialiare(int id, List<Medic> medici, String specializare);
    List<Medic> updateVarsta(int id, List<Medic> medici, int varsta, String dataNasterii);
    Medic updateNume(Medic m, String nume);
    String afiseazaSpecializare(String nume, String prenume, List<Medic> medici);
    String afiseazaIntervalOrar(String nume, String prenume, List<Medic> medici);
}

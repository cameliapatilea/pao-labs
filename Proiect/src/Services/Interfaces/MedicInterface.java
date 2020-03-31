package Services.Interfaces;

import Entities.Medic;

import java.util.List;

public interface MedicInterface {
    Medic getFromListById(List<Medic> lista, int id);
    Medic creareMedic(String nume, String prenume, String dataNasterii, int varsta, String gen, String specializare, double oraStart, double oraEnd, int codParafa);
    Medic updateSpecialiare(Medic m, String specializare);
    Medic updateVarsta(Medic m, int varsta);
    Medic updateNume(Medic m, String nume);
    String afiseazaSpecializare(Medic m);
    String afiseazaIntervalOrar(Medic m);
}

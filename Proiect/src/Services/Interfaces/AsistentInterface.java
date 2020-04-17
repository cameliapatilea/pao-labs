package Services.Interfaces;

import Entities.Asistent;

import java.util.List;

public interface AsistentInterface {
    Asistent getFromListById(List<Asistent> lista, int id);
    void afiseazaAsistenti(List<Asistent> asistenti);
    Asistent creareAsistent(int id, String nume, String prenume, String dataNasterii, int varsta, String gen, String specializare, double oraStart, double oraEnd, boolean ture);
    Asistent updateVarsta(Asistent a, int varsta);
    Asistent updateSpecializare(Asistent a, String specializare);
    Asistent updateTure(Asistent a, boolean ture);
    String afiseazaProgram(Asistent a);
    String afiseazaSpecializare(Asistent a);
    List<Asistent> adaugaAsistentInLista(Asistent a, List<Asistent> lista);
    Asistent  getAsistentBySpecializare(List<Asistent> lista, String specializare);
}

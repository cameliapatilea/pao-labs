package Services.Interfaces;

import Entities.Asistent;
import Entities.Medic;

import java.sql.Connection;
import java.util.List;

public interface AsistentInterface {
    void citesteScrieAudit(String comanda, String timp);
    Asistent getFromListById(List<Asistent> lista, int id);
    void afiseazaAsistenti(List<Asistent> asistenti);
    Asistent creareAsistent(int id, String nume, String prenume, String dataNasterii, int varsta, String gen, String specializare, double oraStart, double oraEnd, boolean ture);
    Asistent updateVarsta(Asistent a, int varsta);
    List<Asistent> updateSpecializare(int id, List<Asistent> asistenti,  String specializare);
    Asistent updateTure(Asistent a, boolean ture);
    String afiseazaProgram(String nume, String prenume, List<Asistent> asistenti);
    String afiseazaSpecializare(Asistent a);
    List<Asistent> adaugaAsistentInLista(Asistent a, List<Asistent> lista);
    Asistent  getAsistentBySpecializare(List<Asistent> lista, String specializare);


    List<Asistent> getAllFromDb(Connection connObj);
    void adaugaAsistentcDb(Connection connObj, Asistent m);
    void updateVarstaAsistentDb(Connection connObj, int id, int varsta, String data);
    void updateSpecializareAsistentDb(Connection connObj, int id, String specializare);
    void getOrarAsistentDb(Connection connObj, int id);
    Asistent getAsistentBySpecializareDb(Connection connObj, String specializare);
    void deleteAsistentFromDb(Connection connObj, int id);
}

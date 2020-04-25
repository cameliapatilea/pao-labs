package Services.Interfaces;

import Entities.Pacient;

import java.util.List;

public interface PacientInterface {
    void citesteScrieAudit(String comanda, String timp);
    Pacient getFromListById(List<Pacient> lista, int id);
    List<Pacient> adaugaAfectiune(int id, List<Pacient> pacienti, String afectiune);
    void afiseazaPacienti(List<Pacient> pacienti);
    Pacient adaugaAfectiuni(Pacient pacient, List<String> afectiuni);
    Pacient updateAfectiune(Pacient pacient, String afectiuneVeche, String afectiuneNoua);
    List<Pacient> stergeAfectiune(int id, List<Pacient> pacienti , String afectiune);
    Pacient stergeAfectiuni(Pacient pacient, List<String> afectiuni, boolean stergeTot); //daca stergeTot este True atunci se sterg toate afectiunile. Daca stergeTot e False se sterg doar afectiunile precizate
    Pacient updateNume(Pacient pacient, String nume);
    Pacient updateVarsta(Pacient pacient, int varsta);
    List<Pacient> adaugaPacientLaLista(Pacient pacient, List<Pacient> pacienti);
    Pacient crearePacient(int id, String nume, String prenume, String dataNasterii, int varsta,  String gen, List<String> afectiuni);

}

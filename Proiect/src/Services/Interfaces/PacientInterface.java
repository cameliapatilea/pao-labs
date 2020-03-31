package Services.Interfaces;

import Entities.Pacient;

import java.util.List;

public interface PacientInterface {
    Pacient getFromListById(List<Pacient> lista, int id);
    Pacient adaugaAfectiune(Pacient pacient, String afectiune);
    Pacient adaugaAfectiuni(Pacient pacient, List<String> afectiuni);
    Pacient updateAfectiune(Pacient pacient, String afectiuneVeche, String afectiuneNoua);
    Pacient stergeAfectiune(Pacient pacient, String afectiune);
    Pacient stergeAfectiuni(Pacient pacient, List<String> afectiuni, boolean stergeTot); //daca stergeTot este True atunci se sterg toate afectiunile. Daca stergeTot e False se sterg doar afectiunile precizate
    Pacient updateNume(Pacient pacient, String nume);
    Pacient updateVarsta(Pacient pacient, int varsta);
    List<Pacient> adaugaPacientLaLista(Pacient pacient, List<Pacient> pacienti);

}

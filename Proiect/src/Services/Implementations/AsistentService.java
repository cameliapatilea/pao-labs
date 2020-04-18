package Services.Implementations;

import Entities.Asistent;
import Services.Interfaces.AsistentInterface;

import java.util.List;

public class AsistentService implements AsistentInterface {
    @Override
    public Asistent getFromListById(List<Asistent> lista, int id) {
        for(int i = 0; i < lista.size(); i++)
            if(lista.get(i).getId() == id)
                return lista.get(i);

        return null;
    }

    @Override
    public void afiseazaAsistenti(List<Asistent> asistenti) {
        for(int i = 0; i < asistenti.size(); i++)
        {
            System.out.println("Asistenti: ");
            System.out.println(asistenti.get(i).toString());
            System.out.println("==================================");
        }
    }

    @Override
    public Asistent creareAsistent(int id, String nume, String prenume, String dataNasterii, int varsta, String gen, String specializare, double oraStart, double oraEnd, boolean ture) {
       Asistent a = new Asistent(id, nume, prenume, dataNasterii, varsta, gen, specializare, oraStart, oraEnd, ture);
       return a;
    }

    @Override
    public Asistent updateVarsta(Asistent a, int varsta) {
        a.setVarsta(varsta);
        return a;
    }

    @Override
    public List<Asistent> updateSpecializare(int id, List<Asistent> asistenti,  String specializare) {
        for(int i = 0; i < asistenti.size(); i++)

        {
            if(asistenti.get(i).getID() == id)
                asistenti.get(i).setSpecializare(specializare);
        }

        return asistenti;
    }

    @Override
    public Asistent updateTure(Asistent a, boolean ture) {
        a.setTure(ture);
        return a;
    }

    @Override
    public String afiseazaProgram(Asistent a) {
        return "Intervalul orar in care lucreaza asistentul este: " + a.getOraStart() + "-" + a.getOraEnd();
    }

    @Override
    public String afiseazaSpecializare(Asistent a) {
        return a.getSpecializare();
    }

    @Override
    public List<Asistent> adaugaAsistentInLista(Asistent a, List<Asistent> lista) {
       lista.add(a);
       return lista;
    }

    @Override
    public Asistent getAsistentBySpecializare(List<Asistent> lista, String specializare) {
        Asistent x = new Asistent();
        for(int i = 0; i <  lista.size(); i++)
            if(lista.get(i).getSpecializare().compareTo(specializare) == 0){
               x = lista.get(i);
                break;
            }
        return x;
    }
}

package Services.Implementations;

import Entities.Medic;
import Services.Interfaces.MedicInterface;

import java.util.List;

public class MedicImplementation implements MedicInterface {
    @Override
    public Medic getFromListById(List<Medic> lista, int id) {
        for(int i = 0; i < lista.size(); i++)
            if(lista.get(i).getId() == id)
                return lista.get(i);

        return null; 
    }

    @Override
    public Medic creareMedic(String nume, String prenume, String dataNasterii, int varsta, String gen, String specializare, double oraStart, double oraEnd, int codParafa) {
        Medic m = new Medic(nume, prenume, dataNasterii, varsta, gen, specializare, oraStart, oraEnd, codParafa);

        return m;
    }

    @Override
    public Medic updateSpecialiare(Medic m, String specializare) {
        m.setSpecializare(specializare);
        return m;
    }

    @Override
    public Medic updateVarsta(Medic m, int varsta) {
        m.setVarsta(varsta);
        return m;
    }

    @Override
    public Medic updateNume(Medic m, String nume) {
       m.setNume(nume);
       return m;
    }

    @Override
    public String afiseazaSpecializare(Medic m) {
      return m.getSpecializare();
    }

    @Override
    public String afiseazaIntervalOrar(Medic m) {
        double start = m.getOraStart();
        double stop = m.getOraEnd();
        return "Intervalul orar in care lucreaza medicul este" + start + "-" + stop;
    }
}

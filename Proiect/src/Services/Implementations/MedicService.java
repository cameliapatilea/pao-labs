package Services.Implementations;

import Entities.Medic;
import Services.Interfaces.MedicInterface;

import java.util.List;

public class MedicService implements MedicInterface {
    @Override
    public Medic getFromListById(List<Medic> lista, int id) {
        for(int i = 0; i < lista.size(); i++)
            if(lista.get(i).getId() == id)
                return lista.get(i);

        return null;
    }

    @Override
    public Medic creareMedic(int id, String nume, String prenume, String dataNasterii, int varsta, String gen, String specializare, double oraStart, double oraEnd, int codParafa) {
        Medic m = new Medic(id, nume, prenume, dataNasterii, varsta, gen, specializare, oraStart, oraEnd, codParafa);

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
    public String afiseazaSpecializare(String nume, String prenume, List<Medic> medici) {
        String x = "";

        for(int i = 0; i <  medici.size(); i++)
            if(medici.get(i).getNume().compareTo(nume) == 0 && medici.get(i).getPrenume().compareTo(prenume) == 0)
                x+= medici.get(i).getSpecializare();
         return x;
    }

    @Override
    public String afiseazaIntervalOrar(String nume, String prenume, List<Medic> medici) {
        String x = "";
        String y = "";

        for(int i = 0; i <  medici.size(); i++)
            if(medici.get(i).getNume().compareTo(nume) == 0 && medici.get(i).getPrenume().compareTo(prenume) == 0)
            {
                x+= medici.get(i).getOraStart();
                y+= medici.get(i).getOraEnd();
            }
        return "Intervalul orar in care lucreaza medicul este" + x + "-" + y;
    }
}

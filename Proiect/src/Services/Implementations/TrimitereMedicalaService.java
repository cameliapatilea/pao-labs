package Services.Implementations;

import Entities.Pacient;
import Entities.TrimitereMedicala;
import Services.Interfaces.TrimitereMedicalaInterface;

import java.util.List;

public class TrimitereMedicalaService implements TrimitereMedicalaInterface {
    @Override
    public TrimitereMedicala creareTrimitere(Pacient pacient, String eliberatDe, String eliberatLa, int valabilitate, String scop, String catre) {
        TrimitereMedicala tm =  new TrimitereMedicala(pacient,  valabilitate, scop, catre, eliberatDe, eliberatLa);
        return tm;
    }

    @Override
    public void afiseazaTrimiteri(List<TrimitereMedicala> trimiteri) {
        for(int i = 0; i < trimiteri.size(); i++)
        {
            System.out.println("Trimiteri medicale: ");
            System.out.println(trimiteri.get(i).toString());
            System.out.println("==================================");
        }
    }

    @Override
    public TrimitereMedicala getFromListById(List<TrimitereMedicala> lista, int id) {
        for(int i = 0; i < lista.size(); i++)
            if(lista.get(i).getId() == id)
                return lista.get(i);

        return null;
    }

    @Override
    public List<TrimitereMedicala> getAllTrimiteri(List<TrimitereMedicala> lista) {
        return lista;
    }

    @Override
    public List<TrimitereMedicala> updateScop(int id, List<TrimitereMedicala> trimiteri, String scop) {
        TrimitereMedicala tm = new TrimitereMedicala();
        for(int i = 0; i <  trimiteri.size(); i++)
        {
            if(trimiteri.get(i).getPacient().getId() == id)
            {
                trimiteri.get(i).setScop(scop);
            }
            //tm = trimiteri.get(i);
        }

        return trimiteri;
    }

    @Override
    public List<TrimitereMedicala> updateValabilitate(int id, List<TrimitereMedicala> trimiteri, int valabilitate) {
        return null;
    }


}

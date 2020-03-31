package Services.Implementations;

import Entities.ConcediuMedical;
import Entities.Pacient;
import Services.Interfaces.ConcediuMedicalInterface;
import com.sun.java.browser.plugin2.liveconnect.v1.ConversionDelegate;

import java.util.List;

public class ConcediuMedicalService implements ConcediuMedicalInterface {
    @Override
    public ConcediuMedical getFromListById(List<ConcediuMedical> lista, int id) {
        for(int i = 0; i < lista.size(); i++)
            if(lista.get(i).getId() == id)
                return lista.get(i);

        return null;
    }

    @Override
    public ConcediuMedical creareCerereConcediu(Pacient pacient, String eliberatDe, String eliberatLa, int nrZileConcediu, String dataFinal) {
        ConcediuMedical cm = new ConcediuMedical(pacient, eliberatDe, eliberatLa, nrZileConcediu, dataFinal);
        return cm;
    }

    @Override
    public ConcediuMedical updateNrZile(ConcediuMedical cm, int nrZile, String dataFinal) {
        cm.setNrZileConcediu(nrZile);
        cm.setDataFinal(dataFinal);
        return cm;
    }

    @Override
    public List<ConcediuMedical> stergereConcediu(List<ConcediuMedical> lista, int indx) {
       for(int i = 0; i < lista.size(); i++)
           if(i == indx){
               lista.remove(i);
           }
       return lista;
    }
}

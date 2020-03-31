package Services.Interfaces;

import Entities.ConcediuMedical;
import Entities.Pacient;

import java.util.List;

public interface ConcediuMedicalInterface {
    ConcediuMedical getFromListById(List<ConcediuMedical> lista, int id);
    ConcediuMedical creareCerereConcediu(Pacient pacient, String eliberatDe, String eliberatLa, int nrZileConcediu, String dataFinal);
    ConcediuMedical updateNrZile(ConcediuMedical cm, int nrZile, String dataFinal);
    List<ConcediuMedical> stergereConcediu(List<ConcediuMedical> lista, int indx);

}

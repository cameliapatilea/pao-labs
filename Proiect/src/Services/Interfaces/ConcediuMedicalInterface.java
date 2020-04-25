package Services.Interfaces;

import Entities.ConcediuMedical;
import Entities.Pacient;

import java.util.List;

public interface ConcediuMedicalInterface {
    void citesteScrieAudit(String comanda, String timp);
    ConcediuMedical getFromListById(List<ConcediuMedical> lista, int id);
    void afiseazaConcedii(List<ConcediuMedical> concedii);
    ConcediuMedical creareCerereConcediu(Pacient pacient, String eliberatDe, String eliberatLa, int nrZileConcediu, String dataFinal);
    ConcediuMedical updateNrZile(ConcediuMedical cm, int nrZile, String dataFinal);
    List<ConcediuMedical> stergereConcediu(List<ConcediuMedical> lista, int indx);

}

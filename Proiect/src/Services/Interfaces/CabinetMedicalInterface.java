package Services.Interfaces;

import Entities.Asistent;
import Entities.CabinetMedical;
import Entities.Medic;
import Entities.Pacient;

import java.util.List;

public interface CabinetMedicalInterface {
        CabinetMedical getFromListById(List<CabinetMedical> lista, int id);
        void afiseazaCabinet(List<CabinetMedical> cm);
        String getIntervalFunctionare(CabinetMedical cab);
        String getAdresaCabinet(CabinetMedical cab);
        String getMedici(List<Medic> listaMedici);
        String getAsistenti(List<Asistent> listaAsistenti);
        String getPacienti(List<Pacient> listaPacienti);


}

package Services.Implementations;

import Entities.Asistent;
import Entities.CabinetMedical;
import Entities.Medic;
import Entities.Pacient;
import Services.Interfaces.CabinetMedicalInterface;

import java.util.List;

public class CabinetMedicalService implements CabinetMedicalInterface {
    @Override
    public CabinetMedical getFromListById(List<CabinetMedical> lista, int id) {
        for(int i = 0; i < lista.size(); i++)
            if(lista.get(i).getId() == id)
                return lista.get(i);

        return null;
    }

    @Override
    public void afiseazaCabinet(List<CabinetMedical> cm) {
        for(int i = 0; i < cm.size(); i++)
        {
            System.out.println("Cabinet medical: ");
            System.out.println(cm.get(i).toString());
            System.out.println("==================================");
        }
    }

    @Override
    public String getIntervalFunctionare(CabinetMedical cab) {
        return "Intervalul orar in care cabinetul este deschis este: " + cab.getOraInceput() + "-" + cab.getOraStop();
    }

    @Override
    public String getAdresaCabinet(CabinetMedical cab) {
        return cab.getAdresa();
    }

    @Override
    public String getMedici(List<Medic> listaMedici) {
        String  x = "Lista de medici din cabinet este: \n";
        for(int i = 0; i <  listaMedici.size(); i++)
            x+=listaMedici.get(i).toString() + "\n";
        return x;
    }

    @Override
    public String getAsistenti(List<Asistent> listaAsistenti) {
        String  x = "Lista de asistenti din cabinet este: \n";
        for(int i = 0; i <  listaAsistenti.size(); i++)
            x+=listaAsistenti.get(i).toString() + "\n";
        return x;
    }

    @Override
    public String getPacienti(List<Pacient> listaPacienti) {
        String  x = "Lista de pacienti din cabinet este: \n";
        for(int i = 0; i <  listaPacienti.size(); i++)
            x+=listaPacienti.get(i).toString() + "\n";
        return x;
    }
}

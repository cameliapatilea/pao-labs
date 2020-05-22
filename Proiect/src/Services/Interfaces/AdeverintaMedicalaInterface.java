package Services.Interfaces;

import Entities.AdeverintaMedicala;
import Entities.ConcediuMedical;
import Entities.Pacient;

import java.sql.Connection;
import java.util.List;

public interface AdeverintaMedicalaInterface {
    void citesteScrieAudit(String comanda, String timp);
    AdeverintaMedicala getFromListById(List<AdeverintaMedicala> lista, int id);
    void afiseazaAdeverinte(List<AdeverintaMedicala> adeverinte);
    AdeverintaMedicala creareAdeverinta(Pacient pacient, String eliberatDe, String eliberatLa, boolean apt, String scop);
    AdeverintaMedicala updateScop(AdeverintaMedicala am, String scop);
    String getAdeverinteEliberateLaDataX(List<AdeverintaMedicala> lista, String data);


    List<AdeverintaMedicala> getAdeverinteFromDb(Connection connObj);
    void createAdeverintaDb(Connection connObj, AdeverintaMedicala am);
    void modificaAptDb(Connection connObj,int id,  boolean apt);
    void deleteAdeverintaDb(Connection connObj, int id);
}

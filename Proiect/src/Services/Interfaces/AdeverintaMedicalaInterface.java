package Services.Interfaces;

import Entities.AdeverintaMedicala;
import Entities.Pacient;

import java.util.List;

public interface AdeverintaMedicalaInterface {
    AdeverintaMedicala getFromListById(List<AdeverintaMedicala> lista, int id);
    AdeverintaMedicala creareAdeverinta(Pacient pacient, String eliberatDe, String eliberatLa, boolean apt, String scop);
    AdeverintaMedicala updateScop(AdeverintaMedicala am, String scop);
    String getAdeverinteEliberateLaDataX(List<AdeverintaMedicala> lista, String data);
}

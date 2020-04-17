package Services.Implementations;

import Entities.Pacient;
import Services.Interfaces.PacientInterface;

import java.util.ArrayList;
import java.util.List;

public class PacientService extends GeneralService<Pacient> implements PacientInterface {
    @Override
    public Pacient getFromListById(List<Pacient> lista, int id) {
        for(int i = 0; i < lista.size(); i++)
            if(lista.get(i).getId() == id)
                return lista.get(i);

        return null;
    }

    @Override
    public List<Pacient> adaugaAfectiune(int id, List<Pacient> pacienti, String afectiune) {
    for(int i = 0; i < pacienti.size(); i++)
    {
        if(pacienti.get(i).getID() == id)
        {
            pacienti.get(i).getAfectiuni().add(afectiune);
        }
    }
        return pacienti;
    }

    @Override
    public void afiseazaPacienti(List<Pacient> pacienti) {
        for(int i = 0; i < pacienti.size(); i++)
        {
            System.out.println("Pacient: ");
            System.out.println(pacienti.get(i).toString());
            System.out.println("==================================");
        }
    }

    @Override
    public Pacient adaugaAfectiuni(Pacient pacient, List<String> afectiuni) {
    pacient.getAfectiuni().addAll(afectiuni);


        return pacient;
    }

    @Override
    public Pacient updateAfectiune(Pacient pacient, String afectiuneVeche, String afectiuneNoua) {
        List<String> afectiuni =  pacient.getAfectiuni();
        for(int i = 0; i < afectiuni.size(); i++)
            if(afectiuni.get(i).toLowerCase().compareTo(afectiuneVeche.toLowerCase()) == 0)
            {
                afectiuni.remove(i);
                afectiuni.add(afectiuneNoua);
            }


        return pacient;
    }

    @Override
    public List<Pacient> stergeAfectiune(int id, List<Pacient> pacienti, String afectiune) {
        for(int i = 0; i < pacienti.size(); i++)
        {
            if(pacienti.get(i).getID() == id)
            {
                pacienti.get(i).getAfectiuni().remove(afectiune);
            }
        }



        return pacienti;
    }

    @Override
    public Pacient stergeAfectiuni(Pacient pacient, List<String> afectiuni, boolean stergeTot) {
       if(stergeTot == true)
           pacient.setAfectiuni(new ArrayList<String>());
       else
       {
           List<String> newAfectiuni = pacient.getAfectiuni();
           for(int i = 0; i <  afectiuni.size(); i++)
               newAfectiuni.remove(afectiuni.get(i));


       }
       return pacient;
    }

    @Override
    public Pacient updateNume(Pacient pacient, String nume) {
        pacient.setNume(nume);

        return pacient;
    }

    @Override
    public Pacient updateVarsta(Pacient pacient, int varsta) {
        pacient.setVarsta(varsta);

        return pacient;
    }

    @Override
    public List<Pacient> adaugaPacientLaLista(Pacient pacient, List<Pacient> pacienti) {
        pacienti.add(pacient);

        return pacienti;

    }

    @Override
    public Pacient crearePacient(int id, String nume, String prenume, String dataNasterii,int varsta, String gen, List<String> afectiuni) {
        Pacient p = new Pacient(id, nume, prenume, dataNasterii, varsta, gen, afectiuni);
        return p;
    }


}

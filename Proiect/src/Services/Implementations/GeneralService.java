package Services.Implementations;

import Services.Interfaces.GeneralInterface;

import java.util.List;

public class GeneralService<T> implements GeneralInterface<T> {
    @Override
    public T getById(int id){
        return null;
    }

    @Override
    public T getByIndex(List<T> lista, int idx) {
        return lista.get(idx);
    }

    @Override
    public List<T> getAll(List<T> lista) {
        return lista;
    }



}

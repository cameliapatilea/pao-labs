package Services.Interfaces;

import java.util.List;

public interface GeneralInterface<T> {
     T getById(int id);
     T getByIndex(List<T> lista, int idx);
     List<T> getAll(List<T> lista);

}

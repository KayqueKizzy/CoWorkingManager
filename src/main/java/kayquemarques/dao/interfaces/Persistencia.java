package kayquemarques.dao.interfaces;

import java.util.List;

public interface Persistencia<T> {

    void salvar(T entidade);

    void salvarTodos(List<T> lista);

    T buscarPorId(int id);

    List<T> buscarTodos();

    void remover(int id);
}

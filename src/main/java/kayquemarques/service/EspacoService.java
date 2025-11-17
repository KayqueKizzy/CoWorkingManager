package kayquemarques.service;

import kayquemarques.dao.interfaces.Persistencia;
import kayquemarques.model.Espaco;

import java.util.List;

public class EspacoService {

    private final Persistencia<Espaco> dao;

    public EspacoService(Persistencia<Espaco> dao) {
        this.dao = dao;
    }

    public void salvar(Espaco espaco) {
        if (espaco == null) {
            throw new IllegalArgumentException("Espaço não pode ser nulo.");
        }
        if (espaco.getNome() == null || espaco.getNome().isBlank()) {
            throw new IllegalArgumentException("O nome do espaço é obrigatório.");
        }
        if (espaco.getCapacidade() <= 0) {
            throw new IllegalArgumentException("A capacidade deve ser maior que zero.");
        }
        if (espaco.getPrecoPorHora() <= 0) {
            throw new IllegalArgumentException("O preço por hora deve ser positivo.");
        }

        dao.salvar(espaco);
    }

    public Espaco buscarPorId(int id) {
        return dao.buscarPorId(id);
    }

    public List<Espaco> buscarTodos() {
        return dao.buscarTodos();
    }

    public void remover(int id) {
        dao.remover(id);
    }
}

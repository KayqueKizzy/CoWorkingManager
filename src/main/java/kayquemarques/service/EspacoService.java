package kayquemarques.service;

import kayquemarques.dao.EspacoDAOJSON;
import kayquemarques.dao.EspacoDTO;
import kayquemarques.dao.interfaces.Persistencia;
import kayquemarques.model.Espaco;
import kayquemarques.exception.DadosInvalidosException;
import kayquemarques.exception.EspacoNaoEncontradoException;
import kayquemarques.service.utils.ConversorDTO;

import java.util.List;

public class EspacoService {

    private final Persistencia<EspacoDTO> dao;

    public EspacoService() {
        this.dao = new EspacoDAOJSON();
    }

    public void salvar(Espaco espaco) {
        if (espaco == null) {
            throw new DadosInvalidosException("Espaço não pode ser nulo.");
        }
        if (espaco.getNome() == null || espaco.getNome().isBlank()) {
            throw new DadosInvalidosException("O nome do espaço é obrigatório.");
        }
        if (espaco.getCapacidade() <= 0) {
            throw new DadosInvalidosException("A capacidade deve ser maior que zero.");
        }
        if (espaco.getPrecoPorHora() <= 0) {
            throw new DadosInvalidosException("O preço por hora deve ser positivo.");
        }

        dao.salvar(ConversorDTO.conversorEspacoToDTO(espaco));
    }
    public Espaco buscarPorId(int id) {
        Espaco e = ConversorDTO.conversorDTOToEspaco(dao.buscarPorId(id));
        if (e == null) {
            throw new EspacoNaoEncontradoException("Espaço não encontrado.");
        }
        return e;
    }

    public List<Espaco> buscarTodos() {
        return ConversorDTO.conversorListaDTOToListaEspaco(dao.buscarTodos());
    }

    public void remover(int id) {
        dao.remover(id);
    }
}


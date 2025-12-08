package kayquemarques.controller;

import kayquemarques.dao.EspacoDTO;
import kayquemarques.model.Espaco;
import kayquemarques.model.SalaDeReuniao;
import kayquemarques.model.CabineIndividual;
import kayquemarques.model.Auditorio;
import kayquemarques.service.EspacoService;

import java.util.List;

public class EspacoController {

    private final EspacoService service;

    public EspacoController() {
        this.service = new EspacoService();
    }

    public void cadastrarSalaDeReuniao(EspacoDTO dto) {

        Espaco espaco = new SalaDeReuniao(dto);

        service.salvar(espaco);
    }

    public void cadastrarCabine(EspacoDTO dto) {

        Espaco espaco = new CabineIndividual(dto);
        service.salvar(espaco);
    }

    public void cadastrarAuditorio(EspacoDTO dto) {

        Espaco espaco = new Auditorio(dto);
        service.salvar(espaco);
    }

    public List<Espaco> listar() {
        return service.buscarTodos();
    }

    public Espaco buscarPorId(int id) {
        return service.buscarPorId(id);
    }

    public void remover(int id) {
        service.remover(id);
    }
}

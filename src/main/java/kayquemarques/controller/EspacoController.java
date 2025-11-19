package kayquemarques.controller;

import kayquemarques.model.Espaco;
import kayquemarques.model.SalaDeReuniao;
import kayquemarques.model.CabineIndividual;
import kayquemarques.model.Auditorio;
import kayquemarques.service.EspacoService;

import java.util.List;

public class EspacoController {

    private final EspacoService service;

    public EspacoController(EspacoService service) {
        this.service = service;
    }

    public void cadastrarSalaDeReuniao(int id, String nome, int capacidade, boolean disponivel,
                                       double precoPorHora, boolean usaProjetor) {

        Espaco espaco = new SalaDeReuniao(id, nome, capacidade, disponivel, precoPorHora, usaProjetor);
        service.salvar(espaco);
    }

    public void cadastrarCabine(int id, String nome, int capacidade, boolean disponivel,
                                double precoPorHora) {

        Espaco espaco = new CabineIndividual(id, nome, capacidade, disponivel, precoPorHora);
        service.salvar(espaco);
    }

    public void cadastrarAuditorio(int id, String nome, int capacidade, boolean disponivel,
                                   double precoPorHora, boolean temPalco, int capacidadeExtra) {

        Espaco espaco = new Auditorio(id, nome, capacidade, disponivel, precoPorHora, temPalco, capacidadeExtra);
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

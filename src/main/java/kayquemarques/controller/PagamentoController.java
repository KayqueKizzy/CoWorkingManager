package kayquemarques.controller;

import kayquemarques.model.Pagamento;
import kayquemarques.model.Reserva;
import kayquemarques.service.PagamentoService;

import java.time.LocalDate;
import java.util.List;

public class PagamentoController {

    private final PagamentoService service;

    public PagamentoController(PagamentoService service) {
        this.service = service;
    }

    public void registrarPagamento(int id, int idReserva, double valorPago, LocalDate data, String metodo) {

        Reserva reserva = service.buscarReserva(idReserva);

        Pagamento pagamento = new Pagamento(
                id,
                reserva,
                valorPago,
                data,
                metodo
        );

        service.registrarPagamento(pagamento);
    }

    public Pagamento buscarPorId(int id) {
        return service.buscarPorId(id);
    }

    public List<Pagamento> listar() {
        return service.buscarTodos();
    }

    public void remover(int id) {
        service.remover(id);
    }
}

package kayquemarques.service;

import kayquemarques.dao.interfaces.Persistencia;
import kayquemarques.exception.PagamentoInvalidoException;
import kayquemarques.exception.ReservaNaoEncontradaException;
import kayquemarques.model.Pagamento;
import kayquemarques.model.Reserva;

import java.time.LocalDate;

public class PagamentoService {

    private final Persistencia<Pagamento> pagamentoDAO;
    private final Persistencia<Reserva> reservaDAO;

    public PagamentoService(Persistencia<Pagamento> pagamentoDAO,
                            Persistencia<Reserva> reservaDAO) {
        this.pagamentoDAO = pagamentoDAO;
        this.reservaDAO = reservaDAO;
    }

    public void registrarPagamento(Pagamento pagamento) {

        if (pagamento.getValorPago() <= 0)
            throw new PagamentoInvalidoException("Valor do pagamento inválido.");

        Reserva r = reservaDAO.buscarPorId(pagamento.getReserva().getId());

        if (r == null)
            throw new ReservaNaoEncontradaException("Reserva não encontrada.");

        if (pagamento.getValorPago() != r.getValorTotal())
            throw new PagamentoInvalidoException("Valor pago diferente do valor total da reserva.");

        if (pagamento.getMetodo() == null || pagamento.getMetodo().isBlank())
            throw new PagamentoInvalidoException("Método de pagamento inválido.");

        if (pagamento.getDataPagamento().isAfter(LocalDate.now()))
            throw new PagamentoInvalidoException("Data de pagamento futura não é permitida.");


        pagamentoDAO.salvar(pagamento);
    }

    public Reserva buscarReserva(int idReserva) {
        return reservaDAO.buscarPorId(idReserva);
    }
    public Pagamento buscarPorId(int id) {
        return pagamentoDAO.buscarPorId(id);
    }

    public void remover(int id) {
        pagamentoDAO.remover(id);
    }

    public java.util.List<Pagamento> buscarTodos() {
        return pagamentoDAO.buscarTodos();
    }
}
package kayquemarques.service;

import kayquemarques.dao.ReservaDAOJSON;
import kayquemarques.dao.ReservaDTO;
import kayquemarques.dao.interfaces.Persistencia;
import kayquemarques.exception.DadosInvalidosException;
import kayquemarques.exception.ReservaNaoEncontradaException;
import kayquemarques.exception.ReservaSobrepostaException;
import kayquemarques.model.Reserva;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

public class ReservaService {

    private final Persistencia<Reserva> dao;

    public ReservaService() {
        this.dao = new ReservaDAOJSON();
    }

    public void salvar(ReservaDTO dto) {
        Reserva novaReserva = new Reserva(dto);
        if (novaReserva == null)
            throw new DadosInvalidosException("A reserva não pode ser nula.");

        if (novaReserva.getEspaco() == null)
            throw new DadosInvalidosException("A reserva precisa de um espaço.");

        if (novaReserva.calcularDuracaoHoras() <= 0)
            throw new DadosInvalidosException("A duração deve ser maior que zero.");

        List<Reserva> existentes = dao.buscarTodos();

        for (Reserva r : existentes) {

            if (r.getId() == novaReserva.getId())
                continue;

            boolean mesmoEspaco =
                    r.getEspaco().getId() == novaReserva.getEspaco().getId();

            if (!mesmoEspaco)
                continue;

            boolean conflito =
                    novaReserva.getInicio().isBefore(r.getFim()) &&
                            novaReserva.getFim().isAfter(r.getInicio());

            if (conflito)
                throw new ReservaSobrepostaException("O espaço já está reservado nesse horário.");
        }

        dao.salvar(novaReserva);
    }

    public double cancelarReserva(int idReserva) {

        Reserva reserva = dao.buscarPorId(idReserva);

        if (reserva == null)
            throw new ReservaNaoEncontradaException("Reserva não encontrada.");

        long horasAntes =
                Duration.between(LocalDateTime.now(), reserva.getInicio()).toHours();

        double multa = 0;

        if (horasAntes < 24) {
            multa = reserva.getValorTotal() * 0.20;
        }

        reserva.cancelar();
        dao.salvar(reserva);

        return multa;
    }

    public Reserva buscarPorId(int id) {
        return dao.buscarPorId(id);
    }

    public List<Reserva> buscarTodos() {
        return dao.buscarTodos();
    }

    public void remover(int id) {
        dao.remover(id);
    }
}
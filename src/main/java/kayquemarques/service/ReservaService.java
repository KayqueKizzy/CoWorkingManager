package kayquemarques.service;

import kayquemarques.dao.interfaces.Persistencia;
import kayquemarques.model.Reserva;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

public class ReservaService {

    private final Persistencia<Reserva> dao;

    public ReservaService(Persistencia<Reserva> dao) {
        this.dao = dao;
    }

    public void salvar(Reserva novaReserva) {

        if (novaReserva == null)
            throw new IllegalArgumentException("A reserva não pode ser nula.");

        if (novaReserva.getEspaco() == null)
            throw new IllegalArgumentException("A reserva deve ter um espaço.");

        if (novaReserva.getUsuario() == null)
            throw new IllegalArgumentException("A reserva deve ter um usuário.");

        if (novaReserva.getHoras() <= 0)
            throw new IllegalArgumentException("As horas devem ser maiores que zero.");

        List<Reserva> existentes = dao.buscarTodos();

        for (Reserva r : existentes) {

            if (r.getId() == novaReserva.getId())
                continue;

            boolean mesmoEspaco = r.getEspaco().getId() == novaReserva.getEspaco().getId();

            if (!mesmoEspaco)
                continue;

            boolean conflito =
                    novaReserva.getInicio().isBefore(r.getFim()) &&
                            novaReserva.getFim().isAfter(r.getInicio());

            if (conflito) {
                throw new IllegalArgumentException("O espaço já está reservado neste horário.");
            }
        }

        dao.salvar(novaReserva);
    }

    public double cancelarReserva(int idReserva) {

        Reserva reserva = dao.buscarPorId(idReserva);

        if (reserva == null)
            throw new IllegalArgumentException("Reserva não encontrada.");

        long horasAntes =
                Duration.between(LocalDateTime.now(), reserva.getInicio()).toHours();

        double multa = 0;

        if (horasAntes < 24) {
            multa = reserva.getValorTotal() * 0.20;
        }

        reserva.setCancelada(true);
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

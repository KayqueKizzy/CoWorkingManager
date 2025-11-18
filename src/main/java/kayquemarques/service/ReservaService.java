package kayquemarques.service;

import kayquemarques.dao.interfaces.Persistencia;
import kayquemarques.model.Reserva;

import java.time.LocalDateTime;
import java.util.List;

public class ReservaService {

    private final Persistencia<Reserva> dao;

    public ReservaService(Persistencia<Reserva> dao) {
        this.dao = dao;
    }

    public void salvar(Reserva nova) {

        if (nova == null)
            throw new IllegalArgumentException("Reserva inválida.");

        if (nova.getEspaco() == null)
            throw new IllegalArgumentException("Espaço inválido.");

        if (nova.getInicio() == null || nova.getFim() == null)
            throw new IllegalArgumentException("Datas inválidas.");

        if (!nova.getFim().isAfter(nova.getInicio()))
            throw new IllegalArgumentException("A data final deve ser depois da inicial.");

        List<Reserva> existentes = dao.buscarTodos();

        for (Reserva r : existentes) {

            if (r.getId() == nova.getId())
                continue;

            boolean mesmoEspaco = r.getEspaco().getId() == nova.getEspaco().getId();

            if (!mesmoEspaco)
                continue;

            boolean conflito =
                    nova.getInicio().isBefore(r.getFim())
                            && nova.getFim().isAfter(r.getInicio());

            if (conflito)
                throw new IllegalArgumentException("O espaço já está reservado neste horário.");
        }

        dao.salvar(nova);
    }

    public double cancelarReserva(int idReserva) {

        Reserva r = dao.buscarPorId(idReserva);

        if (r == null)
            throw new IllegalArgumentException("Reserva não encontrada.");

        if (!r.getStatus().equals("ATIVA"))
            throw new IllegalArgumentException("A reserva não pode ser cancelada.");

        double multa = r.calcularMultaCancelamento();

        r.cancelar();

        dao.salvar(r);

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
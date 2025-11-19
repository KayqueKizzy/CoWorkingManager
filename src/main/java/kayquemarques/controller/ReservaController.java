package kayquemarques.controller;

import kayquemarques.model.Espaco;
import kayquemarques.model.Reserva;
import kayquemarques.service.EspacoService;
import kayquemarques.service.ReservaService;

import java.time.LocalDateTime;
import java.util.List;

public class ReservaController {

    private final ReservaService reservaService;
    private final EspacoService espacoService;

    public ReservaController(ReservaService reservaService, EspacoService espacoService) {
        this.reservaService = reservaService;
        this.espacoService = espacoService;
    }

    public void criarReserva(int idReserva,
                             int idEspaco,
                             LocalDateTime inicio,
                             LocalDateTime fim) {

        Espaco espaco = espacoService.buscarPorId(idEspaco);

        if (espaco == null)
            throw new IllegalArgumentException("Espaço não encontrado.");

        Reserva reserva = new Reserva(idReserva, espaco, inicio, fim);

        reservaService.salvar(reserva);
    }

    public double cancelarReserva(int id) {
        return reservaService.cancelarReserva(id);
    }

    public Reserva buscarPorId(int id) {
        return reservaService.buscarPorId(id);
    }

    public List<Reserva> listarReservas() {
        return reservaService.buscarTodos();
    }

    public void removerReserva(int id) {
        reservaService.remover(id);
    }
}

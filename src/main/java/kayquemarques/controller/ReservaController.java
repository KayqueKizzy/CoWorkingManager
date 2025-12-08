package kayquemarques.controller;

import kayquemarques.dao.EspacoDTO;
import kayquemarques.dao.ReservaDTO;
import kayquemarques.model.Reserva;
import kayquemarques.service.EspacoService;
import kayquemarques.service.ReservaService;
import kayquemarques.service.utils.ConversorDTO;
import java.util.List;

public class ReservaController {

    private final ReservaService reservaService;
    private final EspacoService espacoService;

    public ReservaController() {
        this.reservaService = new ReservaService();
        this.espacoService = new EspacoService();
    }
    public ReservaController(ReservaService reservaService, EspacoService espacoService) {
        this.reservaService = reservaService;
        this.espacoService = espacoService;
    }

    public void criarReserva(ReservaDTO reservaDTO, Integer idEspaco) {

        EspacoDTO espaco = ConversorDTO.conversorEspacoToDTO(espacoService.buscarPorId(idEspaco));

        reservaDTO.setEspaco(espaco);
        if (espaco == null)
            throw new IllegalArgumentException("Espaço não encontrado.");



        reservaService.salvar(reservaDTO);
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

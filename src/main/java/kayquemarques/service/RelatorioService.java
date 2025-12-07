package kayquemarques.service;

import kayquemarques.dao.interfaces.Persistencia;
import kayquemarques.model.Espaco;
import kayquemarques.model.Pagamento;
import kayquemarques.model.Reserva;
import kayquemarques.service.utils.ConversorDTO;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

public class RelatorioService {

    private final Persistencia<Reserva> reservaDAO;
    private final Persistencia<Pagamento> pagamentoDAO;

    public RelatorioService(Persistencia<Reserva> reservaDAO,
                            Persistencia<Pagamento> pagamentoDAO) {
        this.reservaDAO = reservaDAO;
        this.pagamentoDAO = pagamentoDAO;
    }

    public List<Reserva> reservasPorPeriodo(LocalDateTime inicio, LocalDateTime fim) {
        return reservaDAO.buscarTodos().stream()
                .filter(r -> !(r.getFim().isBefore(inicio)
                        || r.getInicio().isAfter(fim)))
                .collect(Collectors.toList());
    }

    public double faturamentoPorPeriodo(LocalDateTime inicio, LocalDateTime fim) {
        return pagamentoDAO.buscarTodos().stream()
                .filter(p -> !p.getDataPagamento().atStartOfDay().isBefore(inicio)
                        && !p.getDataPagamento().atStartOfDay().isAfter(fim))
                .mapToDouble(Pagamento::getValorPago)
                .sum();
    }

    public Map<String, Double> faturamentoPorTipoDeEspaco() {
        Map<String, Double> mapa = new HashMap<>();

        for (Pagamento p : pagamentoDAO.buscarTodos()) {
            String tipo = p.getReserva().getEspaco().getClass().getSimpleName();
            mapa.put(tipo, mapa.getOrDefault(tipo, 0.0) + p.getValorPago());
        }

        return mapa;
    }

    public Map<Espaco, Long> utilizacaoDosEspacos() {
        Map<Espaco, Long> mapa = new HashMap<>();

        for (Reserva r : reservaDAO.buscarTodos()) {
            Espaco e = ConversorDTO.conversorDTOToEspaco(r.getEspaco());
            mapa.put(e, mapa.getOrDefault(e, 0L) + 1L);
        }

        return mapa;
    }

    public List<Map.Entry<Espaco, Long>> topEspacosMaisReservados(int limite) {
        Map<Espaco, Long> mapa = utilizacaoDosEspacos();

        return mapa.entrySet().stream()
                .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
                .limit(limite)
                .collect(Collectors.toList());
    }
}

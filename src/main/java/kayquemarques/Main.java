package kayquemarques;

import kayquemarques.controller.*;
import kayquemarques.dao.*;
import kayquemarques.model.*;
import kayquemarques.service.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Main {

    public static void main(String[] args) {

        EspacoDAOJSON espacoDAO = new EspacoDAOJSON();
        ReservaDAOJSON reservaDAO = new ReservaDAOJSON();
        PagamentoDAOJSON pagamentoDAO = new PagamentoDAOJSON();

        EspacoService espacoService = new EspacoService(espacoDAO);
        ReservaService reservaService = new ReservaService(reservaDAO);
        PagamentoService pagamentoService = new PagamentoService(pagamentoDAO, reservaDAO);
        RelatorioService relatorioService = new RelatorioService(reservaDAO, pagamentoDAO);

        EspacoController espacoController = new EspacoController(espacoService);
        ReservaController reservaController = new ReservaController(reservaService, espacoService);
        PagamentoController pagamentoController = new PagamentoController(pagamentoService);

        espacoController.cadastrarSalaDeReuniao(1, "Sala Azul", 10, true, 50.0, true);
        espacoController.cadastrarCabine(2, "Cabine 1", 1, true, 20.0);
        espacoController.cadastrarAuditorio(3, "Auditório Principal", 100, true, 200.0, true, 200);

        LocalDateTime inicio = LocalDateTime.now().plusHours(2);
        LocalDateTime fim = inicio.plusHours(2);

        reservaController.criarReserva(1, 1, inicio, fim);

        pagamentoController.registrarPagamento(
                1,
                1,
                reservaController.buscarPorId(1).getValorTotal(),
                LocalDate.now(),
                "PIX"
        );

        System.out.println("Reservas:");
        for (Reserva r : reservaController.listarReservas()) {
            System.out.println(r);
        }

        System.out.println("Pagamentos:");
        for (Pagamento p : pagamentoController.listar()) {
            System.out.println(p);
        }

        System.out.println("Faturamento total: R$ " +
                relatorioService.faturamentoPorPeriodo(
                        LocalDateTime.now().minusDays(1),
                        LocalDateTime.now().plusDays(1)
                )
        );
    }
}
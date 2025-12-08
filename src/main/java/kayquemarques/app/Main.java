package kayquemarques.app;

import kayquemarques.controller.*;
import kayquemarques.dao.EspacoDTO;
import kayquemarques.dao.ReservaDTO;
import kayquemarques.service.*;
import kayquemarques.view.EspacoView;
import kayquemarques.view.ReservaView;
import java.util.Scanner;
import static kayquemarques.view.utils.EspacoViewUtils.*;
import static kayquemarques.view.utils.ReservaViewUtils.criarNovaReserva;
import static kayquemarques.view.utils.ReservaViewUtils.exibirMenuReserva;

public class Main {

    public static void main(String[] args) {
        boolean controle = true;
        EspacoController espacoController;
        ReservaController reservaController;
        PagamentoController pagamentoController;
        EspacoView espacoView = new EspacoView();
        ReservaView reservaView = new ReservaView();
        EspacoDTO dto;
        EspacoView view = new EspacoView();

        while (controle) {
            menuPrincipal();
            Scanner sc = new Scanner(System.in);
            String escolha = sc.nextLine();

            switch (escolha) {
                case "1":
                    exibirMenuEspaco();
                    String escolhaEspaco = sc.nextLine();
                    espacoController = new EspacoController();
                    boolean condicaoEscolhaEspaco = true;

                    while (condicaoEscolhaEspaco){
                        switch (escolhaEspaco) {

                            case "1":
                                espacoController = new EspacoController();
                                criarCadastroEspaco();
                                String tipoEspaco = sc.nextLine();
                                dto = view.criarEspaco(tipoEspaco);

                                switch (tipoEspaco) {
                                    case "1":
                                       espacoController.cadastrarCabine(dto);
                                        break;
                                    case "2":
                                        espacoController.cadastrarAuditorio(dto);
                                        break;
                                    case "3":
                                        espacoController.cadastrarSalaDeReuniao(dto);
                                        break;
                                    case "4":
                                        System.out.println("Voltando ao menu De Espaços.");
                                        condicaoEscolhaEspaco = false;
                                        break;
                                    case "5":
                                        System.out.println("Voltando ao menu central.");
                                        controle = true;
                                        condicaoEscolhaEspaco = false;
                                        break;
                                    default:
                                        System.out.println("Tipo de espaço inválido.");
                                }
                                break;
                            case "2":
                                System.out.println("Listar Espaços selecionado.");
                                espacoController.listar().forEach(espaco -> {
                                    System.out.println(espaco);
                                });
                                break;
                            case "3":
                                System.out.println("Atualizar Dados do Espaço: ");


                                break;
                            case "4":
                                System.out.println("Remover Espaço selecionado.");
                                removendoEspaco();
                                espacoController.remover(view.solicitarIdEspaco());

                                break;
                            case "5":
                                System.out.println("Buscar Espaço por ID selecionado.");
                                buscandoEspacoPorId();
                                System.out.println(espacoController.buscarPorId(view.solicitarIdEspaco()));
                                break;
                            case "6":
                                System.out.println("Voltando ao menu principal.");
                                break;
                            default:
                                System.out.println("Opção inválida. Por favor, tente novamente.");
                        }
                        System.out.println("gostaria de continiar no gerenciamento de espaços? (s/n)");
                        String resposta = sc.nextLine();
                        if (resposta.equalsIgnoreCase("n")) {
                            condicaoEscolhaEspaco = false;
                        }
                    }
                case "2":
                    boolean condicaoEscolhaReserva = true;
                    reservaController = new ReservaController();

                    while (condicaoEscolhaReserva){
                        exibirMenuReserva();
                        String escolhaReserva = sc.nextLine();
                        ReservaDTO reservaDTO;
                        switch (escolhaReserva) {
                            case "1":
                                criarNovaReserva();
                                reservaDTO = reservaView.criarReserva();

                                reservaController.criarReserva(reservaDTO, 2);
                                System.out.println("Reserva adicionada com sucesso!");
                                break;
                            case "2":
                                System.out.println("Listar Reservas selecionado.");
                                // Aqui você pode adicionar a lógica para listar reservas
                                break;
                            case "3":
                                System.out.println("Remover Reserva selecionado.");
                                // Aqui você pode adicionar a lógica para remover uma reserva
                                break;
                            case "4":
                                System.out.println("Voltando ao menu principal.");
                                condicaoEscolhaReserva = false;
                                break;
                            default:
                                System.out.println("Opção inválida. Por favor, tente novamente.");
                        }
                    }
                    break;
                case "3":
                    // Sair
                    System.out.println("Saindo do sistema. Até logo!");
                    controle = false;
                    break;
                default:
                    System.out.println("Opção inválida. Por favor, tente novamente.");
            }
        }
    }
}
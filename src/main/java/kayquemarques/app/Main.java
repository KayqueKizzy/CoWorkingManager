package kayquemarques.app;

import kayquemarques.controller.*;
import kayquemarques.service.*;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        boolean controle = true;
        EspacoController espacoController;
        ReservaController reservaController;
        PagamentoController pagamentoController;

        while (controle) {
            System.out.println("Bem-vindo ao sistema de gerenciamento de espaços e reservas!" +
                    "nEscolha uma opção:" +
                    "\n1. Gerenciar Espaços" +
                    "\n2. Gerenciar Reservas" +
                    "\n3. Sair");
            Scanner sc = new Scanner(System.in);
            String escolha = sc.nextLine();

            switch (escolha) {
                case "1":
                    System.out.println("Gerenciamento de Espaços selecionado." +
                            "O que você gostaria de fazer?" +
                            "\n1. Adicionar Espaço" +
                            "\n2. Listar Espaços" +
                            "\n3. Remover Espaço" +
                            "\n4. Atualizar espaço" +
                            "\n5  buscar espaco pelo ID(codigo)" +
                            "\n6. Voltar ao menu principal" +
                            "digite a opção:");
                    String escolhaEspaco = sc.nextLine();
                    boolean condicaoEscolhaEspaco = true;
                    while (condicaoEscolhaEspaco){
                        switch (escolhaEspaco) {
                            case "1":
                                espacoController = new EspacoController( new EspacoService());

                                System.out.println("Adicionar Espaço selecionado.");
                                System.out.println("Escolha o tipo de espaço a adicionar:" +
                                        "\n1. Sala de Reunião" +
                                        "\n2. Cabine Individual" +
                                        "\n3. Auditório" +
                                        "\n4  Voltar ao menu anterior");
                                String tipoEspaco = sc.nextLine();
                                System.out.println("Digite o nome do espaço:");
                                String nome = sc.nextLine();
                                System.out.println("Digite a capacidade do espaço:");
                                int capacidade = Integer.parseInt(sc.nextLine());
                                System.out.println("O espaço está disponível? (true/false):");
                                boolean disponivel = Boolean.parseBoolean(sc.nextLine());
                                System.out.println("Digite o preço por hora:");
                                double precoPorHora = Double.parseDouble(sc.nextLine());
                                switch (tipoEspaco) {
                                    case "1":
                                        System.out.println("A sala de reunião usa projetor? (true/false):");
                                        boolean usaProjetor = Boolean.parseBoolean(sc.nextLine());
                                        espacoController.cadastrarSalaDeReuniao(0, nome, capacidade, disponivel, precoPorHora, usaProjetor);
                                        break;
                                    case "2":
                                        espacoController.cadastrarCabine(0, nome, capacidade, disponivel, precoPorHora);
                                        break;
                                    case "3":
                                        System.out.println("O auditório tem palco? (true/false):");
                                        boolean temPalco = Boolean.parseBoolean(sc.nextLine());
                                        System.out.println("Digite a capacidade extra do auditório:");
                                        int capacidadeExtra = Integer.parseInt(sc.nextLine());
                                        espacoController.cadastrarAuditorio(0, nome, capacidade, disponivel, precoPorHora, temPalco, capacidadeExtra);
                                        break;
                                    default:
                                        System.out.println("Tipo de espaço inválido.");
                                }
                                break;
                            case "2":
                                System.out.println("Listar Espaços selecionado.");
                                // Aqui você pode adicionar a lógica para listar espaços
                                break;
                            case "3":
                                System.out.println("Remover Espaço selecionado.");
                                // Aqui você pode adicionar a lógica para remover um espaço
                                break;
                            case "4":
                                System.out.println("Atualizar Espaço selecionado.");
                                // Aqui você pode adicionar a lógica para atualizar um espaço
                                break;
                            case "5":
                                System.out.println("Buscar Espaço por ID selecionado.");
                                // Aqui você pode adicionar a lógica para buscar um espaço pelo ID
                                break;
                            case "6":
                                System.out.println("Voltando ao menu principal.");
                                break;
                            default:
                                System.out.println("Opção inválida. Por favor, tente novamente.");
                        }
                        break;
                    }
                case "2":
                    System.out.println("Gerenciamento de Reservas selecionado.");
                    boolean condicaoEscolhaReserva = true;
                    reservaController = new ReservaController();

                    while (condicaoEscolhaReserva){
                        System.out.println("O que você gostaria de fazer?" +
                                "\n1. Adicionar Reserva" +
                                "\n2. Listar Reservas" +
                                "\n3. Remover Reserva" +
                                "\n4. Voltar ao menu principal" +
                                "digite a opção:");

                        String escolhaReserva = sc.nextLine();

                        switch (escolhaReserva) {
                            case "1":
                                System.out.println("Adicionar Reserva selecionado.");


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
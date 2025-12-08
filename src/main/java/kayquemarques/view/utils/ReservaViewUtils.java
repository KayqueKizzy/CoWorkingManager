package kayquemarques.view.utils;

public class ReservaViewUtils {
    public static void exibirMenuReserva() {
        System.out.print(
                "\n╔═══════════════════════════════════════╗\n" +
                        "║      GERENCIAMENTO DE RESERVAS       ║\n" +
                        "╠══════════════════════════════════════╣\n" +
                        "║      1️  - Criar Nova Reserva         ║\n" +
                        "║      2️  - Listar Todas as Reservas   ║\n" +
                        "║      3️  - Buscar Reserva por ID      ║\n" +
                        "║      4️  - Remover (Cancelar) Reserva ║\n" +
                        "║      0️  - Voltar ao Menu Principal   ║\n" +
                        "╚══════════════════════════════════════╝\n" +
                        "Selecione uma Opção (0-4) ➡: "
        );
    }
    public static void criarNovaReserva() {
        System.out.print(
                "\n╔═══════════════════════════════════════╗\n" +
                        "║        CRIAÇÃO DE NOVA RESERVA        ║\n" +
                        "╠═══════════════════════════════════════╣\n" +
                        "║   Por favor, insira os detalhes da    ║\n" +
                        "║          nova reserva abaixo:         ║\n" +
                        "╚═══════════════════════════════════════╝\n"
        );

    }
}

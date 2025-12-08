package kayquemarques.view.utils;

public class EspacoViewUtils {

    public static void exibirMenuEspaco() {
        System.out.print(
                "\n==============================\n" +
                        "|       ÁREA DE ESPAÇOS      |\n" +
                        "+============================+\n"
        );
        System.out.print(
                "\n╔═══════════════════════════════╗\n" +
                        "║   GERENCIAMENTO DE ESPAÇOS    ║\n" +
                        "╠═══════════════════════════════╣\n" +
                        "║ 1  - Cadastrar Novo Espaço    ║\n" +
                        "║ 2️  - Listar Todos os Espaços  ║\n" +
                        "║ 3️  - Atualizar Dados do Espaço║\n" +
                        "║ 4️  - Remover Espaço           ║\n" +
                        "║ 5️  - Buscar Espaço por ID     ║\n" +
                        "║ 0️  - Voltar ao Menu Principal ║\n" +
                        "╚═══════════════════════════════╝\n" +
                        "Selecione uma Opção (0-5) ➡: "
        );

    }

    public static void cadastroSucesso() {
        System.out.println("Espaço cadastrado com sucesso!");
    }

    public static void criarCadastroEspaco() {
        System.out.print(
                "\n╔═══════════════════════════════════════════════╗\n" +
                        "║   VOCÊ DESEJA CADASTRAR QUAL TIPO DE ESPAÇO   ║\n" +
                        "╠═══════════════════════════════════════════════╣\n" +
                        "║          1  - Cadastrar cabine individual     ║\n" +
                        "║          2️  - Cadastrar Auditorio             ║\n" +
                        "║          3️  - Cadastrar sala de reunião       ║\n" +
                        "║          4  - Voltar ao Menu de Espaço        ║\n" +
                        "║          0️  - Voltar ao Menu Principal        ║\n" +
                        "╚═══════════════════════════════════════════════╝\n" +
                        "Selecione uma Opção (0-4) ➡: "
        );

    }
    public static void menuPrincipal(){
        System.out.print(
                "\n╔════════════════════════════════════╗\n" +
                        "║          COWORKING MANAGER         ║\n" +
                        "╠════════════════════════════════════╣\n" +
                        "║      1️  - Gerenciar Espaços        ║\n" +
                        "║      2️  - Gerenciar Reservas       ║\n" +
                        "║      0  - Sair do Sistema          ║\n" +
                        "╚════════════════════════════════════╝\n" +
                        "Selecione uma Opção (0-2) ➡: "
        );
    }
    public static void removendoEspaco(){
        System.out.println("DIGITE O ID DO ESPAÇO QUE DESEJA REMOVER: ");
    }
    public static void atualizandoEspaco(){
        System.out.println("DIGITE O ID DO ESPAÇO QUE DESEJA ATUALIZAR: ");
    }
    public static void  buscandoEspacoPorId(){
        System.out.println("DIGITE O ID DO ESPAÇO QUE DESEJA BUSCAR: ");
    }
}

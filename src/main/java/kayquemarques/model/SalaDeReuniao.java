package kayquemarques.model;

import kayquemarques.dao.EspacoDTO;

public class SalaDeReuniao extends Espaco {
    private String TIPO = "Sala de Reuniao";
    private boolean usaProjetor;

    public SalaDeReuniao() {
    }

    public SalaDeReuniao(int id, String nome, int capacidade, boolean disponivel,
                         double precoPorHora, boolean usaProjetor) {
        super(id, nome, capacidade, disponivel, precoPorHora);
        this.usaProjetor = usaProjetor;
        this.TIPO = TIPO;
    }

    public SalaDeReuniao(EspacoDTO espacoDTO) {
        super(espacoDTO);
        this.usaProjetor = espacoDTO.isUsaProjetor();
        this.TIPO = TIPO;
    }

    public boolean isUsaProjetor() {
        return usaProjetor;
    }

    public double calcularCustoReserva(int horas) {
        double custo = getPrecoPorHora() * horas;
        if (usaProjetor) {
            custo += 15.0;
        }
        return custo;
    }

    @Override
    public String toString() {
        String detalhesProjetor = this.usaProjetor ? "Sim (+R$ 15,00)" : "Não";

        return String.format(
                "%s\n" +
                        "| Detalhes: Projetor incluso? %s",
                super.toString(), // Chama a representação da classe Espaco
                detalhesProjetor
        );
    }
}
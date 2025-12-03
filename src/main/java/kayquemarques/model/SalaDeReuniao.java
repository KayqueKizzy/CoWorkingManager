package kayquemarques.model;

public class SalaDeReuniao extends Espaco{

        private boolean usaProjetor;

    public SalaDeReuniao() { }

        public SalaDeReuniao(int id, String nome, int capacidade, boolean disponivel,
                             double precoPorHora, boolean usaProjetor) {
            super(id, nome, capacidade, disponivel, precoPorHora);
            this.usaProjetor = usaProjetor;
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
            return getNome() + " (Projetor: " + (usaProjetor ? "Sim" : "Não") + ")";
        }
    }


















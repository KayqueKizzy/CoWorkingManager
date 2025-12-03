package kayquemarques.model;

public class CabineIndividual extends Espaco {

    public CabineIndividual() { }

    public CabineIndividual(int id, String nome, int capacidade, boolean disponivel, double precoPorHora) {
        super(id, nome, capacidade, disponivel, precoPorHora);
    }


    public double calcularCustoReserva(int horas) {
        double custo = getPrecoPorHora() * horas;
        if (horas > 4) {
            custo = custo * 0.90;
        }
        return custo;
    }
    @Override
    public String toString() {
        return getNome() + " (Cabine Individual)";
    }




























}

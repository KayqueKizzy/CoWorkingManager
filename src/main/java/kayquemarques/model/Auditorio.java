package kayquemarques.model;

public class Auditorio extends Espaco{

    public Auditorio(int id, String nome, int capacidade, boolean disponivel, double precoPorHora) {
        super(id, nome, capacidade, disponivel, precoPorHora);
    }


    public double calcularCustoReserva(int horas) {
        double custo = getPrecoPorHora() * horas;
        custo += 100.0;
        return custo;
    }


    public String toString() {
        return getNome() + " (Auditório)";
    }
}









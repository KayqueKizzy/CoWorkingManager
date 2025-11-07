package kayquemarques.model;

public class CabineIndividual extends Espaco {

    public CabineIndividual(int id, String nome, int capacidade, boolean disponivel, double precoPorHora) {
        super(id, nome, capacidade, disponivel, precoPorHora);
    }


    public double calcularCustoReserva(int horas) {
        double custo = getPrecoPorHora() * horas;
        if (horas > 4) {               // desconto quando a reserva for superior a 4 horas
            custo = custo * 0.90;      // aplica 10% de desconto
        }
        return custo;
    }






























}

package kayquemarques.model;

import kayquemarques.dao.EspacoDTO;

public class CabineIndividual extends Espaco {
    private String TIPO = "Cabine Individual";
    public CabineIndividual() { }

    public CabineIndividual(int id, String nome, int capacidade, boolean disponivel, double precoPorHora) {
        super(id, nome, capacidade, disponivel, precoPorHora);
        this.TIPO = TIPO;
    }
    public CabineIndividual(String nome, int capacidade, boolean disponivel, double precoPorHora) {
        super( nome, capacidade, disponivel, precoPorHora);
        this.TIPO = TIPO;
    }
    public CabineIndividual(EspacoDTO espacoDTO) {
        super(espacoDTO);
        this.TIPO = TIPO;
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
        return "CabineIndividual{}";
    }
}


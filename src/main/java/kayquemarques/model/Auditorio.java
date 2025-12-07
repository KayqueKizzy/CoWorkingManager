package kayquemarques.model;

import kayquemarques.dao.EspacoDTO;

public class Auditorio extends Espaco{

    private boolean temPalco;
    private int capacidadeExtra;
    private String TIPO = "Auditório";
    public Auditorio() { }

    public Auditorio(int id, String nome, int capacidade, boolean disponivel,
                     double precoPorHora, boolean temPalco, int capacidadeExtra) {
        super(id, nome, capacidade, disponivel, precoPorHora);
        this.temPalco = temPalco;
        this.capacidadeExtra = capacidadeExtra;
        this.TIPO = TIPO;
    }

    public Auditorio(EspacoDTO espacoDTO) {
        super(espacoDTO);
        this.temPalco = espacoDTO.isTemPalco();
        this.capacidadeExtra = espacoDTO.getCapacidadeExtra();
        this.TIPO = TIPO;
    }
    public boolean isTemPalco() {
        return temPalco;
    }

    public int getCapacidadeExtra() {
        return capacidadeExtra;
    }

    @Override
    public double calcularCustoReserva(int horas) {
        double custo = getPrecoPorHora() * horas;


        custo += 100.0;

        return custo;
    }

    @Override
    public String toString() {
        return getNome() + " (Auditório)";
    }
}








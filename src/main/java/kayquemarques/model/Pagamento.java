package kayquemarques.model;

import java.time.LocalDate;

public class Pagamento {
    private int id;
    private Reserva reserva;
    private double valorPago;
    private LocalDate dataPagamento;
    private String metodo;

    public Pagamento() {}

    public Pagamento(int id, Reserva reserva, double valorPago, LocalDate dataPagamento, String metodo) {
        if (reserva == null) throw new IllegalArgumentException("Reserva inválida");
        if (valorPago <= 0) throw new IllegalArgumentException("Valor deve ser maior que zero");
        if (dataPagamento == null) throw new IllegalArgumentException("Data inválida");
        if (metodo == null || metodo.isBlank()) throw new IllegalArgumentException("Método inválido");

        this.id = id;
        this.reserva = reserva;
        this.valorPago = valorPago;
        this.dataPagamento = dataPagamento;
        this.metodo = metodo;
    }

    public int getId() {
        return id;
    }

    public Reserva getReserva() {
        return reserva;
    }

    public double getValorPago() {
        return valorPago;
    }

    public LocalDate getDataPagamento() {
        return dataPagamento;
    }

    public String getMetodo() {
        return metodo;
    }

    @Override
    public String toString() {
        return "Pagamento " + id +
                " | Reserva: " + reserva.getId() +
                " | Valor: R$" + valorPago +
                " | Data: " + dataPagamento +
                " | Método: " + metodo;
    }

}





































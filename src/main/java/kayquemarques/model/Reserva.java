package kayquemarques.model;

import java.time.Duration;
import java.time.LocalDateTime;

public class Reserva {

    private int id;
    private Espaco espaco;
    private LocalDateTime dataHoraInicio;
    private LocalDateTime dataHoraFim;
    private double valorTotal;
    private String status;

    public Reserva(int id, Espaco espaco, LocalDateTime dataHoraInicio, LocalDateTime dataHoraFim) {
        this.id = id;
        this.espaco = espaco;
        this.dataHoraInicio = dataHoraInicio;
        this.dataHoraFim = dataHoraFim;
        this.status = "Ativa";
        this.valorTotal = calcularValorTotal();
    }

    public double calcularDuracaoEmHoras() {
        long minutos = Duration.between(dataHoraInicio, dataHoraFim).toMinutes();
        return minutos / 60.0;
    }

    public double calcularValorTotal() {
        double horas = calcularDuracaoEmHoras();
        return espaco.calcularCustoReserva((int) Math.ceil(horas));
    }

    public void cancelar() {
        status = "Cancelada";
    }

    public int getId() {
        return id;
    }

    public Espaco getEspaco() {
        return espaco;
    }

    public LocalDateTime getDataHoraInicio() {
        return dataHoraInicio;
    }

    public LocalDateTime getDataHoraFim() {
        return dataHoraFim;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public String getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return "Reserva " + id + " - " + espaco.getNome() +
                " | Início: " + dataHoraInicio +
                " | Fim: " + dataHoraFim +
                " | Valor: R$" + valorTotal +
                " | Status: " + status;
    }
}



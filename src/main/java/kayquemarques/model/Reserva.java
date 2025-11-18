package kayquemarques.model;

import java.time.Duration;
import java.time.LocalDateTime;

public class Reserva {

    private int id;
    private Espaco espaco;
    private LocalDateTime inicio;
    private LocalDateTime fim;
    private double valorTotal;
    private String status;
    private double multaCancelamento;

    public Reserva(int id, Espaco espaco, LocalDateTime inicio, LocalDateTime fim) {

        if (espaco == null)
            throw new IllegalArgumentException("Espaço inválido");

        if (inicio == null || fim == null)
            throw new IllegalArgumentException("Datas inválidas");

        if (!fim.isAfter(inicio))
            throw new IllegalArgumentException("A data final deve ser depois da inicial");

        this.id = id;
        this.espaco = espaco;
        this.inicio = inicio;
        this.fim = fim;

        this.valorTotal = calcularValorTotal();
        this.status = "ATIVA";
        this.multaCancelamento = 0.0;
    }

    public int getId() {
        return id;
    }

    public Espaco getEspaco() {
        return espaco;
    }

    public LocalDateTime getInicio() {
        return inicio;
    }

    public LocalDateTime getFim() {
        return fim;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public String getStatus() {
        return status;
    }

    public double getMultaCancelamento() {
        return multaCancelamento;
    }

    public double calcularDuracaoHoras() {
        long minutos = Duration.between(inicio, fim).toMinutes();
        return minutos / 60.0;
    }

    public double calcularValorTotal() {
        double horas = calcularDuracaoHoras();
        return espaco.calcularCustoReserva((int) Math.ceil(horas));
    }

    public double calcularMultaCancelamento() {
        long horasAntes = Duration.between(LocalDateTime.now(), inicio).toHours();
        if (horasAntes >= 24)
            return 0.0;
        return valorTotal * 0.20;
    }

    public double cancelar() {
        if (!status.equals("ATIVA"))
            throw new IllegalStateException("A reserva não pode ser cancelada.");

        double multa = calcularMultaCancelamento();
        this.status = "CANCELADA";
        this.multaCancelamento = multa;

        return multa;
    }

    public void concluir() {
        if (!status.equals("ATIVA"))
            throw new IllegalStateException("A reserva não pode ser concluída.");

        this.status = "CONCLUIDA";
    }

    @Override
    public String toString() {
        return "Reserva " + id +
                " | Espaço: " + espaco.getNome() +
                " | Início: " + inicio +
                " | Fim: " + fim +
                " | Valor Total: R$" + valorTotal +
                " | Status: " + status +
                " | Multa: R$" + multaCancelamento;
    }
}


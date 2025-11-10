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
    private double multaCancelamento;

    public Reserva(int id, Espaco espaco, LocalDateTime dataHoraInicio, LocalDateTime dataHoraFim) {
        if (espaco == null) throw new IllegalArgumentException("Espaço inválido");
        if (dataHoraInicio == null || dataHoraFim == null) throw new IllegalArgumentException("Datas inválidas");
        if (!dataHoraFim.isAfter(dataHoraInicio)) throw new IllegalArgumentException("Data/hora final deve ser posterior à inicial");
        if (!espaco.isDisponivel()) throw new IllegalArgumentException("Espaço indisponível");

        this.id = id;
        this.espaco = espaco;
        this.dataHoraInicio = dataHoraInicio;
        this.dataHoraFim = dataHoraFim;
        this.valorTotal = calcularValorTotal();
        this.status = "ATIVA";
        this.multaCancelamento = 0.0;
        this.espaco.setDisponivel(false);
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

    public double getMultaCancelamento() {
        return multaCancelamento;
    }

    public double calcularDuracaoEmHoras() {
        long minutos = Duration.between(dataHoraInicio, dataHoraFim).toMinutes();
        return minutos / 60.0;
    }

    public double calcularValorTotal() {
        double horas = calcularDuracaoEmHoras();
        return espaco.calcularCustoReserva((int) Math.ceil(horas));
    }

    public double calcularMultaCancelamento() {
        LocalDateTime agora = LocalDateTime.now();
        if (agora.isAfter(dataHoraInicio)) {
            throw new IllegalStateException("Não é possível cancelar após o início da reserva");
        }
        long horasAteInicio = Duration.between(agora, dataHoraInicio).toHours();
        if (horasAteInicio >= 24) {
            return 0.0;
        } else {
            return valorTotal * 0.20;
        }
    }

    public double cancelar() {
        if (!"ATIVA".equals(status)) throw new IllegalStateException("A reserva não pode ser cancelada");
        double multa = calcularMultaCancelamento();
        this.multaCancelamento = multa;
        this.status = "CANCELADA";
        this.espaco.setDisponivel(true);
        return multa;
    }

    public void concluir() {
        if (!"ATIVA".equals(status)) throw new IllegalStateException("A reserva não pode ser concluída");
        status = "CONCLUIDA";
    }

    @Override
    public String toString() {
        return "Reserva " + id +
                " | Espaço: " + espaco.getNome() +
                " | Início: " + dataHoraInicio +
                " | Fim: " + dataHoraFim +
                " | Valor: R$" + valorTotal +
                " | Status: " + status +
                " | Multa: R$" + multaCancelamento;
    }
}


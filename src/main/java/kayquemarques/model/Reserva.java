package kayquemarques.model;

import kayquemarques.dao.EspacoDTO;
import kayquemarques.dao.ReservaDTO;
import kayquemarques.model.Enum.StatusEnum;
import kayquemarques.service.utils.ConversorDTO;

import java.time.Duration;
import java.time.LocalDateTime;

public class Reserva {

    private int id;
    private EspacoDTO espaco;
    private LocalDateTime inicio;
    private LocalDateTime fim;
    private double valorTotal;
    private StatusEnum status;
    private double multaCancelamento;

    public Reserva() {}

    public Reserva(int id, EspacoDTO espaco, LocalDateTime inicio, LocalDateTime fim) {

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
        this.status = StatusEnum.ATIVA;
        this.multaCancelamento = 0.0;
    }
    public Reserva(ReservaDTO reservaDTO) {
        this.espaco = reservaDTO.getEspaco();
        this.inicio = reservaDTO.getInicio();
        this.fim = reservaDTO.getFim();
        this.valorTotal = reservaDTO.getValorTotal();
        this.status = reservaDTO.getStatus();
        this.multaCancelamento = reservaDTO.getMultaCancelamento();

    }

    public int getId() {
        return id;
    }

    public EspacoDTO getEspaco() {
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

    public StatusEnum getStatus() {
        return status;
    }

    public double getMultaCancelamento() {
        return multaCancelamento;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setMultaCancelamento(double multaCancelamento) {
        this.multaCancelamento = multaCancelamento;
    }

    public void setStatus(StatusEnum status) {
        this.status = status;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public void setFim(LocalDateTime fim) {
        this.fim = fim;
    }

    public void setInicio(LocalDateTime inicio) {
        this.inicio = inicio;
    }

    public void setEspaco(EspacoDTO espaco) {
        this.espaco = espaco;
    }

    public double calcularDuracaoHoras() {
        long minutos = Duration.between(inicio, fim).toMinutes();
        return minutos / 60.0;
    }

    public double calcularValorTotal() {
        double horas = calcularDuracaoHoras();

        Espaco espacoObj = ConversorDTO.conversorDTOToEspaco(espaco);

        return espacoObj.calcularCustoReserva((int) Math.ceil(horas));
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
        this.status = StatusEnum.CANCELADA;
        this.multaCancelamento = multa;

        return multa;
    }

    public void concluir() {
        if (!status.equals("ATIVA"))
            throw new IllegalStateException("A reserva não pode ser concluída.");

        this.status = StatusEnum.CONCLUIDA;
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


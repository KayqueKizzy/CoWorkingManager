package kayquemarques.dao;

import kayquemarques.model.Enum.StatusEnum;

import java.time.LocalDateTime;

public class ReservaDTO {
    private Integer id;
    private EspacoDTO espaco;
    private LocalDateTime inicio;
    private LocalDateTime fim;
    private double valorTotal;
    private StatusEnum status;
    private double multaCancelamento;

    public double getMultaCancelamento() {
        return multaCancelamento;
    }

    public void setMultaCancelamento(double multaCancelamento) {
        this.multaCancelamento = multaCancelamento;
    }

    public StatusEnum getStatus() {
        return status;
    }

    public void setStatus(StatusEnum status) {
        this.status = status;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public LocalDateTime getFim() {
        return fim;
    }

    public void setFim(LocalDateTime fim) {
        this.fim = fim;
    }

    public LocalDateTime getInicio() {
        return inicio;
    }

    public void setInicio(LocalDateTime inicio) {
        this.inicio = inicio;
    }

    public EspacoDTO getEspaco() {
        return espaco;
    }

    public void setEspaco(EspacoDTO espaco) {
        this.espaco = espaco;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}

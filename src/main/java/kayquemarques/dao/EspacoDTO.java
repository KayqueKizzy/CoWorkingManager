package kayquemarques.dao;

public class EspacoDTO {
    private int id;
    private String nome;
    private int capacidade;
    private boolean disponivel;
    private double precoPorHora;
    private String TIPO;
    private boolean usaProjetor;
    private boolean temPalco;
    private int capacidadeExtra;
    public EspacoDTO() {
    }
    public EspacoDTO(int id, String nome, int capacidade, boolean disponivel, double precoPorHora, String TIPO,
                     boolean usaProjetor, boolean temPalco, int capacidadeExtra) {
        this.id = id;
        this.nome = nome;
        this.capacidade = capacidade;
        this.disponivel = disponivel;
        this.precoPorHora = precoPorHora;
        this.TIPO = TIPO;
        this.usaProjetor = usaProjetor;
        this.temPalco = temPalco;
        this.capacidadeExtra = capacidadeExtra;
    }
public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public int getCapacidade() {
        return capacidade;
    }

    public boolean isDisponivel() {
        return disponivel;
    }

    public double getPrecoPorHora() {
        return precoPorHora;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setCapacidade(int capacidade) {
        this.capacidade = capacidade;
    }

    public void setDisponivel(boolean disponivel) {
        this.disponivel = disponivel;
    }

    public void setPrecoPorHora(double precoPorHora) {
        this.precoPorHora = precoPorHora;
    }

    public void setTIPO(String TIPO) {
        this.TIPO = TIPO;
    }

    public void setUsaProjetor(boolean usaProjetor) {
        this.usaProjetor = usaProjetor;
    }

    public void setTemPalco(boolean temPalco) {
        this.temPalco = temPalco;
    }

    public void setCapacidadeExtra(int capacidadeExtra) {
        this.capacidadeExtra = capacidadeExtra;
    }

    public String getTIPO() {
        return TIPO;
    }

    public boolean isUsaProjetor() {
        return usaProjetor;
    }

    public boolean isTemPalco() {
        return temPalco;
    }

    public int getCapacidadeExtra() {
        return capacidadeExtra;
    }
    public String toString() {
        return "EspacoDTO{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", capacidade=" + capacidade +
                ", disponivel=" + disponivel +
                ", precoPorHora=" + precoPorHora +
                ", TIPO='" + TIPO + '\'' +
                ", usaProjetor=" + usaProjetor +
                ", temPalco=" + temPalco +
                ", capacidadeExtra=" + capacidadeExtra +
                '}';
    }

}


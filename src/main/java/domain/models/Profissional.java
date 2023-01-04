package domain.models;

public class Profissional {
    private int id;
    private String nome;
    private String profissao;
    private double salario;

     public Profissional() {

     }

     public Profissional(int id, String nome, String profissao, double salario) {
        this.id = id;
        this.nome = nome;
        this.profissao = profissao;
        this.salario = salario;
     }

    // Methods Polimofism
    @Override
    public String toString() {
        return String.format("<%s - %s>", this.nome, this.profissao);
    }

    @Override
    public boolean equals (Object obj) {
        if (obj instanceof Profissional) {
            Profissional prof = (Profissional) obj;
            return prof.id == this.id;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return this.id % 2 == 0? this.id * 31 : this.id * 97;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getProfissao() {
        return profissao;
    }

    public void setProfissao(String profissao) {
        this.profissao = profissao;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }
}

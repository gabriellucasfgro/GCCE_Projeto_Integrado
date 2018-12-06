package modelo;

public class Curso {
    private String nome;
    private int id;

    public Curso(String n, int i) {
        this.nome = n;
        this.id = i;
    }

    public Curso(String n) {
        this.nome = n;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return this.nome;
    }
}

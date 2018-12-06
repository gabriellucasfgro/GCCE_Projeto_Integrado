package modelo;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;

public class Aluno {
    protected SimpleLongProperty matricula;
    protected SimpleStringProperty nome;
    protected SimpleIntegerProperty curso_id;
    protected SimpleStringProperty turma;
    protected SimpleStringProperty curso;
    protected Curso c;

    public Aluno(long mat, String name, String tur, String curs) {
        this.matricula = new SimpleLongProperty();
        this.nome = new SimpleStringProperty();
        this.curso_id = new SimpleIntegerProperty();
        this.turma = new SimpleStringProperty();
        this.curso = new SimpleStringProperty();
        this.c = new Curso(curs);
        this.matricula.set(mat);
        this.nome.set(name);
        this.turma.set(tur);
        this.curso.set(curs);
        this.c.setNome(curs);
    }

    public String getCurso() {
        return curso.get();
    }

    public SimpleStringProperty cursoProperty() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso.set(curso);
        this.c.setNome(curso);
    }

    public long getMatricula() {
        return matricula.get();
    }

    public SimpleLongProperty matriculaProperty() {
        return matricula;
    }

    public void setMatricula(int matricula) {
        this.matricula.set(matricula);
    }

    public String getNome() {
        return nome.get();
    }

    public SimpleStringProperty nomeProperty() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome.set(nome);
    }

    public int getCurso_id() {
        return curso_id.get();
    }

    public SimpleIntegerProperty curso_idProperty() {
        return curso_id;
    }

    public void setCurso_id(int curso_id) {
        this.curso_id.set(curso_id);
        this.c.setId(curso_id);
    }

    public String getTurma() {
        return turma.get();
    }

    public SimpleStringProperty turmaProperty() {
        return turma;
    }

    public void setTurma(String turma) {
        this.turma.set(turma);
    }
}

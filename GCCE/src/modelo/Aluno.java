package modelo;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Aluno {
    private SimpleIntegerProperty matricula;
    private SimpleStringProperty nome;
    private SimpleIntegerProperty curso_id;
    private SimpleStringProperty turma;
    private SimpleStringProperty curso;
    private SimpleStringProperty emissao;
    private SimpleStringProperty validade;
    private SimpleIntegerProperty via;

    public Aluno(int mat, String name, String tur, String curs) {
        this.matricula = new SimpleIntegerProperty();
        this.nome = new SimpleStringProperty();
        this.curso_id = new SimpleIntegerProperty();
        this.turma = new SimpleStringProperty();
        this.curso = new SimpleStringProperty();
        this.matricula.set(mat);
        this.nome.set(name);
        this.turma.set(tur);
        this.curso.set(curs);
    }

    public Aluno(int mat, String name, String tur, String curs, String val, String emiss, int v) {
        this.matricula = new SimpleIntegerProperty();
        this.nome = new SimpleStringProperty();
        this.curso_id = new SimpleIntegerProperty();
        this.turma = new SimpleStringProperty();
        this.curso = new SimpleStringProperty();
        this.emissao = new SimpleStringProperty();
        this.validade = new SimpleStringProperty();
        this.via = new SimpleIntegerProperty();
        this.emissao.set(emiss);
        this.validade.set(val);
        this.via.set(v);
        this.matricula.set(mat);
        this.nome.set(name);
        this.turma.set(tur);
        this.curso.set(curs);
    }

    public String getEmissao() {
        return emissao.get();
    }

    public SimpleStringProperty emissaoProperty() {
        return emissao;
    }

    public void setEmissao(String emissao) {
        this.emissao.set(emissao);
    }

    public String getValidade() {
        return validade.get();
    }

    public SimpleStringProperty validadeProperty() {
        return validade;
    }

    public void setValidade(String validade) {
        this.validade.set(validade);
    }

    public int getVia() {
        return via.get();
    }

    public SimpleIntegerProperty viaProperty() {
        return via;
    }

    public void setVia(int via) {
        this.via.set(via);
    }

    public String getCurso() {
        return curso.get();
    }

    public SimpleStringProperty cursoProperty() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso.set(curso);
    }

    public int getMatricula() {
        return matricula.get();
    }

    public SimpleIntegerProperty matriculaProperty() {
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

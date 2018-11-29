package modelo;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;

public class Carteirinha extends Aluno{
    private SimpleStringProperty validade;
    private SimpleStringProperty data_de_emissao;
    private SimpleIntegerProperty via;

    public Carteirinha(long mat, String val, String emissao, int v, String nome, String turm, String curs) {
        super(mat, nome, turm, curs);
        this.matricula = new SimpleLongProperty();
        this.validade = new SimpleStringProperty();
        this.data_de_emissao = new SimpleStringProperty();
        this.via = new SimpleIntegerProperty();
        this.matricula.set(mat);
        this.validade.set(val);
        this.data_de_emissao.set(emissao);
        this.via.set(v);
    }

    public Carteirinha(long mat, String val, String nome, String turm, String curs) {
        super(mat, nome, turm, curs);
        this.matricula = new SimpleLongProperty();
        this.validade = new SimpleStringProperty();
        this.data_de_emissao = new SimpleStringProperty();
        this.via = new SimpleIntegerProperty();
        this.matricula.set(mat);
        this.validade.set(val);
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

    public String getValidade() {
        return validade.get();
    }

    public SimpleStringProperty validadeProperty() {
        return validade;
    }

    public void setValidade(String validade) {
        this.validade.set(validade);
    }

    public String getData_de_emissao() {
        return data_de_emissao.get();
    }

    public SimpleStringProperty data_de_emissaoProperty() {
        return data_de_emissao;
    }

    public void setData_de_emissao(String data_de_emissao) {
        this.data_de_emissao.set(data_de_emissao);
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
}

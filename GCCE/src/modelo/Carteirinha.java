package modelo;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Carteirinha {
    private SimpleIntegerProperty matricula;
    private SimpleStringProperty validade;
    private SimpleStringProperty data_de_emissao;
    private SimpleIntegerProperty via;

    public Carteirinha(int mat, String val, String emissao, int v) {
        this.matricula = new SimpleIntegerProperty();
        this.validade = new SimpleStringProperty();
        this.data_de_emissao = new SimpleStringProperty();
        this.via = new SimpleIntegerProperty();
        this.matricula.set(mat);
        this.validade.set(val);
        this.data_de_emissao.set(emissao);
        this.via.set(v);
    }

    public Carteirinha(int mat, String val) {
        this.matricula = new SimpleIntegerProperty();
        this.validade = new SimpleStringProperty();
        this.data_de_emissao = new SimpleStringProperty();
        this.via = new SimpleIntegerProperty();
        this.matricula.set(mat);
        this.validade.set(val);
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

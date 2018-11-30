package modelo;

public class EmissaoTurma {
    private String validade;
    private String turma;

    public EmissaoTurma(String val, String tur) {
        this.validade = val;
        this.turma = tur;
    }

    public String getValidade() {
        return validade;
    }

    public void setValidade(String validade) {
        this.validade = validade;
    }

    public String getTurma() {
        return turma;
    }

    public void setTurma(String turma) {
        this.turma = turma;
    }
}

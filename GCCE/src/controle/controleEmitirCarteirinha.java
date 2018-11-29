package controle;

import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import modelo.Aluno;
import modelo.Carteirinha;

import java.time.LocalDate;

public class controleEmitirCarteirinha {

    @FXML
    private DatePicker dpValidade;

    private Aluno aluno;

    public void setAluno(Aluno a) {
        this.aluno = a;
    }

    public Carteirinha processResult() {
        if(dpValidade.getValue() != null && dpValidade.getValue().isAfter(LocalDate.now())) {
            Carteirinha carteirinha = new Carteirinha(aluno.getMatricula(),dpValidade.getValue().toString(), aluno.getNome(), aluno.getTurma(), aluno.getCurso());
            return carteirinha;
        }
        else {
            return null;
        }
    }
}

package controle;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import modelo.Aluno;
import modelo.Carteirinha;
import modelo.Curso;
import modelo.JDBCCursoDAO;

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
            Carteirinha carteirinha = new Carteirinha(aluno.getMatricula(),dpValidade.getValue().toString());
            return carteirinha;
        }
        else {
            return null;
        }
    }
}

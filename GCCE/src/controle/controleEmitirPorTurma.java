package controle;

import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import modelo.Aluno;
import modelo.Carteirinha;
import modelo.EmissaoTurma;

import java.time.LocalDate;

public class controleEmitirPorTurma {

    @FXML
    private DatePicker dpValidade;

    @FXML
    private TextField tfTurma;

    public void initialize() {
        dpValidade.setValue(LocalDate.now().plusYears(1));
    }

    public EmissaoTurma processResult() {
        if(dpValidade.getValue() != null && dpValidade.getValue().isAfter(LocalDate.now()) && !tfTurma.getText().isEmpty()) {
            return new EmissaoTurma(dpValidade.getValue().toString(), tfTurma.getText());
        }
        else {
            return null;
        }
    }
}

package controle;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import modelo.Aluno;
import modelo.Carteirinha;
import modelo.EmissaoTurma;
import modelo.JDBCAlunoDAO;

import java.time.LocalDate;

public class controleEmitirPorTurma {

    @FXML
    private DatePicker dpValidade;

    @FXML
    private ComboBox cbTurma;

    public void initialize() {
        dpValidade.setValue(LocalDate.now().plusYears(1));
        try {
            cbTurma.setItems(JDBCAlunoDAO.getInstance().getTurmas());
        } catch(Exception e) {
            e.getMessage();
        }
    }

    public EmissaoTurma processResult() {
        if(dpValidade.getValue() != null && dpValidade.getValue().isAfter(LocalDate.now()) && !cbTurma.getSelectionModel().getSelectedItem().toString().isEmpty()) {
            return new EmissaoTurma(dpValidade.getValue().toString(), cbTurma.getSelectionModel().getSelectedItem().toString());
        }
        else {
            return null;
        }
    }
}

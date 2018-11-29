package controle;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import modelo.Aluno;
import modelo.JDBCAlunoDAO;

public class controleFiltrarAlunos {

    @FXML
    private RadioButton rbCurso;

    @FXML
    private RadioButton rbTurma;

    @FXML
    private CheckBox cbSemCarteirinha;

    @FXML
    private TextField tf;

    public ObservableList<Aluno> processResult() {
        try {
            if(rbCurso.isSelected() && !tf.getText().isEmpty()) {
                if(cbSemCarteirinha.isSelected()) {
                    return JDBCAlunoDAO.getInstance().listCurso(tf.getText(), "sc");
                }
                else {
                    return JDBCAlunoDAO.getInstance().listCurso(tf.getText(), "todos");
                }
            }

            else if(rbTurma.isSelected() && !tf.getText().isEmpty()) {
                if(cbSemCarteirinha.isSelected()) {
                    return JDBCAlunoDAO.getInstance().listTurma(tf.getText(), "sc");
                }
                else {
                    return JDBCAlunoDAO.getInstance().listTurma(tf.getText(), "todos");
                }
            }

            else {
                if(cbSemCarteirinha.isSelected()) {
                    return JDBCAlunoDAO.getInstance().list("todosSC");
                }
                else {
                    return JDBCAlunoDAO.getInstance().list("todos");
                }
            }

        } catch (Exception e) {
            e.getMessage();
        }

        return null;
    }
}

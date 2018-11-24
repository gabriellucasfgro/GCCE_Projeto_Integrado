package controle;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import modelo.Aluno;
import modelo.JDBCCarteirinhaDAO;

public class controleFiltrarCarteirinhas {

    @FXML
    private RadioButton rbVencidas;

    @FXML
    private RadioButton rbAtivas;

    @FXML
    private RadioButton rbTodas;

    public ObservableList<Aluno> processResult() {
        try {
            if(rbAtivas.isSelected()) {
                return JDBCCarteirinhaDAO.getInstance().listAlunoCarteirinha("ativas");
            }
            else if(rbVencidas.isSelected()) {
                return JDBCCarteirinhaDAO.getInstance().listAlunoCarteirinha("vencidas");
            }
            else {
                return JDBCCarteirinhaDAO.getInstance().listAlunoCarteirinha("todas");
            }
        } catch (Exception e) {
            e.getMessage();
        }

        return null;
    }
}
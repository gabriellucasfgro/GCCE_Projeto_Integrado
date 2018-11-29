package controle;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import modelo.Aluno;
import modelo.Carteirinha;
import modelo.JDBCCarteirinhaDAO;

public class controleFiltrarCarteirinhas {

    @FXML
    private RadioButton rbVencidas;

    @FXML
    private RadioButton rbAtivas;

    @FXML
    private RadioButton rbTodas;

    public ObservableList<Carteirinha> processResult() {
        try {
            if(rbAtivas.isSelected()) {
                return JDBCCarteirinhaDAO.getInstance().listCarteirinha("ativas");
            }
            else if(rbVencidas.isSelected()) {
                return JDBCCarteirinhaDAO.getInstance().listCarteirinha("vencidas");
            }
            else {
                return JDBCCarteirinhaDAO.getInstance().listCarteirinha("todas");
            }
        } catch (Exception e) {
            e.getMessage();
        }

        return null;
    }
}
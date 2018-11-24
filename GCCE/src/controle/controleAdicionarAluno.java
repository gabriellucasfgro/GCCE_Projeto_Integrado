package controle;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import modelo.Aluno;
import modelo.Curso;
import modelo.JDBCCursoDAO;

public class controleAdicionarAluno {

    @FXML
    private TextField tfNome;

    @FXML
    private ComboBox cbCurso;

    @FXML
    private TextField tfTurma;

    @FXML
    private TextField tfMatricula;

    public void initialize() {
        try {
            cbCurso.setItems(JDBCCursoDAO.getInstance().list());
        } catch(Exception e) {
            e.getMessage();
        }
    }

    public Aluno processResult() {
        if(!tfMatricula.getText().isEmpty() && !tfNome.getText().isEmpty() && !tfTurma.getText().isEmpty() && cbCurso.getSelectionModel().getSelectedItem() != null) {
            int matricula = Integer.parseInt(tfMatricula.getText());
            String nome = tfNome.getText();
            String turma = tfTurma.getText();
            Curso curso = (Curso) cbCurso.getSelectionModel().getSelectedItem();
            Aluno aluno = new Aluno(matricula, nome, turma, curso.getNome());
            aluno.setCurso_id(curso.getId());
            return aluno;
        }
        else {
            return null;
        }
    }
}

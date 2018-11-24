package controle;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import modelo.Aluno;
import modelo.Curso;
import modelo.JDBCCursoDAO;

public class controleEditarAluno {

    @FXML
    private TextField tfNome;

    @FXML
    private ComboBox cbCurso;

    @FXML
    private TextField tfTurma;

    @FXML
    private TextField tfMatricula;

    private Aluno aluno;

    public void initialize() {
        try {
            cbCurso.setItems(JDBCCursoDAO.getInstance().list());
        } catch(Exception e) {
            e.getMessage();
        }
    }

    public void setAluno(Aluno a) {
        this.aluno = a;
        cbCurso.getSelectionModel().select(aluno.getCurso());
        tfMatricula.setText(String.valueOf(aluno.getMatricula()));
        tfNome.setText(aluno.getNome());
        tfTurma.setText(aluno.getTurma());
    }

    public Aluno processResult() {
        if(!tfNome.getText().isEmpty() && !tfTurma.getText().isEmpty()) {
            String nome = tfNome.getText();
            String turma = tfTurma.getText();
            Aluno aln = new Aluno(aluno.getMatricula(), nome, turma, aluno.getCurso());
            return aln;
        }
        else {
            return null;
        }
    }
}

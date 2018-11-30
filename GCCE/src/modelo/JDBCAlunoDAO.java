package modelo;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;

import java.sql.*;

public class JDBCAlunoDAO implements AlunoDAO {

    private static JDBCAlunoDAO instance;
    private ObservableList<Aluno> lista;

    private JDBCAlunoDAO(){
        lista = FXCollections.observableArrayList();
    }

    public static JDBCAlunoDAO getInstance(){
        if(instance == null){
            instance = new JDBCAlunoDAO();
        }
        return instance;
    }

    @Override
    public void create(Aluno aluno) throws Exception {
        String sql = "INSERT INTO pi_aluno (matricula, curso_id, nome, turma) values(?,?,?,?);";

        Connection c = FabricaConexao.getConnection();

        PreparedStatement pstm = c.prepareStatement(sql);

        pstm.setLong(1, aluno.getMatricula());
        pstm.setInt(2, aluno.getCurso_id());
        pstm.setString(3, aluno.getNome());
        pstm.setString(4, aluno.getTurma());

        pstm.execute();
        pstm.close();
        c.close();
    }

    @Override
    public ObservableList<Aluno> list(String filtro) throws Exception {

        String sql = new String();

        switch (filtro) {
            case "todos":
                sql = "SELECT * FROM pi_relacao_aluno_curso;";
                break;
            case "todosSC":
                sql = "SELECT * FROM pi_relacao_aluno_cursoSC;";
                break;
        }


        Connection c = FabricaConexao.getConnection();

        Statement stm = c.createStatement();

        ResultSet rs = stm.executeQuery(sql);

        lista.clear();
        while(rs.next()) {
            Aluno u = montaAluno(rs);
            lista.add(u);
        }

        rs.close();
        stm.close();
        c.close();

        return lista;
    }

    public ObservableList<Aluno> listNome(String nome, String filtro) throws Exception {

        String sql = new String();

        switch(filtro) {
            case "todos":
                sql = "CALL listarAlunosPorNome('"+nome+"')";
                break;
            case "sc":
                sql = "CALL listarAlunosPorNomeSemCarteirinha('"+nome+"')";
                break;
        }

        Connection c = FabricaConexao.getConnection();

        Statement stm = c.createStatement();

        ResultSet rs = stm.executeQuery(sql);

        lista.clear();
        while(rs.next()) {
            Aluno u = montaAluno(rs);
            lista.add(u);
        }

        rs.close();
        stm.close();
        c.close();

        return lista;
    }

    public ObservableList<Aluno> listCurso(String curso, String filtro) throws Exception {

        String sql = new String();

        switch(filtro) {
            case "todos":
                sql = "CALL listarAlunosPorCurso('" + curso + "')";
                break;
            case "sc":
                sql = "CALL listarAlunosPorCursoSemCarteirinha('" + curso + "')";
                break;
        }

        Connection c = FabricaConexao.getConnection();

        Statement stm = c.createStatement();

        ResultSet rs = stm.executeQuery(sql);

        lista.clear();
        while(rs.next()) {
            Aluno u = montaAluno(rs);
            lista.add(u);
        }

        rs.close();
        stm.close();
        c.close();

        return lista;
    }

    public ObservableList<Aluno> listTurma(String turma, String filtro) throws Exception {
        String sql = new String();
        switch (filtro) {
            case "todos":
                sql = "CALL listarAlunosPorTurma('?')";
                break;
            case "sc":
                sql = "CALL listarAlunosPorTurmaSemCarteirinha('?')";
                break;
        }

        Connection c = FabricaConexao.getConnection();
        CallableStatement cstm = c.prepareCall(sql);
        cstm.setString(1, turma);
        ResultSet rs = cstm.executeQuery(sql);

        lista.clear();
        while(rs.next()) {
            Aluno u = montaAluno(rs);
            lista.add(u);
        }

        rs.close();
        cstm.close();
        c.close();

        return lista;
    }

    @Override
    public void delete(Aluno aluno) throws Exception {

        String sql = "DELETE FROM pi_carteirinha WHERE aluno_matricula = ?;";
        Connection c = FabricaConexao.getConnection();
        PreparedStatement pstm = c.prepareStatement(sql);
        pstm.setLong(1, aluno.getMatricula());
        pstm.execute();

        sql = "DELETE FROM pi_aluno WHERE matricula = ?;";
        pstm = c.prepareStatement(sql);
        pstm.setLong(1, aluno.getMatricula());
        pstm.execute();

        pstm.close();
        c.close();
        lista.remove(aluno);
    }

    @FXML
    public void update(Aluno aluno) throws Exception {
        String sql = "UPDATE pi_aluno SET nome = ?, turma = ? WHERE matricula = ?;";
        Connection c = FabricaConexao.getConnection();
        PreparedStatement pstm = c.prepareStatement(sql);
        pstm.setString(1, aluno.getNome());
        pstm.setString(2, aluno.getTurma());
        pstm.setLong(3, aluno.getMatricula());

        pstm.execute();
        pstm.close();
        c.close();

    }

    public Aluno montaAluno(ResultSet rs) throws Exception {

        long matricula = rs.getLong("matricula");
        String nome = rs.getString("nome");
        String turma = rs.getString("turma");
        String curso = rs.getString("curso");

        return new Aluno(matricula, nome, turma, curso);
    }

    public int getTotal() throws Exception {

        String sql = "SELECT quantidade_alunos() as quantidade;";
        Connection c = FabricaConexao.getConnection();
        Statement stm = c.createStatement();

        ResultSet rs = stm.executeQuery(sql);
        int quantidade = 0;
        while(rs.next()) {
            quantidade = rs.getInt("quantidade");
        }

        rs.close();
        stm.close();
        c.close();

        return quantidade;
    }
}

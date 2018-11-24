package modelo;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class JDBCCursoDAO implements CursoDAO {

    private static JDBCCursoDAO instance;
    private ObservableList<Curso> lista;

    private JDBCCursoDAO(){
        lista = FXCollections.observableArrayList();
    }

    public static JDBCCursoDAO getInstance(){
        if(instance == null){
            instance = new JDBCCursoDAO();
        }
        return instance;
    }

    @Override
    public ObservableList<Curso> list() throws Exception {
        String sql = "SELECT * FROM pi_curso";

        Connection c = FabricaConexao.getConnection();

        Statement stm = c.createStatement();

        ResultSet rs = stm.executeQuery(sql);

        lista.clear();
        while(rs.next()) {
            Curso cur = montaCurso(rs);
            lista.add(cur);
        }

        rs.close();
        stm.close();
        c.close();

        return lista;
    }

    private Curso montaCurso(ResultSet rs) throws Exception {

        int id = rs.getInt("id");
        String nome = rs.getString("nome");

        return new Curso(nome, id);
    }

    @Override
    public Curso create() throws Exception {
        return null;
    }

    @Override
    public void delete(Curso curso) throws Exception {

    }
}

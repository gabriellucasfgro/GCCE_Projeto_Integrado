package modelo;

import com.sun.rowset.CachedRowSetImpl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.joda.time.DateTime;


import javax.sql.rowset.CachedRowSet;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class JDBCCarteirinhaDAO implements CarteirinhaDAO {

    private static JDBCCarteirinhaDAO instance;
    private ObservableList<Carteirinha> listaCarteirinha;

    private JDBCCarteirinhaDAO(){
        listaCarteirinha = FXCollections.observableArrayList();
    }

    public static JDBCCarteirinhaDAO getInstance(){
        if(instance == null){
            instance = new JDBCCarteirinhaDAO();
        }
        return instance;
    }

    @Override
    public void create(Carteirinha carteirinha) throws Exception {
        String sql = "INSERT INTO pi_carteirinha (aluno_matricula, validade, data_de_emissao, via) values (?, ?, ?, ?);";

        Connection c = FabricaConexao.getConnection();

        PreparedStatement pstm = c.prepareStatement(sql);
        pstm.setLong(1, carteirinha.getMatricula());
        pstm.setString(2, carteirinha.getValidade());
        pstm.setString(3, "0000-00-00");
        pstm.setInt(4, 0);

        pstm.execute();
        pstm.close();
        c.close();

        listaCarteirinha.add(carteirinha);
    }

    public ObservableList<Carteirinha> list() throws Exception {
        String sql = "SELECT * FROM pi_carteirinha;";

        Connection c = FabricaConexao.getConnection();

        Statement stm = c.createStatement();

        ResultSet rs = stm.executeQuery(sql);

        listaCarteirinha.clear();
        while(rs.next()) {
            Carteirinha car = montaCarteirinha(rs);
            listaCarteirinha.add(car);
        }

        rs.close();
        stm.close();
        c.close();

        return listaCarteirinha;
    }

    public ObservableList<Carteirinha> listCarteirinha(String filtro) throws Exception {
        String sql = new String();

        switch (filtro) {
            case "todas":
                sql = "CALL listarAlunosCarteirinha();";
                break;
            case "vencidas":
                sql = "CALL listarCarteirinhasVencidas();";
                break;
            case "ativas":
                sql = "CALL listarCarteirinhasAtivas();";
                break;
        }

        Connection c = FabricaConexao.getConnection();

        Statement stm = c.createStatement();

        ResultSet rs = stm.executeQuery(sql);

        listaCarteirinha.clear();
        while(rs.next()) {
            Carteirinha car = montaCarteirinha(rs);
            listaCarteirinha.add(car);
        }

        rs.close();
        stm.close();
        c.close();

        return listaCarteirinha;
    }

    public CachedRowSetImpl getAluno(Aluno aluno) throws Exception {
        String sql = "CALL getAluno("+aluno.getMatricula()+");";
        Connection c = FabricaConexao.getConnection();
        Statement stm = c.createStatement();
        ResultSet rs = stm
                .executeQuery(sql);

        CachedRowSetImpl crs = new CachedRowSetImpl();
        crs.populate(rs);

        rs.close();
        stm.close();
        c.close();

        return crs;
    }

    @Override
    public void delete(Carteirinha carteirinha) throws Exception {

    }

    private Carteirinha montaCarteirinha(ResultSet rs)throws Exception {
        SimpleDateFormat df = new SimpleDateFormat("MMM/yyyy");

        String nome = rs.getString("nome");
        String turma = rs.getString("turma");
        String curso = rs.getString("curso");
        long matricula = rs.getLong("matricula");
        String validade = df.format(rs.getDate("validade"));
        String emissao = df.format(rs.getDate("data_de_emissao"));
        int via = rs.getInt("via");

        Carteirinha cart = new Carteirinha(matricula, validade, emissao, via, nome, turma, curso);

        return cart;
    }

    public int getTotal() throws Exception {

        String sql = "SELECT quantidade_carteirinhas() as quantidade;";
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

    public boolean verificaCarteirinha(Aluno aluno) throws Exception {
        String sql = "CALL getAluno("+aluno.getMatricula()+")";
        Connection c = FabricaConexao.getConnection();
        Statement stm = c.createStatement();
        ResultSet rs = stm
                .executeQuery(sql);

        if(rs.next()) {
            stm.close();
            c.close();
            return true;
        }
        else {
            stm.close();
            c.close();
            return false;
        }
    }

    public void recreate(Carteirinha carteirinha) throws Exception {
        Connection c = FabricaConexao.getConnection();
        PreparedStatement pstm;
        String sql = "UPDATE pi_carteirinha SET data_de_emissao = ?, validade = ? WHERE aluno_matricula = ?;";
        pstm = c.prepareStatement(sql);
        pstm.setString(1, "0000-00-00");
        pstm.setString(2, carteirinha.getValidade());
        pstm.setLong(3, carteirinha.getMatricula());
        pstm.execute();
        pstm.close();


        c.close();

        listaCarteirinha.add(carteirinha);
    }
}

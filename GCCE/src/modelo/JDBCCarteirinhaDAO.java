package modelo;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.joda.time.DateTime;


import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class JDBCCarteirinhaDAO implements CarteirinhaDAO {

    private static JDBCCarteirinhaDAO instance;
    private ObservableList<Aluno> listaAlunoCarteirinha;
    private ObservableList<Carteirinha> listaCarteirinha;

    private JDBCCarteirinhaDAO(){
        listaCarteirinha = FXCollections.observableArrayList();
        listaAlunoCarteirinha = FXCollections.observableArrayList();
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
        pstm.setInt(1, carteirinha.getMatricula());
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

    public ObservableList<Aluno> listAlunoCarteirinha(String filtro) throws Exception {
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

        listaAlunoCarteirinha.clear();
        while(rs.next()) {
            Aluno a = montaAlunoCarteirinha(rs);
            listaAlunoCarteirinha.add(a);
        }

        rs.close();
        stm.close();
        c.close();

        return listaAlunoCarteirinha;
    }

    public ResultSet getAluno(Aluno aluno) throws Exception {
        String sql = "CALL getAluno("+aluno.getMatricula()+")";
        Connection c = FabricaConexao.getConnection();
        Statement stm = c.createStatement();
        ResultSet rs = stm
                .executeQuery(sql);

        return rs;
    }

    @Override
    public void delete(Carteirinha carteirinha) throws Exception {

    }

    private Aluno montaAlunoCarteirinha(ResultSet rs)throws Exception {
        SimpleDateFormat df = new SimpleDateFormat("MMM/yyyy");

        String nome = rs.getString("nome");
        String turma = rs.getString("turma");
        String curso = rs.getString("curso");
        int matricula = rs.getInt("matricula");
        String validade = df.format(rs.getDate("validade"));
        String emissao = df.format(rs.getDate("data_de_emissao"));
        int via = rs.getInt("via");

        Aluno aluno = new Aluno(matricula, nome, turma, curso, validade, emissao, via);

        return aluno;
    }

    private Carteirinha montaCarteirinha(ResultSet rs) throws Exception {
        SimpleDateFormat df = new SimpleDateFormat("MMM/yyyy");

        int matricula = rs.getInt("aluno_matricula");
        String validade = df.format(rs.getDate("validade"));
        String emissao = df.format(rs.getDate("data_de_emissao"));
        int via = rs.getInt("via");

        Carteirinha car = new Carteirinha(matricula, validade, emissao, via);

        return car;
    }

    private int getTotal() throws Exception {

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
            return true;
        }
        else {
            return false;
        }
    }

    public void recreate(Carteirinha carteirinha) throws Exception {
        DateTime validade = new DateTime(carteirinha.getValidade());
        Connection c = FabricaConexao.getConnection();
        PreparedStatement pstm;
        if(validade.isAfterNow()) {
            String sql = "DELETE FROM pi_carteirinha WHERE aluno_matricula = ?";
            pstm = c.prepareStatement(sql);
            pstm.setInt(1, carteirinha.getMatricula());
            pstm.execute();

            sql = "INSERT INTO pi_carteirinha (aluno_matricula, validade, data_de_emissao, via) values (?, ?, ?, ?);";

            pstm = c.prepareStatement(sql);

            pstm.setInt(1, carteirinha.getMatricula());
            pstm.setString(2, carteirinha.getValidade());
            pstm.setString(3, "0000-00-00");
            pstm.setInt(4, 0);

            pstm.execute();

        }
        else {
            String sql = "UPDATE pi_carteirinha SET data_de_emissao = ?, validade = ? WHERE aluno_matricula = ?;";
            pstm = c.prepareStatement(sql);
            pstm.setString(1, "0000-00-00");
            pstm.setString(2, carteirinha.getValidade());
            pstm.setInt(3, carteirinha.getMatricula());

            pstm.execute();
        }

        pstm.close();
        c.close();

        listaCarteirinha.add(carteirinha);
    }
}

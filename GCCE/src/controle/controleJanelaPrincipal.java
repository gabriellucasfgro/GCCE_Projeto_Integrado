package controle;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import modelo.*;
import net.sf.jasperreports.engine.JRResultSetDataSource;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Optional;

public class controleJanelaPrincipal {

    @FXML
    private Button btEmitirPorTurma;
    @FXML
    private Label lbCarteirinha;

    @FXML
    private Label lbAluno;

    @FXML
    private TableColumn<Carteirinha, String> tcName;
    @FXML
    private TableColumn<Carteirinha, Long> tcMatricul;
    @FXML
    private TableColumn<Carteirinha, String> tcCurs;
    @FXML
    private TableColumn<Carteirinha, String> tcTurm;
    @FXML
    private TableColumn<Carteirinha, String> tcDataEmissao;
    @FXML
    private TableColumn<Carteirinha, String> tcValidade;
    @FXML
    private TableColumn<Carteirinha, Integer> tcVia;

    @FXML
    private Button btReemitirCarteirinha;
    @FXML
    private Button btEmitir;
    @FXML
    private Button btEditar;
    @FXML
    private Button btRemoveAluno;
    @FXML
    private ButtonBar btbarControleCarteirinha;
    @FXML
    private ButtonBar btbarManterAlunos;

    @FXML
    private TableView<Aluno> tvAlunos;
    @FXML
    private TableView<Carteirinha> tvCarteirinha;
    @FXML
    private TableColumn<Aluno, String> tcNome;
    @FXML
    private TableColumn<Aluno, Long> tcMatricula;
    @FXML
    private TableColumn<Aluno, Integer> tcCurso;
    @FXML
    private TableColumn<Aluno, String> tcTurma;

    @FXML
    private ListView lvMenu;

    final ImageView IMAGE_MANTERUSUARIOS  = new ImageView("https://i.imgur.com/kbVGZSz.png");
    final ImageView IMAGE_CONTROLECARTEIRINHA = new ImageView("https://i.imgur.com/ir10Ecc.png");

    public void initialize() {
        lvMenu.getItems().addAll("Alunos", "Carteirinhas");
        lvMenu.setCellFactory(param -> new ListCell<String>() {
            @Override
            public void updateItem(String name, boolean empty) {
                super.updateItem(name, empty);
                if (empty) {
                    setText(null);
                    setGraphic(null);
                } else if(name.equals("Alunos")) {
                    setText("       "+name);
                    setGraphic(IMAGE_MANTERUSUARIOS);
                } else if(name.equals("Carteirinhas")) {
                    setText("       "+name);
                    setGraphic(IMAGE_CONTROLECARTEIRINHA);
                }
            }
        });
    }

    @FXML
    private void mudarConteudo() {
        String op = String.valueOf(lvMenu.getSelectionModel().getSelectedItem());
        if(op != null) {
            switch (op) {
                case "Alunos":
                    manterAlunos();
                    break;
                case "Carteirinhas":
                    controleCarteirinhas();
                    break;
            }
        }
    }


    private void manterAlunos() {
        try {
            setControleCarteirinha(false);
            tcNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
            tcMatricula.setCellValueFactory(new PropertyValueFactory<>("matricula"));
            tcCurso.setCellValueFactory(new PropertyValueFactory<>("curso"));
            tcTurma.setCellValueFactory(new PropertyValueFactory<>("turma"));
            tvAlunos.setItems(JDBCAlunoDAO.getInstance().list("todos"));
            setManterAluno(true);
        } catch(SQLException e) {
            System.out.println(e.getMessage());
            System.out.println(e.getSQLState());
        }
        catch(Exception e) {
            e.getMessage();
        }
    }

    private void controleCarteirinhas() {
        try {
            setManterAluno(false);
            tcName.setCellValueFactory(new PropertyValueFactory<>("nome"));
            tcMatricul.setCellValueFactory(new PropertyValueFactory<>("matricula"));
            tcCurs.setCellValueFactory(new PropertyValueFactory<>("curso"));
            tcTurm.setCellValueFactory(new PropertyValueFactory<>("turma"));
            tcDataEmissao.setCellValueFactory(new PropertyValueFactory<>("data_de_emissao"));
            tcValidade.setCellValueFactory(new PropertyValueFactory<>("validade"));
            tcVia.setCellValueFactory(new PropertyValueFactory<>("via"));
            setControleCarteirinha(true);
            tvCarteirinha.setItems(JDBCCarteirinhaDAO.getInstance().listCarteirinha("todas"));
        } catch(SQLException e) {
            System.out.println(e.getMessage());
            System.out.println(e.getSQLState());
        }
        catch(Exception e) {
            e.getMessage();
        }
    }

    @FXML
    private void selecionaAluno() {
        if(tvAlunos.getSelectionModel().getSelectedItem() != null) {
            btRemoveAluno.setVisible(true);
            btRemoveAluno.setManaged(true);
            btEditar.setVisible(true);
            btEditar.setManaged(true);
            try {
                if (!JDBCCarteirinhaDAO.getInstance().verificaCarteirinha(tvAlunos.getSelectionModel().getSelectedItem())) {
                    btEmitir.setVisible(true);
                    btEmitir.setManaged(true);
                } else {
                    btEmitir.setVisible(false);
                    btEmitir.setManaged(false);
                }
            } catch (Exception e) {
                e.getMessage();
            }
        }
        else {
            btRemoveAluno.setVisible(false);
            btRemoveAluno.setManaged(false);
            btEditar.setVisible(false);
            btEditar.setManaged(false);
            btEmitir.setVisible(false);
            btEmitir.setManaged(false);
        }
    }

    @FXML
    private void selecionaCarteirinha() {
        if(tvCarteirinha.getSelectionModel().getSelectedItem() != null) {
            btReemitirCarteirinha.setVisible(true);
            btReemitirCarteirinha.setManaged(true);
        }
        else {
            btReemitirCarteirinha.setVisible(false);
            btReemitirCarteirinha.setManaged(false);
        }
    }

    @FXML
    private void removeAluno() {
        try {

            Aluno aluno = tvAlunos.getSelectionModel().getSelectedItem();
            JDBCAlunoDAO.getInstance().delete(aluno);

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Sucesso!");
            alert.setContentText("Aluno deletado, e consequentemente carteirinha do aluno também foi deletada.");
            alert.showAndWait();

            tvAlunos.setItems(JDBCAlunoDAO.getInstance().list("todos"));
            lbAluno.setText(String.valueOf(JDBCAlunoDAO.getInstance().getTotal()));

        }catch (SQLException es) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro");
            alert.setContentText(es.getSQLState());
            alert.showAndWait();
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }

    }

    @FXML
    private void adicionarAluno() {
        Dialog<ButtonType> dialog = new Dialog<ButtonType>();
        dialog.setTitle("Adicionar Aluno");

        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("../visao/janela_adicionar_aluno.fxml"));

        try{
            dialog.getDialogPane().setContent(fxmlLoader.load());
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
        dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);

        Optional<ButtonType> result = dialog.showAndWait();
        if(result.isPresent() && result.get() == ButtonType.OK){
            controleAdicionarAluno controller = fxmlLoader.getController();
            Aluno aluno = controller.processResult();
            if(aluno != null) {
                try {
                    JDBCAlunoDAO.getInstance().create(aluno);
                    lbAluno.setText(String.valueOf(JDBCAlunoDAO.getInstance().getTotal()));
                    tvAlunos.setItems(JDBCAlunoDAO.getInstance().list("todos"));
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Sucesso!");
                    alert.setContentText("Aluno adicionado!");
                    alert.showAndWait();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erro");
                alert.setContentText("Não foi possível adicionar aluno, não deixe campos vazios!");
                alert.showAndWait();
            }
        }

    }

    @FXML
    private void filtrarAlunos() {
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.setTitle("Filtrar Alunos");

        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("../visao/janela_filtrar_alunos.fxml"));

        try{
            dialog.getDialogPane().setContent(fxmlLoader.load());
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
        dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);

        Optional<ButtonType> result = dialog.showAndWait();
        if(result.isPresent() && result.get() == ButtonType.OK){
            controleFiltrarAlunos controller = fxmlLoader.getController();
            ObservableList<Aluno> list = null;
            list = controller.processResult();
            if(!list.isEmpty() && list!= null) {
                tvAlunos.setItems(list);
            }
            else {
                try {
                    tvAlunos.setItems(JDBCAlunoDAO.getInstance().list("todos"));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erro");
                alert.setContentText("Não foi possível filtrar a lista, não deixe campos vazios!");
                alert.showAndWait();
            }
        }
    }

    @FXML
    private void filtrarCarteirinhas() {
        Dialog<ButtonType> dialog = new Dialog<ButtonType>();
        dialog.setTitle("Filtrar Carteirinhas");

        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("../visao/janela_filtrar_carteirinhas.fxml"));

        try{
            dialog.getDialogPane().setContent(fxmlLoader.load());
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
        dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);

        Optional<ButtonType> result = dialog.showAndWait();
        if(result.isPresent() && result.get() == ButtonType.OK){
            controleFiltrarCarteirinhas controller = fxmlLoader.getController();
            ObservableList<Carteirinha> list = null;
            list = controller.processResult();
            if(!list.isEmpty() && list != null) {
                tvCarteirinha.setItems(list);
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Sucesso!");
                alert.setContentText("Carteirinhas filtradas!");
                alert.showAndWait();
            }
            else {
                try {
                    tvAlunos.setItems(JDBCAlunoDAO.getInstance().list("todos"));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erro");
                alert.setContentText("Não foi possível filtrar a lista, não deixe campos vazios!");
                alert.showAndWait();
            }
        }

    }

    public void setManterAluno(boolean op) {
        if(op) {
            tvAlunos.setVisible(true);
            tvAlunos.setManaged(true);
            btbarManterAlunos.setVisible(true);
            btbarManterAlunos.setManaged(true);
            btRemoveAluno.setVisible(false);
            btRemoveAluno.setManaged(false);
            btEmitir.setVisible(false);
            btEmitir.setManaged(false);
            btEditar.setVisible(false);
            btEditar.setManaged(false);
            lbAluno.setVisible(true);
            lbAluno.setManaged(true);
            try {
                lbAluno.setText(String.valueOf(JDBCAlunoDAO.getInstance().getTotal()));
            } catch (Exception e) {
                e.getMessage();
            }
        }
        else {
            tvAlunos.setVisible(false);
            tvAlunos.setManaged(false);
            btbarManterAlunos.setVisible(false);
            btbarManterAlunos.setManaged(false);
            lbAluno.setVisible(false);
            lbAluno.setManaged(false);
        }
    }

    public void setControleCarteirinha(boolean op) {
        if(op) {
            tvCarteirinha.setVisible(true);
            tvCarteirinha.setManaged(true);
            btbarControleCarteirinha.setVisible(true);
            btbarControleCarteirinha.setManaged(true);
            lbCarteirinha.setVisible(true);
            lbCarteirinha.setManaged(true);
            try {
                lbCarteirinha.setText(String.valueOf(JDBCCarteirinhaDAO.getInstance().getTotal()));
            } catch (Exception e) {
                e.getMessage();
            }
        }
        else {
            lbCarteirinha.setVisible(false);
            lbCarteirinha.setManaged(false);
            tvCarteirinha.setVisible(false);
            tvCarteirinha.setManaged(false);
            btbarControleCarteirinha.setVisible(false);
            btbarControleCarteirinha.setManaged(false);
        }
    }

    @FXML
    private void reemitirCarteirinha() {

        Dialog<ButtonType> dialog = new Dialog<ButtonType>();
        dialog.setTitle("Reemitir Carteirinha");

        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("../visao/janela_emitir_carteirinha.fxml"));

        try{
            dialog.getDialogPane().setContent(fxmlLoader.load());
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
        dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);

        Optional<ButtonType> result = dialog.showAndWait();
        if(result.isPresent() && result.get() == ButtonType.OK){
            Aluno aluno = tvCarteirinha.getSelectionModel().getSelectedItem();
            controleEmitirCarteirinha controller = fxmlLoader.getController();
            controller.setAluno(aluno);
            Carteirinha carteirinha = controller.processResult();
            if(carteirinha != null && aluno != null) {
                try {
                    JDBCCarteirinhaDAO.getInstance().recreate(carteirinha);
                    JRResultSetDataSource relatResul = new
                            JRResultSetDataSource(JDBCCarteirinhaDAO
                            .getInstance().getAluno(aluno));
                    JasperPrint jpPrint = JasperFillManager
                            .fillReport("reports/carteirinhaIFPR.jasper",
                                    new HashMap(),relatResul);
                    JasperViewer jv = new JasperViewer(jpPrint,false);
                    jv.setVisible(true);
                    jv.toFront();
                    tvCarteirinha.setItems(JDBCCarteirinhaDAO.getInstance().listCarteirinha("todas"));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erro");
                alert.setContentText("Não foi possível adicionar aluno, não deixe campos vazios!");
                alert.showAndWait();
            }
        }
    }

    @FXML
    private void emitirCarteirinhaTurma() {

        Dialog<ButtonType> dialog = new Dialog<ButtonType>();
        dialog.setTitle("Emitir Carteirinha por Grupo");

        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("../visao/janela_emitir_por_turma.fxml"));

        try{
            dialog.getDialogPane().setContent(fxmlLoader.load());
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
        dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);

        Optional<ButtonType> result = dialog.showAndWait();
        if(result.isPresent() && result.get() == ButtonType.OK){
            controleEmitirPorTurma controller = fxmlLoader.getController();
            EmissaoTurma et = controller.processResult();
            if(et != null) {
                try {
                    JRResultSetDataSource relatResul = new
                            JRResultSetDataSource(JDBCCarteirinhaDAO.getInstance().emitirPorTurma(et));
                    JasperPrint jpPrint = JasperFillManager
                            .fillReport("reports/carteirinhaIFPR.jasper",
                                    new HashMap(),relatResul);
                    JasperViewer jv = new JasperViewer(jpPrint,false);
                    jv.setVisible(true);
                    jv.toFront();
                    tvCarteirinha.setItems(JDBCCarteirinhaDAO.getInstance().listCarteirinha("todas"));
                    lbCarteirinha.setText(String.valueOf(JDBCCarteirinhaDAO.getInstance().getTotal()));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erro");
                alert.setContentText("Não foi possível adicionar aluno, não deixe campos vazios!");
                alert.showAndWait();
            }
        }
    }

    @FXML
    private void emitirCarteirinha() {

        Dialog<ButtonType> dialog = new Dialog<ButtonType>();
        dialog.setTitle("Emitir Carteirinha");

        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("../visao/janela_emitir_carteirinha.fxml"));

        try{
            dialog.getDialogPane().setContent(fxmlLoader.load());
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
        dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);

        Optional<ButtonType> result = dialog.showAndWait();
        if(result.isPresent() && result.get() == ButtonType.OK){
            Aluno aluno = tvAlunos.getSelectionModel().getSelectedItem();
            controleEmitirCarteirinha controller = fxmlLoader.getController();
            controller.setAluno(aluno);
            Carteirinha carteirinha = controller.processResult();
            if(carteirinha != null && aluno != null) {
                try {
                    JDBCCarteirinhaDAO.getInstance().create(carteirinha);
                    JRResultSetDataSource relatResul = new
                            JRResultSetDataSource(JDBCCarteirinhaDAO
                            .getInstance().getAluno(aluno));
                    JasperPrint jpPrint = JasperFillManager
                            .fillReport("reports/carteirinhaIFPR.jasper",
                                    new HashMap(),relatResul);
                    JasperViewer jv = new JasperViewer(jpPrint,false);
                    jv.setVisible(true);
                    jv.toFront();
                    tvAlunos.setItems(JDBCAlunoDAO.getInstance().list("todos"));
                    lbCarteirinha.setText(String.valueOf(JDBCCarteirinhaDAO.getInstance().getTotal()));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erro");
                alert.setContentText("Não foi possível adicionar aluno, não deixe campos vazios!");
                alert.showAndWait();
            }
        }
    }

    @FXML
    private void editarAluno() {
        Aluno alunoSelected = tvAlunos.getSelectionModel().getSelectedItem();

        Dialog<ButtonType> dialog = new Dialog<ButtonType>();
        dialog.setTitle("Editar Aluno");

        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("../visao/janela_editar_aluno.fxml"));

        try{
            dialog.getDialogPane().setContent(fxmlLoader.load());
            controleEditarAluno controller = fxmlLoader.getController();
            controller.setAluno(alunoSelected);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
        dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);

        Optional<ButtonType> result = dialog.showAndWait();
        if(result.isPresent() && result.get() == ButtonType.OK){
            controleEditarAluno controller = fxmlLoader.getController();
            Aluno aluno = controller.processResult();
            if(aluno != null) {
                try {
                    JDBCAlunoDAO.getInstance().update(aluno);
                    tvAlunos.setItems(JDBCAlunoDAO.getInstance().list("todos"));
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Sucesso!");
                    alert.setContentText("Aluno editado!");
                    alert.showAndWait();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erro");
                alert.setContentText("Não foi possível alterar aluno, não deixe campos vazios!");
                alert.showAndWait();
            }
        }
    }
}

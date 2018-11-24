package modelo;

import javafx.collections.ObservableList;

public interface AlunoDAO {

    public void create(Aluno aluno) throws Exception;
    public ObservableList<Aluno> list(String filtro) throws Exception;
    public void delete(Aluno aluno) throws Exception;

}
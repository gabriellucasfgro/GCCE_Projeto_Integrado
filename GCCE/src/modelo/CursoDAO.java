package modelo;

import javafx.collections.ObservableList;

public interface CursoDAO {

    public ObservableList<Curso> list() throws Exception;
    public Curso create() throws Exception;
    public void delete(Curso curso) throws Exception;

}
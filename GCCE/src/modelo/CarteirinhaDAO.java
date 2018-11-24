package modelo;

import javafx.collections.ObservableList;

public interface CarteirinhaDAO {

    public void create(Carteirinha carteirinha) throws Exception;
    public ObservableList<Carteirinha> list() throws Exception;
    public void delete(Carteirinha carteirinha) throws Exception;

}

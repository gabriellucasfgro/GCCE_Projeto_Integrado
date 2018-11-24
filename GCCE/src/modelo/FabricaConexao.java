package modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;

public class FabricaConexao {

    private static Connection[] pool;
    public static SimpleDateFormat sdf =
            new SimpleDateFormat("yyyy-MM-dd");
    public static DateTimeFormatter dateFormater = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    private static String CONNECTION_STR = "jdbc:mysql://infoprojetos.com.br:3132/tads17_gabriel?useTimezone=true&serverTimezone=UTC";
    private static int MAX_CONNECTIONS=5;

    static {
        pool = new Connection[5];
    }

    public static Connection getConnection() throws SQLException{
        for(int i=0;i<pool.length;i++){
            if((pool[i]==null) || (pool[i].isClosed())){
                pool[i] = DriverManager.getConnection(CONNECTION_STR,"tads17_gabriel", "3020");
                return pool[i];
            }
        }
        throw new SQLException("Muitas conexões abertas!!!");
    }
}

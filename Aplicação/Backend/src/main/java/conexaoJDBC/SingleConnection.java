package conexaoJDBC;

import java.sql.DriverManager;
import java.sql.Connection;

public class SingleConnection {
    //Caminho para conexao do Banco de Dados
    private static String url = "jdbc:postgresql://localhost:5432/db_CID";

    //Usuario e Senha para acesso
    private static String user = "postgres";
    private static String password = "postgres";

    private static Connection connection = null;

    static{
        conectar();
    }

    public SingleConnection(){
        conectar();
    }

    private static void conectar(){
        try{
            if(connection == null){
                Class.forName("org.postgresql.Driver");
                connection = DriverManager.getConnection(url, user, password);
                connection.setAutoCommit(false);
                System.out.println("Conectado com sucesso!");
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public static Connection getConnection(){
        return connection;
    }
}

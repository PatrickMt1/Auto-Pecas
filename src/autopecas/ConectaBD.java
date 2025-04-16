package autopecas;
import java.sql.*;
import javax.swing.*;
public class ConectaBD {
        final String DRIVER = "com.mysql.jdbc.Driver";
        final String DBNAME = "autopecas";
        final String URL = "jdbc:mysql://localhost:3306/" + DBNAME;
        final String LOGIN = "root";
        final String SENHA = "senac";  
        public Connection connection = null;
        
        public boolean conectarBD()
        {           
        try
        {
            Class.forName(DRIVER);
            connection = DriverManager.getConnection(URL, LOGIN, SENHA);  
            //JOptionPane.showMessageDialog(null, "Conectado ao BD");
            return true;
        }
        catch(ClassNotFoundException erro)
        {
            JOptionPane.showMessageDialog(null, "Driver JDBC-ODBC não encontrado!\n" + erro.toString());
            return false;
        }
        catch(SQLException erro)
        {
            JOptionPane.showMessageDialog(null, "Problemas na conexao com a fonte de dados. \n" + erro.toString());
            return false;
        }
    }
        public void fecharBD()
        {
            try
            {
                connection.close();
            }
            catch(SQLException erro){}
        }
}


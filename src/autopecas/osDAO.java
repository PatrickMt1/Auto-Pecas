/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package autopecas;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author LG
 */
public class osDAO {

    public OS os;
    public ConectaBD conecta;
    // Instanciando classe java.sql para manipulação de dados no BD
    private PreparedStatement statement;
    private Statement stm;
    public ResultSet resultSet;
    private String mensagem, sql;

    public osDAO() {
        conecta = new ConectaBD();
        os = new OS();
    }

    public boolean localizarOS() {
        sql = "select * from os where codigo=?";
        try {
            conecta.conectarBD();
            statement = conecta.connection.prepareStatement(sql);
            statement.setString(1, os.getCodigo());
            resultSet = statement.executeQuery();
            resultSet.first();
            os.setCodigo(resultSet.getString(1));
            String dataBR = FormatDateBR(resultSet.getString(2));
            os.setDataEntrega(dataBR);
            String valorP = FormataV(resultSet.getString(3));
            os.setValorTotal(valorP);
            os.setHoraEntrega(resultSet.getString(4));
            os.setCodigoAgenda(resultSet.getString(5));
            os.setMatriculaFuncionario(resultSet.getString(6));
            
            conecta.fecharBD();
            return true;
        } catch (SQLException erro) {
            return false;
        }
}
    public void pesquisarOS(String sql) {
        try {
            conecta.conectarBD();
            // tipo case sensitive e pode pecorrer tanto do início para  o fim quanto do fim para o início
            stm = conecta.connection.createStatement(resultSet.TYPE_SCROLL_SENSITIVE, resultSet.CONCUR_READ_ONLY);
            resultSet = stm.executeQuery(sql);
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro na pesquisa: \n" + erro);
        }
    }
    public int proximoOS() {

        int codigo = 0;
        sql = "select max(codigo) from os";
        try {
            conecta.conectarBD();
            statement = conecta.connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            resultSet.first();
            if (resultSet.getString(1) == null) {
                codigo++;
            } else {
                os.setCodigo(resultSet.getString(1));
                codigo = Integer.parseInt(os.getCodigo());
                conecta.fecharBD();
                codigo++;
            }
            return codigo;
        } catch (SQLException erro) {
            return codigo;
        }
    }
    public String incluirOS() {
        try {
            conecta.conectarBD();
            sql = "insert into os values (?,?,?,?,?,?)";
            statement = conecta.connection.prepareStatement(sql);
            statement.setString(1, os.getCodigo());
            statement.setString(2, os.getDataEntrega());
            statement.setString(3, os.getValorTotal());
            statement.setString(4, os.getHoraEntrega());
            statement.setString(5, os.getCodigoAgenda());
            statement.setString(6, os.getMatriculaFuncionario());
            statement.executeUpdate();
            conecta.fecharBD();
            mensagem = "Dados inseridos com sucesso";

        } catch (SQLException erro) {
            mensagem = "Falha na inclusão.\n" + erro.toString();
        }
        return mensagem;
    }
    public String atualizaOS() {
        try {
            // os parâmetros (?) seguem em uma ordem conforme a escrita dos campos no comando SQL
            conecta.conectarBD();
            sql = "update os set dataEntrega = ?, valorTotal = ?, horaEntrega = ?, fkAgenda = ?, funcionario_matricula = ? where codigo = ?";
            statement = conecta.connection.prepareStatement(sql);
            statement.setString(1, os.getDataEntrega());
            statement.setString(2, os.getValorTotal());
            statement.setString(3, os.getHoraEntrega());
            statement.setString(4, os.getCodigoAgenda());
            statement.setString(5, os.getMatriculaFuncionario());
            statement.setString(6, os.getCodigo());
            statement.executeUpdate();
            conecta.fecharBD();
            mensagem = "Dados atualizados com sucesso";

        } catch (SQLException erro) {
            mensagem = "Falha na atualização.\n" + erro.toString();
        }
        return mensagem;
    }
    public String excluiOS() {
        try {
            conecta.conectarBD();
            sql = "delete from os where codigo = ?";
            statement = conecta.connection.prepareStatement(sql);
            statement.setString(1, os.getCodigo());
            statement.executeUpdate();
            conecta.fecharBD();
            mensagem = "Dados excluídos com sucesso.";

        } catch (SQLException erro) {
            mensagem = "Falha na exclusão.\n" + erro.toString();
        }
        return mensagem;
    }
    public String FormatDateBR(String dataEN) {
        String dataBR = dataEN;
        dataBR = dataBR.substring(8, 10) + "/"
                + dataBR.substring(5, 7) + "/"
                + dataBR.substring(0, 4);
        return dataBR;
    }

    //Formantando do padrão 'BRASILEIRO' para o padrão 'AMERICANO'
    public String FormatDateEN(String dataBR) {
        String dataEN = dataBR;
        dataEN = dataEN.substring(6, 10) + "/"
                + dataEN.substring(3, 5) + "/"
                + dataEN.substring(0, 2);
        return dataEN;
    }
    public String FormataV(String valorP) {
        String valorV = valorP.replace(".", ",");
        return valorV;
    }
    
}//FIM

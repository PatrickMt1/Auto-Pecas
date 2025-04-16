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
public class CarroDAO {

    public Carro carro;
    public Cliente cliente;
    public ConectaBD conecta;
    //Instanciando classe do java.sql para manipulação de dados no Banco de Dados
    private PreparedStatement statement;
    private Statement stm;
    public ResultSet resultSet;
    private String mensagem, sql;

    public CarroDAO() {
        conecta = new ConectaBD();
        carro = new Carro();
        cliente = new Cliente();
    }

    public boolean localizarCarro() {
        sql = "select * from carro where placa=?";
        try {
            conecta.conectarBD();
            statement = conecta.connection.prepareStatement(sql);
            statement.setString(1, carro.getPlaca());
            resultSet = statement.executeQuery();
            resultSet.first();
            carro.setPlaca(resultSet.getString(1));
            carro.setCodigoCliente(resultSet.getString(2));
            carro.setMarca(resultSet.getString(3));
            carro.setNome(resultSet.getString(4));
            carro.setModelo(resultSet.getString(5));
            conecta.fecharBD();
            return true;

        } catch (SQLException erro) {
            return false;
        }
    }

    public String inserirCarro() {
        try {
            conecta.conectarBD();
            sql = "insert into carro values(?,?,?,?,?)";
            statement = conecta.connection.prepareStatement(sql);
            statement.setString(1, carro.getPlaca());
            statement.setString(2, carro.getCodigoCliente());
            statement.setString(3, carro.getMarca());
            statement.setString(4, carro.getNome());
            statement.setString(5, carro.getModelo());
            statement.executeUpdate();
            conecta.fecharBD();
            mensagem = "Dados inseridos com sucesso";
        } catch (SQLException erro) {
            mensagem = "Falha na inclusão do novo carro.\n" + erro.toString();
        }
        return mensagem;
    }

    public String atualizarCarro() {
        try {
            conecta.conectarBD();
            sql = "update carro set marca = ?, nome=?, modelo=? where placa=?";
            statement = conecta.connection.prepareStatement(sql);
            statement.setString(1, carro.getMarca());
            statement.setString(2, carro.getNome());
            statement.setString(3, carro.getModelo());
            statement.setString(4, carro.getPlaca());
            statement.executeUpdate();
            conecta.fecharBD();
            mensagem = "Dados atualizados com sucesso";
        } catch (SQLException erro) {
            mensagem = "Falha na atualização de Dados" + erro.toString();
        }
        return mensagem;
    }

    public String excluirCarro() {
        sql = "delete from carro where placa = ?";
        try {
            conecta.conectarBD();
            statement = conecta.connection.prepareStatement(sql);
            statement.setString(1, carro.getPlaca());
            statement.executeUpdate();
            conecta.fecharBD();
            mensagem = "Dados excluídos com suceso";
        } catch (SQLException erro) {
            mensagem = "Falha na exclusão do carro.\n" + erro.toString();
        }
        return mensagem;
    }
    public String excluirCliente()
    {
        try
        {
            conecta.conectarBD();
            sql = "delete from cliente where fkCliente = ?";
            statement = conecta.connection.prepareStatement(sql);
            statement.setString(1, carro.getCodigoCliente());
            statement.executeUpdate();
            conecta.fecharBD();
            mensagem = "Dados excluidos com sucesso";
        }
        catch(SQLException erro)
        {
            mensagem = "Falha na exclusão do cliente.\n" + erro.toString();
        }
        return mensagem;
    }
    public void pesquisarCarro(String sql) {
        try {
            conecta.conectarBD();
            // tipo case sensitive e pode percorrer tanto do início para o fim quanto do fim para o início 
            stm = conecta.connection.createStatement(resultSet.TYPE_SCROLL_SENSITIVE, resultSet.CONCUR_READ_ONLY);
            resultSet = stm.executeQuery(sql);
        } catch (SQLException erro) {
        }
    }
}// FIM

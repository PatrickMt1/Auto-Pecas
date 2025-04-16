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
public class ProdutoDAO {

    public Produto produto;
    public ConectaBD conecta;
    //Instanciando classes do java.sql para manipulação de dados no bd
    private PreparedStatement statement;
    private Statement stm;
    public ResultSet resultSet;
    private String mensagem, sql;
    
    public ProdutoDAO()
    {
//        Conectando ao Banco de Dados
        conecta = new ConectaBD();
        
        produto = new Produto();
    }   
    public boolean localizarProduto()
    {
        sql = "select * from produto where codigo=?";
        try
        {
            conecta.conectarBD();
            statement = conecta.connection.prepareStatement(sql);
            statement.setString(1, produto.getCodigo());
            resultSet = statement.executeQuery();
            resultSet.first();
            produto.setCodigo(resultSet.getString(1));
            produto.setNome(resultSet.getString(2));
            produto.setCarrosCompativeis(resultSet.getString(3));
            produto.setFornecedor(resultSet.getString(4));
            produto.setMarca(resultSet.getString(5));
            produto.setQuantidade(resultSet.getString(6));
            String valorV = FormataV(resultSet.getString(7));
            produto.setPreco(valorV);
            produto.setImagem(resultSet.getString(8));
            produto.setCodigoFornecedor(resultSet.getString(9));
            conecta.fecharBD();
            return true; 
        }
        catch(SQLException erro)
        {
            return false;
        }
    }
    
    public int proximoCodProduto()
    {
        int codigo = 0;
        sql = "select max(codigo) from produto";
        try{
            conecta.conectarBD();
            statement = conecta.connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            resultSet.first();
            if(resultSet.getString(1)==null)
            {
                conecta.fecharBD();
                codigo++;
            }else {
                produto.setCodigo(resultSet.getString(1));
                codigo = Integer.parseInt(produto.getCodigo());
                conecta.fecharBD();
                codigo++;
            }
            return codigo;
        }
        catch(SQLException erro)
        {
            return codigo;
        }
    }
    public String inserirProduto()
    {
        try
        {
            conecta.conectarBD();
            sql = "insert into produto values(?,?,?,?,?,?,?,?,?)";
            statement = conecta.connection.prepareStatement(sql);
            statement.setString(1, produto.getCodigo());
            statement.setString(2, produto.getNome());
            statement.setString(3, produto.getCarrosCompativeis());
            statement.setString(4, produto.getFornecedor());
            statement.setString(5, produto.getMarca());
            statement.setString(6, produto.getQuantidade());
            statement.setString(7, produto.getPreco());
            statement.setString(8, produto.getImagem());
            statement.setString(9, produto.getCodigoFornecedor());
            statement.executeUpdate();
            conecta.fecharBD();
            mensagem = "Dados inseridos com sucesso";
        }
        catch(SQLException erro)
        {
            mensagem = "Falha na inclusão do produto.\n" + erro.toString();
        }
        return mensagem;
    }
    public String atualizarProduto()
    {
        try
        {
            conecta.conectarBD();
            sql = "update produto set nome=?, carrosCompativeis=?, fornecedor=?, marca=?, quantidade=?, preco=?, imagem=?, fornecedores_codigo =? where codigo=?";
            statement = conecta.connection.prepareStatement(sql);
            statement.setString(9, produto.getCodigo());
            statement.setString(1, produto.getNome());
            statement.setString(2, produto.getCarrosCompativeis());
            statement.setString(3, produto.getFornecedor());
            statement.setString(4, produto.getMarca());
            statement.setString(5, produto.getQuantidade());
            statement.setString(6, produto.getPreco());
            statement.setString(7, produto.getImagem());
            statement.setString(8, produto.getCodigoFornecedor());
            
            statement.executeUpdate();
            conecta.fecharBD();
            mensagem = "Dados atualizados com sucesso";
        }
        catch(SQLException erro)
        {
            mensagem = "Falha na atualização dos dados." + erro.toString();
        }
        return mensagem;
    }
    public String excluirProduto()
    {
        sql = "delete from produto where codigo = ?";
        try{
            conecta.conectarBD();
            statement = conecta.connection.prepareStatement(sql);
            statement.setString(1, produto.getCodigo());
            statement.executeUpdate();
            conecta.fecharBD();
            mensagem = "Dados excluídos com sucesso.";
        }catch(SQLException erro){
            mensagem = "Falha na exclusão do Produto.\n" + erro.toString();
        }
        return mensagem;
    }
    public void pesquisarProduto(String sql){
        try {
            conecta.conectarBD();
            // tipo case sensitive e pode percorrer tanto do início para o fim quanto do fim para o início 
            stm = conecta.connection.createStatement(resultSet.TYPE_SCROLL_SENSITIVE,resultSet.CONCUR_READ_ONLY);  
            resultSet = stm.executeQuery(sql);
        } catch (SQLException erro) {}
    }
    public String FormataV(String valorP) {
        String valorV = valorP.replace(".", ",");
        return valorV;
    }
}// FIM

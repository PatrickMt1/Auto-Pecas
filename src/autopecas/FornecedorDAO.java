package autopecas;
import java.sql.*;
import javax.swing.JOptionPane;
public class FornecedorDAO {
    public Fornecedor fornecedor;
    public ConectaBD conecta;
    //Instanciando classes do java.sql para manipulação de dados no bd
    private PreparedStatement statement;
    private Statement stm;
    public ResultSet resultSet;
    private String mensagem, sql;
    
    public FornecedorDAO()
    {
        conecta = new ConectaBD();
        //conecta.conectarBD();
        fornecedor = new Fornecedor();
    }
    public String inserirFornecedor()
    {
        try
        {
            conecta.conectarBD();
            sql = "insert into fornecedores values (?,?,?,?,?,?,?,?)";
            statement = conecta.connection.prepareStatement(sql);
            statement.setString(1, fornecedor.getCodigo());
            statement.setString(2, fornecedor.getNome());
            statement.setString(3, fornecedor.getTelefone());
            statement.setString(4, fornecedor.getEndereco());
            statement.setString(5, fornecedor.getCnpj());
            statement.setString(6, fornecedor.getCidade());
            statement.setString(7, fornecedor.getEstado());
            statement.setString(8, fornecedor.getCep());
            statement.executeUpdate();
            conecta.fecharBD();
            mensagem = "Dados inseridos com sucesso";
        }
        catch(SQLException erro)
        {
            mensagem = "Falha na inclusao do Fornecedor.\n" + erro.toString();
        }
        return mensagem;
    }
    public String atualizarFornecedor()
    {
        try
        {
            conecta.conectarBD();
            sql = "update fornecedores set nome= ?, telefone = ?, endereco = ?, cnpj = ?, cidade = ?, estado = ?, cep = ? where codigo = ?";
            statement = conecta.connection.prepareStatement(sql);
            statement.setString(8, fornecedor.getCodigo());
            statement.setString(1, fornecedor.getNome());
            statement.setString(2, fornecedor.getTelefone());
            statement.setString(3, fornecedor.getEndereco());
            statement.setString(4, fornecedor.getCnpj());
            statement.setString(5, fornecedor.getCidade());
            statement.setString(6, fornecedor.getEstado());
            statement.setString(7, fornecedor.getCep());
            statement.executeUpdate();
            conecta.fecharBD();
            mensagem = "Dados atualizados com sucesso";
        }
        catch(SQLException erro)
        {
            mensagem = "Falha na atualização do Fornecedor.\n" + erro.toString();
        }
        return mensagem;
    }
    public String excluirFornecedor()
    {
        sql = "delete from fornecedores where codigo = ?";
        try
        {
            conecta.conectarBD();
            statement = conecta.connection.prepareStatement(sql);
            statement.setString(1, fornecedor.getCodigo());
            statement.executeUpdate();
            conecta.fecharBD();
            mensagem = "Dados excluidos com sucesso";
        }
        catch(SQLException erro)
        {
            mensagem = "Falha na exclusão do Fornecedor.\n" + erro.toString();
        }
        return mensagem;
    }
    public int proximoFornecedor()
    {
        int codigo=0;
        sql = "select max(codigo) from fornecedores";
        try
        {
            conecta.conectarBD();
            statement = conecta.connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            resultSet.first();
            if(resultSet.getString(1)==null)
            {
                conecta.conectarBD();
                codigo++;
            }
            else
            {
                fornecedor.setCodigo(resultSet.getString(1));
                codigo = Integer.parseInt(fornecedor.getCodigo());
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
    public boolean localizarFornecedor()
    {
        sql = "select * from fornecedores where codigo = ?";
        try
        {
            conecta.conectarBD();
            statement = conecta.connection.prepareStatement(sql);
            statement.setString(1, fornecedor.getCodigo());
            resultSet = statement.executeQuery();
            resultSet.first();
            fornecedor.setCodigo(resultSet.getString(1));
            fornecedor.setNome(resultSet.getString(2));
            fornecedor.setTelefone(resultSet.getString(3));
            fornecedor.setEndereco(resultSet.getString(4));
            fornecedor.setCnpj(resultSet.getString(5));
            fornecedor.setCidade(resultSet.getString(6));
            fornecedor.setEstado(resultSet.getString(7));
            fornecedor.setCep(resultSet.getString(8));
            conecta.fecharBD();
            return true; 
        }
        catch(SQLException erro)
        {
            return false;
        }        
    }
    public void pesquisarFornecedor(String sql) {
        try {
            conecta.conectarBD();
            // tipo case sensitive e pode pecorrer tanto do início para  o fim quanto do fim para o início
            stm = conecta.connection.createStatement(resultSet.TYPE_SCROLL_SENSITIVE, resultSet.CONCUR_READ_ONLY);
            resultSet = stm.executeQuery(sql);
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro na pesquisa: \n" + erro);
        }
    }
}
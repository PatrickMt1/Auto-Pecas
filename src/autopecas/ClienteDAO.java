package autopecas;
import java.sql.*;
import javax.swing.JOptionPane;
public class ClienteDAO {
    public Cliente cliente;
    public Agenda agenda;
    public Carro carro;
    public ConectaBD conecta;
    //Instanciando classes do java.sql para manipulação de dados no bd
    private PreparedStatement statement;
    private Statement stm;
    public ResultSet resultSet;
    private String mensagem, sql;
    
    public ClienteDAO()
    {
        conecta = new ConectaBD();
        //conecta.conectarBD();
        cliente = new Cliente();
        agenda = new Agenda();
        carro = new Carro();
    }
    public String excluirAgendamento()
    {
        
        try
        {
            conecta.conectarBD();
            sql = "delete from agenda where codigoAgendada = ?";
            statement = conecta.connection.prepareStatement(sql);
            statement.setString(1, cliente.getCodigo());
            statement.executeUpdate();
            conecta.fecharBD();
        }
        catch(SQLException erro)
        {
            mensagem = "Falha na exclusão do Agendamento.\n" + erro.toString();
        }
        return mensagem;
    }
    public String excluirCarro() {
        
        try {
            conecta.conectarBD();
            sql = "delete from carro where fkCliente = ?";
            statement = conecta.connection.prepareStatement(sql);
            statement.setString(1, cliente.getCodigo());
            statement.executeUpdate();
            conecta.fecharBD();
        } catch (SQLException erro) {
            mensagem = "Falha na exclusão do carro.\n" + erro.toString();
        }
        return mensagem;
    }
    public boolean localizarCliente()
    {
        sql = "select * from cliente where codigo=?";
        try
        {
            conecta.conectarBD();
            statement = conecta.connection.prepareStatement(sql);
            statement.setString(1, cliente.getCodigo());
            resultSet = statement.executeQuery();
            resultSet.first();
            cliente.setCodigo(resultSet.getString(1));
            cliente.setNome(resultSet.getString(2));
            cliente.setEmail(resultSet.getString(3));
            cliente.setSexo(resultSet.getString(4));
            cliente.setPessoa(resultSet.getString(5));
            cliente.setCpf(resultSet.getString(6));
            cliente.setRg(resultSet.getString(7));
            cliente.setCnpj(resultSet.getString(8));
            cliente.setTelefone(resultSet.getString(9));
            cliente.setCelular(resultSet.getString(10));
            cliente.setEndereco(resultSet.getString(11));
            cliente.setCidade(resultSet.getString(12));
            cliente.setCep(resultSet.getString(13));
            conecta.fecharBD();
            return true; 
        }
        catch(SQLException erro)
        {
            return false;
        }
    }
    public int proximoCliente()
    {
        int codigo = 0;
        sql = "select max(codigo) from cliente";
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
                cliente.setCodigo(resultSet.getString(1));
                codigo = Integer.parseInt(cliente.getCodigo());
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
    public String inserirCliente()
    {
        try
        {
            conecta.conectarBD();
            sql = "insert into cliente values(?,?,?,?,?,?,?,?,?,?,?,?,?)";
            statement = conecta.connection.prepareStatement(sql);
            statement.setString(1, cliente.getCodigo());
            statement.setString(2, cliente.getNome());
            statement.setString(3, cliente.getEmail());
            statement.setString(4, cliente.getSexo());
            statement.setString(5, cliente.getPessoa());
            statement.setString(6, cliente.getCpf());
            statement.setString(7, cliente.getRg());
            statement.setString(8, cliente.getCnpj());
            statement.setString(9, cliente.getTelefone());
            statement.setString(10, cliente.getCelular());
            statement.setString(11, cliente.getEndereco());
            statement.setString(12, cliente.getCidade());
            statement.setString(13, cliente.getCep());
            statement.executeUpdate();
            conecta.fecharBD();
            mensagem = "Dados inseridos com sucesso";
        }
        catch(SQLException erro)
        {
            mensagem = "Falha na inclusão do cliente.\n" + erro.toString();
        }
        return mensagem;
    }
    public String atualizarCliente()
    {
        try
        {
            conecta.conectarBD();
            sql = "update cliente set nome=?, email=?, sexo=?, pessoa=?, cpf=?, rg=?, cnpj=?, telefone=?, celular=?, endereco=?, cidade=?, cep=? where codigo=?";
            statement = conecta.connection.prepareStatement(sql);
            statement.setString(13, cliente.getCodigo());
            statement.setString(1, cliente.getNome());
            statement.setString(2, cliente.getEmail());
            statement.setString(3, cliente.getSexo());
            statement.setString(4, cliente.getPessoa());
            statement.setString(5, cliente.getCpf());
            statement.setString(6, cliente.getRg());
            statement.setString(7, cliente.getCnpj());
            statement.setString(8, cliente.getTelefone());
            statement.setString(9, cliente.getCelular());
            statement.setString(10, cliente.getEndereco());
            statement.setString(11, cliente.getCidade());
            statement.setString(12, cliente.getCep());
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
    public String excluirCliente()
    {
        try
        {
            conecta.conectarBD();
            sql = "delete from cliente where codigo = ?";
            statement = conecta.connection.prepareStatement(sql);
            statement.setString(1, cliente.getCodigo());
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
    public void pesquisarCliente(String sql)
    {
        try
        {
            conecta.conectarBD();
            stm = conecta.connection.createStatement(resultSet.TYPE_SCROLL_SENSITIVE,resultSet.CONCUR_READ_ONLY);
            resultSet = stm.executeQuery(sql);
        }
        catch(SQLException erro)
        {
            JOptionPane.showMessageDialog(null, "Erro na pesquisa: \n" + erro);
        }
    }
}
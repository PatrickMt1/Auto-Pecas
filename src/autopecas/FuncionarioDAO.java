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
public class FuncionarioDAO {

    public Funcionario funcionario;
    public OS os;
    public ConectaBD conecta;
    // Instanciando classe do java.sql para manipulação de dados no BD
    private PreparedStatement statement;
    private Statement stm;
    public ResultSet resultSet;
    private String mensagem, sql;

    public FuncionarioDAO() {
        conecta = new ConectaBD();
        //conecta.conectionBD();
        funcionario = new Funcionario();
        os = new OS();
    }

    // Método para localizar os dados de apenas um funcionario
    public boolean localizarFuncionario() {
        sql = "select * from funcionario where matricula = ?";
        try {
            conecta.conectarBD();
            statement = conecta.connection.prepareStatement(sql);
            statement.setString(1, funcionario.getMatricula());
            resultSet = statement.executeQuery();
            resultSet.first();
            funcionario.setMatricula(resultSet.getString(1));
            funcionario.setNome(resultSet.getString(2));
            String dataBR = FormatDateBR(resultSet.getString(3));
            funcionario.setDataNascimento(dataBR);
            funcionario.setSexo(resultSet.getString(4));
            funcionario.setTelefone(resultSet.getString(5));
            funcionario.setCelular(resultSet.getString(6));
            funcionario.setCpf(resultSet.getString(7));
            funcionario.setRg(resultSet.getString(8));
            funcionario.setCep(resultSet.getString(9));
            funcionario.setEndereco(resultSet.getString(10));
            funcionario.setBairro(resultSet.getString(11));
            funcionario.setCidade(resultSet.getString(12));
            String dataBR2 = FormatDateBR(resultSet.getString(13));
            funcionario.setDataAdmissao(dataBR2);
            funcionario.setCarteiraTrabalho(resultSet.getString(14));
            funcionario.setCargo(resultSet.getString(15));
            String valorP = FormataV(resultSet.getString(16));
            funcionario.setSalario(valorP);
            funcionario.setUsuario(resultSet.getString(17));
            funcionario.setSenha(resultSet.getString(18));
            funcionario.setPerfil(resultSet.getString(19));
            conecta.fecharBD();
            return true;
        } catch (SQLException erro) {
            return false;
        }
    }

    public boolean localizarUsuario() {
        sql = "select * from funcionario where usuario=? and senha=? limit 1";
        
        try {
            conecta.conectarBD();
            statement = conecta.connection.prepareStatement(sql);
            statement.setString(1, funcionario.getUsuario());
            statement.setString(2, funcionario.getSenha());
            resultSet = statement.executeQuery();
            resultSet.first();
            funcionario.setMatricula(resultSet.getString(1));
            funcionario.setNome(resultSet.getString(2));
            String dataBR = FormatDateBR(resultSet.getString(3));
            funcionario.setDataNascimento(dataBR);
            funcionario.setSexo(resultSet.getString(4));
            funcionario.setTelefone(resultSet.getString(5));
            funcionario.setCelular(resultSet.getString(6));
            funcionario.setCpf(resultSet.getString(7));
            funcionario.setRg(resultSet.getString(8));
            funcionario.setCep(resultSet.getString(9));
            funcionario.setEndereco(resultSet.getString(10));
            funcionario.setBairro(resultSet.getString(11));
            funcionario.setCidade(resultSet.getString(12));
            String dataBR2 = FormatDateBR(resultSet.getString(13));
            funcionario.setDataAdmissao(dataBR2);
            funcionario.setCarteiraTrabalho(resultSet.getString(14));
            funcionario.setCargo(resultSet.getString(15));
            String valorP = FormataV(resultSet.getString(16));
            funcionario.setSalario(valorP);
            funcionario.setUsuario(resultSet.getString(17));
            funcionario.setSenha(resultSet.getString(18));
            funcionario.setPerfil(resultSet.getString(19));
            conecta.fecharBD();
            return true;
        } catch (SQLException erro) {
            return false;
        }
    }

    public void pesquisarFuncionarios(String sql) {
        try {
            conecta.conectarBD();
            // tipo case sensitive e pode pecorrer tanto do início para  o fim quanto do fim para o início
            stm = conecta.connection.createStatement(resultSet.TYPE_SCROLL_SENSITIVE, resultSet.CONCUR_READ_ONLY);
            resultSet = stm.executeQuery(sql);
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro na pesquisa: \n" + erro);
        }
    }

    public int proximoFuncionario() {

        int codigo = 0;
        sql = "select max(matricula) from funcionario";
        try {
            conecta.conectarBD();
            statement = conecta.connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            resultSet.first();
            if (resultSet.getString(1) == null) {
                codigo++;
            } else {
                funcionario.setMatricula(resultSet.getString(1));
                codigo = Integer.parseInt(funcionario.getMatricula());
                conecta.fecharBD();
                codigo++;
            }
            return codigo;
        } catch (SQLException erro) {
            return codigo;
        }
    }

    // método de inserção de dados na tabela filme
    // Inserção
    public String incluirFuncionario() {
        try {
            conecta.conectarBD();
            sql = "insert into funcionario values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,md5(?),?)";
            statement = conecta.connection.prepareStatement(sql);
            statement.setString(1, funcionario.getMatricula());
            statement.setString(2, funcionario.getNome());
            statement.setString(3, funcionario.getDataNascimento());
            statement.setString(4, funcionario.getSexo());
            statement.setString(5, funcionario.getTelefone());
            statement.setString(6, funcionario.getCelular());
            statement.setString(7, funcionario.getCpf());
            statement.setString(8, funcionario.getRg());
            statement.setString(9, funcionario.getCep());
            statement.setString(10, funcionario.getEndereco());
            statement.setString(11, funcionario.getBairro());
            statement.setString(12, funcionario.getCidade());
            statement.setString(13, funcionario.getDataAdmissao());
            statement.setString(14, funcionario.getCarteiraTrabalho());
            statement.setString(15, funcionario.getCargo());
            statement.setString(16, funcionario.getSalario());
            statement.setString(17, funcionario.getUsuario());
            statement.setString(18, funcionario.getSenha());
            statement.setString(19, funcionario.getPerfil());
            statement.executeUpdate();
            conecta.fecharBD();
            mensagem = "Dados inseridos com sucesso";

        } catch (SQLException erro) {
            mensagem = "Falha na inclusão do novo Funcionário.\n" + erro.toString();
        }
        return mensagem;
    }

    // Método de atualização (EDITAR) nos dados da tabela funcionario
    //Atualização
    public String atualizaFuncionario() {
        try {
            // os parâmetros (?) seguem em uma ordem conforme a escrita dos campos no comando SQL
            conecta.conectarBD();
            sql = "update funcionario set nome = ?, dataNascimento = ?, sexo = ?, telefone = ?, celular = ?, cpf = ?, rg = ?, cep = ?, endereco = ?, bairro = ?, cidade= ?, dataAdmissao = ?, carteiraTrabalho = ?, cargo = ?, salario = ?, usuario = ?, senha = ?, perfil = ? where matricula = ?";
            statement = conecta.connection.prepareStatement(sql);
            statement.setString(1, funcionario.getNome());
            statement.setString(2, funcionario.getDataNascimento());
            statement.setString(3, funcionario.getSexo());
            statement.setString(4, funcionario.getTelefone());
            statement.setString(5, funcionario.getCelular());
            statement.setString(6, funcionario.getCpf());
            statement.setString(7, funcionario.getRg());
            statement.setString(8, funcionario.getCep());
            statement.setString(9, funcionario.getEndereco());
            statement.setString(10, funcionario.getBairro());
            statement.setString(11, funcionario.getCidade());
            statement.setString(12, funcionario.getDataAdmissao());
            statement.setString(13, funcionario.getCarteiraTrabalho());
            statement.setString(14, funcionario.getCargo());
            statement.setString(15, funcionario.getSalario());
            statement.setString(16, funcionario.getUsuario());
            statement.setString(17, funcionario.getSenha());
            statement.setString(18, funcionario.getPerfil());
//          MATRÍCULA:
            statement.setString(19, funcionario.getMatricula());
            statement.executeUpdate();
            conecta.fecharBD();
            mensagem = "Dados atualizados com sucesso";

        } catch (SQLException erro) {
            mensagem = "Falha na atualização do novo Funcionário.\n" + erro.toString();
        }
        return mensagem;
    }
    public String atualizarUsuario()
    {
     sql="update funcionario set senha=? where matricula=?";
        try{
            conecta.conectarBD();
            statement = conecta.connection.prepareStatement(sql);
            statement.setString(1, funcionario.getSenha());
            statement.setString(2, LoginAP.codigoFuncionario);
            
            statement.executeUpdate();
            conecta.fecharBD();
            mensagem = "Dados atualizados com Sucesso";
        }
       catch(SQLException erro){
            mensagem = "Falha na atualização.\n" + erro.toString();
        }
        return mensagem;
    }
    public String atualizarSenha()
    {
        sql =" update funcionario set senha = ? where matricula = ?";
        try{
            conecta.conectarBD();
            statement = conecta.connection.prepareStatement(sql);
            
            statement.setString(1, funcionario.getSenha());
            statement.setString(2, funcionario.getMatricula());
            statement.executeUpdate();
            conecta.fecharBD();
            mensagem = "Dados atualizados com Sucesso";
        }
        catch(SQLException erro)
        {
          mensagem = "Falha na atualizaçao do Funcionario.\n" + erro.toString();  
        }
        return mensagem;
    }
    public String excluiOS() {
        try {
            conecta.conectarBD();
            sql = "delete from os where codigo = ?";
            statement = conecta.connection.prepareStatement(sql);
            statement.setString(1, funcionario.getMatricula());
            statement.executeUpdate();
            conecta.fecharBD();
        } catch (SQLException erro) {
            mensagem = "Falha na exclusão.\n" + erro.toString();
        }
        return mensagem;
    }
    public String excluiFuncionario() {
        try {
            conecta.conectarBD();
            sql = "delete from funcionario where matricula = ?";
            statement = conecta.connection.prepareStatement(sql);
            statement.setString(1, funcionario.getMatricula());
            statement.executeUpdate();
            conecta.fecharBD();
            mensagem = "Dados excluídos com sucesso.";
        } catch (SQLException erro) {
            mensagem = "Falha na exclusão do Funcionário.\n" + erro.toString();
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
    public void pesquisarFornecedorCB(String sql){
        try {
            conecta.conectarBD();
            // tipo case sensitive e pode percorrer tanto do início para o fim quanto do fim para o início 
            stm = conecta.connection.createStatement(resultSet.TYPE_SCROLL_SENSITIVE,resultSet.CONCUR_READ_ONLY);  
            resultSet = stm.executeQuery(sql);
        } catch (SQLException erro) {}
    }
}//FIM

package autopecas;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

public class EmissaoDAO {

    public Emissao emissao;
    public Funcionario funcionario;
    public ConectaBD conecta;
    //Instanciando classes do java.sql para manipulação de dados no bd
    private PreparedStatement statement;
    private Statement stm;
    public ResultSet resultSet;
    private String mensagem, sql;

    public EmissaoDAO() {
        conecta = new ConectaBD();
        //conecta.conectarBD();
        emissao = new Emissao();
        funcionario = new Funcionario();
    }

    public boolean localizarNota() {
        sql = "select * from notafiscal where codigo=?";
        try {
            conecta.conectarBD();
            statement = conecta.connection.prepareStatement(sql);
            statement.setString(1, emissao.getCodigo());
            resultSet = statement.executeQuery();
            resultSet.first();
            emissao.setCodigo(resultSet.getString(1));
            emissao.setCliente(resultSet.getString(2));
            String valorP = FormataV(resultSet.getString(3));
            emissao.setValor(valorP);
            String dataBR = FormatDateBR(resultSet.getString(4));
            emissao.setData(dataBR);
            emissao.setVendedor(resultSet.getString(5));
            emissao.setPgto(resultSet.getString(6));
            emissao.setOS(resultSet.getString(7));
            emissao.setCodigoCli(resultSet.getString(8));
            emissao.setCodigoOS(resultSet.getString(9));
            conecta.fecharBD();
            return true;
        } catch (SQLException erro) {
            return false;
        }
    }

    public int proximaNota() {
        int codigo = 0;
        sql = "select max(codigo) from notafiscal";
        try {
            conecta.conectarBD();
            statement = conecta.connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            resultSet.first();
            if (resultSet.getString(1) == null) {
                conecta.fecharBD();
                codigo++;
            } else {
                emissao.setCodigo(resultSet.getString(1));
                codigo = Integer.parseInt(emissao.getCodigo());
                conecta.fecharBD();
                codigo++;
            }
            return codigo;
        } catch (SQLException erro) {
            return codigo;
        }
    }

    public int proximaOS() {
        int codigo = 0;
        sql = "select max(fkOs) from listaservico_has_servico";
        try {
            conecta.conectarBD();
            statement = conecta.connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            resultSet.first();
            if (resultSet.getString(1) == null) {
                conecta.fecharBD();
                codigo++;
            } else {
                emissao.setFkOS(resultSet.getString(1));
                codigo = Integer.parseInt(emissao.getFkOS());
                conecta.fecharBD();
                codigo++;
            }
            return codigo;
        } catch (SQLException erro) {
            return codigo;
        }
    }

    public String inserirNota() {
        try {
            conecta.conectarBD();
            sql = "insert into notafiscal values(?,?,?,?,?,?,?,?,?)";
            statement = conecta.connection.prepareStatement(sql);
            statement.setString(1, emissao.getCodigo());
            statement.setString(2, emissao.getCliente());
            statement.setString(3, emissao.getValor());
            statement.setString(4, emissao.getData());
            statement.setString(5, emissao.getVendedor());
            statement.setString(6, emissao.getPgto());
            statement.setString(7, emissao.getOS());
            statement.setString(8, emissao.getCodigoCli());
            statement.setString(9, emissao.getCodigoOS());
            statement.executeUpdate();
            conecta.fecharBD();
            mensagem = "Dados inseridos com sucesso";
        } catch (SQLException erro) {
            mensagem = "Falha na inclusão do Nota Fiscal.\n" + erro.toString();
        }
        return mensagem;
    }

    public String inserirServicos() {
        try {
            conecta.conectarBD();
            sql = "insert into listaservico_has_servico values (?,?)";
            statement = conecta.connection.prepareStatement(sql);
            statement.setString(1, emissao.getFkListaServicos());
            statement.setString(2, emissao.getFkOS());
            statement.executeUpdate();
            conecta.fecharBD();
        } catch (SQLException erro) {
            mensagem = "Falha na inclusão.\n" + erro.toString();
        }
        return mensagem;
    }

    public String atualizarNota() {
        try {
            conecta.conectarBD();
            sql = "update notafiscal set nomeCliente=?, valor=?, data=?, vendedor=?, tipoPgto=?, has_OS=?, OS_codigo=?, cliente_codigo=? where codigo=?";
            statement = conecta.connection.prepareStatement(sql);
            statement.setString(9, emissao.getCodigo());
            statement.setString(1, emissao.getCliente());
            statement.setString(2, emissao.getValor());
            statement.setString(3, emissao.getData());
            statement.setString(4, emissao.getVendedor());
            statement.setString(5, emissao.getPgto());
            statement.setString(6, emissao.getOS());
            statement.setString(7, emissao.getCodigoOS());
            statement.setString(8, emissao.getCodigoCli());
            statement.executeUpdate();
            conecta.fecharBD();
            mensagem = "Dados atualizados com sucesso";
        } catch (SQLException erro) {
            mensagem = "Falha na atualização dos dados." + erro.toString();
        }
        return mensagem;
    }

    public String excluirNota() {
        sql = "delete from notafiscal where codigo = ?";
        try {
            conecta.conectarBD();
            statement = conecta.connection.prepareStatement(sql);
            statement.setString(1, emissao.getCodigo());
            statement.executeUpdate();
            conecta.fecharBD();
            mensagem = "Dados excluidos com sucesso";
        } catch (SQLException erro) {
            mensagem = "Falha na exclusão do cliente.\n" + erro.toString();
        }
        return mensagem;
    }

    public void pesquisarNota(String sql) {
        try {
            conecta.conectarBD();
            stm = conecta.connection.createStatement(resultSet.TYPE_SCROLL_SENSITIVE, resultSet.CONCUR_READ_ONLY);
            resultSet = stm.executeQuery(sql);
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro na pesquisa: \n" + erro);
        }
    }
public void pesquisar(String sql){
        try {
            conecta.conectarBD();
            // tipo case sensitive e pode percorrer tanto do início para o fim quanto do fim para o início 
            stm = conecta.connection.createStatement(resultSet.TYPE_SCROLL_SENSITIVE,resultSet.CONCUR_READ_ONLY);  
            resultSet = stm.executeQuery(sql);
        } catch (SQLException erro) {}
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


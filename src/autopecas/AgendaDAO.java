package autopecas;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

public class AgendaDAO {
    public Agenda agenda;
    public ConectaBD conecta;
    //Instanciando classes do java.sql para manipulação de dados no bd
    private PreparedStatement statement;
    private Statement stm;
    public ResultSet resultSet;
    private String mensagem, sql;
    public AgendaDAO()
    {
        conecta = new ConectaBD();
        //conecta.conectarBD();
        agenda = new Agenda();
    }
    public String inserirAgendamento()
    {
        try
        {
            conecta.conectarBD();
            sql = "insert into agenda values(?,?,?,?,?,?,?)";
            statement = conecta.connection.prepareStatement(sql);
            statement.setString(1, agenda.getCodigoAgendada());
            statement.setString(2, agenda.getHoraServico());
            statement.setString(3, agenda.getDataServico());
            statement.setString(4, agenda.getDataAgendada());
            statement.setString(5, agenda.getHoraAgendada());
            statement.setString(6, agenda.getFkCarro());
            statement.setString(7, agenda.getFkCliente());
            statement.executeUpdate();
            conecta.fecharBD();
            mensagem = "Dados inseridos com sucesso";
        }
        catch(SQLException erro)
        {
            mensagem = "Falha na inclusao do Agendamento.\n" + erro.toString();
        }
        return mensagem;
    }
    public String atualizarAgenda()
    {
        try
        {
            conecta.conectarBD();
            sql = "update agenda set horaServico=?, dataServico=?, dataAgendada=?, horaAgendada=?, fkCarro=?, fkCliente=? where codigoAgendada=?";
            statement = conecta.connection.prepareStatement(sql);
            statement.setString(7, agenda.getCodigoAgendada());
            statement.setString(1, agenda.getHoraServico());
            statement.setString(2, agenda.getDataServico());
            statement.setString(3, agenda.getDataAgendada());
            statement.setString(4, agenda.getHoraAgendada());
            statement.setString(5, agenda.getFkCarro());
            statement.setString(6, agenda.getFkCliente());
            statement.executeUpdate();
            conecta.fecharBD();
            mensagem = "Dados atualizados com sucesso";
        }
        catch(SQLException erro)
        {
            mensagem = "Falha na atualização da Agenda.\n" + erro.toString();
        }
        return mensagem;
    }
    public String excluirAgendamento()
    {
        sql = "delete from agenda where codigoAgendada = ?";
        try
        {
            conecta.conectarBD();
            statement = conecta.connection.prepareStatement(sql);
            statement.setString(1, agenda.getCodigoAgendada());
            statement.executeUpdate();
            conecta.fecharBD();
            mensagem = "Dados excluidos com sucesso";
        }
        catch(SQLException erro)
        {
            mensagem = "Falha na exclusão do Agendamento.\n" + erro.toString();
        }
        return mensagem;
    }
    public int proximoAgendamento()
    {
        int codigo=0;
        sql = "select max(codigoAgendada) from agenda";
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
                agenda.setCodigoAgendada(resultSet.getString(1));
                codigo = Integer.parseInt(agenda.getCodigoAgendada());
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
    public boolean localizarAgendamento()
    {
        sql = "select * from agenda where codigoAgendada = ?";
        try
        {
            conecta.conectarBD();
            statement = conecta.connection.prepareStatement(sql);
            statement.setString(1, agenda.getCodigoAgendada());
            resultSet = statement.executeQuery();
            resultSet.first();
            agenda.setCodigoAgendada(resultSet.getString(1));
            agenda.setHoraServico(resultSet.getString(2));
            String dataBR = FormatDateBR(resultSet.getString(3));
            agenda.setDataServico(dataBR);
            String dataBR2 = FormatDateBR(resultSet.getString(4));
            agenda.setDataAgendada(dataBR2);
            agenda.setHoraAgendada(resultSet.getString(5));
            conecta.fecharBD();
            return true; 
        }
        catch(SQLException erro)
        {
            return false;
        }        
    }
    public void pesquisarAgenda(String sql) {
        try {
            conecta.conectarBD();
            // tipo case sensitive e pode pecorrer tanto do início para  o fim quanto do fim para o início
            stm = conecta.connection.createStatement(resultSet.TYPE_SCROLL_SENSITIVE, resultSet.CONCUR_READ_ONLY);
            resultSet = stm.executeQuery(sql);
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro na pesquisa: \n" + erro);
        }
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
}

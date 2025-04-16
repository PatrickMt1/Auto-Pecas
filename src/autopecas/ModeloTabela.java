package autopecas;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;
public class ModeloTabela extends AbstractTableModel{
    private ArrayList linhas = null;
    private String[] colunas = null;
    
    /**
     * Método responsável por indicar (setar) a qtde de linhas e colunas     * 
     * @param lin - linhas
     * @param col  - colunas
     */
    public ModeloTabela(ArrayList lin, String[] col){
        setLinhas(lin);
        setColunas(col);
    }    
    /**
     * Métodos para pegar as linhas e colunas e para setar as linhas
     * e colunas
     */
    public ArrayList getLinhas(){
        return linhas;
    }
    public void setLinhas(ArrayList dados){
        linhas = dados;
    }
    public String[] getColunas(){
        return colunas;
    }
    public void setColunas(String[] nomes){
        colunas = nomes;
    }

    /* Métodos para realizar a contagem do número de colunas */
    public int getColumnCount(){
        return colunas.length; // lengt conta qtde de caracteres no nome
    }
     /* Métodos para realizar a contagem do número de linhas */
    public int getRowCount() {
        return linhas.size();  // size conta o tamanho do array
    }
    /* retornar os nomes das colunas */
    public String getColumnName(int numCol){
        return colunas[numCol];
    }
    
    /* método para montar a tabela (preencher com dados) */
    public Object getValueAt(int numLin, int numCol){
        Object[] linha = (Object[])getLinhas().get(numLin);
        return linha[numCol];
    }
}

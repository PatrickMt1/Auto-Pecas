package autopecas;

public class Emissao {

    private String codigoNF, nomeCliente, valor, data, vendedor, /*servicos,*/ tipoPgto, hasOS, codigoOS, codigoCli;
    private String fkListaServicos, fkOS;

    public void setCodigo(String codigoNF) {
        this.codigoNF = codigoNF;
    }

    public String getCodigo() {
        return codigoNF;
    }

    public void setCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public String getCliente() {
        return nomeCliente;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public String getValor() {
        return valor;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getData() {
        return data;
    }

    public void setVendedor(String vendedor) {
        this.vendedor = vendedor;
    }

    public String getVendedor() {
        return vendedor;
    }

    /*    public void setServicos(String servicos)
    {
        this.servicos = servicos;
    }
    public String getServicos()
    {
        return servicos;
    }
     */
    public void setPgto(String tipoPgto) {
        this.tipoPgto = tipoPgto;
    }

    public String getPgto() {
        return tipoPgto;
    }

    public void setOS(String hasOS) {
        this.hasOS = hasOS;
    }

    public String getOS() {
        return hasOS;
    }

    public void setCodigoOS(String codigoOS) {
        this.codigoOS = codigoOS;
    }

    public String getCodigoOS() {
        return codigoOS;
    }

    public void setCodigoCli(String codigoCli) {
        this.codigoCli = codigoCli;
    }

    public String getCodigoCli() {
        return codigoCli;
    }

    /**
     * @return the fkListaServicos
     */
    public String getFkListaServicos() {
        return fkListaServicos;
    }

    /**
     * @param fkListaServicos the fkListaServicos to set
     */
    public void setFkListaServicos(String fkListaServicos) {
        this.fkListaServicos = fkListaServicos;
    }

    /**
     * @return the fkOS
     */
    public String getFkOS() {
        return fkOS;
    }

    /**
     * @param fkOS the fkOS to set
     */
    public void setFkOS(String fkOS) {
        this.fkOS = fkOS;
    }

}

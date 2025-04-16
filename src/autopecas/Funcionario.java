/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package autopecas;

/**
 *
 * @author LG
 */
public class Funcionario {
    // Atributos
    public String matricula, nome, dataNascimento, sexo, telefone, celular, cpf, rg, cep;
    public String endereco, bairro, cidade, dataAdmissao, carteiraTrabalho, cargo, salario;
    private String usuario, senha, perfil, funcionario_matricula;
    // Métodos de encapsulamento
    public void setMatricula(String matricula)
    {
        this.matricula = matricula;
    }
    public String getMatricula()
    {
        return matricula;
    }
    public void setNome(String nome)
    {
        this.nome = nome;
    }
    public String getNome()
    {
        return nome;
    }
    public void setDataNascimento(String dataNascimento)
    {
        this.dataNascimento = dataNascimento;
    }
    public String getDataNascimento()
    {
        return dataNascimento;
    }
    public void setSexo(String sexo)
    {
        this.sexo = sexo;
    }
    public String getSexo()
    {
        return sexo;
    }
    public void setTelefone(String telefone)
    {
        this.telefone = telefone;
    }
    public String getTelefone()
    {
        return telefone;
    }
    public void setCelular(String celular)
    {
        this.celular = celular;
    }
    public String getCelular()
    {
        return celular;
    }
    public void setCpf(String cpf)
    {
        this.cpf = cpf;
    }
    public String getCpf()
    {
        return cpf;
    }
    public void setRg(String rg)
    {
        this.rg = rg;
    }
    public String getRg()
    {
        return rg;
    }
    public void setCep(String cep)
    {
        this.cep = cep;
    }
    public String getCep()
    {
        return cep;
    }
    public void setEndereco(String endereco)
    {
        this.endereco = endereco;
    }
    public String getEndereco()
    {
        return endereco;
    }
    public void setBairro(String bairro)
    {
        this.bairro = bairro;
    }
    public String getBairro()
    {
        return bairro;
    }
    public void setCidade(String cidade)
    {
        this.cidade = cidade;
    }
    public String getCidade()
    {
        return cidade;
    }
    public void setDataAdmissao(String dataAdmissao)
    {
        this.dataAdmissao = dataAdmissao;
    }
    public String getDataAdmissao()
    {
        return dataAdmissao;
    }
    public void setCarteiraTrabalho(String carteiraTrabalho)
    {
        this.carteiraTrabalho = carteiraTrabalho;
    }
    public String getCarteiraTrabalho()
    {
        return carteiraTrabalho;
    }
    public void setCargo(String cargo)
    {
        this.cargo = cargo;
    }
    public String getCargo()
    {
        return cargo;
    }
    public void setSalario(String salario)
    {
        this.salario = salario;
    }
    public String getSalario()
    {
        return salario;
    }

    /**
     * @return the usuario
     */
    public String getUsuario() {
        return usuario;
    }

    /**
     * @param usuario the usuario to set
     */
    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    /**
     * @return the senha
     */
    public String getSenha() {
        return senha;
    }

    /**
     * @param senha the senha to set
     */
    public void setSenha(String senha) {
        this.senha = senha;
    }

    /**
     * @return the perfil
     */
    public String getPerfil() {
        return perfil;
    }

    /**
     * @param perfil the perfil to set
     */
    public void setPerfil(String perfil) {
        this.perfil = perfil;
    }

    /**
     * @return the funcionario_matricula
     */
    public String getFuncionario_matricula() {
        return funcionario_matricula;
    }

    /**
     * @param funcionario_matricula the funcionario_matricula to set
     */
    public void setFuncionario_matricula(String funcionario_matricula) {
        this.funcionario_matricula = funcionario_matricula;
    }
    
    
   
            
    
}

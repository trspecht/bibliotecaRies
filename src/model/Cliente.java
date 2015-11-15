package model;

/**
 *
 * @author Tainara Specht
 * @author Diego Peixoto Classe Models para a classe Cliente
 */
public class Cliente {

    private int id, livrosAlugados, qntdelivrosalugados;
    private String nome, telefone;
    private Long matricula, rg;

    /**
     * Construtor da classe
     *
     * @param nomeIn - recebe o nome do cliente
     * @param rgIn
     * @param telefoneIn - recebe o telefone do cliente
     * @param matriculaIn - recebe o número de matrícula do cliente
     */
    public Cliente(String nomeIn, long rgIn, String telefoneIn, Long matriculaIn) {
        this.nome = nomeIn;
        this.rg = rgIn;
        this.telefone = telefoneIn;
        this.matricula = matriculaIn;
        this.livrosAlugados = 0;
        this.qntdelivrosalugados = 0;
    }

    public Cliente(int idIn, String nomeIn, long rgIn, String telefoneIn, Long matriculaIn) {
        this.id = idIn;
        this.nome = nomeIn;
        this.rg = rgIn;
        this.telefone = telefoneIn;
        this.matricula = matriculaIn;
        this.livrosAlugados = 0;
        this.qntdelivrosalugados = 0;
    }

     public Cliente(String nomeIn, long rgIn, String telefoneIn) {
        this.nome = nomeIn;
        this.rg = rgIn;
        this.telefone = telefoneIn;
    }
     
    public void setNome(String nomeIn) {
        this.nome = nomeIn;
    }

    public String getNome() {
        return nome;
    }

    public void setTelefone(String telefoneIn) {
        this.telefone = telefoneIn;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setMatricula(Long matriculaIn) {
        this.matricula = matriculaIn;
    }

    public Long getMatricula() {
        return matricula;
    }

    public void setLivrosAlugados(int livrosAlugados) {
        this.livrosAlugados = livrosAlugados;
    }

    public int getLivrosAlugados() {
        return livrosAlugados;
    }

    public void setQntdelivrosalugados(int qntdelivrosalugados) {
        this.qntdelivrosalugados = qntdelivrosalugados;
    }

    public int getQntdelivrosalugados() {
        return qntdelivrosalugados;
    }

    public void setId(int idIn) {
        this.id = idIn;
    }

    public int getId() {
        return id;
    }

    public void setRg(Long rg) {
        this.rg = rg;
    }

    public Long getRg() {
        return rg;
    }

    @Override
    public String toString() {
        return "Nome: " + getNome() + "\n RG: " +getRg()+ "\nTelefone: " + getTelefone() + "\nMatricula: " + getMatricula() + "\nQuantidade de livros alugados: " + getLivrosAlugados() + "\n";
    }

}

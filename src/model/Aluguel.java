/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Date;

/**
 *
 * @author Tainara Specht
 * @author Diego Peixoto Classe Models para classe Aluguel
 */
public class Aluguel {

    private long codigo, cod;
    private int idAluguel, id;
    private Cliente c;
    private Livro livrosAlugados;
    private Date dataAluguel;

    /**
     * Construtor da classe
     *
     * @param cIn
     * @param livrosAlugadosIn
     * @param dataAluguelIn
     * @param codIn
     * @param c - recebe o objeto cliente
     * @param livrosAlugados - recebe o objeto livro
     * @param dataAluguel - recebe a data em que foi efetuada a transação
     * @param cod
     */
    public Aluguel(Cliente cIn, Livro livrosAlugadosIn, Date dataAluguelIn, long codIn) {
        this.c = cIn;
        this.livrosAlugados = livrosAlugadosIn;
        this.dataAluguel = dataAluguelIn;
        this.codigo = codIn;  
    }

    public Aluguel(int idIn, Cliente cIn, Livro livrosAlugadosIn, Date dataAluguelIn, long codIn) {
        this.idAluguel = idIn;
        this.c = cIn;
        this.livrosAlugados = livrosAlugadosIn;
        this.dataAluguel = dataAluguelIn;
        this.codigo = codIn;
    }

    public Aluguel(int idAluguel, long codigo, Date dataUtil, int id, long cod) {
        this.idAluguel = idAluguel;
        this.codigo = codigo;
        this.dataAluguel = dataUtil;
        this.id = id;
        this.cod = cod;
    }
    
    public void setId(int id) {
        this.idAluguel = id;
    }

    public int getId() {
        return idAluguel;
    }

    public void setCodigo(int cod) {
        this.codigo = cod;
    }

    public long getCodigo() {
        return codigo;
    }

    public void setC(Cliente c) {
        this.c = c;
    }

    public Cliente getC() {
        return c;
    }

    public void setLivrosAlugados(Livro livrosAlugados) {
        this.livrosAlugados = livrosAlugados;
    }

    public Livro getLivrosAlugados() {
        return livrosAlugados;
    }

    public void setDataAluguel(Date dataAluguel) {
        this.dataAluguel = dataAluguel;
    }

    public Date getDataAluguel() {
        return dataAluguel;
    }

    @Override
    public String toString() {
        return "\nCódigo para devolução: " + codigo + "\nCliente: " + c + "\nLivro Alugado: " + livrosAlugados + "\nDataAluguel= " + dataAluguel;
    }

}

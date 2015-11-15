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
 * @author Diego Peixoto Classe Models para classe Devolucao
 */
public class Devolucao {
    
    private int cod;
    private Date dataDevolucao;
    private Aluguel a;

    /**
     * Construtor da classe
     *
     * @param aIn
     */
    public Devolucao(Aluguel aIn) {
        this.a = aIn;
        Date data = new Date();
        this.dataDevolucao = data;
    }

     public Devolucao(int codIn, Aluguel aIn) {
        this.cod = codIn;
        this.a = aIn;
        Date data = new Date();
        this.dataDevolucao = data;
    }
     
    public Aluguel getA() {
        return a;
    }

    public void setA(Aluguel a) {
        this.a = a;
    }

    public Date getDataDevolucao() {
        return dataDevolucao;
    }

    public void setDataDevolucao(Date dataDevolucao) {
        this.dataDevolucao = dataDevolucao;
    }

    @Override
    public String toString() {
        return "Devolucao{" + "a=" + a + ", dataDevolucao=" + dataDevolucao + '}';
    }

}

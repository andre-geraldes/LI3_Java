/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

import java.io.Serializable;

/**
 * 19-05-2014
 * @author barbosa
 */
public class Compra implements Serializable {
    private double preco;
    private String modo;
    private int quantidade;
    private String produto;
    
    //Construtores
    //Construtor vazio
    public Compra() {
        this.preco = 0;
        this.modo = "";
        this.quantidade = 0;
        this.produto = "";
    }

    //Construtor por parametros
    public Compra(double preco, String modo, int quantidade, String produto) {
        this.preco = preco;
        this.modo = modo;
        this.quantidade = quantidade;
        this.produto = produto;
    }
    
    //Construtor por copia
    public Compra(Compra compra){
        this.preco = compra.getPreco();
        this.modo = compra.getModo();
        this.quantidade = compra.getQuantidade();
        this.produto = compra.getProduto();
    }

    
    
    //Métodos
    //Getters e Setters

    public String getModo() {return modo;}

    public double getPreco() {return preco;}

    public int getQuantidade() {return quantidade;}
    
    public String getProduto() {return produto;}

    public void setModo(String modo) {this.modo = modo;}

    public void setPreco(double preco) {this.preco = preco;}

    public void setQuantidade(int quantidade) {this.quantidade = quantidade;}
    
    public void setProduto(String produto){this.produto = produto;}
    
    
    //Tostring

    public String toString(){
        return "Produto"  + this.produto + ", Preço: " + this.preco + ", Quantidade: " + this.quantidade + 
                    ", Modo: " + this.modo;
    }
    
    //Equals

     public boolean equals(Object obj) {
      if(this == obj) return true; 
      if((obj == null) || (this.getClass() != obj.getClass())) return false;
      Compra c = (Compra) obj;
      return this.preco == c.getPreco() && this.modo.equals(c.getModo()) && this.quantidade == c.getQuantidade();
   }
    
    //Clone
    
     public Compra clone(){
         return new Compra(this);
     } 
    
}


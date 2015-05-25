/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

/**
 * 19-05-2014
 * @author barbosa
 */
public class Compra {
    private double preco;
    private String modo;
    private int quantidade;
    private String cliente;
    
    //Construtores
    //Construtor vazio
    public Compra() {
        this.preco = 0;
        this.modo = "";
        this.quantidade = 0;
        this.cliente = "";
    }

    //Construtor por parametros
    public Compra(double preco, String modo, int quantidade, String cliente) {
        this.preco = preco;
        this.modo = modo;
        this.quantidade = quantidade;
        this.cliente = cliente;
    }
    
    //Construtor por copia
    public Compra(Compra compra){
        this.preco = compra.getPreco();
        this.modo = compra.getModo();
        this.quantidade = compra.getQuantidade();
        this.cliente = compra.getCliente();
    }

    
    
    //Métodos
    //Getters e Setters

    public String getModo() {return modo;}

    public double getPreco() {return preco;}

    public int getQuantidade() {return quantidade;}
    
    public String getCliente() {return cliente;}

    public void setModo(String modo) {this.modo = modo;}

    public void setPreco(double preco) {this.preco = preco;}

    public void setQuantidade(int quantidade) {this.quantidade = quantidade;}
    
    public void setCliente(String cliente){this.cliente = cliente;}
    
    
    //Tostring

    public String toString(){
        return "Cliente"  + this.cliente + ", Preço: " + this.preco + ", Quantidade: " + this.quantidade + 
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


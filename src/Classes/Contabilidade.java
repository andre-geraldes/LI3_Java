/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

import java.util.Set;
import java.util.TreeSet;

/**
 *
 * @author Tiago Cunha
 */
public class Contabilidade {
    private int quantidade;
    private int ncompras;
    private double faturado;
    private String modo;
    private TreeSet<String> utilizador;


//Construtores
    //Construtor vazio
    public Contabilidade() {
        this.quantidade = 0;
        this.ncompras = 0;
        this.faturado = 0;
        this.modo = "";
        this.utilizador = new TreeSet<>();
    }

    //Construtor por parametros
    public Contabilidade(int quantidade, int ncompras, double faturado,String modo) {
        this.quantidade = quantidade;
        this.ncompras = ncompras;
        this.faturado = faturado;
        this.modo = modo;
    }
    
      
    //Construtor por copia

    public Contabilidade(Contabilidade contabilidade){
        this.quantidade = contabilidade.getQuantidade();
        this.ncompras = contabilidade.getNcompras();
        this.faturado = contabilidade.getFaturado();
        this.modo = contabilidade.getModo();
        this.utilizador = contabilidade.getUtilizador();
    }

    //Métodos
    //Getters e Setters

    public double getFaturado() {return faturado;}

    public int getNcompras() {return ncompras;}

    public int getQuantidade() {return quantidade;}
    
    public String getModo() {return modo;}

    public TreeSet<String> getUtilizador() {return utilizador;}

    public void setFaturado(double faturado) {this.faturado = faturado;}

    public void setNcompras(int ncompras) {this.ncompras = ncompras;}

    public void setQuantidade(int quantidade) {this.quantidade = quantidade;}
    
    public void setModo(String modo) {this.modo = modo;}

    public void setUtilizador(TreeSet<String> utilizadorN) {this.utilizador = utilizadorN;}
       
    
    public void addUtilizador(String u){
        this.utilizador.add(u);
    }
    
    //Tostring

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        
        sb.append("Vendas: ");
        sb.append(this.quantidade).append("\n");
        sb.append("Compras: ");
        sb.append(this.ncompras).append("\n");
        sb.append("Faturado: ");
        sb.append(this.faturado).append("\n");
        sb.append("Modo: ");
        sb.append(this.modo).append("\n");
        
        for(String nome : utilizador){
            sb.append("Utilizador: ");
            sb.append(nome).append("\n");
        }
        
        return sb.toString();
    }
   
   
    
    //Equals

    @Override
     public boolean equals(Object obj) {
      if(this == obj) return true; 
      if((obj == null) || (this.getClass() != obj.getClass())) return false;
      Contabilidade c = (Contabilidade) obj;
      return this.quantidade == c.getQuantidade() && this.ncompras == c.getNcompras() && this.faturado == c.getFaturado()
             && this.utilizador.equals(c.getUtilizador()) && this.modo.equals(c.getModo());
   }

     
    //Clone
    
    @Override
     public Contabilidade clone(){
         return new Contabilidade(this);
     }

}





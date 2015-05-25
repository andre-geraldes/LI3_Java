/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

/**
 *
 * @author Tiago Cunha
 */
public class Produto {
    private String codigo;
    
    //Construtores
    //Construtor vazio
    public Produto() {
        this.codigo = "";
    }
    
    //Construtor por parametros

    public Produto(String codigo) {
        this.codigo = codigo;
    }
    
    //Construtor por copia
    public Produto(Produto produto){
        this.codigo = produto.getCodigo();
    }
    
    //Metodos
    //getters e setters

    public String getCodigo() {return codigo;}

    public void setCodigo(String codigo) {this.codigo = codigo;}
    
    //toString
    
    public String toString(){
        return "Código: "  + this.codigo;
    }
    
    //Equals
    
    public boolean equals(Object obj) {
      if(this == obj) return true; 
      if((obj == null) || (this.getClass() != obj.getClass())) return false;
      Produto c = (Produto) obj;
      return this.codigo.equals(c.getCodigo()); 
    }
    
    //Clone
    
    public Produto clone(){
         return new Produto(this);
     } 
    
}

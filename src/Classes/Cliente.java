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
public class Cliente {
    private String codigo;
    
    //Construtores
    //Construtor vazio
    public Cliente() {
        this.codigo = "";
    }
    
    //Construtor por parametros

    public Cliente(String codigo) {
        this.codigo = codigo;
    }
    
    //Construtor por copia
    public Cliente(Cliente cliente){
        this.codigo = cliente.getCodigo();
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
      Cliente c = (Cliente) obj;
      return this.codigo.equals(c.getCodigo()); 
    }
    
    //Clone
    
    public Cliente clone(){
         return new Cliente(this);
     } 
    
}

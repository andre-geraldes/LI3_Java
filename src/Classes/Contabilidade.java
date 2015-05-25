/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;

/**
 *
 * @author Tiago Cunha
 */
public class Contabilidade {
    private int nvendasP;
    private int ncomprasP;
    private double faturadoP;
    private Set<String> utilizadorP;
    private int nvendasN;
    private int ncomprasN;
    private double faturadoN;
    private Set<String> utilizadorN;


//Construtores
    //Construtor vazio
    public Contabilidade() {
        this.nvendasP = 0;
        this.ncomprasP = 0;
        this.faturadoP = 0;
        this.utilizadorP = new TreeSet<String>();;
        this.nvendasN = 0;
        this.ncomprasN = 0;
        this.faturadoN = 0;
        this.utilizadorN = new TreeSet<String>();
    }

    //Construtor por parametros
  
    public Contabilidade(int nvendasP, int ncomprasP, double faturadoP, Set<String> utilizadorP, int nvendasN, int ncomprasN, double faturadoN, Set<String> utilizadorN) {
        this.nvendasP = nvendasP;
        this.ncomprasP = ncomprasP;
        this.faturadoP = faturadoP;
        this.utilizadorP = utilizadorP;
        this.nvendasN = nvendasN;
        this.ncomprasN = ncomprasN;
        this.faturadoN = faturadoN;
        this.utilizadorN = utilizadorN;
    }
    
      
    //Construtor por copia

    public Contabilidade(Contabilidade contabilidade){
        this.nvendasP = contabilidade.getNvendasP();
        this.ncomprasP = contabilidade.getNcomprasP();
        this.faturadoP = contabilidade.getFaturadoP();
        this.utilizadorP = contabilidade.getUtilizadorP();
        this.nvendasN = contabilidade.getNvendasN();
        this.ncomprasN = contabilidade.getNcomprasN();
        this.faturadoN = contabilidade.getFaturadoN();
        this.utilizadorN = contabilidade.getUtilizadorN();
    }

    //Métodos
    //Getters e Setters

    public double getFaturadoN() {return faturadoN;}

    public int getNcomprasN() {return ncomprasN;}

    public int getNvendasN() {return nvendasN;}

    public Set<String> getUtilizadorN() {return utilizadorN;}

    public void setFaturadoN(double faturadoN) {this.faturadoN = faturadoN;}

    public void setNcomprasN(int ncomprasN) {this.ncomprasN = ncomprasN;}

    public void setNvendasN(int nvendasN) {this.nvendasN = nvendasN;}

    public void setUtilizadorN(Set<String> utilizadorN) {this.utilizadorN = utilizadorN;}
    
    public double getFaturadoP() {return faturadoP;}

    public int getNcomprasP() {return ncomprasP;}

    public int getNvendasP() {return nvendasP;}

    public Set<String> getUtilizadorP() {return utilizadorP;}

    public void setFaturadoP(double faturadoP) {this.faturadoP = faturadoP;}

    public void setNcomprasP(int ncomprasP) {this.ncomprasP = ncomprasP;}

    public void setNvendasP(int nvendasP) {this.nvendasP = nvendasP;}

    public void setUtilizadorP(Set<String> utilizadorP) {this.utilizadorP = utilizadorP;}
    
    
    //Tostring

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        
        sb.append("Vendas, modo normal: ");
        sb.append(this.nvendasN).append("\n");
        sb.append("Compras, modo normal: ");
        sb.append(this.ncomprasN).append("\n");
        sb.append("Faturado, modo normal: ");
        sb.append(this.faturadoN).append("\n");
        sb.append("Vendas, modo promocional: ");
        sb.append(this.nvendasP).append("\n");
        sb.append("Compras, modo promocional: ");
        sb.append(this.ncomprasP).append("\n");
        sb.append("Faturado, modo promocional: ");
        sb.append(this.faturadoP).append("\n");
        
        for(String nomeN : utilizadorN){
            sb.append("Utilizador, modo normal: ");
            sb.append(nomeN).append("\n");
        }
        
        for(String nomeP : utilizadorP){
            sb.append("Utilizador, modo normal: ");
            sb.append(nomeP).append("\n");
        }
        
        return sb.toString();
    }
   
   
    
    //Equals

    @Override
     public boolean equals(Object obj) {
      if(this == obj) return true; 
      if((obj == null) || (this.getClass() != obj.getClass())) return false;
      Contabilidade c = (Contabilidade) obj;
      return this.nvendasP == c.getNvendasP() && this.ncomprasP == c.getNcomprasP() && this.faturadoP == c.getFaturadoP()
             && this.utilizadorP.equals(c.getUtilizadorP()) && this.nvendasN == c.getNvendasN() && this.ncomprasN == c.getNcomprasN() &&
             this.faturadoN == c.getFaturadoN() && this.utilizadorN.equals(c.getUtilizadorN()) ;
   }

     
    //Clone
    
    @Override
     public Contabilidade clone(){
         return new Contabilidade(this);
     }

}





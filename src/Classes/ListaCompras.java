/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author Tiago Cunha
 */
public class ListaCompras implements Serializable {
 
    private ArrayList<Compra> lista;

    //Construtor vazio
    public ListaCompras() {
        this.lista = new ArrayList();
    }
    
    //Construtor por parametros
    public ListaCompras(ArrayList<Compra> lista) {
        this.lista = lista;
    }
    
    //Construtor cópia
     public ListaCompras(ListaCompras lista){
        this.lista = lista.getLista();
     }
     
    public ArrayList<Compra> getLista() {
        return this.lista;
    }

    public void setLista(ArrayList<Compra> lista) {
        this.lista = lista;
    }
    
    public int tamLista(){
        return this.lista.size();
    }
    
    public boolean containsProdut(String s){
        boolean t = false;
        for(Compra c : this.lista){
            if(c.getProduto().equals(s)) t = true;
        }
        
        return t;
    }
    
    public int vezesComprado(String s){
        int i, q = 0;
        for(i = 0; i < this.lista.size(); i++){
            if(this.lista.get(i).getProduto().equals(s)) q = this.lista.get(i).getQuantidade();
        }
        
        return q;
    }
    
    public double valorGasto(String s){
        int i;
        double q = 0;
        for (i = 0; i < this.lista.size(); i++) {
            if (this.lista.get(i).getProduto().equals(s)) {
                q = this.lista.get(i).getPreco() * this.getLista().get(i).getQuantidade();
            }
        }

        return q;
    }
    
    //clone
    @Override
    public ListaCompras clone(){
         return new ListaCompras(this);
    }

    public void addCompra(Compra compra) {
       this.lista.add(compra);
    }

    Iterable<ListaCompras> values() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}

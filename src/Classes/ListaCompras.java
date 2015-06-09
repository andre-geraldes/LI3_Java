/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

import java.util.ArrayList;

/**
 *
 * @author Tiago Cunha
 */
public class ListaCompras {
 
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

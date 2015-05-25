/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Classe hipermercado
 * 19-05-2014
 * @author barbosa
 */
public class Hipermercado implements Serializable {
    private final TreeSet<String> catalogoClientes;
    private final TreeSet<String> catalogoProdutos;
    private final ArrayList<TreeMap<String,Compra>> compras;//Cliente
    private final ArrayList<TreeMap<String,Contabilidade>> contabilidade;//Produto
    
    
    /**
     * Construtor vazio
     */
    public Hipermercado() {
        this.catalogoClientes = new TreeSet<>();
        this.catalogoProdutos = new TreeSet<>();
        this.compras = new ArrayList<>(12);
        this.contabilidade = new ArrayList<>(12);
    }
    
    public boolean lerClientes(String fich) throws IOException {
        try {
            BufferedReader br = new BufferedReader(new FileReader("src/Ficheiros/FichClientes.txt"));
            String linha;
            
            linha = br.readLine();
            while(linha != null) {
                this.catalogoClientes.add(linha);
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Hipermercado.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
    }
    
    public boolean lerProdutos(String fich) throws IOException {
        try {
            BufferedReader br = new BufferedReader(new FileReader("src/Ficheiros/FichProdutos.txt"));
            String produto;
            
            produto = br.readLine();
            while(produto != null) {
                this.catalogoProdutos.add(produto);
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Hipermercado.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
    }
    
    public boolean lerCompras(String fich) throws IOException {
        try {
            BufferedReader br = new BufferedReader(new FileReader("src/Ficheiros/Compras.txt"));
            String linha;
            String[] array;
            
            linha = br.readLine();
            while(linha != null) {
                //Preencher compra e contabilidade
                array = linha.split(" ");
                
                //this.compras.add((novo[5]-1),);
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Hipermercado.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
    }
        
    
    public boolean guardaEstado(String fich) {
        return true;
    }
    
    public boolean carregaEstado(String fich) {
        return true;
    }
}

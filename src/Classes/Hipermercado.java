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
    private Contabilidade contabilidade;
    private Compras compras;
    
    /**
     * Construtor vazio
     */
    public Hipermercado() {
        this.catalogoClientes = new TreeSet<>();
        this.catalogoProdutos = new TreeSet<>();
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
    
    public boolean lerCompras(String fich) {
        
        return true;
    }
    
    
    public boolean guardaEstado(String fich) {
        return true;
    }
    
    public boolean carregaEstado(String fich) {
        return true;
    }
}

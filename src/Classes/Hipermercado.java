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
import java.util.Set;
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
            BufferedReader br = new BufferedReader(new FileReader(fich));
            String linha;
            
            linha = br.readLine();
            while(linha != null) {
                this.catalogoClientes.add(linha);
                linha = br.readLine();
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Hipermercado.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
    }
    
    public boolean lerProdutos(String fich) throws IOException {
        try {
            BufferedReader br = new BufferedReader(new FileReader(fich));
            String produto;
            
            produto = br.readLine();
            while(produto != null) {
                this.catalogoProdutos.add(produto);
                produto = br.readLine();
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Hipermercado.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
    }
    
    public boolean lerCompras(String fich) throws IOException {
        try {
            BufferedReader br = new BufferedReader(new FileReader(fich));
            String linha;
            String[] novo;
            Compra compra;
            Contabilidade contas;
            int quant=0,total = 0;
            
            
            linha = br.readLine();
            while(linha != null) {
                //Preencher compra e contabilidade
                novo = linha.split(" ");
                int mes = Integer.parseInt(novo[5]);
                double preco = Double.parseDouble(novo[1]);
                int quantidade = Integer.parseInt(novo[2]);
                double faturado = preco * quantidade;
                
                compra = new Compra(preco, novo[3], quantidade,novo[0]);
                this.compras.get(mes-1).put(novo[4],compra);    
                
                if(this.contabilidade.get(mes-1).containsKey(novo[0]) && this.contabilidade.get(mes-1).get(novo[0]).getModo().equals(novo[3])){
                    Set<String> u = new TreeSet<String>();
                    
                    int q = this.contabilidade.get(mes-1).get(novo[0]).getQuantidade();
                    int n = this.contabilidade.get(mes-1).get(novo[0]).getNcompras();
                    double f = this.contabilidade.get(mes-1).get(novo[0]).getFaturado();
                    u = this.contabilidade.get(mes-1).get(novo[0]).getUtilizador();
                    u.add(novo[4]);
                    
                    this.contabilidade.get(mes-1).get(novo[0]).setQuantidade(q+quantidade);
                    this.contabilidade.get(mes-1).get(novo[0]).setNcompras(n+1);
                    this.contabilidade.get(mes-1).get(novo[0]).setFaturado(f + faturado);
                    this.contabilidade.get(mes-1).get(novo[0]).setUtilizador(u);
                }else{
                    contas = new Contabilidade(quantidade, 1, faturado,novo[3],novo[4]);
                    this.contabilidade.get(mes-1).put(novo[0],contas);
                }
                
                linha = br.readLine();
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

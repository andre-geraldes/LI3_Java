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
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Classe hipermercado
 * 19-05-2014
 * @author barbosa
 */
public class Hipermercado implements Serializable {
    public final TreeSet<String> catalogoClientes;
    public final TreeSet<String> catalogoProdutos;
    public final ArrayList<HashMap<String,ListaCompras>> compras;//Cliente
    public final ArrayList<HashMap<String,Contabilidade>> contabilidade;//Produto
    public int linhasInvalidas;
    
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
        int total = 0;
        try {
            BufferedReader br = new BufferedReader(new FileReader(fich));
            
            String linha;
            String[] novo;
            Compra compra;
            Contabilidade contas;
            
            for(int i = 0; i < 12; i++){
                this.compras.add(i,new HashMap<String,ListaCompras>());
            }
            
            for(int i = 0; i < 12; i++){
                this.contabilidade.add(i,new HashMap<String,Contabilidade>());
            }
            
            linha = br.readLine();
            while(linha != null) {
                //Preencher compra e contabilidade
                novo = linha.split(" ");
                int mes = Integer.parseInt(novo[5]);
                double preco = Double.parseDouble(novo[1]);
                int quantidade = Integer.parseInt(novo[2]);
                double faturado = preco * quantidade;
                
                if(linha_valida(novo[0],preco,quantidade,novo[3],novo[4],mes)){
                    
                    compra = new Compra(preco, novo[3], quantidade,novo[0]);
                    
                    if(this.compras.get(mes-1).containsKey(novo[4])){
                        this.compras.get(mes-1).get(novo[4]).addCompra(compra);
                    }else{
                        
                        ListaCompras lista = new ListaCompras();
                        lista.addCompra(compra);
                        this.compras.get(mes-1).put(novo[4],lista);
                    }
                    
                if(this.contabilidade.get(mes-1).containsKey(novo[0]) && this.contabilidade.get(mes-1).get(novo[0]).getModo().equals(novo[3])){
                    TreeSet<String> u = new TreeSet<>();

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
                        
                    contas = new Contabilidade();
                    contas.setFaturado(faturado);
                    contas.setModo(novo[3]);
                    contas.setNcompras(1);
                    contas.setQuantidade(quantidade);
                    contas.addUtilizador(novo[4]);
                    this.contabilidade.get(mes-1).put(novo[0],contas);
                    }
           }else{
                    this.linhasInvalidas++; 
                }
                linha = br.readLine();
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Hipermercado.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
    }
    
    
    public boolean linha_valida (String prod, double p, int quant, String modo, String cliente,int mes){
	boolean x = false;
        
        if(this.catalogoClientes.contains(cliente) && this.catalogoProdutos.contains(prod) && (p >= 0.0) && (quant > 0) && ((mes >= 1) || (mes <= 12))){ x = true;}
        
        return x;
    }
    
    /* 
     * Funções para as queries de consulta
     */

    //Como o TreeSet não insere repetidos, vai devolver todos os que são diferentes
    public Set<String> produtosCompradosDif(){
        int i = 0;
        String pal;
        Set<String> novo = new TreeSet<>();
        
        while(i < 12){
           for(ListaCompras c : this.compras.get(i).values()){
                Iterator<Compra> it = c.getLista().iterator();
                
                while(it.hasNext()){
                    Compra co = it.next();
                    pal = co.getProduto();
                    novo.add(pal);
                }
            }
        i++;   
        }        
        return novo;
    }
    
       
    public int clientesCompraram(){
        int i = 0;
        
        
        Set<String> novo = new TreeSet<>();
        Set<String> total = new TreeSet<>();
        
        while(i < 12){
            novo = this.compras.get(i).keySet();
            total.addAll(novo);
            i++;
        }        
        return total.size();
    }
    
    public int valorIgual(){
        int total = 0, i = 0;
        double preco = 0;
        
        while(i < 12){
            for(ListaCompras c : this.compras.get(i).values()){
                
                Iterator<Compra> it = c.getLista().iterator();
                
                while(it.hasNext()){
                    Compra co = it.next();
                    preco = co.getPreco();
                    if(preco == 0 ){
                        total++;
                    }
                }    
            }
            i++;
        }
        return total;
    }
    
    public double faturacaoTotal(){
        double total=0,faturacao = 0;
        int i=0;
        
        while(i < 12){
            for(Contabilidade c : this.contabilidade.get(i).values()){
                faturacao = c.getFaturado();
                total += faturacao;
            }
            i++;
        }
        return total;
    }
    
    public int[] totalComprasMes(){
        int total, i=0;
        int[] novo = new int[12];
        
        while(i < 12){
            total = this.compras.get(i).size();
            novo[i] = total;
            i++;
        }
        return novo;
    }
    
    public double[] totalFaturacaoMes(){
        int i=0;
        double mes, separado;
        double[] novo = new double[12];
        
         while(i < 12){
            mes = 0;
            
            for(Contabilidade c : this.contabilidade.get(i).values()){
                separado = c.getFaturado();
                mes += separado;
            }
            novo[i] = mes;
            i++;
        }
         return novo;
    }
    
    public int[] totalClientDif(){
        int i = 0,total=0;
        Set<String> novo = new TreeSet<>();
        int[] mes = new int[12];
        
        while(i < 12){
            novo = this.compras.get(i).keySet();
            total = novo.size();
            mes[i]=total;
            
            i++;
        }
        return mes;
    }
    
    
    
    /**
     * Queries Interactivas
     */
    
    
    //Querie 1, está resolvida em funções em cima
    /**************************************************************************/
    public Set<String> querie1(){
        int i = 0;
        Set<String> novo = new TreeSet<>();
        
        for(String s : this.catalogoProdutos){
            while(i < 12){
                for(ListaCompras c : this.compras.get(i).values()){
                    Iterator<Compra> it = c.getLista().iterator();
                
                    while(it.hasNext()){
                        Compra co = it.next();
                        
                        if(!co.getProduto().equals(s)){
                            novo.add(s);
                        }
                    }
                }
                i++;   
            }
        }
        return novo;
    }

    /**************************************************************************/
    //Querie 2
    public Set<String> querie2(){
        int i = 0;
        Set<String> novo = new TreeSet<>();
        Set<String> total = new TreeSet<>();
        
          
        while(i < 12){
            novo = this.compras.get(i).keySet();
            
            for(String s : novo){
                total.add(s);
            }
            i++;
        }
        return total;
    }
    
    /**
     * @param mes
     * @return 
     */
     //Querie 3
    public int querie3NTCompras(int mes){
        int tam = 0;
        for(ListaCompras c : this.compras.get(mes).values()){
            tam += c.tamLista();
        }
        return tam;
    }
    
    public int querie3NTDistintos(int mes){
        Set<String> novo = new TreeSet<>();
        
        novo = this.compras.get(mes).keySet();
        
        return novo.size();
    }

    /**************************************************************************/
    //Querie 4
    public int[] clienteCompraMes(String cliente){
        int[] novo = new int[12];
        int i = 0;
        
        while(i < 12){
            if(this.compras.get(i).containsKey(cliente)){
                novo[i] = this.compras.get(i).get(cliente).getLista().size();
            }
            i++;     
        }
        return novo;
    }

    public int[] clienteCompraDistMes(String cliente){
        int[] novo = new int[12];
        int i = 0;
        String pal;
        Set<String> aux = new TreeSet<>();
        
        while(i < 12){
            aux.clear();  
            if(this.compras.get(i).containsKey(cliente)){
                for(Compra c : this.compras.get(i).get(cliente).getLista()){
                    pal = c.getProduto();
                    aux.add(pal);
                }
                novo[i] = aux.size();
            }
            i++;
        }
        return novo;
    }
    
    public double[] clienteGastaMes(String cliente){
        double[] novo = new double[12];
        int i = 0,quantidade=0;
        double preco = 0,total = 0;
        
            while(i < 12){
                total = 0;
                if(this.compras.get(i).containsKey(cliente)){
                    for(Compra c : this.compras.get(i).get(cliente).getLista()){
                        quantidade = c.getQuantidade();
                        preco = c.getPreco();
                        total += preco*quantidade;    
                   }
                   novo[i] = total; 
                }
               i++;
            }
        return novo;
    }
    
    public double clienteTotalFaturado(String cliente){
        double total = 0;
        for(int i = 0; i < 12; i++){
            total += clienteGastaMes(cliente)[i];
        }
        return total;
    }
 
    //Querie 5 
    public int[] produtoCompraMes(String produto){
        int total = 0,i=0,aux = 0;
        int[] novo = new int[12];
        
        while( i< 12){
            if(this.contabilidade.get(i).containsKey(produto)){
                for(Contabilidade c : this.contabilidade.get(i).values()){
                    aux = c.getNcompras();
                    total += aux;
                }
            }
            novo[i] = total;
            i++;
        }
        return novo;
    }
    
    public int[] produtoClienteDifMes(String produto){
        int[] novo = new int[12];
        int i = 0;
        Set<String> aux = new TreeSet<>();
        
        
        while(i < 12){
            if(this.contabilidade.get(i).containsKey(produto)){
                aux = this.contabilidade.get(i).get(produto).getUtilizador();
                novo[i] = aux.size();
            }
            i++;
        }
        return novo;
    }
    
    public double produtoTotalFaturado(String produto){
        int total = 0,i = 0;
        while(i < 12){
            if(this.contabilidade.get(i).containsKey(produto)){
                for(Contabilidade c : this.contabilidade.get(i).values()){
                    total += c.getFaturado();
                }
            }    
            i++;
        }
        return total;
    }
    
    //Querie 6
    public int[] compradoNP(String produto, String modo){
        int[] novo = new int[12];
        int i = 0, aux;
        
        while(i < 12){
            aux = 0;
            if(this.contabilidade.get(i).containsKey(produto)){
                for(Contabilidade c : this.contabilidade.get(i).values()){
                    if(c.getModo().equals(modo)){
                        aux = c.getNcompras();
                    }
                }
            }
            novo[i] = aux;
            i++;
        }
        return novo;
    }
    
    public double faturadoNP(String produto,String modo){
        int i = 0;
        double total = 0;
        
        while(i < 12){
            if(this.contabilidade.get(i).containsKey(produto)){
                for(Contabilidade c : this.contabilidade.get(i).values()){
                    if(c.getModo().equals(modo)){
                        total += c.getFaturado();
                    }
                }
            }
            i++;
        }
        return total;
    }
    
    //Querie 7
}

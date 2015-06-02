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
import java.util.List;
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
    public final TreeSet<String> catalogoClientes;
    public final TreeSet<String> catalogoProdutos;
    public final ArrayList<TreeMap<String,Compra>> compras;//Cliente
    public final ArrayList<TreeMap<String,Contabilidade>> contabilidade;//Produto
    public int linhasInvalidas;
    
    /** 
     * Construtor vazio
     */
    
    public Hipermercado() {
        this.catalogoClientes = new TreeSet<>();
        this.catalogoProdutos = new TreeSet<>();
        this.compras = new ArrayList<>();
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
            
            for(int i = 0; i < 12; i++){
                this.compras.add(i,new TreeMap<String,Compra>());
            }
            
            for(int i = 0; i < 12; i++){
                this.contabilidade.add(i,new TreeMap<String,Contabilidade>());
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
                    this.compras.get(mes-1).put(novo[4],compra);

                    
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
                }else {this.linhasInvalidas++;}
                linha = br.readLine();
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Hipermercado.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
    }
    
    //Funções para as queries de consulta
    public boolean linha_valida (String prod, double p, int quant, String modo, String cliente,int mes){
	boolean x = false;
        
        if(this.catalogoClientes.contains(cliente) && this.catalogoProdutos.contains(prod) && (p >= 0.0) && (quant > 0) && ((mes >= 1) || (mes <= 12))){ x = true;}
        
        return x;
    }
   
    public int produtosCompradosDif(){
        int i = 0;
        String pal;
        Set<String> novo = new TreeSet<>();
        
        while(i < 12){
           for(Compra c : this.compras.get(i).values()){
                pal = c.getProduto();
                novo.add(pal);
           }
           i++;
        }        
        return novo.size();
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
    
    public int valorSuperior(){
        int total = 0, i= 0;
        double preco = 0;
        
        while(i < 12){
            for(Compra c : this.compras.get(i).values()){
                preco = c.getPreco();
                if(preco == 0 ){
                    total++;
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
    
    /**
     *
     * @return
     */
    public int[] totalComprasMes(){
        int total, i=0;
        int[] novo = new int[12];
        
        while(i < 12){
            total = 0;
     
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
            
            total = 0;
            novo = this.compras.get(i).keySet();
            total = novo.size();
            mes[i]=total;
            
            i++;
        }
        return mes;
    }
    
    //Queries Interactivas
    
    public void querie1(){
        TreeSet<String> novo = new TreeSet<>();
        int i = 0;
        String codigo;
        
        while(i < 12){
            for(Compra c : this.compras.get(i).values()){
                codigo = c.getProduto();
                
                if(this.catalogoProdutos.contains(codigo)){
                    novo.add(codigo);
                }
            }
            i++;
        }
    
    }
}

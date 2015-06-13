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
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.TreeSet;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Classe hipermercado 19-05-2014
 * Classe agregadora
 * @author barbosa
 */
public class Hipermercado implements Serializable {
    /* Paths para os ficheiros: */

    private String ficheiroClientes;
    private String ficheiroProdutos;
    private String ficheiroCompras;
    /* Estruturas para guardar dados */
    private TreeSet<String> catalogoClientes;
    private TreeSet<String> catalogoProdutos;
    private ArrayList<HashMap<String, ListaCompras>> compras;//Cliente
    private ArrayList<HashMap<String, Contabilidade>> contabilidade;//Produto
    private int linhasInvalidas;

    /**
     * Construtor vazio
     */
    public Hipermercado() {
        this.catalogoClientes = new TreeSet<>();
        this.catalogoProdutos = new TreeSet<>();
        this.compras = new ArrayList<>(12);
        this.contabilidade = new ArrayList<>(12);

        for (int i = 0; i < 12; i++) {
            this.compras.add(i, new HashMap<String, ListaCompras>());
        }

        for (int i = 0; i < 12; i++) {
            this.contabilidade.add(i, new HashMap<String, Contabilidade>());
        }
    }
    
    /**
     * Gets e sets
     * @return 
     */
    public String getFicheiroClientes() {
        return this.ficheiroClientes;
    }

    public void setFicheiroClientes(String aFicheiroClientes) {
        ficheiroClientes = aFicheiroClientes;
    }

    public String getFicheiroProdutos() {
        return this.ficheiroProdutos;
    }

    public void setFicheiroProdutos(String aFicheiroProdutos) {
        ficheiroProdutos = aFicheiroProdutos;
    }

    public String getFicheiroCompras() {
        return this.ficheiroCompras;
    }

    public void setFicheiroCompras(String aFicheiroCompras) {
        ficheiroCompras = aFicheiroCompras;
    }

    public TreeSet<String> getCatalogoClientes() {
        return this.catalogoClientes;
    }

    public void setCatalogoClientes(TreeSet<String> catalogoClientes) {
        this.catalogoClientes = catalogoClientes;
    }

    public TreeSet<String> getCatalogoProdutos() {
        return this.catalogoProdutos;
    }

    public void setCatalogoProdutos(TreeSet<String> catalogoProdutos) {
        this.catalogoProdutos = catalogoProdutos;
    }

    public ArrayList<HashMap<String, ListaCompras>> getCompras() {
        return this.compras;
    }

    public void setCompras(ArrayList<HashMap<String, ListaCompras>> compras) {
        this.compras = compras;
    }

    public ArrayList<HashMap<String, Contabilidade>> getContabilidade() {
        return this.contabilidade;
    }

    public void setContabilidade(ArrayList<HashMap<String, Contabilidade>> contabilidade) {
        this.contabilidade = contabilidade;
    }

    public int getLinhasInvalidas() {
        return this.linhasInvalidas;
    }

    public void setLinhasInvalidas(int linhasInvalidas) {
        this.linhasInvalidas = linhasInvalidas;
    }
    
    /**
     * Parsing do ficheiro de clientes
     * @return
     * @throws IOException 
     */
    public boolean lerClientes() throws IOException {
        try {
            BufferedReader br = new BufferedReader(new FileReader(this.ficheiroClientes));
            String linha;

            linha = br.readLine();
            while (linha != null) {
                this.catalogoClientes.add(linha);
                linha = br.readLine();
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Hipermercado.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
    }
    
    /**
     * Parsing do ficheiro produtos
     * @return
     * @throws IOException 
     */
    public boolean lerProdutos() throws IOException {
        try {
            BufferedReader br = new BufferedReader(new FileReader(this.ficheiroProdutos));
            String produto;

            produto = br.readLine();
            while (produto != null) {
                this.catalogoProdutos.add(produto);
                produto = br.readLine();
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Hipermercado.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
    }
    
    /**
     * Lê ficheiro de compras
     * @return
     * @throws IOException 
     */
    public boolean lerCompras() throws IOException {
        int total = 0;
        try {
            BufferedReader br = new BufferedReader(new FileReader(this.ficheiroCompras));

            String linha;
            String[] novo;
            Compra compra;
            Contabilidade contas;

            linha = br.readLine();
            while (linha != null) {
                //Preencher compra e contabilidade
                novo = linha.split(" ");
                int mes = Integer.parseInt(novo[5]);
                double preco = Double.parseDouble(novo[1]);
                int quantidade = Integer.parseInt(novo[2]);
                double faturado = preco * quantidade;
                String produto = novo[0];
                String modo = novo[3];
                String cliente = novo[4];

                if (linha_valida(produto, preco, quantidade, modo, cliente, mes)) {

                    compra = new Compra(preco, modo, quantidade, produto);

                    if (this.compras.get(mes - 1).containsKey(cliente)) {
                        this.compras.get(mes - 1).get(cliente).addCompra(compra);
                    } else {
                        ListaCompras lista = new ListaCompras();
                        lista.addCompra(compra);
                        this.compras.get(mes - 1).put(cliente, lista);
                    }

                    if (this.contabilidade.get(mes - 1).containsKey(produto)) {
                        if (modo.equals("P")) {
                            this.contabilidade.get(mes - 1).get(produto).setQuantidadeP(this.contabilidade.get(mes - 1).get(produto).getQuantidadeP() + quantidade);
                            this.contabilidade.get(mes - 1).get(produto).setNcomprasP(this.contabilidade.get(mes - 1).get(produto).getNcomprasP() + 1);
                            this.contabilidade.get(mes - 1).get(produto).setFaturadoP(this.contabilidade.get(mes - 1).get(produto).getFaturadoP() + faturado);
                            this.contabilidade.get(mes - 1).get(produto).getClientes().add(cliente);
                        } else {
                            this.contabilidade.get(mes - 1).get(produto).setQuantidadeN(this.contabilidade.get(mes - 1).get(produto).getQuantidadeN() + quantidade);
                            this.contabilidade.get(mes - 1).get(produto).setNcomprasN(this.contabilidade.get(mes - 1).get(produto).getNcomprasN() + 1);
                            this.contabilidade.get(mes - 1).get(produto).setFaturadoN(this.contabilidade.get(mes - 1).get(produto).getFaturadoN() + faturado);
                            this.contabilidade.get(mes - 1).get(produto).getClientes().add(cliente);
                        }
                    } else {
                        if (modo.equals("P")) {
                            contas = new Contabilidade();
                            contas.setFaturadoP(faturado);
                            contas.setNcomprasP(1);
                            contas.setQuantidadeP(quantidade);
                            contas.getClientes().add(cliente);
                            this.contabilidade.get(mes - 1).put(produto, contas);
                        } else if (modo.equals("N")) {
                            contas = new Contabilidade();
                            contas.setFaturadoN(faturado);
                            contas.setNcomprasN(1);
                            contas.setQuantidadeN(quantidade);
                            contas.getClientes().add(cliente);
                            this.contabilidade.get(mes - 1).put(produto, contas);
                        }
                    }
                } else {
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
    
    /**
     * verifica se uma linha é válida
     * @param prod
     * @param p
     * @param quant
     * @param modo
     * @param cliente
     * @param mes
     * @return 
     */
    public boolean linha_valida(String prod, double p, int quant, String modo, String cliente, int mes) {
        boolean x = false;

        if (this.catalogoClientes.contains(cliente) && this.catalogoProdutos.contains(prod) && (p >= 0.0) && (quant > 0) && ((mes >= 1) || (mes <= 12))) {
            x = true;
        }

        return x;
    }

    /* 
     * Funções para as queries de consulta
     * Calcula quantos produtos diferentes foram comprados
     */
    public Set<String> produtosCompradosDif() {
        int i = 0;
        String pal;
        Set<String> novo = new TreeSet<>();

        while (i < 12) {
            for (ListaCompras c : this.compras.get(i).values()) {
                Iterator<Compra> it = c.getLista().iterator();

                while (it.hasNext()) {
                    Compra co = it.next();
                    pal = co.getProduto();
                    novo.add(pal);
                }
            }
            i++;
        }
        return novo;
    }
    
    /**
     * Calcula quantos clientes compraram
     * @return 
     */
    public int clientesCompraram() {
        int i = 0;

        Set<String> novo = new TreeSet<>();
        Set<String> total = new TreeSet<>();

        while (i < 12) {
            novo = this.compras.get(i).keySet();
            total.addAll(novo);
            i++;
        }
        return total.size();
    }
    
    /**
     * calcula o número de compras com preço zero
     * @return 
     */
    public int valorIgual() {
        int total = 0, i = 0;
        double preco = 0;

        while (i < 12) {
            for (ListaCompras c : this.compras.get(i).values()) {

                Iterator<Compra> it = c.getLista().iterator();

                while (it.hasNext()) {
                    Compra co = it.next();
                    preco = co.getPreco();
                    if (preco == 0) {
                        total++;
                    }
                }
            }
            i++;
        }
        return total;
    }
    
    
    /**
     * calcula o total faturado
     * @return 
     */
    public double faturacaoTotal() {
        double total = 0;
        int i = 0;

        while (i < 12) {
            for (Contabilidade c : this.contabilidade.get(i).values()) {
                total += c.getFaturadoP() + c.getFaturadoN();
            }
            i++;
        }
        return total;
    }
    
    /**
     * Caclula o número de compras efectuadas num mês
     * @param i
     * @return 
     */
    public int totalComprasMes(int i) {
        int j = 0;

        for (ListaCompras c : this.compras.get(i).values()) {
            j += c.tamLista();
        }

        return j;
    }
    
    /**
     * calcula para todos os mesese os total faturado
     * @return 
     */
    public double[] totalFaturacaoMes() {
        int i = 0;
        double mes,facturado;
        double[] novo = new double[12];

        while (i < 12) {
            mes = 0;

            for (Contabilidade c : this.contabilidade.get(i).values()) {
                facturado = c.getFaturadoN() + c.getFaturadoP();
                mes += facturado;
            }
            novo[i] = mes;
            i++;
        }
        return novo;
    }
    
    /**
     * calcula para cada mês, o número distinto de clientes que compraram
     * @return 
     */
    public int[] totalClientDif() {
        int i = 0, total = 0;
        Set<String> novo = new TreeSet<>();
        int[] mes = new int[12];

        while (i < 12) {
            novo = this.compras.get(i).keySet();
            total = novo.size();
            mes[i] = total;

            i++;
        }
        return mes;
    }

    /**
     * Queries Interactivas
     */
    
    /**
     * Querie 1, está resolvida em funções em cima
     */
    public Set<String> querie1() {
        int i, h, j;
        Set<String> novo = new TreeSet<>(this.catalogoProdutos);
        Set<String> containsComprados = new TreeSet<>();
        ArrayList<ListaCompras> listC;

        for (i = 0; i < 12; i++) {
            listC = new ArrayList<>(this.compras.get(i).values());
            for (h = 0; h < listC.size(); h++) {
                for (j = 0; j < listC.get(h).getLista().size(); j++) {
                    containsComprados.add(listC.get(h).getLista().get(j).getProduto());
                }
            }
            listC.clear();
        }
        novo.removeAll(containsComprados);
        return novo;
    }

    /**
     * ***********************************************************************
     */
    /**
     * Query 2
     * @return 
     */
    public Set<String> querie2() {
        int i = 0;
        Set<String> novo = new TreeSet<>();
        Set<String> total = new TreeSet<>();

        while (i < 12) {
            novo = this.compras.get(i).keySet();
            for (String s : novo) {
                if (this.compras.get(i).get(s).getLista().size() == 0) { //significa que não fez compras
                    total.add(s);
                }
            }
            novo.clear();
            i++;
        }
        return total;
    }

    /**
     * Número total de compras num determinado mês
     *
     * @param mes
     * @return
     */
    public int querie3NTCompras(int mes) {
        int tam = 0;
        for (ListaCompras c : this.compras.get(mes).values()) {
            tam += c.tamLista();
        }
        return tam;
    }

    /**
     * número total dos clientes distintos que compraram em determinado mês cria
     * um set com as chaves (clientes)
     *
     * @param mes
     * @return
     */
    public int querie3NTDistintos(int mes) {
        Set<String> novo = new TreeSet<>();

        novo = this.compras.get(mes).keySet();

        return novo.size();
    }

    /**
     * ***********************************************************************
     */
    /**
     * Query 4
     * @param j
     * @param cliente
     * @return 
     */
    public int clienteCompraMes(int j, String cliente) {
        if (this.compras.get(j).containsKey(cliente)) {
            return this.compras.get(j).get(cliente).getLista().size();
        } else {
            return 0;
        }
    }
    
    /**
     * Calcula quantas compras um cliente fez em cada mês
     * @param cliente
     * @return 
     */
    public int[] clienteCompraDistMes(String cliente) {
        int[] novo = new int[12];
        int i = 0;
        String pal;
        Set<String> aux = new TreeSet<>();

        while (i < 12) {
            aux.clear();
            if (this.compras.get(i).containsKey(cliente)) {
                for (Compra c : this.compras.get(i).get(cliente).getLista()) {
                    pal = c.getProduto();
                    aux.add(pal);
                }
                novo[i] = aux.size();
            }
            i++;
        }
        return novo;
    }
    
    /**
     * Calcula quanto e que um cliente gastou em cada mês
     * @param cliente
     * @return 
     */
    public double[] clienteGastaMes(String cliente) {
        double[] novo = new double[12];
        int i = 0, quantidade = 0;
        double preco = 0, total = 0;

        while (i < 12) {
            total = 0;
            if (this.compras.get(i).containsKey(cliente)) {
                for (Compra c : this.compras.get(i).get(cliente).getLista()) {
                    quantidade = c.getQuantidade();
                    preco = c.getPreco();
                    total += preco * quantidade;
                }
                novo[i] = total;
            }
            i++;
        }
        return novo;
    }
    
    /**
     * Calcula o total faturado de um cliente
     * @param cliente
     * @return 
     */
    public double clienteTotalFaturado(String cliente) {
        double total = 0;
        for (int i = 0; i < 12; i++) {
            total += clienteGastaMes(cliente)[i];
        }
        return total;
    }

   /**
    * Query 5
    * @param i
    * @param produto
    * @return 
    */
    public int produtoCompraMes(int i, String produto) {
        int total = 0;

        if (this.contabilidade.get(i).containsKey(produto)) {
            total = this.contabilidade.get(i).get(produto).getNcomprasP() + this.contabilidade.get(i).get(produto).getNcomprasN();
        }

        return total;
    }
    
    /**
     * Calcula quantos clientes diferentes compraram um produto num dado mes
     * @param i
     * @param produto
     * @return 
     */
    public int produtoClienteDifMes(int i, String produto) {
        Set<String> aux = new TreeSet<>();

        if (this.contabilidade.get(i).containsKey(produto)) {
            aux = this.contabilidade.get(i).get(produto).getClientes();
        }

        return aux.size();
    }
    
    /**
     * Total faturado de um produto
     * @param produto
     * @return 
     */
    public double produtoTotalFaturado(String produto) {
        double total = 0;
        int i = 0;
        while (i < 12) {
            if (this.contabilidade.get(i).containsKey(produto)) {
                total += this.contabilidade.get(i).get(produto).getFaturadoN() + this.contabilidade.get(i).get(produto).getFaturadoP();
            }
            i++;
        }
        return total;
    }

    /**
     * Calcula o total de compras de um produto num mes
     * @param i
     * @param produto
     * @param modo
     * @return 
     */
    public int compradoNP(int i, String produto, String modo) {
        int aux = 0;

        if (this.contabilidade.get(i).containsKey(produto)) {
            if (modo.equals("P")) {
                aux = this.contabilidade.get(i).get(produto).getNcomprasP();
            } else {
                aux = this.contabilidade.get(i).get(produto).getNcomprasN();
            }
        }
        return aux;
    }
    
    /**
     * Calcula o total faturado de um produto
     * @param produto
     * @param modo
     * @return 
     */
    public double faturadoNP(String produto, String modo) {
        double aux = 0;

        for (int i = 0; i < 12; i++) {
            if (this.contabilidade.get(i).containsKey(produto)) {
                if (modo.equals("P")) {
                    aux += this.contabilidade.get(i).get(produto).getFaturadoP();
                } else {
                    aux += this.contabilidade.get(i).get(produto).getFaturadoN();
                }
            }
        }
        return aux;
    }

    /**
     * ´Query 7
     * @param cliente
     * @return 
     */
    public Set<String> querie7(String cliente) {
        HashMap<String, Integer> produtos = new HashMap<>();
        ArrayList<String> prod = new ArrayList<>();
        Set<String> novo = new LinkedHashSet<>();

        for (int i = 0; i < 12; i++) {
            if (this.compras.get(i).containsKey(cliente)) {
                for (int j = 0; j < this.compras.get(i).get(cliente).getLista().size(); j++) {
                    if (!produtos.containsKey(this.compras.get(i).get(cliente).getLista().get(j).getProduto())) {
                        produtos.put(this.compras.get(i).get(cliente).getLista().get(j).getProduto(), this.compras.get(i).get(cliente).getLista().get(j).getQuantidade());
                    } else {
                        produtos.put(this.compras.get(i).get(cliente).getLista().get(j).getProduto(), produtos.get(this.compras.get(i).get(cliente).getLista().get(j).getProduto()) + this.compras.get(i).get(cliente).getLista().get(j).getQuantidade());
                    }
                }
            }
        }

        for (String s : produtos.keySet()) {
            String n = s + " " + produtos.get(s);
            prod.add(n);
        }

        //Ordenar por ordem descendente e se quantidade igual por ordem alfabetica dos codigos
        Collections.sort(prod, new Comparator<String>() {
            public int compare(String a, String b) {
                int res = getQuant(b) - getQuant(a);
                if (res == 0) {
                    res = a.compareTo(b);
                }
                return res;
            }
        });

        novo = new LinkedHashSet<>(prod);

        return novo;
    }
    
    /**
     * Parsing para obter quantidade
     * @param n
     * @return 
     */
    public int getQuant(String n) {
        String[] nova;
        nova = n.split(" ");
        nova = nova[1].split("\0");

        return Integer.parseInt(nova[0]);
    }

    /**
     * Query 8
     * @param npro
     * @return 
     */
    public Set<String> querie8(int npro) {
        HashMap<String, Integer> produtos = new HashMap<>();
        ArrayList<String> prod = new ArrayList<>();
        Set<String> novo = new LinkedHashSet<>();

        for (int i = 0; i < 12; i++) {
            for (String s : this.contabilidade.get(i).keySet()) {
                if (!produtos.containsKey(s)) {
                    produtos.put(s, this.contabilidade.get(i).get(s).getQuantidadeN() + this.contabilidade.get(i).get(s).getQuantidadeP());
                } else {
                    produtos.put(s, produtos.get(s) + this.contabilidade.get(i).get(s).getQuantidadeN() + this.contabilidade.get(i).get(s).getQuantidadeP());
                }
            }
        }

        for (String s : produtos.keySet()) {
            String n = s + " " + produtos.get(s);
            prod.add(n);
        }

        //Ordenar por ordem descendente
        Collections.sort(prod, new Comparator<String>() {
            public int compare(String a, String b) {
                int res = getQuant(b) - getQuant(a);
                return res;
            }
        });

        for (int j = 0; j < prod.size(); j++) {
            int nrClientes = 0;
            TreeSet<String> clientes = new TreeSet<>();
            for (int i = 0; i < 12; i++) {
                if (this.contabilidade.get(i).containsKey(getProdu(prod.get(j)))) {
                    for (String x : this.contabilidade.get(i).get(getProdu(prod.get(j))).getClientes()) {
                        clientes.add(x);
                    }
                }
            }
            prod.set(j, "Produto: " + prod.get(j) + " Nr clientes: " + clientes.size());
        }

        for (int k = 0; k < npro; k++) {
            novo.add(prod.get(k));
        }

        return novo;
    }
    
    /**
     * Parsing para reter o produto de uma string
     * @param s
     * @return 
     */
    public String getProdu(String s) {
        String[] nova;
        nova = s.split(" ");

        return nova[0];
    }

    /**
     * Query 9
     * @param npro
     * @return 
     */
    public Set<String> querie9(int npro) {
        HashMap<String, TreeSet<String>> clientes = new HashMap<>();
        ArrayList<String> prod = new ArrayList<>();
        Set<String> novo = new LinkedHashSet<>();

        //Get do cliente e os produtos que comprou em todos os meses
        for (int i = 0; i < 12; i++) {
            for (String s : this.compras.get(i).keySet()) {
                if (!clientes.containsKey(s)) {
                    clientes.put(s, new TreeSet<String>());
                }
                for (Compra c : this.compras.get(i).get(s).getLista()) {
                    if (clientes.containsKey(s)) {
                        clientes.get(s).add(c.getProduto());
                    }
                }
            }
        }

        for (String s : clientes.keySet()) {
            String n = s + " " + clientes.get(s).size();
            prod.add(n);
        }

        //Ordenar por ordem descendente e se quantidade igual por ordem alfabetica dos codigos
        Collections.sort(prod, new Comparator<String>() {
            public int compare(String a, String b) {
                int res = getQuant(b) - getQuant(a);
                if (res == 0) {
                    res = a.compareTo(b);
                }
                return res;
            }
        });

        for (int k = 0; k < npro && k<novo.size(); k++) {
            novo.add(prod.get(k));
        }

        return novo;
    }

    /**
     * Query 10
     * @param produto
     * @param npro
     * @return 
     */
    public Set<String> querie10(String produto, int npro) {
        HashMap<String, Integer> clientes = new HashMap<>();
        HashMap<String, Double> total = new HashMap<>();
        ArrayList<String> prod = new ArrayList<>();
        Set<String> novo = new LinkedHashSet<>();

        //Get do cliente e os produtos que comprou em todos os meses
        for (int i = 0; i < 12; i++) {
            for (String s : this.compras.get(i).keySet()) {
                if (clientes.containsKey(s)) {
                    if (this.compras.get(i).get(s).containsProdut(produto)) {
                        clientes.put(s, clientes.get(s) + this.compras.get(i).get(s).vezesComprado(produto));
                        total.put(s, total.get(s) + this.compras.get(i).get(s).valorGasto(produto));
                    }
                } else {
                    if (this.compras.get(i).get(s).containsProdut(produto)) {
                        clientes.put(s, this.compras.get(i).get(s).vezesComprado(produto));
                        total.put(s, this.compras.get(i).get(s).valorGasto(produto));
                    }
                }
            }
        }

        for (String s : clientes.keySet()) {
            String n = s + " " + clientes.get(s) + " " + total.get(s);
            prod.add(n);
        }

        //Ordenar por ordem descendente e se quantidade igual por ordem alfabetica dos codigos
        Collections.sort(prod, new Comparator<String>() {
            public int compare(String a, String b) {
                int res = getQuantB(b) - getQuantB(a);
                if (res == 0) {
                    res = a.compareTo(b);
                }
                return res;
            }
        });

        for (int k = 0; k < prod.size() && k < npro; k++) {
            novo.add(prod.get(k));
        }

        return novo;
    }
    
    /**
     * Parsing de string para reter quantidade
     * @param n
     * @return 
     */
    public int getQuantB(String n) {
        String[] nova;
        nova = n.split(" ");
        nova = nova[1].split(" ");

        return Integer.parseInt(nova[0]);
    }
    
    /**
     * Faz reset às estruturas
     */
    public void clean(){
        this.catalogoClientes.clear();
        this.catalogoProdutos.clear();
        this.compras.clear();
        this.contabilidade.clear();
        
        this.catalogoClientes = new TreeSet<>();
        this.catalogoProdutos = new TreeSet<>();
        this.compras = new ArrayList<>(12);
        this.contabilidade = new ArrayList<>(12);

        for (int i = 0; i < 12; i++) {
            this.compras.add(i, new HashMap<String, ListaCompras>());
        }

        for (int i = 0; i < 12; i++) {
            this.contabilidade.add(i, new HashMap<String, Contabilidade>());
        }
    }
}

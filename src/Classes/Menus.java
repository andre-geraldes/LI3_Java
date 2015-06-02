/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;

/**
 *
 * @author Tiago Cunha
 */
public class Menus {
    
    private static String ficheiroClientes;
    private static String ficheiroProdutos;
    private static String ficheiroCompras;
    
    public String getFicheiroClientes(){
        return ficheiroClientes;
    }
    
    public String getFicheiroProdutos(){
        return ficheiroProdutos;
    }
    
    public String getFicheiroCompras(){
        return ficheiroCompras;
    }
    
    
    
    public Menus(){
    }
    
    public void menuLerFicheiro(Hipermercado hiper) throws IOException  {
        int op = 1;
        Scanner input = new Scanner(System.in);
        
        ficheiroClientes = "src/Ficheiros/FichClientes.txt";
        ficheiroProdutos = "src/Ficheiros/FichProdutos.txt";
        
        Crono.start();
        
        hiper.lerClientes(ficheiroClientes);
        hiper.lerProdutos(ficheiroProdutos);
        
        
        while(op != 0){

            System.out.println("\f");
            System.out.println("Ler ficheiros com:");
            System.out.println("1 - Compras.txt");
            System.out.println("2 - Compras1.txt");
            System.out.println("3 - Compras3.txt");
            System.out.println("0 - Voltar");
            
            op = input.nextInt();
            System.out.println();
            switch(op){
                case 1 :
                    ficheiroCompras = "src/Ficheiros/Compras.txt";
                    hiper.lerCompras(ficheiroCompras);
                    Crono.stop();
                    op = 0;
                    break;
                 
                case 2 :
                    ficheiroCompras = "src/Ficheiros/Compras1.txt";
                    hiper.lerCompras(ficheiroCompras);
                    Crono.stop();
                    op = 0;
                    break;
                
                case 3 :
                    ficheiroCompras = "src/Ficheiros/Compras3.txt";
                    hiper.lerCompras(ficheiroCompras);
                    Crono.stop();
                    op = 0;
                    break; 
                    
                case 0 :
                    // sair
                    break;
                default :
                    System.out.println(" Opção ou valor inválido, prima Enter e volte a introduzir!");
                    input.nextLine();
                    input.nextLine();
                    System.out.print("\f");
                    
            }
        }
        Crono.print();
        System.out.println("Tempo de leitura:");
        System.out.println(Crono.print()+" segs.\n");
    }

    public void menuGravarEstado(Hipermercado hiper) throws IOException{
        int op = 1;
        String file;
        Scanner input = new Scanner(System.in);
        
        while(op != 0){

            System.out.println("\f");
            System.out.println("Gravar estado em:");
            System.out.println("1 - hipermercado.obj");
            System.out.println("2 - Outro ...");
            System.out.println("0 - Voltar");
            
            
            op = input.nextInt();
            
            switch(op){
                case 1 :
                    serializaHipermercado(hiper, "hipermercado.obj");
                    op = 0;
                    break;
                 
                case 2 :
                    System.out.println("Introduza o nome do ficheiro onde pretende gravar:");
                    file = input.next();
                    serializaHipermercado(hiper, file);
                    op = 0;
                    break;

                case 0 :
                    // sair
                    break;
                default :
                    System.out.println(" Opção ou valor inválido, prima Enter e volte a introduzir!");
                    input.nextLine();
                    input.nextLine();
                    System.out.print("\f");
                    
            }
        }
    }
    
    // método que guarda a informação da rede de autores na forma de um object
    //serializar - estamos a afirmar que este objeto será transformado em bytes
    private void serializaHipermercado(Hipermercado atual, String arquivo) {
        FileOutputStream arq = null; 
        ObjectOutputStream out = null; 
        
        try {
            //arquivo(arq) no qual os dados vao ser gravados
            arq = new FileOutputStream(arquivo);
 
            //objeto que vai escrever os dados no ficheiro
            out = new ObjectOutputStream(arq);
 
            //escreve todos os dados no ficheiro
            out.writeObject(atual);
        } catch (IOException ex) {
            ex.printStackTrace(); //imprime de onde vem o erro
        } finally {
            try {
                arq.close();
                out.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
    
    public Hipermercado menuCarregarDados(Hipermercado guardado){
        int op = 1;
        String file;
        Scanner input = new Scanner(System.in);
        Hipermercado hiper = new Hipermercado();
        
        while(op != 0){

            System.out.println("\f");
            System.out.println("Carregar dados de:");
            System.out.println("1 - hipermercado.obj");
            System.out.println("2 - Outro ...");
            System.out.println("0 - Voltar");
            
            
            op = input.nextInt();
            
            switch(op){
                case 1 :
                    hiper = deserializaHipermercado("publicx.obj");
                    op = 0;
                    break;
                 
                case 2 :
                    System.out.println("Introduza o nome do ficheiro de onde pretende carregar:");
                    file = input.next();
                    hiper = deserializaHipermercado(file);
                    op = 0;
                    break;
                
                case 0 :
                    return guardado;
                    
                default :
                    System.out.println(" Opção ou valor inválido, prima Enter e volte a introduzir!");
                    input.nextLine();
                    input.nextLine();
                    System.out.print("\f");
                    
            }
        }
        return hiper;
    }

    private Hipermercado deserializaHipermercado(String nome){
        FileInputStream arqLeitura = null;
        ObjectInputStream in = null;
        Hipermercado carrega = new Hipermercado();
        
        try {
            //arquivo onde estao os dados serializados
            arqLeitura = new FileInputStream(nome);
            
            //objeto que vai ler os dados do arquivo
            in = new ObjectInputStream(arqLeitura);
            //recupera os dados
            carrega = (Hipermercado) in.readObject();
            
        } catch (FileNotFoundException fl){
             System.out.println("Ficheiro nao encontrado! Por favor volte a tentar.");
             Scanner input = new Scanner(System.in);
             input.nextLine();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (in != null){
                try {
                    arqLeitura.close();
                    in.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
           }
        }
        
 
        return carrega;
    }
    
    public void menuConsultasEstatisticas(Hipermercado hiper){
        int op = 1;
        Scanner input = new Scanner(System.in);
        
        while(op != 0){

            System.out.println("\f");
            System.out.println("Escolha a consulta que pretende fazer:");
            System.out.println("1 - Dados do ultimo ficheiro lido");
            System.out.println("2 - Números dos dados actuais");
            System.out.println("0 - Voltar");
            
            
            op = input.nextInt();
            
            switch(op){
                case 1 :
                    consultaUltimoFicheiro(hiper);
                    break;
                 
                case 2 :
                    consultaAtualFicheiro(hiper);
                    break;
                
                case 0 :
                    // sair
                    break;
                default :
                    System.out.println(" Opção ou valor inválido, prima Enter e volte a introduzir!");
                    input.nextLine();
                    input.nextLine();
                    System.out.print("\f");
                    
            }
        }
    }  
    
    private static void consultaUltimoFicheiro(Hipermercado hiper){
        Crono.start();
        
        Scanner input = new Scanner(System.in);
        System.out.println("\f");
        System.out.println("Nome do ficheiro: ");
        System.out.println("Clientes: " + indicaFich(ficheiroClientes) + "  Produtos: " + indicaFich(ficheiroProdutos) + "  Compras: " + indicaFich(ficheiroCompras));
        System.out.println("Número total de produtos: " + hiper.catalogoProdutos.size());
        System.out.println("Diferentes produtos comprados: " + hiper.produtosCompradosDif());
        System.out.println("Total não comprados: " + (hiper.catalogoProdutos.size()- hiper.produtosCompradosDif()));
        System.out.println("Número total de clientes: " + hiper.catalogoClientes.size() );
        System.out.println("Total clientes que realizaram compras: " + hiper.clientesCompraram());
        System.out.println("Total de clientes que nada compraram: " + (hiper.catalogoClientes.size() - hiper.clientesCompraram()));
        System.out.println("Total de compras de valor total igual a 0: " + hiper.valorSuperior());
        System.out.println("Faturação total: " + hiper.faturacaoTotal());
        
        Crono.stop();
        
        System.out.println("Tempo: "+ Crono.print()+" segs.\n");
        System.out.println("Prima Enter para voltar");
        input.nextLine();
    }
    
    private static void consultaAtualFicheiro(Hipermercado hiper){
        int i =0;
        int[] novoI;
        double[] novoD;
        String[] meses = {"Janeiro", "Fevereiro","Março","Abril","Maio","Junho","Julho","Agosto","Setembro","Novembro","Dezembro"};
        
        Crono.start();
        
        Scanner input = new Scanner(System.in);
        System.out.println("\f");
        System.out.println("Número total de compras por mês:");
           
            for(i = 0;i < 12 ;i++){
             System.out.println(meses[i] + ": "+ novoI[i] + ".");
            }
        System.out.println("Facturação total por mês: ");
            for(i = 0;i < 12 ;i++){
                novoD = new double[12];
                novoD = hiper.totalFaturacaoMes();
             System.out.println(meses[i] + ": "+ novoD[i] + ".");
            }/*
            
        System.out.println("Facturação total: " + hiper.faturacaoTotal());
        System.out.println();
        System.out.println("Número de distintos clientes que compraram em cada mês: ");
        hiper.totalClientDif();
        System.out.println();
        System.out.println("Total de registos de compras inválidos: " + hiper.linhasInvalidas);
        System.out.println();
                */
                
        Crono.stop();
        
        System.out.println("Tempo: "+ Crono.print()+" segs.\n");
        System.out.println("Prima Enter para voltar");
        input.nextLine();
    }

    public void menuConsultaInterativas(Hipermercado hiper) {
        int op = 1;
        Scanner input = new Scanner(System.in);
        
        while(op != 0){

            System.out.println("\f");
            System.out.println("Escolha a consulta que pretende fazer:");
            System.out.println("1 - Lista ordenada com os códigos dos produtos nunca comprados e respectivo total.");
            System.out.println("2 - Lista ordenada com os códigos dos clientes que nunca compraram e seu total.");
            System.out.println("3 - Dado um mês válido, determinar o número total de compras e o total de clientes distintos que as realizaram.");
            System.out.println("4 - Dado um código de cliente, determinar, para cada mês, quantas compras fez,\n" +
                                   "quantos produtos distintos comprou e quanto gastou. Apresentar também o total\n" +
                                   "anual facturado ao cliente.");
            System.out.println("5 - Dado o código de um produto existente, determinar, mês a mês, quantas vezes foi comprado, por quantos clientes diferentes e o total facturado.");
            System.out.println("6 - Dado o código de um produto existente, determinar, mês a mês, quantas vezes foi\n" +
                                   "comprado em modo N e em modo P e respectivas facturações;");
            System.out.println("7 - Dado o código de um cliente determinar a lista de códigos de produtos que mais\n" +
                                   "comprou (e quantos), ordenada por ordem decrescente de quantidade e, para\n" +
                                   "quantidades iguais, por ordem alfabética dos códigos;");
            System.out.println("8 - Determinar o conjunto dos X produtos mais vendidos em todo o ano, indicando o número total de distintos clientes que o compraram.");
            System.out.println("9 - Determinar os X clientes que compraram um maior número de diferentes produtos,\n" +
                                   "indicando quantos, sendo o critério de ordenação igual a 7");
            System.out.println("10 - Dado o código de um produto, determinar o conjunto dos X clientes que mais o compraram e qual o valor gasto.");
            System.out.println("0 - Voltar");
            
            
            op = input.nextInt();
            
            switch(op){
                case 1 :
                    break;
                 
                case 2 :
                    break;
                
                case 3 :
                    break;
                    
                case 4 :
                    break;
                    
                case 5 :
                    break;
                
                case 6 :
                    break;
                
                case 7 :
                    break;
                
                case 8 :
                    break;
                    
                case 9 :
                    break;
                    
                case 10 :
                    break;
                    
                case 0 :
                    // sair
                    break;
                default :
                    System.out.println(" Opção ou valor inválido, prima Enter e volte a introduzir!");
                    input.nextLine();
                    input.nextLine();
                    System.out.print("\f");
                    
            }
        }
    
    }

    private static String indicaFich(String ficheiro){
        String novo;
        
        if(ficheiro.equals("src/Ficheiros/Compras.txt")){
            novo = "Compras.txt";
        }else{
            if(ficheiro.equals("src/Ficheiros/Compras1.txt")){
                novo = "Compras1.txt";
            }else{
                novo = "Compras3.txt";
                if(ficheiro.equals("src/Ficheiros/FichClientes.txt")){
                    novo = "FichClientes.txt";
                }else{
                    novo = "FichProdutos.txt1"
                            + "";
                }
            }
        }
        
        return novo;
    }
    
    
}
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
    public void menuLerFicheiro(Hipermercado hiper) throws IOException{
        int op = 1;
        String file;
        Scanner input = new Scanner(System.in);
        
        //RedeGlobalAutores rdaut = new RedeGlobalAutores("Rede de Autores");
        
        hiper.lerClientes("src/Ficheiros/FichClientes.txt");
        hiper.lerProdutos("src/Ficheiros/FichProdutos.txt");
        
        
        while(op != 0){

            System.out.println("\f");
            System.out.println("Ler ficheiros com:");
            System.out.println("1 - Compra.txt");
            System.out.println("2 - Compra1.txt");
            System.out.println("3 - Compra3.txt");
            System.out.println("0 - Voltar");
            
            
            op = input.nextInt();
            
            switch(op){
                case 1 :
                    hiper.lerCompras("src/Ficheiros/Compra.txt");
                    op = 0;
                    break;
                 
                case 2 :
                    hiper.lerCompras("src/Ficheiros/Compra1.txt");
                    op = 0;
                    break;
                
                case 3 :
                    hiper.lerCompras("src/Ficheiros/Compra3.txt");
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
}
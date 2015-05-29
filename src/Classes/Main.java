/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author Tiago Cunha
 */
public class Main {
    public static void main(String[] args) throws IOException{
        int op = 1;
        
        Hipermercado hiper = new Hipermercado();
        
        
        Scanner input = new Scanner(System.in);   
              
        while(op != 0){
            // prints do menu
            System.out.println("\f");
            System.out.println("Escolha uma opção:");
            System.out.println("1 - Ler ficheiro ...");
            System.out.println("2 - Gravar dados ...");
            System.out.println("3 - Carregar dados ...");
            System.out.println("4 - Consultas estatisticas ...");
            System.out.println("5 - Consultas interactivas...");
            System.out.println("0 - Terminar");
            
            Menus ler = new Menus();
            
            op = input.nextInt();
            
            switch(op){
                case 1 :
                    // menu lerFicheiro
                    ler.menuLerFicheiro(hiper);
                    break;
                 
                case 2 :
                    // menu gravarDados
                    ler.menuGravarEstado(hiper);
                    break;
                
                case 3 :
                    // menu carregarDados
                        hiper = ler.menuCarregarDados(hiper);
                        
                    break;
                
                case 4 :
                    // menu consultasEstatisticas
                    //menuConsultasEstatisticas(rdaut);
                    break;
                    
                case 5 :
                    // menu consultasPorAno
                    //menuConsultasPorAno(rdaut);
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
        
        System.out.println("Aplicação terminou!");
    }
}

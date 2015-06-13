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
        //private static Input input;
    public static void main(String[] args) throws IOException{
        //input = new Input();
        int op = 1;
        boolean leu = false;
        
        Hipermercado hiper = new Hipermercado();
        Menus ler = new Menus();
        
        Scanner input = new Scanner(System.in);   
              
        while(op != 0){
            // prints do menu
            System.out.println("\f");
            System.out.println("Escolha uma opção:");
            System.out.println("1 - Ler ficheiros");
            System.out.println("2 - Gravar dados");
            System.out.println("3 - Carregar dados");
            System.out.println("4 - Consultas estatisticas");
            System.out.println("5 - Consultas interactivas");
            System.out.println("0 - Terminar");
                                    
            op = input.nextInt();
            
            //verificar caso de não ter carregado os ficheiros
            switch(op){
                case 1 :
                    // menu lerFicheiro
                    hiper.clean();
                    ler.menuLerFicheiro(hiper);
                    leu = true;
                    break;
                 
                case 2 :
                    
                    // menu gravarDados
                    if(leu) ler.menuGravarEstado(hiper);
                    break;
                
                case 3 :
                    // menu carregarDados
                    hiper.clean();
                    hiper = ler.menuCarregarDados(hiper);
                        
                    break;
                
                case 4 :
                    // menu consultasEstatisticas&
                    if(leu) ler.menuConsultasEstatisticas(hiper);
                    
                    break;
                    
                case 5 :
                    // menu consultasInterativas
                    if(leu) ler.menuConsultaInterativas(hiper);
                    break;
                
                case 0 :
                    // sair
                    break;
                default :
                    System.out.println(" Opção ou valor inválido, prima Enter e volte a introduzir!");
                    input.nextLine();
                    System.out.print("\f");
                    
            }
        }
        
        System.out.println("Aplicação terminou!");
    }

    
    
}

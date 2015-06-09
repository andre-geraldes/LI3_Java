/**
 * Classe que abstrai a utiliza��o da classe Scanner, escondendo todos os
 * problemas relacionados com excep��es, e que oferece m�todos simples e
 * robustos para a leitura de valores de tipos simples.
 * 
 * @author F. M�rio Martins
 * @version 1.0 (6/2006)
 */

package Classes;
import static java.lang.System.out;
import static java.lang.System.in;
import java.util.Scanner;
import java.util.InputMismatchException;

/**
 * 1905-15
 * @author barbosa
 */
public class Input {
/**
* M�todos de Classe
     * @return 
*/
 
 public static String lerString() {
     String txt;
     try (Scanner input = new Scanner(in)) {
         boolean ok = false;
         txt = "";
         while(!ok) {
             try {
                 txt = input.nextLine();
                 ok = true; 
             }
             catch(InputMismatchException e)
             { out.println("Texto Inválido");
             out.print("Novo valor: ");
             input.nextLine();
             }
         }
     }
     return txt;
  } 

 
 public static int lerInt() {
     Scanner input = new Scanner(in);
     boolean ok = false; 
     int i = 0; 
     while(!ok) {
         try {
             i = input.nextInt();
             ok = true;
         }
         catch(InputMismatchException e) 
             { out.println("Inteiro Inválido"); 
               out.print("Novo valor: ");
               input.nextLine(); 
             }
     }
     input.close();
     return i;
  } 
  
  public static double lerDouble() {
     Scanner input = new Scanner(in);
     boolean ok = false; 
     double d = 0.0; 
     while(!ok) {
         try {
             d = input.nextDouble();
             ok = true;
         }
         catch(InputMismatchException e) 
             { out.println("Valor real Inválido"); 
               out.print("Novo valor: ");
               input.nextLine(); 
             }
     }
     input.close();
     return d;
  }  
  
   public static float lerFloat() {
     Scanner input = new Scanner(in);
     boolean ok = false; 
     float f = 0.0f; 
     while(!ok) {
         try {
             f = input.nextFloat();
             ok = true;
         }
         catch(InputMismatchException e) 
             { out.println("Valor real Inválido"); 
               out.print("Novo valor: ");
               input.nextLine(); 
             }
     }
     input.close();
     return f;
  }  
  
   public static boolean lerBoolean() {
     Scanner input = new Scanner(in);
     boolean ok = false; 
     boolean b = false; 
     while(!ok) {
         try {
             b = input.nextBoolean();
             ok = true;
         }
         catch(InputMismatchException e) 
             { out.println("Booleano Inválido"); 
               out.print("Novo valor: ");
               input.nextLine(); 
             }
     }
     input.close();
     return b;
  } 
  
  public static short lerShort() {
     Scanner input = new Scanner(in);
     boolean ok = false; 
     short s = 0; 
     while(!ok) {
         try {
             s = input.nextShort();
             ok = true;
         }
         catch(InputMismatchException e) 
             { out.println("Short Inválido"); 
               out.print("Novo valor: ");
               input.nextLine(); 
             }
     }
     input.close();
     return s;
  }   
}

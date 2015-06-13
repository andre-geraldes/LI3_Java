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
import static java.lang.System.out;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

/**
 *
 * @author Tiago Cunha
 */
public class Menus {

    public Menus() {
    }

    public void menuLerFicheiro(Hipermercado hiper) throws IOException {
        int op = 1;
        Scanner input = new Scanner(System.in);

        hiper.setFicheiroClientes("src/Ficheiros/FichClientes.txt");
        hiper.setFicheiroProdutos("src/Ficheiros/FichProdutos.txt");

        while (op != 0) {

            System.out.println("\f");
            System.out.println("Qual o ficheiro de compras:");
            System.out.println("1 - Compras.txt");
            System.out.println("2 - Compras1.txt");
            System.out.println("3 - Compras3.txt");
            System.out.println("0 - Voltar");

            op = input.nextInt();
            System.out.println();
            switch (op) {
                case 1:
                    hiper.setFicheiroCompras("src/Ficheiros/Compras.txt");
                    Crono.start();

                    hiper.lerClientes();
                    hiper.lerProdutos();
                    hiper.lerCompras();
                    Crono.stop();
                    op = 0;
                    break;

                case 2:
                    hiper.setFicheiroCompras("src/Ficheiros/Compras1.txt");
                    Crono.start();

                    hiper.lerClientes();
                    hiper.lerProdutos();
                    hiper.lerCompras();
                    Crono.stop();
                    op = 0;
                    break;

                case 3:
                    hiper.setFicheiroCompras("src/Ficheiros/Compras3.txt");
                    Crono.start();

                    hiper.lerClientes();
                    hiper.lerProdutos();
                    hiper.lerCompras();
                    Crono.stop();
                    op = 0;
                    break;

                case 0:
                    // sair
                    break;
                default:
                    System.out.println("Opção ou valor inválido, prima Enter e volte a introduzir!");
                    input.nextLine();
                    input.nextLine();
                    System.out.print("\f");

            }
        }
        Crono.print();
        System.out.println("Tempo de leitura:");
        System.out.println(Crono.print() + " segs.\n");
    }

    public void menuGravarEstado(Hipermercado hiper) throws IOException {
        int op = 1;
        String file;
        Scanner input = new Scanner(System.in);

        while (op != 0) {

            System.out.println("\f");
            System.out.println("Gravar estado em:");
            System.out.println("1 - hipermercado.obj");
            System.out.println("2 - Outro");
            System.out.println("0 - Voltar");

            op = input.nextInt();

            switch (op) {
                case 1:
                    serializaHipermercado(hiper, "hipermercado.obj");
                    op = 0;
                    break;

                case 2:
                    System.out.println("Introduza o nome do ficheiro onde pretende gravar:");
                    file = input.next();
                    serializaHipermercado(hiper, file);
                    op = 0;
                    break;

                case 0:
                    // sair
                    break;
                default:
                    System.out.println("Opção ou valor inválido, prima Enter e volte a introduzir!");
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

    public Hipermercado menuCarregarDados(Hipermercado guardado) {
        int op = 1;
        String file;
        Scanner input = new Scanner(System.in);
        Hipermercado hiper = new Hipermercado();

        while (op != 0) {

            System.out.println("\f");
            System.out.println("Carregar dados de:");
            System.out.println("1 - hipermercado.obj");
            System.out.println("2 - Outro");
            System.out.println("0 - Voltar");

            op = input.nextInt();

            switch (op) {
                case 1:
                    hiper = deserializaHipermercado("hipermercado.obj");
                    op = 0;
                    break;

                case 2:
                    System.out.println("Introduza o nome do ficheiro de onde pretende carregar:");
                    file = input.next();
                    hiper = deserializaHipermercado(file);
                    op = 0;
                    break;

                case 0:
                    return guardado;

                default:
                    System.out.println("Opção ou valor inválido, prima Enter e volte a introduzir!");
                    input.nextLine();
                    input.nextLine();
                    System.out.print("\f");

            }
        }
        return hiper;
    }

    private Hipermercado deserializaHipermercado(String nome) {
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

        } catch (FileNotFoundException fl) {
            System.out.println("Ficheiro nao encontrado! Por favor volte a tentar.");
            Scanner input = new Scanner(System.in);
            input.nextLine();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (in != null) {
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

    public void menuConsultasEstatisticas(Hipermercado hiper) {
        int op = 1;
        Scanner input = new Scanner(System.in);

        while (op != 0) {

            System.out.println("\f");
            System.out.println("Escolha a consulta que pretende fazer:");
            System.out.println("1 - Dados do ultimo ficheiro lido");
            System.out.println("2 - Números dos dados actuais");
            System.out.println("0 - Voltar");

            op = input.nextInt();

            switch (op) {
                case 1:
                    consultaUltimoFicheiro(hiper);
                    break;

                case 2:
                    consultaAtualFicheiro(hiper);
                    break;

                case 0:
                    // sair
                    break;
                default:
                    System.out.println("Opção ou valor inválido, prima Enter e volte a introduzir!");
                    input.nextLine();
                    input.nextLine();
                    System.out.print("\f");

            }
        }
    }

    private static void consultaUltimoFicheiro(Hipermercado hiper) {
        Crono.start();

        Scanner input = new Scanner(System.in);
        System.out.println("\f");
        System.out.println("Nome do ficheiro: ");
        System.out.println("Clientes: " + indicaFich(hiper.getFicheiroClientes()) + "  Produtos: " + indicaFich(hiper.getFicheiroProdutos()) + "  Compras: " + indicaFich(hiper.getFicheiroCompras()));
        System.out.println("Número total de produtos: " + hiper.getCatalogoProdutos().size());
        System.out.println("Diferentes produtos comprados: " + hiper.produtosCompradosDif().size());
        System.out.println("Total não comprados: " + (hiper.getCatalogoProdutos().size() - hiper.produtosCompradosDif().size()));
        System.out.println("Número total de clientes: " + hiper.getCatalogoClientes().size());
        System.out.println("Total clientes que realizaram compras: " + hiper.clientesCompraram());
        System.out.println("Total de clientes que nada compraram: " + (hiper.getCatalogoClientes().size() - hiper.clientesCompraram()));
        System.out.println("Total de compras de valor total igual a 0: " + hiper.valorIgual());
        System.out.println("Faturação total: " + hiper.faturacaoTotal());

        Crono.stop();

        System.out.println("Tempo: " + Crono.print() + " segs.\n");
        System.out.println("Prima Enter para voltar");
        input.nextLine();
    }

    private static void consultaAtualFicheiro(Hipermercado hiper) {
        int i;
        String[] meses = {"Janeiro", "Fevereiro", "Março", "Abril", "Maio", "Junho", "Julho", "Agosto", "Setembro", "Outubro", "Novembro", "Dezembro"};

        Crono.start();

        Scanner input = new Scanner(System.in);
        System.out.println("\f");
        System.out.println("Número total de compras por mês:");

        for (i = 0; i < 12; i++) {
            int total = 0;
            total = hiper.totalComprasMes(i);
            System.out.println(meses[i] + ": " + total);
        }
        System.out.println();
        System.out.println("Facturação total por mês: ");
        for (i = 0; i < 12; i++) {
            double total = 0;
            total = hiper.totalFaturacaoMes()[i];
            System.out.printf(meses[i]);
            System.out.printf(": %.3f", total);
            System.out.println();
        }
        System.out.println();

        System.out.println("Facturação total: " + hiper.faturacaoTotal());
        System.out.println();
        System.out.println("Número de distintos clientes que compraram em cada mês: ");
        for (i = 0; i < 12; i++) {
            int total = 0;
            total = hiper.totalClientDif()[i];
            System.out.println(meses[i] + ": " + total);
        }
        System.out.println();
        System.out.println("Total de registos de compras inválidos: " + hiper.getLinhasInvalidas());
        System.out.println();

        Crono.stop();

        System.out.println("Tempo: " + Crono.print() + " segs.\n");
        System.out.println("Prima Enter para voltar");
        input.nextLine();
    }

    public void menuConsultaInterativas(Hipermercado hiper) {
        int op = 1;
        Scanner input = new Scanner(System.in);
        String[] meses = {"Janeiro", "Fevereiro", "Março", "Abril", "Maio", "Junho", "Julho", "Agosto", "Setembro", "Outubro", "Novembro", "Dezembro"};

        while (op != 0) {

            System.out.println("\f");
            System.out.println("Escolha a consulta que pretende fazer:");
            System.out.println("1 - Lista ordenada com os códigos dos produtos nunca comprados e respectivo total.");
            System.out.println("2 - Lista ordenada com os códigos dos clientes que nunca compraram e seu total.");
            System.out.println("3 - Dado um mês válido, determinar o número total de compras e o total de clientes\n"
                             + "distintos que as realizaram.");
            System.out.println("4 - Dado um código de cliente, determinar, para cada mês, quantas compras fez,\n"
                             + "quantos produtos distintos comprou e quanto gastou. Apresentar também o total\n"
                             + "anual facturado ao cliente.");
            System.out.println("5 - Dado o código de um produto existente, determinar, mês a mês, quantas vezes \n"
                             + "foi comprado, por quantos clientes diferentes e o total facturado.");
            System.out.println("6 - Dado o código de um produto existente, determinar, mês a mês, quantas vezes foi\n"
                             + "comprado em modo N e em modo P e respectivas facturações;");
            System.out.println("7 - Dado o código de um cliente determinar a lista de códigos de produtos que mais\n"
                             + "comprou (e quantos), ordenada por ordem decrescente de quantidade e, para\n"
                             + "quantidades iguais, por ordem alfabética dos códigos;");
            System.out.println("8 - Determinar o conjunto dos X produtos mais vendidos em todo o ano, indicando o número\n"
                             + "total de distintos clientes que o compraram.");
            System.out.println("9 - Determinar os X clientes que compraram um maior número de diferentes produtos,\n"
                             + "indicando quantos, sendo o critério de ordenação igual a 7");
            System.out.println("10 - Dado o código de um produto, determinar o conjunto dos X clientes que mais o\n"
                             + "compraram e qual o valor gasto.");
            System.out.println("0 - Voltar");

            op = input.nextInt();

            switch (op) {
                case 1:
                    Crono.start();
                    System.out.println("Códigos dos produtos nunca comprados: ");
                    Set<String> q1 = hiper.querie1();
                    Crono.stop();
                    System.out.println("Tempo de execução: " + Crono.print() + " segs.\n");
                    System.out.println("Prima Enter para continuar");
                    input.nextLine();
                    listaSets(q1);
                    System.out.println("Prima Enter para voltar");
                    input.nextLine();
                    break;

                case 2:
                    Crono.start();
                    System.out.println("Códigos dos clientes que nunca compraram: ");
                    Set<String> q2 = hiper.querie2();
                    Crono.stop();
                    System.out.println("Tempo de execução: " + Crono.print() + " segs.\n");
                    System.out.println("Prima Enter para continuar");
                    input.nextLine();

                    listaSets(q2);
                    System.out.println("Total de clientes que nunca compraram: " + q2.size());
                    System.out.println("Total clientes: " + hiper.getCatalogoClientes().size());

                    System.out.println("Prima Enter para voltar");
                    input.nextLine();
                    break;

                case 3:
                    System.out.println("Insira um mês (inteiro): ");

                    int i = input.nextInt();

                    while (i < 1 || i > 12) {
                        System.out.println("Mês inválido! ");
                        System.out.println("Insira novo valor: ");
                        i = input.nextInt();
                    }

                    Crono.start();
                    //fica (i-1), porque o array tem tamanho 12
                    System.out.println("Total de compras neste mês: " + hiper.querie3NTCompras(i - 1));
                    System.out.println("Clientes distintos neste mês: " + hiper.querie3NTDistintos(i - 1));
                    Crono.stop();
                    System.out.println("Tempo de execução: " + Crono.print() + " segs.\n");

                    System.out.println("Prima Enter para voltar");
                    input.nextLine();
                    break;

                case 4:
                    System.out.println("Insira um  código de um cliente: ");
                    String cliente;
                    cliente = input.next();

                    while (!hiper.getCatalogoClientes().contains(cliente)) {
                        System.out.println("Cliente inválido! ");
                        System.out.println("Insira novo código: ");
                        cliente = input.next();
                    }
                    Crono.start();

                    System.out.println("Quantidade de compras efectuadas em: ");
                    for (int j = 0; j < 12; j++) {
                        System.out.println(meses[j] + ": " + hiper.clienteCompraMes(j, cliente));
                    }
                    System.out.println();
                    System.out.println("Produtos diferentes comprados: ");
                    for (int j = 0; j < 12; j++) {
                        System.out.println(meses[j] + ": " + hiper.clienteCompraDistMes(cliente)[j]);
                    }
                    System.out.println();
                    System.out.println("Quanto gastou por mês: ");
                    for (int j = 0; j < 12; j++) {
                        System.out.println(meses[j] + ": " + hiper.clienteGastaMes(cliente)[j]);
                    }
                    System.out.println();
                    System.out.println("Total anual faturado: " + hiper.clienteTotalFaturado(cliente));
                    Crono.stop();
                    System.out.println("Tempo de execução: " + Crono.print() + " segs.\n");

                    System.out.println("Prima Enter para voltar");
                    input.nextLine();
                    break;

                case 5:
                    System.out.println("Insira um código de um produto: ");
                    String produto;
                    produto = input.next();

                    while (!hiper.getCatalogoProdutos().contains(produto)) {
                        System.out.println("Produto inválido!");
                        System.out.println("Insira novo código:");
                        produto = input.next();
                    }
                    Crono.start();

                    System.out.println("Quantidade de compras efectuadas em: ");
                    for (int j = 0; j < 12; j++) {
                        System.out.println(meses[j] + ": " + hiper.produtoCompraMes(j, produto));
                    }
                    System.out.println();
                    System.out.println("Clientes diferentes que compraram: ");
                    for (int j = 0; j < 12; j++) {
                        System.out.println(meses[j] + ": " + hiper.produtoClienteDifMes(j, produto));
                    }
                    System.out.println();
                    System.out.println("Total anual faturado: " + hiper.produtoTotalFaturado(produto));
                    Crono.stop();
                    System.out.println("Tempo de execução: " + Crono.print() + " segs.\n");

                    System.out.println("Prima Enter para voltar");
                    input.nextLine();
                    break;

                case 6:
                    System.out.println("Insira um código de um produto: ");
                    produto = input.next();

                    while (!hiper.getCatalogoProdutos().contains(produto)) {
                        System.out.println("Produto inválido! ");
                        System.out.println("Insira novo código: ");
                        produto = input.next();
                    }
                    Crono.start();

                    System.out.println("Quantidade de compras em modo normal: ");
                    for (int j = 0; j < 12; j++) {
                        System.out.println(meses[j] + ": " + hiper.compradoNP(j, produto, "N"));
                    }
                    System.out.println();
                    System.out.println("Faturado em modo promocional: " + hiper.faturadoNP(produto, "N"));
                    System.out.println();
                    System.out.println("Quantidade de compras em modo promocional: ");
                    for (int j = 0; j < 12; j++) {
                        System.out.println(meses[j] + ": " + hiper.compradoNP(j, produto, "P"));
                    }
                    System.out.println();
                    System.out.println("Faturado em modo promocional: " + hiper.faturadoNP(produto, "P"));
                    Crono.stop();
                    System.out.println("Tempo de execução: " + Crono.print() + " segs.\n");

                    System.out.println("Prima Enter para voltar");
                    input.nextLine();
                    break;

                case 7:
                    System.out.println("Insira um  código de um cliente: ");
                    cliente = input.next();

                    while (!hiper.getCatalogoClientes().contains(cliente)) {
                        System.out.println("Cliente inválido! ");
                        System.out.println("Insira novo código: ");
                        cliente = input.next();
                    }
                    Crono.start();
                    Set<String> novo = hiper.querie7(cliente);
                    Crono.stop();
                    System.out.println("Tempo de execução: " + Crono.print() + " segs.\n");

                    System.out.println("Prima Enter para continuar");
                    input.nextLine();
                    
                    listaSets(novo);
                    novo.clear();
                    
                    System.out.println("Prima Enter para voltar");
                    input.nextLine();
                    break;

                case 8:
                    System.out.println("Quantos produtos: ");
                    int nprod = input.nextInt();
                    Crono.start();
                    novo = hiper.querie8(nprod);
                    Crono.stop();
                    System.out.println("Tempo de execução: " + Crono.print() + " segs.\n");

                    System.out.println("Prima Enter para continuar");
                    input.nextLine();
                    listaSets(novo);
                    
                    System.out.println("Prima Enter para voltar");
                    input.nextLine();
                    
                    novo.clear();
                    break;

                case 9:
                    System.out.println("Quantos clientes: ");
                    nprod = input.nextInt();
                    Crono.start();
                    novo = hiper.querie9(nprod);
                    Crono.stop();
                    System.out.println("Tempo de execução: " + Crono.print() + " segs.\n");

                    System.out.println("Prima Enter para continuar");
                    input.nextLine();
                    
                    listaSets(novo);
                    
                    System.out.println("Prima Enter para voltar");
                    input.nextLine();
                    novo.clear();
                    break;

                case 10:
                    System.out.println("Insira um código de um produto: ");
                    produto = input.next();

                    while (!hiper.getCatalogoProdutos().contains(produto)) {
                        System.out.println("Produto inválido! ");
                        System.out.println("Insira novo código: ");
                        produto = input.next();
                    }
                    System.out.println("Quantos clientes: ");
                    nprod = input.nextInt();
                    Crono.start();
                    novo = hiper.querie10(produto, nprod);
                    Crono.stop();
                    System.out.println("Tempo de execução: " + Crono.print() + " segs.\n");

                    System.out.println("Prima Enter para continuar");
                    input.nextLine();
                    listaSets(novo);
                    novo.clear();
                    
                    System.out.println("Tempo de execução: " + Crono.print() + " segs.\n");

                    System.out.println("Prima Enter para voltar");
                    input.nextLine();
                    break;

                case 0:
                    // sair
                    break;
                default:
                    System.out.println(" Opção ou valor inválido, prima Enter e volte a introduzir!");
                    input.nextLine();
                    input.nextLine();
                    System.out.print("\f");

            }
        }

    }

    private static String indicaFich(String ficheiro) {
        String novo;

        if (ficheiro.equals("src/Ficheiros/Compras.txt")) {
            novo = "Compras.txt";
        } else {
            if (ficheiro.equals("src/Ficheiros/Compras1.txt")) {
                novo = "Compras1.txt";
            } else {
                novo = "Compras3.txt";
                if (ficheiro.equals("src/Ficheiros/FichClientes.txt")) {
                    novo = "FichClientes.txt";
                } else {
                    novo = "FichProdutos.txt";
                }
            }
        }

        return novo;
    }

    public void listaSets(Set<String> s) {
        int i = 0, indInicial = 0, indFinal = 0, n;
        int pagAtual = 0;
        int nrpag;
        Scanner in = new Scanner(System.in);
        ArrayList<String> a = new ArrayList<>(s.size());
        String op = "p";

        System.out.println("Quantos elementos quer ver de cada vez?");
        n = in.nextInt();
        nrpag = s.size() / n;

        if (!(nrpag % 2 == 0)) {
            nrpag++;
        }

        for (String o : s) {
            a.add(o);
        }

        while (!op.equals("s")) {
            switch (op) {
                case "p": {
                    for (i = indInicial; i < indInicial + n && i < s.size(); i++) {
                        System.out.println(a.get(i));
                    }
                    indInicial = i;
                    indFinal = indInicial - n * 2;
                    if (indFinal < 0) {
                        indFinal = 0;
                    }
                    pagAtual++;
                    break;
                }
                case "a": {
                    for (i = indFinal; i < indFinal + n && i >= 0; i++) {
                        System.out.println(a.get(i));
                    }
                    if (indFinal > 0) {
                        indFinal = i - n * 2;
                        if (indFinal < 0) {
                            indFinal = 0;
                        }
                        indInicial = i;
                        if (pagAtual > 1) {
                            pagAtual--;
                        }
                    } else if (indFinal == 0) {
                        indInicial = i;
                        if (pagAtual > 1) {
                            pagAtual--;
                        }
                    }
                    break;
                }
                case "s": {
                    break;
                }
                default: {
                    System.out.println("Letra inválida.");
                }
            }
            System.out.println("| p - Próximo | a - Anterior | s - Sair |");
            System.out.println("| Pagina " + pagAtual + " de " + nrpag + " | Lidos " + indInicial + " de " + s.size());
            op = in.next();
        }
    }

}

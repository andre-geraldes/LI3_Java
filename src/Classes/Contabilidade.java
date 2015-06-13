/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

import java.io.Serializable;
import java.util.TreeSet;

/**
 * Classe contabilidade
 * @author Tiago Cunha
 */
public class Contabilidade implements Serializable {
    private int quantidadeN;
    private int ncomprasN;
    private double faturadoN;
    private int quantidadeP;
    private int ncomprasP;
    private double faturadoP;
    private TreeSet<String> clientes;

    public Contabilidade() {
        this.quantidadeN = 0;
        this.ncomprasN = 0;
        this.faturadoN = 0;
        this.quantidadeP = 0;
        this.ncomprasP = 0;
        this.faturadoP = 0;
        this.clientes = new TreeSet<>();
    }
    
    public Contabilidade(int quantidadeN, int ncomprasN, double faturadoN, int quantidadeP, int ncomprasP, double faturadoP) {
        this.quantidadeN = quantidadeN;
        this.ncomprasN = ncomprasN;
        this.faturadoN = faturadoN;
        this.quantidadeP = quantidadeP;
        this.ncomprasP = ncomprasP;
        this.faturadoP = faturadoP;
        this.clientes = new TreeSet<>();
    }

    public int getQuantidadeN() {
        return this.quantidadeN;
    }

    public void setQuantidadeN(int quantidadeN) {
        this.quantidadeN = quantidadeN;
    }

    public int getNcomprasN() {
        return this.ncomprasN;
    }

    public void setNcomprasN(int ncomprasN) {
        this.ncomprasN = ncomprasN;
    }

    public double getFaturadoN() {
        return this.faturadoN;
    }

    public void setFaturadoN(double faturadoN) {
        this.faturadoN = faturadoN;
    }

    public int getQuantidadeP() {
        return this.quantidadeP;
    }

    public void setQuantidadeP(int quantidadeP) {
        this.quantidadeP = quantidadeP;
    }

    public int getNcomprasP() {
        return this.ncomprasP;
    }

    public void setNcomprasP(int ncomprasP) {
        this.ncomprasP = ncomprasP;
    }

    public double getFaturadoP() {
        return this.faturadoP;
    }

    public void setFaturadoP(double faturadoP) {
        this.faturadoP = faturadoP;
    }

    public TreeSet<String> getClientes() {
        return this.clientes;
    }

    public void setClientes(TreeSet<String> clientes) {
        this.clientes = clientes;
    }

    
        
}






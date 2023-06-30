/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package types;

import java.util.ArrayList;


public abstract class Colaborador {
    protected int id;
    protected String username;
    private String nome;
    private String senha;
    private double salario;
    private boolean desativado;
    
    public void realizaLogin(){}

    public Colaborador(int id, String username, String nome, String senha, double salario, boolean desativado) {
        this.id = id;
        this.username = username;
        this.nome = nome;
        this.senha = senha;
        this.salario = salario;
        this.desativado = desativado;
    }

    public String getUsername() {
        return username;
    }

    @Override
    public String toString() {
        return "Colaborador{" + "id=" + id + ", username=" + username + ", nome=" + nome + ", senha=" + senha + ", salario=" + salario + '}';
    }

    public String getNome() {
        return nome;
    }

    public String getSenha() {
        return senha;
    }

    public double getSalario() {
        return salario;
    }

    public int getId() {
        return id;
    }

    public boolean isDesativado() {
        return desativado;
    }

    public void setDesativado(boolean desativado) {
        this.desativado = desativado;
    }
    
}

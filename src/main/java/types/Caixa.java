/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package types;

import java.util.ArrayList;

public class Caixa extends Colaborador{
    public void incluiVenda(){};
    public void cancelaVenda(){};

    public Caixa(int id, String username, String nome, String senha, double salario, boolean desativado) {
        super(id, username, nome, senha, salario, desativado);
    }
    
    public void consultarProdutos(){};
}

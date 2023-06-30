/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package types;

import java.util.ArrayList;
import models.ColaboradoresModel;



public class Gerente extends Colaborador{

    public Gerente(int id, String username, String nome, String senha, double salario, boolean desativado) {
        super(id, username, nome, senha, salario, desativado);
    }
}

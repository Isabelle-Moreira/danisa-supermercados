/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import types.Colaborador;

/**
 *
 * @author Danilo
 */
public class UsuarioLogadoSingleton {
    private static Colaborador usuarioLogado;
    
    static public Colaborador getInstance(){
        return usuarioLogado;
    }
    
    static public void setInstance(Colaborador c){
        usuarioLogado = c;
    }
}

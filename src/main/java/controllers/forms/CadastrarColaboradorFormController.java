/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers.forms;

import types.Caixa;
import types.Fornecedor;
import types.Gerente;
import controllers.ControllerInterface;
import controllers.main.GerenteController;
import java.awt.event.ActionEvent;
import javax.swing.ButtonModel;
import javax.swing.JOptionPane;
import models.ColaboradoresModel;
import view.forms.CadastrarColaboradorFormView;

/**
 *
 * @author Danilo
 */
public class CadastrarColaboradorFormController implements ControllerInterface{
    CadastrarColaboradorFormView formView;

    public CadastrarColaboradorFormController() {
        this.formView = new CadastrarColaboradorFormView();
        this.formView.getCaixaRadio().setActionCommand("Caixa");
        this.formView.getGerenteRadio().setActionCommand("Gerente");
        this.formView.getFornecedorRadio().setActionCommand("Fornecedor");
        this.formView.getCancelar().addActionListener((ActionEvent e) -> {
            this.formView.setVisible(false);
            this.formView.dispose();
        });
        
        this.formView.getConfirmar().addActionListener((ActionEvent e) -> {
            this.cadastrarColaborador();
        });
    }
    
    public void cadastrarColaborador(){
        ColaboradoresModel model = new ColaboradoresModel();
        
        String nome = this.formView.getNome().getText();
        String salario =this.formView.getSalario().getText();
        String usuario = this.formView.getUsuario().getText();
        String senha = this.formView.getSenha().getText();
        String confirmarSenha = this.formView.getConfirmarSenha().getText();
        ButtonModel cargoButton = this.formView.getCargoButtonGroup().getSelection();
        
        
        try{
            Double.parseDouble(salario);
        }catch(Exception e){
            JOptionPane.showMessageDialog(this.formView, "Valor inválido informado no campo salário. Informe um valor numérico.", "Salário inválido", 0);
            return;
        }
        
        if(model.GetColaboradorByUsername(usuario)!=null){
            JOptionPane.showMessageDialog(this.formView, "Já existe um colaborador com este nome de usuário. Digite outro nome.", "Usuário já existente", 0);
            return;
        }
        
        if(nome.equals("")||usuario.equals("")||salario.equals("")||senha.equals("")||confirmarSenha.equals("")||cargoButton==null){
            JOptionPane.showMessageDialog(this.formView, "Preencha todos os campos para cadastrar o usuário.", "Campos em branco", 0);
            return;
        }
        
        if(!senha.equals(confirmarSenha)){
            JOptionPane.showMessageDialog(this.formView, "Certifique-se de que as senhas foram digitadas corretamente", "Erro de confirmação de senha", 0);
            return;
        }
        
        
        boolean sucess = false;
        String selectedCommand = cargoButton.getActionCommand();
        if (selectedCommand.equals("Caixa")) {
            Caixa c = new Caixa(0, usuario, nome, senha, Double.parseDouble(salario), false);
            sucess = model.insertColaborador(c);
        } else if (selectedCommand.equals("Gerente")) {
            Gerente g = new Gerente(0, usuario, nome, senha, Double.parseDouble(salario), false);
            sucess = model.insertColaborador(g);
        }else if (selectedCommand.equals("Fornecedor")) {
            Fornecedor f = new Fornecedor(0, usuario, nome, senha, Double.parseDouble(salario), false);
            sucess = model.insertColaborador(f);
        }
        
        if(sucess){
            JOptionPane.showMessageDialog(this.formView, "Colaborador cadastrado com sucesso no sistema.", "Colaborador cadastrado!", 1);
            GerenteController.getColaboradores();
        }else{
            JOptionPane.showMessageDialog(this.formView, "Ocorreu algum erro ao cadastrar o colaborador. Caso o erro persista, entre em contato com o suporte.", "Erro no sistema", 0);
        }
    }
  
    
    @Override
    public void run(){
        this.formView.setVisible(true);
    }
}

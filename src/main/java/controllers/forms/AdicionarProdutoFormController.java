/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers.forms;

import types.Fornecedor;
import types.ProdutoEstoque;
import controllers.ControllerInterface;
import controllers.main.FornecedorController;
import controllers.UsuarioLogadoSingleton;
import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;
import models.ProdutosEstoqueModel;
import view.forms.AdicionarProdutoFormView;

/**
 *
 * @author danilo
 */
public class AdicionarProdutoFormController implements ControllerInterface{
    private AdicionarProdutoFormView adicionarProdutoForm;

    public AdicionarProdutoFormController() {
        this.adicionarProdutoForm = new AdicionarProdutoFormView();
        this.adicionarProdutoForm.getAdicionarProdutoBtn().addActionListener((ActionEvent e) -> {
            this.AdicionarNovoProduto();
        });
        
        this.adicionarProdutoForm.getFecharFormBtn().addActionListener((ActionEvent e) -> {
            this.adicionarProdutoForm.setVisible(false);
            this.adicionarProdutoForm.dispose();
        });
    }
    
    public void AdicionarNovoProduto(){
        
        ProdutosEstoqueModel produtosEstoqueModel = new ProdutosEstoqueModel(); 
        String nomeProduto = this.adicionarProdutoForm.getNomeProdutoInput().getText();
        String quantEstoque = this.adicionarProdutoForm.getQuantidadeSpinner().getValue().toString();
        String valorUnitarioString = this.adicionarProdutoForm.getValorUnitarioInput().getText();
        double valorUnitario;
        
        if(nomeProduto.isEmpty()||quantEstoque.isEmpty()||valorUnitarioString.isEmpty()){
            JOptionPane.showMessageDialog(adicionarProdutoForm, "Preencha todos os campos para cadastrar um novo produto.", "Campos em branco", 0);
            return;
        }
        
        try{
            valorUnitario = Double.parseDouble(valorUnitarioString);
        }catch(NumberFormatException e){
            JOptionPane.showMessageDialog(this.adicionarProdutoForm, "Valor inválido informado no campo valor unitário.", "Não é número", 0);
            return;
        }
        
        
        ProdutoEstoque p = new ProdutoEstoque(Integer.parseInt(quantEstoque), nomeProduto, valorUnitario, (Fornecedor)UsuarioLogadoSingleton.getInstance());
        produtosEstoqueModel.insertProdutoEstoque(p);
        FornecedorController.getProdutosEstoqueFornecedor();
    }
    
    @Override
    public void run(){
        this.adicionarProdutoForm.setVisible(true);
    }
}

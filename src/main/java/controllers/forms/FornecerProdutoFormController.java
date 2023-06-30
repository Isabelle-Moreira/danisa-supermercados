/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers.forms;

import types.ProdutoEstoque;
import controllers.ControllerInterface;
import controllers.main.FornecedorController;
import controllers.UsuarioLogadoSingleton;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import models.ProdutosEstoqueModel;
import view.forms.FornecerProdutoFormView;

/**
 *
 * @author danilo
 */
public class FornecerProdutoFormController implements ControllerInterface{
    private FornecerProdutoFormView fornecerProdutoForm;

    public FornecerProdutoFormController(){
       this.fornecerProdutoForm = new FornecerProdutoFormView();
       this.setComboBox();
       
       this.fornecerProdutoForm.getFornecerBtn().addActionListener((ActionEvent e)->{
           this.fornecerProduto();
       });
       
       this.fornecerProdutoForm.getFecharBtn().addActionListener((ActionEvent e)->{
           this.fornecerProdutoForm.dispose();
       });
    }
    
    private void setComboBox(){
        int idFornecedor = UsuarioLogadoSingleton.getInstance().getId();
        ProdutosEstoqueModel produtosEstoqueModel = new ProdutosEstoqueModel();
        ArrayList<ProdutoEstoque> produtosEstoqueFornecedor = produtosEstoqueModel.GetProdutoEstoqueByFornecedor(idFornecedor);
        String []opcoesComboBox = new String[produtosEstoqueFornecedor.size()];
        
        for(int i=0; i<opcoesComboBox.length; i++){
            ProdutoEstoque p = produtosEstoqueFornecedor.get(i);
            opcoesComboBox[i] = p.getIdProduto()+"-"+p.getNome();
        }
        
        DefaultComboBoxModel<String> modelo = new DefaultComboBoxModel<>(opcoesComboBox);
        
        
        this.fornecerProdutoForm.getProdutosComboBox().setModel(modelo);
        
    }
    
    private void fornecerProduto(){
        ProdutosEstoqueModel produtosEstoqueModel = new ProdutosEstoqueModel();
        String produtoSelecionado = (String)this.fornecerProdutoForm.getProdutosComboBox().getSelectedItem();
        String quantidade = this.fornecerProdutoForm.getSpinnerQuantidade().getValue().toString();
        if(quantidade.isEmpty()||produtoSelecionado.isEmpty()){
            JOptionPane.showMessageDialog(fornecerProdutoForm,"Preencha todos os campos para fornecer um produto","Campos em branco", 0);
            return;
        }
        
        int idProdutoSelecionado = Integer.parseInt((produtoSelecionado.split("-"))[0]);
        
        produtosEstoqueModel.adicionarProdutoAoEstoque(idProdutoSelecionado, Integer.parseInt(quantidade));
        
        JOptionPane.showMessageDialog(fornecerProdutoForm, "Produto fornecido com sucesso!");
        FornecedorController.getProdutosEstoqueFornecedor();
    }
    
    @Override
    public void run(){
        this.fornecerProdutoForm.setVisible(true);
    }
}

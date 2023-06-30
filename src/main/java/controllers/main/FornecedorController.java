/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers.main;
import controllers.UsuarioLogadoSingleton;
import types.ProdutoEstoque;
import controllers.ControllerInterface;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import models.ProdutosEstoqueModel;
import view.main.FornecedorView;
import controllers.forms.AdicionarProdutoFormController;
import controllers.forms.FornecerProdutoFormController;

/**
 *
 * @author Danilo
 */
public class FornecedorController implements ControllerInterface{
    static private FornecedorView fornecedorView;

    public FornecedorController() {
        FornecedorController.fornecedorView = new FornecedorView();   
        
        FornecedorController.fornecedorView.getAdicionarProdutoBtn().addActionListener((ActionEvent e)->{
            AdicionarProdutoFormController formAdicionar = new AdicionarProdutoFormController();
            formAdicionar.run();
        });
        
        FornecedorController.fornecedorView.getFornecerProdutoBtn().addActionListener((ActionEvent e)->{
            FornecerProdutoFormController formFornecer = new FornecerProdutoFormController();
            formFornecer.run();
        });
        
        FornecedorController.fornecedorView.getSairBtn().addActionListener((ActionEvent e)->{
            int resposta = JOptionPane.showConfirmDialog(FornecedorController.fornecedorView, "Tem certeza de que deseja sair do DanIsa Supermarketing?", "Confirmação", JOptionPane.YES_NO_OPTION);
            if (resposta == JOptionPane.YES_OPTION) {
                UsuarioLogadoSingleton.setInstance(null);
                FornecedorController.fornecedorView.dispose();
            }
        });
    }
    
    static public void getProdutosEstoqueFornecedor(){
        ProdutosEstoqueModel produtosEstoqueModel = new ProdutosEstoqueModel();
        int idFornecedor = UsuarioLogadoSingleton.getInstance().getId();
        
        ArrayList<ProdutoEstoque> produtosFornecedor = produtosEstoqueModel.GetProdutoEstoqueByFornecedor(idFornecedor);
        
        DefaultTableModel model = (DefaultTableModel) FornecedorController.fornecedorView.getTabelaProdutosFornecidos().getModel();
        
        model.setRowCount(produtosFornecedor.size());
        for(int i=0; i<produtosFornecedor.size(); i++){
           ProdutoEstoque p = produtosFornecedor.get(i);
           
           model.setValueAt(p.getIdProduto(), i, 0);
           model.setValueAt(p.getNome(), i, 1);
           model.setValueAt( p.getValorUnitario(), i, 2);
        }
        
        model.fireTableDataChanged();
        
    };
    
    public void run(){
        FornecedorController.getProdutosEstoqueFornecedor();
        FornecedorController.fornecedorView.setVisible(true);   
    }
}

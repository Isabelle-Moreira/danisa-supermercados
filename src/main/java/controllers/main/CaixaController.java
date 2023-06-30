/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers.main;

import controllers.UsuarioLogadoSingleton;
import types.Venda;
import controllers.ControllerInterface;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
import models.VendasModel;
import view.main.CaixaView;
import controllers.forms.AdicionarVendaFormController;
import javax.swing.JOptionPane;
import models.ProdutosEstoqueModel;
import types.ProdutoEstoque;

/**
 *
 * @author Danilo
 */
public class CaixaController implements ControllerInterface{
    static private CaixaView caixaView;

    public CaixaController() {
        CaixaController.caixaView = new CaixaView();
        
        CaixaController.caixaView.getIncluirVendaBtn().addActionListener((ActionEvent e)->{
            AdicionarVendaFormController adicionarVendaForm = new AdicionarVendaFormController();
            adicionarVendaForm.run();
        });
        CaixaController.caixaView.getSairBtn().addActionListener((ActionEvent e)->{
            int resposta = JOptionPane.showConfirmDialog(CaixaController.caixaView, "Tem certeza de que deseja sair do DanIsa Supermarketing?", "Confirmação", JOptionPane.YES_NO_OPTION);
            if (resposta == JOptionPane.YES_OPTION) {
                UsuarioLogadoSingleton singleton = new UsuarioLogadoSingleton();
                singleton.setInstance(null);
                CaixaController.caixaView.dispose();
            }
        });
    }
    
   
    
    static public void getEstoqueDisponivel(){
        ProdutosEstoqueModel produtosEstoqueModel = new ProdutosEstoqueModel();
        ArrayList<ProdutoEstoque> estoqueDisponivel = produtosEstoqueModel.GetEstoqueDisponivel();
        DefaultTableModel model = (DefaultTableModel) CaixaController.caixaView.getTabelaEstoqueDisponivel().getModel();
        
        model.setRowCount(estoqueDisponivel.size());
        for(int i=0; i<estoqueDisponivel.size(); i++){
           ProdutoEstoque p = estoqueDisponivel.get(i);
           
           model.setValueAt(p.getIdProduto(), i, 0);
           model.setValueAt(p.getNome(),i, 1);
           model.setValueAt(p.getValorUnitario(), i, 2);
       }
        
        model.fireTableDataChanged();
    }
    
    public void run(){
        CaixaController.getEstoqueDisponivel();
        CaixaController.caixaView.setVisible(true);
    }
}

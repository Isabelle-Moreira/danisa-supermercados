/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers.forms;

import types.Caixa;
import types.ProdutoEstoque;
import types.ProdutoVenda;
import types.Venda;
import controllers.main.CaixaController;
import controllers.ControllerInterface;
import controllers.UsuarioLogadoSingleton;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import models.ProdutosEstoqueModel;
import models.ProdutosVendaModel;
import models.VendasModel;
import view.forms.AdicionarVendaFormView;

/**
 *
 * @author danilo
 */
public class AdicionarVendaFormController implements ControllerInterface{
    private AdicionarVendaFormView adicionarVendaView;
    
    private ArrayList<ProdutoVenda> produtosAvender = new ArrayList<>();
    private Venda novaVenda;
    private double TotalVenda;
    private int QuantidadeProdutos;

    public AdicionarVendaFormController() {
        this.novaVenda = new Venda((Caixa)UsuarioLogadoSingleton.getInstance(),VendasModel.getNextId());
        this.adicionarVendaView = new AdicionarVendaFormView();
        this.exibirProdutosAVender();
        this.setComboBox();
        this.adicionarVendaView.getAdicionarProdutoBtn().addActionListener((ActionEvent e)->{
            this.adicionarProduto();
        });
        
        this.adicionarVendaView.getConfirmarVendaBtn().addActionListener((ActionEvent e)->{
            this.adicionarVenda();
        });
        
        this.adicionarVendaView.getFecharBtn().addActionListener((ActionEvent e)->{
            this.adicionarVendaView.dispose();
        });
    }
    private void setComboBox(){
        ProdutosEstoqueModel produtosEstoqueModel = new ProdutosEstoqueModel();
        ArrayList<ProdutoEstoque> produtosEstoque = produtosEstoqueModel.getAllProdutosEstoque();
        String []opcoesComboBox = new String[produtosEstoque.size()];
        
        for(int i=0; i<opcoesComboBox.length; i++){
            ProdutoEstoque p = produtosEstoque.get(i);
            opcoesComboBox[i] = p.getIdProduto()+"-"+p.getNome();
        }
        
        DefaultComboBoxModel<String> modelo = new DefaultComboBoxModel<>(opcoesComboBox);
        
        
        this.adicionarVendaView.getComboBoxProduto().setModel(modelo);
        
    }
    
    private void exibirProdutosAVender(){         
        DefaultTableModel model = (DefaultTableModel) this.adicionarVendaView.getTabelaProdutosAvender().getModel();
        
        model.setRowCount(this.produtosAvender.size());
        for(int i=0; i<this.produtosAvender.size(); i++){
           ProdutoVenda p = this.produtosAvender.get(i);
           
           model.setValueAt(p.getProdutoEstoque().getNome(), i, 0);
           model.setValueAt(p.getProdutoEstoque().getValorUnitario(), i, 1);
           model.setValueAt(p.getQuantidadeVendida(), i, 2);
           model.setValueAt( p.getQuantidadeVendida()*p.getProdutoEstoque().getValorUnitario(), i, 3);
        }
        
        model.fireTableDataChanged();
    }
    
    private void adicionarProduto(){
        ProdutosEstoqueModel produtosEstoqueModel = new ProdutosEstoqueModel();
        String produtoString = (String)this.adicionarVendaView.getComboBoxProduto().getSelectedItem();
        
        int idProduto = Integer.parseInt(produtoString.split("-")[0]);
        ProdutoEstoque produtoEstoque = produtosEstoqueModel.GetProdutoEstoqueById(idProduto);
        
        int quantidade = (int)this.adicionarVendaView.getSpinnerQuantidade().getValue();
        
        ProdutoVenda novoProdutoVenda = new ProdutoVenda(produtoEstoque, this.novaVenda, quantidade);
        
        if(quantidade==0){
            JOptionPane.showMessageDialog(adicionarVendaView, "Deve-se vender pelo menos uma unidade do produto.", "Quantidade inválida", 0);
            return;
        }else if(quantidade<0){
            JOptionPane.showMessageDialog(adicionarVendaView, "Quantidade negativa é inválida.", "Quantidade inválida", 0);
            return;
        }
        
        if(novoProdutoVenda.getQuantidadeVendida()>produtoEstoque.getQuantEstoque()){
            String message = "Este produto não possui este estoque disponível para venda. Tente vender uma quantidade menor \nou entre em contato com um gerente para que seja feita uma solicitação de estoque.";
            JOptionPane.showMessageDialog(adicionarVendaView, message, "Estoque indisponível", 0);
            return;
        }
        this.produtosAvender.add(novoProdutoVenda);
        this.adicionarVendaView.getSpinnerQuantidade().setValue(0);
        
        this.QuantidadeProdutos+=quantidade;
        this.TotalVenda+=produtoEstoque.getValorUnitario()*quantidade;
        
        this.adicionarVendaView.getTotalItens().setText(Integer.toString(this.QuantidadeProdutos));
        this.adicionarVendaView.getValorTotalVenda().setText(Double.toString(this.TotalVenda));
        this.adicionarVendaView.repaint();
        this.adicionarVendaView.revalidate();
        
        this.exibirProdutosAVender();
    }
    
    private void adicionarVenda(){
        ProdutosEstoqueModel produtosEstoqueModel = new ProdutosEstoqueModel();
        ProdutosVendaModel produtosVendaModel = new ProdutosVendaModel();
        VendasModel vendasModel = new VendasModel();
        double valorTotalVenda = 0;
        for(int i=0; i<this.produtosAvender.size(); i++){
            ProdutoVenda p = this.produtosAvender.get(i);
            
            produtosEstoqueModel.retirarProdutoDoEstoque(p.getProdutoEstoque().getIdProduto(), p.getQuantidadeVendida());
            
            produtosVendaModel.insertProdutoVenda(p);
            valorTotalVenda += p.getProdutoEstoque().getValorUnitario()*p.getQuantidadeVendida();
        }
        
        this.novaVenda.setValorTotal(valorTotalVenda);
        vendasModel.insertVenda(novaVenda);
        
        JOptionPane.showMessageDialog(adicionarVendaView, "Venda adicionada com sucesso!");
    }
    
    
    @Override
    public void run(){
        this.adicionarVendaView.setVisible(true);
    }
    
    
}

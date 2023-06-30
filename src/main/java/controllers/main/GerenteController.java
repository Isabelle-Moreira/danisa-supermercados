/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers.main;


import controllers.UsuarioLogadoSingleton;
import types.Caixa;
import types.Colaborador;
import types.Fornecedor;
import types.Gerente;
import types.ProdutoEstoque;
import types.Venda;
import controllers.ControllerInterface;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import models.ColaboradoresModel;
import models.ProdutosEstoqueModel;
import models.VendasModel;
import view.main.GerenteView;
import controllers.forms.CadastrarColaboradorFormController;

/**
 *
 * @author Danilo
 */
public class GerenteController implements ControllerInterface{
    static private GerenteView gerenteView;

    public GerenteController() {
        GerenteController.gerenteView = new GerenteView();
        GerenteController.gerenteView.getBtnCadastrarColaborador().addActionListener((ActionEvent e) -> {
            CadastrarColaboradorFormController form = new CadastrarColaboradorFormController();
            form.run();
        });
        
        GerenteController.gerenteView.getDesativarColaboradorBtn().addActionListener((ActionEvent e) -> {
           GerenteController.desativarColaborador();
        });
        
        GerenteController.gerenteView.getAtivarColaboradorBtn().addActionListener((ActionEvent e) -> {
            GerenteController.ativarColaborador();
        });
        
        GerenteController.gerenteView.getBtnSair().addActionListener((ActionEvent e) -> {
            int resposta = JOptionPane.showConfirmDialog(GerenteController.gerenteView, "Tem certeza de que deseja sair do DanIsa Supermarketing?", "Confirmação", JOptionPane.YES_NO_OPTION);
            if (resposta == JOptionPane.YES_OPTION) {
                UsuarioLogadoSingleton singleton = new UsuarioLogadoSingleton();
                singleton.setInstance(null);
                GerenteController.gerenteView.dispose();
            }
        });
        
        GerenteController.gerenteView.getBuscaColaboradorInput().getDocument().addDocumentListener(new DocumentListener(){
            @Override
            public void changedUpdate(DocumentEvent e) {
                GerenteController.searchColaboradores();
            }
            @Override
            public void removeUpdate(DocumentEvent e) {
                GerenteController.searchColaboradores();
            }
            @Override
            public void insertUpdate(DocumentEvent e) {
                GerenteController.searchColaboradores();
            }
        });
        
        
        GerenteController.gerenteView.getBuscaEstoqueInput().getDocument().addDocumentListener(new DocumentListener(){
            @Override
            public void changedUpdate(DocumentEvent e) {
                GerenteController.searchEstoque();
            }
            @Override
            public void removeUpdate(DocumentEvent e) {
                GerenteController.searchEstoque();
            }
            @Override
            public void insertUpdate(DocumentEvent e) {
                GerenteController.searchEstoque();
            }
        });
     }

    static public void getColaboradores(){
        ColaboradoresModel colaboradoresModel = new ColaboradoresModel();
        ArrayList<Colaborador> listaColaboradores = colaboradoresModel.getAllColaboradores();
        //DefaultTableModel model = new DefaultTableModel(new String[]{"ID", "Username", "Nome", "Cargo","Salario"}, 0);
        
        DefaultTableModel model = (DefaultTableModel) GerenteController.gerenteView.getTabelaColaboradores().getModel();
        
        model.setRowCount(listaColaboradores.size());
        for(int i=0; i<listaColaboradores.size(); i++){
           Colaborador c = listaColaboradores.get(i);
           
           model.setValueAt(c.getId(), i, 0);
           model.setValueAt(c.getUsername(), i, 1);
           model.setValueAt( c.getNome(), i, 2);
           model.setValueAt((c instanceof Gerente)?"Gerente":(c instanceof Caixa)?"Caixa":(c instanceof Fornecedor)?"Fornecedor":"", i, 3);
           model.setValueAt(c.getSalario(), i, 4);
           model.setValueAt(c.isDesativado()?"Não":"Sim", i, 5);
        }
        
        model.fireTableDataChanged();
       
    }
    
    static public void ativarColaborador(){
        ColaboradoresModel colaboradoresModel = new ColaboradoresModel();
            String id = JOptionPane.showInputDialog("Informe o ID do colaborador que deseja ativar no sistema\nOBS: Reativar um colaborador retorna com seu login. Tenha tenha atenção e certeza do que está fazendo.");
            
            try{
                Integer.valueOf(id);
            }catch(Exception err){
                JOptionPane.showMessageDialog(gerenteView, "Valor inválido informado no campo de ID. Informe apenas inteiros sem alfanuméricos.");
            }
            
            if(Integer.parseInt(id)==UsuarioLogadoSingleton.getInstance().getId()){
                JOptionPane.showMessageDialog(gerenteView, "Não é possível desativar a si mesmo!");
                return;
            }
            
             try{
                colaboradoresModel.desativarColaborador(Integer.parseInt(id));
            }catch(Exception err){
                JOptionPane.showMessageDialog(gerenteView, "Não há colaborador com este ID no sistema.");
                return;
            }
            GerenteController.getColaboradores();
    }
    
    static public void desativarColaborador(){
            ColaboradoresModel colaboradoresModel = new ColaboradoresModel();
            String id = JOptionPane.showInputDialog("Informe o ID do colaborador que deseja desativar do sistema\nOBS: Desativar um colaborador impede seu login posteriormente. Tenha tenha atenção e certeza do que está fazendo.");
            
            try{
                Integer.valueOf(id);
            }catch(Exception err){
                JOptionPane.showMessageDialog(gerenteView, "Valor inválido informado no campo de ID. Informe apenas inteiros sem alfanuméricos.");
            }
            
            if(Integer.parseInt(id)==UsuarioLogadoSingleton.getInstance().getId()){
                JOptionPane.showMessageDialog(gerenteView, "Não é possível desativar a si mesmo!");
                return;
            }
            
            try{
                colaboradoresModel.desativarColaborador(Integer.parseInt(id));
            }catch(Exception err){
                JOptionPane.showMessageDialog(gerenteView, "Não há colaborador com este ID no sistema.");
                return;
            }
            
            
            GerenteController.getColaboradores();
    }
    
    static public void getProdutosEstoque(){
        ProdutosEstoqueModel produtosEstoqueModel = new ProdutosEstoqueModel();
        ArrayList<ProdutoEstoque> listaProdutosEstoque = produtosEstoqueModel.getAllProdutosEstoque();
        //DefaultTableModel model = new DefaultTableModel(new String[]{"ID", "Username", "Nome", "Cargo","Salario"}, 0);
        
        DefaultTableModel model = (DefaultTableModel) GerenteController.gerenteView.getTabelaProdutosEstoque().getModel();
        
        model.setRowCount(listaProdutosEstoque.size());
        for(int i=0; i<listaProdutosEstoque.size(); i++){
           ProdutoEstoque p = listaProdutosEstoque.get(i);
        
           model.setValueAt(p.getIdProduto(), i, 0);
           model.setValueAt(p.getNome(), i, 1);
           model.setValueAt(p.getQuantEstoque(), i, 2);
           model.setValueAt(p.getValorUnitario(), i, 3);
        }
        
        model.fireTableDataChanged();
    }
    
    static public void getVendas(){
        VendasModel vendasModel = new VendasModel();
        ArrayList<Venda> listaVendas = vendasModel.getVendas();
        //DefaultTableModel model = new DefaultTableModel(new String[]{"ID", "Username", "Nome", "Cargo","Salario"}, 0);
        
        DefaultTableModel model = (DefaultTableModel) GerenteController.gerenteView.getTabelaVendas().getModel();
        
        model.setRowCount(listaVendas.size());
        for(int i=0; i<listaVendas.size(); i++){
           Venda v = listaVendas.get(i);
        
           model.setValueAt(v.getIdVenda(), i, 0);
           model.setValueAt(Integer.toString(v.getCaixa().getId()), i, 1);
           model.setValueAt(v.getCaixa().getNome(), i, 2);
           model.setValueAt(v.getValorTotal(), i, 3);
        }
        
        model.fireTableDataChanged();
    }
    
    static public void searchColaboradores(){
        ColaboradoresModel colaboradoresModel = new ColaboradoresModel();
        String search = GerenteController.gerenteView.getBuscaColaboradorInput().getText();
        if(search.isEmpty()){
            GerenteController.getColaboradores();
            return;
        }
        
        ArrayList<Colaborador> listaColaboradores = colaboradoresModel.GetColaboradorBySearchString(search);
        DefaultTableModel model = (DefaultTableModel) GerenteController.gerenteView.getTabelaColaboradores().getModel();
        
        model.setRowCount(listaColaboradores.size());
        for(int i=0; i<listaColaboradores.size(); i++){
           Colaborador c = listaColaboradores.get(i);
           
           model.setValueAt(c.getId(), i, 0);
           model.setValueAt(c.getUsername(), i, 1);
           model.setValueAt( c.getNome(), i, 2);
           model.setValueAt((c instanceof Gerente)?"Gerente":(c instanceof Caixa)?"Caixa":(c instanceof Fornecedor)?"Fornecedor":"", i, 3);
           model.setValueAt(c.getSalario(), i, 4);
           model.setValueAt(c.isDesativado()?"Não":"Sim", i, 5);
        }
        
        model.fireTableDataChanged();
        
        
    }
    
   
    
    static public void searchEstoque(){
        ProdutosEstoqueModel produtosEstoqueModel = new ProdutosEstoqueModel();
        String search = GerenteController.gerenteView.getBuscaEstoqueInput().getText();
        if(search.isEmpty()){
            GerenteController.getProdutosEstoque();
            return;
        }
        
        ArrayList<ProdutoEstoque> listaProdutoEstoque = produtosEstoqueModel.GetProdutoEstoqueBySearchString(search);
        DefaultTableModel model = (DefaultTableModel) GerenteController.gerenteView.getTabelaProdutosEstoque().getModel();
        
        model.setRowCount(listaProdutoEstoque.size());
        for(int i=0; i<listaProdutoEstoque.size(); i++){
           ProdutoEstoque p = listaProdutoEstoque.get(i);
           
           model.setValueAt(p.getIdProduto(), i, 0);
           model.setValueAt(p.getNome(), i, 1);
           model.setValueAt( p.getQuantEstoque(), i, 2);
        }
        
        model.fireTableDataChanged();
        
        
    }
    
    public void run(){
        GerenteController.getColaboradores();
        GerenteController.getVendas();
        GerenteController.getProdutosEstoque();
        
        GerenteController.gerenteView.setVisible(true);
    }
}

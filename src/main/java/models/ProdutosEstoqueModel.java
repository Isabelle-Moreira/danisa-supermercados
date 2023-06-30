package models;
import types.ProdutoEstoque;
import types.Fornecedor;
import com.mycompany.supermercado2.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class ProdutosEstoqueModel {
   static private ArrayList<ProdutoEstoque> estoque = new ArrayList<>();
   static private int nextId;
   private String dbPath = "/src/main/java/models/db/ProdutosEstoqueDB.txt";
    
   public ProdutosEstoqueModel() {       
       this.CarregarProdutosEstoque();
   }
   
   private void CarregarProdutosEstoque(){
        ProdutosEstoqueModel.estoque = new ArrayList<>();
        String path = System.getProperty("user.dir")+this.dbPath;
        File ProdutosEstoqueDB = new File(path);
        try{
            
            Scanner fileReader = new Scanner(ProdutosEstoqueDB);
            ColaboradoresModel model = new ColaboradoresModel();
            while(fileReader.hasNextLine()){
                String rawData = fileReader.nextLine();
                String formatedData[] = rawData.split(",");
                Fornecedor fornecedor = (Fornecedor)model.GetColaboradorById(Integer.parseInt(formatedData[0]));
                int idProduto = Integer.parseInt(formatedData[1]);
                int quantEstoque = Integer.parseInt(formatedData[2]);
                String nome = (formatedData[3]);
                double valorUnitario = Double.parseDouble(formatedData[4]);
                ProdutoEstoque p = new ProdutoEstoque(idProduto, quantEstoque, nome, valorUnitario, fornecedor);
                ProdutosEstoqueModel.estoque.add(p);
                ProdutosEstoqueModel.nextId = p.getIdProduto()+1;
            }
       }catch(FileNotFoundException e){
            System.out.print("Não foi encontrado o arquivo TXT");
        }
              
  }
   
   public void insertProdutoEstoque(ProdutoEstoque produto){
     try {
            String path = System.getProperty("user.dir")+this.dbPath;
            FileWriter ProdutosEstoqueDbWriter = new FileWriter(path, true);
            
            String dataStringfied = produto.getFornecedor().getId()+","+ProdutosEstoqueModel.nextId+","+produto.getQuantEstoque()+","+produto.getNome()+","+produto.getValorUnitario()+"\n";
            
            ProdutosEstoqueDbWriter.write(dataStringfied);
            ProdutosEstoqueDbWriter.close();
            ProdutosEstoqueModel.nextId++;
            this.CarregarProdutosEstoque();
        } catch (IOException e) {
          System.out.println("Ops! Ocorreu um erro no sistema.");
        }
        
        
    }
  
   public void clearFile(){
        try {
            String path = System.getProperty("user.dir")+this.dbPath;
            try (FileWriter produtoEstoqueDbWriter = new FileWriter(path, false)) {  
                produtoEstoqueDbWriter.write("");    
            }
        } catch (IOException e) {
          System.out.println("Ops! Ocorreu um erro no sistema.");
        }
    }
   
   private void rewriteProdutosEstoque(){
      this.clearFile();
      try {
         String path = System.getProperty("user.dir")+this.dbPath;
         FileWriter ProdutoEstoqueDbWriter = new FileWriter(path, true);
         for(int i=0;i<ProdutosEstoqueModel.estoque.size(); i++){
            
            ProdutoEstoque produto = ProdutosEstoqueModel.estoque.get(i);
            String dataStringfied = produto.getFornecedor().getId()+","+produto.getIdProduto()+","+produto.getQuantEstoque()+","+produto.getNome()+","+produto.getValorUnitario()+"\n";
            
            ProdutoEstoqueDbWriter.write(dataStringfied);
         }
         ProdutoEstoqueDbWriter.close();
         this.CarregarProdutosEstoque();
        } catch (IOException e) {
          System.out.println("Ops! Ocorreu um erro no sistema.");
        }
      
  }
   
   
   
   
   public ArrayList<ProdutoEstoque> getAllProdutosEstoque(){
       return ProdutosEstoqueModel.estoque;
   }
   
   public ProdutoEstoque GetProdutoEstoqueById(int id){
       for (int i = 0; i < ProdutosEstoqueModel.estoque.size(); i++) {
       ProdutoEstoque p = ProdutosEstoqueModel.estoque.get(i);
        if(p.getIdProduto()==id){
            return p;
        }
           
       }
       return null;
    
   }
   
   public void adicionarProdutoAoEstoque(int id, int quantidade){
       this.GetProdutoEstoqueById(id).addEstoque(quantidade);
       this.clearFile();
       this.rewriteProdutosEstoque();
   }
   
   //Retira um produto do estoque
   public void retirarProdutoDoEstoque(int id, int quantidade){
       this.GetProdutoEstoqueById(id).retiraEstoque(quantidade);
       this.clearFile();
       this.rewriteProdutosEstoque();
   }
  
   //Recupera uma lista de produtos por busca
   public ArrayList<ProdutoEstoque> GetProdutoEstoqueBySearchString(String search){
        ArrayList<ProdutoEstoque> searchProdutoEstoque = new ArrayList<>();
        
        for(int i=0; i<ProdutosEstoqueModel.estoque.size(); i++){
            ProdutoEstoque p = ProdutosEstoqueModel.estoque.get(i);
            if(p.getNome().toLowerCase().contains(search.toLowerCase())){
                searchProdutoEstoque.add(p);
            }
        }
        
        return searchProdutoEstoque;
   }
   
   //Recupera os produtos de um determinado fornecedor
   public ArrayList<ProdutoEstoque> GetProdutoEstoqueByFornecedor(int id){
       ArrayList<ProdutoEstoque> fornecedorProdutoEstoque = new ArrayList<>();
        
        for(int i=0; i<ProdutosEstoqueModel.estoque.size(); i++){
            ProdutoEstoque p = ProdutosEstoqueModel.estoque.get(i);
            if(p.getFornecedor().getId()==id){
                fornecedorProdutoEstoque.add(p);
            }
        }
        
        return fornecedorProdutoEstoque;
   }
   
   //Recupera os produtos com estoque maior que 0.
   public ArrayList<ProdutoEstoque> GetEstoqueDisponivel(){
       ArrayList<ProdutoEstoque> estoqueDisponivel = new ArrayList<>();
        
        for(int i=0; i<ProdutosEstoqueModel.estoque.size(); i++){
            ProdutoEstoque p = ProdutosEstoqueModel.estoque.get(i);
            if(p.getQuantEstoque()>0){
                estoqueDisponivel.add(p);
            }
        }
        
        return estoqueDisponivel;
   }
}

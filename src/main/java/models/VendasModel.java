package models;
import types.Venda;
import types.Caixa;
import com.mycompany.supermercado2.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author DanIsa
 */
public class VendasModel {
   static private ArrayList<Venda> vendas = new ArrayList<>();
   private String dbPath = "/src/main/java/models/db/VendasDB.txt";
   static private int nextId;
   public VendasModel() {
       this.CarregarVendas();
    }
   
   static public int getNextId(){
       return VendasModel.nextId;
   }
   
   private void CarregarVendas(){
       VendasModel.vendas = vendas = new ArrayList<>();
        String path = System.getProperty("user.dir")+this.dbPath;
        File VendasDB = new File(path);
        try{
            Scanner fileReader = new Scanner(VendasDB);
            ColaboradoresModel model= new ColaboradoresModel();
            
            while(fileReader.hasNextLine()){
                String rawData = fileReader.nextLine();
                String formatedData[] = rawData.split(",");
                Caixa caixa = (Caixa)model.GetColaboradorById(Integer.parseInt(formatedData[0]));
                int idVenda = Integer.parseInt(formatedData[1]);
                double valorTotal = Double.parseDouble(formatedData[2]);
                
                Venda v = new Venda(caixa, idVenda, valorTotal);
                VendasModel.vendas.add(v);
                VendasModel.nextId = v.getIdVenda()+1;
                
            }
       }catch(FileNotFoundException e){
            System.out.print("Não foi encontrado o arquivo TXT");
        }
              
  }
   
  public void insertVenda(Venda venda){
     try {
            String path = System.getProperty("user.dir")+this.dbPath;
            FileWriter ProdutosEstoqueDbWriter = new FileWriter(path, true);
            
            String dataStringfied = +venda.getCaixa().getId()+","+VendasModel.nextId+","+venda.getValorTotal()+"\n";
            VendasModel.nextId++;
            ProdutosEstoqueDbWriter.write(dataStringfied);
            ProdutosEstoqueDbWriter.close();
            System.out.println("Estoque inserido com sucesso!");
        } catch (IOException e) {
          System.out.println("Ops! Ocorreu um erro no sistema.");
          
        }
        
        
    }
  
  public ArrayList<Venda> getVendas(){
      return vendas;
  }
  
  public Venda GetVendaById(int id){
       for (int i = 0; i < VendasModel.vendas.size(); i++) {
        Venda v = VendasModel.vendas.get(i);
        if(v.getIdVenda()==id){
            return v;
        }
           
       }
       return null;
    
   }
  
  public ArrayList<Venda> GetVendasBySearchString(String search){
        ArrayList<Venda> searchVendas = new ArrayList<>();
        
        for(int i=0; i<searchVendas.size(); i++){
            Venda v = searchVendas.get(i);
            if(v.getCaixa().getNome().toLowerCase().contains(search.toLowerCase())){
                searchVendas.add(v);
            }
        }
        
        return searchVendas;
    }
}

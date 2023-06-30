package models;
import types.Venda;
import types.ProdutoEstoque;
import types.ProdutoVenda;
import com.mycompany.supermercado2.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Danisa
 */
public class ProdutosVendaModel {
    private ArrayList<ProdutoVenda> produto = new ArrayList<>();
    private String dbPath = "/src/main/java/models/db/ProdutosVendaDB.txt";
    
    public ProdutosVendaModel() {
        this.CarregarProdutosVenda();
    }
    private void CarregarProdutosVenda(){
        String path = System.getProperty("user.dir")+this.dbPath;
        File ProdutosVendaDB = new File(path);
        try{
            Scanner fileReader = new Scanner(ProdutosVendaDB);
            ProdutosEstoqueModel modelEstoque = new ProdutosEstoqueModel();
            VendasModel modelVenda = new VendasModel();
            while(fileReader.hasNextLine()){
                String rawData = fileReader.nextLine();
                String formatedData[] = rawData.split(",");
                 ProdutoEstoque produtoEstoque = modelEstoque.GetProdutoEstoqueById(Integer.parseInt(formatedData[0]));
                 Venda venda = modelVenda.GetVendaById(Integer.parseInt(formatedData[1]));
                 int quantidadeVendida = Integer.parseInt(formatedData[2]);
                 ProdutoVenda e = new ProdutoVenda(produtoEstoque, venda,quantidadeVendida);
                 this.produto.add(e);
            }
        }catch(FileNotFoundException e){
            System.out.print("Não foi encontrado o arquivo TXT");
        }
       }        
       
       
    public void insertProdutoVenda(ProdutoVenda produto){
            try {
                String path = System.getProperty("user.dir")+this.dbPath;
                FileWriter ProdutosVendaDbWriter = new FileWriter(path, true);

                   String dataStringfied = produto.getProdutoEstoque().getIdProduto()+","
                                            +produto.getVenda().getIdVenda()+","
                                            +produto.getQuantidadeVendida()+"\n";
                   
                   ProdutosVendaDbWriter.write(dataStringfied);
                   ProdutosVendaDbWriter.close();
                   System.out.println("Produto inserido com sucesso!");
            } catch (IOException e) {
                 System.out.println("Ops! Ocorreu um erro no sistema.");
            }


        }   
    
}
package models;
import types.Colaborador;
import types.Gerente;
import types.Caixa;
import types.Fornecedor;
import com.mycompany.supermercado2.*;
import java.io.File;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class ColaboradoresModel {
    static private ArrayList<Colaborador> colaboradores;
    static private int nextId;
    private String dbPath = "/src/main/java/models/db/ColaboradorDB.txt";
    public ColaboradoresModel() {
        this.CarregarColaboradores();
    }
    
    //carrega os dados do arquivo txt e os transforma em instâncias de classe para serem usados no programa
    private void CarregarColaboradores(){
        ColaboradoresModel.colaboradores = new ArrayList<>();
        String path = System.getProperty("user.dir")+this.dbPath;
        System.out.println(path);
        File colaboradoresDb = new File(path);
        try{
            Scanner fileReader = new Scanner(colaboradoresDb);
            while(fileReader.hasNextLine()){
                String rawData = fileReader.nextLine();
                String formatedData[] = rawData.split(",");
                
                int id = Integer.parseInt(formatedData[0]);
                String username = formatedData[1];
                String nome = formatedData[2];
                String senha = formatedData[3];
                double salario= Double.parseDouble(formatedData[4]);
                int cargo = Integer.parseInt(formatedData[5]);
                boolean isDesativado = Boolean.parseBoolean(formatedData[6]);
                Colaborador c;
                switch(cargo){
                    /**
                    Legenda de cargos:

                    0 - Caixa
                    1 - Gerente
                    2 - Fornecedor
                    */
                    case 0 -> {
                        c = new Caixa(id, username, nome, senha, salario, isDesativado);
                        ColaboradoresModel.colaboradores.add(c);
                        ColaboradoresModel.nextId = c.getId()+1;
                    }
                    case 1 -> {
                        c = new Gerente (id, username, nome, senha, salario, isDesativado);
                        ColaboradoresModel.colaboradores.add(c);
                        ColaboradoresModel.nextId = c.getId()+1;
                    }
                    case 2 -> { 
                        c = new Fornecedor (id, username, nome, senha, salario, isDesativado);
                        ColaboradoresModel.colaboradores.add(c);
                        ColaboradoresModel.nextId = c.getId()+1;
                    }
                }
                
            }
            
        }catch(FileNotFoundException e){
            System.out.print("Não foi encontrado o arquivo TXT");
        }
    }
    
    public boolean insertColaborador(
        Colaborador colab
    ){
        boolean sucess = true;
        try {
            String path = System.getProperty("user.dir")+this.dbPath;
            FileWriter colaboradoresDbWriter = new FileWriter(path, true);
            int cargo = 0;
            
            /**
                Legenda de cargos:

                0 - Caixa
                1 - Gerente
                2 - Fornecedor
            */
            if(colab instanceof Caixa){
                cargo = 0;
            }else if(colab instanceof Gerente){
                cargo = 1;
            }else if(colab instanceof Fornecedor){
                cargo = 2;
            }
            String dataStringfied;
            dataStringfied = ColaboradoresModel.nextId+","
                    +colab.getUsername()+","+colab.getNome()+","
                    +colab.getSenha()+","+colab.getSalario()+","
                    +cargo+","
                    +colab.isDesativado()
                    +"\n";
                
            colaboradoresDbWriter.write(dataStringfied);
            colaboradoresDbWriter.close();
            
            if(colab.getId()==0){
                ColaboradoresModel.nextId++;
            }
            
            this.CarregarColaboradores();
            return sucess;
        } catch (IOException e) {
          System.out.println("Ops! Ocorreu um erro no sistema.");
          return !sucess;
        }
    }
    
    //Reescreve os dados salvos no programa para o arquivo de txt. Necessário fazer isso quando se edita alguma informação que já estava salva
    private void rewriteColaboradores(){
      this.clearFile();
      try {
         String path = System.getProperty("user.dir")+this.dbPath;
         FileWriter colaboradoresDbWriter = new FileWriter(path, true);
         for(int i=0;i<ColaboradoresModel.colaboradores.size(); i++){
            Colaborador colab = ColaboradoresModel.colaboradores.get(i);
            int cargo = 0;
            if(colab instanceof Caixa){
                cargo = 0;
            }else if(colab instanceof Gerente){
                cargo = 1;
            }else if(colab instanceof Fornecedor){
                cargo = 2;
            }
            String dataStringfied;
            dataStringfied = colab.getId()+","
                    +colab.getUsername()+","+colab.getNome()+","
                    +colab.getSenha()+","+colab.getSalario()+","
                    +cargo+","+colab.isDesativado()+"\n";
                
            colaboradoresDbWriter.write(dataStringfied);
         }
         colaboradoresDbWriter.close();
         this.CarregarColaboradores();
        } catch (IOException e) {
          System.out.println("Ops! Ocorreu um erro no sistema.");
        }
      
  }
    
    //limpa todos os dados do arquivo
    public void clearFile(){
        try {
            String path = System.getProperty("user.dir")+this.dbPath;
            try (FileWriter colaboradoresDbWriter = new FileWriter(path, false)) {  
                colaboradoresDbWriter.write("");    
            }
        } catch (IOException e) {
          System.out.println("Ops! Ocorreu um erro no sistema.");
        }
    }
    
    //retorna classes para serem usadas no programa
    public ArrayList<Colaborador> getAllColaboradores(){
        return colaboradores;
    }
    
    //retorna uma classe por ID
    public Colaborador GetColaboradorById(int id){
       for (int i = 0; i < ColaboradoresModel.colaboradores.size(); i++) {
        Colaborador c = ColaboradoresModel.colaboradores.get(i);
        if(c.getId()==id){
            return c;
        }
           
       }
       return null;
    
    }
    //faz login de usuário com senha
    public Colaborador GetColaboradorByLogin(String usuario, String senha){
       for (int i = 0; i < ColaboradoresModel.colaboradores.size(); i++) {
        Colaborador c = ColaboradoresModel.colaboradores.get(i);
        if(c.getUsername().equals(usuario)&&c.getSenha().equals(senha)){
            return c;
        }
           
       }
       return null;
    
   }
   //faz login de usuário com senha
    public Colaborador GetColaboradorByUsername(String usuario){
       for (int i = 0; i < ColaboradoresModel.colaboradores.size(); i++) {
        Colaborador c = ColaboradoresModel.colaboradores.get(i);
        if(c.getUsername().equals(usuario)){
            return c;
        }
           
       }
       return null;
    
   }
    
    //Recupera colaboradores por busca
    public ArrayList<Colaborador> GetColaboradorBySearchString(String search){
        ArrayList<Colaborador> searchColaboradores = new ArrayList<>();
        
        for(int i=0; i<colaboradores.size(); i++){
            Colaborador c = colaboradores.get(i);
            if(c.getNome().toLowerCase().contains(search.toLowerCase())){
                searchColaboradores.add(c);
            }
        }
        
        return searchColaboradores;
    }
    
    //Desativa um colaborador. Colaboradores desativados não conseguem logar no sistema.
    public void desativarColaborador(int id){
      this.GetColaboradorById(id).setDesativado(true);
      this.clearFile();
      this.rewriteColaboradores();
  }
  
    //Reativa um colaborador de modo a recuperar seu login.
    public void ativarColaborador(int id){
      this.GetColaboradorById(id).setDesativado(false);
      this.clearFile();
      this.rewriteColaboradores();
  }
}
   

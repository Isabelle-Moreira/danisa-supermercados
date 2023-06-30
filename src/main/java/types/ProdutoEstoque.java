package types;

public class ProdutoEstoque {
    private Fornecedor fornecedor;
    private int idProduto;
    private int quantEstoque;
    private String nome;
    private double valorUnitario;
    
    
    public void addEstoque(int quantidade){
        this.quantEstoque+=quantidade;
    }
    
    public void retiraEstoque(int quantidade){
        this.quantEstoque+=quantidade;
    }
    
    public int getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(int idProduto) {
        this.idProduto = idProduto;
    }

    public int getQuantEstoque() {
        return quantEstoque;
    }

    public void setQuantEstoque(int quantEstoque) {
        this.quantEstoque = quantEstoque;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getValorUnitario() {
        return valorUnitario;
    }

    public void setValorUnitario(double valorUnitario) {
        this.valorUnitario = valorUnitario;
    }

    public Fornecedor getFornecedor() {
        return fornecedor;
    }
  
    

    public ProdutoEstoque(int idProduto, int quantEstoque, String nome, double valorUnitario, Fornecedor fornecedor) {
        this.idProduto = idProduto;
        this.quantEstoque = quantEstoque;
        this.nome = nome;
        this.valorUnitario = valorUnitario;
        this.fornecedor = fornecedor;
    }
    
    public ProdutoEstoque(int quantEstoque, String nome, double valorUnitario, Fornecedor fornecedor) {
        this.quantEstoque = quantEstoque;
        this.nome = nome;
        this.valorUnitario = valorUnitario;
        this.fornecedor = fornecedor;
    }
    
}

package types;

/**
 *
 * @author DanIsa
 */
public class ProdutoVenda {
    private ProdutoEstoque produtoEstoque;
    private Venda venda;
    private int quantidadeVendida;

    public ProdutoVenda(ProdutoEstoque produtoEstoque, Venda venda, int quantidadeVendida) {
        this.venda = venda;
        this.produtoEstoque = produtoEstoque;
        this.quantidadeVendida = quantidadeVendida;
    }

    public ProdutoEstoque getProdutoEstoque() {
        return produtoEstoque;
    }

    public void setProdutoEstoque(ProdutoEstoque produtoEstoque) {
        this.produtoEstoque = produtoEstoque;
    }

    public int getQuantidadeVendida() {
        return quantidadeVendida;
    }

    public Venda getVenda() {
        return venda;
    }

    public void setQuantidadeVendida(int quantidadeVendida) {
        this.quantidadeVendida = quantidadeVendida;
    }
}



package types;

import types.Colaborador;
import types.Caixa;

public class Venda {
    private Caixa caixa;
    private int idVenda;
    private double valorTotal;

    public Venda(Caixa caixa, int idVenda, double valorTotal) {
        this.caixa = caixa;
        this.idVenda = idVenda;
        this.valorTotal = valorTotal;
    }

    public Venda(Caixa caixa, int idVenda) {
        this.caixa = caixa;
        this.idVenda = idVenda;
    }
    

    public Colaborador getCaixa() {
        return caixa;
    }

    public void setCaixa(Caixa caixa) {
        this.caixa = caixa;
    }

    public int getIdVenda() {
        return idVenda;
    }

    public void setIdVenda(int idVenda) {
        this.idVenda = idVenda;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }
    
}

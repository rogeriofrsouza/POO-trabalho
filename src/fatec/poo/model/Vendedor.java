package fatec.poo.model;

import java.util.ArrayList;
import java.util.List;

public class Vendedor extends Pessoa {
    
    private double salarioBase;
    private double taxaComissao;
    
    private List<Pedido> pedidos = new ArrayList<>();

    public Vendedor(String cpf, String nome, double salarioBase) {
        super(cpf, nome);
        this.salarioBase = salarioBase;
    }

    public void setSalarioBase(double salarioBase) {
        this.salarioBase = salarioBase;
    }

    public void setTaxaComissao(double taxaComissao) {
        this.taxaComissao = taxaComissao / 100.0;
    }

    public double getSalarioBase() {
        return salarioBase;
    }

    public double getTaxaComissao() {
        return taxaComissao;
    }

    public List<Pedido> getPedidos() {
        return pedidos;
    }
    
    public void addPedido(Pedido pedido) {
        pedido.setVendedor(this);
        pedidos.add(pedido);
    }
}

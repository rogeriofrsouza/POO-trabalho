package fatec.poo.model;

import java.util.ArrayList;
import java.util.List;

public class Pedido {
    
    private String numero;
    private String dataEmissao;
    private String dataPagto;
    private boolean formaPagto;
    private boolean situacao;
    
    private Cliente cliente;
    private Vendedor vendedor;
    private List<ItemPedido> itens = new ArrayList<>();

    public Pedido(String numero, String dataEmissao) {
        this.numero = numero;
        this.dataEmissao = dataEmissao;
    }

    public void setDataPagto(String dataPagto) {
        this.dataPagto = dataPagto;
    }

    public void setFormaPagto(boolean formaPagto) {
        this.formaPagto = formaPagto;
    }

    public void setSituacao(boolean situacao) {
        this.situacao = situacao;
    }

    public String getNumero() {
        return numero;
    }

    public String getDataEmissao() {
        return dataEmissao;
    }

    public String getDataPagto() {
        return dataPagto;
    }

    public boolean getFormaPagto() {
        return formaPagto;
    }

    public boolean getSituacao() {
        return situacao;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Vendedor getVendedor() {
        return vendedor;
    }

    public void setVendedor(Vendedor vendedor) {
        this.vendedor = vendedor;
    }

    public List<ItemPedido> getItems() {
        return itens;
    }

    public void addItem(ItemPedido item) {
        item.setPedido(this);
        itens.add(item);
        
        // A cada ligação com um objeto ItemPedido o valor do item 
        // deve ser subtraído do limite disponível do objeto Cliente.
        cliente.setLimiteDisp(cliente.getLimiteDisp() - item.getQtdeVendida() * item.getProduto().getPreco());
    }
}

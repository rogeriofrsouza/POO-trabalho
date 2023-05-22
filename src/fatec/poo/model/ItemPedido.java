package fatec.poo.model;

public class ItemPedido {
    
    private int sequencia;
    private double qtdeVendida;
    
    private Pedido pedido;
    private Produto produto;

    public ItemPedido(int sequencia, double qtdeVendida, Produto produto) {
        this.sequencia = sequencia;
        this.produto = produto;
        
        // A quantidade vendida do item deve ser subtraída da quantidade disponível 
        // em estoque do objeto Produto que está ligado ao objeto ItemPedido.      
        setQtdeVendida(qtdeVendida);
    }

    public void setQtdeVendida(double qtdeVendida) {
        produto.setQtdeEstoque(produto.getQtdeEstoque() + this.qtdeVendida);
        this.qtdeVendida = qtdeVendida;
        produto.setQtdeEstoque(produto.getQtdeEstoque() - qtdeVendida);
    }

    public int getSequencia() {
        return sequencia;
    }

    public double getQtdeVendida() {
        return qtdeVendida;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }
}

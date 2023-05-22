package fatec.poo.model;

import java.util.ArrayList;
import java.util.List;

public class Cliente extends Pessoa {
    
    private double limiteCred;
    private double limiteDisp;
    
    private List<Pedido> pedidos = new ArrayList<>();

    public Cliente(String cpf, String nome, double limiteCred) {
        super(cpf, nome);
        this.limiteCred = limiteCred;
        this.limiteDisp = limiteCred;
    }

    public void setLimiteCred(double limiteCred) {
        this.limiteCred = limiteCred;
    }

    public void setLimiteDisp(double limiteDisp) {
        this.limiteDisp = limiteDisp;
    }

    public double getLimiteCred() {
        return limiteCred;
    }

    public double getLimiteDisp() {
        return limiteDisp;
    }
    
    public List<Pedido> getPedidos() {
        return pedidos;
    }
    
    public void addPedido(Pedido pedido) {
        pedido.setCliente(this);
        pedidos.add(pedido);
    }
}

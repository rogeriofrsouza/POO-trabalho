package fatec.poo.control;

import fatec.poo.model.Cliente;
import fatec.poo.model.ItemPedido;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.ResultSet;

import fatec.poo.model.Pedido;
import fatec.poo.model.Vendedor;
import java.util.List;

public class DaoPedido {

    private Connection conn;

    public DaoPedido(Connection conn) {
        this.conn = conn;
    }

    public void inserir(Pedido pedido) {
        PreparedStatement ps = null;

        try {
            ps = conn.prepareStatement("INSERT INTO TB_PEDIDO ("
                    + "NUMERO, SITUACAO, DATA_EMISSAO,DATA_PAGTO,CPF_CLIENTE,CPF_VENDEDOR) "
                    + "VALUES (?,?,?,?,?,?)");
            
            ps.setString(1, pedido.getNumero());
            ps.setInt(2, pedido.getSituacao() ? 1 : 0);
            ps.setString(3, pedido.getDataEmissao());
            ps.setString(4, pedido.getDataPagto());
            ps.setString(5, pedido.getCliente().getCpf());
            ps.setString(6, pedido.getVendedor().getCpf());
            
            ps.execute();
        } catch (SQLException ex) {
            System.out.println("Falha inserir Pedido: " + ex.toString());
        }
    }

    public void alterar(Pedido pedido) {
        PreparedStatement ps = null;
        
        try {
            ps = conn.prepareStatement("UPDATE TB_PEDIDO SET "
                    + "DATA_EMISSAO=?,DATA_PAGAMENTO=?,FORMA_PAGAMENTO=?,SITUACAO=?,"
                    + "CLIENTE=?,VENDEDOR=? "
                    + "WHERE NUMERO = ?");

            ps.setString(1, pedido.getDataEmissao());
            ps.setString(2, pedido.getDataPagto());
            ps.setInt(3, pedido.getFormaPagto() ? 1 : 0);
            ps.setInt(4, pedido.getSituacao() ? 1 : 0);
            ps.setString(5, pedido.getCliente().getCpf());
            ps.setString(6, pedido.getVendedor().getCpf());
            ps.setString(7, pedido.getNumero());

            ps.execute();
        } catch (SQLException ex) {
            System.out.println(ex.toString());
        }
    }

    public Pedido consultar(String numero) {
        Pedido pedido = null;
        DaoCliente daoCliente = new DaoCliente(conn);
        DaoVendedor daoVendedor = new DaoVendedor(conn);
        DaoItemPedido daoItemPedido = new DaoItemPedido(conn);
        
        PreparedStatement ps = null;

        try {
            ps = conn.prepareStatement("SELECT * FROM TB_PEDIDO WHERE NUMERO = ?");
            ps.setString(1, numero);
            
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                pedido = new Pedido(numero, rs.getString("DATA_EMISSAO"));
                Cliente cliente = daoCliente.consultar(rs.getString("CLIENTE"));
                Vendedor vendedor = daoVendedor.consultar(rs.getString("VENDEDOR"));
                
                List<ItemPedido> itens = daoItemPedido.consultarByPedido(numero);
                for (ItemPedido item : itens) {
                    pedido.addItem(item);
                }
                
                pedido.setCliente(cliente);
                pedido.setVendedor(vendedor);
                pedido.setFormaPagto(rs.getInt("FORMA") != 0);
                pedido.setSituacao(rs.getInt("SITUACAO") != 0);
                pedido.setDataPagto(rs.getString("DATA_PAGAMENTO"));
            }
        } catch (SQLException ex) {
            System.out.println(ex.toString());
        }
        
        return pedido;
    }

    public void excluir(Pedido pedido) {
        PreparedStatement ps = null;
        
        try {
            ps = conn.prepareStatement("DELETE FROM TB_PEDIDO WHERE NUMERO = ?");

            ps.setString(1, pedido.getNumero());
            ps.execute();
        } catch (SQLException ex) {
            System.out.println(ex.toString());
        }
    }
}

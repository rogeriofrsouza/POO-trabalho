package fatec.poo.control;

import fatec.poo.model.Cliente;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.ResultSet;

import fatec.poo.model.Pedido;
import fatec.poo.model.Vendedor;

public class DaoPedido {

    private Connection conn;
    public DaoPedido(Connection conn) {
         this.conn = conn;
    }
    
    public void inserir(Pedido pedido) {
        int formaPagto=0;
        int situacao=0;
        PreparedStatement ps = null;
        
        try {
            if(pedido.getFormaPagto())
                formaPagto=1;
            else
                formaPagto=0;     
            
            if(pedido.getSituacao())
                situacao=1;
            else
                situacao=0;
            
            ps = conn.prepareStatement("INSERT INTO TB_PEDIDO (" +
                    "DATA_EMISSAO,DATA_PAGAMENTO,FORMA_PAGAMENTO,SITUACAO,CLIENTE,VENDEDOR) "+
                    "VALUES (?,?,?,?,?,?,?)");
            ps.setString(1,pedido.getDataEmissao());
            ps.setString(2,pedido.getDataPagto());
            ps.setInt(3,formaPagto);
            ps.setInt(4,situacao);
            ps.setString(5,pedido.getCliente().getCpf());
            ps.setString(6,pedido.getVendedor().getCpf());
            ps.execute();
        } catch (SQLException ex) {
             System.out.println(ex.toString());   
        }
    }
    
    public void alterar(Pedido pedido) {
        int formaPagto=0;
        int situacao=0;
        PreparedStatement ps = null;
        try {
            if(pedido.getFormaPagto())
                formaPagto=1;
            else
                formaPagto=0;     
            
            if(pedido.getSituacao())
                situacao=1;
            else
                situacao=0;
            ps = conn.prepareStatement("UPDATE TB_PEDIDO SET " +
                    "DATA_EMISSAO=?,DATA_PAGAMENTO=?,FORMA_PAGAMENTO=?,SITUACAO=?,"+
                    "CLIENTE=?,VENDEDOR=? "+
                    "WHERE NUMERO = ?");
            
            ps.setString(1,pedido.getDataEmissao());
            ps.setString(2,pedido.getDataPagto());
            ps.setInt(3,formaPagto);
            ps.setInt(4,situacao);
            ps.setString(5,pedido.getCliente().getCpf());
            ps.setString(6,pedido.getVendedor().getCpf());
            ps.setString(7,pedido.getNumero());
           
            ps.execute();
        } catch (SQLException ex) {
             System.out.println(ex.toString());   
        }
    }
        
     public  Pedido consultar (String numero) {
        Pedido pedido = null;
        PreparedStatement ps = null;
        Cliente cliente;
        Vendedor vendedor;
        DaoCliente daoCliente = new DaoCliente(conn);
        DaoVendedor daoVendedor = new DaoVendedor(conn);
        
        try {
            ps = conn.prepareStatement("SELECT * FROM TB_PEDIDO WHERE " +
                                                 "NUMERO = ?");
            ps.setString(1, numero);
            ResultSet rs = ps.executeQuery();
           
            if (rs.next() == true) {
                pedido = new Pedido(numero, rs.getString("DATA_EMISSAO"));
                cliente = daoCliente.consultar(rs.getString("CLIENTE"));
                vendedor = daoVendedor.consultar(rs.getString("VENDEDOR"));
                pedido.setCliente(cliente);
                pedido.setVendedor(vendedor);
                if (rs.getInt("FORMA") == 0)
                    pedido.setFormaPagto(false);
                else
                    pedido.setFormaPagto(true);
                if (rs.getInt("SITUACAO")==0)
                    pedido.setSituacao(false);
                else
                    pedido.setSituacao(true);
                pedido.setDataPagto(rs.getString("DATA_PAGAMENTO"));
            }
        }
        catch (SQLException ex) { 
             System.out.println(ex.toString());   
        }
        return (pedido);
    }    
     
     public void excluir(Pedido pedido) {
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement("DELETE FROM TB_PEDIDO WHERE " +
                    "NUMERO = ?");
            
            ps.setString(1, pedido.getNumero());
            ps.execute();
        } catch (SQLException ex) {
             System.out.println(ex.toString());   
        }
    }
}

package fatec.poo.control;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.ResultSet;

import fatec.poo.model.ItemPedido;
import fatec.poo.model.Pedido;
import fatec.poo.model.Produto;
import java.util.ArrayList;
import java.util.List;

public class DaoItemPedido {

    private Connection conn;

    public DaoItemPedido(Connection conn) {
        this.conn = conn;
    }

    public void inserir(ItemPedido itemPedido) {
        PreparedStatement ps = null;

        try {
            ps = conn.prepareStatement("INSERT INTO TB_ITEM_PEDIDO ("
                    + "SEQUENCIA,QTDE_VENDIDA,NUM_PEDIDO,COD_PRODUTO) "
                    + "VALUES (seq_item_pedido.NEXTVAL,?,?,?)");
            
            ps.setDouble(1, itemPedido.getQtdeVendida());
            ps.setString(2, itemPedido.getPedido().getNumero());
            ps.setString(3, itemPedido.getProduto().getCodigo());
            
            ps.execute();
        } catch (SQLException ex) {
             System.out.println("Falha inserir Item Pedido: " + ex.toString());
        }
    }

    public void alterar(ItemPedido itemPedido) {
        PreparedStatement ps = null;
        
        try {
            ps = conn.prepareStatement("UPDATE TB_ITEM_PEDIDO SET "
                    + "QTDE_VENDIDA=?,NUM_PEDIDO=?,COD_PRODUTO=?) "
                    + "WHERE SEQUENCIA = ?");

            ps.setDouble(1, itemPedido.getQtdeVendida());
            ps.setString(2, itemPedido.getPedido().getNumero());
            ps.setString(3, itemPedido.getProduto().getCodigo());
            ps.setInt(4, itemPedido.getSequencia());

            ps.execute();
        } catch (SQLException ex) {
            System.out.println(ex.toString());
        }
    }
    
    public ItemPedido consultarBySequencia(int sequencia) {
        ItemPedido ip = null;
        DaoProduto daoProduto = new DaoProduto(conn);
        DaoPedido daoPedido = new DaoPedido(conn);

        PreparedStatement ps = null;
        
        try {
            ps = conn.prepareStatement("SELECT * FROM TB_ITEM_PEDIDO WHERE SEQUENCIA = ?");
            
            ps.setInt(1, sequencia);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Produto produto = daoProduto.consultar(rs.getString("PRODUTO"));
                Pedido pedido = daoPedido.consultar(rs.getString("PEDIDO"));
                
                ip = new ItemPedido(sequencia, rs.getDouble("QTDE_VENDIDA"), produto);
                ip.setPedido(pedido);
            }
        } catch (SQLException ex) {
            System.out.println(ex.toString());
        }
        
        return ip;
    }
    
    public List<ItemPedido> consultarByPedido(String numeroPedido) {
        List<ItemPedido> itens = new ArrayList<>();
        DaoProduto daoProduto = new DaoProduto(conn);

        PreparedStatement ps = null;
        
        try {
            ps = conn.prepareStatement("SELECT * FROM TB_ITEM_PEDIDO WHERE NUM_PEDIDO = ?");
            
            ps.setString(1, numeroPedido);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Produto produto = daoProduto.consultar(rs.getString("PRODUTO"));

                itens.add(new ItemPedido(rs.getInt("SEQUENCIA"), rs.getDouble("QTDE_VENDIDA"), produto));
            }
        } catch (SQLException ex) {
            System.out.println(ex.toString());
        }
        
        return itens;
    }
    
    public ItemPedido consultar(String numeroPedido, String codigo) {
        DaoProduto daoProduto = new DaoProduto(conn);
        DaoPedido daoPedido = new DaoPedido(conn);
        ItemPedido itemPedido = null;

        PreparedStatement ps = null;
        
        try {
            ps = conn.prepareStatement("SELECT * FROM TB_ITEM_PEDIDO WHERE NUM_PEDIDO = ? AND COD_PRODUTO = ?");
            
            ps.setString(1, numeroPedido);
            ps.setString(2, codigo);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Produto prod = daoProduto.consultar(rs.getString(3));
                Pedido ped = daoPedido.consultar(rs.getString(2));
                
                itemPedido = new ItemPedido(rs.getInt(0), rs.getDouble(1), prod);
                itemPedido.setPedido(ped);
            }
        } catch (SQLException ex) {
            System.out.println("Falha consultar Item Pedido: " + ex.toString());
        }
        
        return itemPedido;
    }

    public void excluir(Produto produto) {
        PreparedStatement ps = null;
        
        try {
            ps = conn.prepareStatement("DELETE FROM TB_PRODUTO WHERE CODIGO = ?");

            ps.setString(1, produto.getCodigo());
            ps.execute();
        } catch (SQLException ex) {
            System.out.println(ex.toString());
        }
    }
}

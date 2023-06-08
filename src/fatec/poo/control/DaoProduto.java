package fatec.poo.control;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.ResultSet;

import fatec.poo.model.Produto;

public class DaoProduto {

    private Connection conn;

    public DaoProduto(Connection conn) {
        this.conn = conn;
    }

    public void inserir(Produto produto) {
        PreparedStatement ps = null;

        try {
            ps = conn.prepareStatement("INSERT INTO TB_PRODUTO ("
                    + "CODIGO,DESCRICAO,QTDE_ESTOQUE,UNIDADE_MEDIDA,PRECO,ESTOQUE_MINIMO) "
                    + "VALUES (?,?,?,?,?,?)");
            
            ps.setString(1, produto.getCodigo());
            ps.setString(2, produto.getDescricao());
            ps.setDouble(3, produto.getQtdeEstoque());
            ps.setString(4, produto.getUnidadeMedida());
            ps.setDouble(5, produto.getPreco());
            ps.setDouble(6, produto.getEstoqueMinimo());
            
            ps.execute();
        } catch (SQLException ex) {
            System.out.println(ex.toString());
        }
    }

    public void alterar(Produto produto) {
        PreparedStatement ps = null;
        
        try {
            ps = conn.prepareStatement("UPDATE TB_PRODUTO SET "
                    + "DESCRICAO=?,QTDE_ESTOQUE=?,UNIDADE_MEDIDA=?,PRECO=?,ESTOQUE_MINIMO=? "
                    + "WHERE CODIGO = ?");

            ps.setString(1, produto.getDescricao());
            ps.setDouble(2, produto.getQtdeEstoque());
            ps.setString(3, produto.getUnidadeMedida());
            ps.setDouble(4, produto.getPreco());
            ps.setDouble(5, produto.getEstoqueMinimo());
            ps.setString(6, produto.getCodigo());

            ps.execute();
        } catch (SQLException ex) {
            System.out.println(ex.toString());
        }
    }

    public Produto consultar(String codigo) {
        Produto p = null;
        PreparedStatement ps = null;
        
        try {
            ps = conn.prepareStatement("SELECT * FROM TB_PRODUTO WHERE CODIGO = ?");

            ps.setString(1, codigo);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                p = new Produto(codigo, rs.getString("DESCRICAO"));
                p.setQtdeEstoque(rs.getDouble("QTDE_ESTOQUE"));
                p.setUnidadeMedida(rs.getString("UNIDADE_MEDIDA"));
                p.setPreco(rs.getDouble("PRECO"));
                p.setEstoqueMinimo(rs.getDouble("ESTOQUE_MINIMO"));
            }
        } catch (SQLException ex) {
            System.out.println(ex.toString());
        }
        
        return p;
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

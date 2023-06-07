package fatec.poo.control;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.ResultSet;

import fatec.poo.model.Vendedor;

public class DaoVendedor {

    private Connection conn;
    
    public DaoVendedor(Connection conn) {
         this.conn = conn;
    }
    
    public void inserir(Vendedor vendedor) {
        
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement("INSERT INTO TB_VENDEDOR (" +
                    "CPF,NOME,ENDERECO,CIDADE,UF,CEP,DDD,TELEFONE," +
                    "SALARIO_BASE,TAXA_COMISSAO) "+
                    "VALUES (?,?,?,?,?,?,?,?,?,?)");
            ps.setString(1,vendedor.getCpf());
            ps.setString(2,vendedor.getNome());
            ps.setString(3,vendedor.getEndereco());
            ps.setString(4,vendedor.getCidade());
            ps.setString(5,vendedor.getUf());
            ps.setString(6,vendedor.getCep());
            ps.setString(7,vendedor.getDdd());
            ps.setString(8,vendedor.getTelefone());
            ps.setDouble(9,vendedor.getSalarioBase());
            ps.setDouble(10,vendedor.getTaxaComissao());
            ps.execute();
        } catch (SQLException ex) {
             System.out.println(ex.toString());   
        }
    }
    
    public void alterar(Vendedor vendedor) {
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement("UPDATE TB_VENDEDOR SET " +
                   "NOME=?,ENDERECO=?,CIDADE=?,UF=?,CEP=?,DDD=?,TELEFONE=?," +
                   "SALARIO_BASE=?,TAXA_COMISSAO=? "+
                   "WHERE CPF = ?");
           
            ps.setString(1, vendedor.getNome());
            ps.setString(2,vendedor.getEndereco());
            ps.setString(3,vendedor.getCidade());
            ps.setString(4,vendedor.getUf());
            ps.setString(5,vendedor.getCep());
            ps.setString(6,vendedor.getDdd());
            ps.setString(7,vendedor.getTelefone());
            ps.setDouble(8,vendedor.getSalarioBase());
            ps.setDouble(9,vendedor.getTaxaComissao());
            ps.setString(10, vendedor.getCpf());
            ps.execute();
        } catch (SQLException ex) {
             System.out.println(ex.toString());   
        }
    }
        
     public  Vendedor consultar (String cpf) {
        Vendedor v = null;
       
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement("SELECT * FROM TB_VENDEDOR WHERE " +
                                                 "CPF = ?");
            
            ps.setString(1, cpf);
            ResultSet rs = ps.executeQuery();
           
            if (rs.next() == true) {
                v = new Vendedor(cpf, rs.getString("NOME"),rs.getDouble("SALARIO_BASE"));
                v.setEndereco(rs.getString("ENDERECO"));
                v.setCidade(rs.getString("CIDADE"));
                v.setCep(rs.getString("CEP"));
                v.setDdd(rs.getString("DDD"));
                v.setUf(rs.getString("UF"));
                v.setTelefone(rs.getString("TELEFONE"));
                v.setTaxaComissao(rs.getDouble("TAXA_COMISSAO"));
            }
        }
        catch (SQLException ex) { 
             System.out.println(ex.toString());   
        }
        return (v);
    }    
     
     public void excluir(Vendedor vendedor) {
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement("DELETE FROM TB_VENDEDOR WHERE " +
                    "CPF = ?");
            
            ps.setString(1, vendedor.getCpf());
                      
            ps.execute();
        } catch (SQLException ex) {
             System.out.println(ex.toString());   
        }
    }
}

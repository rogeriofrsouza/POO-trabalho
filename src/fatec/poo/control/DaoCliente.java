package fatec.poo.control;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.ResultSet;

import fatec.poo.model.Cliente;

public class DaoCliente {

    private Connection conn;

    public DaoCliente(Connection conn) {
        this.conn = conn;
    }

    public void inserir(Cliente cliente) {
        PreparedStatement ps = null;

        try {
            ps = conn.prepareStatement("INSERT INTO TB_CLIENTE ("
                    + "CPF,NOME,ENDERECO,CIDADE,UF,CEP,DDD,TELEFONE,"
                    + "LIMITE_CRED,LIMITE_DISP) "
                    + "VALUES (?,?,?,?,?,?,?,?,?,?)");

            ps.setString(1, cliente.getCpf());
            ps.setString(2, cliente.getNome());
            ps.setString(3, cliente.getEndereco());
            ps.setString(4, cliente.getCidade());
            ps.setString(5, cliente.getUf());
            ps.setString(6, cliente.getCep());
            ps.setString(7, cliente.getDdd());
            ps.setString(8, cliente.getTelefone());
            ps.setDouble(9, cliente.getLimiteCred());
            ps.setDouble(10, cliente.getLimiteDisp());

            ps.execute();
        } catch (SQLException ex) {
            System.out.println(ex.toString());
        }
    }

    public void alterar(Cliente cliente) {
        PreparedStatement ps = null;

        try {
            ps = conn.prepareStatement("UPDATE TB_CLIENTE SET "
                    + "NOME=?,ENDERECO=?,CIDADE=?,UF=?,CEP=?,DDD=?,TELEFONE=?,"
                    + "LIMITE_CRED=?,LIMITE_DISP=? "
                    + "WHERE CPF = ?");

            ps.setString(1, cliente.getNome());
            ps.setString(2, cliente.getEndereco());
            ps.setString(3, cliente.getCidade());
            ps.setString(4, cliente.getUf());
            ps.setString(5, cliente.getCep());
            ps.setString(6, cliente.getDdd());
            ps.setString(7, cliente.getTelefone());
            ps.setDouble(8, cliente.getLimiteCred());
            ps.setDouble(9, cliente.getLimiteDisp());
            ps.setString(10, cliente.getCpf());

            ps.execute();
        } catch (SQLException ex) {
//            System.out.println("UPDATE TB_CLIENTE SET "
//                    + "NOME=?,ENDERECO=?,CIDADE=?,UF=?,CEP=?,DDD=?,TELEFONE=?,"
//                    + "LIMITE_CRED=?,LIMITE_DISP=?) "
//                    + "WHERE CPF = ?");
//            System.out.println("UPDATE TB_CLIENTE SET "
//                    + "NOME='" + cliente.getNome() + "',ENDERECO='" + cliente.getEndereco() + "',CIDADE='" + cliente.getCidade()
//                    + "',UF='" + cliente.getUf() + "',CEP='" + cliente.getCep() + "',DDD='" + cliente.getDdd() + "',TELEFONE='" + cliente.getTelefone() + "',"
//                    + "LIMITE_CRED=" + cliente.getLimiteCred() + ",LIMITE_DISP=" + cliente.getLimiteDisp() + " "
//                    + "WHERE CPF = '" + cliente.getCpf());
            System.out.println(ex.toString());
        }
    }

    public Cliente consultar(String cpf) {
        Cliente c = null;
        PreparedStatement ps = null;

        try {
            ps = conn.prepareStatement("SELECT * FROM TB_CLIENTE WHERE CPF = ?");

            ps.setString(1, cpf);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                c = new Cliente(cpf, rs.getString("NOME"), rs.getDouble("LIMITE_CRED"));
                c.setEndereco(rs.getString("ENDERECO"));
                c.setCidade(rs.getString("CIDADE"));
                c.setCep(rs.getString("CEP"));
                c.setDdd(rs.getString("DDD"));
                c.setUf(rs.getString("UF"));
                c.setTelefone(rs.getString("TELEFONE"));
                c.setLimiteDisp(rs.getDouble("LIMITE_DISP"));
            }
        } catch (SQLException ex) {
            System.out.println(ex.toString());
        }

        return c;
    }

    public void excluir(Cliente cliente) {
        PreparedStatement ps = null;

        try {
            ps = conn.prepareStatement("DELETE FROM TB_CLIENTE WHERE CPF = ?");

            ps.setString(1, cliente.getCpf());
            ps.execute();
        } catch (SQLException ex) {
            System.out.println(ex.toString());
        }
    }
}

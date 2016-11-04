package DAO;

import beans.Contato;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by handyc on 02/11/16.
 */
public class ContatoDAO {

    public void adicionar(Contato contato){
        ConnectionFactory con = new ConnectionFactory();
        String sql = "insert into AGENDA(nome, dtnascimento, endereco, email ) values (?,?,?,?)";
        try{
            PreparedStatement pst = con.getCon().prepareStatement
                    (sql);
            pst.setString(1, contato.getNome());
            pst.setString(2, String.valueOf(contato.getDtNascimento()));
            pst.setString(3, contato.getEndereco());
            pst.setString(4, contato.getEmail());
            pst.executeQuery();
            pst.close();
        }catch (SQLException e){
            throw new RuntimeException(e);
        }finally {
            try {
                con.getCon().close();
            }catch (SQLException e){
                throw new RuntimeException(e);
            }
        }
    }
}

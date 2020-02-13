package com.rodnei_miranda.dao;

import com.rodnei_miranda.bean.Valet;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Rodnei Miranda da Silva – Polo Mauá - 297297
 */
public class ValetDAO {

    public Connection Conectar() {

        Connection conn = null;
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "system", "idlrtt2");
            System.out.println("Conectou !!");
        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
        }
        return conn;
    }

    public void Desconectar(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
                System.out.println("Desconectou !!");
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }

    }

    public Valet salvar(Valet v) {

        Connection conn = this.Conectar();

        try {

            long id = 0L;
            String consultarID = "select valet_seq.nextval as ID from dual";
            PreparedStatement consulta = conn.prepareStatement(consultarID);
            ResultSet rs = consulta.executeQuery();
            if (rs.next()) {
                id = rs.getLong("id");
            }

            String inserir = "insert into valet (id,modelo,placa,entrada) "
                    + "values (?,?,?,?)";
            PreparedStatement stmt = conn.prepareStatement(inserir);
            stmt.setLong(1, id);
            stmt.setString(2, v.getModelo());
            stmt.setString(3, v.getPlaca());
            stmt.setDate(4, new Date(v.getEntrada().getTime()));
            stmt.execute();
            v.setId(id);

        } catch (SQLException ex) {
            ex.printStackTrace();

        } finally {
            Desconectar(conn);
        }
        return v;
    }

    public List<Valet> consultarTodos() {
        List<Valet> valets = new ArrayList<>();
        Connection conn = Conectar();
        try {
            String consulta = "select * from valet where saida is null order by entrada";
            PreparedStatement stmt = conn.prepareStatement(consulta);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Date saida = rs.getDate("saida");
                valets.add(new Valet(
                        rs.getLong("id"),
                        rs.getString("modelo"),
                        rs.getString("placa"),
                        new java.util.Date(rs.getDate("entrada").getTime()),
                        saida != null ? new java.util.Date(saida.getTime()) : null,
                        rs.getDouble("preco")));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            Desconectar(conn);
        }
        return valets;
    }

    public void atualizar(Valet v) {
        Connection conn = Conectar();
        try {
            String atualizar = "update valet set saida = ?, preco = ?" + "where id= ?";
            PreparedStatement stmt = conn.prepareCall(atualizar);
            stmt.setDate(1, new Date(v.getSaida().getTime()));
            stmt.setDouble(2, v.getPreco());
            stmt.setLong(3, v.getId());
            stmt.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            Desconectar(conn);
        }
    }
    //public static void main(String[] args) {
      //  ValetDAO dao = new ValetDAO();
        //dao.Desconectar(dao.Conectar());
    //}
}

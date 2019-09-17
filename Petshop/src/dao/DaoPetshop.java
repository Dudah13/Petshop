/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import modelo.Petshop;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author Administrador
 */
public class DaoPetshop {
    
    public static boolean inserir(Petshop objeto) {
        String sql = "INSERT INTO Petshop (endereco, nome, numero, avaliacao) VALUES (?, ?, ?, ?)";
        try {
            PreparedStatement ps = conexao.Conexao.getConexao().prepareStatement(sql);
            ps.setString(1, objeto.getEndereco());
            ps.setString(2, objeto.getNome());
            ps.setInt(3, objeto.getNumero());
            ps.setInt(4, objeto.getAvaliacao());
            ps.executeUpdate();
            return true;
        } catch (SQLException | ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }
    
     public static void main(String[] args) {
        Petshop objeto = new Petshop();
        objeto.setEndereco("BR");
        objeto.setNome("Kacau");
        objeto.setNumero(1);
        objeto.setAvaliacao(5);
        boolean resultado = inserir(objeto);
        if (resultado) {
            JOptionPane.showMessageDialog(null, "Inserido com sucesso!");
        } else {
            JOptionPane.showMessageDialog(null, "Erro!");
        }
    }
     
     public static List<Petshop> consultar() {
        List<Petshop> resultados = new ArrayList<>();
        //editar o SQL conforme a entidade
        String sql = "SELECT codigo, nome, endereco, avaliacao, numero FROM Petshop";
        PreparedStatement ps;
        try {
            ps = conexao.Conexao.getConexao().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Petshop objeto = new Petshop();
                //definir um set para cada atributo da entidade, cuidado com o tipo
                objeto.setCodigo(rs.getInt("codigo"));
                objeto.setNome(rs.getString("nome"));
                objeto.setEndereco(rs.getString("endereco"));
                objeto.setNumero(rs.getInt("numero"));
                objeto.setAvaliacao(rs.getInt("avaliacao"));
                
                resultados.add(objeto);//não mexa nesse, ele adiciona o objeto na lista
            }
            return resultados;
        } catch (SQLException | ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
            return null;
        }
}
}

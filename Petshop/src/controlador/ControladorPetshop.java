/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import dao.DaoPetshop;
import javax.swing.JOptionPane;
import modelo.Petshop;
import tela.manutencao.ManutencaoPetshop;
import java.util.List;

import java.util.Vector;
import java.util.function.Function;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


/**
 *
 * @author Administrador
 */
public class ControladorPetshop {

   public static void inserir(ManutencaoPetshop man){
        Petshop objeto = new Petshop();
        objeto.setEndereco(man.jtfEndereco.getText());
        objeto.setNome(man.jtfNome.getText());
        objeto.setNumero(Integer.parseInt(man.jtfNumero.getText()));
        objeto.setAvaliacao(Integer.parseInt(man.jtfAvaliacao.getText()));
        
        
        boolean resultado = DaoPetshop.inserir(objeto);
        if (resultado) {
            JOptionPane.showMessageDialog(null, "Inserido com sucesso!");
        } else {
            JOptionPane.showMessageDialog(null, "Erro!");
        }
}
   
   public static void atualizarTabela(JTable tabela) {
        DefaultTableModel modelo = new DefaultTableModel();
        //definindo o cabeçalho da tabela
        modelo.addColumn("Código");
        modelo.addColumn("Nome");
        modelo.addColumn("Endreço");
        modelo.addColumn("Número");
        modelo.addColumn("Avaliação");
        List<Petshop> resultados = DaoPetshop.consultar();
        resultados.stream().map(new Function<Petshop, Vector>() {
            @Override
            public Vector apply(Petshop objeto) {
                Vector linha = new Vector();
                //definindo o conteúdo da tabela
                linha.add(objeto.getCodigo());
                linha.add(objeto.getNome());
                 linha.add(objeto.getEndereco());
                  linha.add(objeto.getAvaliacao());
                   linha.add(objeto.getNumero());
                return linha;
            }
        }).forEach((linha) -> {
           modelo.addRow(linha); //adicionando a linha na tabela
       });
        tabela.setModel(modelo);
    }
}

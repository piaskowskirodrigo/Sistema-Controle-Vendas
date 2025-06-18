/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.projeto.dao;

import br.com.projeto.jdbc.ConnectionFactory;
import br.com.projeto.model.Produtos;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Rodrigo
 */
public class ProdutosDAO {

    private Connection con;

    public ProdutosDAO() {
        //cia um construtor para conectar o banco
        this.con = new ConnectionFactory().getConnection();
    }

    //cria metodo cadastrar produtos
    public void cadastrar(Produtos obj) {
        try {
            String sql = "insert into tb_produtos (descricao,preco,qtd_estoque,for_id)values(?,?,?,?)";

            //2passo- conectar banco e organizar
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, obj.getDescricao());
            stmt.setDouble(2, obj.getPreco());
            stmt.setInt(3, obj.getQtd_estoque());
            stmt.setInt(4, obj.getFornecedor().getId());

            stmt.execute();
            stmt.close();

            JOptionPane.showMessageDialog(null, "Produto cadastrado com sucesso");

        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, "Erro: " + erro);
        }

    }

}

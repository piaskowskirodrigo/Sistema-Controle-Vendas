/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.projeto.dao;

import br.com.projeto.jdbc.ConnectionFactory;
import br.com.projeto.model.Fornecedores;
import br.com.projeto.model.Produtos;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
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
     //cria metodo alterar produtos
    public void alterar(Produtos obj) {
        try {
            String sql = "update tb_produtos set descricao=?,preco=?,qtd_estoque=?,for_id=? where id=?";

            //2passo- conectar banco e organizar
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, obj.getDescricao());
            stmt.setDouble(2, obj.getPreco());
            stmt.setInt(3, obj.getQtd_estoque());
            stmt.setInt(4, obj.getFornecedor().getId());
            stmt.setInt(5,obj.getId());

            stmt.execute();
            stmt.close();

            JOptionPane.showMessageDialog(null, "Produto alterado com sucesso");

        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, "Erro: " + erro);
        }

    }
    
     //cria metodo excluir produtos
    public void excluir(Produtos obj) {
        try {
            String sql = "delete from tb_produtos where id=?";

            //2passo- conectar banco e organizar
            PreparedStatement stmt = con.prepareStatement(sql);
      
            stmt.setInt(1,obj.getId());

            stmt.execute();
            stmt.close();

            JOptionPane.showMessageDialog(null, "Produto excluido com sucesso");

        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, "Erro: " + erro);
        }

    }
    
    
    //MetodoListarProdutos
    public List<Produtos> listarProdutos(){
        try {
            //1passo - criar lista
            List<Produtos> lista = new ArrayList<>();
            
            //2passo- criar metodo sql
            String sql = "select p.id,p.descricao,p.preco,p.qtd_estoque,f.nome from tb_produtos as p " //tb_produto as p nomea a tabela produtos como p...
                    + "inner join tb_fornecedores as f on (p.for_id = f.id)";

            PreparedStatement stmt = con.prepareStatement(sql);
            
            ResultSet rs = stmt.executeQuery();//toda vez que usar select usa esse comando
            
            while(rs.next()){
                Produtos obj = new Produtos();
                Fornecedores f = new Fornecedores();
                
                obj.setId(rs.getInt("p.id"));
                obj.setDescricao(rs.getString("p.descricao"));
                obj.setPreco(rs.getDouble("p.preco"));
                obj.setQtd_estoque(rs.getInt("p.qtd_estoque"));
                
                f.setNome(rs.getString("f.nome"));
                obj.setFornecedor(f);
                lista.add(obj);

            }
            return lista;
            
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro"+erro);
            return null;
        }
           
    }
    
    //MetodolistarProdutosPorNome
    public List<Produtos> listarProdutosPorNome(String nome){
        try {
            //1passo - criar lista
            List<Produtos> lista = new ArrayList<>();
            
            //2passo- criar metodo sql
            String sql = "select p.id,p.descricao,p.preco,p.qtd_estoque,f.nome from tb_produtos as p " //tb_produto as p nomea a tabela produtos como p...
                    + "inner join tb_fornecedores as f on (p.for_id = f.id)where p.descricao like?";

            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, nome);
            
            ResultSet rs = stmt.executeQuery();//toda vez que usar select usa esse comando
            
            while(rs.next()){
                Produtos obj = new Produtos();
                Fornecedores f = new Fornecedores();
                
                obj.setId(rs.getInt("p.id"));
                obj.setDescricao(rs.getString("p.descricao"));
                obj.setPreco(rs.getDouble("p.preco"));
                obj.setQtd_estoque(rs.getInt("p.qtd_estoque"));
                
                f.setNome(rs.getString("f.nome"));
                obj.setFornecedor(f);
                lista.add(obj);

            }
            return lista;
            
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro"+erro);
            return null;
        }
    }
        
         //metodo consultaCliente por Nome
    public Produtos consultaPorNome(String nome) {
        try {
            //1passo- criar metodo sql
            String sql = "select p.id,p.descricao,p.preco,p.qtd_estoque,f.nome from tb_produtos as p " 
                    + "inner join tb_fornecedores as f on (p.for_id = f.id)where p.descricao = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, nome);

            ResultSet rs = stmt.executeQuery();
            Produtos obj = new Produtos();
            Fornecedores f = new Fornecedores();
            if (rs.next()) {

                obj.setId(rs.getInt("p.id"));
                obj.setDescricao(rs.getString("p.descricao"));
                obj.setPreco(rs.getDouble("p.preco"));
                obj.setQtd_estoque(rs.getInt("p.qtd_estoque"));
                
                f.setNome(rs.getString("f.nome"));
                obj.setFornecedor(f);

            }
            return obj;
         
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Produto nao encontrado");
            return null;
        }
    }
    
}


 
    


       

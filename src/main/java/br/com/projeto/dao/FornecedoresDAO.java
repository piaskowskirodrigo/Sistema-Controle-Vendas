/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.projeto.dao;

import br.com.projeto.jdbc.ConnectionFactory;


import br.com.projeto.model.Fornecedores;
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
public class FornecedoresDAO {
    
     private Connection con;
    
    public FornecedoresDAO(){
        //cia um construtor para conectar o banco
        this.con = new ConnectionFactory().getConnection();
    }
     //metodo cadastrar Fornecedores
    public void cadastrarFornecedores(Fornecedores obj){
        try {
            //1 passo - comando sql
            String sql = "insert into tb_fornecedores(nome,cnpj,email,telefone,celular,cep,endereco,numero,complemento,bairro,cidade,estado)"
                                               + "values(?,?,?,?,?,?,?,?,?,?,?,?)";
            //2passo- conectar banco e organizar
            PreparedStatement stmt = con.prepareStatement(sql);
            //organiza a ordem
            stmt.setString(1, obj.getNome());
            stmt.setString(2, obj.getCnpj());
            stmt.setString(3, obj.getEmail());
            stmt.setString(4, obj.getTelefone());
            stmt.setString(5, obj.getCelular());
            stmt.setString(6, obj.getCep());
            stmt.setString(7, obj.getEndereco());
            stmt.setInt(8, obj.getNumero());
            stmt.setString(9, obj.getComplemento());
            stmt.setString(10, obj.getBairro());
            stmt.setString(11, obj.getCidade());
            stmt.setString(12, obj.getUf());
            
            
            
            //3 passo- executa comando
            stmt.execute();
            stmt.close();
            
            JOptionPane.showMessageDialog(null, "Cadastro relizado com sucesso");
            
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro"+erro);
        }
        
    }
    //metodo excluir fornecedor
    public void excluirFornecedores(Fornecedores obj){
        try {
            //1 passo - comando sql
            String sql = "delete from tb_fornecedores where id = ?";
            //2passo- conectar banco e organizar
            PreparedStatement stmt = con.prepareStatement(sql);
            //organiza a ordem
            stmt.setInt(1, obj.getId());
            
            //3 passo- executa comando
            stmt.execute();
            stmt.close();
            
            JOptionPane.showMessageDialog(null, "Excluido com sucesso");
            
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro"+erro);
        }
    }
    
    //metodo alterar fornecedores
    public void alterarFornecedores(Fornecedores obj){
        try {
            //1 passo - comando sql
            String sql = "update tb_fornecedores set nome=?,cnpj=?,email=?,telefone=?,celular=?,cep=?,"
                    + "endereco=?,numero=?,complemento=?,bairro=?,cidade=?,estado=? where id =?";
            //2passo- conectar banco e organizar
            PreparedStatement stmt = con.prepareStatement(sql);
            //organiza a ordem
            stmt.setString(1, obj.getNome());
            stmt.setString(2, obj.getCnpj());
            stmt.setString(3, obj.getEmail());
            stmt.setString(4, obj.getTelefone());
            stmt.setString(5, obj.getCelular());
            stmt.setString(6, obj.getCep());
            stmt.setString(7, obj.getEndereco());
            stmt.setInt(8, obj.getNumero());
            stmt.setString(9, obj.getComplemento());
            stmt.setString(10, obj.getBairro());
            stmt.setString(11, obj.getCidade());
            stmt.setString(12, obj.getUf());
            
            stmt.setInt(13, obj.getId());
            
            
            //3 passo- executa comando
            stmt.execute();
            stmt.close();
            
            JOptionPane.showMessageDialog(null, "Alterado com sucesso");
            
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro"+erro);
        }
    }
        //metodo listarFornecedores
         public List<Fornecedores> listarFornecedores(){
        try {
            //1passo - criar lista
            List<Fornecedores> lista = new ArrayList<>();
            
            //2passo- criar metodo sql
            String sql = "select * from tb_fornecedores";
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();//toda vez que usar select usa esse comando
            while(rs.next()){
                Fornecedores obj = new Fornecedores();
                
                obj.setId(rs.getInt("id"));
                obj.setNome(rs.getString("nome"));
                obj.setCnpj(rs.getString("cnpj"));
                obj.setEmail(rs.getString("email"));
                obj.setTelefone(rs.getString("telefone"));
                obj.setCelular(rs.getString("celular"));
                obj.setCep(rs.getString("cep"));
                obj.setEndereco(rs.getString("endereco"));
                obj.setNumero(rs.getInt("numero"));
                obj.setComplemento(rs.getString("complemento"));
                obj.setBairro(rs.getString("bairro"));
                obj.setCidade(rs.getString("cidade"));
                obj.setUf(rs.getString("estado"));
                
                //add na list
                lista.add(obj);
            }
            return lista;
            
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro"+erro);
            return null;
        }
        
     }
        
        //metodo listarFornecedores por nome
         public List<Fornecedores> listarFornecedoresPorNome(String nome){
        try {
            //1passo - criar lista
            List<Fornecedores> lista = new ArrayList<>();
            
            //2passo- criar metodo sql
            String sql = "select * from tb_fornecedores where nome like ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, nome);
            ResultSet rs = stmt.executeQuery();//toda vez que usar select usa esse comando
            
            while(rs.next()){
                Fornecedores obj = new Fornecedores();
                
                obj.setId(rs.getInt("id"));
                obj.setNome(rs.getString("nome"));
                obj.setCnpj(rs.getString("cnpj"));
                obj.setEmail(rs.getString("email"));
                obj.setTelefone(rs.getString("telefone"));
                obj.setCelular(rs.getString("celular"));
                obj.setCep(rs.getString("cep"));
                obj.setEndereco(rs.getString("endereco"));
                obj.setNumero(rs.getInt("numero"));
                obj.setComplemento(rs.getString("complemento"));
                obj.setBairro(rs.getString("bairro"));
                obj.setCidade(rs.getString("cidade"));
                obj.setUf(rs.getString("estado"));
                
                //add na list
                lista.add(obj);
            }
            return lista;
            
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro"+erro);
            return null;
        }
        
    }
         
         //metodo consultaFornecedoresPorNome
        public Fornecedores consultaPorNome(String nome) {
        try {
            //1passo- criar metodo sql
            String sql = "select * from tb_fornecedores where nome =?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, nome);

            ResultSet rs = stmt.executeQuery();
            Fornecedores obj = new Fornecedores();
            if (rs.next()) {

                obj.setId(rs.getInt("id"));
                obj.setNome(rs.getString("nome"));
                obj.setCnpj(rs.getString("cnpj"));
                obj.setEmail(rs.getString("email"));
                obj.setTelefone(rs.getString("telefone"));
                obj.setCelular(rs.getString("celular"));
                obj.setCep(rs.getString("cep"));
                obj.setEndereco(rs.getString("endereco"));
                obj.setNumero(rs.getInt("numero"));
                obj.setComplemento(rs.getString("complemento"));
                obj.setBairro(rs.getString("bairro"));
                obj.setCidade(rs.getString("cidade"));
                obj.setUf(rs.getString("estado"));

            }
            return obj;
         
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Fornecedor nao encontrado");
            return null;
        }
    }
    
}

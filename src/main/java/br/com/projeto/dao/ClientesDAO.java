/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.projeto.dao;

import br.com.projeto.jdbc.ConnectionFactory;
import br.com.projeto.model.Clientes;
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
public class ClientesDAO {
    
    private Connection con;
    
    public ClientesDAO(){
        //cia um construtor para conectar o banco
        this.con = new ConnectionFactory().getConnection();
    }
    
    //metodo cadastrar cliente
    public void cadastrarCliente(Clientes obj){
        try {
            //1 passo - comando sql
            String sql = "insert into tb_clientes(nome,rg,cpf,email,telefone,celular,cep,endereco,numero,complemento,bairro,cidade,estado)"
                                               + "values(?,?,?,?,?,?,?,?,?,?,?,?,?)";
            //2passo- conectar banco e organizar
            PreparedStatement stmt = con.prepareStatement(sql);
            //organiza a ordem
            stmt.setString(1, obj.getNome());
            stmt.setString(2, obj.getRg());
            stmt.setString(3, obj.getCpf());
            stmt.setString(4, obj.getEmail());
            stmt.setString(5, obj.getTelefone());
            stmt.setString(6, obj.getCelular());
            stmt.setString(7, obj.getCep());
            stmt.setString(8, obj.getEndereco());
            stmt.setInt(9, obj.getNumero());
            stmt.setString(10, obj.getComplemento());
            stmt.setString(11, obj.getBairro());
            stmt.setString(12, obj.getCidade());
            stmt.setString(13, obj.getUf());
            
            //3 passo- executa comando
            stmt.execute();
            stmt.close();
            
            JOptionPane.showMessageDialog(null, "Cadastro relizado com sucesso");
            
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro"+erro);
        }
        
    }
    //metodo alterar cliente
    public void alterarCliente(Clientes obj){
        try {
            //1 passo - comando sql
            String sql = "update tb_clientes set nome=?,rg=?,cpf=?,email=?,telefone=?,celular=?,cep=?,"
                    + "endereco=?,numero=?,complemento=?,bairro=?,cidade=?,estado=? where id =?";
            //2passo- conectar banco e organizar
            PreparedStatement stmt = con.prepareStatement(sql);
            //organiza a ordem
            stmt.setString(1, obj.getNome());
            stmt.setString(2, obj.getRg());
            stmt.setString(3, obj.getCpf());
            stmt.setString(4, obj.getEmail());
            stmt.setString(5, obj.getTelefone());
            stmt.setString(6, obj.getCelular());
            stmt.setString(7, obj.getCep());
            stmt.setString(8, obj.getEndereco());
            stmt.setInt(9, obj.getNumero());
            stmt.setString(10, obj.getComplemento());
            stmt.setString(11, obj.getBairro());
            stmt.setString(12, obj.getCidade());
            stmt.setString(13, obj.getUf());
            stmt.setInt(14, obj.getId());
            
            //3 passo- executa comando
            stmt.execute();
            stmt.close();
            
            JOptionPane.showMessageDialog(null, "Alterado com sucesso");
            
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro"+erro);
        }
    }
    //metodo excluir cliente
    public void excluirCliente(Clientes obj){
        try {
            //1 passo - comando sql
            String sql = "delete from tb_clientes where id = ?";
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
    
    //metodo listar clientes
    public List<Clientes> listarCliente(){
        try {
            //1passo - criar lista
            List<Clientes> lista = new ArrayList<>();
            
            //2passo- criar metodo sql
            String sql = "select * from tb_clientes";
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();//toda vez que usar select usa esse comando
            
            while(rs.next()){
                Clientes obj = new Clientes();
                
                obj.setId(rs.getInt("id"));
                obj.setNome(rs.getString("nome"));
                obj.setRg(rs.getString("rg"));
                obj.setCpf(rs.getString("cpf"));
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
}

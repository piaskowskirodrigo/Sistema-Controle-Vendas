/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.projeto.dao;

import br.com.projeto.jdbc.ConnectionFactory;

import br.com.projeto.model.Fornecedores;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
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
}

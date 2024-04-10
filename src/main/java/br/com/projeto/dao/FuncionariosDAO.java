/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.projeto.dao;

import br.com.projeto.jdbc.ConnectionFactory;
import br.com.projeto.model.Funcionarios;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class FuncionariosDAO {
    //conexao
    private Connection con;
    
    public FuncionariosDAO(){
        //cia um construtor para conectar o banco
        this.con = new ConnectionFactory().getConnection();
    }
    
    //metodo cadastrar funcionario
    
    public void cadastrarFuncionarios(Funcionarios obj){
        try {
            //1 passo - comando sql
            String sql = "insert into tb_funcionarios(nome,rg,cpf,email,senha,cargo,nivel_acesso,telefone,celular,cep,endereco,numero,complemento,bairro,cidade,estado)"
                                               + "values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            //2passo- conectar banco e organizar
            PreparedStatement stmt = con.prepareStatement(sql);
            //organiza a ordem
            stmt.setString(1, obj.getNome());
            stmt.setString(2, obj.getRg());
            stmt.setString(3, obj.getCpf());
            stmt.setString(4, obj.getEmail());
            stmt.setString(5, obj.getSenha());
            stmt.setString(6, obj.getCargo());
            stmt.setString(7, obj.getNivel_acesso());
            stmt.setString(8, obj.getTelefone());
            stmt.setString(9, obj.getCelular());
            stmt.setString(10, obj.getCep());
            stmt.setString(11, obj.getEndereco());
            stmt.setInt(12, obj.getNumero());
            stmt.setString(13, obj.getComplemento());
            stmt.setString(14, obj.getBairro());
            stmt.setString(15, obj.getCidade());
            stmt.setString(16, obj.getUf());
            
            //3 passo- executa comando
            stmt.execute();
            stmt.close();
            
            JOptionPane.showMessageDialog(null, "Funcionario cadastrado com sucesso");
            
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro"+erro);
        }
        
    }
}

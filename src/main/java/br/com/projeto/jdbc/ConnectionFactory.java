/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.projeto.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author Rodrigo
 */
/*CONEXAO COM O BANCO DE DADOS*/
public class ConnectionFactory {
    public Connection getConnection(){
        
        try {
            return  DriverManager.getConnection("jdbc:mysql://127.0.0.1/bdvendas","usuariocurso","123"); //DriverManager que conecta o banco
        } catch (Exception erro) {
            throw new RuntimeException(erro);
        }
    }
}

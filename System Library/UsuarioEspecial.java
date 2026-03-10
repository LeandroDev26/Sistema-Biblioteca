/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sistema_biblioteca;

public class UsuarioEspecial extends Usuario {

    private static final int MAX_EMPRESTIMOS_ESPECIAL = 5; // Exemplo de limite maior

    public UsuarioEspecial() {
        super(); // Chama o construtor da classe mãe (Usuario)
        super.setMaxEmprestimos(MAX_EMPRESTIMOS_ESPECIAL);
    }

    
    public UsuarioEspecial(String nome, String login, String senha) {
        super(); 
        this.setNome(nome); 
        this.setLogin(login);
        this.setSenha(senha);
        super.setMaxEmprestimos(MAX_EMPRESTIMOS_ESPECIAL);
    }

    
}

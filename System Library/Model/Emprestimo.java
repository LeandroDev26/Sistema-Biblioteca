/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sistema_biblioteca;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Emprestimo {

    private Usuario usuario;
    private Item item;
    private int qtRenovado;
    private LocalDate dataExpiracao;
    private boolean status;

    public Emprestimo(Usuario usuario, Item item) {
        this.usuario = usuario;
        this.item = item;
        this.status = true;
        this.qtRenovado = 0;
        this.dataExpiracao = LocalDate.now().plusDays(14);
    }

    public void renovar() {
        this.qtRenovado++;
        this.dataExpiracao = this.dataExpiracao.plusDays(14);
    }

    public String getDataExpiracaoFormatada() {
        DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return this.dataExpiracao.format(formatador);
    }

    // --- GETTER ADICIONADO ---
    public LocalDate getDataExpiracao() {
        return dataExpiracao;
    }

    // --- SEUS MÉTODOS EXISTENTES (sem alterações) ---
    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public int getQtRenovado() {
        return qtRenovado;
    }

    public void setQtRenovado(int qtRenovado) {
        this.qtRenovado = qtRenovado;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}

package com.mycompany.sistema_biblioteca;

import java.time.LocalDate;

public class Reserva {

    private Usuario usuario;
    private Item item;
    private boolean status;
    private LocalDate dataLimite; // CAMPO ADICIONADO

    // Construtor atualizado
    // Em Reserva.java
public Reserva(Usuario usuario, Item item) {
    this.usuario = usuario;
    this.item = item;
    this.status = true; // Reserva nasce ativa
    this.dataLimite = LocalDate.now().plusDays(2); 
}

    public boolean expirou() {
        return LocalDate.now().isAfter(this.dataLimite);
    }

    // Getters e Setters
    public Usuario getUsuario() {
        return usuario;
    }

    public Item getItem() {
        return item;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}

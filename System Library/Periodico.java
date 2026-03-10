package com.mycompany.sistema_biblioteca;

import java.util.List;

public class Periodico extends Item {

    public Periodico(int id, String nome, List<String> autores, int ano, float penalidadeAtraso, int tempoReserva) {
        super(id, nome, autores, ano, penalidadeAtraso, tempoReserva);
    }
    
    public Periodico() {
        super();
    }
    
}
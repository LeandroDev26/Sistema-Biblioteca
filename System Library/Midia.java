package com.mycompany.sistema_biblioteca;

import java.util.List;

public class Midia extends Item {

    public Midia(int id, String nome, List<String> autores, int ano, float penalidadeAtraso, int tempoReserva) {
        super(id, nome, autores, ano, penalidadeAtraso, tempoReserva);
    }

    public Midia() {
        super();
    }
    
}
package com.mycompany.sistema_biblioteca;

import java.util.ArrayList;
import java.util.List;

public class Item {

    private int id;
    private String nome;
    private List<String> autores;
    private int ano;
    private float penalidadeAtraso;
    private int tempoReserva;

    private List<Emprestimo> listaEmprestimos;
    private List<Reserva> listaReservas;

    public Item(int id, String nome, List<String> autores, int ano, float penalidadeAtraso, int tempoReserva) {
        this.id = id;
        this.nome = nome;
        this.autores = autores;
        this.ano = ano;
        this.penalidadeAtraso = penalidadeAtraso;
        this.tempoReserva = tempoReserva;
        this.listaEmprestimos = new ArrayList<>();
        this.listaReservas = new ArrayList<>();
    }

    public Item() {
        this.autores = new ArrayList<>();
        this.listaEmprestimos = new ArrayList<>();
        this.listaReservas = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<String> getAutores() {
        return autores;
    }

    public void setAutores(List<String> autores) {
        this.autores = autores;
    }

    public void adicionarAutor(String autor) {
        if (this.autores == null) {
            this.autores = new ArrayList<>();
        }
        this.autores.add(autor);
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public float getPenalidadeAtraso() {
        return penalidadeAtraso;
    }

    public void setPenalidadeAtraso(float penalidadeAtraso) {
        this.penalidadeAtraso = penalidadeAtraso;
    }

    public int getTempoReserva() {
        return tempoReserva;
    }

    public void setTempoReserva(int tempoReserva) {
        this.tempoReserva = tempoReserva;
    }

    public List<Emprestimo> getListaEmprestimos() {
        return listaEmprestimos;
    }

    public void setListaEmprestimos(List<Emprestimo> listaEmprestimos) {
        this.listaEmprestimos = listaEmprestimos;
    }

    public List<Reserva> getListaReservas() {
        return listaReservas;
    }

    public void setListaReservas(List<Reserva> listaReservas) {
        this.listaReservas = listaReservas;
    }

    public void InserirEmprestimo(Emprestimo emprestimo) {
        this.listaEmprestimos.add(emprestimo);
    }

    public void InserirReserva(Reserva reserva) {
        this.listaReservas.add(reserva);
    }

    public boolean estaEmprestado() {
        for (Emprestimo emp : this.listaEmprestimos) {
            if (emp.isStatus()) {
                return true;
            }
        }
        return false;
    }

    public boolean estaReservado() {
        for (Reserva res : this.listaReservas) {
            if (res.isStatus()) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return "ID: " + id + ", Nome: " + nome + ", Autores: " + String.join(", ", autores) + ", Ano: " + ano;
    }
}
package com.mycompany.sistema_biblioteca;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Usuario {

    private String nome;
    private String login;
    private String senha;
    private double multaPendente;
    private LocalDate dataFimSuspensao;
    private int maxEmprestimos;
    private List<Emprestimo> listaEmprestimos = new ArrayList<>();
    private List<Reserva> listaReservas = new ArrayList<>();

    public Usuario() {
        this.multaPendente = 0.0;
        this.dataFimSuspensao = null;
        this.listaEmprestimos = new ArrayList<>();
        this.listaReservas = new ArrayList<>();
        // maxEmprestimos será definido pelas subclasses (UsuarioComum, UsuarioEspecial)
        // ou pode ter um valor padrão se você instanciar Usuario diretamente.
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public double getMultaPendente() {
        return multaPendente;
    }

    public void adicionarMulta(double valor) {
        this.multaPendente += valor;
    }

    public void pagarMulta(double valor) {
        this.multaPendente -= valor;
        if (this.multaPendente < 0) {
            this.multaPendente = 0;
        }
    }

    public LocalDate getDataFimSuspensao() {
        return dataFimSuspensao;
    }

    public void setDataFimSuspensao(LocalDate dataFimSuspensao) {
        this.dataFimSuspensao = dataFimSuspensao;
    }

    public boolean estaPunido() {
        if (this.dataFimSuspensao == null) {
            return false; 
        }
        
        return !this.dataFimSuspensao.isBefore(LocalDate.now());
    }

    public int getMaxEmprestimos() {
        return maxEmprestimos;
    }

    public void setMaxEmprestimos(int maxEmprestimos) {
        this.maxEmprestimos = maxEmprestimos;
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

    public void ImprimirListaEmprestimos() {
        System.out.println("--- Empréstimos ATIVOS de: " + this.getNome() + " ---");
        boolean temAtivos = false;
        int contadorAtivos = 0;
        for (Emprestimo emp : this.listaEmprestimos) {
            if (emp.isStatus()) { // Verifica se o empréstimo está ativo
                System.out.println(" - Item: " + emp.getItem().getNome());
                temAtivos = true;
                contadorAtivos++;
            }
        }
        if (!temAtivos) {
            System.out.println("Nenhum item emprestado no momento.");
        } else {
            System.out.println("Total de empréstimos ativos: " + contadorAtivos);
        }
        System.out.println("---------------------------------------------");
    }

    
    public int getNumeroEmprestimosAtivos() {
        int contador = 0;
        for (Emprestimo emp : this.listaEmprestimos) {
            if (emp.isStatus()) { // Verifica se o empréstimo está ativo
                contador++;
            }
        }
        return contador;
    }
}
package com.mycompany.sistema_biblioteca;

import java.util.Iterator;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class Sistema_Biblioteca {

    private List<Usuario> listaUsuarios = new ArrayList<>();
    private List<Item> listaItens = new ArrayList<>();

    private static final int DIAS_DE_SUSPENSAO_POR_DIA_DE_ATRASO = 1;

    public Emprestimo emprestar(Usuario usuario, Item item) {
        if (usuario.estaPunido()) {
            DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            System.out.println("Empréstimo não permitido: Usuário " + usuario.getNome()
                    + " está punido (suspenso) até " + usuario.getDataFimSuspensao().format(formatador) + ".");
            return null;
        }

        if (item.estaEmprestado()) {
            System.out.println("Empréstimo não permitido: O item '" + item.getNome() + "' já está emprestado.");
            return null;
        }

        if (usuario.getNumeroEmprestimosAtivos() >= usuario.getMaxEmprestimos()) {
            System.out.println("Empréstimo não permitido: Usuário " + usuario.getNome()
                    + " atingiu o limite de " + usuario.getMaxEmprestimos() + " empréstimos ativos.");
            return null;
        }
        
        Emprestimo emp = new Emprestimo(usuario, item);
        usuario.InserirEmprestimo(emp);
        item.InserirEmprestimo(emp);
        
       
        return emp;
    }

    public void devolverItem(Emprestimo emprestimo) {
        if (emprestimo == null) {
            System.out.println("Erro: Empréstimo inválido.");
            return;
        }
        if (!emprestimo.isStatus()) {
            System.out.println("Aviso: Este item já foi devolvido anteriormente.");
            return;
        }

        verificarEaplicarPunicao(emprestimo);

        emprestimo.setStatus(false);
        System.out.println("\nDevolução realizada com sucesso!");
        System.out.println("Item: " + emprestimo.getItem().getNome());
    }

    private void verificarEaplicarPunicao(Emprestimo emprestimo) {
        LocalDate hoje = LocalDate.now();
        LocalDate dataExpiracao = emprestimo.getDataExpiracao();

        if (hoje.isAfter(dataExpiracao)) {
            long diasDeAtraso = ChronoUnit.DAYS.between(dataExpiracao, hoje);
            Usuario usuario = emprestimo.getUsuario();
            Item item = emprestimo.getItem();
            float penalidadeDiaria = item.getPenalidadeAtraso() > 0 ? item.getPenalidadeAtraso() : 0.50f;
            double multaTotal = diasDeAtraso * penalidadeDiaria;
            int diasDeSuspensao = (int) diasDeAtraso * DIAS_DE_SUSPENSAO_POR_DIA_DE_ATRASO;
            LocalDate novaDataFimSuspensao = hoje.plusDays(diasDeSuspensao);
            usuario.adicionarMulta(multaTotal);

            if (usuario.getDataFimSuspensao() == null || novaDataFimSuspensao.isAfter(usuario.getDataFimSuspensao())) {
                usuario.setDataFimSuspensao(novaDataFimSuspensao);
            }

            System.out.println("\n--- ATENÇÃO: DEVOLUÇÃO COM ATRASO! ---");
            System.out.println("Dias de Atraso: " + diasDeAtraso);
            System.out.printf("Multa Aplicada: R$ %.2f%n", multaTotal);
            System.out.println("Usuário " + usuario.getNome() + " suspenso até: " + usuario.getDataFimSuspensao().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        }
    }

    public List<Usuario> getListaUsuarios() {
        return this.listaUsuarios;
    }

    public List<Item> getListaItens() {
        return this.listaItens;
    }

    public void adicionarUsuario(Usuario usuario) {
        this.listaUsuarios.add(usuario);
    }

    public void adicionarItem(Item item) {
        this.listaItens.add(item);
    }

    public void reservar(Usuario usuario, Item item) {
        if (!item.estaEmprestado()) {
            System.out.println("Reserva não permitida: O item '" + item.getNome() + "' está disponível.");
            return;
        }
        for (Emprestimo emp : usuario.getListaEmprestimos()) {
            if (emp.getItem().equals(item) && emp.isStatus()) {
                System.out.println("Reserva não permitida: Você já está com este item.");
                return;
            }
        }
        for (Reserva res : usuario.getListaReservas()) {
            if (res.getItem().equals(item) && res.isStatus()) {
                System.out.println("Reserva não permitida: Você já possui uma reserva para este item.");
                return;
            }
        }
        System.out.println("Realizando reserva para o usuário " + usuario.getNome() + "...");
        Reserva novaReserva = new Reserva(usuario, item);
        usuario.InserirReserva(novaReserva);
        item.InserirReserva(novaReserva);
        System.out.println("Reserva do item '" + item.getNome() + "' realizada com sucesso!");
    }

    public void renovarEmprestimo(Emprestimo emprestimo) {
        final int MAX_RENOVACOES = 2;

        if (emprestimo.getItem().estaReservado()) {
            System.out.println("Renovação não permitida: O item '" + emprestimo.getItem().getNome() + "' está reservado.");
            return;
        }
        if (emprestimo.getQtRenovado() >= MAX_RENOVACOES) {
            System.out.println("Renovação não permitida: Limite de " + MAX_RENOVACOES + " renovações atingido.");
            return;
        }

        emprestimo.renovar();

        System.out.println("Empréstimo do item '" + emprestimo.getItem().getNome() + "' renovado com sucesso!");
        System.out.println("Nova data de devolução: " + emprestimo.getDataExpiracaoFormatada());
    }

    public void limparReservas() {
        System.out.println("\n--- Iniciando limpeza de reservas expiradas ---");
        int reservasRemovidas = 0;

        for (Item itemDaLista : this.listaItens) {
            List<Reserva> reservasDoItem = itemDaLista.getListaReservas();
            Iterator<Reserva> iterator = reservasDoItem.iterator();
            while (iterator.hasNext()) {
                Reserva res = iterator.next();
                if (!res.isStatus() || res.expirou()) {
                    iterator.remove();
                    res.getUsuario().getListaReservas().remove(res);
                    reservasRemovidas++;
                    System.out.println("Reserva para o item '" + itemDaLista.getNome() + "' pelo usuário '" + res.getUsuario().getNome() + "' foi removida.");
                }
            }
        }

        if (reservasRemovidas > 0) {
            System.out.println("Limpeza concluída. Total de " + reservasRemovidas + " reservas removidas.");
        } else {
            System.out.println("Nenhuma reserva expirada ou inválida encontrada.");
        }
        System.out.println("--------------------------------------------");
    }
}
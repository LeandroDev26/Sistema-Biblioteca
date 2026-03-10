package com.mycompany.sistema_biblioteca;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static Scanner scanner = new Scanner(System.in);
    private static Sistema_Biblioteca biblioteca = new Sistema_Biblioteca();

    public static void main(String[] args) {

        boolean executando = true;

        while (executando) {
            exibirMenu();
            try {
                int opcao = scanner.nextInt();
                scanner.nextLine(); // Limpa o buffer do scanner

                switch (opcao) {
                    case 1:
                        cadastrarNovoUsuario();
                        break;
                    case 2:
                        cadastrarNovoItem();
                        break;
                    case 3:
                        realizarEmprestimo();
                        break;
                    case 4:
                        realizarDevolucao();
                        break;
                    case 5:
                        realizarReserva();
                        break;
                    case 6:
                        realizarRenovacao();
                        break;
                    case 7:
                        limparReservasDoSistema();
                        break;
                    case 0:
                        executando = false;
                        System.out.println("Obrigado por utilizar o sistema da biblioteca!");
                        break;
                    default:
                        System.out.println("Opção inválida! Por favor, tente novamente.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Erro: Entrada inválida. Por favor, digite um número.");
                scanner.nextLine(); // Limpa a entrada incorreta do scanner
            }
            if (executando) {
                pressioneEnterParaContinuar();
            }
        }

        scanner.close();
    }

    private static void exibirMenu() {
        System.out.println("\n--- MENU DA BIBLIOTECA ---");
        System.out.println("1. Cadastrar Novo Usuário");
        System.out.println("2. Cadastrar Novo Item");
        System.out.println("3. Realizar Empréstimo");
        System.out.println("4. Realizar Devolução");
        System.out.println("5. Reservar Item");
        System.out.println("6. Renovar Empréstimo");
        System.out.println("7. Limpar Reservas Expiradas");
        System.out.println("0. Sair do Sistema");
        System.out.print("Escolha uma opção: ");
    }

    private static void limparReservasDoSistema() {
        biblioteca.limparReservas();
    }

    private static void cadastrarNovoUsuario() {
        System.out.println("\n--- Cadastro de Usuário ---");
        System.out.print("Digite o nome do usuário: ");
        String nome = scanner.nextLine();
        System.out.print("Digite o login: ");
        String login = scanner.nextLine();
        System.out.print("Digite a senha: ");
        String senha = scanner.nextLine();

        System.out.println("Qual o tipo de usuário?");
        System.out.println("1. Comum");
        System.out.println("2. Especial");
        System.out.print("Escolha o tipo: ");
        int tipoUsuarioOpcao = -1;
        try {
            tipoUsuarioOpcao = scanner.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Entrada inválida. Assumindo Comum.");
            tipoUsuarioOpcao = 1;
        } finally {
            scanner.nextLine();
        }

        Usuario novoUsuario;

        if (tipoUsuarioOpcao == 2) {
            novoUsuario = new UsuarioEspecial(nome, login, senha);
            System.out.println("Usuário Especial selecionado.");
        } else {
            novoUsuario = new UsuarioComum(nome, login, senha);
            System.out.println("Usuário Comum selecionado.");
        }

        biblioteca.adicionarUsuario(novoUsuario);
        System.out.println("Usuário '" + novoUsuario.getNome() + "' cadastrado com sucesso como "
                + (novoUsuario instanceof UsuarioEspecial ? "Especial" : "Comum")
                + " (Limite de empréstimos: " + novoUsuario.getMaxEmprestimos() + ").");
    }

    private static void cadastrarNovoItem() {
        System.out.println("\n--- Cadastro de Novo Item ---");
        Item novoItem = null;

        System.out.println("Qual o tipo de item a ser cadastrado?");
        System.out.println("1. Livro");
        System.out.println("2. Periódico");
        System.out.println("3. Mídia");
        System.out.println("4. Monografia");
        System.out.print("Escolha o tipo: ");
        int tipoItemOpcao = -1;
        try {
            tipoItemOpcao = scanner.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Entrada inválida. Operação cancelada.");
            scanner.nextLine();
            return;
        } finally {
            scanner.nextLine();
        }

        System.out.print("Digite o ID do item: ");
        int id = -1;
        try {
            id = scanner.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("ID inválido. Operação cancelada.");
            scanner.nextLine();
            return;
        } finally {
            scanner.nextLine();
        }

        System.out.print("Digite o nome do item: ");
        String nome = scanner.nextLine();

        List<String> autores = new ArrayList<>();
        System.out.print("Digite um autor (ou deixe em branco para parar): ");
        String autorInput = scanner.nextLine();
        while (!autorInput.isEmpty()) {
            autores.add(autorInput);
            System.out.print("Digite outro autor (ou deixe em branco para parar): ");
            autorInput = scanner.nextLine();
        }
        if (autores.isEmpty()) {
            autores.add("Autor Desconhecido");
        }


        System.out.print("Digite o ano de publicação: ");
        int ano = 0;
        try {
            ano = scanner.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Ano inválido. Usando 0 como padrão.");
        } finally {
            scanner.nextLine();
        }


        System.out.print("Digite a penalidade por atraso (ex: 1.50 ou 1,50): ");
        float penalidade = 0.0f;
        String penalidadeStr = scanner.nextLine();
        try {
            penalidade = Float.parseFloat(penalidadeStr.replace(',', '.'));
        } catch (NumberFormatException e) {
            System.out.println("Penalidade inválida. Usando 0.0 como padrão.");
        }


        System.out.print("Digite o tempo de reserva em dias (ex: 2): ");
        int tempoReserva = 2;
        try {
            tempoReserva = scanner.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Tempo de reserva inválido. Usando " + tempoReserva + " como padrão.");
        } finally {
            scanner.nextLine();
        }


        switch (tipoItemOpcao) {
            case 1:
                novoItem = new Livro(id, nome, autores, ano, penalidade, tempoReserva);
                System.out.println("Livro cadastrado.");
                break;
            case 2:
                novoItem = new Periodico(id, nome, autores, ano, penalidade, tempoReserva);
                System.out.println("Periódico cadastrado.");
                break;
            case 3:
                novoItem = new Midia(id, nome, autores, ano, penalidade, tempoReserva);
                System.out.println("Mídia cadastrada.");
                break;
            case 4:
                novoItem = new Monografia(id, nome, autores, ano, penalidade, tempoReserva);
                System.out.println("Monografia cadastrada.");
                break;
            default:
                System.out.println("Tipo de item inválido. Nenhum item foi cadastrado.");
                return;
        }

        if (novoItem != null) {
            biblioteca.adicionarItem(novoItem);
            System.out.println("Item '" + novoItem.getNome() + "' adicionado à biblioteca com sucesso!");
        }
    }

    private static void realizarEmprestimo() {
        System.out.println("\n--- Realizar Empréstimo ---");
        Usuario usuario = selecionarUsuario();
        if (usuario == null) return;
        Item item = selecionarItem(true);
        if (item == null) return;
        biblioteca.emprestar(usuario, item);
    }

    private static void realizarDevolucao() {
        System.out.println("\n--- Realizar Devolução ---");
        Usuario usuario = selecionarUsuario();
        if (usuario == null) return;
        Emprestimo emprestimo = selecionarEmprestimo(usuario);
        if (emprestimo == null) return;
        biblioteca.devolverItem(emprestimo);
    }

    private static void realizarReserva() {
        System.out.println("\n--- Realizar Reserva ---");
        Usuario usuario = selecionarUsuario();
        if (usuario == null) return;
        Item item = selecionarItem(false);
        if (item == null) return;
        biblioteca.reservar(usuario, item);
    }

    private static void realizarRenovacao() {
        System.out.println("\n--- Renovar Empréstimo ---");
        Usuario usuario = selecionarUsuario();
        if (usuario == null) return;
        Emprestimo emprestimo = selecionarEmprestimo(usuario);
        if (emprestimo == null) return;
        biblioteca.renovarEmprestimo(emprestimo);
    }

    private static Usuario selecionarUsuario() {
        List<Usuario> usuarios = biblioteca.getListaUsuarios();
        if (usuarios.isEmpty()) {
            System.out.println("Nenhum usuário cadastrado.");
            return null;
        }
        System.out.println("Selecione um usuário:");
        for (int i = 0; i < usuarios.size(); i++) {
            String tipo = (usuarios.get(i) instanceof UsuarioEspecial) ? "Especial" : "Comum";
            System.out.println((i + 1) + ". " + usuarios.get(i).getNome() + " (" + tipo + ")");
        }
        System.out.print("Opção: ");
        int escolha = -1;
        try {
            escolha = scanner.nextInt();
        } catch (InputMismatchException e) {}
        finally {
            scanner.nextLine();
        }
        
        if (escolha > 0 && escolha <= usuarios.size()) {
            return usuarios.get(escolha - 1);
        } else {
            System.out.println("Seleção inválida.");
            return null;
        }
    }

    private static Item selecionarItem(boolean apenasDisponiveis) {
        List<Item> todosOsItens = biblioteca.getListaItens();
        if (todosOsItens.isEmpty()) {
            System.out.println("Nenhum item cadastrado.");
            return null;
        }
        
        List<Item> itensExibiveis = new ArrayList<>();
        System.out.println("Selecione um item:");
        for (Item item : todosOsItens) {
            if (apenasDisponiveis && item.estaEmprestado()) {
                continue;
            }
            itensExibiveis.add(item);
        }

        if (itensExibiveis.isEmpty()) {
            System.out.println(apenasDisponiveis ? "Nenhum item disponível para empréstimo." : "Nenhum item para listar.");
            return null;
        }

        for (int i = 0; i < itensExibiveis.size(); i++) {
            Item item = itensExibiveis.get(i);
            String disponibilidade = item.estaEmprestado() ? "(Emprestado)" : "(Disponível)";
            System.out.println((i + 1) + ". " + item.getNome() + " " + disponibilidade);
        }
        
        System.out.print("Opção: ");
        int escolha = -1;
        try {
            escolha = scanner.nextInt();
        } catch (InputMismatchException e) {}
        finally {
            scanner.nextLine();
        }

        if (escolha > 0 && escolha <= itensExibiveis.size()) {
            return itensExibiveis.get(escolha - 1);
        } else {
            System.out.println("Seleção inválida.");
            return null;
        }
    }

    private static Emprestimo selecionarEmprestimo(Usuario usuario) {
        List<Emprestimo> emprestimosAtivos = new ArrayList<>();
        for (Emprestimo emp : usuario.getListaEmprestimos()) {
            if (emp.isStatus()) {
                emprestimosAtivos.add(emp);
            }
        }

        if (emprestimosAtivos.isEmpty()) {
            System.out.println("Este usuário não possui empréstimos ativos.");
            return null;
        }

        System.out.println("Selecione um Item para devolver/renovar:");
        for (int i = 0; i < emprestimosAtivos.size(); i++) {
            System.out.println((i + 1) + ". " + emprestimosAtivos.get(i).getItem().getNome());
        }
        System.out.print("Opção: ");
        int escolha = -1;
         try {
            escolha = scanner.nextInt();
        } catch (InputMismatchException e) {}
        finally {
            scanner.nextLine();
        }

        if (escolha > 0 && escolha <= emprestimosAtivos.size()) {
            return emprestimosAtivos.get(escolha - 1);
        } else {
            System.out.println("Seleção inválida.");
            return null;
        }
    }
    
    private static void pressioneEnterParaContinuar() {
        System.out.print("\nPressione Enter para continuar...");
        scanner.nextLine();
    }
}
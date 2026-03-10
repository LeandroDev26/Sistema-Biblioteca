# 📚 Sistema de Gestão de Biblioteca (Library System)

Este projeto é uma aplicação robusta desenvolvida em Java para automatizar as operações de uma biblioteca. O sistema gerencia diversos tipos de itens (Livros, Periódicos, Mídias e Monografias) e diferentes categorias de usuários, aplicando regras de negócio para empréstimos, reservas e penalidades.

## 🚀 Funcionalidades Principais
O sistema é operado via console e oferece um fluxo completo de gestão:
1.  **Gestão de Usuários:** Cadastro de usuários **Comuns** e **Especiais**, cada um com diferentes limites de empréstimo.
2.  **Catálogo de Itens:** Cadastro categorizado de Livros, Periódicos, Mídias e Monografias.
3.  **Sistema de Empréstimo:** Realização de empréstimos com verificação de disponibilidade, limite do usuário e status de suspensão.
4.  **Devolução e Penalidades:** Cálculo automático de multas e dias de suspensão em caso de atraso na entrega.
5.  **Reservas:** Permite reservar itens que já estão emprestados, com sistema de limpeza de reservas expiradas.
6.  **Renovações:** Extensão do prazo de devolução, desde que o item não possua reservas ativas.

## 🏗️ Arquitetura e Estrutura de Classes
O projeto demonstra o uso de conceitos avançados de **Programação Orientada a Objetos**:

* **`Sistema_Biblioteca.java`**: O "Controller" do sistema, contendo toda a lógica de negócio (emprestar, devolver, punir).
* **`Usuario.java` (Base)**: Classe que define o comportamento comum, estendida por `UsuarioComum` e `UsuarioEspecial` para polimorfismo de limites.
* **`Item.java` (Base)**: Superclasse para `Livro`, `Midia`, `Periodico` e `Monografia`, utilizando herança para compartilhar atributos como ID, Nome e Autores.
* **`Emprestimo.java` & `Reserva.java`**: Classes associativas que gerenciam o vínculo entre Usuários e Itens, incluindo controle de datas com `LocalDate`.
* **`Main.java`**: Interface de usuário (View) que gerencia o menu e a interação via console.

## 🛠️ Tecnologias e Conceitos Aplicados
* **Java 8+ (API `java.time`):** Uso de `LocalDate`, `ChronoUnit` e `DateTimeFormatter` para cálculos precisos de datas de devolução e multas.
* **Herança e Polimorfismo:** Especialização de classes para tratar diferentes regras de negócio de forma genérica.
* **Encapsulamento:** Proteção de dados sensíveis e controle de estado através de Getters e Setters.
* **Tratamento de Exceções:** Uso de `InputMismatchException` para garantir a estabilidade do sistema contra entradas inválidas.

## 💻 Como Executar
1.  Clone o repositório.
2.  Compile o projeto a partir da classe principal:
    ```bash
    javac com/mycompany/sistema_biblioteca/Main.java
    ```
3.  Execute a aplicação:
    ```bash
    java com.mycompany.sistema_biblioteca.Main
    ```

---
*Projeto desenvolvido para consolidar conceitos de Programação Orientada a Objetos I no 3º Período de Ciência da Computação.*
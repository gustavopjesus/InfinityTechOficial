package SistemaGeral.Dominio;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Cliente implements Serializable {
    private static final long serialVersionUID = 1L;
    private String nome;
    public String cpf;
    private String telefone;
    private String email;
    private Cliente cliente;

    public Cliente(ArrayList<Cliente> clientes) {
        this.nome = "Gustavo";
        this.cpf = "48117281813";
        this.telefone = "11912578787";
        this.email = "gustavopaulodejesus797@gmail.com";

        clientes.add(this);
    }

    public static void salvarClientes(ArrayList<Cliente> clientes) {
        try (ObjectOutputStream oos = new ObjectOutputStream(
                new FileOutputStream("clientes.dat"))) {
            oos.writeObject(clientes);
        } catch (IOException e) {
            System.out.println("Erro ao salvar clientes: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    public static ArrayList<Cliente> carregarClientes() {
        File arquivo = new File("clientes.dat");
        if (!arquivo.exists()) {
            System.out.println("Nenhum cliente cadastrado anteriormente.");
            return new ArrayList<>();
        }
        try (ObjectInputStream ois = new ObjectInputStream(
                new FileInputStream("clientes.dat"))) {
            ArrayList<Cliente> clientes = (ArrayList<Cliente>) ois.readObject();
            System.out.println(clientes.size() + " cliente(s) carregado(s).");
            return clientes;
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Erro ao carregar clientes: " + e.getMessage());
            return new ArrayList<>();
        }
    }


    public void cadastrarCliente(Scanner leia, ArrayList<Cliente> clientes) {
        System.out.println("\n╔══════════════════════════╗");
        System.out.println("║    Cadastro de cliente   ║");
        System.out.println("╚══════════════════════════╝");

        System.out.print("\nDigite o nome do cliente: ");
        nome = leia.nextLine();

        System.out.print("Digite o telefone (com DDD, apenas números): ");
        telefone = leia.nextLine().trim();

        while (!telefone.matches("\\d{11}")) {
            System.out.println("Telefone inválido! Digite 11 números com DDD");
            System.out.print("Digite o telefone: ");
            telefone = leia.nextLine().trim();
        }

        boolean cpfContinua = true;

        while (cpfContinua) {
            System.out.print("Digite o CPF do cliente: ");
            cpf = leia.nextLine();

            cpf = cpf.replaceAll("[^0-9]", "");

            if (cpf.length() != 11) {
                System.out.println("CPF inválido");
                continue;
            }

            if (cpf.matches("(\\d)\\1{10}")) {
                System.out.println("CPF inválido");
                continue;
            }

            int soma = 0;
            for (int i = 0; i < 9; i++) {
                soma += (cpf.charAt(i) - '0') * (10 - i);
            }
            int primeirodig = 11 - (soma % 11);
            if (primeirodig >= 10) primeirodig = 0;
            if (primeirodig != (cpf.charAt(9) - '0')) {
                System.out.println("CPF inválido");
                continue;
            }

            soma = 0;
            for (int i = 0; i < 10; i++) {
                soma += (cpf.charAt(i) - '0') * (11 - i);
            }
            int segundodig = 11 - (soma % 11);
            if (segundodig >= 10) segundodig = 0;
            if (segundodig != (cpf.charAt(10) - '0')) {
                System.out.println("CPF inválido");
                continue;
            }


            boolean cpfEncontrado = false;
            for (Cliente cli : clientes) {
                if (cli.getCpf().equals(cpf)) {
                    cpfEncontrado = true;
                    break;
                }
            }

            if (cpfEncontrado) {
                System.out.println("\n╔══════════════════════════╗");
                System.out.println("║     CPF já cadastrado    ║");
                System.out.println("╠══════════════════════════╣");
                System.out.println("║01 - Digitar outro CPF    ║");
                System.out.println("║02 - Sair                 ║");
                System.out.println("╚══════════════════════════╝");
                System.out.print("\n   Escolha a opção: ");
                String opcao = leia.nextLine().trim();

                if (opcao.equals("1") || opcao.equals("01")) {
                    continue;
                } else {
                    return;
                }
            }

            System.out.print("Digite o email: ");
            email = leia.nextLine();

            clientes.add(this);
            System.out.println("\nCliente cadastrado com sucesso!");
            cpfContinua = false;
        }
    }

    public void imprimeCliente() {
        System.out.println("Nome: " + this.nome);
        System.out.println("CPF: " + this.cpf);
        System.out.println("Telefone: " + this.telefone);
        System.out.println("Email: " + this.email);
    }

    public void listaClinte(ArrayList<Cliente> clientes) {
        for (Cliente c : clientes) {
            System.out.println("=============================");
            System.out.println("Nome: " + c.getNome());
            System.out.println("CPF: " + c.getCpf());
            System.out.println("Telefone: " + c.getTelefone());
            System.out.println("Email: " + c.getEmail());
            System.out.println("=============================");
        }
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
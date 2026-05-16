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

//        String [] preCadastro = new String[4];
//        preCadastro[0] = "Gustavo";
//        preCadastro[1] = "481172818136";
//        preCadastro[2] = "11912578787";
//        preCadastro[3] = "gustavopaulodejesus797@gmail.com";

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


    public void cadastrarCliente(Scanner leia, ArrayList<Cliente> clientes){
        System.out.println("-----------------------------");
        System.out.println("    Cadastro de cliente");
        System.out.println("-----------------------------");

        System.out.print("\nDigite o nome do cliente: ");
        nome = leia.nextLine();

        System.out.print("\nDigite o telefone: ");
        telefone = leia.nextLine();

        boolean cpfContinua = true;

        while (cpfContinua) {
            System.out.print("Digite o CPF do cliente: ");
            cpf = leia.nextLine();

            cpf = cpf.replaceAll("[^0-9]", "");

            if (cpf.length() != 11){
                System.out.println("CPF inválido");
                continue;
            }

            if (cpf.matches("(\\d)\\1{10}")) {
                System.out.println("CPF inválido");
                continue;
            }
             int soma = 0;

        for (int i = 0; i<9; i++){
            soma+= (cpf.charAt(i) - '0') * (10 - i);
        }
        int primeirodig = 11 - (soma % 11);

        if (primeirodig>=10){
            primeirodig = 0;
        }
        if (primeirodig !=(cpf.charAt(9) - '0')){
            System.out.println("CPF inválido");
            continue;
        }
        soma = 0;

        for (int i = 0; i<10; i++){
            soma += (cpf.charAt(i) - '0') * (11-i);
        }
        int segundodig = 11 - (soma % 11);

        if (segundodig>=10){
            segundodig = 0;
        }
        if (segundodig != (cpf.charAt(10) - '0')) {
            System.out.println("CPF inválido");
            continue;
        }
        boolean cpfEncontrado = false;

            System.out.print("Digite o email: ");
            email = leia.nextLine();

        for (Cliente cli: clientes){
            if (cli.getCpf().equals(cpf)){
                cpfEncontrado = true;
                System.out.println("\nCliente Cadastrado com sucesso!");
                System.out.println("Cliente: " + cli.getNome() + "\nCPF: " + cli.getCpf());
                break;
            }
        }
            if (!cpfEncontrado){
                System.out.println("Cliente não cadastrado!");
                cliente.cadastrarCliente(leia, clientes);
            }
        cpfContinua = false;
}
}
    public void imprimeCliente(){
        System.out.println("Nome: "+this.nome);
        System.out.println("CPF: "+this.cpf);
        System.out.println("Telefone: "+this.telefone);
        System.out.println("Email: "+this.email);
    }
    public void listaClinte(ArrayList<Cliente> clientes){
        for(Cliente c : clientes){
            System.out.println("=============================");
            System.out.println("Nome: "+c.getNome());
            System.out.println("CPF: "+c.getCpf());
            System.out.println("Telefone: "+c.getTelefone());
            System.out.println("Email: "+c.getEmail());
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
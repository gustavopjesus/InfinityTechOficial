package SistemaGeral.Dominio;

import java.util.ArrayList;
import java.util.Scanner;

public class Cliente {
    private String nome;
    private String cpf;
    private String telefone;
    private String email;

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

    public void cadastrarCliente(Scanner leia, ArrayList<Cliente> clientes){
        System.out.println("-----------------------------");
        System.out.println("    Cadastro de cliente");
        System.out.println("-----------------------------");

        System.out.print("\nDigite o nome do cliente: ");
        nome = leia.nextLine();

        boolean cpfContinua = true;

        while (cpfContinua) {
            System.out.print("Digite o CPF do cliente: ");
            cpf = leia.nextLine();
            if (cpf.length() < 11) {
                System.out.println("CPF invalido");
            } else if (cpf.length() > 11) {
                System.out.println("CPF invalido");
            } else if(getCpf().equals(cpf)) {
                System.out.println("\nCPF já cadastrado!");
                break;
            }else{
                System.out.print("Digite o telefone do cliente: ");
                telefone = leia.nextLine();
                System.out.print("Digite o email do cliente: ");
                email = leia.nextLine();

                clientes.add(this);

                System.out.println("\nCliente cadastrado com sucesso!");
                cpfContinua = false;
            }
        }
    }

    public void imprimeCliente(){
        System.out.println("Nome: "+this.nome);
        System.out.println("CPF: "+this.cpf);
        System.out.println("Telefone: "+this.telefone);
        System.out.println("Email: "+this.email);
    }

    public void listaClinte(ArrayList<Cliente> pessoas){
        for(Cliente c : pessoas){
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

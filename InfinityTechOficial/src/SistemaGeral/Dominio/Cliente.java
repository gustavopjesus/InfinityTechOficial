package SistemaGeral.Dominio;

import java.util.ArrayList;
import java.util.Scanner;

public class Cliente {
    private String nome;
    private String cpf;
    private String telefone;
    private String email;



    public void cadastrarCliente(Scanner leia, ArrayList<Cliente> pessoas){
        System.out.println("Cadastrando Cliente");

        System.out.print("Digite o nome do cliente: ");
        nome = leia.nextLine();
        System.out.print("Digite o CPF do cliente: ");
        cpf = leia.nextLine();
        System.out.print("Digite o telefone do cliente: ");
        telefone = leia.nextLine();
        System.out.print("Digite o email do cliente: ");
        email = leia.nextLine();

        pessoas.add(this);
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

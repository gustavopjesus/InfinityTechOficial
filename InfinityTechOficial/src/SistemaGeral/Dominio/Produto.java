package SistemaGeral.Dominio;

import java.util.ArrayList;
import java.util.Scanner;

public class Produto {
    private String nome;
    private int id;
    //    private String categoria;
    private double valor;
    private int quantidade;

    public void imprimeProduto(){
        System.out.println("\nNome:" + this.nome);
        System.out.println("ID:" + this.id);
//        System.out.println("Categoria: " + this.categoria);
        System.out.printf("Valor: R$%.2f " , this.valor);
        System.out.println("\nQuantidade: " + this.quantidade);
    }
    public void cadastrarPoduto(ArrayList<Produto> lista, Scanner leia) {
        Produto produtos = new Produto();

        System.out.println("\nCADASTRO DE PRODUTO\n");

        System.out.print("Digite o nome do produto: ");
        produtos.setNome(leia.nextLine());

        System.out.print("Digite o ID do produto: ");
        produtos.setId(leia.nextInt());
        leia.nextLine();

//        System.out.print("Digite a categoria do produto: ");
//        produtos.setCategoria(leia.nextLine());

        System.out.print("Digite o valor: ");
        produtos.setValor(leia.nextDouble());
        leia.nextLine();

        System.out.print("Digite a quantidade: ");
        produtos.setQuantidade(leia.nextInt());
        leia.nextLine();

        lista.add(produtos);

        System.out.println("\nProduto cadastrado com sucesso\n");
    }


    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setId(int id) {
        this.id = id;
    }

//    public void setCategoria(String categoria) {
//        this.categoria = categoria;
//    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public String getNome() {
        return nome;
    }

    public int getId() {
        return id;
    }

//    public String getCategoria(){
//        return categoria;
//    }

    public double getValor(){
        return valor;
    }

    public int getQuantidade(){
        return quantidade;
    }
}
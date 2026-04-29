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
        System.out.println("Nome:" + this.nome);
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


        int maiorId = 0;
        for (Produto p : lista) {
            if (p.getId() > maiorId) {
                maiorId = p.getId();
            }
        }
        produtos.setId(maiorId + 1);

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

    public void produtoCadastrado(ArrayList<Produto> lista){

        Produto p1 = new Produto();
        p1.setNome("Teclado");
        p1.setId(1);
        p1.setValor(150.00);
        p1.setQuantidade(10);
        lista.add(p1);

        Produto p2 = new Produto();
        p2.setNome("Mouse");
        p2.setId(2);
        p2.setValor(80.00);
        p2.setQuantidade(5);
        lista.add(p2);

        Produto p3 = new Produto();
        p3.setNome("Monitor");
        p3.setId(3);
        p3.setValor(1200.00);
        p3.setQuantidade(3);
        lista.add(p3);

        Produto p4 = new Produto();
        p4.setNome("Headset");
        p4.setId(4);
        p4.setValor(250.00);
        p4.setQuantidade(7);
        lista.add(p4);

        Produto p5 = new Produto();
        p5.setNome("Webcam");
        p5.setId(5);
        p5.setValor(320.00);
        p5.setQuantidade(4);
        lista.add(p5);

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
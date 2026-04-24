package SistemaGeral.Dominio;

import java.util.ArrayList;
import java.util.Scanner;

public class Funcionario {
    public void cadastrarPoduto(ArrayList<Produto> lista, Scanner leia) {
        Produto produtos = new Produto();

        System.out.println("\nCADASTRO DE PRODUTO\n");

        System.out.print("Digite o nome do produto: ");
        produtos.setNome(leia.nextLine());

        System.out.print("Digite o ID do produto: ");
        produtos.setId(leia.nextInt());
        leia.nextLine();

        System.out.print("Digite a categoria do produto: ");
        produtos.setCategoria(leia.nextLine());

        System.out.print("Digite o valor: ");
        produtos.setValor(leia.nextDouble());
        leia.nextLine();

        System.out.print("Digite a quantidade: ");
        produtos.setQuantidade(leia.nextInt());
        leia.nextLine();

        lista.add(produtos);

        System.out.println("\nProduto cadastrado com sucesso\n");
    }

    public void listProdutos(ArrayList<Produto> lista) {
        System.out.println("\nLista de produtos:");

        for (Produto produto : lista) {
            System.out.println("-----------------");
            produto.imprimeProduto();
            System.out.println("-----------------\n");
        }
    }

}
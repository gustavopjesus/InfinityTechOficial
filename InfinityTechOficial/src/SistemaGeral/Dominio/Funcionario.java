package SistemaGeral.Dominio;

import java.util.ArrayList;
import java.util.Scanner;

public class Funcionario {


    public void listProdutos(ArrayList<Produto> lista) {
        System.out.println("\nLista de produtos:\n");

        for (Produto produto : lista) {
            System.out.println("-----------------");
            produto.imprimeProduto();
            System.out.println("-----------------\n");
        }
    }

}
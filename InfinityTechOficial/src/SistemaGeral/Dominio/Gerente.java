package SistemaGeral.Dominio;

import java.util.ArrayList;
import java.util.Scanner;

public class Gerente {
    public void alterarProduto(ArrayList<Produto> lista, Scanner leia) {
        boolean removido = false;
        System.out.print("Digite o ID do produto que deseja alterar: ");
        int id = leia.nextInt();
        leia.nextLine();

        Produto produtoEncontrado = null;
        for (Produto produto: lista){
            if(produto.getId() == id){
                produtoEncontrado = produto;
                break;
            }
        }

        if (produtoEncontrado != null){
            System.out.print("Novo nome: ");
            produtoEncontrado.setNome(leia.nextLine());
            System.out.print("Novo valor: ");
            produtoEncontrado.setValor(leia.nextDouble());
            leia.nextLine();
            System.out.print("Nova quantidade: ");
            produtoEncontrado.setQuantidade(leia.nextInt());
            leia.nextLine();
            System.out.print("Produto alterado com sucesso!\n");
        }else{
            System.out.println("Produto não encotrado!");
        }

    }





        public void excluirProduto(ArrayList<Produto> lista, Scanner leia) {
            boolean removido = false;
            System.out.print("Digite o ID do produto que deseja excluir: ");
            int id = leia.nextInt();
            for (int i = 0; i < lista.size(); i++) {
                if (lista.get(i).getId() == id) {
                    lista.remove(i);
                    removido = true;
                    System.out.println("Produto removido");
                    break;

                }


            }

        }






}


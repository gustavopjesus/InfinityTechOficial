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
        for (Produto produto : lista) {
            if (produto.getId() == id) {
                produtoEncontrado = produto;
                break;
            }
        }

        if (produtoEncontrado == null) {
            System.out.println("Produto não cadastrado");
            return;
        }
        while (true) {
            System.out.print("Novo nome: ");
            String nvnome = leia.nextLine();

            boolean temn = false;
            for (Character c : nvnome.toCharArray()) {
                if (Character.isDigit(c)) {
                    temn = false;
                    break;
                }
            }
            if (nvnome.length() == 0) {
                System.out.println("Nome inválido");
            } else if (temn) {
                System.out.println("Nome não pode ser vázio");
            } else {
                produtoEncontrado.setNome(nvnome);
                break;
            }
        }
        String nvvalor;
        while (true) {
            System.out.print("Novo valor: ");
            nvvalor = leia.nextLine();
            boolean teml2 = false;
            for (char c : nvvalor.toCharArray()) {
                if (!Character.isDigit(c)) {
                    teml2 = true;
                    break;
                }
            }
            if (nvvalor.length() == 0 || teml2) {
                System.out.println("Digite apenas números");
            } else {
                produtoEncontrado.setValor(Double.parseDouble(nvvalor));
                break;
            }
        }

        while (true) {
            System.out.print("Nova quantidade: ");
            String input = leia.nextLine();
            boolean teml = false;
            for (char c : input.toCharArray()) {
                if (!Character.isDigit(c)) {
                    teml = true;
                    break;
                }
            }
            if (input.length() == 0 || teml) {
                System.out.println("Digite apenas números");
            } else {
                int quantidadenova = Integer.parseInt(input);
                produtoEncontrado.setQuantidade(produtoEncontrado.getQuantidade() + quantidadenova);
                System.out.print("Produto alterado com sucesso!\n");
                return;
            }
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


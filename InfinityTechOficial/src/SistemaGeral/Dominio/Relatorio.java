package SistemaGeral.Dominio;

import java.util.ArrayList;

public class Relatorio {

    public void imprimeRelatorio(ArrayList<Venda> relatorioVendas) {

        if (relatorioVendas.isEmpty()) {
            System.out.println("\nNenhuma venda realizada.");
            return;
        }

        System.out.println("\n===== RELATÓRIO DE VENDAS =====");

        for (Venda venda : relatorioVendas) {
            System.out.println("-----------------------------");
            System.out.println("Produto: " + venda.getNomeProdutoVendido());
            System.out.println("Quantidade: " + venda.getQuantidadeProdutoVendido());
            System.out.println("Valor total: R$ " + venda.getValorDoDoProdutoVendido());
        }

        System.out.println("-----------------------------");
    }
    public void imprimeRelatorioProdutos(ArrayList<Produto> lista) {
        if (lista.isEmpty()) {
            System.out.println("\nNenhum produto cadastrado.");
            return;
        }

        System.out.println("\n===== RELATÓRIO DE PRODUTOS =====");

        for (Produto produto : lista) {
            System.out.println("-----------------------------");
            System.out.println("ID: " + produto.getId());
            System.out.println("Nome: " + produto.getNome());
            System.out.println("Valor: R$ " + produto.getValor());
            System.out.println("Quantidade em estoque: " + produto.getQuantidade());
        }

        System.out.println("-----------------------------");
    }
}

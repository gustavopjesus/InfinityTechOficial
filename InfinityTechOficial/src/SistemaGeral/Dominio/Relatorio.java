package SistemaGeral.Dominio;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Relatorio {

    public void imprimeRelatorio(ArrayList<Venda> relatorioVendas) {

        if (relatorioVendas.isEmpty()) {
            System.out.println("\nNenhuma venda realizada.");
        } else {
            System.out.println("\n===== RELATÓRIO DE VENDAS =====");

            for (Venda venda : relatorioVendas) {
                System.out.println("-----------------------------");
                System.out.println("Produto: " + venda.getNomeProdutoVendido());
                System.out.println("Quantidade: " + venda.getQuantidadeProdutoVendido());
                System.out.println("Valor total: R$ " + venda.getValorDoDoProdutoVendido());
            }

            System.out.println("-----------------------------");
        }

        try {
            BufferedReader reader = new BufferedReader(new FileReader("historico.txt"));
            String linha;

            System.out.println("\n===== HISTÓRICO DE VENDAS =====");

            while ((linha = reader.readLine()) != null) {
                if (linha.contains("Venda")) {
                    System.out.println("-----------------------------");
                    System.out.println(linha);
                }
            }

            reader.close();

        } catch (IOException e) {
            System.out.println("\nNenhum histórico encontrado.");
        }
    }

    public void imprimeRelatorioProdutos(ArrayList<Produto> lista, ArrayList<String> relatorioHistorico) {

        System.out.println("\n===== RELATÓRIO DE PRODUTOS =====");

        if (lista.isEmpty()) {
            System.out.println("\nNenhum produto cadastrado.");
        } else {
            for (Produto produto : lista) {
                System.out.println("-----------------------------");
                System.out.println("ID: " + produto.getId());
                System.out.println("Nome: " + produto.getNome());
                System.out.println("Valor: R$ " + produto.getValor());
                System.out.println("Quantidade em estoque: " + produto.getQuantidade());
            }
        }

        if (relatorioHistorico.isEmpty()) {
            System.out.println("\nNenhum produto cadastrado.");
            return;
        }

        for (String historico : relatorioHistorico) {

            String dados = historico.substring(historico.indexOf("Produto"));

            String[] partes = dados.split("\\|");

            System.out.println("-----------------------------");

            for (String parte : partes) {
                System.out.println(parte.trim());
            }
        }
        System.out.println("-----------------------------");
    }
}
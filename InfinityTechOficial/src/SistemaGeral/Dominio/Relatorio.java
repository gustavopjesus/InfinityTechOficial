package SistemaGeral.Dominio;

import java.util.ArrayList;

public class Relatorio {
    public void imprimeRelatorio(ArrayList<Venda> relatorioVendas) {
        System.out.println("\nLista de vendas:");

        for (Venda venda : relatorioVendas) {
            System.out.println("-----------------");
            System.out.println(venda.getNomeProdutoVendido());
            System.out.println(venda.getQuantidadeProdutoVendido());
            System.out.println(venda.getValorDoDoProdutoVendido());
            System.out.println("-----------------\n");
        }
    }
}

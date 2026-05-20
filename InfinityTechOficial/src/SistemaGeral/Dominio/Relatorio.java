package SistemaGeral.Dominio;

import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

public class Relatorio {

    public void imprimeRelatorio(ArrayList<Venda> relatorioVendas, Scanner leia, ArrayList<String> relatorioHistorico) {


        if (relatorioVendas.isEmpty()) {
            System.out.println("\nNenhuma venda realizada.");
            return;
        }
        boolean noRelatorio = true;
        while(noRelatorio){
            System.out.println("\n╔══════════════════════════════╗");
            System.out.println("║     RELATÓRIO DE VENDAS      ║");
            System.out.println("╠══════════════════════════════╣");
            System.out.println("║   01 - Relatorio geral       ║");
            System.out.println("║   02 - Filtrar por produto   ║");
            System.out.println("║   03 - Faturamento total     ║");
            System.out.println("║   04 - Produto mais vendido  ║");
            System.out.println("║   05 - Voltar                ║");
            System.out.println("╚══════════════════════════════╝");

            int opcaoRelatorio;

            System.out.print("\n  Digite a opção: ");
            if (leia.hasNextInt()) {
                opcaoRelatorio = leia.nextInt();
                leia.nextLine();

            } else {
                System.out.println("\nDigite apenas números\n");
                leia.next();
                continue;
            }
                switch (opcaoRelatorio) {
                    case 1:
                        double faturamentoTotal01 = 0;
                        int itemProduto = 0;

                        System.out.println("--------- VENDAS GERAIS---------\n");
                        for (Venda venda : relatorioVendas) {
                            System.out.println("__________________________________________________");
                            System.out.println("Produto  : " + venda.getNomeProdutoVendido());
                            System.out.println("Quantidade  :" + venda.getQuantidadeProdutoVendido());
                            System.out.printf("Valor  : R$ %.2f%n", venda.getValorDoDoProdutoVendido());
                            System.out.println("__________________________________________________");
                            faturamentoTotal01 += venda.getValorDoDoProdutoVendido();
                            itemProduto += venda.getQuantidadeProdutoVendido();
                        }

                        System.out.println("\n__________________________________________________");
                        System.out.println(" Total de vendas   : " + relatorioVendas.size());
                        System.out.println(" Total de itens    : " + itemProduto);
                        System.out.println(" Faturamento total R$ " + faturamentoTotal01);
                        System.out.println("__________________________________________________");

                        break;
                    case 2:

                        System.out.print("\nDigite o nome do produto: ");
                        String busca = leia.nextLine().trim().toLowerCase();
                        //.trim() = tirar os espaços | .toLowerCase() = Para deixar minusculo

                        double totalPrduto = 0;
                        int qltProduto = 0;
                        int vendasPrduto = 0;

                        System.out.println("\n--------- VENDAS---------\n");
                        for (Venda filtro : relatorioVendas) {
                            if (filtro.getNomeProdutoVendido().toLowerCase().contains(busca)) { //.contains = encontrar a palavra digitada
                                System.out.println("------------------------------");
                                System.out.println("Prodtuo: " + filtro.getNomeProdutoVendido());
                                System.out.println("Quantidade: " + filtro.getQuantidadeProdutoVendido());
                                System.out.println("Preço: " + filtro.getValorDoDoProdutoVendido());
                                System.out.println("------------------------------");
                                totalPrduto += filtro.getValorDoDoProdutoVendido();
                                qltProduto += filtro.getQuantidadeProdutoVendido();
                                vendasPrduto++;
                            }
                        }
                        if (vendasPrduto == 0) {
                            System.out.println("\nNenhuma venda encontrada para o produto: " + busca);
                            return;
                        }
                        System.out.println("------------------------------");
                        System.out.println("Produto: " + busca);
                        System.out.println("Número de vendas: " + vendasPrduto);
                        System.out.println("Total do produto: " + qltProduto);
                        System.out.printf("Total faturado: R$ %.2f%n", totalPrduto);
                        System.out.println("------------------------------");
                        break;
                    case 3:
                        HashMap<String, Double> faturamentoPorProduto = new HashMap<>();
                        HashMap<String, Integer> qtdPorProduto = new HashMap<>();
                        double totalGeral = 0;
                        // HASHMAP = caixas que guardam dados em pares

                        for (Venda faturamento : relatorioVendas) {
                            String nome = faturamento.getNomeProdutoVendido();
                            if (faturamentoPorProduto.containsKey(nome)) {
                                double valorAtual = faturamentoPorProduto.get(nome);
                                faturamentoPorProduto.put(nome, valorAtual + faturamento.getValorDoDoProdutoVendido());

                                int qtdAtual = qtdPorProduto.get(nome);
                                qtdPorProduto.put(nome, qtdAtual + faturamento.getQuantidadeProdutoVendido());
                            } else {
                                faturamentoPorProduto.put(nome, faturamento.getValorDoDoProdutoVendido());

                                qtdPorProduto.put(nome, faturamento.getQuantidadeProdutoVendido());
                            }
                            totalGeral += faturamento.getValorDoDoProdutoVendido();
                        }
                        System.out.println("\n--------- FATURAMENTO POR PRODUTO ---------");

                        for (String nome : faturamentoPorProduto.keySet()) {
                            System.out.println("-------------------------------------");
                            System.out.println("Produto         : " + nome);
                            System.out.println("Qtd total vendida: " + qtdPorProduto.get(nome));
                            System.out.printf("Faturamento     : R$ %.2f%n", faturamentoPorProduto.get(nome));
                        }

                        System.out.println("\n=====================================");
                        System.out.printf("FATURAMENTO TOTAL: R$ %.2f%n", totalGeral);
                        System.out.println("======================================");

                        break;
                    case 4:


                        HashMap<String, Integer> qtdPorProdut = new HashMap<>();
                        HashMap<String, Double> faturamentoProduto = new HashMap<>();

                        for (Venda v : relatorioVendas) {
                            String nome = v.getNomeProdutoVendido();
                            qtdPorProdut.merge(nome, v.getQuantidadeProdutoVendido(), Integer::sum);
                            faturamentoProduto.merge(nome, v.getValorDoDoProdutoVendido(), Double::sum);
                        }

                        String maisVendido = null;
                        int maiorQuantidade = 0;

                        for (HashMap.Entry<String, Integer> entry : qtdPorProdut.entrySet()) {
                            if (entry.getValue() > maiorQuantidade) {
                                maiorQuantidade = entry.getValue();
                                maisVendido = entry.getKey();
                            }
                        }

                        System.out.println("\n===== PRODUTO MAIS VENDIDO =====");
                        System.out.println("-------------------------------------");
                        System.out.println("Produto        : " + maisVendido);
                        System.out.println("Total vendido  : " + maiorQuantidade + " unidade(s)");
                        System.out.printf("Total faturado : R$ %.2f%n", faturamentoProduto.get(maisVendido));
                        System.out.println("-------------------------------------");

                        break;
                    case 5:
                        noRelatorio = false;
                        return;
                    default:
                        System.out.println("Opção invalida");
                        break;
                }
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
            int index = historico.indexOf("Produto");
            if (index == -1) {
                continue;
            }
            String dados = historico.substring(index);
            String[] partes = dados.split("\\|");
            System.out.println("-----------------------------");
            for (String parte : partes) {
                System.out.println(parte.trim());
            }
        }
        System.out.println("-----------------------------");
    }

}

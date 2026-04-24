package SistemaGeral.Start;

import SistemaGeral.Dominio.Funcionario;
import SistemaGeral.Dominio.Produto;
import SistemaGeral.Dominio.Relatorio;
import SistemaGeral.Dominio.Venda;

import java.util.ArrayList;
import java.util.Scanner;

public class StartNow {
    public static void main(String[] args) {


        Scanner leia = new Scanner(System.in);
        ArrayList<Produto> lista = new ArrayList<>();
        ArrayList<Venda> venda = new ArrayList<>();
        ArrayList<Venda> relatorioVendas = new ArrayList<>();


        int escolha;
        int quemEvoce;


        while (true) {

            System.out.println("----------------------------------------------------------------------------");
            System.out.println("                              Infinity Tech");
            System.out.println("----------------------------------------------------------------------------");
            System.out.println("\n01 - Cliente ");
            System.out.println("02 - Funcionario");
            System.out.println("03 - Gerente");
            System.out.println("04 - Sair");

            System.out.print("\nDigite a opção: ");
            quemEvoce = leia.nextInt();
            leia.nextLine();

            switch (quemEvoce) {
                case 1:
                    System.out.println("Cliente");
                    break;
                case 2:
                    boolean continuar = true;
                    while (continuar) {

                        System.out.println("\n1 - Adicionar produto");
                        System.out.println("2 - Listar produtos");
                        System.out.println("3 - Venda");
                        System.out.println("4 - Voltar");
                        System.out.print("\nDigite a escolha: ");
                        escolha = leia.nextInt();

                        leia.nextLine();

                        switch (escolha) {

                            case 1:
                                Funcionario funcionario = new Funcionario();
                                funcionario.cadastrarPoduto(lista, leia);
                                break;
                            case 2:
                                Funcionario listProdutos = new Funcionario();
                                listProdutos.listProdutos(lista);
                                break;
                            case 3:
                                Venda vendaProduto = new Venda();
                                vendaProduto.vendaProduto(lista, venda, relatorioVendas);
                                break;
                            case 4:
                                continuar = false;
                                System.out.println("Voltar");
                                break;
                            default:
                                System.out.println("Opção inválida!");
                                break;
                        }
                    }
                    break;
                case 3:
                    boolean contin = true;
                    while (contin) {

                        System.out.println("\n1 - Listar produtos");
                        System.out.println("2 - Cadastrar produto produtos");
                        System.out.println("3 - Venda");
                        System.out.println("4 - Voltar");


                        int escolha01;

                        escolha01 = leia.nextInt();

                        switch (escolha01) {
                            case 1:
                                Relatorio relatorio = new Relatorio();
                                relatorio.imprimeRelatorio(relatorioVendas);
                                break;
                            case 2:
                                System.out.println(" ");
                                break;
                            case 3:
                                System.out.println("44 ");
                            case 4:
                                contin = false;
                                System.out.println("Voltar");
                                break;
                            default:
                                System.out.println("Opção inválida!");
                                break;
                        }
                    }
                    break;
                case 4:
                    System.out.println("Saindo do sistema...");
                    return;
                default:
                    System.out.println("\nOpção ivalida");
                    System.out.println("Tente novamente\n");
                    break;
            }
        }
    }
}
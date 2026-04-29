package SistemaGeral.Start;

import SistemaGeral.Dominio.*;

import java.util.ArrayList;
import java.util.Scanner;

public class StartNow {
    public static void main(String[] args) {


        Scanner leia = new Scanner(System.in);
        ArrayList<Produto> lista = new ArrayList<>();

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


        ArrayList<Venda> venda = new ArrayList<>();
        ArrayList<Venda> relatorioVendas = new ArrayList<>();


        int escolha;
        int quemEvoce;


        while (true) {
            System.out.println("----------------------------------------------------------------------------");
            System.out.println("                              Infinity Tech");
            System.out.println("----------------------------------------------------------------------------");
            System.out.println("\n01 - Funcionário ");
            System.out.println("02 - Gerente");
            System.out.println("03 - Sair");

            System.out.print("\nDigite a opção: ");
            quemEvoce = leia.nextInt();
            leia.nextLine();

            switch (quemEvoce) {
                case 1:
                    boolean passouf = true;

                    while (passouf) {
                        Credenciais credenciais = new Credenciais("root", "root");
                        System.out.print("\nLogin: ");
                        // credenciais.setLogin(leia.nextLine());
                        String Logindigitado = leia.nextLine();
                        System.out.print("Senha: ");
                        //  credenciais.setSenha(leia.nextLine());
                        String Senhadigitado = leia.nextLine();

                        if (Logindigitado.equals(credenciais.getLogin()) && Senhadigitado.equals(credenciais.getSenha())) {
                            System.out.println("\n Acesso permitido");
                            break;
                        } else {
                            System.out.println("\nLogin/senha inválido Tente novamente ");
                        }
                    }
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
                                Produto produto = new Produto();
                                produto.cadastrarPoduto(lista, leia);
                                break;
                            case 2:
                                Funcionario listProdutos = new Funcionario();
                                listProdutos.listProdutos(lista);
                                break;
                            case 3:
                                Venda vendaProduto = new Venda();
                                vendaProduto.vendaProduto(lista,relatorioVendas);
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
                case 2:
                    boolean passoug = true;

                    while (passoug) {
                    Credenciais credenciais = new Credenciais("admin", "admin");
                    System.out.print("\nLogin: ");
                    // credenciais.setLogin(leia.nextLine());
                    String Logindigitado = leia.nextLine();
                    System.out.print("Senha: ");
                    //  credenciais.setSenha(leia.nextLine());
                    String Senhadigitado = leia.nextLine();

                        if (Logindigitado.equals(credenciais.getLogin()) && Senhadigitado.equals(credenciais.getSenha())) {
                            System.out.println("\n Acesso permitido");
                            break;
                        } else {
                            System.out.println("\nLogin/senha inválido Tente novamente ");
                        }
                    }


                    boolean contin = true;
                    while (contin) {
                        System.out.println("\n1 - Cadastrar produto ");
                        System.out.println("2 - Alterar produto");
                        System.out.println("3 - Excluir produtos");
                        System.out.println("4 - Relátorio vendas");
                        System.out.println("5 - Relátorio de produtos");
                        System.out.println("6 - listar produtos");
                        System.out.println("7 - Voltar");


                        int escolha01;
                        System.out.print("\nDigite a opção: ");
                        escolha01 = leia.nextInt();

                        Gerente gerente = new Gerente();
                        switch (escolha01) {
                            case 1:
                                Produto produto = new Produto();
                                leia.nextLine();
                                produto.cadastrarPoduto(lista, leia);
                                break;
                            case 2:
                                leia.nextLine();
                                gerente.alterarProduto(lista, leia);
                                break;
                            case 3:
                                gerente.excluirProduto(lista, leia);
                                break;

                            case 4:
                                Relatorio relatorio = new Relatorio();
                                relatorio.imprimeRelatorio(relatorioVendas);
                                break;
                            case 5:
                                Relatorio relatorioProdutos = new Relatorio();
                                relatorioProdutos.imprimeRelatorioProdutos(lista);
                                break;
                            case 6:
                                Funcionario listProdutos = new Funcionario();
                                listProdutos.listProdutos(lista);
                                break;
                            case 7:
                                contin = false;
                                System.out.println("Voltar");
                                break;
                            default:
                                System.out.println("Opção inválida!");
                                break;
                        }
                    }
                    break;
                case 3:
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

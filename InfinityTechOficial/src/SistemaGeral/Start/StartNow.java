package SistemaGeral.Start;

import SistemaGeral.Dominio.*;

import java.util.ArrayList;
import java.util.Scanner;

public class StartNow {
    public static void main(String[] args) {
        Scanner leia = new Scanner(System.in);

        ArrayList<Produto> lista = Produto.carregarProdutos();
        if (lista.isEmpty()) {
            new Produto().produtoCadastrado(lista);
        }

        ArrayList<Venda> relatorioVendas = Venda.carregarVendas();

        ArrayList<Cliente> clientes = Cliente.carregarClientes();
        ArrayList<String> relatorioHistorico = new ArrayList<>();

        try {
            java.io.BufferedReader reader =
                    new java.io.BufferedReader(
                            new java.io.FileReader("historico.txt")
                    );

            String linha;

            while ((linha = reader.readLine()) != null) {
                relatorioHistorico.add(linha);
            }

            reader.close();

        } catch (java.io.IOException e) {
            System.out.println("Nenhum histórico encontrado.");
        }
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            Cliente.salvarClientes(clientes);
            Produto.salvarProdutos(lista);
            Venda.salvarVendas(relatorioVendas);

            System.out.println("Dados salvos automaticamente!");
        }));

        int escolha;
        int quemEvoce;
        boolean sistema = true;


        while (sistema) {
            System.out.println("============================================================================");
            System.out.println("                              INFINITY TECH");
            System.out.println("============================================================================");
            System.out.println("\n01 - Funcionário ");
            System.out.println("02 - Gerente");
            System.out.println("03 - Sair");

            System.out.print("\nDigite a opção: ");
            if (leia.hasNextInt()) {
                quemEvoce = leia.nextInt();
                leia.nextLine();

            } else {
                System.out.println("Digite apenas números");
                leia.nextLine();
                continue;
            }

            switch (quemEvoce) {
                case 1:
                    boolean passouf = true;
                    int tentativa01 = 0;

                    while (passouf) {
                        Credenciais credenciais = new Credenciais("root", "root");
                        System.out.print("\nLogin: ");
                        String Logindigitado = leia.nextLine();
                        System.out.print("Senha: ");
                        String Senhadigitado = leia.nextLine();

                        if (Logindigitado.equals(credenciais.getLogin()) && Senhadigitado.equals(credenciais.getSenha())) {
                            System.out.println("\n Acesso permitido");
                            break;
                        } else {
                            tentativa01++;
                            System.out.println("\nLogin/senha inválido Tente novamente ");

                            if (tentativa01 == 3) {
                                System.out.println("\nVocê antigiu o limite de tentativas.");
                                System.out.println("\n01 - Tentar novamente");
                                System.out.println("02 - Voltar menu principal");

                                System.out.print("\nDigite a opção: ");
                                int opcao00 = 0;

                                if (leia.hasNextInt()) {
                                    opcao00 = leia.nextInt();
                                } else {
                                    leia.nextLine();
                                    passouf = false;
                                }
                                if (opcao00 == 1) {
                                    tentativa01 = 0;
                                    leia.nextLine();
                                } else if (opcao00 == 2) {
                                    passouf = false;
                                } else {
                                    passouf = false;
                                }
                            }
                        }
                    }
                    if (tentativa01 == 3) {
                        break;
                    }
                    boolean continuar = true;
                    while (continuar) {

                        System.out.println("\n1 - Adicionar produto");
                        System.out.println("2 - Listar produtos");
                        System.out.println("3 - Venda");
                        System.out.println("4 - Voltar");
                        System.out.print("\nDigite a escolha: ");

                        if (leia.hasNextInt()) {
                            escolha = leia.nextInt();

                        } else {
                            System.out.println("\nDigite apenas números");
                            leia.nextLine();
                            continue;
                        }

                        leia.nextLine();

                        switch (escolha) {
                            case 1:
                                Produto produto = new Produto();
                                produto.cadastrarPoduto(lista, leia, relatorioHistorico);
                                break;
                            case 2:
                                Relatorio relatorioProdutos = new Relatorio();
                                relatorioProdutos.imprimeRelatorioProdutos(lista, relatorioHistorico);
                                break;
                            case 3:
                                Venda vendaProduto = new Venda();
                                vendaProduto.vendaProduto(lista, relatorioVendas, clientes);
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
                    int tentativa02 = 0;

                    while (passoug) {
                        Credenciais credenciais = new Credenciais("admin", "admin");
                        System.out.print("\nLogin: ");
                        String Logindigitado = leia.nextLine();
                        System.out.print("Senha: ");
                        String Senhadigitado = leia.nextLine();

                        if (Logindigitado.equals(credenciais.getLogin()) && Senhadigitado.equals(credenciais.getSenha())) {
                            System.out.println("\n Acesso permitido");
                            break;
                        } else {
                            tentativa02++;
                            System.out.println("\nLogin/senha inválido Tente novamente ");

                            if (tentativa02 == 3) {
                                System.out.println("\nVocê antigiu o limite de tentativas.");
                                System.out.println("\n01 - Tentar novamente");
                                System.out.println("02 - Voltar menu principal");

                                System.out.print("\nDigite a opção: ");
                                int opcao01 = 0;

                                if (leia.hasNextInt()) {
                                    opcao01 = leia.nextInt();
                                } else {
                                    leia.nextLine();
                                    passoug = false;
                                }
                                if (opcao01 == 1) {
                                    tentativa02 = 0;
                                    leia.nextLine();
                                } else if (opcao01 == 2) {
                                    passoug = false;
                                } else {
                                    passoug = false;
                                }
                            }
                        }
                    }
                    if (tentativa02 == 3) {
                        break;
                    }
                    boolean contin = true;

                    while (contin) {
                        System.out.println("\n1 - Cadastrar produto ");
                        System.out.println("2 - Alterar produto");
                        System.out.println("3 - Excluir produtos");
                        System.out.println("4 - Relátorio vendas");
                        System.out.println("5 - Relátorio de produtos");
                        System.out.println("6 - Voltar");

                        int escolha01;

                        System.out.print("\nDigite a opção: ");
                        if (leia.hasNextInt()) {
                            escolha01 = leia.nextInt();
                        } else {
                            System.out.println("\nDigite apenas números");
                            leia.nextLine();
                            continue;
                        }

                        Gerente gerente = new Gerente();
                        switch (escolha01) {
                            case 1:
                                Produto produto = new Produto();
                                leia.nextLine();
                                produto.cadastrarPoduto(lista, leia, relatorioHistorico);
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
                                relatorioProdutos.imprimeRelatorioProdutos(lista, relatorioHistorico);
                                break;
                            case 6:
                                System.out.println("Voltar");
                                contin = false;
                                break;
                            default:
                                System.out.println("Opção inválida!");
                                break;
                        }
                    }
                    break;
                case 3:
                    System.out.println("Saindo do sistema...");
                    sistema = false;
                    return;
                default:
                    System.out.println("\nOpção ivalida");
                    System.out.println("Tente novamente\n");
                    break;
            }
        }

    }
}


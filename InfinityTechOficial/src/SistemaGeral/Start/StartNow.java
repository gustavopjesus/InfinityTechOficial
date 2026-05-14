package SistemaGeral.Start;
import SistemaGeral.Dominio.*;
import java.util.ArrayList;
import java.util.Scanner;

public class StartNow {
    public static void main(String[] args) {


        Scanner leia = new Scanner(System.in);
        ArrayList<Produto> lista = new ArrayList<>();

        Produto produtoCadastrado = new Produto();
        produtoCadastrado.produtoCadastrado(lista);

        ArrayList<Venda> venda = new ArrayList<>();
        ArrayList<Venda> relatorioVendas = new ArrayList<>();

        ArrayList<Cliente> clientes = new ArrayList<>();


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
            if (leia.hasNextInt()) {
                quemEvoce = leia.nextInt();
                leia.nextLine();

            }else {
                System.out.println("Digite apenas números");
                leia.nextLine();
                continue;
            }

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
                            System.out.println("6 - Voltar");

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
                        return;
                    default:
                        System.out.println("\nOpção ivalida");
                        System.out.println("Tente novamente\n");
                        break;
                }

        }
    }
}


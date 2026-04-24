package SistemaGeral.Dominio;
import java.util.ArrayList;
import java.util.Scanner;


public class Venda {

    private String nomeProdutoVendido;
    private double valorDoDoProdutoVendido;
    private int quantidadeProdutoVendido;


    public void vendaProduto(ArrayList<Produto> lista,ArrayList<Venda> venda,ArrayList<Venda> relatorioVendas ) {

        Scanner leia = new Scanner(System.in);

        System.out.println("\nRealizar venda");

        boolean control = true;
        while (control) {

            boolean pagamentoAprovado = false;
            Produto produtoEncontrado = null;

            System.out.println("\nBuscar produto: ");
            System.out.println("\n01 - Nome");
            System.out.println("02 - ID");
            System.out.println("03 - Voltar\n");

            System.out.print("Digite opção: ");
            int buscaProduto = leia.nextInt();
            leia.nextLine();

            if (buscaProduto == 1) {
                System.out.print("\nDigite o nome do produto: ");
                String nomeBuscado = leia.nextLine();

                for (Produto produto : lista) {
                    if (produto.getNome().equals(nomeBuscado)) {
                        produtoEncontrado = produto;
                        break;
                    }
                }
            } else if (buscaProduto == 2) {
                System.out.print("Digite o ID do produto: ");
                int idBuscado = leia.nextInt();

                for (Produto produto : lista) {
                    if (produto.getId() == idBuscado) {
                        produtoEncontrado = produto;
                        break;
                    }
                }
            } else if (buscaProduto == 3){
//                control = false;
                break;
            }else{
                System.out.println("Opção inválida");
                continue;
            }
            if (produtoEncontrado != null) {
                System.out.println("\nProduto encontrado!");
                produtoEncontrado.imprimeProduto();

                int quantidade;

                while (true) {
                    System.out.print("\nDigite a quantidade desejada pelo cliente: ");
                    quantidade = leia.nextInt();

                    if(produtoEncontrado.getQuantidade() <= 0){
                        System.out.println("Estoque zerado...");
//                        control = false;
                        return;
                    }else if (quantidade <= 0) {
                        System.out.println("Quantidade inválida");
                    } else if (produtoEncontrado.getQuantidade() < quantidade) {
                        System.out.println("\nEstoque insuficiente");
                        System.out.println("Estoque: "+produtoEncontrado.getQuantidade());
                    }else {
                        System.out.println("\nProduto: " + produtoEncontrado.getNome());
                        System.out.println("Valor: " + produtoEncontrado.getValor() * quantidade);
                        break;
                    }
                }
                double valorAtual = produtoEncontrado.getValor() * quantidade;


                while (!pagamentoAprovado) {

                    System.out.println("\nDigite a forma de pagamento:");
                    System.out.println("01 - Dinheiro");
                    System.out.println("02 - Cartão");
                    System.out.println("03 - Pix");
                    System.out.println("04 - Cancelar compra");

                    int pagamento = leia.nextInt();
                    if (pagamento == 1) {

                        System.out.print("Valor recebido: R$ ");
                        double valor = leia.nextDouble();
                        if (valor < valorAtual) {
                            System.out.println("Valor insuficiente");
                        } else {
                            if (valor > valorAtual) {
                                double troco = valor - valorAtual;
                                System.out.printf("Troco: R$ %.2f\n", troco);
                            }
                            System.out.println("Sucesso!");
                            pagamentoAprovado = true;
                        }
                    } else if (pagamento == 2) {

                        int opcao;

                        while (true) {
                            System.out.println("01 - débito \n02 - crédito?");
                            opcao = leia.nextInt();

                            if (opcao == 1 || opcao == 2)
                                break;
                            System.out.println("Opção inválida");
                        }
                        while (true) {
                            System.out.println("Compra aprovada? \n01 - Sim \n02 - Não");
                            opcao = leia.nextInt();

                            if (opcao == 1) {
                                System.out.println("Sucesso!");
                                pagamentoAprovado = true;
                                break;
                            } else if (opcao == 2) {
                                System.out.println("Pagamento recusado");
                                break;
                            } else {
                                System.out.println("Opção inválida");
                            }
                        }
                    } else if (pagamento == 3) {
                        System.out.println("Leia o QR-CODE");

                        int opcao;

                        while (true) {
                            System.out.println("Compra aprovada? \n01 - Sim \n02 - Não");
                            opcao = leia.nextInt();

                            if (opcao == 1) {
                                System.out.println("Sucesso!");
                                pagamentoAprovado = true;
                                break;
                            } else if (opcao == 2) {
                                System.out.println("Pagamento recusado");
                                break;
                            } else {
                                System.out.println("Opção inválida");
                            }
                        }

                    } else if (pagamento == 4) {
                        System.out.println("Compra cancelada");
                        control = false;
                        break;
                    } else {
                        System.out.println("Forma de pagamento inválida");
                    }
                }
                if (!pagamentoAprovado) {
                    continue;
                }

                produtoEncontrado.setQuantidade(produtoEncontrado.getQuantidade() - quantidade);
                System.out.println("Venda finalizada!");

                Venda relatorio = new Venda();

                relatorio.setNomeProdutoVendido(produtoEncontrado.getNome());
                relatorio.setQuantidadeProdutoVendido(quantidade);
                relatorio.setValorDoDoProdutoVendido(valorAtual);

                relatorioVendas.add(relatorio);

                control = false;

            } else {
                System.out.println("Produto não encontrado");
            }
        }
    }

    public String getNomeProdutoVendido() {
        return nomeProdutoVendido;
    }

    public void setNomeProdutoVendido(String nomeProdutoVendido) {
        this.nomeProdutoVendido = nomeProdutoVendido;
    }

    public double getValorDoDoProdutoVendido() {
        return valorDoDoProdutoVendido;
    }

    public void setValorDoDoProdutoVendido(double valorDoDoProdutoVendido) {
        this.valorDoDoProdutoVendido = valorDoDoProdutoVendido;
    }

    public int getQuantidadeProdutoVendido() {
        return quantidadeProdutoVendido;
    }

    public void setQuantidadeProdutoVendido(int quantidadeProdutoVendido) {
        this.quantidadeProdutoVendido = quantidadeProdutoVendido;
    }
}


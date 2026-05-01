package SistemaGeral.Dominio;

import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.JFrame;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;


public class Venda {

    private String nomeProdutoVendido;
    private double valorDoDoProdutoVendido;
    private int quantidadeProdutoVendido;

    //ArrayList<Venda> venda,
    //venda,
    public void vendaProduto(ArrayList<Produto> lista, ArrayList<Venda> relatorioVendas) {
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
            } else if (buscaProduto == 3) {
                break;
            } else {
                System.out.println("Opção inválida");
                continue;
            }

            if (produtoEncontrado != null) {

                System.out.println("\nProduto encontrado!");
                produtoEncontrado.imprimeProduto();

                int quantidade;

                while (true) {

                    System.out.println("\n1 - Escolher quantidade");
                    System.out.println("2 - Cancelar venda");
                    System.out.print("Escolha: ");

                    int op = leia.nextInt();

                    if (op == 1) {
                        System.out.print("\nDigite a quantidade: ");
                        quantidade = leia.nextInt();

                        if (produtoEncontrado.getQuantidade() <= 0) {
                            System.out.println("Estoque zerado");
                            return;

                        } else if (quantidade <= 0) {
                            System.out.println("Quantidade inválida");
                        } else if (produtoEncontrado.getQuantidade() < quantidade) {
                            System.out.println("Estoque insuficiente");
                            System.out.println("Estoque: " + produtoEncontrado.getQuantidade());
                        } else {
                            System.out.println("\nProduto: " + produtoEncontrado.getNome());
                            System.out.printf("Total R$%.2f\n", (produtoEncontrado.getValor() * quantidade));
                            break;

                        }

                    } else if (op == 2) {
                        System.out.println("Venda cancelada!");
                        return;
                    } else {
                        System.out.println("Opção Inválida");
                    }
                }
                if (quantidade == 0) {
                    continue;
                }
                double valorAtual = produtoEncontrado.getValor() * quantidade;

                while (!pagamentoAprovado) {

                    System.out.println("\nForma de pagamento:\n");
                    System.out.println("1 - Dinheiro");
                    System.out.println("2 - Cartão");
                    System.out.println("3 - Pix");
                    System.out.println("4 - Cancelar\n");

                    System.out.print("Escola a opção: ");
                    int pagamento = leia.nextInt();

                    if (pagamento == 1) {
                        System.out.print("Valor recebido: R$%.2f");
                        double valor = leia.nextDouble();

                        if (valor < valorAtual) {
                            System.out.printf("\nValor insuficiente está faltando R$%.2fn", (valorAtual - valor));

                        } else {
                            if (valor > valorAtual) {
                                System.out.printf("Troco: R$%.2fn", (valor - valorAtual));
                            }
                            pagamentoAprovado = true;
                        }

                    } else if (pagamento == 2) {

                        int opcao;

                        while (true) {
                            System.out.println("1 - Débito\n2 - Crédito");
                            System.out.print("\nEscola a opção: ");
                            opcao = leia.nextInt();
                            if (opcao == 1 || opcao == 2)
                                break;
                        }
                        while (true) {
                            if (valorAtual >= 100) {
                                System.out.println("\nDeseja parcela?");
                                System.out.println("\n01 - Sim");
                                System.out.println("02 - Não");
                                System.out.println("03 - Cancelar");

                                System.out.print("\nEscola a opção: ");
                                int opcao05 = 0;
                                opcao05 = leia.nextInt();

                                if (opcao05 == 1) {
                                    System.out.print("Digite a quantidade de parcelas: ");
                                    int parcela = leia.nextInt();
                                    if (parcela <= 10) {
                                        for (int i = 1; i <= parcela; i++) {
                                            double parcelado = valorAtual / parcela;
                                            System.out.printf("Parcela %d Valor R$%.2f", i, parcelado);
                                        }
                                        System.out.printf("Valor total R$%.2f", valorAtual);
                                    } else {
                                        System.out.println("\nMaximo 10x");
                                        continue;
                                    }
                                }
                            }
                            System.out.println("\nCompra aprovada? 1-Sim 2-Não");
                            System.out.print("\nEscola a opção: ");
                            opcao = leia.nextInt();

                            if (opcao == 1) {
                                pagamentoAprovado = true;
                                break;
                            } else if (opcao == 2) {
                                System.out.println("Pagamento recusado");
                                break;
                            }
                        }

                    } else if (pagamento == 3) {
                        ImageIcon icone = new ImageIcon(
                                getClass().getResource("/Images/qrcode.jpeg")
                        );

                        JFrame janela = new JFrame();
                        janela.setAlwaysOnTop(true);
                        janela.setLocationRelativeTo(null);

                        JOptionPane.showMessageDialog(
                                janela, null, "",
                                JOptionPane.PLAIN_MESSAGE,
                                icone
                        );

                        janela.dispose();

                        System.out.println("Leia o QRCODE");

                        while (true) {
                            System.out.println("Compra aprovada? 1-Sim 2-Não");
                            int opcao = leia.nextInt();

                            if (opcao == 1) {
                                pagamentoAprovado = true;
                                break;
                            } else if (opcao == 2) {
                                System.out.println("Pagamento recusado");
                                break;
                            }
                        }

                    } else if (pagamento == 4) {
                        System.out.println("Compra cancelada");
                        break;
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
                Notafiscal nota = new Notafiscal();
                nota.gerarNota(produtoEncontrado.getNome(), quantidade, valorAtual);

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
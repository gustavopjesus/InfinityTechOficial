package SistemaGeral.Dominio;
import java.io.*;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.JFrame;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;


public class Venda implements Serializable {
    private static final long serialVersionUID = 1L;

    private String nomeProdutoVendido;
    private double valorDoDoProdutoVendido;
    private int quantidadeProdutoVendido;

    public static void salvarVendas(ArrayList<Venda> relatorioVendas) {
        try (ObjectOutputStream oos = new ObjectOutputStream(
                new FileOutputStream("vendas.dat"))) {
            oos.writeObject(relatorioVendas);
        } catch (IOException e) {
            System.out.println("Erro ao salvar vendas: " + e.getMessage());
        }
    }

    public static ArrayList<Venda> carregarVendas() {
        File arquivo = new File("vendas.dat");
        if (!arquivo.exists()) {
            return new ArrayList<>();
        }
        try (ObjectInputStream ois = new ObjectInputStream(
                new FileInputStream("vendas.dat"))) {
            return (ArrayList<Venda>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            return new ArrayList<>();
        }
    }

    public void vendaProduto(ArrayList<Produto> lista, ArrayList<Venda> relatorioVendas, ArrayList<Cliente> clientes) {
        Scanner leia = new Scanner(System.in);
        String cpfNota = "Não informado";
        boolean cpfContinua = true;

        while (cpfContinua) {
            System.out.println("\n╔══════════════════════════╗");
            System.out.println("║      CPF NA NOTA?        ║");
            System.out.println("╠══════════════════════════╣");
            System.out.println("║  01 - Sim                ║");
            System.out.println("║  02 - Não                ║");
            System.out.println("╚══════════════════════════╝");
            System.out.print("\n   Escolha a opção: ");
            String opcnota = leia.nextLine();

            if (opcnota.equals("01") || opcnota.equals("1")) {
                System.out.print("\n   Digite o CPF do cliente: ");
                String cpfCliente = leia.nextLine();

                if (cpfCliente.length() != 11) {
                    System.out.println("\n   CPF inválido, tente novamente");
                    continue;
                }

                Cliente cliente = new Cliente(clientes);
                boolean cpfEncontrado = false;

                for (Cliente cli : clientes) {
                    if (cli.getCpf().equals(cpfCliente)) {
                        cpfEncontrado = true;
                        System.out.println("\n   Cliente encontrado!\n");
                        System.out.println("   Cliente: " + cli.getNome() + "\n   CPF: " + cli.getCpf());
                        cpfNota = cli.getCpf();
                        break;
                    }
                }

                if (!cpfEncontrado) {
                    System.out.println("\n   Cliente não cadastrado!\n");

                    cliente.cadastrarCliente(leia, clientes);

                    cpfNota = cliente.getCpf();
                }

                cpfContinua = false;

            } else if (opcnota.equals("02") || opcnota.equals("2")) {
                cpfContinua = false;
            } else {
                System.out.println("\n   Opção Inválida");
            }
        }

        System.out.println("\n   Realizar venda");

        boolean control = true;
        while (control) {

            boolean pagamentoAprovado = false;
            Produto produtoEncontrado = null;

            System.out.println("\n╔══════════════════════════╗");
            System.out.println("║      BUSCAR PRODUTO      ║");
            System.out.println("╠══════════════════════════╣");
            System.out.println("║  01 - Nome               ║");
            System.out.println("║  02 - ID                 ║");
            System.out.println("║  03 - Voltar             ║");
            System.out.println("╚══════════════════════════╝");
            System.out.print("\n   Escolha a opção: ");
            String buscaProduto = leia.nextLine().trim();

            if (buscaProduto.equals("1")) {
                System.out.print("\n   Digite o nome do produto: ");
                String nomeBuscado = leia.nextLine();

                for (Produto produto : lista) {
                    if (produto.getNome().equals(nomeBuscado)) {
                        produtoEncontrado = produto;
                        break;
                    }
                }
            } else if (buscaProduto.equals("2")) {
                System.out.print("\n   Digite o ID do produto: ");
                String entradaId = leia.nextLine().trim();

                if (!entradaId.matches("\\d+")) {
                    System.out.println("\n   Digite apenas números!");
                    continue;
                }

                int idBuscado = Integer.parseInt(entradaId);
                for (Produto produto : lista) {
                    if (produto.getId() == idBuscado) {
                        produtoEncontrado = produto;
                        break;
                    }
                }
            } else if (buscaProduto.equals("3")) {
                break;
            } else {
                System.out.println("\n   Opção inválida");
                continue;
            }
            if (produtoEncontrado != null) {

                System.out.println("\n   Produto encontrado!\n");
                produtoEncontrado.imprimeProduto();

                int quantidade;

                while (true) {
                    System.out.println("\n╔══════════════════════════╗");
                    System.out.println("║       QUANTIDADE         ║");
                    System.out.println("╠══════════════════════════╣");
                    System.out.println("║  01 - Escolher           ║");
                    System.out.println("║  02 - Cancelar venda     ║");
                    System.out.println("╚══════════════════════════╝");
                    System.out.print("\n   Escolha a opção: ");

                    String op = leia.nextLine().trim();

                    if (op.equals("1")) {
                        System.out.print("\n   Digite a quantidade: ");
                        String qntStr = leia.nextLine().trim();

                        if (!qntStr.matches("\\d+")) {
                            System.out.println("\n   Digite apenas números!");
                            continue;
                        }

                        quantidade = Integer.parseInt(qntStr);

                        if (produtoEncontrado.getQuantidade() <= 0) {
                            System.out.println("\n   Estoque zerado");
                            return;
                        } else if (quantidade <= 0) {
                            System.out.println("\n   Quantidade inválida");
                        } else if (produtoEncontrado.getQuantidade() < quantidade) {
                            System.out.println("\n   Estoque insuficiente");
                            System.out.println("\n   Estoque: " + produtoEncontrado.getQuantidade());
                        } else {
                            System.out.println("\nProduto: " + produtoEncontrado.getNome());
                            System.out.printf("Total R$%.2f\n", (produtoEncontrado.getValor() * quantidade));
                            break;
                        }
                    } else if (op.equals("2")) {
                        System.out.println("\n   Venda cancelada!");
                        return;
                    } else {
                        System.out.println("\n   Opção Inválida");
                    }
                }
                double valorAtual = produtoEncontrado.getValor() * quantidade;

                while (!pagamentoAprovado) {

                    System.out.println("\n╔══════════════════════════╗");
                    System.out.println("║     FORMA DE PAGAMENTO   ║");
                    System.out.println("╠══════════════════════════╣");
                    System.out.println("║  01 - Dinheiro           ║");
                    System.out.println("║  02 - Cartão             ║");
                    System.out.println("║  03 - Pix                ║");
                    System.out.println("║  04 - Cancelar           ║");
                    System.out.println("╚══════════════════════════╝");

                    System.out.print("\n   Escolha a opção: ");
                    int pagamento = leia.nextInt();

                    if (pagamento == 1) {
                        System.out.print("\n   Digite o valor recebido R$ ");
                        double valor = leia.nextDouble();

                        if (valor < valorAtual) {
                            System.out.printf("\n   Valor insuficiente está faltando R$%.2f", (valorAtual - valor));

                        } else {
                            if (valor > valorAtual) {
                                System.out.print("\n   Troco R$" + (valor - valorAtual));
                            }
                            pagamentoAprovado = true;
                        }

                    } else if (pagamento == 2) {
                        leia.nextLine();

                        boolean controlePagamento = true;

                        while (controlePagamento) {

                            System.out.println("\n╔══════════════════════════╗");
                            System.out.println("║     FORMA DE PAGAMENTO   ║");
                            System.out.println("╠══════════════════════════╣");
                            System.out.println("║  01 - Crédito            ║");
                            System.out.println("║  02 - Débito             ║");
                            System.out.println("║  03 - Cancelar           ║");
                            System.out.println("╚══════════════════════════╝");
                            System.out.print("\n   Escolha a opção: ");
                            String opcaoPagamento = leia.nextLine().trim();

                            if (opcaoPagamento.equals("1")) {

                                if (valorAtual >= 100) {
                                    System.out.println("\n╔══════════════════════════╗");
                                    System.out.println("║       PARCELAMENTO       ║");
                                    System.out.println("╠══════════════════════════╣");
                                    System.out.println("║  01 - Parcelar           ║");
                                    System.out.println("║  02 - À vista            ║");
                                    System.out.println("╚══════════════════════════╝");
                                    System.out.print("\n   Escolha a opção: ");
                                    String opcaoParcela = leia.nextLine().trim();

                                    if (opcaoParcela.equals("1")) {
                                        System.out.print("\n   Digite a quantidade de parcelas (máx. 8): ");
                                        String entradaParcela = leia.nextLine().trim();

                                        if (!entradaParcela.matches("\\d+")) {
                                            System.out.println("\n   Digite apenas números!");
                                            continue;
                                        }

                                        int parcela = Integer.parseInt(entradaParcela);

                                        if (parcela <= 0) {
                                            System.out.println("\n   Número de parcelas inválido!");
                                            continue;
                                        } else if (parcela > 8) {
                                            System.out.println("\n   Máximo 8 parcelas!");
                                            continue;
                                        }

                                        System.out.println();
                                        for (int i = 1; i <= parcela; i++) {
                                            double parcelado = valorAtual / parcela;
                                            System.out.printf("Parcela %d  -  R$%.2f%n", i, parcelado);
                                        }
                                        System.out.printf("%nTotal: R$%.2f%n", valorAtual);

                                    } else if (!opcaoParcela.equals("2")) {
                                        System.out.println("\n   Opção inválida!");
                                        continue;
                                    }
                                }

                            } else if (opcaoPagamento.equals("2")) {
                                System.out.printf("%n Valor: R$%.2f%n", valorAtual);

                            } else if (opcaoPagamento.equals("3")) {
                                System.out.println("\n   Compra cancelada.");
                                break;

                            } else {
                                System.out.println("\n   Opção inválida!");
                                continue;
                            }

                            System.out.println("\n╔══════════════════════════╗");
                            System.out.println("║     COMPRA APROVADA?     ║");
                            System.out.println("╠══════════════════════════╣");
                            System.out.println("║  01 - Sim                ║");
                            System.out.println("║  02 - Não                ║");
                            System.out.println("╚══════════════════════════╝");
                            System.out.print("\n   Escolha a opção: ");
                            String opcaoAprovado = leia.nextLine().trim();

                            if (opcaoAprovado.equals("1")) {
                                pagamentoAprovado = true;
                                System.out.println("\n   Pagamento aprovado!");
                                break;
                            } else if (opcaoAprovado.equals("2")) {
                                System.out.println("\n   Pagamento recusado.");
                                break;
                            } else {
                                System.out.println("\n   Opção inválida!");
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
                            System.out.println("\n╔══════════════════════════╗");
                            System.out.println("║     COMPRA APROVADA?     ║");
                            System.out.println("╠══════════════════════════╣");
                            System.out.println("║  01 - Sim                ║");
                            System.out.println("║  02 - Não                ║");
                            System.out.println("╚══════════════════════════╝");
                            System.out.print("\n   Escolha a opção: ");
                            String opcaoPix = leia.nextLine().trim();

                            if (opcaoPix.equals("1")){
                                pagamentoAprovado = true;
                                break;
                            } else if (opcaoPix.equals("2")) {
                                System.out.println("\n   Pagamento recusado");
                                break;
                            }else {
                                System.out.println("\n   Opção inválida!");
                            }
                        }
                    } else if (pagamento == 4) {
                        System.out.println("\n   Compra cancelada");
                        break;
                    }
                }
                if (!pagamentoAprovado) {
                    continue;
                }
                produtoEncontrado.setQuantidade(produtoEncontrado.getQuantidade() - quantidade);

                System.out.println("\n   Venda finalizada!");

                Venda relatorio = new Venda();
                relatorio.setNomeProdutoVendido(produtoEncontrado.getNome());
                relatorio.setQuantidadeProdutoVendido(quantidade);
                relatorio.setValorDoDoProdutoVendido(valorAtual);

                relatorioVendas.add(relatorio);

                Notafiscal nota = new Notafiscal();
                nota.gerarNota(produtoEncontrado.getNome(), quantidade, valorAtual, cpfNota);

                control = false;

            } else {
                System.out.println("\n   Produto não encontrado");
            }
        }
    }

    public String getNomeProdutoVendido () {
        return nomeProdutoVendido;
    }

    public void setNomeProdutoVendido (String nomeProdutoVendido){
        this.nomeProdutoVendido = nomeProdutoVendido;
    }

    public double getValorDoDoProdutoVendido () {
        return valorDoDoProdutoVendido;
    }

    public void setValorDoDoProdutoVendido ( double valorDoDoProdutoVendido){
        this.valorDoDoProdutoVendido = valorDoDoProdutoVendido;
    }

    public int getQuantidadeProdutoVendido () {
        return quantidadeProdutoVendido;
    }

    public void setQuantidadeProdutoVendido ( int quantidadeProdutoVendido){
        this.quantidadeProdutoVendido = quantidadeProdutoVendido;
    }
}

package SistemaGeral.Dominio;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Produto implements Serializable {
    private static final long serialVersionUID = 1L;
    private String nome;
    private int id;
    private double valor;
    private int quantidade;

    public static void salvarProdutos(ArrayList<Produto> lista) {
        try (ObjectOutputStream oos = new ObjectOutputStream(
                new FileOutputStream("produtos.dat"))) {
            oos.writeObject(lista);
        } catch (IOException e) {
            System.out.println("Erro ao salvar produtos: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    public static ArrayList<Produto> carregarProdutos() {
        File arquivo = new File("produtos.dat");
        if (!arquivo.exists()) {
            return new ArrayList<>();
        }
        try (ObjectInputStream ois = new ObjectInputStream(
                new FileInputStream("produtos.dat"))) {
            ArrayList<Produto> lista = (ArrayList<Produto>) ois.readObject();
            System.out.println(lista.size() + " produto(s) carregado(s).");
            return lista;
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Erro ao carregar produtos: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    public void imprimeProduto() {
        System.out.println("================================");
        System.out.println("    Nome:" + this.nome);
        System.out.println("    ID:" + this.id);
        System.out.printf("    Valor: R$%.2f ", this.valor);
        System.out.println("\n    Quantidade: " + this.quantidade);
        System.out.println("================================");
    }

    public void cadastrarPoduto(ArrayList<Produto> lista, Scanner leia, ArrayList<String> relatorioHistorico) {
        Produto produtos = new Produto();

        System.out.println("\nCADASTRO DE PRODUTO\n");

        int etapa = 0;

        while (true) {

            if (etapa == 0) {
                System.out.print("Digite o nome do produto: ");
                String nome = leia.nextLine().trim();

                if (nome.length() == 0) {
                    System.out.println("Nome não pode ser vazio");
                    continue;
                }

                boolean soDigitos = true;
                for (char c : nome.toCharArray()) {
                    if (!Character.isDigit(c)) {
                        soDigitos = false;
                        break;
                    }
                }
                if (soDigitos) {
                    System.out.println("Nome inválido");
                    continue;
                }

                boolean jaExiste = false;
                int maiorId = 0;
                for (Produto p : lista) {
                    if (p.getNome().equalsIgnoreCase(nome)) {
                        jaExiste = true;
                    }
                    if (p.getId() > maiorId) {
                        maiorId = p.getId();
                    }
                }

                if (jaExiste) {
                    System.out.println("Produto já cadastrado");
                    continue;
                }

                produtos.setNome(nome);
                produtos.setId(maiorId + 1);
                etapa++;

            } else if (etapa == 1) {
                System.out.print("Digite o valor: ");
                String valor = leia.nextLine().trim().replace(",", ".");

                if (valor.length() == 0 || !valor.matches("\\d+(\\.\\d+)?")) {
                    System.out.println("Digite apenas números!");
                    continue;
                }

                produtos.setValor(Double.parseDouble(valor));
                etapa++;

            } else if (etapa == 2) {
                System.out.print("Digite a quantidade: ");
                String quant = leia.nextLine().trim();

                boolean soNumeros = true;
                for (char c : quant.toCharArray()) {
                    if (!Character.isDigit(c)) {
                        soNumeros = false;
                        break;
                    }
                }

                if (quant.length() == 0 || !soNumeros) {
                    System.out.println("Digite apenas números!");
                    continue;
                }

                int quantidade = Integer.parseInt(quant);

                if (quantidade <= 0) {
                    System.out.println("Quantidade não pode ser zero ou negativa!");
                    continue;
                }

                produtos.setQuantidade(quantidade);
                break;
            }
        }

        lista.add(produtos);

        Historico.salvar(
                "      | Nome: " + produtos.getNome()
                        + " | ID: " + produtos.getId()
                        + " | Valor: R$" + produtos.getValor()
                        + " | Quantidade: " + produtos.getQuantidade()
        );

        System.out.println("\nProduto cadastrado com sucesso");
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public String getNome() {
        return nome;
    }

    public int getId() {
        return id;
    }

    public double getValor() {
        return valor;
    }

    public int getQuantidade() {
        return quantidade;
    }

}
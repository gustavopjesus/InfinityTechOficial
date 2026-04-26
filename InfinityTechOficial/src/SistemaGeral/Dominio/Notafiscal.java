package SistemaGeral.Dominio;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.font.Standard14Fonts;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.text.NumberFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class Notafiscal {

    public void gerarNota(String nomeProduto, int quantidade, double valorTotal) {

        try {
            PDDocument documento = new PDDocument();
            PDPage pagina = new PDPage();
            documento.addPage(pagina);

            PDPageContentStream conteudo = new PDPageContentStream(documento, pagina);

            PDType1Font titulo = new PDType1Font(Standard14Fonts.FontName.HELVETICA_BOLD);
            PDType1Font texto = new PDType1Font(Standard14Fonts.FontName.HELVETICA);

            LocalDateTime agora = LocalDateTime.now();
            DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

            NumberFormat nf = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));

            double valorUnitario = valorTotal / quantidade;
            int numeroNota = (int) (Math.random() * 10000);

            conteudo.beginText();
            conteudo.setFont(titulo, 20);
            conteudo.newLineAtOffset(180, 750);
            conteudo.showText("INFINITY TECH");
            conteudo.endText();

            conteudo.beginText();
            conteudo.setFont(texto, 12);
            conteudo.newLineAtOffset(210, 730);
            conteudo.showText("NOTA FISCAL");
            conteudo.endText();

            conteudo.beginText();
            conteudo.setFont(texto, 10);
            conteudo.newLineAtOffset(50, 700);
            conteudo.showText("Data/Hora: " + agora.format(formato));
            conteudo.endText();

            conteudo.beginText();
            conteudo.setFont(texto, 10);
            conteudo.newLineAtOffset(450, 700);
            conteudo.showText("Nº: " + numeroNota);
            conteudo.endText();

            conteudo.moveTo(50, 690);
            conteudo.lineTo(550, 690);
            conteudo.stroke();

            conteudo.beginText();
            conteudo.setFont(titulo, 12);
            conteudo.newLineAtOffset(50, 660);
            conteudo.showText("Produto");
            conteudo.newLineAtOffset(200, 0);
            conteudo.showText("Qtd");
            conteudo.newLineAtOffset(80, 0);
            conteudo.showText("Unitário");
            conteudo.newLineAtOffset(100, 0);
            conteudo.showText("Total");
            conteudo.endText();

            conteudo.moveTo(50, 650);
            conteudo.lineTo(550, 650);
            conteudo.stroke();

            conteudo.beginText();
            conteudo.setFont(texto, 12);
            conteudo.newLineAtOffset(50, 620);
            conteudo.showText(nomeProduto.toUpperCase());
            conteudo.newLineAtOffset(200, 0);
            conteudo.showText(String.valueOf(quantidade));
            conteudo.newLineAtOffset(80, 0);
            conteudo.showText(nf.format(valorUnitario));
            conteudo.newLineAtOffset(100, 0);
            conteudo.showText(nf.format(valorTotal));
            conteudo.endText();

            conteudo.moveTo(50, 580);
            conteudo.lineTo(550, 580);
            conteudo.stroke();

            conteudo.beginText();
            conteudo.setFont(titulo, 14);
            conteudo.newLineAtOffset(350, 550);
            conteudo.showText("TOTAL: " + nf.format(valorTotal));
            conteudo.endText();

            conteudo.beginText();
            conteudo.setFont(texto, 12);
            conteudo.newLineAtOffset(170, 500);
            conteudo.showText("Obrigado pela preferência!");
            conteudo.endText();

            conteudo.close();

            String nomeArquivo = "nota_fiscal.pdf";
            documento.save(nomeArquivo);
            documento.close();

            File arquivo = new File(nomeArquivo);

            if (Desktop.isDesktopSupported()) {
                Desktop.getDesktop().open(arquivo);
            }

            System.out.println("Nota fiscal gerada com sucesso!");

        } catch (IOException e) {
            System.out.println("Erro ao gerar nota fiscal: " + e.getMessage());
        }
    }
}
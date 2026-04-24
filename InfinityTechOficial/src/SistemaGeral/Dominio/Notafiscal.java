package SistemaGeral.Dominio;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.font.Standard14Fonts;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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

            conteudo.beginText();
            conteudo.setFont(titulo, 18);
            conteudo.newLineAtOffset(220, 750);
            conteudo.showText("NOTA FISCAL - INFINITY TECH");
            conteudo.endText();

            conteudo.beginText();
            conteudo.setFont(texto, 12);
            conteudo.newLineAtOffset(80, 700);
            conteudo.showText("Data/Hora: " + agora.format(formato));
            conteudo.newLineAtOffset(0, -30);
            conteudo.showText("Produto: " + nomeProduto);
            conteudo.newLineAtOffset(0, -30);
            conteudo.showText("Quantidade: " + quantidade);
            conteudo.newLineAtOffset(0, -30);
            conteudo.showText("Valor total: R$ " + valorTotal);
            conteudo.newLineAtOffset(0, -40);
            conteudo.showText("Obrigado pela compra!");
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
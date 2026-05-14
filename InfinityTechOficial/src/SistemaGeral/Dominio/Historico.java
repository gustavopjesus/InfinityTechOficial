package SistemaGeral.Dominio;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;

public class Historico {

    public static void salvar(String acao) {
        try (FileWriter writer = new FileWriter("historico.txt", true)) {
            writer.write(LocalDateTime.now() + " - " + acao + "\n");
        } catch (IOException e) {
            System.out.println("Erro ao salvar histórico.");
        }
    }
}

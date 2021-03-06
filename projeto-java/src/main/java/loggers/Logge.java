package loggers;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.github.britooo.looca.api.core.Looca;
import org.codehaus.plexus.util.xml.pull.XmlPullParserException;

public class Logge {

    private String dataArquivo;
    private String empresaArquivo;
    private String nomeArquivo;
    private File diretorio;
    private File arquivo;

    public Logge() {
        this.empresaArquivo = "Request - ";
        this.dataArquivo = DateTimeFormatter.ofPattern("dd.MM.yyy").format(LocalDateTime.now());
        this.nomeArquivo = String.format("../logs/%s%s.txt", empresaArquivo, dataArquivo);

        this.diretorio = new File("../logs");
        diretorio.mkdir();
        this.arquivo = new File(nomeArquivo);

    }

    public void guardarLog(String textoLog) {
        try (
                FileWriter criadorDeArquivos = new FileWriter(arquivo, true);
                BufferedWriter escrever = new BufferedWriter(criadorDeArquivos);
                PrintWriter escritorDeArquivos = new PrintWriter(escrever);) {
            escritorDeArquivos.append(textoLog);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void startApp() throws FileNotFoundException, IOException, XmlPullParserException {
        String dataLog = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(LocalDateTime.now());

        Looca looca = new Looca();

        try (
                FileWriter criadorDeArquivos = new FileWriter(arquivo, true);
                BufferedWriter escrever = new BufferedWriter(criadorDeArquivos);
                PrintWriter escritorDeArquivos = new PrintWriter(escrever);) {
            escritorDeArquivos.append(String.format(
                    "==========================================================================\n"
                    + "             Aplica????o REQUEST iniciada as: %s\n"
                    + "\n"
                    + "Sistema operacional: %s\n"
                    + "Bits do sistema: %s bits\n"
                    + "Permiss??es: %s\n"
                    + "Vers??o da aplica????o:\n"
                    + "==========================================================================\n\n\n", dataLog, looca.getSistema().getSistemaOperacional(), looca.getSistema().getArquitetura(), looca.getSistema().getPermissao()
            ));
        } catch (Exception e) {
            System.out.println(e);;
        }
    }

    public void closeApp() {

        String dataLog = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(LocalDateTime.now());

        Looca looca = new Looca();

        String textoLog = String.format(
                "==========================================================================\n"
                        + "             Aplica????o REQUEST finalizada as: %s\n"
                        + "\n"
                        + "Sistema operacional: %s\n"
                        + "Bits do sistema: %s bits\n"
                        + "Permiss??es: %s\n"
                        + "Vers??o da aplica????o: 2.0\n"
                        + "Modelo CPU: %s \n"
                        + "Total Ram: %s \n"
                        + "==========================================================================\n\n\n", dataLog, looca.getSistema().getSistemaOperacional(), looca.getSistema().getArquitetura(), looca.getSistema().getPermissao(), looca.getProcessador().getFabricante(), looca.getMemoria().getTotal());
        try (
                FileWriter criadorDeArquivos = new FileWriter(arquivo, true);
                BufferedWriter escrever = new BufferedWriter(criadorDeArquivos);
                PrintWriter escritorDeArquivos = new PrintWriter(escrever);) {
            escritorDeArquivos.append(textoLog);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

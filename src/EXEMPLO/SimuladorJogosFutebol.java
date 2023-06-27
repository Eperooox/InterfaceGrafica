package EXEMPLO;
import javax.swing.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

 public class SimuladorJogosFutebol
{
    public static void main(String[] args)
    {
        String arquivoClubesMG_RJ = "clubes_mg_rj.txt";
        String arquivoClubesSP_RS = "clubes_sp_rs.txt";
        String arquivoConfrontos = "confrontos.txt";

        List<String> clubesMG_RJ = lerArquivoClubes(arquivoClubesMG_RJ);
        List<String> clubesSP_RS = lerArquivoClubes(arquivoClubesSP_RS);

        List<String> confrontos = gerarConfrontos(clubesMG_RJ, clubesSP_RS);

        escreverArquivoConfrontos(arquivoConfrontos, confrontos);

        System.out.println("Jogos do Campeonato: " + arquivoConfrontos );
    }

    private static String gerarHorarioJogo()
    {
        Random tempo = new Random();
        int hora = tempo.nextInt(24);
        return String.format("%02d:%02d", hora);
    }

    private static void escreverArquivoConfrontos(String nomeArquivo, List<String> confrontos)
    {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(nomeArquivo)))
        {
            for (String confronto : confrontos)
            {
                bw.write(confronto);
                bw.newLine();
            }
        } catch (IOException e)
        {
            System.err.println("Erro: " + nomeArquivo);
            e.printStackTrace();
        }
    }
    private static List<String> lerArquivoClubes(String nomeArquivo)
    {
        List<String> clubes = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(nomeArquivo)))
        {
            String linha;
            while ((linha = br.readLine()) != null)
            {
                if (!linha.isEmpty()) {
                    clubes.add(linha.trim());
                }
            }
        }
        catch (IOException e)
        {
            System.err.println("Erro ao ler o arquivo: " + nomeArquivo);
            e.printStackTrace();
        }

        return clubes;
    }
    private static List<String> gerarConfrontos(List<String> clubesA, List<String> clubesB)
    {
        List<String> confrontos = new ArrayList<>();
        for (String clubeA : clubesA)
        {
            for (String clubeB : clubesB)
            {
                String confronto = clubeA + " x " + clubeB + " " + gerarHorarioJogo();
                confrontos.add(confronto);
            }
        }

        return confrontos;
    }

}
}
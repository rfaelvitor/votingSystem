package homeWork;

import java.util.Scanner;
import java.util.Arrays;
import java.util.Comparator;


public class project {

    public static void bold(String text){
        String bold = "\033[1m";
        String reset = "\033[0m";
        System.out.println(bold + text + reset);
    }

    public static void greenColor(String text){
        String green = "\u001B[32m";
        String reset = "\u001B[0m";
        System.out.println(green + text + reset);
    }

    public static void redColor(String text){
        String red = "\u001B[31m";
        String reset = "\u001B[0m";
        System.out.println(red + text + reset);
    }

    public static void yellowColor(String text){
        String yellow = "\u001B[33m";
        String reset = "\u001B[0m";
        System.out.println(yellow + text + reset);
    }

    public static void blueColor(String text){
        String blue = "\u001B[34m";
        String reset = "\u001B[0m";
        System.out.println(blue + text + reset);
    }

    public static void magentaColor(String text){
        String magenta = "\u001B[35m";
        String reset = "\u001B[0m";
        System.out.println(magenta + text + reset);
    }



    public static void main(String[] args) {
        Object[][] matrizCandidatosEVotos = null;
        Scanner read = new Scanner(System.in);

        int acao = 0;
        while (acao != 5) {
            System.out.println("\nConfira suas opções abaixo:");

            System.out.println("\n[1] Cadastrar os candidados a síndico");
            System.out.println("[2] Realizar a votação");
            System.out.println("[3] Identificar o vencedor");
            System.out.println("[4] Mostrar a lista de candidatos com votos recebidos");
            System.out.println("[5] Encerrar o programa");


            System.out.print("\nEscolha uma ação: ");

            acao = read.nextInt();


            if (acao == 1) {

                System.out.print("Digite a quantidade de candidatos: ");
                int qtdeCandidatos = read.nextInt();
                read.nextLine();

                matrizCandidatosEVotos = new Object[qtdeCandidatos][3];

                for (int i = 0; i < qtdeCandidatos; i++) {
                    System.out.print("Digite o nome do " + (i + 1) + "º candidato: ");
                    matrizCandidatosEVotos[i][0] = read.nextLine();
                    matrizCandidatosEVotos[i][1] = 0;
                }
                greenColor("Candidatos cadastrado com sucesso!");
                continue;

            } else if (acao == 2){
                if(matrizCandidatosEVotos != null) {
                    System.out.println("Candidatos disponíveis na eleição: ");
                    for (int i = 0; i < matrizCandidatosEVotos.length; i++) {
                        bold("\n[" + (i + 1) + "] " + matrizCandidatosEVotos[i][0]);
                    }

                    int votacao;
                    yellowColor("\n\nAtenção: Digite 0 para encerrar a votação.");
                    do {
                        System.out.print("Eleitor, vote inserindo o número do candidato desejado: ");
                        votacao = read.nextInt();
                        for (int i = 0; i < matrizCandidatosEVotos.length; i++) {
                            if (votacao - 1 == i) {
                                matrizCandidatosEVotos[i][1] = (int) matrizCandidatosEVotos[i][1] + 1;
                            }
                        }
                    } while (votacao != 0);
                }else{
                    redColor("Nenhum candidato cadastrado ainda.");
                }
                continue;
            } else if (acao == 3) {
                if (matrizCandidatosEVotos != null) {
                    int indexVencedor = 0;
                    int maiorVoto = (int) matrizCandidatosEVotos[0][1];

                    for (int i = 1; i < matrizCandidatosEVotos.length; i++) {
                        int votosAtual = (int) matrizCandidatosEVotos[i][1];

                        if (votosAtual > maiorVoto) {
                            maiorVoto = votosAtual;
                            indexVencedor = i;
                        }
                    }
                    String vencedor = (String) matrizCandidatosEVotos[indexVencedor][0];
                    double porcentagem = (double) matrizCandidatosEVotos[indexVencedor][2];
                    blueColor("O vencedor é " + vencedor + " com " + maiorVoto + " votos (" + String.format("%.2f", porcentagem) + "%) recebidos!");
                }else{
                    redColor("Nenhum candidato cadastrado ainda.");
                }
                continue;
            } else if (acao == 4) {
                if (matrizCandidatosEVotos != null) {

                    Arrays.sort(matrizCandidatosEVotos, Comparator.comparingInt(o -> -(int) o[1]));

                    int totalVotos = 0;

                    //somando os votos
                    for (int i = 0; i < matrizCandidatosEVotos.length; i++){
                       totalVotos += (int) matrizCandidatosEVotos[i][1];
                    }
                    //calculando porcentagem
                    for(int i = 0; i < matrizCandidatosEVotos.length; i++){
                        if (totalVotos > 0){
                            double porcentagem = ((int) matrizCandidatosEVotos[i][1] / (double) totalVotos) * 100;
                            matrizCandidatosEVotos[i][2] = porcentagem;
                        }else{
                            matrizCandidatosEVotos[i][2] = 0.00;
                        }
                    }

                    for (int i = 0; i < matrizCandidatosEVotos.length; i++) {
                        bold(matrizCandidatosEVotos[i][0] + " - Votos: " + matrizCandidatosEVotos[i][1] + " - Porcentagem: " + String.format("%.2f", matrizCandidatosEVotos[i][2]) + "%");
                    }
                } else {
                    redColor("Nenhum candidato cadastrado ainda.");
                }
                continue;
            }
            magentaColor("\nPrograma encerrado. Obrigado por usar nosso sistema.");
        }
    }
}












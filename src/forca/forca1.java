package forca;

import java.util.*;

public class forca1 {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int opcao;

        do {
            System.out.println("Escolha um jogo:");
            System.out.println("1 - Jogo da Forca");
            System.out.println("2 - Sudoku");
            System.out.println("3 - Sair");
            System.out.print("Opção: ");

            opcao = scan.nextInt();

            switch (opcao) {
                case 1:
                    jogarForca();
                    break;
                case 2:
                    jogarSudoku();
                    break;
                case 3:
                    System.out.println("Saindo do programa. Até mais!");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
                    break;
            }
        } while (opcao != 3);
    }

    public static void jogarForca() {
        Character palavras[];
        palavras = palavras();
        Character determinarTent[] = new Character[palavras.length];
        tentativa(palavras, determinarTent);
        mostrar(determinarTent);
    }

    public static Character[] palavras() {
        String palavra;
        int w;
        Random rd = new Random();
        String nomes_palavras[] = {"abacaxi", "banana", "caju", "damasco", "espinafre"};
        w = rd.nextInt(5);
        palavra = nomes_palavras[w];
        Character[] letrasPalavra = new Character[palavra.length()];

        for (int i = 0; i < palavra.length(); i++) {
            letrasPalavra[i] = palavra.charAt(i);
        }

        return letrasPalavra;
    }

    public static void tentativa(Character palavra[], Character determinarTent[]) {
        int i = 0;
        String letra;
        Character l;
        Scanner scan = new Scanner(System.in);

        System.out.println("\n---Bem-vindo ao Jogo da Forca!---\n");

        int erros = 0;

        do {
            System.out.println((i + 1) + "ª tentativa: ");
            letra = scan.next();
            l = letra.toLowerCase().charAt(0);

            determinarTent = conferirLetra(l, palavra, determinarTent);

            mostrar(determinarTent);

            if (Arrays.asList(determinarTent).indexOf(null) < 0) {
                System.out.println("Vencedor!");
                break;
            }

            if (!Arrays.asList(determinarTent).contains(l)) {
                erros=erros+1;
                desenharForca(erros);
            }

            i=i+1;

        } while (erros < 6 && Arrays.asList(determinarTent).contains(null));

        if (erros == 6) {
            System.out.println("A palavra era: " + Arrays.toString(palavra));
        }
    }

    public static Character[] conferirLetra(Character letra, Character palavra[], Character determinarTent[]) {
        Boolean verdade = false;

        for (int i = 0; i < palavra.length; i++) {
            if (letra.equals(palavra[i])) {
                determinarTent[i] = letra;
                verdade = true;
            }
        }
        if (!verdade) {
            System.out.println("Ops, errou!");
        }

        return determinarTent;
    }

    public static void mostrar(Character determinarTent[]) {
        int i;
        for (i = 0; i < determinarTent.length; i++) {
            if (determinarTent[i] == null) {
                System.out.print(" __ ");
            } else {
                System.out.print(" " + determinarTent[i] + " ");
            }
        }
        System.out.println("\n");
    }

    public static void desenharForca(int partesBoneco) {
        System.out.println("Forca:");

        switch (partesBoneco) {
            case 0:
                System.out.println(" |    |");
                break;
            case 1:
                System.out.println("  ____");
                System.out.println(" |    |");
                System.out.println(" |    O");
                break;
            case 2:
                System.out.println("  ____");
                System.out.println(" |    |");
                System.out.println(" |    O");
                System.out.println(" |   /");
                break;
            case 3:
                System.out.println("  ____");
                System.out.println(" |    |");
                System.out.println(" |    O");
                System.out.println(" |   /|\\");
                break;
            case 4:
                System.out.println("  ____");
                System.out.println(" |    |");
                System.out.println(" |    O");
                System.out.println(" |   /|\\");
                System.out.println(" |   /");
                break;
            case 5:
                System.out.println("  ____");
                System.out.println(" |    |");
                System.out.println(" |    O");
                System.out.println(" |   /|\\");
                System.out.println(" |   / \\");
                break;
            default:
                break;
        }
    }

    public static void jogarSudoku() {
        int[][] tabuleiro = gerarSudoku();
        jogarSudoku(tabuleiro);
    }

    public static int[][] gerarSudoku() {
        int[][] tabuleiro = {
                {5, 3, 0, 0, 7, 0, 0, 0, 0},
                {6, 0, 0, 1, 9, 5, 0, 0, 0},
                {0, 9, 8, 0, 0, 0, 0, 6, 0},
                {8, 0, 0, 0, 6, 0, 0, 0, 3},
                {4, 0, 0, 8, 0, 3, 0, 0, 1},
                {7, 0, 0, 0, 2, 0, 0, 0, 6},
                {0, 6, 0, 0, 0, 0, 2, 8, 0},
                {0, 0, 0, 4, 1, 9, 0, 0, 5},
                {0, 0, 0, 0, 8, 0, 0, 7, 9}
        };
        return tabuleiro;
    }

    public static void jogarSudoku(int[][] tabuleiro) {
        Scanner scan = new Scanner(System.in);

        System.out.println("\n---Bem-vindo ao Sudoku!---\n");

        do {
            mostrarSudoku(tabuleiro);
            preencherEspacosVazios(tabuleiro, scan);
        } while (aindaHaEspacosVazios(tabuleiro));

        System.out.println("Parabéns! Você completou o Sudoku!");
    }

    public static void mostrarSudoku(int[][] tabuleiro) {
        System.out.println("Tabuleiro Sudoku:\n");
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.print(tabuleiro[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void preencherEspacosVazios(int[][] tabuleiro, Scanner scan) {
        System.out.println("Informe a linha, coluna e o valor (separados por espaço) para preencher o espaço vazio:");
        int linha = scan.nextInt();
        int coluna = scan.nextInt();
        int valor = scan.nextInt();

        if (linha >= 1 && linha <= 9 && coluna >= 1 && coluna <= 9 && valor >= 1 && valor <= 9 && tabuleiro[linha - 1][coluna - 1] == 0) {
            tabuleiro[linha - 1][coluna - 1] = valor;
        } else {
            System.out.println("Entrada inválida. Tente novamente.");
        }
    }

    public static boolean aindaHaEspacosVazios(int[][] tabuleiro) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (tabuleiro[i][j] == 0) {
                    return true;
                }
            }
        }
        return false;
    }
}

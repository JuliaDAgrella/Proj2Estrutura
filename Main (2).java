import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    static Arvore arvore = new Arvore();
    static boolean carregado = false;

    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        int opcao;

        do {
            System.out.println("\nMENU:");
            System.out.println("1. Carregar discurso");
            System.out.println("2. Contador de palavras");
            System.out.println("3. Buscar palavra");
            System.out.println("4. Exibir as palavras em ordem alfabética");
            System.out.println("5. Verificar sinais de depressão");
            System.out.println("6. Estatísticas sobre o texto");
            System.out.println("7. Sair");
            System.out.print("Escolha uma opção: ");
            opcao = teclado.nextInt();
            teclado.nextLine();

            switch (opcao) {
                case 1:
                    carregarDiscurso("Discurso.txt");
                    break;
                case 2:
                    if (!carregado) erroCarregamento(); else System.out.println("Total de palavras: " + arvore.contarPalavras());
                    break;
                case 3:
                    if (!carregado) erroCarregamento();
                    else {
                        System.out.print("Digite a palavra a buscar: ");
                        String palavra = teclado.nextLine().toLowerCase();
                        Palavra encontrada = arvore.buscar(palavra);
                        if (encontrada != null) {
                            System.out.println("Encontrada: " + encontrada.getOcorrencias() + " ocorrência(s)");
                        } else {
                            System.out.println("Palavra não encontrada no discurso.");
                        }
                    }
                    break;
                case 4:
                    if (!carregado) erroCarregamento(); else arvore.emOrdem();
                    break;
                case 5:
                    if (!carregado) erroCarregamento(); else verificarSinaisDepressao();
                    break;
                case 6:
                    if (!carregado) erroCarregamento(); else mostrarEstatisticas();
                    break;
                case 7:
                    System.out.println("Integrantes: Fulano, Ciclano, Beltrano");
                    System.out.println("Vídeo: https://link-do-video.com");
                    break;
                default:
                    System.out.println("Opção inválida.");
            }

        } while (opcao != 7);

        teclado.close();
    }

    private static void erroCarregamento() {
        System.out.println("Discurso ainda não carregado! Use a opção 1 primeiro.");
    }

    private static void carregarDiscurso(String nomeArquivo) {
        try {
            Scanner leitor = new Scanner(new File(nomeArquivo));
            while (leitor.hasNext()) {
                String palavra = leitor.next().toLowerCase().replaceAll("[^\\p{L}]", "");
                if (!palavra.isEmpty()) {
                    arvore.inserir(palavra);
                }
            }
            leitor.close();
            carregado = true;
            System.out.println("Discurso carregado com sucesso.");
        } catch (FileNotFoundException e) {
            System.out.println("Arquivo não encontrado: " + nomeArquivo);
        }
    }

    private static void verificarSinaisDepressao() {
        try {
            Scanner leitor = new Scanner(new File("PalavrasDepressao.txt"));
            int total = 0;
            System.out.println("Palavras encontradas:");
            while (leitor.hasNext()) {
                String palavra = leitor.nextLine().trim().toLowerCase();
                Palavra p = arvore.buscar(palavra);
                if (p != null) {
                    System.out.println("- " + p.getTexto() + " (" + p.getOcorrencias() + ")");
                    total++;
                }
            }
            leitor.close();
            System.out.println("Sinais de alerta identificados: " + total + " palavras");
        } catch (FileNotFoundException e) {
            System.out.println("Arquivo PalavrasDepressao.txt não encontrado.");
        }
    }

    private static void mostrarEstatisticas() {
        // Exemplo de estatísticas:
        System.out.println("Estatísticas:");
        // 1. Número de palavras distintas
        System.out.print("- Palavras distintas: ");
        java.util.ArrayList<Palavra> lista = new java.util.ArrayList<>();
        arvore.palavrasEmLista(lista);
        System.out.println(lista.size());

        // 2. Palavra mais frequente
        Palavra maisFrequente = null;
        for (Palavra p : lista) {
            if (maisFrequente == null || p.getOcorrencias() > maisFrequente.getOcorrencias()) {
                maisFrequente = p;
            }
        }

        if (maisFrequente != null) {
            System.out.println("- Palavra mais frequente: " + maisFrequente.getTexto() +
                    " (" + maisFrequente.getOcorrencias() + ")");
        }
    }
}

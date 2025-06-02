//Júlia DAgrella Araújo (RA:10426655")
//"Rafael Carvalho Cordeiro (RA: 10437533)")

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    static BinarySearchTree arvore = new BinarySearchTree();
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
            System.out.print("\nEscolha uma opção: ");
            opcao = teclado.nextInt();
            teclado.nextLine();

            switch (opcao) {
                case 1:
                    // Carrega o discurso a partir do arquivo Discurso.txt
                    carregarDiscurso("Discurso.txt");
                    break;
                case 2:
                    // Se o discurso não foi carregado mostra erro, caso contrário conta palavras totais
                    if (!carregado) erroCarregamento(); else System.out.println("\nTotal de palavras: " + arvore.contarPalavras());
                    break;
                case 3:
                    // Busca uma palavra na árvore e mostra quantidade de ocorrências
                    if (!carregado) erroCarregamento();
                    else {
                        System.out.print("\nDigite a palavra a buscar: ");
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
                    // Exibe as palavras em ordem alfabética e o número de vezes que apareceu
                    if (!carregado) erroCarregamento();
                    else {
                        System.out.println("\nPalavras em ordem alfabética:");
                        arvore.emOrdem();
                    }
                    break;
                case 5:
                    // Verifica e exibe sinais de depressão
                    if (!carregado) erroCarregamento(); else verificarSinaisDepressao();
                    break;
                case 6:
                    // Exibe estatísticas de número de palavras distintas e a palavra mais frequente
                    if (!carregado) erroCarregamento(); else mostrarEstatisticas();
                    break;
                case 7:
                    System.out.println("\nEncerrando programa...");
                    System.out.println("\nJúlia DAgrella Araújo (RA:10426655");
                    System.out.println("Rafael Carvalho Cordeiro (RA: 10437533)");
                    System.out.println("\nLink do vídeo: ");
                    break;
                default:
                    System.out.println("Opção inválida.");
            }

        } while (opcao != 7);

        teclado.close();
    }
    // Método para mostrar mensagem de erro caso o discurso não esteja carregado
    private static void erroCarregamento() {
        System.out.println("\nDiscurso ainda não carregado! Use a opção 1 primeiro.");
    }

    // Método que lê o arquivo texto, separa as palavras, e as insere na árvore binária
    private static void carregarDiscurso(String nomeArquivo) {
        try {
            Scanner leitor = new Scanner(new File(nomeArquivo));
            while (leitor.hasNext()) {
                // Lê palavra, converte para minúscula e remove caracteres não alfabéticos
                String palavra = leitor.next().toLowerCase().replaceAll("[^\\p{L}]", "");
                if (!palavra.isEmpty()) {
                // Insere a palavra na árvore
                    arvore.inserir(palavra);
                }
            }
            leitor.close();
            carregado = true;
            System.out.println("\nDiscurso carregado com sucesso.");
        } catch (FileNotFoundException e) {
            System.out.println("\nArquivo não encontrado: " + nomeArquivo);
        }
    }

    // Método que verifica sinais de depressão lendo um arquivo com palavras específicas
    private static void verificarSinaisDepressao() {
        try {
            Scanner leitor = new Scanner(new File("PalavrasDepressao.txt"));
            int total = 0;
            System.out.println("\nPalavras encontradas:");
            while (leitor.hasNext()) {
                String palavra = leitor.nextLine().trim().toLowerCase();
                Palavra p = arvore.buscar(palavra);
                if (p != null) {
                    // Exibe palavra encontrada e número de ocorrências no discurso
                    System.out.println("- " + p.getTexto() + " (" + p.getOcorrencias() + ")");
                    total++;
                }
            }
            leitor.close();
            System.out.println("\nSinais de alerta identificados: " + total + " palavras");
        } catch (FileNotFoundException e) {
            System.out.println("\nArquivo PalavrasDepressao.txt não encontrado.");
        }
    }

    // Método para mostrar estatísticas: número de palavras distintas e a palavra mais frequente
    private static void mostrarEstatisticas() {
        System.out.println("\nEstatísticas:");
        // Número de palavras distintas
        System.out.print("- Palavras distintas: ");
        java.util.ArrayList<Palavra> lista = new java.util.ArrayList<>();
        arvore.palavrasEmLista(lista);
        System.out.println(lista.size());

        // Palavra mais frequente
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

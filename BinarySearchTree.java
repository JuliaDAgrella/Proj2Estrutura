//Júlia DAgrella Araújo (RA:10426655")
//"Rafael Carvalho Cordeiro (RA: 10437533)")

import java.util.ArrayList;

public class BinarySearchTree {
    private Node raiz;

    // Insere uma palavra na árvore
    public void inserir(String texto) {
        raiz = inserirRec(raiz, texto.toLowerCase());
    }

    // Método recursivo que insere a palavra na posição correta da árvore
    private Node inserirRec(Node atual, String texto) {
        if (atual == null) {
            // Criar novo nó se chegar em posição vazia
            return new Node(new Palavra(texto));
        }

        // Compara a palavra a ser inserida com a palavra do nó atual para decidir o caminho
        int cmp = texto.compareTo(atual.palavra.getTexto());
        if (cmp < 0) {
            // Palavra menor: vai para a subárvore esquerda
            atual.esquerda = inserirRec(atual.esquerda, texto);
        } else if (cmp > 0) {
            // Palavra maior: vai para a subárvore direita
            atual.direita = inserirRec(atual.direita, texto);
        } else {
            // Palavra já existe: incrementa a contagem de ocorrências
            atual.palavra.incrementar();
        }
        return atual;
    }

    // Busca uma palavra na árvore e retorna o objeto Palavra correspondente
    public Palavra buscar(String texto) {
        return buscarRec(raiz, texto.toLowerCase());
    }

    // Método para buscar a palavra na árvore
    private Palavra buscarRec(Node atual, String texto) {
        if (atual == null) return null;
        int cmp = texto.compareTo(atual.palavra.getTexto());
        if (cmp < 0) return buscarRec(atual.esquerda, texto);
        else if (cmp > 0) return buscarRec(atual.direita, texto);
        else return atual.palavra;
    }

    // Exibe todas as palavras da árvore em ordem alfabética
    public void emOrdem() {
        emOrdem(raiz);
    }

    // Método que percorre a árvore em ordem para exibir as palavras
    private void emOrdem(Node no) {
        if (no != null) {
            emOrdem(no.esquerda);
            System.out.println( "- " + no.palavra.getTexto() + " (" + no.palavra.getOcorrencias() + ")");
            emOrdem(no.direita);
        }
    }

    // Conta o total de palavras no discurso
    public int contarPalavras() {
        return contarPalavras(raiz);
    }

    // Método recursivo que soma as ocorrências das palavras na árvore
    private int contarPalavras(Node no) {
        if (no == null) return 0;
        return no.palavra.getOcorrencias() + contarPalavras(no.esquerda) + contarPalavras(no.direita);
    }

    // Adiciona todas as palavras da árvore numa lista em ordem alfabética
    public void palavrasEmLista(ArrayList<Palavra> lista) {
        palavrasEmLista(raiz, lista);
    }

    // Método que percorre a árvore e adiciona as palavras na lista
    private void palavrasEmLista(Node no, ArrayList<Palavra> lista) {
        if (no != null) {
            palavrasEmLista(no.esquerda, lista);
            lista.add(no.palavra);
            palavrasEmLista(no.direita, lista);
        }
    }
}

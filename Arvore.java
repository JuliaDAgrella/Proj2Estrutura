import java.util.ArrayList;

public class Arvore {
    private No raiz;

    public void inserir(String texto) {
        raiz = inserirRec(raiz, texto.toLowerCase());
    }

    private No inserirRec(No atual, String texto) {
        if (atual == null) {
            return new No(new Palavra(texto));
        }

        int cmp = texto.compareTo(atual.palavra.getTexto());
        if (cmp < 0) {
            atual.esquerda = inserirRec(atual.esquerda, texto);
        } else if (cmp > 0) {
            atual.direita = inserirRec(atual.direita, texto);
        } else {
            atual.palavra.incrementar();
        }
        return atual;
    }

    public Palavra buscar(String texto) {
        return buscarRec(raiz, texto.toLowerCase());
    }

    private Palavra buscarRec(No atual, String texto) {
        if (atual == null) return null;
        int cmp = texto.compareTo(atual.palavra.getTexto());
        if (cmp < 0) return buscarRec(atual.esquerda, texto);
        else if (cmp > 0) return buscarRec(atual.direita, texto);
        else return atual.palavra;
    }

    public void emOrdem() {
        emOrdem(raiz);
    }

    private void emOrdem(No no) {
        if (no != null) {
            emOrdem(no.esquerda);
            System.out.println(no.palavra.getTexto() + " (" + no.palavra.getOcorrencias() + ")");
            emOrdem(no.direita);
        }
    }

    public int contarPalavras() {
        return contarPalavras(raiz);
    }

    private int contarPalavras(No no) {
        if (no == null) return 0;
        return no.palavra.getOcorrencias() + contarPalavras(no.esquerda) + contarPalavras(no.direita);
    }

    public void palavrasEmLista(ArrayList<Palavra> lista) {
        palavrasEmLista(raiz, lista);
    }

    private void palavrasEmLista(No no, ArrayList<Palavra> lista) {
        if (no != null) {
            palavrasEmLista(no.esquerda, lista);
            lista.add(no.palavra);
            palavrasEmLista(no.direita, lista);
        }
    }
}

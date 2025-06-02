public class No {
    Palavra palavra;
    No esquerda, direita;

    public No(Palavra palavra) {
        this.palavra = palavra;
        this.esquerda = null;
        this.direita = null;
    }
}

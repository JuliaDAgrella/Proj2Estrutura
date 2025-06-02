//Júlia DAgrella Araújo (RA:10426655")
//"Rafael Carvalho Cordeiro (RA: 10437533)")

public class Node {
    Palavra palavra;
    Node esquerda, direita;

    public Node(Palavra palavra) {
        this.palavra = palavra;
        this.esquerda = null;
        this.direita = null;
    }
}

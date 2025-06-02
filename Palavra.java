//Júlia DAgrella Araújo (RA:10426655")
//"Rafael Carvalho Cordeiro (RA: 10437533)")

public class Palavra {
    String texto;
    int ocorrencias;

    public Palavra(String texto) {
        this.texto = texto.toLowerCase(); // Garante que o texto sempre esteja em minúsculas
        this.ocorrencias = 1;
    }

    // Contador de ocorrências da palavra
    public void incrementar() {
        this.ocorrencias++;
    }

    public String getTexto() {
        return texto;
    }

    public int getOcorrencias() {
        return ocorrencias;
    }
}
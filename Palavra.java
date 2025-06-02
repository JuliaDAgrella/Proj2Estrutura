public class Palavra {
    String texto;
    int ocorrencias;

    public Palavra(String texto) {
        this.texto = texto.toLowerCase();
        this.ocorrencias = 1;
    }

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

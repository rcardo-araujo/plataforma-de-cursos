public abstract class AQuestao {
    private int pontos;
    private int qtdErros;
    private String tipo;
    private String enunciado;
    private boolean correta;

    public AQuestao(int pontos, String tipo, String enunciado) {
        this.pontos = pontos;
        this.tipo = tipo;
        this.enunciado = enunciado;
        this.qtdErros = 0;
        this.correta = false;   
    }

    public int getPontos() {
        return pontos;
    }

    public void setPontos(int pontos) {
        this.pontos = pontos;
    }

    public String getTipo() {
        return tipo;
    }

    public String getEnunciado() {
        return enunciado;
    }

    public boolean getCorreta() {
        return correta;
    }

    public void setCorreta(boolean status) {
        this.correta = status;
    }

    public int getQtdErros() {
        return qtdErros;
    }

    public void setQtdErros(int qtdErros) {
        this.qtdErros = qtdErros;
    }

    public void alteraPontos(int nPontos) {
        this.setPontos(nPontos);
    }

    public void imprimeHeader() {
        System.out.printf("(%d pontos) %s%n", this.pontos, this.enunciado);
    }

    public abstract boolean checaResposta(Object respostaUser);
    public abstract void imprimeQuest();
}

public abstract class AQuestao {
    private int pontos;
    private int qtdErros;
    private String tipo;
    private String enunciado;
    private boolean certa;

    public AQuestao(int pontos, String tipo, String enunciado) {
        this.pontos = pontos;
        this.tipo = tipo;
        this.enunciado = enunciado;
        this.qtdErros = 0;
        this.certa = false;   
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

    public boolean getCerta() {
        return certa;
    }

    public void setCerta(boolean status) {
        this.certa = status;
    }

    public int getQtdErros() {
        return qtdErros;
    }

    public void setQtdErros(int qtdErros) {
        this.qtdErros = qtdErros;
    }

    public void alterarPontos(int nPontos) {
        this.setPontos(nPontos);
    }

    public void adicionarErro() {
        this.qtdErros += 1;
    }

    public void imprimeHeader() {
        if(this.certa) System.out.printf("%s(%d pontos) %s%s%n", TextColor.COLOR_VERDE, this.pontos, this.enunciado, TextColor.COLOR_RESET);
        else System.out.printf("(%d pontos) %s%n", this.pontos, this.enunciado);
    }

    public abstract boolean checaResposta(Object respostaUser);
    public abstract void imprimeQuestao();
}

public abstract class AQuestao {
    private int pontos;
    private String enunciado;
    private int status; // 0 : Não respondida
                        // -1 : Resposta errada
                        // 1 : Resposta certa

    public AQuestao(int pontos, String enunciado) {
        this.pontos = pontos;
        this.enunciado = enunciado;
        this.status = 0;   
    }

    public int getPontos() {
        return pontos;
    }

    public void setPontos(int pontos) {
        this.pontos = pontos;
    }

    public String getEnunciado() {
        return enunciado;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void alteraPontos(int nPontos) {
        this.setPontos(nPontos);
    }

    public abstract boolean checaResposta(Object respostaUser);
    public abstract void imprimeQuestao();
}

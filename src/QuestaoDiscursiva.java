public class QuestaoDiscursiva extends AQuestao {
    private String resposta;

    public QuestaoDiscursiva(int pontos, String enunciado, String resposta) {
        super(pontos, enunciado);
        this.resposta = resposta;
    }

    public String getResposta() {
        return resposta;
    }

    public void setResposta(String resposta) {
        this.resposta = resposta;
    }

    @Override
    public boolean checaResposta(Object respostaUser) {
        return this.resposta.equals(respostaUser);
    }

    @Override
    public void imprimeQuestao() {
        System.out.printf("(%d pontos) %s%n", this.getPontos(), this.getEnunciado());
    }
}

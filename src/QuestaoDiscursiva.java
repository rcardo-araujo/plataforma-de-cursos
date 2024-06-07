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
        if((respostaUser != null) && (respostaUser instanceof String))
            return respostaUser.equals(this.getResposta());
        return false;
    }

    @Override
    public void imprimeQuestao() {
        this.imprimeHeader();
        System.out.println();
    }
}

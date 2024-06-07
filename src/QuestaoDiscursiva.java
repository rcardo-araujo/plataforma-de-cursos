public class QuestaoDiscursiva extends AQuestao {
    private String resposta;

    public QuestaoDiscursiva(int pontos, String tipo, String enunciado, String resposta) {
        super(pontos, tipo, enunciado);
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
    public void imprimeQuest() {
        this.imprimeHeader();
        System.out.println();
    }
}

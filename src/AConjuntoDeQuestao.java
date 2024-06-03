public abstract class AConjuntoDeQuestao {
    private String nomeArquivo;
    private int numQuestao;

    public AConjuntoDeQuestao(String nomeArquivo) {
        this.nomeArquivo = nomeArquivo;
        this.numQuestao = 0;
    }

    public int getNumQuestao() {
        return numQuestao;
    }

    public void setNumQuestao(int numQuestao) {
        this.numQuestao = numQuestao;
    }

    public String getNomeArquivo() {
        return nomeArquivo;
    }

    public abstract void adicionaQuestao(AQuestao nQuestao);
    public abstract void imprimeConjunto();
}

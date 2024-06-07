import java.util.Map;

public abstract class AModulo {
    protected static final String COLOR_RESET = "\u001B[0m";
    protected static final String COLOR_VERDE = "\u001B[32m";
    protected static final String COLOR_VERM = "\u001B[31m";

    private ConjuntoDeQuestao questoes;

    public AModulo(String nomeArquivo) {
        this.questoes = new ConjuntoDeQuestao(nomeArquivo);
    }

    public ConjuntoDeQuestao getQuestoes() {
        return questoes;
    }

    public int numQuest() {
        return questoes.getNumQuest();
    }

    public void adicionaQuest(int id, AQuestao nQuestao) {
        this.questoes.adicionaQuest(nQuestao);
    }

    public int minErro() {
        int id = 0;
        int min = Integer.MAX_VALUE;
        for(Map.Entry<Integer, AQuestao> par : questoes.getConj().entrySet()) {
            if(par.getValue().getQtdErros() < min) {
                min = par.getValue().getQtdErros();
                id = par.getKey();
            }
        }
        return id;
    }

    public abstract void adicionaQuest(AQuestao nQuestao);
    public abstract boolean toDo(int id);
    public abstract void imprimeMod();
}

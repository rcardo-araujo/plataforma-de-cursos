import java.util.Map;

public abstract class AModulo {
    private ConjuntoDeQuestao questoes;

    public AModulo(String path) {
        this.questoes = new ConjuntoDeQuestao(path);
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

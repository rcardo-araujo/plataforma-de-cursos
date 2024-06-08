import java.util.Map;
import java.util.Scanner;

public class ModuloEspecial extends AModulo {
    private static final int MAX_QUEST = 6;

    public ModuloEspecial() {
        super(null);
    }

    public boolean cheio() {
        return this.getQuestoes().getNumQuestoes() == MAX_QUEST;
    }

    public int menorQtdErros() {
        int id = 0;
        int min = Integer.MAX_VALUE;
        for(Map.Entry<Integer, AQuestao> par : getQuestoes().getConjunto().entrySet()) {
            if(par.getValue().getQtdErros() < min) {
                min = par.getValue().getQtdErros();
                id = par.getKey();
            }
        }
        return id;
    }

    @Override
    public void adicionarQuestao(AQuestao nQuestao) {
        if(this.cheio()) {
            int idSub = this.menorQtdErros();
            this.getQuestoes().adicionaQuestao(idSub, nQuestao);
        } else {
            this.getQuestoes().adicionaQuestao(nQuestao);
        }
    }

    @Override
    public void imprimeModulo() {
        System.out.printf("### Módulo de Revisão ###%n%n");
        Map<Integer, AQuestao> conj = this.getQuestoes().getConjunto();
        for(Map.Entry<Integer, AQuestao> par : conj.entrySet()) {
            System.out.printf("[%d] %s%n", par.getKey(), par.getValue().getEnunciado());
        }
    }
}

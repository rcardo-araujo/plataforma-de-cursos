import java.util.Map;

public class ModuloEspecial extends AModulo {
    private static final int MAX_QUEST = 6;

    public ModuloEspecial() {
        super(null);
    }

    public boolean cheio() {
        return this.getQuestoes().getNumQuestoes() == MAX_QUEST;
    }

    public int getMenorQtdErros() {
        if(!this.cheio()) return 0;
        
        int min = Integer.MAX_VALUE;
        for(Map.Entry<Integer, AQuestao> par : getQuestoes().getConjunto().entrySet()) {
            if(par.getValue().getQtdErros() < min) {
                min = par.getValue().getQtdErros();
            }
        }
        if(min == Integer.MAX_VALUE) return 0;
        return min;
    }

    @Override
    public void adicionarQuestao(AQuestao nQuestao) {
        if(this.getQuestoes().existeQuestao(nQuestao)) return;

        if(this.cheio()) {
            int idSub = this.getMenorQtdErros();
            this.getQuestoes().adicionaQuestao(idSub, nQuestao);
        } else {
            this.getQuestoes().adicionaQuestao(nQuestao);
        }
    }

    @Override
    public void imprimeModulo() {
        System.out.printf("%sMódulo de Revisão%s%n%n", TextColor.BOLD_BRAN, TextColor.COLOR_RESET);
        Map<Integer, AQuestao> conj = this.getQuestoes().getConjunto();
        for(Map.Entry<Integer, AQuestao> par : conj.entrySet()) {
            System.out.printf("[%d] %s%n", par.getKey(), par.getValue().getEnunciado());
        }
        System.out.println();
    }
}

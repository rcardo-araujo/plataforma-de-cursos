import java.util.List;
import java.util.LinkedList;

public class ConjuntoDeQuestaoLinkedList extends AConjuntoDeQuestao {
    private List<AQuestao> conjunto;

    public ConjuntoDeQuestaoLinkedList(String nomeArquivo) {
        super(nomeArquivo);
        this.conjunto = new LinkedList<>();
        this.carregaConjuntoDeArquivo();
    }

    @Override
    public void adicionaQuestao(AQuestao nQuestao) {
        this.conjunto.add(nQuestao);
        this.setNumQuestao(getNumQuestao() + 1);
    }

    @Override
    public void imprimeConjunto() {
        int i;
        for(i = 0; i < this.conjunto.size(); i ++) 
            this.conjunto.get(i).imprimeQuestao();
    }
}

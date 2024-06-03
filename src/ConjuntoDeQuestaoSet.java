import java.util.Set;
import java.util.HashSet;
import java.util.Iterator;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileNotFoundException;

public class ConjuntoDeQuestaoSet extends AConjuntoDeQuestao {
    private Set<AQuestao> conjunto;

    public ConjuntoDeQuestaoSet(String nomeArquivo) {
        super(nomeArquivo);
        this.conjunto = new HashSet<>();
    }

    @Override
    public void adicionaQuestao(AQuestao nQuestao) {
        this.conjunto.add(nQuestao);
        this.setNumQuestao(getNumQuestao() + 1);
    }

    @Override
    public void imprimeConjunto() {
        Iterator<AQuestao> it = this.conjunto.iterator();
        while(it.hasNext()) {
            it.next().imprimeQuestao();
        }
    }
}

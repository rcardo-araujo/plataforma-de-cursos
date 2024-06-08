import java.util.List;
import java.util.ArrayList;

public class QuestaoMultEscolha extends AQuestao {
    private List<String> opcoes;
    private int indexResposta;
    private int numOpcoes;

    public QuestaoMultEscolha(int pontos, String tipo, String enunciado, int indexResposta) {
        super(pontos, tipo, enunciado);
        this.indexResposta = indexResposta;
        this.opcoes = new ArrayList<>();
        this.numOpcoes = 0;
    }

    public int getNumOpcoes() {
        return numOpcoes;
    }

    public void setNumOpcoes(int numOpcoes) {
        this.numOpcoes = numOpcoes;
    }

    public void adicionaOpcao(String nOpcao) {
        this.opcoes.add(nOpcao);
        this.setNumOpcoes(this.getNumOpcoes() + 1);
    }

    @Override
    public boolean checaResposta(Object respostaUser) {
        if((respostaUser != null) && (respostaUser instanceof Integer)) { 
            int intResp = (int) respostaUser;
            if((intResp >= 1 && intResp <= this.opcoes.size()) && intResp == indexResposta)
                    return true;
        }
        return false;
    }
    
    @Override
    public void imprimeQuestao() {
        this.imprimeHeader();
        for(int i = 0; i < this.opcoes.size(); i++) {
            System.out.printf("[%d] %s%n", i + 1, this.opcoes.get(i));
        }
        System.out.println();
    }
}

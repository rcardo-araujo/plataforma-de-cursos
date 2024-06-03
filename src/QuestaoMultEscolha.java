import java.util.List;

public class QuestaoMultEscolha extends AQuestao {
    private List<String> opcoes;
    private int indexResposta;

    public QuestaoMultEscolha(int pontos, String enunciado, List<String> opcoes, int indexResposta) {
        super(pontos, enunciado);
        this.opcoes = opcoes;
        this.indexResposta = indexResposta;
    }

    public void adicionaOpcao(String opcao) {
        this.opcoes.add(opcao);
    }

    public void removeOpcao(int nOpcao) {
        if((nOpcao > 0) && (nOpcao <= this.opcoes.size())) {
            this.opcoes.remove(nOpcao);
        } else {
            System.out.println("Não existe opção com esta numeração!");
        }
    }

    @Override
    public boolean checaResposta(Object respostaUser) {
        if(!(respostaUser instanceof Integer)) return false;

        if(respostaUser != null) { 
            int intResp = (int) respostaUser;
            if((intResp >= 1 && intResp <= this.opcoes.size()) &&
                intResp == indexResposta)
                    return true;
        }

        return false;
    }

    @Override
    public void imprimeQuestao() {
        System.out.printf("(%d pontos) %s%n", this.getPontos(), this.getEnunciado());
        for(int i = 0; i < this.opcoes.size(); i++) {
            System.out.printf("[%d] %s%n", i + 1, this.opcoes.get(i));
        }
        System.out.println();
    }
}

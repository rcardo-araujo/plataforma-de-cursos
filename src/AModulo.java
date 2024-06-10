public abstract class AModulo {
    private ConjuntoDeQuestao questoes;

    public AModulo(String path) {
        this.questoes = new ConjuntoDeQuestao(path);
    }

    public ConjuntoDeQuestao getQuestoes() {
        return questoes;
    }

    public int getNumQuestoes() {
        return questoes.getNumQuestoes();
    }

    public void adicionarQuestao(int id, AQuestao nQuestao) {
        this.questoes.adicionaQuestao(nQuestao);
    }

    public AQuestao buscarQuestao(int id) {
        return this.questoes.buscarQuestao(id);
    }

    public boolean fazerTarefa(int id) {
        AQuestao questao = buscarQuestao(id);
        
        if(questao != null) {    
            imprimeQuestao(id);
            Object resp = Solicita.resposta(questao.getTipo());
            
            return questao.checaResposta(resp);
        }
        else {
            Mensagens.questaoNaoLocalizada();
            return false;
        }
    }

    public void imprimeQuestao(int id) {
        AQuestao questao = this.buscarQuestao(id);
        if(questao != null) {
            System.out.printf("%sQuestão %d%s%n", TextColor.BOLD_CIANO, id, TextColor.COLOR_RESET);
            questao.imprimeQuestao();
        }
    }

    public abstract void adicionarQuestao(AQuestao nQuestao);
    public abstract void imprimeModulo();
}

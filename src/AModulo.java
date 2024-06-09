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
            Object resp = solicitarResposta(questao.getTipo());
            
            return questao.checaResposta(resp);
        }
        else {
            Mensagens.questaoNaoLocalizada();
            return false;
        }
    }

    public Object solicitarResposta(String tipo) {
        Leitor leitor = Leitor.getInstance();
        System.out.print("Digite sua resposta: ");
        Object resp = null;

        if(tipo.equals("MUL")) {
            try {
                resp = leitor.nextInt();
                leitor.nextLine();
            } catch(ExcecaoLeitorFechado e) {
                e.printStackTrace();
                System.exit(1);
            }
        }
        if(tipo.equals("DIS")) {
            try {
                resp = leitor.nextLine();
            } catch(ExcecaoLeitorFechado e) {
                e.printStackTrace();
                System.exit(1);
            }
        }

        return resp;
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

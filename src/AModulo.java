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
            System.out.printf("%sNão foi possível localizar a questão com este id!%s%n%n", TextColor.COLOR_AMAR, TextColor.COLOR_RESET);
            return false;
        }
    }

    public Object solicitarResposta(String tipo) {
        Leitor leitor = Leitor.getInstance();
        System.out.print("Digite sua resposta: ");

        if(tipo.equals("MUL")) {
            try {
                return leitor.nextInt();
            } catch(ExcecaoLeitorFechado e) {
                e.printStackTrace();
                System.exit(1);
            }
        }
        if(tipo.equals("DIS")) {
            try {
                return leitor.nextLine();
            } catch(ExcecaoLeitorFechado e) {
                e.printStackTrace();
                System.exit(1);
            }
        }

        return null;
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

public class ModuloComum extends AModulo {
    private String nomeModulo;

    public ModuloComum(String nomeCurso, String nomeModulo){
        super(String.format("./Cursos/%s/%s", nomeCurso, nomeModulo));
        this.nomeModulo = nomeModulo;
    }

    public String getNomeModulo() {
        return nomeModulo;
    }

    public boolean moduloCompleto() {
        for(AQuestao questao : this.getQuestoes().getConjunto().values())
            if(!questao.getCerta()) return false;
        return true;
    }

    public int getPontosQuestao(int id) {
        AQuestao questao = buscarQuestao(id);
        if(questao.getCerta()) return 0;
        return questao.getPontos(); 
    }

    public int getQtdErrosQuestao(int id) {
        return buscarQuestao(id).getQtdErros();
    }

    public void adicionarErro(int id) {
        AQuestao questao = buscarQuestao(id);
        if(!questao.getCerta()) questao.setQtdErros(questao.getQtdErros() + 1); 
    }

    @Override
    public void adicionarQuestao(AQuestao nQuestao) {
        this.getQuestoes().adicionaQuestao(nQuestao);
    }

    @Override
    public void imprimeModulo() {
        System.out.printf("%s%s%s%n%n", TextColor.BOLD_BRAN, this.nomeModulo, TextColor.COLOR_RESET);
        this.getQuestoes().imprimeHeaders();
        System.out.println();
    }
}

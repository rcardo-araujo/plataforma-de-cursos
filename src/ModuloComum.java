public class ModuloComum extends AModulo {
    private String nomeModulo;
    private boolean completo;

    public ModuloComum(String nomeCurso, String nomeModulo){
        super(String.format("./Cursos/%s/%s", nomeCurso, nomeModulo));
        this.nomeModulo = nomeModulo;
        this.completo = false;
    }

    public String getNomeModulo() {
        return nomeModulo;
    }

    public void setCompleto(boolean completo) {
        this.completo = completo;
    }

    public boolean isCompleto() {
        return completo;
    }

    public boolean verificaModuloCompleto() {
        for(AQuestao questao : this.getQuestoes().getConjunto().values())
            if(!questao.getCerta()) return false;
        return true;
    }

    public int getPontosQuestao(int id) {
        AQuestao questao = buscarQuestao(id);
        if(questao.getCerta()) return 0;
        return questao.getPontos(); 
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

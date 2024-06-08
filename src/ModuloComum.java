import java.util.Scanner;

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
        for(AQuestao quest : this.getQuestoes().getConjunto().values())
            if(!quest.getCerta()) return false;
        return true;
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

import java.io.File;
import java.util.LinkedHashMap;
import java.util.Map;

public class Curso {
    private String nome;
    private Map<Integer, ModuloComum> modulos;
    private ModuloEspecial moduloRevisao;

    public Curso(String path) {
        if(path.contains("/")) this.nome = path.substring(path.indexOf("/") + 1, path.length());
        else this.nome = path;
        this.modulos = new LinkedHashMap<>();
        this.moduloRevisao = new ModuloEspecial();

        File f = new File(String.format("./Cursos/%s", path));
        String[] nomesModulos = f.list();
        for(int i = 0; i < nomesModulos.length; i ++) {
            ModuloComum nModulo = new ModuloComum(path, nomesModulos[i].substring(0, nomesModulos[i].indexOf(".")));
            this.modulos.put(i + 1, nModulo);
        }
    }

    public String getNome() {
        return this.nome;
    }

    public Map<Integer, ModuloComum> getModulos() {
        return this.modulos;
    }

    public ModuloEspecial getModuloRevisao() {
        return moduloRevisao;
    }

    public ModuloComum buscarModulo(int id) {
        return this.modulos.get(id);
    }

    public int getMenorQtdErrosModuloRevisao() {
        return this.moduloRevisao.getMenorQtdErros();
    }

    public void adicionarQuestaoModuloRevisao(AQuestao nQuestao) {
        this.moduloRevisao.adicionarQuestao(nQuestao);
    }

    public void imprimeCurso() {
        System.out.printf("%s%s%s%n", TextColor.BOLD_BRAN, this.nome, TextColor.COLOR_RESET);
        for(Map.Entry<Integer, ModuloComum> par : this.modulos.entrySet())
            System.out.printf("[%d] %s%n", par.getKey(), par.getValue().getNomeModulo());
        System.out.println();
    }
}

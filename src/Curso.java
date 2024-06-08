import java.io.File;
import java.util.Map;
import java.util.LinkedHashMap;

public class Curso {
    private String nome;
    private Map<Integer, ModuloComum> mods;
    private ModuloEspecial modRevisao;

    public Curso(String nome) {
        this.nome = nome;
        this.mods = new LinkedHashMap<>();
        this.modRevisao = new ModuloEspecial();

        File f = new File(String.format("./Cursos/%s", nome));
        String[] nomesModulos = f.list();
        for(int i = 0; i < nomesModulos.length; i ++) {
            ModuloComum nModulo = new ModuloComum(this.nome, nomesModulos[i].substring(0, nomesModulos[i].indexOf(".")));
            this.mods.put(i + 1, nModulo);
        }
    }

    public String getNome() {
        return this.nome;
    }

    public Map<Integer, ModuloComum> getMods() {
        return mods;
    }

    public ModuloEspecial getModRevisao() {
        return modRevisao;
    }

    public ModuloComum buscaMod(int id) {
        return this.mods.get(id);
    }

    public void imprimeCurso() {
        System.out.printf("%s%s%s%n", TextColor.BOLD_BRAN, this.nome, TextColor.COLOR_RESET);
        for(Map.Entry<Integer, ModuloComum> par : this.mods.entrySet())
            System.out.printf("[%d] %s%n", par.getKey(), par.getValue().getNomeModulo());
        System.out.println();
    }
}

import java.io.File;
import java.util.Map;
import java.util.LinkedHashMap;

public class Curso {
    private int nivel;
    private String nome;
    private int pontosTotais;
    private Map<Integer, ModuloComum> mods;
    private ModuloEspecial modRevisao;

    public Curso(String nome) {
        this.nome = nome;
        this.nivel = 1;
        this.pontosTotais = 0;
        this.mods = new LinkedHashMap<>();
        this.modRevisao = new ModuloEspecial();

        File f = new File(String.format("./Cursos/%s", nome));
        String[] nomesModulos = f.list();
        for(int i = 0; i < nomesModulos.length; i ++) {
            ModuloComum nModulo = new ModuloComum(nomesModulos[i], this.nome);
            this.mods.put(i + 1, nModulo);
        }
    }

    public String getNome() {
        return this.nome;
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    public Map<Integer, ModuloComum> getMods() {
        return mods;
    }

    public ModuloEspecial getModRevisao() {
        return modRevisao;
    }
}

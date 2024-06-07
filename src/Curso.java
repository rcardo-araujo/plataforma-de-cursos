import java.util.Map;
import java.util.HashMap;

public class Curso {
    private String nome;
    private int nivelAtual;
    private int totalPontos;
    private Map<Integer, AModulo> modulos;

    public Curso(String nome) {
        this.nome = nome;
        this.nivelAtual = 1;
        this.totalPontos = 0;
        this.modulos = new HashMap<>();
    }
}

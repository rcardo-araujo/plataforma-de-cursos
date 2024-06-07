import java.util.Collection;
import java.util.ArrayList;


public class Curso {
    private String nome;
    private Collection<Modulo> modulos;
    
    public Curso(String nome){
        this.nome = nome;
        this.modulos = new ArrayList<>();
    }

    public String getNome(){
        return this.nome;
    }
}

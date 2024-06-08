import java.util.Map;
import java.util.Scanner;

public class GerenciaCurso {
    private Curso curso;
    private int nivel;
    private int pontos;

    public GerenciaCurso(Curso curso) {
        this.curso = curso;
        this.nivel = 1;
        this.pontos = 0;
    }

    public String getNome(){
        return this.curso.getNome();
    }

    public Curso getCurso() {
        return curso;
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    public int getPontos() {
        return pontos;
    }

    public void setPontos(int pontos) {
        this.pontos = pontos;
    }

    public void fazerMod(int id) {
        ModuloComum mod = this.curso.buscaMod(id);
        if(mod == null) {
            System.out.printf("%sMódulo com este id inexistente!%s%n%n",
                TextColor.BOLD_BRAN,
                TextColor.COLOR_RESET);
            
            return;
        }

        try {
            Scanner s = new Scanner(System.in);
            
            mod.imprimeModulo();
            System.out.print("Qual questão gostaria de fazer?: ");
            int opc = s.nextInt();
            System.out.println();

            
            mod.fazerTarefa(opc);
        } catch(Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    public void imprimeInterfaceCurso() {
        System.out.printf("%s%s%s%n", 
            TextColor.BOLD_BRAN, 
            this.curso.getNome(), 
            TextColor.COLOR_RESET);
        
        for(Map.Entry<Integer, ModuloComum> par : this.curso.getMods().entrySet()) {
            System.out.printf("[%d] ", par.getKey());
            if(par.getKey() == this.nivel) System.out.print(TextColor.COLOR_CIANO);
            else if(par.getKey() < this.nivel) System.out.print(TextColor.COLOR_VERDE);
            System.out.println(par.getValue().getNomeModulo() + TextColor.COLOR_RESET);
        }

        System.out.printf("%n[0] Revisão%n%n");
    } 
}

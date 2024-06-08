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

    public Curso getCurso() {
        return curso;
    }

    public String getNomeCurso() {
        return this.curso.getNome();
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

    public void fazerModulo(int id) {
        if(id == 0) {
            fazerModuloRevisao();
            return;
        }
        
        ModuloComum modulo = this.curso.buscarModulo(id);
        if(modulo != null) {
            modulo.imprimeModulo();
            int opcao = solicitarId();
            if(modulo.fazerTarefa(opcao)) {
                this.pontos += modulo.getPontosQuestao(id);
                TextColor.respostaCerta();
            }
            else {
                modulo.adicionarErro(id);
                if(modulo.getQtdErrosQuestao(id) > this.curso.getMenorQtdErrosModuloRevisao()) {
                    this.curso.adicionarQuestaoModuloRevisao(modulo.buscarQuestao(id));
                }
                TextColor.respostaErrada();
            }
        }
        else {
            System.out.printf("%sNão foi possível localizar módulo com esse id!%s%n%n", TextColor.COLOR_AMAR, TextColor.COLOR_RESET);
        }
    }

    public void fazerModuloRevisao() {
        this.curso.getModuloRevisao().imprimeModulo();
        int opcao = solicitarId();
        this.curso.getModuloRevisao().fazerTarefa(opcao);
    }

    public int solicitarId() {
        Leitor leitor = Leitor.getInstance();
        System.out.printf("Digite o id da questao: ");
        int resp;

        try {
            resp = leitor.nextInt();
            System.out.println();
            return resp;
        } catch(ExcecaoLeitorFechado e) {
            e.printStackTrace();
            System.exit(1);
        }

        return 0;
    }

    public void imprimeInterfaceCurso() {
        System.out.printf("%s%s%s%n", 
            TextColor.BOLD_BRAN, 
            this.curso.getNome(), 
            TextColor.COLOR_RESET);
        
        for(Map.Entry<Integer, ModuloComum> par : this.curso.getModulos().entrySet()) {
            System.out.printf("[%d] ", par.getKey());
            if(par.getKey() == this.nivel) System.out.print(TextColor.COLOR_CIANO);
            else if(par.getKey() < this.nivel) System.out.print(TextColor.COLOR_VERDE);
            System.out.println(par.getValue().getNomeModulo() + TextColor.COLOR_RESET);
        }

        System.out.printf("%n[0] Revisão%n%n");
    } 
}

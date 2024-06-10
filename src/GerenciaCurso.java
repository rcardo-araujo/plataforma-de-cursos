import java.util.Map;

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
        if(id > this.nivel) {
            Mensagens.usuarioNaoAptoModulo();
            return;
        }

        ModuloComum modulo = this.curso.buscarModulo(id);
        if(modulo != null) {
            modulo.imprimeModulo();
            int opcao = Solicita.idQuestao();

            AQuestao questao = modulo.buscarQuestao(opcao);
            if(questao != null) {
                if(modulo.fazerTarefa(opcao)) {
                    int pontosQuestao = modulo.getPontosQuestao(opcao);
                    System.out.println(pontosQuestao);
                    if(pontosQuestao != 0)  {
                        questao.setCerta(true);
                        this.pontos += pontosQuestao;
                        if(modulo.verificaModuloCompleto()) {
                            System.out.println("BLA");
                            if(this.nivel < this.curso.getModulos().size()) {
                                this.nivel ++;
                                Mensagens.moduloDesbloqueado(this.curso.buscarModulo(this.nivel).getNomeModulo());
                            }
                            else {
                                this.nivel = Integer.MAX_VALUE;
                                Mensagens.cursoCompleto(getNomeCurso());
                            }
                        }
                    }
                    Mensagens.respostaCerta();
                }
                else {
                    questao.adicionarErro();
                    if(questao.getQtdErros() > this.curso.getMenorQtdErrosModuloRevisao()) {
                        this.curso.adicionarQuestaoModuloRevisao(questao);
                    }
                    Mensagens.respostaErrada();
                }
            }
            else {
                Mensagens.questaoNaoLocalizada();
            }
        }
        else {
            Mensagens.moduloNaoLocalizado();
        }
    }

    public void fazerModuloRevisao() {
        this.curso.getModuloRevisao().imprimeModulo();
        int opcao = Solicita.idQuestao();
        if(this.curso.getModuloRevisao().fazerTarefa(opcao)) Mensagens.respostaCerta();
        else Mensagens.respostaErrada();
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

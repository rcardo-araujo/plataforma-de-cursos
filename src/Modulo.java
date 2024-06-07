public class Modulo {
    private AConjuntoDeQuestao questoes;
    private String nomeArquivo;

    public Modulo(int lvl, String nomeCurso){
        this.nomeArquivo = nomeCurso + lvl + ".txt";
        this.questoes = new ConjuntoDeQuestaoLinkedList(nomeArquivo);
    }

    public boolean fazerLicao(){
        
        return true;
    }
}

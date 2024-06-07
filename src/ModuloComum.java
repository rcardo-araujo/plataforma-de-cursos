import java.util.Scanner;

public class ModuloComum extends AModulo {
    private String nomeModulo;

    public ModuloComum(int nivel, String nomeCurso){
        super(String.format("%d-%s.txt", nivel, nomeCurso));
        this.nomeModulo = String.format("%d-%s", nivel, nomeCurso);
    }

    public String getNomeModulo() {
        return nomeModulo;
    }

    public boolean modCompleto() {
        for(AQuestao quest : this.getQuestoes().getConj().values())
            if(!quest.estaCorreta()) return false;
        return true;
    }

    @Override
    public void adicionaQuest(AQuestao nQuestao) {
        this.getQuestoes().adicionaQuest(nQuestao);
    }

    @Override
    public void imprimeMod() {
        System.out.printf("### %s ###%n%n", this.nomeModulo);
        this.getQuestoes().imprimeHeaders();
        System.out.println();
    }

    @Override
    public boolean toDo(int id) {
        try {
            Scanner s = new Scanner(System.in);
            AQuestao quest = this.getQuestoes().buscaQuest(id);
            if(quest == null) return false;

            System.out.printf("# Questão %d #%n", id);
            quest.imprimeQuest();
            System.out.print("Digite sua resposta: ");
            Object resp = null;
            
            if(quest.getTipo().equals("MUL")) resp = s.nextInt();
            else if(quest.getTipo().equals("DIS")) resp = s.nextLine();
            
            boolean check = quest.checaResposta(resp);
            if(!check) {
                System.out.printf("%sResposta ERRADA!%s%n%n", COLOR_VERM, COLOR_RESET);
                quest.setQtdErros(quest.getQtdErros() + 1);
            }
            else {
                System.out.printf("%sResposta CORRETA!%s%n%n", COLOR_VERDE, COLOR_RESET);
                quest.setCorreta(check);
            }

            return check;
        } catch(Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
        return false;
    }
}

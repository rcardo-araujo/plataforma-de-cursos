import java.util.Map;
import java.util.Scanner;

public class ModuloEspecial extends AModulo {
    private static final int MAX_QUEST = 6;

    public ModuloEspecial() {
        super(null);
    }

    public boolean cheio() {
        return this.getQuestoes().getNumQuest() == MAX_QUEST;
    }

    @Override
    public void adicionaQuest(AQuestao nQuestao) {
        if(this.cheio()) {
            int idSub = this.minErro();
            this.getQuestoes().adicionaQuest(idSub, nQuestao);
        } else {
            this.getQuestoes().adicionaQuest(nQuestao);
        }
    }

    @Override
    public void imprimeMod() {
        System.out.printf("### Módulo de Revisão ###%n%n");
        Map<Integer, AQuestao> conj = this.getQuestoes().getConj();
        for(Map.Entry<Integer, AQuestao> par : conj.entrySet()) {
            System.out.printf("[%d] %s%n", par.getKey(), par.getValue().getEnunciado());
        }
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
            if(check) System.out.printf("Resposta CORRETA!%n%n");
            else System.out.print("Resposta ERRADA!%n%n");

            return check;
        } 
        catch(Exception e) {
            e.printStackTrace();
            System.exit(1);
        } 
        return false;
    }
}

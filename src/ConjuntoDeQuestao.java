import java.nio.charset.StandardCharsets;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.util.Map;
import java.util.LinkedHashMap;

public class ConjuntoDeQuestao {
    private static final int IND_TIPO = 0;
    private static final int IND_ENUN = 1;
    private static final int IND_PON = 2;
    private static final int IND_RESP = 3;
    private static final int IND_OPC = 4;

    private Map<Integer, AQuestao> conj;
    private String path;
    private int numQuest;

    public ConjuntoDeQuestao(String path) {
        this.conj = new LinkedHashMap<>();
        this.path = path;
        this.numQuest = 0;
        this.loadQuest();
    }

    public Map<Integer, AQuestao> getConj() {
        return conj;
    }

    public String getPath() {
        return path;
    }

    public int getNumQuest() {
        return numQuest;
    }

    public void setNumQuest(int numQuest) {
        this.numQuest = numQuest;
    }

    private void loadQuest() {
        if(this.path != null) {
            try {
                FileInputStream fs = new FileInputStream(path);
                InputStreamReader is = new InputStreamReader(fs, StandardCharsets.UTF_8);
                BufferedReader b = new BufferedReader(is);

                String line = b.readLine();
                while(line != null) {
                    String[] atributos = line.trim().split(";");
                    for(String atr : atributos) atr.trim();

                    if(atributos[IND_TIPO].equals("MUL")) {
                        QuestaoMultEscolha quest = new QuestaoMultEscolha(
                            Integer.parseInt(atributos[IND_PON]),
                            atributos[IND_TIPO],
                            atributos[IND_ENUN],
                            Integer.parseInt(atributos[IND_RESP]));
                        for(int i = IND_OPC; i < atributos.length; i ++)
                            quest.adicionaOpcao(atributos[i]);
                        
                        adicionaQuest(quest);
                    }
                    else if(atributos[IND_TIPO].equals("DIS")) {
                        QuestaoDiscursiva quest = new QuestaoDiscursiva(
                            Integer.parseInt(atributos[IND_PON]),
                            atributos[IND_TIPO],
                            atributos[IND_ENUN],
                            atributos[IND_RESP]);
                        
                        adicionaQuest(quest);
                    }

                    line = b.readLine();
                }
            } catch(Exception e) {
                e.printStackTrace();
                System.exit(1);
            }
        }
    }

    public void adicionaQuest(AQuestao nQuestao) {
        this.setNumQuest(this.getNumQuest() + 1);
        this.conj.put(this.getNumQuest(), nQuestao);
    }

    public void adicionaQuest(int id, AQuestao nQuestao) {
        this.setNumQuest(this.getNumQuest() + 1);
        this.conj.put(id, nQuestao);
    }

    public AQuestao buscaQuest(int id) {
        return this.conj.get(id);
    }

    public boolean vazio() {
        return this.getNumQuest() == 0;
    }

    public void imprimeHeaders() {
        for(Map.Entry<Integer, AQuestao> par : this.conj.entrySet()) { 
            System.out.printf("[%d] ", par.getKey());
            par.getValue().imprimeHeader();
        }
    }

    public void imprime() {
        for(AQuestao quest : this.conj.values()) 
            quest.imprimeQuest();
    }
}

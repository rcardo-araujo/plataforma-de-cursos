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

    private Map<Integer, AQuestao> conjunto;
    private int numQuestoes;

    public ConjuntoDeQuestao(String path) {
        this.conjunto = new LinkedHashMap<>();
        this.numQuestoes = 0;
        this.loadQuestoes(path);
    }

    public Map<Integer, AQuestao> getConjunto() {
        return conjunto;
    }

    public int getNumQuestoes() {
        return numQuestoes;
    }

    public void setNumQuestoes(int numQuestoes) {
        this.numQuestoes = numQuestoes;
    }

    private void loadQuestoes(String path) {
        if(path != null) {
            try {
                FileInputStream fs = new FileInputStream(path + ".txt");
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
                        
                        adicionaQuestao(quest);
                    }
                    else if(atributos[IND_TIPO].equals("DIS")) {
                        QuestaoDiscursiva quest = new QuestaoDiscursiva(
                            Integer.parseInt(atributos[IND_PON]),
                            atributos[IND_TIPO],
                            atributos[IND_ENUN],
                            atributos[IND_RESP]);
                        
                        adicionaQuestao(quest);
                    }

                    line = b.readLine();
                }
            } catch(Exception e) {
                e.printStackTrace();
                System.exit(1);
            }
        }
    }

    public void adicionaQuestao(AQuestao nQuestao) {
        this.setNumQuestoes(this.getNumQuestoes() + 1);
        this.conjunto.put(this.getNumQuestoes(), nQuestao);
    }

    public void adicionaQuestao(int id, AQuestao nQuestao) {
        this.setNumQuestoes(this.getNumQuestoes() + 1);
        this.conjunto.put(id, nQuestao);
    }

    public AQuestao buscaQuestao(int id) {
        return this.conjunto.get(id);
    }

    public boolean vazio() {
        return this.getNumQuestoes() == 0;
    }

    public void imprimeHeaders() {
        for(Map.Entry<Integer, AQuestao> par : this.conjunto.entrySet()) { 
            System.out.printf("[%d] ", par.getKey());
            par.getValue().imprimeHeader();
        }
    }

    public void imprimeConjunto() {
        for(AQuestao quest : this.conjunto.values()) 
            quest.imprimeQuestao();
    }
}

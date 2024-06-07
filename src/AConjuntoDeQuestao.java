import java.io.BufferedReader;
import java.io.FileReader;

public abstract class AConjuntoDeQuestao {
    protected static final int IND_TIPO = 0;
    protected static final int IND_ENUN = 1;
    protected static final int IND_PON = 2;
    protected static final int IND_RESP = 3;
    protected static final int IND_OPC = 4;

    private String nomeArquivo;
    private int numQuestao;

    public AConjuntoDeQuestao(String nomeArquivo) {
        this.nomeArquivo = nomeArquivo;
        this.numQuestao = 0;
    }

    public int getNumQuestao() {
        return numQuestao;
    }

    public void setNumQuestao(int numQuestao) {
        this.numQuestao = numQuestao;
    }

    public String getNomeArquivo() {
        return nomeArquivo;
    }

    public void carregaConjuntoDeArquivo() {
        try(FileReader f = new FileReader(this.nomeArquivo); BufferedReader b = new BufferedReader(f)) {
            do {
                String linha = b.readLine();
                if(linha == null) break;

                String[] atributos = linha.trim().split(",");
                for(String atributo : atributos) atributo.trim();

                if(atributos[IND_TIPO].equals("Multipla Escolha")) {
                    QuestaoMultEscolha questM = new QuestaoMultEscolha(
                        Integer.parseInt(atributos[IND_PON]),
                        atributos[IND_ENUN],
                        Integer.parseInt(atributos[IND_RESP])
                    );

                    int i;
                    for(i = IND_OPC; i < atributos.length; i++)
                        questM.adicionaOpcao(atributos[i]);

                    this.adicionaQuestao(questM);
                }
                else if(atributos[IND_TIPO].equals("Discursiva")) {
                    QuestaoDiscursiva questD = new QuestaoDiscursiva(
                        Integer.parseInt(atributos[IND_PON]),
                        atributos[IND_ENUN],
                        atributos[IND_RESP]
                    );

                    this.adicionaQuestao(questD);
                }
            } while(true);
        } catch(Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    public abstract void adicionaQuestao(AQuestao nQuestao);
    public abstract void imprimeConjunto();
}

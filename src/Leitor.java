import java.util.Scanner;

public class Leitor {
    private Scanner s;
    private boolean fechado;
    private static Leitor singleton = null;

    private Leitor() {
        this.s = new Scanner(System.in);
        this.fechado = false;
    }

    public static Leitor getInstance() {
        if(singleton == null) {
            singleton = new Leitor();
        }
        return singleton;
    }

    public int nextInt() throws ExcecaoLeitorFechado {
        if(!fechado) {
            try {
                return s.nextInt();
            } catch(Exception e) {
                e.printStackTrace();
                System.exit(1);
            }
        }

        throw new ExcecaoLeitorFechado();
    }

    public String nextLine() throws ExcecaoLeitorFechado {
        if(!fechado) {
            try {
                return s.nextLine();
            } catch(Exception e) {
                e.printStackTrace();
                System.exit(1);
            }
        }
        
        throw new ExcecaoLeitorFechado();
    }

    public void close() {
        this.fechado = true;
        s.close();
    }
}

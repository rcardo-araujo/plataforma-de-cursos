public class ExcecaoLeitorFechado extends Exception {
    public ExcecaoLeitorFechado() {
        super("O leitor já foi fechado!");
    }
}
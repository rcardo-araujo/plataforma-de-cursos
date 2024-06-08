public class TextColor {
    static final String COLOR_RESET = "\u001B[0m";
    static final String COLOR_VERDE = "\u001B[32m";
    static final String COLOR_VERM = "\u001B[31m";
    static final String COLOR_CIANO = "\u001B[36m";
    static final String COLOR_AMAR = "\033[0;33m";
    static final String BOLD_BRAN = "\033[1;37m";
    static final String BOLD_CIANO = "\033[1;36m";
    static final String BOLD_AMAR = "\033[1;33m";

    public static void respostaCerta() {
        System.out.printf("%sResposta CERTA!%s%n%n", COLOR_VERDE, COLOR_RESET);
    }

    public static void respostaErrada() {
        System.out.printf("%sResposta ERRADA!%s%n%n", COLOR_VERM, COLOR_RESET);
    }
}

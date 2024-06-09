public class Mensagens {
    public static void respostaCerta() {
        System.out.printf("%sResposta CERTA!%s%n%n", TextColor.COLOR_VERDE, TextColor.COLOR_RESET);
    }

    public static void respostaErrada() {
        System.out.printf("%sResposta ERRADA!%s%n%n", TextColor.COLOR_VERM, TextColor.COLOR_RESET);
    }

    public static void usuarioNaoAptoModulo() {
        System.out.printf("%sEsse módulo ainda não foi desbloqueado.%s%n%n", TextColor.COLOR_AMAR, TextColor.COLOR_RESET);
    }

    public static void moduloNaoLocalizado() {
        System.out.printf("%sNão foi possível localizar módulo com esse id!%s%n%n", TextColor.COLOR_AMAR, TextColor.COLOR_RESET);
    }

    public static void questaoNaoLocalizada() {
        System.out.printf("%sNão foi possível localizar a questão com esse id!%s%n%n", TextColor.COLOR_AMAR, TextColor.COLOR_RESET);
    }

    public static void moduloDesbloqueado(String nomeModulo) {
        System.out.printf("%sMódulo %s%s%s desbloqueado!%s%n%n", TextColor.COLOR_AMAR, TextColor.BOLD_AMAR, nomeModulo, TextColor.COLOR_AMAR, TextColor.COLOR_RESET);
    }

    public static void cursoCompleto(String nomeCurso) {
        System.out.printf("%sCurso %s%s%s finalizado!%s%n%n", TextColor.COLOR_AMAR, TextColor.BOLD_AMAR, nomeCurso, TextColor.COLOR_AMAR, TextColor.COLOR_RESET);
    }
}

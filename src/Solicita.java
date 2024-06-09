public class Solicita {
    public static String username() {
        Leitor leitor = Leitor.getInstance();
        System.out.printf("%sUsername:%s ", TextColor.BOLD_BRAN, TextColor.COLOR_RESET);
        try {
            String username = leitor.nextLine();
            System.out.println();
            return username;
        } catch(ExcecaoLeitorFechado e) {
            e.printStackTrace();
            System.exit(1);
        }
        return null;
    }

    public static String pswd() {
        Leitor leitor = Leitor.getInstance();
        System.out.printf("%sSenha:%s ", TextColor.BOLD_BRAN, TextColor.COLOR_RESET);
        try {
            String pswd = leitor.nextLine();
            System.out.println();
            return pswd;
        } catch(ExcecaoLeitorFechado e) {
            e.printStackTrace();
            System.exit(1);
        }
        return null;
    }

    public static String tipoUser() {
        Leitor leitor = Leitor.getInstance();
        System.out.printf("%sAdministrador [A] ou usuário comum [U]?: %s ", TextColor.BOLD_BRAN, TextColor.COLOR_RESET);
        try {
            String tipoUser = leitor.nextLine();
            leitor.nextLine();
            System.out.println();
            return tipoUser;
        } catch(ExcecaoLeitorFechado e) {
            e.printStackTrace();
            System.exit(1);
        }
        return null;
    }

    public static int opcao() {
        Leitor leitor = Leitor.getInstance();
        System.out.printf("Digite a opção desejada: ");
        try {
            int opcao = leitor.nextInt();
            System.out.println();
            return opcao;
        } catch(ExcecaoLeitorFechado e) {
            e.printStackTrace();
            System.exit(1);
        }
        return 0;
    }
}

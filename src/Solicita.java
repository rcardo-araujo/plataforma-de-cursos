public class Solicita {
    public static String username() {
        Leitor leitor = Leitor.getInstance();
        System.out.printf("%sUsername:%s ", TextColor.BOLD_BRAN, TextColor.COLOR_RESET);
        try {
            String username = leitor.nextLine();
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
        System.out.printf("%sAdmin [A] ou user [U]?: %s", TextColor.BOLD_BRAN, TextColor.COLOR_RESET);
        try {
            return leitor.nextLine();
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
            leitor.nextLine();
            System.out.println();
            return opcao;
        } catch(ExcecaoLeitorFechado e) {
            e.printStackTrace();
            System.exit(1);
        }
        return 0;
    }

    public static String curso() {
        Leitor leitor = Leitor.getInstance();
        System.out.printf("Digite o nome do curso: ");
        try {
            String curso = leitor.nextLine();
            System.out.println();
            return curso;
        } catch(ExcecaoLeitorFechado e) {
            e.printStackTrace();
            System.exit(1);
        }
        return null;
    }

    public static int idQuestao() {
        Leitor leitor = Leitor.getInstance();
        System.out.printf("Digite o id da questão: ");
        int resp;

        try {
            resp = leitor.nextInt();
            leitor.nextLine();
            System.out.println();
            return resp;
        } catch(ExcecaoLeitorFechado e) {
            e.printStackTrace();
            System.exit(1);
        }

        return 0;
    }

    public static int idModulo() {
        Leitor leitor = Leitor.getInstance();
        System.out.printf("Digite o id do módulo: ");
        int resp;

        try {
            resp = leitor.nextInt();
            System.out.println();
            return resp;
        } catch(ExcecaoLeitorFechado e) {
            e.printStackTrace();
            System.exit(1);
        }

        return 0;
    }

    public static boolean desejaContinuar() {
        System.out.printf("%sDeseja continuar?%s%n", TextColor.BOLD_BRAN, TextColor.COLOR_RESET);
        System.out.println("[1] Sim");
        System.out.println("[2] Não\n");
        int resp = opcao();

        if(resp == 1) return true;
        else return false;
    }

    public static Object resposta(String tipo) {
        Leitor leitor = Leitor.getInstance();
        System.out.print("Digite sua resposta: ");
        Object resp = null;

        if(tipo.equals("MUL")) {
            try {
                resp = leitor.nextInt();
                leitor.nextLine();
            } catch(ExcecaoLeitorFechado e) {
                e.printStackTrace();
                System.exit(1);
            }
        }
        if(tipo.equals("DIS")) {
            try {
                resp = leitor.nextLine();
            } catch(ExcecaoLeitorFechado e) {
                e.printStackTrace();
                System.exit(1);
            }
        }
        System.out.println();
        return resp;
    }
}

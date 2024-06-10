public class Main {
    public static void main(String[] args) throws Exception { 
        Leitor leitor = Leitor.getInstance();
        Sistema sistema = Sistema.getInstance();

        // Primeira tela a aparecer
        // Executar em loop até que ESC seja pressionado
        System.out.printf("%sTela de login%s%n", TextColor.BOLD_BRAN, TextColor.COLOR_RESET);
        System.out.println("[1] Fazer login");
        System.out.println("[2] Cadastrar-se\n");
        System.out.println("[ESC] Sair\n");
        int alt = Solicita.opcao();
        

            // [1] Fazer login OU [2] Cadastrar-se
            if(alt == 1 || alt == 2) {    
                String tipoUser = Solicita.tipoUser();
                String username;
                String pswd;

                int tnt = 3;
                while((tnt--) != 0) {
                    username = Solicita.username();
                    pswd = Solicita.pswd();

                    IAdmin userAdmin;
                    if(tipoUser.equals("A")) {
                        IAdmin adminUser;
                        if(alt == 1) adminUser = sistema.fazerLoginAdmin(username, pswd);
                        
                    }
                }
            }  
            
            // [03] Sair
            {
                System.exit(0);
            }

        // [1] Fazer curso
        // 1. Exibir a lista de cursos.
        // 2. Pegar iD do curso
        // 3. Em loop, até que ESC seja pressionado, exibir módulos do curso
        // 4. Em loop, até que ESC seja pressionado, permanecer no módulo
        System.out.println("Qual curso gostaria de fazer agora?: ");

        // [02] Exibir cursos disponíveis


        leitor.close();
    }
}

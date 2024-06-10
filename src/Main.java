public class Main {
    private static Sistema sistema = Sistema.getInstance();
    private static Main main = new Main();

    public static void main(String[] args) throws Exception { 
        Leitor leitor = Leitor.getInstance();

        while(true) {    
            System.out.printf("%sTela de login%s%n", TextColor.BOLD_BRAN, TextColor.COLOR_RESET);
            System.out.println("[1] Fazer login");
            System.out.println("[2] Cadastrar-se\n");
            System.out.println("[3] Sair\n");
            
            int alt = Solicita.opcao();
            if(alt == 1 || alt == 2) {    
                String tipoUser = Solicita.tipoUser();
                String username;
                String pswd;

                int tnt = 3;
                while((tnt--) != 0) {
                    username = Solicita.username();
                    pswd = Solicita.pswd();

                    if(alt == 1) {
                        if(tipoUser.equals("U")) {
                            IUser commonUser = sistema.fazerLogin(username, pswd);
                            if(commonUser != null) { 
                                main.telaCommonUserLogado(commonUser);
                                break;
                            }
                            else Mensagens.notLogin();
                        }
                        else if(tipoUser.equals("A")) {
                            IAdmin adminUser = sistema.fazerLoginAdmin(username, pswd);
                            if(adminUser != null) {
                                main.telaAdminLogado(adminUser);
                                break;
                            }
                            else Mensagens.notLogin();
                        }
                    }
                    else if(alt == 2) {
                        if(tipoUser.equals("U")) {
                            sistema.regCommonUser(username, pswd);
                            main.telaCommonUserLogado(sistema.fazerLogin(username, pswd));
                            break;
                        }
                        else if(tipoUser.equals("A")) {
                            sistema.fazerLoginAdmin(username, pswd);
                            main.telaAdminLogado(sistema.fazerLoginAdmin(username, pswd));
                            break;
                        }
                    }
                }
            }  
            else if(alt == 3) {
                leitor.close();
                System.exit(0);
            }
        }
    }

    public void telaCommonUserLogado(IUser user) {
        Mensagens.boasVindas();
        while(true) {
            System.out.println("[1] Exibir cursos disponíveis");
            System.out.println("[2] Fazer curso");
            System.out.println("[3] Inscrever-se em um curso");
            System.out.println("[4] Sair de um curso\n");
            System.out.println("[5] Sair para tela de login\n");

            int alt = Solicita.opcao();
            if(alt == 1) {
                System.out.printf("%sLista de cursos:%s%n", TextColor.BOLD_BRAN, TextColor.COLOR_RESET);
                sistema.exibirCursos();
            }
            else if(alt == 2) {

            } 
            else if(alt == 3) {
                System.out.printf("%sLista de cursos:%s%n", TextColor.BOLD_BRAN, TextColor.COLOR_RESET);
                sistema.exibirCursos();
                user.inscreverCurso(Solicita.curso());
            }
            else if(alt == 4) {
                System.out.printf("%sMeus cursos:%s%n", TextColor.BOLD_BRAN, TextColor.COLOR_RESET);
                user.mostrarMeusCursos();
                user.sairCurso(Solicita.curso());
            }
            else if(alt == 5) {
                Mensagens.volteSempre();
                return;
            }
        }
    }

    public void telaAdminLogado(IAdmin admin) {
        Mensagens.boasVindas();
        while(true) {
            System.out.println("[1] Remover usuário");
            System.out.println("[2] Adicionar novo curso\n");
            System.out.println("[3] Sair para tela de login\n");

            int alt = Solicita.opcao();
            if(alt == 1) {
                System.out.printf("%sLista de usuários do sistema:%s%n", TextColor.BOLD_BRAN, TextColor.COLOR_RESET);
                admin.listarUsuarios();
                System.out.println();
                admin.removerUsuario(Solicita.username());
            }
            else if(alt == 2) {
                System.out.printf("%sLista de cursos que não estão no sistema:%s%n", TextColor.BOLD_BRAN, TextColor.COLOR_RESET);
                admin.listarCursos();
                admin.adicionarCurso(Solicita.curso());   
            }
            else if(alt == 3) {
                Mensagens.volteSempre();
                return;
            }
        }
    }
}

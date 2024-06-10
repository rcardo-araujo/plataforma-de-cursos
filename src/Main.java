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

                username = Solicita.username();
                pswd = Solicita.pswd();

                if(alt == 1) {
                    if(tipoUser.equals("U")) {
                        IUser commonUser = sistema.fazerLogin(username, pswd);
                        if(commonUser != null) { 
                            main.telaCommonUserLogado(commonUser);
<<<<<<< HEAD
                            continue;
=======
                            break;
>>>>>>> 7f2a301 (REBASE DO IGOR)
                        }
                        else Mensagens.notLogin();
                    }
                    else if(tipoUser.equals("A")) {
                        IAdmin adminUser = sistema.fazerLoginAdmin(username, pswd);
                        if(adminUser != null) {
                            main.telaAdminLogado(adminUser);
<<<<<<< HEAD
                            continue;
=======
                            break;
>>>>>>> 7f2a301 (REBASE DO IGOR)
                        }
                        else Mensagens.notLogin();
                    }
                }
                else if(alt == 2) {
                    if(tipoUser.equals("U")) {
                        sistema.regCommonUser(username, pswd);
                        main.telaCommonUserLogado(sistema.fazerLogin(username, pswd));
<<<<<<< HEAD
                        continue;
=======
>>>>>>> 7f2a301 (REBASE DO IGOR)
                    }
                    else if(tipoUser.equals("A")) {
                        sistema.regAdminUser(username, pswd);
                        main.telaAdminLogado(sistema.fazerLoginAdmin(username, pswd));
<<<<<<< HEAD
                        continue;
=======
                        
>>>>>>> 7f2a301 (REBASE DO IGOR)
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
                System.out.printf("%sMeus cursos:%s%n", TextColor.BOLD_BRAN, TextColor.COLOR_RESET);
                user.mostrarMeusCursos();
                String nomeCurso = Solicita.curso();
                
                System.out.println("[1] Continuar");
                System.out.println("[2] Escolher módulo");
                int alt2 = Solicita.opcao();
                if(alt == 2) user.listarModulos(nomeCurso);
                
                while(true) {    
                    if(alt2 == 1) {
                        user.fazerTarefa(nomeCurso);
                    }
                    else if(alt2 == 2) {
                        user.fazerTarefa(nomeCurso, Solicita.idModulo());
                    }

                    System.out.printf("%sDeseja continuar?%s%n", TextColor.BOLD_BRAN, TextColor.COLOR_RESET);
                    System.out.println("[1] Sim");
                    System.out.println("[2] Não");
                    if(Solicita.opcao() == 2) break;
                }
            } 
            else if(alt == 3) {
                System.out.printf("%sLista de cursos:%s%n", TextColor.BOLD_BRAN, TextColor.COLOR_RESET);
                sistema.exibirCursos();
                String s = Solicita.curso();
                System.out.println(s);
                System.out.println("bla");
                user.inscreverCurso(s);
            }
            else if(alt == 4) {
                System.out.printf("%sMeus cursos:%s%n", TextColor.BOLD_BRAN, TextColor.COLOR_RESET);
                user.mostrarMeusCursos();
                System.out.println();
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
            System.out.println("[2] Adicionar novo curso");
            System.out.println("[3] Remover curso");
            System.out.println("[4] Sair para tela de login\n");

            int alt = Solicita.opcao();
            if(alt == 1) {
                System.out.printf("%sLista de usuários do sistema:%s%n", TextColor.BOLD_BRAN, TextColor.COLOR_RESET);
                admin.listarUsuarios();
                System.out.println();
                admin.removerUsuario(Solicita.username());
            }
            else if(alt == 2) {
                System.out.printf("%sLista de cursos que NÃO estão no sistema:%s%n", TextColor.BOLD_BRAN, TextColor.COLOR_RESET);
                admin.listarCursos();
                System.out.println();
                admin.adicionarCurso(Solicita.curso());   
            }
            else if(alt == 3) {
                System.out.printf("%sCursos do sistema:%s%n", TextColor.BOLD_BRAN, TextColor.COLOR_RESET);
                sistema.exibirCursos();
                System.out.println();
                admin.removerCurso(Solicita.curso());
            }
            else if(alt == 4) {
                Mensagens.volteSempre();
                return;
            }
        }
    }
}

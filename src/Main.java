public class Main {
    private static Sistema sistema = Sistema.getInstance();
    private static Main main = new Main();

    public static void main(String[] args) throws Exception { 
        Leitor leitor = Leitor.getInstance();

        while(true) {    
            System.out.printf("%sTela de Login%s%n", TextColor.BOLD_AZUL, TextColor.COLOR_RESET);
            TextColor.linhasBrancas();
            System.out.println("[1] Fazer login");
            System.out.println("[2] Cadastrar-se\n");
            System.out.printf("[3] %sSair%s%n", TextColor.COLOR_VERM, TextColor.COLOR_RESET);
            TextColor.linhasBrancas();
            System.out.println();
            
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
                            continue;
                        }
                        else Mensagens.notLogin();
                    }
                    else if(tipoUser.equals("A")) {
                        IAdmin adminUser = sistema.fazerLoginAdmin(username, pswd);
                        if(adminUser != null) {
                            main.telaAdminLogado(adminUser);
                            continue;
                        }
                        else Mensagens.notLogin();
                    }
                    else {
                        Mensagens.opcaoInvalida();
                    }
                }
                else if(alt == 2) {
                    if(tipoUser.equals("U")) {
                        sistema.regCommonUser(username, pswd);
                        main.telaCommonUserLogado(sistema.fazerLogin(username, pswd));
                        continue;
                    }
                    else if(tipoUser.equals("A")) {
                        sistema.regAdminUser(username, pswd);
                        main.telaAdminLogado(sistema.fazerLoginAdmin(username, pswd));
                        continue;
                    }
                    else {
                        Mensagens.opcaoInvalida();
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
            System.out.printf("%sTela Inicial Usuário%s%n", TextColor.BOLD_AZUL, TextColor.COLOR_RESET);
            TextColor.linhasBrancas();
            System.out.println("[1] Exibir cursos disponíveis");
            System.out.println("[2] Fazer curso");
            System.out.println("[3] Inscrever-se em um curso");
            System.out.println("[4] Sair de um curso\n");
            System.out.printf("[5] %sSair para tela de login%s%n", TextColor.COLOR_VERM, TextColor.COLOR_RESET);
            TextColor.linhasBrancas();
            System.out.println();

            int alt = Solicita.opcao();
            if(alt == 1) {
                System.out.printf("%sCursos disponíveis:%s%n", TextColor.BOLD_BRAN, TextColor.COLOR_RESET);
                sistema.exibirCursos();
                System.out.println();
            }
            else if(alt == 2) {
                System.out.printf("%sMeus Cursos:%s%n", TextColor.BOLD_AZUL, TextColor.COLOR_RESET);
                TextColor.linhasBrancas();
                user.mostrarMeusCursos();
                TextColor.linhasBrancas();
                System.out.println();
                if(!user.cadastradoEmAlgumCurso()) continue;
                String nomeCurso = Solicita.curso();
                if(user.cadastradoNoCurso(nomeCurso)) {
                    System.out.println("[1] Continuar");
                    System.out.println("[2] Escolher módulo\n");
                    int altB = Solicita.opcao();
                    int idModulo = 1;

                    while(true) {  
                        if(altB == 2) {
                            user.listarModulos(nomeCurso);
                            System.out.printf("[5] %sVoltar%s%n%n", TextColor.COLOR_VERM, TextColor.COLOR_RESET);
                            idModulo = Solicita.opcao();
                            if(idModulo == 5) break;
                        }
                        while(true) {    
                            if(altB == 1) {
                                altB = 2;
                                user.fazerTarefa(nomeCurso);
                                if(!Solicita.desejaContinuar()) break;
                            }
                            else if(altB == 2) {
                                user.fazerTarefa(nomeCurso, idModulo);
                                if(!Solicita.desejaContinuar()) break;  
                            }
                        }
                    }
                }
            } 
            else if(alt == 3) {
                System.out.printf("%sLista de cursos:%s%n", TextColor.BOLD_BRAN, TextColor.COLOR_RESET);
                sistema.exibirCursos();
                System.out.println();
                user.inscreverCurso(Solicita.curso());
            }
            else if(alt == 4) {
                System.out.printf("%sMeus Cursos:%s%n", TextColor.BOLD_AZUL, TextColor.COLOR_RESET);
                TextColor.linhasBrancas();
                user.mostrarMeusCursos();
                TextColor.linhasBrancas();
                System.out.println();
                if(!user.cadastradoEmAlgumCurso()) continue;
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
            System.out.printf("%sTela Inicial Admin%s%n", TextColor.BOLD_AZUL, TextColor.COLOR_RESET);
            TextColor.linhasBrancas();
            System.out.println("[1] Remover usuário");
            System.out.println("[2] Adicionar novo curso");
            System.out.println("[3] Remover curso\n");
            System.out.printf("[4] %sSair para tela de login%s%n", TextColor.COLOR_VERM, TextColor.COLOR_RESET);
            TextColor.linhasBrancas();
            System.out.println();

            int alt = Solicita.opcao();
            if(alt == 1) {
                System.out.printf("%sLista de usuários do sistema:%s%n", TextColor.BOLD_AZUL, TextColor.COLOR_RESET);
                TextColor.linhasBrancas();
                admin.listarUsuarios();
                TextColor.linhasBrancas();
                System.out.println();
                if(!admin.temUsuariosCadastrados()) continue;
                String username = Solicita.username();
                System.out.println();
                admin.removerUsuario(username);
            }
            else if(alt == 2) {
                System.out.printf("%sLista de cursos disponíveis:%s%n", TextColor.BOLD_BRAN, TextColor.COLOR_RESET);
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

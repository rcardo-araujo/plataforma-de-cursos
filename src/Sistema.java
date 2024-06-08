import java.io.*;
import java.util.Collection;
import java.util.ArrayList;

public class Sistema {
    public class AdminUser extends AUser implements IAdmin {
        Sistema s;
        private AdminUser(String pswd, String username){
            super(pswd, username);
            this.s = instancia;
        }

        @Override
        public void removerCurso(String curso) {
            for(Curso c: cursos){
                if(c.getNome() == curso) cursos.remove(c);
            }
        }

        @Override
        public void adicionarCurso(String curso) {
            for(Curso c: cursos){
                if(c.getNome() == curso) return;
            }
            cursos.add(new Curso(curso));
        }

        @Override
        public void removerUsuario(String username) {
            for(CommonUser u: users){
                if(u.getUsername() == username) users.remove(u);
            }
        }
    }

    private class CommonUser extends AUser implements IUser {
        Collection<GerenciaCurso> meusCursos;
        private CommonUser(String pswd, String username){
            super(pswd, username);
        }

        private boolean existe(){
            for(IUser u: users){
                if(u == this) return true;
            }
            return false;
        }
    
        @Override
        public void inscreverCurso(String curso){
            if(!this.existe()) return;
            for(GerenciaCurso c: meusCursos){
                if(c.getNomeCurso() == curso){
                    System.out.println("Você já está inscrito!");
                    return;
                }
            }
            for(Curso c: cursos){
                if(c.getNome() == curso){
                    System.out.println("Você foi inscrito no curso " + curso);
                    this.meusCursos.add(new GerenciaCurso(c));
                    break;
                }
            }
        }
    
        @Override
        public void sairCurso(String curso){
            if(!this.existe()) return;
            for(GerenciaCurso c: meusCursos){
                if(c.getNomeCurso() == curso) meusCursos.remove(c);
                System.out.println("Você saiu do curso " + curso + " com sucesso!");
                break;
            }
        }
    }

    private static Sistema instancia = null;
    private Collection<CommonUser> users;
    private Collection<AdminUser> admins;
    private Collection<Curso> cursos;
    private String logName;

    private Sistema(){
        this.users = new ArrayList<>();
        this.admins = new ArrayList<>();
        this.cursos = new ArrayList<>();
        this.logName = "log.bin";
    }

    public static Sistema getInstance(){
        if(instancia==null){
            instancia = new Sistema();
        }
        return instancia;
    }

    public IUser fazerLogin(String username, String password){
        for (CommonUser a: users){
            if(a.getUsername() == username && a.getPassword() == password) return a;
        }
        return null;
    }

    public IAdmin fazerLoginAdmin(String username, String password){
        for (AdminUser a: admins){
            if(a.getUsername() == username && a.getPassword() == password) return a;
        }
        return null;
    }

    public void regCommonUser(String username, String password){
        this.users.add(new CommonUser(password, username));
    }

    public void regAdminUser(String username, String password){
        this.admins.add(new AdminUser(password, username));
    }

    public void exibirCursos(){
        for(Curso c: cursos){
            System.out.println(c.getNome());
        }
    }

    private void logAtividade(String userClass, String action){
        try{
            FileOutputStream log = new FileOutputStream(logName, true);
            String data = new java.util.Date().toString();
            log.write(data.getBytes());
            log.write(userClass.getBytes());
            log.write(": ".getBytes());
            log.write(action.getBytes());
            log.write("\\".getBytes());
            log.close();
        } catch(IOException e){
            e.printStackTrace();
        }
    }
}

import java.io.*;
import java.util.ArrayList;
import java.util.Collection;

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
            logAtividade(this.getClass().getName(), "removeu um curso");
        }

        @Override
        public void adicionarCurso(String curso) {
            for(Curso c: cursos){
                if(c.getNome() == curso) return;
            }
            cursos.add(new Curso(curso));
            logAtividade(this.getClass().getName(), "adicionou um curso");
        }

        @Override
        public void removerUsuario(String username) {
            for(CommonUser u: users){
                if(u.getUsername() == username) users.remove(u);
            }
            logAtividade(this.getClass().getName(), "removeu um usuário");
        }
    }

    private class CommonUser extends AUser implements IUser {
        Collection<GerenciaCurso> meusCursos;
        private CommonUser(String pswd, String username){
            super(pswd, username);
            this.meusCursos = new ArrayList<>();
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
                if(c.getNomeCurso().equals(curso)){
                    System.out.println("Você já está inscrito!");
                    return;
                }
            }
            for(Curso c: cursos){
                if(c.getNome().equals(curso)){
                    System.out.println("Você foi inscrito no curso " + curso);
                    this.meusCursos.add(new GerenciaCurso(c));
                    break;
                }
            }
            logAtividade(this.getUsername(), "se inscreveu em um curso");
        }
    
        @Override
        public void sairCurso(String curso){
            if(!this.existe()) return;
            for(GerenciaCurso c: meusCursos){
                if(c.getNomeCurso().equals(curso)) meusCursos.remove(c);
                System.out.println("Você saiu do curso " + curso + " com sucesso!");
                break;
            }
            logAtividade(this.getUsername(), "saiu de um curso");
        }

        @Override
        public void mostrarMeusCursos(){
            for(GerenciaCurso c: this.meusCursos){
                System.out.println(c.getNomeCurso());
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
        try{
            File dir = new File("./Cursos/");
            File[] lista = dir.listFiles();
            if(lista != null){
                for (File f: lista){
                    cursos.add(new Curso(f.getName()));
                }
            }
        } catch(Exception e){
            e.printStackTrace();
            System.exit(1);
        }
    }

    public static Sistema getInstance(){
        if(instancia==null){
            instancia = new Sistema();
        }
        return instancia;
    }

    public IUser fazerLogin(String username, String password){
        for (CommonUser a: users){
            if(a.getUsername().equals(username) && a.getPassword().equals(password)) return a;
        }
        return null;
    }

    public IAdmin fazerLoginAdmin(String username, String password){
        for (AdminUser a: admins){
            if(a.getUsername().equals(username) && a.getPassword().equals(password)) return a;
        }
        return null;
    }

    public void regCommonUser(String username, String password){
        this.users.add(new CommonUser(password, username));
    }

    public void regAdminUser(String username, String password){
        AdminUser adm = new AdminUser(password, username);
        this.admins.add(adm);
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

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
        public void removerCurso(String nomeCurso) {
            Curso curso = null;
            for(Curso c: cursos){
                if(c.getNome().equals(nomeCurso)) curso = c;
            }
            if(curso == null) {
                Mensagens.cursoInexistente();
                return;
            }

            cursos.remove(curso);
            File oldDir = new File("./Cursos/Sistema/"+nomeCurso);
            File newDir = new File("./Cursos/"+nomeCurso);
            if(newDir.mkdir()){
                File[] files = oldDir.listFiles();
                int k = 0;
                while(k < files.length){
                    try {
                        FileReader f = new FileReader(files[k]);
                        File dest =  new File("./Cursos/"+ nomeCurso + "/" + files[k].getName());
                        FileWriter d = new FileWriter(dest);
                        while(true){
                            int i = f.read();
                            if(i < 0) break;
                            d.write(i);
                        }
                        f.close();
                        files[k].delete();
                        d.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    oldDir.delete();
                    k++;
                }
            } else {
                System.out.println("ERRO TOTAL");
            }
        }

        @Override
        public void adicionarCurso(String nomeCurso) {
            for(Curso c: cursos){
                if(c.getNome().equals(nomeCurso)) return;
            }
            cursos.add(new Curso(nomeCurso));
            File newDir = new File("./Cursos/Sistema/"+ nomeCurso);
            File oldDir = new File("./Cursos/"+ nomeCurso);
            if(newDir.mkdir()){
                File[] files = oldDir.listFiles();
                int k = 0;
                while(k < files.length){
                    try {
                        FileReader f = new FileReader(files[k]);
                        FileWriter d = new FileWriter("./Cursos/Sistema/"+nomeCurso+"/"+files[k].getName());
                        while(true){
                            int i = f.read();
                            if(i < 0) break;
                            d.write(i);
                        }
                        f.close();
                        files[k].delete();
                        d.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    k++;
                }
                oldDir.delete();
                logAtividade(this.getUsername(), "adicionou um curso");
            } else {
                System.out.println("ERRO TOTAL");
            }
        }

        @Override
        public void removerUsuario(String username) {
            for(CommonUser u: users){
                if(u.getUsername().equals(username)) {
                    users.remove(u);
                    System.out.printf("Usuário %s removido com sucesso!%n%n", username);
                    logAtividade(this.getUsername(), "removeu um usuário");
                    break;
                }
            }
            Mensagens.usuarioInexistente();
        }

        @Override
        public void listarUsuarios() {
            for(CommonUser user : users) {
                System.out.println(user.getUsername());
            }
        }

        @Override
        public void listarCursos() {
            File f = new File("./Cursos/");
            String[] nomeCursos = f.list();
            for(String curso : nomeCursos) {
                if(curso.equals("Sistema")) continue;
                System.out.println(curso);
            }
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
                    Mensagens.jaInscritoNoCurso();;
                    return;
                }
            }
            for(Curso c: cursos){
                if(c.getNome().equals(curso)){
                    Mensagens.foiInscritoNoCurso(curso);
                    this.meusCursos.add(new GerenciaCurso(c));
                    logAtividade(this.getUsername(), "se inscreveu em um curso");
                    return;
                }
            }
            Mensagens.cursoInexistente();
        }
    
        @Override
        public void sairCurso(String curso){
            if(!this.existe()) return;
            for(GerenciaCurso c: meusCursos){
                if(c.getNomeCurso().equals(curso)) { 
                    meusCursos.remove(c);
                    System.out.println("Você saiu do curso " + curso + " com sucesso!\n");
                    logAtividade(this.getUsername(), "saiu de um curso");
                    return;
                }
            }
            Mensagens.cursoInexistente();
        }

        @Override
        public void mostrarMeusCursos(){
            if(meusCursos.size() == 0) {
                Mensagens.semCadastroEmCursos();
                return;
            }
            
            for(GerenciaCurso c : this.meusCursos){
                System.out.println(c.getNomeCurso());
            }
        }

        @Override
        public void fazerTarefa(String nomeCurso){
            GerenciaCurso c = null;
            for (GerenciaCurso cur: meusCursos){
                if(cur.getNomeCurso().equals(nomeCurso)) {c = cur; break;}
            }
            if(c==null) {Mensagens.cursoInexistente(); return;}
            c.fazerModulo(c.getNivel());
            logAtividade(this.getUsername(), "fez sua tarefa atual");
        }

        @Override
        public void fazerTarefa(String nomeCurso, int id){
            GerenciaCurso c = null;
            for (GerenciaCurso cur: meusCursos){
                if(cur.getNomeCurso().equals(nomeCurso)) {c = cur; break;}
            }
            if(c==null) {Mensagens.cursoInexistente(); return;}
            c.fazerModulo(id);
            logAtividade(this.getUsername(), "fez uma tarefa");
        }

        @Override
        public void listarModulos(String nomeCurso) {
            GerenciaCurso curso = null;
            for(GerenciaCurso c : meusCursos) {
                if(c.getNomeCurso().equals(nomeCurso)) {
                    curso = c;
                    break;
                }
            }
            
            if(curso == null) {
                Mensagens.cursoInexistente();
                return;
            }
            curso.imprimeInterfaceCurso();
            logAtividade(this.getUsername(), "listou os módulos do curso " + nomeCurso);
        }

        @Override
        public boolean temCurso(String nomeCurso) {
            for(GerenciaCurso gc : meusCursos) {
                if(gc.getNomeCurso().equals(nomeCurso)) return true;
            }
            Mensagens.naoTemCurso();
            return false;
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
            File dir = new File("./Cursos/Sistema");
            File[] lista = dir.listFiles();
            if(lista != null){
                for (File f: lista){
                    cursos.add(new Curso("Sistema/" + f.getName()));
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
        try (FileOutputStream log = new FileOutputStream(logName, true)) {
            String data = new java.util.Date().toString();
            log.write(data.getBytes());
            log.write(userClass.getBytes());
            log.write(": ".getBytes());
            log.write(action.getBytes());
            log.write("\n".getBytes());
            log.close();
        } catch(IOException e){
            e.printStackTrace();
        }
    }
}

import java.io.*;
import java.nio.charset.StandardCharsets;
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
                Mensagens.cursoInexistente(nomeCurso);
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
                        FileInputStream fi = new FileInputStream(files[k]);
                        InputStreamReader is = new InputStreamReader(fi, StandardCharsets.UTF_8);
                        BufferedReader br = new BufferedReader(is);
                        
                        File dest =  new File("./Cursos/"+ nomeCurso + "/" + files[k].getName());
                        FileOutputStream fo = new FileOutputStream(dest);
                        OutputStreamWriter os = new OutputStreamWriter(fo, StandardCharsets.UTF_8);
                        BufferedWriter bw = new BufferedWriter(os);
                        while(true){
                            String questao = br.readLine();
                            if(questao == null) break;
                            bw.write(questao + "\n");
                        }
                        br.close();
                        is.close();
                        fi.close();
                        bw.close();
                        os.close();
                        fo.close();
                        files[k].delete();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    k++;
                }
                oldDir.delete();
                Mensagens.cursoRemovidoDoSistema(nomeCurso);
                logAtividade(this.getUsername(), "removeu curso " + nomeCurso + " do sistema");
            } else {
                Mensagens.erroTotal();
            }
        }

        @Override
        public void adicionarCurso(String nomeCurso) {
            for(Curso c: cursos){
                if(c.getNome().equals(nomeCurso)) return;
            }
            File oldDir = new File("./Cursos/"+ nomeCurso);
            if(!oldDir.exists()) {
                Mensagens.cursoInexistente(nomeCurso);
                return;
            }
            cursos.add(new Curso(nomeCurso));
            File newDir = new File("./Cursos/Sistema/"+ nomeCurso);
            if(newDir.mkdir()){
                File[] files = oldDir.listFiles();
                int k = 0;
                while(k < files.length){
                    try {
                        FileInputStream fi = new FileInputStream(files[k]);
                        InputStreamReader is = new InputStreamReader(fi, StandardCharsets.UTF_8);
                        BufferedReader br = new BufferedReader(is);
                        
                        FileOutputStream fo = new FileOutputStream("./Cursos/Sistema/" + nomeCurso + "/"+files[k].getName());
                        OutputStreamWriter os = new OutputStreamWriter(fo, StandardCharsets.UTF_8);
                        BufferedWriter bw = new BufferedWriter(os);
                        while(true){
                            String questao = br.readLine();
                            if(questao == null) break;
                            bw.write(questao + "\n");
                        }
                        br.close();
                        is.close();
                        fi.close();
                        files[k].delete();
                        bw.close();
                        os.close();
                        fo.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    k++;
                }
                oldDir.delete();
                Mensagens.cursoAdicionadoAoSistema(nomeCurso);
                logAtividade(this.getUsername(), "adicionou o curso " + nomeCurso + " ao sistema");
            } else {
                Mensagens.erroTotal();
            }
        }

        @Override
        public void removerUsuario(String username) {
            for(CommonUser u: users){
                if(u.getUsername().equals(username)) {
                    users.remove(u);
                    armazenarUsuarios();
                    File f = new File("./users/"+username);
                    f.delete();
                    Mensagens.usuarioRemovido(username);
                    logAtividade(this.getUsername(), "removeu o usuário " + username + " do sistema");
                    return;
                }
            }
            Mensagens.usuarioInexistente();
        }

        @Override
        public void listarUsuarios() {
            if(users.size() == 0) {
                Mensagens.semUsuarioCadastrado();
                return;
            }
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

        @Override
        public boolean temUsuariosCadastrados() {
            return users.size() != 0;
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
                    this.meusCursos.add(new GerenciaCurso(new Curso("Sistema/" + curso)));
                    logAtividade(this.getUsername(), "se inscreveu no curso " + curso);
                    armazenarUsuarios();
                    return;
                }
            }
            Mensagens.cursoInexistente(curso);
        }
    
        @Override
        public void sairCurso(String curso){
            if(!this.existe()) return;
            for(GerenciaCurso c: meusCursos){
                if(c.getNomeCurso().equals(curso)) { 
                    meusCursos.remove(c);
                    Mensagens.cursoRemovido(curso);
                    logAtividade(this.getUsername(), "saiu do curso " + curso);
                    armazenarUsuarios();
                    return;
                }
            }
            Mensagens.cursoInexistente(curso);
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
            if(c==null) {Mensagens.cursoInexistente(nomeCurso); return;}
            c.fazerModulo(c.getNivel());
            armazenarUsuarios();
            logAtividade(this.getUsername(), "fez sua tarefa atual do curso de " + nomeCurso);
        }

        @Override
        public void fazerTarefa(String nomeCurso, int id){
            GerenciaCurso c = null;
            for (GerenciaCurso cur: meusCursos){
                if(cur.getNomeCurso().equals(nomeCurso)) {c = cur; break;}
            }
            if(c==null) {Mensagens.cursoInexistente(nomeCurso); return;}
            c.fazerModulo(id);
            armazenarUsuarios();
            if(id <= c.getNivel() && id > 0) logAtividade(this.getUsername(), "acessou o módulo " + c.getCurso().buscarModulo(id).getNomeModulo() + " do curso " + nomeCurso);
            else if(id == 0) logAtividade(this.getUsername(), "acessou o módulo de revisão curso " + nomeCurso);
            else logAtividade(this.getUsername(), "tentou acessar o módulo " + c.getCurso().buscarModulo(id).getNomeModulo() + " do curso " + nomeCurso);
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
                Mensagens.cursoInexistente(nomeCurso);
                return;
            }
            curso.imprimeInterfaceCurso();
            logAtividade(this.getUsername(), "listou os módulos do curso " + nomeCurso);
        }

        @Override
        public boolean cadastradoNoCurso(String nomeCurso) {
            for(GerenciaCurso gc : meusCursos) {
                if(gc.getNomeCurso().equals(nomeCurso)) return true;
            }
            Mensagens.naoTemCurso();
            return false;
        }

        @Override
        public boolean cadastradoEmAlgumCurso() {
            return meusCursos.size() != 0;
        }

        private Collection<String> desempenhoCursos(){
            Collection<String> desempenhos = new ArrayList<>();
            for(GerenciaCurso c: meusCursos){
                String s = c.getNomeCurso() + ":" + c.getNivel() + ":" + c.getPontos();
                desempenhos.add(s);
            }
            return desempenhos;
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

        this.admins = loadAdmins();
        this.users = loadUsers();
    }

    public static Sistema getInstance(){
        if(instancia==null){
            instancia = new Sistema();
        }
        return instancia;
    }

    public IUser fazerLogin(String username, String password){
        for (CommonUser a: users){
            if(a.getUsername().equals(username) && a.getPassword().equals(password)) {
                logAtividade(username, "fez login");
                return a;
            }
        }
        return null;
    }

    public IAdmin fazerLoginAdmin(String username, String password){
        for (AdminUser a: admins){
            if(a.getUsername().equals(username) && a.getPassword().equals(password)) {
                logAtividade(username, "fez login");
                return a;
            }
        }
        return null;
    }

    public void regCommonUser(String username, String password){
        logAtividade(username, "se cadastrou na plataforma");
        this.users.add(new CommonUser(password, username));
        armazenarUsuarios();
    }

    public void regAdminUser(String username, String password){
        logAtividade(username, "se cadastrou na plataforma");
        AdminUser adm = new AdminUser(password, username);
        this.admins.add(adm);
        armazenarUsuarios();
    }

    public void exibirCursos(){
        for(Curso c: cursos){
            System.out.println(c.getNome());
        }
    }

    private void logAtividade(String userClass, String action){
        try (FileOutputStream log = new FileOutputStream(logName, true)) {
            String data = new java.util.Date().toString();
            log.write((data + " ").getBytes());
            log.write(userClass.getBytes());
            log.write(": ".getBytes());
            log.write(action.getBytes());
            log.write("\n".getBytes());
            log.close();
        } catch(IOException e){
            e.printStackTrace();
        }
    }

    private void armazenarUsuarios(){
        String s;
        try(FileWriter f = new FileWriter("admins.txt")){
            for(AdminUser u: admins){
                s = u.getUsername() + ":" +u.getPassword() + "\n";
                f.write(s);
            }
            f.close();
        } catch(Exception e){
            e.printStackTrace();
        }
        try(FileWriter f = new FileWriter("users.txt")){
            for(CommonUser u: users){
                s = u.getUsername() + ":" + u.getPassword() + "\n";
                f.write(s);
                f.close();
                try (FileWriter f2 = new FileWriter("./users/"+u.getUsername())){
                    Collection<String> desempenhos = u.desempenhoCursos();
                    for (String d: desempenhos){
                        f2.write(d +"\n");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch(Exception e){
            e.printStackTrace();
        }
    }

    private Collection<AdminUser> loadAdmins(){
        Collection<AdminUser> lAdmins = new ArrayList<>();
        try(FileReader f = new FileReader("./admins.txt")){
            int i = f.read();
            while(i != -1){
                char c = (char) i;
                String nome = "";
                while(c != ':'){
                    nome += c;
                    c = (char) f.read();
                }
                c = (char) f.read();
                String password = "";
                while(c != '\n' && c != -1){
                    password += c;
                    c = (char) f.read();
                }
                lAdmins.add(new AdminUser(password, nome));
                if(c == -1) break;
                i = f.read();
            }

        } catch (Exception e) {
        }
        return lAdmins;
    }
    private Collection<CommonUser> loadUsers(){
        Collection<CommonUser> lUsers = new ArrayList<>();
        try(FileReader f = new FileReader("./users.txt")){
            int i = f.read();
            while(i != -1){
                char c = (char) i;
                String nome = "";
                while(c != ':'){
                    nome += c;
                    c = (char) f.read();
                }
                c = (char) f.read();
                String password = "";
                while(c != '\n' && c != -1){
                    password += c;
                    c = (char) f.read();
                }
                CommonUser userr = new CommonUser(password, nome);
                lUsers.add(userr);
                try (FileReader f2 = new FileReader("./users/"+nome)){
                    int j = f2.read();
                    while(j != -1){
                        char k = (char) j;
                        String nomeCurso = "";
                        while(k != ':'){
                            nomeCurso += k;
                            k = (char) f2.read();
                        }
                        k = (char) f2.read();
                        String nivel = "";
                        while(k != ':'){
                            nivel += k;
                            k = (char) f2.read();
                        }
                        k = (char) f2.read();
                        String pontos = "";
                        while(k != '\n' && k != -1){
                            pontos += k;
                            k = (char) f2.read();
                        }
                        userr.inscreverCurso(nomeCurso);
                        for(GerenciaCurso gc : userr.meusCursos){
                            if(gc.getNomeCurso().equals(nomeCurso)) {
                                gc.setNivel(Integer.parseInt(nivel));
                            }
                        }
                        if(k == -1) break;
                        j = f2.read();
                    }
                } catch (Exception e) {
                }
                if(c == -1) break;
                i = f.read();
            }

        } catch (Exception e) {
        }
        return lUsers;
    }
}

public class Main {
    public static void main(String[] args) throws Exception { 
        /*GerenciaCurso g = new GerenciaCurso(new Curso("Futebol"));
        g.imprimeInterfaceCurso();
        g.fazerModulo(2); */
        Sistema s = Sistema.getInstance();
        s.exibirCursos();
        s.regAdminUser("igor", "igorrodrigues");
        IUser u = s.fazerLogin("igor", "igorrodrigues");
        u.inscreverCurso("Futebol");
        u.mostrarMeusCursos();
        
        // IAdmin user;
        // Sistema s = Sistema.getInstance();
        // s.regAdminUser("igor", "igor12345");
        // user = s.fazerLoginAdmin("igor", "igor12345");
        // user.adicionarCurso("facebook");
        // user.adicionarCurso("instagram");
        // s.exibirCursos();
        // user.removerCurso("facebook");
        // s.exibirCursos();
    }
}

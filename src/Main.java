public class Main {
    public static void main(String[] args) throws Exception {
        IAdmin user;
        Sistema s = Sistema.getInstance();
        s.regAdminUser("igor", "igor12345");
        user = s.fazerLoginAdmin("igor", "igor12345");
        user.adicionarCurso("facebook");
        user.adicionarCurso("instagram");
        s.exibirCursos();
        user.removerCurso("facebook");
        s.exibirCursos();
    }
}
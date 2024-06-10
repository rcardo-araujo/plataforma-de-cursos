public interface IAdmin {
    public void removerCurso(String curso);
    public void adicionarCurso(String curso);
    public void removerUsuario(String username);
    public void listarUsuarios();
    public void listarCursos();
    public boolean temUsuariosCadastrados();
}
public interface IUser{
    public void inscreverCurso(String curso);
    public void sairCurso(String curso);
    public void mostrarMeusCursos();
    public void fazerTarefa(String nomeCurso);
    public void fazerTarefa(String nomeCurso, int id);
}
public class Main {
    public static void main(String[] args) throws Exception {
        ModuloComum mod = new ModuloComum(1, "Geografia");
        mod.imprimeMod();
        mod.toDo(1);
        mod.toDo(2);
        mod.toDo(3);
        
        if(mod.modCompleto()) {
            System.out.printf("Módulo completo 100%s", "%");
        } else System.out.printf("Módulo NÃO está completo!");
    }
}

import java.io.*;
import java.util.Collection;
import java.util.ArrayList;

public class Sistema {
    private class CommonUser extends AUser {
        public CommonUser(String pswd, String username){
            super(pswd, username);
        }
    }
    private Collection<AUser> users;
    private Collection<Curso> cursos;

    public Sistema(){

    }

    public AUser fazerLogin(String username, String password){
        try{
            return new CommonUser(password, username);
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}

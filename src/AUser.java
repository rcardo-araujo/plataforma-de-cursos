public abstract class AUser {
    private String username;
    private String pswd;
    public AUser(String pswd, String username){
        this.username = username;
        this.pswd = pswd;
    }

    public String getUsername(){
        return this.username;
    }

    public String getPassword(){
        return this.pswd;
    }
    
    //public abstract void inscrever(String s);
}

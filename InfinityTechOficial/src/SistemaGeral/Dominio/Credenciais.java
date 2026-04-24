package SistemaGeral.Dominio;

public class Credenciais {
   public String login ;
   public String senha ;

   public Credenciais (String login, String senha){
       this.login = login;
       this.senha = senha;


   }
   public void login(){
       System.out.println("Login: " + login);

       System.out.println("Senha: " + senha);

   }
    public void setLogin(String login) {
        this.login = login;
    }
    public void setSenha(String Senha) {
        this.senha = senha;
    }
    public String getLogin(){
       return login;
    }
    public String getSenha(){
       return senha;
    }

    }


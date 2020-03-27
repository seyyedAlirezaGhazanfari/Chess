import java.util.ArrayList;

public class RegisteringMenu {
public static boolean register(String username ,String password){
    for (Participator participator : Participator.getParticipators()) {
        if (participator.getUsername().equals(username)){
            System.out.println("a user exist with this username");
            return false;
        }
    }
    Participator participator = new Participator(username, password);
    System.out.println("register was successfully");
    return true;
}
public static boolean login(String username , String password){
    if (checkExistenceOfUser(username, password)) return false;
    System.out.println("login successful");
    return true;
}
    public static boolean checkExistenceOfUser(String username, String password) {
        if (Participator.weHaveThisPlayer(username)==null){
            System.out.println("no user exist with this username");
            return true;
        }
        if (!password.equals(Participator.weHaveThisPlayer(username))){
            System.out.println("pass is incorrect");
            return true;
        }
        return false;
    }
    public static boolean remove(String username , String password){
        if (checkExistenceOfUser(username, password)) return false;
       if (Participator.removePlayer(username,password)) {
           return true;
       }
       return false;
    }
    public static boolean listUsers(){
    String[] usernams = Participator.arrangeUserList( Participator.extractUsernamesList(Participator.getParticipators()));
        for (String usernam : usernams) {
            System.out.println(usernam);
        }
    return  true;
    }
    public static boolean help(){
        System.out.println("register [username] [password]\nlogin [username] [password]\nremove [username] [password]\nlist_users\nhelp\nexit");
        return true;
    }
}

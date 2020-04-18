public class OriginalMenu {
    public static boolean help() {
        System.out.println("new_game [username] [limit]\nscoreboard\nlist_users\nhelp\nlogout");
        return true;
    }

    public static boolean logout(Participator participator) {
        if (participator.attention = true) {
            participator.attention = false;
            System.out.println("logout successful");
            return true;

        }
        return false;
    }
}

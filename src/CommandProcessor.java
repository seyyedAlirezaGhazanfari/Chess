import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class CommandProcessor {
    private static Participator participator;
    public static void run() {
        final int MAX_INF = 10000;
        boolean letGoToMovePart = false;
        Scanner scanner = new Scanner(System.in);
        Matcher matcher;
        int helpSpecifier = 0;
        while (true) {
            String command = scanner.nextLine();
            String[] commandSplits = command.split("\\s");
            if (command.startsWith("register")) {
                if (checkUsernameAndPasswordFormat(commandSplits)) continue;
                RegisteringMenu.register(commandSplits[1], commandSplits[2]);
            }
            if (command.startsWith("login")) {
                if (checkUsernameAndPasswordFormat(commandSplits)) continue;
                if (RegisteringMenu.login(commandSplits[1], commandSplits[2])) {
                    participator = Participator.getParticipatorByUsername(commandSplits[1]);
                    participator.attention = true;
                    helpSpecifier = 1;
                }
            }
            if (command.startsWith("remove")) {
                if (checkUsernameAndPasswordFormat(commandSplits)) continue;
                if (RegisteringMenu.remove(commandSplits[1], commandSplits[2])) {
                    System.out.println("remove successfully");
                }
            }
            if (command.startsWith("list_users")) {
                RegisteringMenu.listUsers();
            }
            if (command.startsWith("help")) {
                if (helpSpecifier == 0)
                    RegisteringMenu.help();
                else if (helpSpecifier == 1)
                    OriginalMenu.help();
                else
                    GameMenu.help();
            }
            if (command.startsWith("exit")) {
                System.out.println("Program ended");
                break;
            }
            if (command.startsWith("new_game")) {
                if (!doesItHaveTrueFormat(commandSplits[1])) {
                    System.out.println("invalid format of username");
                    continue;
                }
                int limit = Integer.parseInt(commandSplits[2]);
                if (limit < 0) {
                    System.out.println("number should be positive to have a limit or 0 for no limit");
                    continue;
                }
                if (participator.getUsername().equals(commandSplits[1])) {
                    System.out.println("you must choose another player to start a game");
                    continue;
                }
                if (Participator.getParticipatorByUsername(commandSplits[1]) == null) {
                    System.out.println("no user exists with this username");
                    continue;
                }
                System.out.println("new game started successfully between " + participator.getUsername() + " and " + commandSplits[1] + " with limit " + limit);
                helpSpecifier = 2;
                participator.setColor("W");
                Participator participator1 = Participator.getParticipatorByUsername(commandSplits[1]);
                participator1.setColor("B");
                if (limit == 0)
                    limit = MAX_INF;
                GameMenu gameMenu = new GameMenu(participator,participator1);
            }
            if (command.startsWith("logout")) {
                if (OriginalMenu.logout(participator)) {
                    helpSpecifier = 0;
                    System.out.println("logout successful");
                }

            }
            if (command.startsWith("scoreboard")) {
                Participator.arrangeByScore(Participator.getParticipators());
            }
            matcher = getMatcher(command,"select (\\d+),(\\d+)");
            if (matcher.find()){
                GameMenu.select(Integer.parseInt(matcher.group(1)),Integer.parseInt(matcher.group(2)));
            }
            if (command.startsWith("deselect")){
                GameMenu.deslect();
            }
            matcher = getMatcher(command,"move (\\d+),(\\d+)");
            if (matcher.find()){
                GameMenu.move(Integer.parseInt(matcher.group(1)),Integer.parseInt(matcher.group(2)));
            }
            if (command.equals("next_turn")){
               if (!GameMenu.next_turn())
                   continue;
            }
            if (command.equals("show_turn")){
                GameMenu.showTurn();
            }
            if (command.equals("undo")){
                GameMenu.undo();
            }
            if (command.equals("undo_turn")){
                GameMenu.showUndoNumber();
            }
            if (command.equals("show_moves")){
                GameMenu.showMovesOfThisPlayer();
            }
            if (command.equals("show_moves-all")){
                GameMenu.showMoveAll();
            }
            if (command.equals("show_killed")){
                GameMenu.showKilledPieceOfThisPlayer();
            }
            if (command.equals("show_killed-all")){
                GameMenu.showKilledAll();
            }
        }
    }
    public static boolean checkUsernameAndPasswordFormat(String[] commandSplits) {
        if (!doesItHaveTrueFormat(commandSplits[1])) {
            System.out.println("username format is invalid");
            return true;
        }
        if (!doesItHaveTrueFormat(commandSplits[2])) {
            System.out.println("password format is invalid");
            return true;
        }
        return false;
    }
    private static Matcher getMatcher(String command, String regex) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(command);
        return matcher;
    }
    private static boolean doesItHaveTrueFormat(String input) {
        if (input.matches("^(\\w+|_+)"))
            return true;
        return false;
    }
}

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommandProcessor {
    private static Participator participator;
    private static Scanner scanner = new Scanner(System.in);

    private static Matcher getMatcher(String command, String regex) {
        Pattern pattern = Pattern.compile(regex);
        return pattern.matcher(command);
    }

    public static void run() {
        Matcher matcher;
        int positionOfUserInMenus = 1;
        while (true) {
            boolean isInThisLoopAnyValidCommand = false;

            String command = scanner.nextLine();
            if (positionOfUserInMenus == 1) {
                matcher = getMatcher(command, "register (\\S+) (\\S+)");
                if (matcher.matches()) {
                    if (!matcher.group(1).matches("\\w+")) {
                        System.out.println("username format is invalid");
                        continue;
                    }
                    if (!matcher.group(2).matches("\\w+")) {
                        System.out.println("password format is invalid");
                        continue;
                    }
                    matcher = getMatcher(command, "register (\\w+) (\\w+)");
                    if (matcher.matches()) {
                        RegisteringMenu.register(matcher.group(1), matcher.group(2));
                    }
                    continue;

                }
                matcher = getMatcher(command, "login (\\S+) (\\S+)");
                if (matcher.matches()) {
                    if (!matcher.group(1).matches("\\w+")) {
                        System.out.println("username format is invalid");
                        continue;
                    }
                    if (!matcher.group(2).matches("\\w+")) {
                        System.out.println("password format is invalid");
                        continue;
                    }
                    if (RegisteringMenu.login(matcher.group(1), matcher.group(2))) {
                        participator = Participator.getParticipatorByUsername(matcher.group(1));
                        positionOfUserInMenus = 2;
                        continue;
                    }
                    continue;
                }
                matcher = getMatcher(command, "remove (\\S+) (\\S+)");
                if (matcher.matches()) {
                    if (!matcher.group(1).matches("\\w+")) {
                        System.out.println("username format is invalid");
                        continue;
                    }
                    if (!matcher.group(2).matches("\\w+")) {
                        System.out.println("password format is invalid");
                        continue;
                    }
                    RegisteringMenu.remove(matcher.group(1), matcher.group(2));
                    continue;
                }
                matcher = getMatcher(command, "list_users");
                if (matcher.matches()) {
                    RegisteringMenu.listUsers();
                    continue;
                }
                matcher = getMatcher(command, "help");
                if (matcher.matches()) {
                    RegisteringMenu.help();
                    continue;
                }
                matcher = getMatcher(command, "exit");
                if (matcher.matches()) {
                    System.out.println("program ended");
                    break;
                }
            }
            if (positionOfUserInMenus == 2) {
                matcher = getMatcher(command, "new_game (\\S+) -{0,1}(\\d+)");
                if (matcher.matches()) {
                    if (!matcher.group(1).matches("\\w+")) {
                        System.out.println("username format is invalid");
                        continue;
                    }
                    if (command.charAt(command.indexOf(matcher.group(2)) - 1) == '-') {
                        System.out.println("number should be positive to have a limit or 0 for no limit");
                        continue;
                    }
                    if (matcher.group(1).equals(participator.getUsername())) {
                        System.out.println("you must choose another player to start a game");
                        continue;
                    }
                    int limit = Integer.parseInt(matcher.group(2));
                    Participator participator2 = Participator.getParticipatorByUsername(matcher.group(1));
                    if (participator2 == null) {
                        System.out.println("no user exists with this username");
                        continue;
                    }
                    GameMenu gameMenu = new GameMenu(participator, participator2, limit);
                    System.out.println("new game started successfully between " + participator.getUsername() + " and " + participator2.getUsername() + " with limit " + limit);
                    isInThisLoopAnyValidCommand = true;
                    positionOfUserInMenus = 3;
                    continue;
                }
                matcher = getMatcher(command, "scoreboard");
                if (matcher.matches()) {
                    Participator.arrangeByScore(Participator.getParticipators());
                    isInThisLoopAnyValidCommand = true;
                }
                matcher = getMatcher(command, "help");
                if (matcher.matches()) {
                    OriginalMenu.help();
                    continue;
                }
                matcher = getMatcher(command, "list_users");
                if (matcher.matches()) {
                    RegisteringMenu.listUsers();
                    continue;
                }
                matcher = getMatcher(command, "logout");
                if (matcher.matches()) {
                    OriginalMenu.logout(participator);
                    isInThisLoopAnyValidCommand = true;
                    positionOfUserInMenus = 1;
                }
            }
            if (positionOfUserInMenus == 3) {
                matcher = getMatcher(command, "select -{0,1}(\\d+),-{0,1}(\\d+)");
                if (matcher.matches()) {
                    if (matcher.group(1).contains("0") || matcher.group(2).contains("0") || command.charAt(7) == '-' || command.charAt(command.indexOf(",") + 1) == '-') {
                        System.out.println("wrong coordination");
                        continue;
                    }
                    if (matcher.group(1).length() > 1 || matcher.group(2).length() > 1) {
                        System.out.println("wrong coordination");
                        continue;
                    }
                    if (GameMenu.select(Integer.parseInt(matcher.group(1)), Integer.parseInt(matcher.group(2)))) {
                        isInThisLoopAnyValidCommand = true;
                        continue;
                    }
                    continue;
                }
                matcher = getMatcher(command, "deselect");
                if (matcher.matches()) {
                    GameMenu.deselect();
                    isInThisLoopAnyValidCommand = true;
                }
                matcher = getMatcher(command, "move -{0,1}(\\d+),-{0,1}(\\d+)");
                if (matcher.matches()) {

                    GameMenu.move((matcher.group(1)), (matcher.group(2)));
                    continue;
                }
                matcher = getMatcher(command, "next_turn");
                if (matcher.matches()) {
                    if (GameMenu.nextTurn()) {
                        positionOfUserInMenus = 2;
                        continue;
                    }
                    isInThisLoopAnyValidCommand = true;

                }
                matcher = getMatcher(command, "show_turn");
                if (matcher.matches()) {
                    GameMenu.showTurn();
                    isInThisLoopAnyValidCommand = true;
                }
                matcher = getMatcher(command, "undo");
                if (matcher.matches()) {
                    GameMenu.undo();
                    isInThisLoopAnyValidCommand = true;
                }
                matcher = getMatcher(command, "undo_number");
                if (matcher.matches()) {
                    GameMenu.showUndoNumber();
                    isInThisLoopAnyValidCommand = true;
                }
                matcher = getMatcher(command, "show_moves -all");
                if (matcher.matches()) {
                    GameMenu.showMoveAll();
                    isInThisLoopAnyValidCommand = true;
                }
                matcher = getMatcher(command, "show_moves");
                if (matcher.matches()) {
                    GameMenu.showMovesOfThisPlayer();
                    isInThisLoopAnyValidCommand = true;
                }
                matcher = getMatcher(command, "show_killed -all");
                if (matcher.matches()) {
                    GameMenu.showKilledAll();
                    continue;
                }
                matcher = getMatcher(command, "show_killed");
                if (matcher.matches()) {
                    GameMenu.showKilledPieceOfThisPlayer();
                    continue;
                }
                matcher = getMatcher(command, "show_board");
                if (matcher.matches()) {
                    Table.PrintTable();
                    continue;
                }
                matcher = getMatcher(command, "help");
                if (matcher.matches()) {
                    GameMenu.help();
                    continue;
                }
                matcher = getMatcher(command, "forfeit");
                if (matcher.matches()) {
                    GameMenu.forfeit();
                    positionOfUserInMenus = 2;
                    continue;
                }
            }

            if (!isInThisLoopAnyValidCommand) {
                System.out.println("invalid command");
            }
        }
    }

}
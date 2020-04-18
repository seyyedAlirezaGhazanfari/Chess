
import java.util.ArrayList;

class PlayerInfo {
    private int winNumber;
    private int loseNumber;
    private int equalNumber;
    private int score;

    public PlayerInfo(int winNumber, int loseNumber, int equalNumber, int score) {
        this.winNumber = winNumber;
        this.loseNumber = loseNumber;
        this.equalNumber = equalNumber;
        this.score = score;
    }

    public int getWinNumber() {
        return winNumber;
    }

    public void setWinNumber(int winNumber) {
        this.winNumber = winNumber;
    }

    public int getLoseNumber() {
        return loseNumber;
    }

    public void setLoseNumber(int loseNumber) {
        this.loseNumber = loseNumber;
    }

    public int getEqualNumber() {
        return equalNumber;
    }

    public void setEqualNumber(int equalNumber) {
        this.equalNumber = equalNumber;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}

public class Participator {
    private PlayerInfo playerInfo;
    public boolean attention;

    private static ArrayList<Participator> participators = new ArrayList<>();
    private String username;
    private String password;

    public Participator(String username, String password) {
        this.username = username;
        this.password = password;
        participators.add(this);
        attention = false;
        playerInfo = new PlayerInfo(0, 0, 0, 0);
    }

    public PlayerInfo getPlayerInfo() {
        return playerInfo;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public static String[] extractUsernamesList(ArrayList<Participator> participators) {
        String[] usernames = new String[participators.size()];
        int iterator = 0;
        for (Participator participator : participators) {
            usernames[iterator] = participator.getUsername();
            iterator += 1;
        }
        return usernames;
    }

    public static int[] extractWin(ArrayList<Participator> participators, int id) {
        int[] wins = new int[participators.size()];
        int i = 0;
        for (Participator participator : participators) {
            if (id == 1)
                wins[i] = participator.playerInfo.getWinNumber();
            if (id == 2)
                wins[i] = participator.playerInfo.getEqualNumber();
            if (id == 3)
                wins[i] = participator.playerInfo.getLoseNumber();
            if (id == 4)
                wins[i] = participator.playerInfo.getScore();
            i++;
        }
        return wins;
    }

    public static ArrayList<Participator> getParticipators() {
        return participators;
    }

    public static boolean removePlayer(String username, String password) {
        for (Participator participator : participators) {
            if (participator.getPassword().equals(password) && participator.getUsername().equals(username)) {
                participators.remove(participator);
                return true;
            }
        }
        return false;
    }

    public static String weHaveThisPlayer(String username) {
        for (Participator participator : participators) {
            if (participator.getUsername().equals(username))
                return participator.getPassword();
        }
        return null;
    }

    public static String[] arrangeUserList(String[] usernames) {
        for (int i = 0; i < participators.size(); i++) {
            for (int k = 0; k < participators.size() - 1; k++) {
                if (usernames[k].compareTo(usernames[k + 1]) > 0) {
                    transferUserNames(usernames, k);
                }
            }
        }
        return usernames;
    }

    public static Participator getParticipatorByUsername(String username) {
        for (Participator participator : participators) {
            if (participator.getUsername().equals(username))
                return participator;
        }
        return null;
    }

    public static void arrangement(String[] usernames, int[] win, int[] lose, int[] equal, int[] score) {
        for (int i = 0; i < participators.size(); i++) {
            for (int k = 0; k < participators.size() - 1; k++) {
                if (score[k] < score[k + 1]) {
                    transfering(usernames, win, lose, equal, score, k);
                }
                if (score[k] == score[k + 1]) {
                    if (win[k] < win[k + 1]) {
                        transfering(usernames, win, lose, equal, score, k);
                    }
                    if (win[k] == win[k + 1]) {
                        if (equal[k] < equal[k + 1])
                            transfering(usernames, win, lose, equal, score, k);
                        if (equal[k] == equal[k + 1]) {
                            if (lose[k] > lose[k + 1]) {
                                transfering(usernames, win, lose, equal, score, k);
                            }
                            if (lose[k] == lose[k + 1]) {
                                if (usernames[k].compareTo(usernames[k + 1]) > 0) {
                                    String help = usernames[k];
                                    usernames[k] = usernames[k + 1];
                                    usernames[k + 1] = help;
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    public static void transfering(String[] usernames, int[] win, int[] lose, int[] equal, int[] score, int k) {
        transfer(score, k);
        transferUserNames(usernames, k);
        transfer(win, k);
        transfer(lose, k);
        transfer(equal, k);
    }

    public static void transferUserNames(String[] usernames, int k) {
        String nameSub = usernames[k];
        usernames[k] = usernames[k + 1];
        usernames[k + 1] = nameSub;
    }

    public static void transfer(int[] score, int k) {
        int submission = score[k];
        score[k] = score[k + 1];
        score[k + 1] = submission;
    }

    public static void arrangeByScore(ArrayList<Participator> participators) {
        int[] wins = extractWin(participators, 1);
        int[] loses = extractWin(participators, 3);
        int[] equals = extractWin(participators, 2);
        int[] scores = extractWin(participators, 4);
        String[] usernames = extractUsernamesList(participators);
        arrangement(usernames, wins, loses, equals, scores);
        for (int i = 0; i < participators.size(); i++) {
            System.out.println(usernames[i] + " " + scores[i] + " " + wins[i] + " " + equals[i] + " " + loses[i]);
        }
    }
}

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class Notifications {
    int counter = 1,sounter=1;
    int[] mapper = new int[10000];
    private final String username;
    Text text = new Text();
    jsonNotifs Not = new jsonNotifs();
    List<objNotifs> notifs = Not.get();
    jsonUsers Getu = new jsonUsers();
    List<objUsers> users = Getu.get();
    submitRequest sr = new submitRequest();
    int[] tenet = new int[10000];
    File f = new File("Notifs.json");
    private static final Logger logger = LogManager.getLogger(Notifications.class);
    public Notifications(String username) throws IOException {
        logger.info("System: user went to Notifications");
        notifs = Not.get();
        this.username = username;
        text.printer("\na-Requests", ConsoleColors.BLUE_BOLD);
        text.printer("b-Notifications", ConsoleColors.BLUE_BOLD);
        text.printer("c-Home\n", ConsoleColors.BLUE_BOLD);
        String response = text.getText();
        if (response.equals("a")) {
            Requests(username);
        } else if (response.equals("b")) {
            Notifs(username);
        } else if (response.equals("c")) {
            new Home(username);
        } else {
            text.printer("\nInsert a valid command!", ConsoleColors.RED_BOLD_BRIGHT);
            new Notifications(username);
        }
    }

    public void Requests(String username) throws IOException {
        new nextPage(1);
        text.printer("your Requests:",ConsoleColors.CYAN_BOLD);
        for (int i = notifs.size() - 1; i >= 0; i--)
            if (notifs.get(i).getType() == 9
                    && notifs.get(i).getUser1().equals(username) && isEnable(notifs.get(i).getUser2())) {
                text.printer(sounter + "- " + notifs.get(i).getUser2() + " Accepted your request!",
                        ConsoleColors.BLACK_BOLD_BRIGHT);
                sounter++;
            }
        if(sounter==1) text.printer("Nobody Accepted your request!",ConsoleColors.RED_BOLD_BRIGHT);
        else {
            text.printer("\nClear list? (y for yes)\n",ConsoleColors.RED_BOLD_BRIGHT);
        if (text.getText().equals("y"))
            for (int i = notifs.size() - 1; i >= 0; i--)
                if (notifs.get(i).getType() == 9
                        && notifs.get(i).getUser1().equals(username)
                        && isEnable(notifs.get(i).getUser2())) {
                    notifs.remove(i);
                    new jsonNotifs(notifs);
                    text.printer("\nList cleared!\n", ConsoleColors.RED_BOLD_BRIGHT);
                }
        }


        text.printer("\nRequests to you:",ConsoleColors.CYAN_BOLD);
        for (int i = notifs.size() - 1; i >= 0; i--)
            if (notifs.get(i).getType() == 8
                    && notifs.get(i).getUser2().equals(username)&& isEnable(notifs.get(i).getUser1())) {
                text.printer(counter + "- " + notifs.get(i).getUser1() + " Requested to follow you!",
                        ConsoleColors.BLACK_BOLD_BRIGHT);
                mapper[counter - 1] = i;
                counter++;
            }
        if(counter==1) text.printer("Nobody Requested to follow you!",ConsoleColors.RED_BOLD_BRIGHT);
        else{
            String response = text.getText();
            if(response.length()>1 && response.charAt(0)=='*'){
                sr.submitReject(response, mapper,counter);
                text.getText();
                new Notifications(username);
                return;
            }else if(response.length()>1 && response.charAt(0)=='#'){
                sr.submitMuteAccept(response, mapper,counter);
                text.getText();
                new Notifications(username);
                return;
            }else if(response.length()>1 && response.charAt(0)=='@'){
                sr.submitAccept(response, mapper,counter);
                text.getText();
                new Notifications(username);
                return;
            }else if(response.equals("c")){

            }
        }
        new Notifications(username);
        return;
    }

    public void Notifs(String username) throws IOException {
        new nextPage(1);
        notifs = Not.get();
        for (int i = notifs.size() - 1; i >= 0; i--)
            announcer(i);
        if (counter == 1) text.printer(
                " You haven't any notifications!", ConsoleColors.RED_BOLD_BRIGHT);
        text.printer("\na-Back", ConsoleColors.BLUE_BOLD);
        text.printer("b-Home", ConsoleColors.BLUE_BOLD);
        if (counter != 0) text.printer("c-Clear list\n", ConsoleColors.BLUE_BOLD);
        switch (text.getText()) {
            case "c":
                for (int i = notifs.size() - 1; i >= 0; i--) {
                    if (notifs.get(i).getUser2().equals(username) &&
                            notifs.get(i).getType()!=8 && notifs.get(i).getType()!=9) {
                        notifs.remove(i);
                    }
                }
                new jsonNotifs(notifs);
                new Notifications(username);
                return;
            case "a":
                new Notifications(username);
                return;
            case "b":
                new Home(username);
                return;
        }
    }

    private void announcer(int i) {
        if (notifs.get(i).getType() == 1
                && notifs.get(i).getUser2().equals(username)&& isEnable(notifs.get(i).getUser1())) {
            text.printer(counter + "- " + notifs.get(i).getUser1() + " started Following you!",
                    ConsoleColors.GREEN_BOLD);
            counter++;
        } else if (notifs.get(i).getType() == 2
                && notifs.get(i).getUser2().equals(username)&& isEnable(notifs.get(i).getUser1())) {
            text.printer(counter + "- " + notifs.get(i).getUser1() + " Unfollowed you!",
                    ConsoleColors.RED_BOLD_BRIGHT);
            counter++;
        } else if (notifs.get(i).getType() == 3
                && notifs.get(i).getUser2().equals(username)&& isEnable(notifs.get(i).getUser1())) {
            text.printer(counter + "- " + notifs.get(i).getUser1() + " Blocked you!",
                    ConsoleColors.RED_BOLD_BRIGHT);
            counter++;
        } else if (notifs.get(i).getType() == 4
                && notifs.get(i).getUser2().equals(username)&& isEnable(notifs.get(i).getUser1())) {
            text.printer(counter + "- " + notifs.get(i).getUser1() + " UnBlocked you!",
                    ConsoleColors.GREEN_BOLD);
            counter++;
        } else if (notifs.get(i).getType() == 5
                && notifs.get(i).getUser2().equals(username)&& isEnable(notifs.get(i).getUser1())) {
            text.printer(counter + "- " + notifs.get(i).getUser1() + " Muted you!",
                    ConsoleColors.RED_BOLD_BRIGHT);
            counter++;
        } else if (notifs.get(i).getType() == 6
                && notifs.get(i).getUser2().equals(username)&& isEnable(notifs.get(i).getUser1())) {
            text.printer(counter + "- " + notifs.get(i).getUser1() + " UnMuted you!",
                    ConsoleColors.GREEN_BOLD);
            counter++;
        } else if (notifs.get(i).getType() == 7 &&
                notifs.get(i).getUser2().equals(username) && isEnable(notifs.get(i).getUser1())) {
                    text.printer(counter + "- " + notifs.get(i).getUser1() + " Deleted you!",
                            ConsoleColors.RED_BOLD_BRIGHT);
                    counter++;
        }
    }
    public boolean isEnable(String username){
        for (int j = 0; j < users.size(); j++)
            if (username.equals(users.get(j).getUsername()) && users.get(j).getIsEnable())
                return true;
            return false;
    }
}

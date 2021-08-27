import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

public class massMessenger {


    jsonUsers get_users = new jsonUsers();
    List<objUsers> users = get_users.get();
    jsonMessage get_mess = new jsonMessage();
    List<objMessage> messages = get_mess.get();
    submitMessage sb = new submitMessage();
    Text text = new Text();
    int target,sounter=1,cat,use;
    int counter=1;
    int[] mapper = new int [10000];
    int[] chater = new int [10000];
    String respones;
    private static final Logger logger = LogManager.getLogger(massMessenger.class);
    public massMessenger(String username) throws IOException {
        logger.info("System: user went to massMessenger");

        for (int i = 0; i < users.size(); i++)
            if (users.get(i).getUsername().equals(username)) {
                target = i;
                break;
            }

        text.printer("\nCategory:", ConsoleColors.CYAN_BOLD);
        text.printer("a-Message to a category", ConsoleColors.BLACK_BOLD_BRIGHT);
        text.printer("b-Message to select message", ConsoleColors.BLACK_BOLD_BRIGHT);
        text.printer("c-Message to whole followings", ConsoleColors.BLACK_BOLD_BRIGHT);
        text.printer("d-go to feed", ConsoleColors.BLACK_BOLD_BRIGHT);
        text.printer("e-go to expelorer\n", ConsoleColors.BLACK_BOLD_BRIGHT);


        switch (text.getText()) {
            case "a":
                cat = getCategories(username);
                if (cat == -1) return;
                text.printer("\nImport message:", ConsoleColors.BLACK_BOLD_BRIGHT);
                respones = text.getText();
                for (int j = 1; j < users.get(target).getCategoiries().get(chater[cat - 1]).size(); j++) {
                    sb.SubMess(respones, username, users.get(target).getCategoiries().get(chater[cat - 1]).get(j));
                }
                logger.info("System: sent to "+username,
                        users.get(target).getCategoiries().get(chater[cat - 1]).get(0));
                text.printer("\nMessages sent to selected categories!", ConsoleColors.GREEN_BOLD);
                new massMessenger(username);
                return;

            case "b":

                text.printer("\nImport message: (0- to canlce)", ConsoleColors.RED_BOLD_BRIGHT);
                respones = text.getText();
                while (true) {
                    use = getFollowings(username);
                    if (use == 0 || use == -1) break;
                    sb.SubMess(respones, username, users.get(target).getFollowings().get(mapper[use - 1]));

                }
                logger.info("System: Messages sent to selected users");
                text.printer("\nMessages sent to selected users!", ConsoleColors.GREEN_BOLD);
                new massMessenger(username);
                return;

            case "c":
                text.printer("\nImport message:", ConsoleColors.BLACK_BOLD_BRIGHT);
                respones = text.getText();
                for (int j = 1; j < users.get(target).getFollowings().size(); j++) {
                    sb.SubMess(respones, username, users.get(target).getFollowings().get(j));
                }
                logger.info("System: Message sent to all");
                text.printer("\nMessage sent to all of your followings!", ConsoleColors.GREEN_BOLD);
                new massMessenger(username);
                return;
            case "d":
                new Feed(username);
                return;
            case "e":
                new Feed(username);
                return;

            default:
                text.printer("\nInvalid command!", ConsoleColors.RED_BOLD_BRIGHT);
                new massMessenger(username);
                return;
        }

    }
    public int getCategories(String username) throws IOException {
        if (users.get(target).getCategoiries().size() != 1) {
            text.printer("\nYour Categories:", ConsoleColors.CYAN_BOLD);
            for (int j = 1; j < users.get(target).getCategoiries().size(); j++) {
                text.printer(sounter + "- " + users.get(target).getCategoiries().get(j).get(0),
                        ConsoleColors.BLACK_BOLD_BRIGHT);
                chater[sounter - 1] = j;
                sounter++;
            }
        }

        if (sounter == 1) {
            text.printer("You dont have any category!", ConsoleColors.RED_BOLD_BRIGHT);
        } else {
            text.printer("\nInsert category number:", ConsoleColors.PURPLE_BOLD);
            try {
                int resnum = Integer.parseInt(text.getText());
                if (resnum < sounter) {
                    return resnum;
                } else {
                    text.printer("\nPlease insert valid number!", ConsoleColors.RED_BOLD_BRIGHT);
                    text.getText();
                    new massMessenger(username);
                }
            } catch (NumberFormatException | IOException exception) {
                new massMessenger(username);
            }
        }
        return -1;
    }
    public int getFollowings(String username) throws IOException {
        counter=1;
        if (users.get(target).getFollowings().size() != 1){
            text.printer("\nYour Followings:", ConsoleColors.CYAN_BOLD);
            for (int j = 1; j < users.get(target).getFollowings().size(); j++)
                for (int i = 0; i < users.size(); i++)
                    if (users.get(i).getUsername().equals(users.get(target).getFollowings().get(j))
                            && users.get(i).getIsEnable()){
                        text.printer(counter + "- " + users.get(target).getFollowings().get(j),
                                ConsoleColors.BLACK_BOLD_BRIGHT);
                        mapper[counter - 1] = j;
                        counter++;
                    }
        }
        if (counter == 1) {
            text.printer("You dont follow anybody!", ConsoleColors.RED_BOLD_BRIGHT);
        } else {
            text.printer("\nInsert users number:", ConsoleColors.PURPLE_BOLD);
            try {
                int resnum2 = Integer.parseInt(text.getText());
                if (resnum2 < counter) {
                    return resnum2;
                } else {
                    text.printer("\nPlease insert valid number!", ConsoleColors.RED_BOLD_BRIGHT);
                    text.getText();
                    new Category(username);
                    return -1 ;
                }
            } catch (NumberFormatException | IOException exception) {
                new Category(username);
                return -1 ;
            }
        }
        return -1;
    }
}

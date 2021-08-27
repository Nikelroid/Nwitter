import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.List;

public class Info {
    private static final Logger logger = LogManager.getLogger(Info.class);
    public Info(String username) throws IOException {
        logger.info("System: user went to Info");
        var get_j = new jsonUsers();
        var text = new Text();
        List<objUsers> users = get_j.get();
        text.printer(username+"s Info of" , ConsoleColors.CYAN_BOLD);
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getUsername().equals(username)) {
                text.printer("ID: " + users.get(i).getID(), ConsoleColors.BLUE_BOLD_BRIGHT);
                text.printer("Name: " + users.get(i).getName(), ConsoleColors.BLACK_BOLD_BRIGHT);
                text.printer("Username: " + users.get(i).getUsername(), ConsoleColors.BLACK_BOLD_BRIGHT);
                text.printer("Birthday date: " + users.get(i).getBirthday(), ConsoleColors.BLACK_BOLD_BRIGHT);
                text.printer("Email address: " + users.get(i).getEmail(), ConsoleColors.BLACK_BOLD_BRIGHT);
                text.printer("Phone number: " + users.get(i).getPhonenumber(), ConsoleColors.BLACK_BOLD_BRIGHT);
                text.printer("Bio: " + users.get(i).getBio(), ConsoleColors.BLACK_BOLD_BRIGHT);
                System.out.print(ConsoleColors.BLACK_BOLD_BRIGHT+"Lastseen: ");
                text.printer( users.get(i).getLastseen(), ConsoleColors.GREEN_BOLD);
                System.out.print(ConsoleColors.BLACK_BOLD_BRIGHT+"Account Privacy: ");
                if (users.get(i).getAccount()) text.printer("Public", ConsoleColors.GREEN_BOLD);
                else text.printer("Private", ConsoleColors.RED_BOLD_BRIGHT);

                System.out.print(ConsoleColors.BLACK_BOLD_BRIGHT+"Account State: ");
                if(users.get(i).getIsEnable()) {
                    text.printer("Enable", ConsoleColors.GREEN_BOLD);
                }else {
                    text.printer("Disable", ConsoleColors.RED_BOLD_BRIGHT);
                }
                new nextPage(1);
            }
        }
        text.printer("a-Go to Home", ConsoleColors.BLUE_BOLD);
        text.printer("b-Go to Feed", ConsoleColors.BLUE_BOLD);
        new nextPage(1);
        switch (text.getText()){
            case "a" : new Home(username);return;
            case "b" : new Feed(username);return;
            default:
                text.printer("\nPlease insert a valid cammand",ConsoleColors.RED_BOLD_BRIGHT);
                text.getText();
                new Setting(username);
                return;
        }
    }
}

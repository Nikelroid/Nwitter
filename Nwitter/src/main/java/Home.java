import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

public class Home {
    String username;
    private static final Logger logger = LogManager.getLogger(Home.class);
    public Home(String username) throws IOException {
        logger.info("System: user went to Home");
        this.username=username;
        var text = new Text();
        Menu_command();
        String response = text.getText();
        switch (response){
            case "a":
                new Newtwitte(username);break;
            case "b":
                new userTwittes(username,username);break;
            case "c":
                new editProfile(username);break;
            case "i":
                new Category(username);break;
            case "d":
                new Lists(username,username);break;
            case "e":
                new Info(username);break;
            case "f":
                new Notifications(username);break;
            case "g":
                new Feed(username);break;
            default:
                text.printer("Please insert a valid Command!", ConsoleColors.RED_BOLD_BRIGHT);
                new Home(username);
                return;
        }
    }
    public void Menu_command() {
        var text = new Text();
        new nextPage(1);
        text.printer("Home:",ConsoleColors.CYAN_BOLD);
        text.printer("a-New Nwitte",ConsoleColors.BLACK_BOLD_BRIGHT);
        text.printer("b-Your nwittes",ConsoleColors.BLACK_BOLD_BRIGHT);
        text.printer("c-Edit personal information",ConsoleColors.BLACK_BOLD_BRIGHT);
        text.printer("i-Categories",ConsoleColors.BLACK_BOLD_BRIGHT);
        text.printer("d-Lists",ConsoleColors.BLACK_BOLD_BRIGHT);
        text.printer("e-Info",ConsoleColors.BLACK_BOLD_BRIGHT);
        text.printer("f-Notifications",ConsoleColors.BLACK_BOLD_BRIGHT);
        text.printer("g-Go to feed",ConsoleColors.BLACK_BOLD_BRIGHT);
        new nextPage(1);
    }
}

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

public class Newtwitte {
    int serial;

    public Newtwitte() {

    }
    private static final Logger logger = LogManager.getLogger(Newtwitte.class);
    public int newComment(String username) {
        logger.info("System: user went to newComment");
        var text = new Text();
        var submit = new submitTwitte();
        System.out.println();
        text.printer("Insert your Comment:(a-Cancel and go to feed)", ConsoleColors.BLUE_BOLD);
        String response = text.getText();
        if (response.equals("a")) {
            return -1;
        }else{
            logger.info("System: Commented");
            text.printer("\nCommented Successfully!", ConsoleColors.GREEN_BOLD);
            return submit.Sub_twitte(response, username);
        }



    }


    public Newtwitte(String username) throws IOException {
        logger.info("System: user went to Newtwitte");
        System.out.println();
        var text = new Text();
        var submit = new submitTwitte();
        text.printer("Insert your Nwitt:(a-Cancel and go to feed)", ConsoleColors.BLUE_BOLD);
        String response = text.getText();
        if (response.equals("a"))
            new Feed(username);
        else
        if (submit.Sub_twitte(response, username)!=0) {
            logger.info("System: twitted");
            text.printer("\nSuccessfully twitted!", ConsoleColors.GREEN_BOLD);
            System.out.println();
        } else {
            text.printer("\nAn error occurred, try again!", ConsoleColors.RED_BOLD_BRIGHT);
            System.out.println();
        }
        Menu_command();

        switch (text.getText()) {
            case "a":
                new Newtwitte(username);
                break;
            case "b":
                new Feed(username);
                break;
        }
    }
        public void Menu_command(){
        Text text = new Text();
            text.printer("Menu:", ConsoleColors.CYAN_BOLD);
            text.printer("a-Creat another Nwitte", ConsoleColors.BLACK_BOLD_BRIGHT);
            text.printer("b-Go to feed", ConsoleColors.BLACK_BOLD_BRIGHT);
            new nextPage(1);
        }


}

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class savedMessages {
    Text text = new Text();
    jsonUsers get_users = new jsonUsers();
    List<objUsers> users = get_users.get();
    jsonTwittes Get = new jsonTwittes();
    List<objTwitte> twittes = Get.get();
    jsonMessage get_mess = new jsonMessage();
    List<objMessage> messages = get_mess.get();
    submitMessage sb = new submitMessage();
    int counter = 1;
    int target;
    private static final Logger logger = LogManager.getLogger(savedMessages.class);
    public savedMessages(String username) throws IOException {
        logger.info("System: user went to savedMessages");


        for (int k = 0; k < users.size(); k++)
            if (users.get(k).getUsername().equals(username)) {
                target = k;
                break;
            }

            if (users.get(target).getPmsaved().size()<2)
                text.printer("You didn't save any message yet!",ConsoleColors.RED_BOLD_BRIGHT);
            else {
                text.printer("Your saved Messages:", ConsoleColors.CYAN_BOLD);
                for (int i = 0; i < users.get(target).getPmsaved().size(); i++)
                    for (int j = 0; j < messages.size(); j++)
                        if (users.get(target).getPmsaved().get(i).equals(messages.get(j).getSerial()))
                            text.printer(counter + " - " + messages.get(j).getText() + " From " + messages.get(j).getSender(),
                                    ConsoleColors.BLACK_BOLD_BRIGHT);
            }
                new nextPage(1);


        if (users.get(target).getTwittesaved().size()<2)
            text.printer("You didn't save any Twittes yet!",ConsoleColors.RED_BOLD_BRIGHT);
        else {

            text.printer("Your saved Twittes:", ConsoleColors.CYAN_BOLD);
            for (int i = 0; i < users.get(target).getTwittesaved().size(); i++)
                for (int j = 0; j < twittes.size(); j++)
                    if (users.get(target).getTwittesaved().get(i).equals(twittes.get(j).getSerial()))
                        for (int k = 0; k < users.size(); k++)
                            if(users.get(k).getUsername().equals(twittes.get(j).getSender())
                            && users.get(k).getIsEnable() && !users.get(k).getBlocks().contains(username))
                        text.printer(counter + " - " + twittes.get(j).getText() + " From " + twittes.get(j).getSender(),
                                ConsoleColors.BLACK_BOLD_BRIGHT);
        }
        new nextPage(1);

        text.printer("For save a message, insert it:(Insert m for back to Messenger)",ConsoleColors.BLUE_BOLD);
        String res = text.getText();
        if (res.equals("m")) {
            new Messenger(username);
            return;
        }else if (res.isEmpty()) {
            text.printer("Input a valid command or text :)",ConsoleColors.RED_BOLD_BRIGHT);
            new savedMessages(username);
            return;
        }else{
            int serial = sb.SubMess(res, username, username);
            for (int i = 0; i < users.size(); i++)
                if(users.get(i).getUsername().equals(username))
                    users.get(i).getPmsaved().add(serial);
            new jsonUsers(users);
            logger.info("System: New message saved");
            text.printer("message saved!", ConsoleColors.GREEN_BOLD);

        }
    }
}

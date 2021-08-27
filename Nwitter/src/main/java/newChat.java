import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.List;

public class newChat {
    jsonUsers get_users = new jsonUsers();
    List<objUsers> users = get_users.get();
    jsonMessage get_mess = new jsonMessage();
    List<objMessage> messages = get_mess.get();
    submitMessage sb = new submitMessage();

    Text text = new Text();
    int target=0,counter = 1;
    int[] mapper = new int[10000];
    private static final Logger logger = LogManager.getLogger(newChat.class);
    public newChat(String username) throws IOException {
        logger.info("System: user went to newChat");
        for (int i = 0; i < users.size(); i++)
            if (users.get(i).getUsername().equals(username))target=i;
        text.printer("\nCreat new chat",ConsoleColors.CYAN_BOLD);
        for (int i = 0; i < users.size(); i++)
            if(users.get(target).getFollowings().contains(users.get(i).getUsername())){
                text.printer(counter+"-"+users.get(i).getUsername(),ConsoleColors.BLACK_BOLD_BRIGHT);
                mapper[counter - 1] = i;
                counter++;
            }
        text.printer("\na-Messenege",ConsoleColors.BLUE_BOLD);
        text.printer("\nb-Feed",ConsoleColors.BLUE_BOLD);
            String response = text.getText();

        try {
            int resnum = Integer.parseInt(response);
            if (resnum < counter) {
                text.printer("\nType first message:",ConsoleColors.YELLOW_BOLD);
                sb.SubMess(text.getText(),users.get(target).getUsername(),
                        users.get(mapper[resnum-1]).getUsername());
                new chatPage(users.get(target).getUsername(),
                        users.get(mapper[resnum-1]).getUsername());
                logger.info("System: user sent a message");
                new nextPage(1);
            }else {
                text.printer("Please insert valid number!\n", ConsoleColors.RED_BOLD_BRIGHT);
                text.getText();
                new newChat(username);
                return;
            }
        }catch (NumberFormatException | IOException exception) {
            if (response.equals("a")) {
                new Messenger(username);
                return;
        }else if(response.equals("b")) {
                new Feed(username);
                return;
            }else {
                text.printer("Please insert valid command!", ConsoleColors.RED_BOLD_BRIGHT);
                text.getText();
                new newChat(username);
                return;
            }

        }


    }
}

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Messenger {
    Text text = new Text();
    jsonMessage get_mess = new jsonMessage();
    List<objMessage> messages = get_mess.get();
    ArrayList<String> retorder = new ArrayList<String>();
    jsonUsers get_users = new jsonUsers();
    List<objUsers> users = get_users.get();
    int target;
    int counter=1;
    int nonseen;
    int [] mapper = new int[10000];
    boolean [] marker = new boolean[10000];
    private static final Logger logger = LogManager.getLogger(Messenger.class);
    public Messenger(String username) throws IOException {
        logger.info("System: user went to Messenger");
        for (int j = 0; j < users.size(); j++)
            if (users.get(j).getUsername().equals(username))
                target=j;

            text.printer("\nMessenger:",ConsoleColors.CYAN_BOLD);
        text.printer("a-Saved message",ConsoleColors.BLACK_BOLD_BRIGHT);
        text.printer("b-New chat",ConsoleColors.BLACK_BOLD_BRIGHT);
        text.printer("c-Go to feed\n",ConsoleColors.BLACK_BOLD_BRIGHT);
        text.printer("m-Go to Mass messenger\n",ConsoleColors.BLACK_BOLD_BRIGHT);
        text.printer("Your private chats:",ConsoleColors.CYAN_BOLD);
        for (int i = messages.size()-1; i>=0 ; i--) {
            if (messages.get(i).getSender().equals(username)
            && !retorder.contains(messages.get(i).getReceiver()))
                for (int j = 0; j < users.size(); j++)
                    if (users.get(j).getUsername().equals(messages.get(i).getReceiver()))
                        if(!users.get(j).getBlocks().contains(username) &&
                                !users.get(target).getBlocks().contains(users.get(j).getUsername())
                        && !(users.get(j).getUsername().equals(username))){

                retorder.add(messages.get(i).getReceiver());
                text.printer( counter + "-" + messages.get(i).getReceiver()+ " | Nonseen messages: 0", ConsoleColors.BLUE_BOLD);
                    retorder.add(messages.get(i).getReceiver());
                    mapper[counter - 1] = i;
                    marker[counter-1]=true;
                    counter++;
                }
            if (messages.get(i).getReceiver().equals(username)
                    && !retorder.contains(messages.get(i).getSender()))
                for (int j = 0; j < users.size(); j++)
                    if (users.get(j).getUsername().equals(messages.get(i).getSender()))
                        if(!users.get(j).getBlocks().contains(username) &&
                                !users.get(target).getBlocks().contains(users.get(j).getUsername())
                                && !(users.get(j).getUsername().equals(username))){
                nonseen=0;
                for (int k = messages.size()-1; k>=0 ; k--) {
                    if (messages.get(k).getReceiver().equals(username)&&
                            messages.get(k).getSender().equals(messages.get(i).getSender())&&
                            !messages.get(k).isSeen())nonseen++;
                }

                text.printer( counter + "-" + messages.get(i).getSender()+ " | Nonseen messages: "+nonseen, ConsoleColors.BLUE_BOLD);
                retorder.add(messages.get(i).getSender());
                mapper[counter - 1] = i;
                marker[counter-1]=false;
                counter++;



            }
        }
        new nextPage(1);
        String response = text.getText();
        new nextPage(1);
        switch (response){
            case "a":
                new savedMessages(username);
                break;
            case "b":
                new newChat(username);
                return;
            case "c":
                new Feed(username);
                return;
            case "m":
                new massMessenger(username);
                return;
            default:
                try {
                    int resnum = Integer.parseInt(response);
                    if (resnum < counter) {
                        if (marker[resnum - 1])
                          new chatPage(username, messages.get(mapper[resnum - 1]).getReceiver());
                        else
                            new chatPage(username, messages.get(mapper[resnum - 1]).getSender());
                        return;
                    }else {
                        text.printer("Please insert valid number!", ConsoleColors.RED_BOLD_BRIGHT);
                        text.getText();
                        new Messenger(username);
                        return;
                    }
                }catch (NumberFormatException exception){
                    text.printer("Please insert valid Command!", ConsoleColors.RED_BOLD_BRIGHT);
                    text.getText();
                    new Messenger(username);
                }
        }
    }
}

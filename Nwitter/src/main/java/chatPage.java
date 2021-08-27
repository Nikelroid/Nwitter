import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.List;
public class chatPage {
    Text text = new Text();
    jsonUsers get_users = new jsonUsers();
    List<objUsers> users = get_users.get();
    jsonMessage get_mess = new jsonMessage();
    List<objMessage> messages = get_mess.get();
    submitMessage sb = new submitMessage();
    private static final Logger logger = LogManager.getLogger(chatPage.class);

    public chatPage(String me,String he) throws IOException {
        logger.info("System: user went to ChatPage");
        new nextPage(100);
        for (int i = messages.size()-1; i>=0 ; i--)
            if (messages.get(i).getSender().equals(he) &&
                    messages.get(i).getReceiver().equals(me))
                messages.get(i).setSeen(true);
        new jsonMessage(messages);
        messages = get_mess.get();

        for (int i = messages.size()-1; i>=0 ; i--) {
            if (messages.get(i).getSender().equals(me)&&messages.get(i).getReceiver().equals(he)) {
                text.printer("You: " + messages.get(i).getText(), ConsoleColors.BLUE_BOLD_BRIGHT);
                text.printer("Has Seen: " + messages.get(i).isSeen()
                        +" | Time: "+messages.get(i).getTime()+ " | Serial="+messages.get(i).getSerial(), ConsoleColors.WHITE_BOLD);

                new nextPage(1);
            }
            if (messages.get(i).getSender().equals(he)&&messages.get(i).getReceiver().equals(me)) {
                text.printer( messages.get(i).getSender()+": " + messages.get(i).getText(), ConsoleColors.RED_BOLD);
                text.printer("Time: "+messages.get(i).getTime() + " | Serial="+messages.get(i).getSerial() , ConsoleColors.WHITE_BOLD);
                new nextPage(1);
            }
        }
        text.printer("Type your message: (Insert c to go messenger | " +
                "for save message type (%Serial) of message)", ConsoleColors.WHITE_BOLD);
        String pm = text.getText();
        if (pm.equals("c")){
            new Messenger(me);
        } else if (pm.length()>1 && pm.charAt(0)=='%') {
            try {
                int resnum = Integer.parseInt(pm.substring(1));
                for (int i = 0; i < users.size(); i++)
                    if(users.get(i).getUsername().equals(me))
                        users.get(i).getPmsaved().add(resnum);
                    new jsonUsers(users);
                logger.info("System: message saved!");
                text.printer("message saved!", ConsoleColors.GREEN_BOLD);
                new chatPage(me,he);
                return;
            }catch (NumberFormatException exception){
                text.printer("\nInvalid command!\n", ConsoleColors.RED_BOLD_BRIGHT);
                new chatPage(me,he);
                return;
            }
        }else{
            sb.SubMess(pm, me, he);
            new chatPage(me, he);
        }
        return;
    }
}

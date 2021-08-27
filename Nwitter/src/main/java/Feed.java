import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Feed {

    public Feed() {

    }
    int serial;
    Text text = new Text();
    int counter=1;
    public String username;
    int [] mapper = new int[10000];
    ArrayList<Integer> retorder = new ArrayList<Integer>();
    jsonTwittes Get = new jsonTwittes();
    jsonUsers get_users = new jsonUsers();
    List<objUsers> users = get_users.get();
    List<objTwitte> twittes = Get.get();
    submitMessage sb = new submitMessage();
    int[] cheat = new int [10000];
    List<String> newcat = new ArrayList<String>();
    int sn=1;
    int target=0;
    private static final Logger logger = LogManager.getLogger(Feed.class);
    public Feed(String username) throws IOException {
        logger.info("System: user went to Feed");
        this.username=username;
        int search = 0;
            File f = new File("Twittes.json");
            if (f.exists()) {
                int target = 0;
                System.out.println();
                text.printer("Feed:", ConsoleColors.YELLOW_BOLD);

                for (int i = 0; i < users.get(target).getFollowings().size() ; i++)
                    for (int j = twittes.size()-1; j >= 0 ; j--)
                        if (twittes.get(j).getRetwittes().contains(username)&&
                                !(retorder.contains(twittes.get(j).getSerial()))
                        && users.get(target).getIsEnable()){
                            System.out.print(ConsoleColors.GREEN_BOLD +
                                    "You Retwitted: ");
                            text.printer(counter + "- "+twittes.get(j).getText() +
                                            " | Likes:" + (twittes.get(j).getLikes().size()-1) +
                                            " | Comment:" + (twittes.get(j).getComments().size()-1) +
                                            " | Retwittes:" + (twittes.get(j).getRetwittes().size()-1) +
                                            " | Data:" + twittes.get(j).getTime(),
                                    ConsoleColors.BLUE_BOLD_BRIGHT);


                            for (int l = 0; l < twittes.size(); l++)
                                if(twittes.get(l).getComments().contains(twittes.get(j).getSerial()))
                                    text.printer("( It's a comment of: "+twittes.get(l).getText()+
                                            " from "+twittes.get(l).getSender()+" )",ConsoleColors.PURPLE_BOLD);
                            retorder.add(twittes.get(j).getSerial());
                            mapper[counter - 1] = j;
                            counter++;
                        }else
                       if (twittes.get(j).getRetwittes().contains(users.get(target).getFollowings().get(i)))
                           for (int k = 0; k < users.size(); k++)
                               if (users.get(k).getUsername().equals(twittes.get(j).getSender()) &&
                                       (users.get(k).getIsEnable()))
                                   if (!(users.get(k).getBlocks().contains(username)) &&
                                           !(retorder.contains(twittes.get(j).getSerial())) &&
                !(users.get(target).getMutes().contains(users.get(target).getFollowings().get(i)))){
                                       System.out.print(ConsoleColors.GREEN_BOLD +
                                               users.get(target).getFollowings().get(i) +" Retwitted: ");
                                     new twittePrinter(j,counter);
                                       for (int l = 0; l < twittes.size(); l++)
                                           if(twittes.get(l).getComments().contains(twittes.get(j).getSerial()))
                                      text.printer("( It's a comment of: "+twittes.get(l).getText()+
                                              " from "+twittes.get(l).getSender()+" )",ConsoleColors.PURPLE_BOLD);
                                       retorder.add(twittes.get(j).getSerial());
                                       mapper[counter - 1] = j;
                                       counter++;
                                   }
                new nextPage(1);
                for (int i = 0; i < users.size() ; i++)
                    if (users.get(i).getUsername().equals(username)){
                        target = i;
                        break;
                    }

                for (int i = twittes.size() - 1; i >= 0; i--)
                    for (int m = 0; m < users.size(); m++)
                        if (users.get(m).getUsername().equals(username) &&
                                (users.get(m).getFollowings().contains(twittes.get(i).getSender())
                                        || (twittes.get(i).getSender().equals(username))))
                            for (int j = 0; j < users.size(); j++)
                                if (users.get(j).getUsername().equals(username))
                                    for (int k = 0; k < users.size(); k++)
                                        if (users.get(k).getUsername().equals(twittes.get(i).getSender()) &&
                                                (users.get(k).getIsEnable()))
                                            if (!(users.get(k).getBlocks().contains(username)) &&
                                                    !(users.get(j).getMutes().contains(twittes.get(i).getSender()))
                                                    && !(users.get(j).getBlocks().contains(twittes.get(i).getSender()))
                                                    && !(retorder.contains(twittes.get(i).getSerial()))){
                                                new twittePrinter(i,counter);
                                                for (int l = 0; l < twittes.size(); l++)
                                                    if(twittes.get(l).getComments().contains(twittes.get(i).getSerial()))
                                                        text.printer("( It's a comment of: "+twittes.get(l).getText()+
                                                                " from "+twittes.get(l).getSender()+" )",ConsoleColors.PURPLE_BOLD);
                                                mapper[counter - 1] = i;
                                                counter++;
                                            }
            }
                if (counter==1)text.printer("No Nwittes exist to show!",ConsoleColors.RED_BOLD_BRIGHT);
                Menu_command();
                Menu(username);



    }

    public void getter(String response) throws IOException {
        for (int i = 0; i < users.size() ; i++) {
            if (users.get(i).getUsername().equals(username)){
                target = i;
                break;
            }
        }
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        var text = new Text();
        switch (response){
            case "a" : new Home(username);return;
            case "b" : new Feed(username);return;
            case "c" : new Expelorer(username);return;
            case "d" : new Messenger(username);return;
            case "e" : new Setting(username);return;
            case "m" : new massMessenger(username);return;
            case "f" :
                users.get(target).setLastseen(dtf.format(now));
                new jsonUsers(users);
                new Login(true);return;
            case "g" :
                users.get(target).setLastseen(dtf.format(now));
                new jsonUsers(users);
                logger.info("User Exited the app");
               return;
            default: text.printer("Invalid command!", ConsoleColors.RED_BOLD_BRIGHT);
                new Feed(username);return;
        }
    }
    public void Menu_command() {

        new nextPage(1);
        text.printer("Feed:",ConsoleColors.CYAN_BOLD);
        text.printer("a-Home page",ConsoleColors.BLACK_BOLD_BRIGHT);
        text.printer("b-Refresh Feed",ConsoleColors.BLACK_BOLD_BRIGHT);
        text.printer("c-Explorer",ConsoleColors.BLACK_BOLD_BRIGHT);
        text.printer("d-Messenger",ConsoleColors.BLACK_BOLD_BRIGHT);
        text.printer("m-Mass Messenger",ConsoleColors.BLACK_BOLD_BRIGHT);
        text.printer("e-Setting",ConsoleColors.BLACK_BOLD_BRIGHT);
        text.printer("f-Logout",ConsoleColors.BLACK_BOLD_BRIGHT);
        text.printer("g-Exit",ConsoleColors.BLACK_BOLD_BRIGHT);
        new nextPage(1);
    }
    public void Menu(String username) throws IOException {
        String response = text.getText();
        if(response.length()>1 && response.charAt(0)=='*' && response.charAt(1)!='*'){
            new submitLike(username, mapper,response,counter);
            text.getText();
            new Feed(username);
            return;
        }else if(response.length()>1 && response.charAt(0)=='*' && response.charAt(1)=='*'){
            submitLike Like = new submitLike();
            Like.list(mapper,response,counter,username);
            text.getText();
            new Feed(username);
            return;
        }else if(response.length()>1 && response.charAt(0)=='#' && response.charAt(1)!='#'){
            new submitRetwitte(username, mapper,response,counter);
            text.getText();
            new Feed(username);
            return;
        }else if(response.length()>1 && response.charAt(0)=='#' && response.charAt(1)=='#'){
            submitRetwitte Ret = new submitRetwitte();
            Ret.list(mapper,response,counter,username);
            text.getText();
            new Feed(username);
            return;
        }else if(response.length()>1 && response.charAt(0)=='$' ) {
            text.printer("\nSay your philosophy of report:", ConsoleColors.BLUE_BOLD);
            String reptext = text.getText();
            new nextPage(1);
            new Reports(username, reptext, mapper, response, counter);
            text.getText();
            new Feed(username);
            return;
        }else if(response.length()>1 && response.charAt(0)=='@' && response.charAt(1)!='@'){
            new submitComment(username, mapper,response,counter);
            text.getText();
            new Feed(username);
            return;
        }else if(response.length()>1 && response.charAt(0)=='@' && response.charAt(1)=='@'){
            submitComment Com = new submitComment();
            Com.list(mapper,response,counter,username);
            new Feed(username);
            return;
        }else if(response.length()>1 && response.charAt(0)=='%'){
            try {

                int resnum = Integer.parseInt(response.substring(1));
                if (resnum < counter) {
                    for (int i = 0; i < users.size(); i++)
                        if (users.get(i).getUsername().equals(username))
            users.get(i).getTwittesaved().add(twittes.get(mapper[resnum - 1]).getSerial());
        new jsonUsers(users);
                    logger.info("System: Twitte saved");
        text.printer("\nTwitte saved!", ConsoleColors.GREEN_BOLD);
        text.getText();
        new Feed(username);
        return;
    }else {
        text.printer("Please insert valid number!", ConsoleColors.RED_BOLD_BRIGHT);
        text.getText();
        Menu(username);
        return;
    }
}catch (NumberFormatException exception){
        text.printer("\nInvalid command!\n", ConsoleColors.RED_BOLD_BRIGHT);
        new Feed(username);
        return;
        }
        }else if(response.length()>1 && response.charAt(0)=='!'){
            text.printer("\nChoose a Following to Forward twitte:", ConsoleColors.CYAN_BOLD);
            for (int i = 0; i < users.size() ; i++) {
                if (users.get(i).getUsername().equals(username)){
                    target = i;
                    break;
                }
            }
            try {
                int resnum = Integer.parseInt(response.substring(1));
                if (resnum < counter) {

                    for (int i = 0; i < users.size(); i++)
                        if(users.get(target).getFollowings().contains(users.get(i).getUsername())){
                            text.printer(sn+"-"+users.get(i).getUsername(),ConsoleColors.BLACK_BOLD_BRIGHT);
                            cheat[sn - 1] = i;
                            sn++;
                        }
                    new nextPage(1);
                    try {
                        int usernum = Integer.parseInt(text.getText());
                        if (usernum < sn) {
                            sb.SubMess("Forwarded Twitte from "+twittes.get(mapper[resnum - 1]).getSender()
                            +" : "+twittes.get(mapper[resnum - 1]).getText(),username,
                                    users.get(cheat[usernum-1]).getUsername());
                            new jsonUsers(users);
                            logger.info("System: Twitte Forwarded");
                            text.printer("\nTwitte Forwarded!", ConsoleColors.GREEN_BOLD);
                            text.getText();
                            new Feed(username);
                            return;

                        }else{
                            text.printer("\nPlease insert valid number!", ConsoleColors.RED_BOLD_BRIGHT);
                            text.getText();
                            Menu(username);
                            return;
                        }
                    }catch (NumberFormatException exception){
                        text.printer("\nInvalid command!\n", ConsoleColors.RED_BOLD_BRIGHT);
                        new Feed(username);
                        return;
                    }
                }else {
                    text.printer("\nPlease insert valid number!", ConsoleColors.RED_BOLD_BRIGHT);
                    text.getText();
                    Menu(username);
                    return;
                }
            }catch (NumberFormatException exception){
                text.printer("\nInvalid command!\n", ConsoleColors.RED_BOLD_BRIGHT);
                new Feed(username);
                return;
            }
        }

        try {
            int resnum = Integer.parseInt(response);
            if (resnum < counter) {
                new userProfile(username, twittes.get(mapper[resnum - 1]).getSender());
            }else {
                text.printer("Please insert valid number!", ConsoleColors.RED_BOLD_BRIGHT);
                text.getText();
                Menu(username);
            }
        }catch (NumberFormatException exception){
            getter(response);
        }
    }
}

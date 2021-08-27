import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Expelorer {
    Text text = new Text();
    String username;
    int counter=1;
    int [] mapper = new int[10000];
    jsonUsers Users_get = new jsonUsers();
    List<objUsers> users = Users_get.get();
    jsonTwittes Get = new jsonTwittes();
    List<objTwitte> twittes = Get.get();
    ArrayList<Integer> retorder = new ArrayList<Integer>();
    int target = 0;
    int tar = 0;
    submitMessage sb = new submitMessage();
    int[] cheat = new int [10000];
    int sn=1;
    private static final Logger logger = LogManager.getLogger(Expelorer.class);
    public Expelorer(String username) throws IOException {
        logger.info("System: user went to Expelorer");
        System.out.println();
        this.username=username;

        File f = new File("Twittes.json");
        if (f.exists()) {

            text.printer("Expelorer:", ConsoleColors.YELLOW_BOLD);
            for (int j = twittes.size()-1; j >= 0 ; j--)
                for (int i = 0; i < users.size() ; i++)
                        if (twittes.get(j).getRetwittes().contains(users.get(i).getUsername()))
                        for (int k = 0; k < users.size(); k++)
                            if (users.get(k).getUsername().equals(twittes.get(j).getSender()))
                                if (!(users.get(k).getBlocks().contains(username)) &&

                                        !(users.get(i).getBlocks().contains(username)) &&
                                        !(users.get(target).getMutes().contains(users.get(i).getUsername()))
                                        && !(users.get(target).getBlocks().contains(users.get(i).getUsername()))
                                      &&  !(retorder.contains(twittes.get(j).getSerial()))
                                        && (users.get(k).getIsEnable())&&
                                        (users.get(k).getAccount()||users.get(k).getFollowers().contains(username))
                                        || users.get(k).getUsername().equals(username)) {
                                    System.out.print(ConsoleColors.GREEN_BOLD +
                                            users.get(i).getUsername()+ " Retwitted: ");
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


            for (int i = twittes.size() - 1; i >= 0; i--)
                for (int j = 0; j < users.size(); j++)
                    if (users.get(j).getUsername().equals(username))
                        for (int k = 0; k < users.size(); k++)
                            if (users.get(k).getUsername().equals(twittes.get(i).getSender()))
                                if (!(users.get(k).getBlocks().contains(username)) &&
                                        !(users.get(j).getMutes().contains(twittes.get(i).getSender()))
                                        && !(users.get(j).getBlocks().contains(twittes.get(i).getSender()))
                                        && !(retorder.contains(twittes.get(i).getSerial()))
                                        && (users.get(k).getIsEnable()) &&
                                        (users.get(k).getAccount()||users.get(k).getFollowers().contains(username))
                                || users.get(k).getUsername().equals(username)) {
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
                tar = i;
                break;
            }
        }
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        switch (response){
            case "a" : new Home(username);return;
            case "b" : new Feed(username);return;
            case "c" : new Expelorer(username);return;
            case "d" : new Messenger(username);return;
            case "e" : new Setting(username);return;
            case "m" : new massMessenger(username);return;
            case "f" :
                users.get(tar).setLastseen(dtf.format(now));
                new jsonUsers(users);
                new Login(true);return;
            case "g" :
                users.get(tar).setLastseen(dtf.format(now));
                new jsonUsers(users);
                logger.info("User Exited the app");
                return;
            case "s" : searching(username);break;
            default: text.printer("Invalid command!", ConsoleColors.RED_BOLD_BRIGHT);
                Menu(username);break;
        }
    }
    public void Menu_command() {
        var text = new Text();
        text.printer("\nExpelorer:",ConsoleColors.CYAN_BOLD);
        text.printer("s-Search",ConsoleColors.BLACK_BOLD_BRIGHT);
        text.printer("a-Home page",ConsoleColors.BLACK_BOLD_BRIGHT);
        text.printer("b-Feed",ConsoleColors.BLACK_BOLD_BRIGHT);
        text.printer("c-Refresh Explorer",ConsoleColors.BLACK_BOLD_BRIGHT);
        text.printer("d-Messenger",ConsoleColors.BLACK_BOLD_BRIGHT);
        text.printer("m-Mass Messenger",ConsoleColors.BLACK_BOLD_BRIGHT);
        text.printer("e-Setting",ConsoleColors.BLACK_BOLD_BRIGHT);
        text.printer("f-Logout",ConsoleColors.BLACK_BOLD_BRIGHT);
        text.printer("g-Exit",ConsoleColors.BLACK_BOLD_BRIGHT);
    }

    public void searching(String username) throws IOException {
        text.printer("\nTo search users, insert the username or name: (a-Cancle)",ConsoleColors.BLUE_BOLD);
        String response=text.getText();
        if (!response.equals("a")) {
            for (int i = 0; i < users.size(); i++)
                if (users.get(i).getUsername().toLowerCase(Locale.ROOT).
                        equals(response.toLowerCase(Locale.ROOT))) {
                    for (int j = 0; j < users.size(); j++)
                        if (!(users.get(i).getBlocks().contains(username))&& users.get(i).getIsEnable()) {
                            new userProfile(username, users.get(i).getUsername());
                            return;
                        }
                } else if (users.get(i).getName().toLowerCase(Locale.ROOT).equals(response.toLowerCase(Locale.ROOT))) {
                    if (!(users.get(i).getBlocks().contains(username)) && users.get(i).getIsEnable()) {
                        new userProfile(username, users.get(i).getUsername());
                        return;
                    }
                }
                text.printer("\nUsername or name not found!\n", ConsoleColors.RED_BOLD_BRIGHT);
        }

            text.printer("a- Search again", ConsoleColors.BLACK_BOLD_BRIGHT);
            text.printer("b- Back to Expelorer", ConsoleColors.BLACK_BOLD_BRIGHT);
            switch (text.getText()){
                case "a" :  searching(username);break;
                case "b" :  new Expelorer(username);break;
                default: text.printer("Invalid command!", ConsoleColors.RED_BOLD_BRIGHT);
                Menu_command();
                    Menu(username);
                    return;
            }


        }


    public void Menu(String username) throws IOException {
        System.out.println();
        String response = text.getText();
    if(response.length()>1 && response.charAt(0)=='*' && response.charAt(1)!='*'){
        new submitLike(username, mapper,response,counter);
        text.getText();
        new Expelorer(username);
        return;
    }else if(response.length()>1 && response.charAt(0)=='*' && response.charAt(1)=='*'){
        submitLike Like = new submitLike();
        Like.list(mapper,response,counter,username);
        text.getText();
        new Expelorer(username);
        return;
    }else if(response.length()>1 && response.charAt(0)=='#' && response.charAt(1)!='#'){
        new submitRetwitte(username, mapper,response,counter);
        text.getText();
        new Expelorer(username);
        return;
    }else if(response.length()>1 && response.charAt(0)=='#' && response.charAt(1)=='#'){
        submitRetwitte Ret = new submitRetwitte();
        Ret.list(mapper,response,counter,username);
        text.getText();
        new Expelorer(username);
        return;
    }else if(response.length()>1 && response.charAt(0)=='$' ) {
        text.printer("\nSay your philosophy of report:", ConsoleColors.BLUE_BOLD);
        String reptext = text.getText();
        new nextPage(1);
        new Reports(username, reptext, mapper, response, counter);
        text.getText();
        new Expelorer(username);
        return;
    }else if(response.length()>1 && response.charAt(0)=='@' && response.charAt(1)!='@'){
        new submitComment(username, mapper,response,counter);
        text.getText();
        new Expelorer(username);
        return;
    }else if(response.length()>1 && response.charAt(0)=='@' && response.charAt(1)=='@'){
        submitComment Com = new submitComment();
        Com.list(mapper,response,counter,username);
        new Expelorer(username);
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
                text.printer("Twitte saved!", ConsoleColors.GREEN_BOLD);
                text.getText();
                new Expelorer(username);
                return;
            }else {
                text.printer("Please insert valid number!", ConsoleColors.RED_BOLD_BRIGHT);
                text.getText();
                Menu(username);
                return;
            }
        }catch (NumberFormatException exception){
            text.printer("\nInvalid command!\n", ConsoleColors.RED_BOLD_BRIGHT);
            new Expelorer(username);
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
                        new Expelorer(username);
                        return;

                    }else{
                        text.printer("\nPlease insert valid number!", ConsoleColors.RED_BOLD_BRIGHT);
                        text.getText();
                        Menu(username);
                        return;
                    }
                }catch (NumberFormatException exception){
                    text.printer("\nInvalid command!\n", ConsoleColors.RED_BOLD_BRIGHT);
                    new Expelorer(username);
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
            new Expelorer(username);
            return;
        }
    }

        try {
            int resnum = Integer.parseInt(response);
            if (resnum < counter){
                new userProfile(username, twittes.get(mapper[resnum - 1]).getSender());
            }else{
                text.printer("Please insert valid number!", ConsoleColors.RED_BOLD_BRIGHT);
                text.getText();
                Menu(username);
            }
        }catch (NumberFormatException exception){
            if(response.equals("g")){
                return;
            }
            getter(response);
        }
    }
}

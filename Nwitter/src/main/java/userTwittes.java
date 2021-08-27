import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class userTwittes {
    String username;
    int counter=1,sounter = 1;
    int numtwitte;
    int [] mapper = new int[10000];
    jsonTwittes Get = new jsonTwittes();
    jsonUsers Users_get = new jsonUsers();
    List<objTwitte> twittes = Get.get();
    jsonUsers get_users = new jsonUsers();
    List<objUsers> users = get_users.get();
    submitMessage sb = new submitMessage();
    int[] cheat = new int [10000];
    int sn=1;
    int target;
    private static final Logger logger = LogManager.getLogger(userTwittes.class);
    public userTwittes(String owner, String username) throws IOException {
        logger.info("System: user went to userTwittes");
        var text = new Text();
        System.out.println();
        this.username = owner;
        File f = new File("Twittes.json");
        if (f.exists()) {
            text.printer(owner+"'s Nwittes:", ConsoleColors.YELLOW_BOLD);
            for (int i = twittes.size() - 1; i >= 0; i--)
                if (twittes.get(i).getSender().equals(owner)) {
                    new twittePrinter(i,counter);
                    for (int l = 0; l < twittes.size(); l++)
                        if(twittes.get(l).getComments().contains(twittes.get(i).getSerial()))
                            text.printer("( It's a comment of: "+twittes.get(l).getText()+
                                    " from "+twittes.get(l).getSender()+" )",ConsoleColors.PURPLE_BOLD);
                    mapper[counter - 1] = i;
                    counter++;
                }
            new nextPage(1);
            if (counter==1)
                text.printer(owner+" doesn't have any Nwittes!\n", ConsoleColors.RED_BOLD_BRIGHT);
            text.printer(owner+"'s Renwittes:", ConsoleColors.YELLOW_BOLD);
            for (int i = twittes.size() - 1; i >= 0; i--)
                if (twittes.get(i).getRetwittes().contains(owner)) {
                    new twittePrinter(i,counter);
                    for (int l = 0; l < twittes.size(); l++)
                        if(twittes.get(l).getComments().contains(twittes.get(i).getSerial()))
                            text.printer("( It's a comment of: "+twittes.get(l).getText()+
                                    " from "+twittes.get(l).getSender()+" )",ConsoleColors.PURPLE_BOLD);
                    mapper[counter - 1] = i;
                    sounter++;
                    counter++;
                }

            if (sounter==1)
            text.printer(owner+" doesn't have any ReNwittes!\n", ConsoleColors.RED_BOLD_BRIGHT);
        }else
        text.printer(owner+" doesn't have any Nwittes or ReNwittes!\n", ConsoleColors.RED_BOLD_BRIGHT);


            while (true) {

                text.printer("\nh-Go to Home",ConsoleColors.BLACK_BOLD_BRIGHT);
                text.printer("f-Go to Feed\n",ConsoleColors.BLACK_BOLD_BRIGHT);

                for (int k = 0; k < users.size(); k++)
                    if (users.get(k).getUsername().equals(username)) {
                        target = k;
                        break;
                    }

                String response = text.getText();
                if (response.equalsIgnoreCase("h")) {
                    new Home(username);
                    return;
                } else if(response.equalsIgnoreCase("f")) {
                    new Feed(username);
                    return;
                }else if(response.length()>1 && response.charAt(0)=='*' && response.charAt(1)!='*'){
                    new submitLike(username, mapper,response,counter);
                    text.getText();
                    new userTwittes(owner,username);
                    return;
                }else if(response.length()>1 && response.charAt(0)=='*' && response.charAt(1)=='*'){
                    submitLike Like = new submitLike();
                    Like.list(mapper,response,counter,username);
                    text.getText();
                    new userTwittes(owner,username);
                }else if(response.length()>1 && response.charAt(0)=='#' && response.charAt(1)!='#'){
                    new submitRetwitte(username, mapper,response,counter);
                    text.getText();
                    new userTwittes(owner,username);
                    return;
                }else if(response.length()>1 && response.charAt(0)=='#' && response.charAt(1)=='#'){
                    submitRetwitte Ret = new submitRetwitte();
                    Ret.list(mapper,response,counter,username);
                    text.getText();
                    new userTwittes(owner,username);
                    return;
                }else if(response.length()>1 && response.charAt(0)=='$' ){
                    text.printer("\nSay your philosophy of report:",ConsoleColors.BLUE_BOLD);
                    String reptext = text.getText();
                    new nextPage(1);
                    new Reports(username,reptext,mapper,response,counter);
                    text.getText();
                    new userTwittes(owner,username);
                    return;
                }else if(response.length()>1 && response.charAt(0)=='@' && response.charAt(1)!='@'){
                    new submitComment(username, mapper,response,counter);
                    text.getText();
                    new userTwittes(owner,username);
                    return;
                }else if(response.length()>1 && response.charAt(0)=='@' && response.charAt(1)=='@'){
                    submitComment Com = new submitComment();
                    Com.list(mapper,response,counter,username);
                    text.getText();
                    new userTwittes(owner,username);
                    return;
                } else if(response.length()>1 && response.charAt(0)=='%'){
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
                    new userTwittes(owner,username);
                    return;
                }else {
                    text.printer("Please insert valid number!", ConsoleColors.RED_BOLD_BRIGHT);
                    text.getText();
                    new userTwittes(owner,username);
                    return;
                }
            }catch (NumberFormatException | IOException exception){
                text.printer("\nInvalid command!\n", ConsoleColors.RED_BOLD_BRIGHT);
                new userTwittes(owner,username);
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
                            new userTwittes(owner,username);
                            return;

                        }else{
                            text.printer("\nPlease insert valid number!", ConsoleColors.RED_BOLD_BRIGHT);
                            text.getText();
                            new userTwittes(owner,username);
                            return;
                        }
                    }catch (NumberFormatException exception){
                        text.printer("\nInvalid command!\n", ConsoleColors.RED_BOLD_BRIGHT);
                        new userTwittes(owner,username);
                        return;
                    }
                }else {
                    text.printer("\nPlease insert valid number!", ConsoleColors.RED_BOLD_BRIGHT);
                    text.getText();
                    new userTwittes(owner,username);
                    return;
                }
            }catch (NumberFormatException exception){
                text.printer("\nInvalid command!\n", ConsoleColors.RED_BOLD_BRIGHT);
                new userTwittes(owner,username);
                return;
            }
        }
                }
            }

    }


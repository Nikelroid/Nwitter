import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.List;

public class commentsPage {
    String username;
    List<Integer> serial;
    jsonTwittes Get = new jsonTwittes();
    List<objTwitte> twittes = Get.get();
    jsonUsers Getu = new jsonUsers();
    List<objUsers> users = Getu.get();
    int counter = 1;
    private static final Logger logger = LogManager.getLogger(commentsPage.class);

    int[] mapper = new int[10000];
    Text text = new Text();
    int tar;
    int target;
    submitMessage sb = new submitMessage();
    int[] cheat = new int [10000];
    int sn=1;
    public commentsPage(List<Integer> serial, String username) throws IOException {
        logger.info("System: user went to commentsPage");
        for (int i = 0; i < users.size() ; i++) {
            if (users.get(i).getUsername().equals(username)){
                tar = i;
                break;
            }
        }

        this.serial=serial;
        this.username=username;
        for (int i = twittes.size()-1; i >= 0; i--) {
            if (serial.contains(twittes.get(i).getSerial()))
                for (int j = 0; j < users.size(); j++) {
                    if (twittes.get(i).getSender().equals(users.get(j).getUsername()) &&
                            !(users.get(j).getBlocks().contains(username))
                    &&users.get(j).getIsEnable() &&  (users.get(j).getAccount()||users.get(j).getFollowers().contains(username)
                            || users.get(j).getUsername().equals(username))) {
                        new twittePrinter(i, counter);
                        mapper[counter - 1] = i;
                        counter++;
                    }
                }
        }
        text.printer("\nMenu:",ConsoleColors.CYAN_BOLD);
        text.printer("b-Back",ConsoleColors.BLACK_BOLD_BRIGHT);
        Menu(username);
    }
    public void Menu(String username) throws IOException {
        System.out.println();
        String response = text.getText();
        if(response.length()>1 && response.charAt(0)=='*' && response.charAt(1)!='*'){
            new submitLike(username, mapper,response,counter);
            text.getText();
            new commentsPage(serial,username);
            return;
        }else if(response.length()>1 && response.charAt(0)=='*' && response.charAt(1)=='*'){
            submitLike Like = new submitLike();
            Like.list(mapper,response,counter,username);
            text.getText();
            new commentsPage(serial,username);
            return;
        }else if(response.length()>1 && response.charAt(0)=='#' && response.charAt(1)!='#'){
            new submitRetwitte(username, mapper,response,counter);
            text.getText();
            new commentsPage(serial,username);
            return;
        }else if(response.length()>1 && response.charAt(0)=='#' && response.charAt(1)=='#'){
            submitRetwitte Ret = new submitRetwitte();
            Ret.list(mapper,response,counter,username);
            text.getText();
            new commentsPage(serial,username);
            return;
        }else if(response.length()>1 && response.charAt(0)=='$' ) {
            text.printer("\nSay your philosophy of report:", ConsoleColors.BLUE_BOLD);
            String reptext = text.getText();
            new nextPage(1);
            new Reports(username, reptext, mapper, response, counter);
            text.getText();
            new commentsPage(serial,username);
            return;
        }else if(response.length()>1 && response.charAt(0)=='@' && response.charAt(1)!='@'){
            new submitComment(username, mapper,response,counter);
            text.getText();
            new commentsPage(serial,username);
            return;
        }else if(response.length()>1 && response.charAt(0)=='@' && response.charAt(1)=='@'){
            submitComment Com = new submitComment();
            Com.list(mapper,response,counter,username);
            text.getText();
            new commentsPage(serial,username);
            return;
        }else if(response.length()>1 && response.charAt(0)=='%'){
                try {
                    int resnum = Integer.parseInt(response.substring(1));
                if (resnum < counter) {
                    for (int i = 0; i < users.size(); i++)
                        if (users.get(i).getUsername().equals(username))
                            users.get(i).getTwittesaved().add(twittes.get(mapper[resnum - 1]).getSerial());
                    new jsonUsers(users);
                    logger.info("System: Twitte saved!");
                    text.printer("Twitte saved!", ConsoleColors.GREEN_BOLD);
                    text.getText();
                    new commentsPage(serial,username);
                    return;
                }else {
                    text.printer("Please insert valid number!", ConsoleColors.RED_BOLD_BRIGHT);
                    text.getText();
                    Menu(username);
                    return;
                }
            }catch (NumberFormatException | IOException exception){
                text.printer("\nInvalid command!\n", ConsoleColors.RED_BOLD_BRIGHT);
                    new commentsPage(serial,username);
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
                            logger.info("System: Twitte Forwarded!");
                            text.printer("\nTwitte Forwarded!", ConsoleColors.GREEN_BOLD);
                            text.getText();
                            new commentsPage(serial,username);
                            return;

                        }else{
                            text.printer("\nPlease insert valid number!", ConsoleColors.RED_BOLD_BRIGHT);
                            text.getText();
                            Menu(username);
                            return;
                        }
                    }catch (NumberFormatException exception){
                        text.printer("\nInvalid command!\n", ConsoleColors.RED_BOLD_BRIGHT);
                        new commentsPage(serial,username);
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
                new commentsPage(serial,username);
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
        }catch (NumberFormatException | IOException exception){
            getter(response);
        }
    }
    public void getter(String response) throws IOException {
        var text = new Text();
        switch (response){
            case "b" : return;
            default: text.printer("Invalid command!", ConsoleColors.RED_BOLD_BRIGHT);
                new commentsPage(serial,username);break;
        }
    }
}

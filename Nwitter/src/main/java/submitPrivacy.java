import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

public class submitPrivacy {
    jsonUsers get_j =new jsonUsers();
    List<objUsers> users= get_j.get();
    int target;
    Text text = new Text();
    private static final Logger logger = LogManager.getLogger(submitPrivacy.class);
    public submitPrivacy(String ussername) throws IOException {
        logger.info("System: user went to submitPrivacy");
        for (int i = 0; i < users.size(); i++)
            if (users.get(i).getUsername().equals(ussername)){
                target=i;
                break;
        }
        for (int i = 0; i < 4; i++)
            printStatus(i);

            text.printer("\nPrivacy set:",ConsoleColors.CYAN_BOLD);
        text.printer("a-Last seen",ConsoleColors.BLACK_BOLD_BRIGHT);
        text.printer("b-Birthday date",ConsoleColors.BLACK_BOLD_BRIGHT);
        text.printer("c-Email",ConsoleColors.BLACK_BOLD_BRIGHT);
        text.printer("d-Phone number",ConsoleColors.BLACK_BOLD_BRIGHT);
        text.printer("e-Go to setting\n",ConsoleColors.BLACK_BOLD_BRIGHT);

        switch (text.getText()){
            case"a":
                privacypr();
                privacySwitcher(0);
                new submitPrivacy(ussername);
                return;
            case"b":
                privacypr();
                privacySwitcher(1);
                new submitPrivacy(ussername);
                return;
            case"c":
                privacypr();
                privacySwitcher(2);
                new submitPrivacy(ussername);
                return;
            case"d":
                privacypr();
                privacySwitcher(3);
                new submitPrivacy(ussername);
                return;
            case"e":
                new Setting(ussername);
                return;
            default:
             text.printer("\nPlease insert a valid command!\n", ConsoleColors.RED_BOLD_BRIGHT);
                new submitPrivacy(ussername);
                return;
        }
    }
    public void printStatus(int i){
        String var="";
        switch (i){
            case 0 : var = "Last seen    ";break;
            case 1 : var = "Birthday date";break;
            case 2 : var = "Email        ";break;
            case 3 : var = "Phone number ";break;
        }
        System.out.print(ConsoleColors.BLACK_BOLD + var +" status: ");
        switch (users.get(target).getPrivacy().get(i)) {
            case 1:
                text.printer("Everyone can see your "+var, ConsoleColors.GREEN_BOLD);
                break;
            case 2:
                text.printer("Just your followings can see your "+var, ConsoleColors.YELLOW_BOLD);
                break;
            case 3:
                text.printer("Nobody can't see your "+var, ConsoleColors.RED_BOLD_BRIGHT);
                break;
        }
    }
    public void privacySwitcher(int i){
        switch (text.getText()){
            case"a":privacySetter(i,1);break;
            case"b":privacySetter(i,2);break;
            case"c":privacySetter(i,3);break;
            case"d":break;
            default:
                text.printer("\nPlease insert a valid command!\n", ConsoleColors.RED_BOLD_BRIGHT);
        }
    }
    public void privacySetter(int i,int j){
        users.get(target).getPrivacy().set(i,j);
        new jsonUsers(users);
        logger.info("System: Privacy status saved");
        text.printer("\nNew privacy status set!",ConsoleColors.GREEN_BOLD);
        text.getText();
    }
    public void privacypr(){
        text.printer("\na-Everyone can see",ConsoleColors.BLACK_BOLD_BRIGHT);
        text.printer("b-Just Followings can see",ConsoleColors.BLACK_BOLD_BRIGHT);
        text.printer("c-Nobody can't see",ConsoleColors.BLACK_BOLD_BRIGHT);
        text.printer("d-Cancle\n",ConsoleColors.BLACK_BOLD_BRIGHT);
    }
}

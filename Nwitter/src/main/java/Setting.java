import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

public class Setting {
    Text text = new Text();
    submitEdit edt = new submitEdit();
    jsonUsers Users_get = new jsonUsers();
    List<objUsers> users = Users_get.get();
    String response;
    int target=0;
    private static final Logger logger = LogManager.getLogger(Setting.class);
    public Setting(String username) throws IOException {
        logger.info("System: user went to Setting");

        text.printer("\nSettings:",ConsoleColors.CYAN_BOLD);
        text.printer("a-Toggle account Public/Privet",ConsoleColors.BLACK_BOLD_BRIGHT);
        text.printer("b-Privacy account",ConsoleColors.BLACK_BOLD_BRIGHT);
        text.printer("c-Toggle account Enable/Disable",ConsoleColors.BLACK_BOLD_BRIGHT);
        text.printer("i-Categories",ConsoleColors.BLACK_BOLD_BRIGHT);
        text.printer("d-Delete account",ConsoleColors.BLACK_BOLD_BRIGHT);
        text.printer("e-Change password",ConsoleColors.BLACK_BOLD_BRIGHT);
        text.printer("f-Logout",ConsoleColors.BLACK_BOLD_BRIGHT);
        text.printer("g-Go to Feed",ConsoleColors.BLACK_BOLD_BRIGHT);
        text.printer("h-Go to Home\n",ConsoleColors.BLACK_BOLD_BRIGHT);

        for (int i = 0; i < users.size() ; i++) {
            if (users.get(i).getUsername().equals(username)){
                target = i;
                break;
            }
        }
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        switch (text.getText()){
            case "d":
                text.printer("Are you sure?",ConsoleColors.BLACK_BOLD);
                text.printer("y-Yes",ConsoleColors.GREEN_BOLD);
                text.printer("n-No\n",ConsoleColors.RED_BOLD_BRIGHT);
                switch (text.getText()){
                    case "y":
                        new submitDelet(username);
                        logger.info("System: Account deleted");
                        text.printer("\nAccount deleted!",ConsoleColors.RED_BOLD_BRIGHT);
                        text.getText();
                        new Login(true);
                        return;
                    case "n":
                        new Setting(username);
                        return;
                    default:
                        text.printer("\nPlease insert a valid cammand",ConsoleColors.RED_BOLD_BRIGHT);
                        text.getText();
                        new Setting(username);
                        return;
                }


            case "a":
                if(edt.editPrivacy(username)){
                    logger.info("System: Account set to Public");
                    text.printer("\nAccount set to Public!",ConsoleColors.GREEN_BOLD);
                }else{
                    logger.info("System: Account set to Private");
                    text.printer("\nAccount set to Private!",ConsoleColors.RED_BOLD_BRIGHT);
                }
                text.getText();
                new Setting(username);
                return;
            case "b":
                new submitPrivacy(username);
                text.getText();new Setting(username);return;
            case "c":
                if(edt.editStatus(username)){
                    logger.info("System: Account Enabled");
                    text.printer("\nAccount Enabled!",ConsoleColors.GREEN_BOLD);
                }else{
                    logger.info("System: Account Disabled");
                    text.printer("\nAccount Disabled!",ConsoleColors.RED_BOLD_BRIGHT);
                }
                text.getText();
                new Setting(username);
                return;
            case "e":
                changePass(username);
              text.getText();new Setting(username);return;
            case "f" :
                users.get(target).setLastseen(dtf.format(now));
                new jsonUsers(users);
                new Login(true);return;

            case "h": new Home(username);return;
            case "g": new Feed(username);return;
            case "i": new Category(username);return;
            default:
                text.printer("\nPlease insert a valid cammand",ConsoleColors.RED_BOLD_BRIGHT);
                text.getText();
                new Setting(username);
                return;
        }

    }

    private void changePass(String username) {
        String oldpass;
        while (true) {
            System.out.println();
            text.printer("Insert old password:",ConsoleColors.BLUE_BOLD);
            oldpass = text.getText();
            System.out.println();
            if (Register.Checker(oldpass, username)) break;
            else text.printer("Password is wrong!", ConsoleColors.RED_BOLD_BRIGHT);
        }
        String newpass;
        while (true) {
            while (true) {
                System.out.println();
                text.printer("Insert new password:", ConsoleColors.BLUE_BOLD);
                newpass = text.getText();

                if(newpass.isEmpty()) {
                    text.printer("Password can't be empty!\n", ConsoleColors.RED_BOLD_BRIGHT);
                } else if (newpass.length() < 6) {
                    text.printer("Password is too short!\n", ConsoleColors.RED_BOLD_BRIGHT);
                }else if(isstrong(newpass)){
                    text.printer("Your password is weak, use letters!", ConsoleColors.RED_BOLD_BRIGHT);
                }else if(newpass.toLowerCase(Locale.ROOT).equals(newpass)||
                        newpass.toUpperCase(Locale.ROOT).equals(newpass)) {
                    text.printer("Use letters in lower and uppercase!", ConsoleColors.RED_BOLD_BRIGHT);
                } else if (newpass.equals(oldpass)) {
                    text.printer("New password can be old one!\n", ConsoleColors.RED_BOLD_BRIGHT);
                } else {
                    break;
                }

            }
            text.printer("Insert new password again:", ConsoleColors.BLUE_BOLD);
            if (text.getText().equals(newpass)){
                break;
            }else {
                System.out.println();
                text.printer("\nPasswords doesnt match, try again!",ConsoleColors.RED_BOLD_BRIGHT);
            }
        }
        System.out.println();
        edt.editPassword(username,newpass);
        text.printer("\nPassword changed successfully!",ConsoleColors.GREEN_BOLD);
    }
    private boolean isstrong(String password){
        try{
            Long.parseLong(password);
            return true;
        }catch (NumberFormatException exception){
            return false;
        }
    }
}

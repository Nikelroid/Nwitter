import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class editProfile {
    String username;
    int target=0;
    private static final Logger logger = LogManager.getLogger(editProfile.class);
    public editProfile(String username) throws IOException {
        logger.info("System: user went to editProfile");
        Menu_command();
        this.username = username;


        var get_j = new jsonUsers();
        var text = new Text();
        List<objUsers> users = get_j.get();

        for (int i = 0; i < users.size(); i++)
            if (users.get(i).getUsername().equals(username))target=i;

        var sub = new submitEdit();
        String response = text.getText();
        System.out.println();
        switch (response) {
            case "a":
                String lastname,shortname;
                while (true) {
                    while (true) {
                        text.printer("* Insert new shortname:", ConsoleColors.BLUE_BOLD);
                        shortname = text.getText();
                        System.out.println();
                        if (shortname.isEmpty())
                            text.printer("Shortname can't be empty!\n", ConsoleColors.RED_BOLD_BRIGHT);
                        else break;
                    }
                    while (true) {
                        text.printer("* Insert new lastname:", ConsoleColors.BLUE_BOLD);
                        lastname = text.getText();
                        System.out.println();
                        if (lastname.isEmpty())
                            text.printer("Lastname can't be empty!\n", ConsoleColors.RED_BOLD_BRIGHT);
                        else break;
                    }
                    if (users.get(target).getName().equals(shortname + " " + lastname)) {
                        text.printer("New name can't be old one!\n", ConsoleColors.RED_BOLD_BRIGHT);
                    }else break;
                }
               sub.editName(username,shortname+" "+lastname);
                logger.info("System: Name changed");
                text.printer("Name changed successfully!",ConsoleColors.GREEN_BOLD);
               break;
            case "b":
                String newuser;
                while (true) {
                    text.printer("* Insert new username:", ConsoleColors.BLUE_BOLD);
                    System.out.println();
                    newuser = text.getText();
                    if (Register.Checker(newuser,1) &&!(newuser.equalsIgnoreCase(username))){
                        text.printer("Username exist, choose another username",ConsoleColors.RED_BOLD_BRIGHT);
                        System.out.println();
                    }else if(newuser.isEmpty()) {
                        text.printer("Username can't be empty!\n", ConsoleColors.RED_BOLD_BRIGHT);
                    }else if(newuser.length()<3) {
                        text.printer("Username is too short!\n", ConsoleColors.RED_BOLD_BRIGHT);
                    }else if(newuser.equals(username)) {
                        text.printer("New username can be old one!\n", ConsoleColors.RED_BOLD_BRIGHT);
                    }else{
                        break;
                    }
                }
                sub.editUsername(username,newuser);
                logger.info("System: Username changed");
                text.printer("Username changed successfully!",ConsoleColors.GREEN_BOLD);
                text.printer("h-Home",ConsoleColors.BLACK_BOLD_BRIGHT);
                text.printer("f-Feed",ConsoleColors.BLACK_BOLD_BRIGHT);
                text.printer("Enter-change something else\n",ConsoleColors.BLACK_BOLD_BRIGHT);
                switch (text.getText()){
                    case "h":new Home(newuser);
                    case "f":new Feed(newuser);
                    default:new editProfile(newuser);
                }
                return;
            case "c":
                String oldpass;
                while (true) {
                    System.out.println();
                    text.printer("*Insert old password:",ConsoleColors.BLUE_BOLD);
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
                        text.printer("Passwords doesnt match, try again!",ConsoleColors.RED_BOLD_BRIGHT);
                    }
                }
                System.out.println();
                sub.editPassword(username,newpass);
                logger.info("System: Password changed");
                text.printer("Password changed successfully!",ConsoleColors.GREEN_BOLD);
                break;
            case "d":
                while (true) {

                    String day,mounth,year;
                    System.out.println();
                    try {
                        while (true){
                        text.printer("Insert new day:", ConsoleColors.BLUE_BOLD);
                        day = text.getText();
                        if(!day.isEmpty())Integer.parseInt(day);
                        text.printer("Insert new mounth:", ConsoleColors.BLUE_BOLD);
                        mounth = text.getText();
                        if(!day.isEmpty())Integer.parseInt(mounth);
                        text.printer("Insert new year:", ConsoleColors.BLUE_BOLD);
                        year = text.getText();
                        if(!day.isEmpty())Integer.parseInt(year);
                        System.out.println();
                        if (users.get(target).getBirthday().equals(year + "/" + mounth + "/" + day)) {
                            text.printer("New birthday date can't be old one!\n", ConsoleColors.RED_BOLD_BRIGHT);
                        } else {
                            break;
                        }
                    }
                        sub.editBithdaydate(username,year+"/"+mounth+"/"+day);
                        logger.info("System: Birthday date changed");
                        text.printer("Birthday date changed successfully!",ConsoleColors.GREEN_BOLD);
                        break;
                    }catch (NumberFormatException ignored){
                        System.out.println();
                        text.printer("Please insert a valid number!",ConsoleColors.RED_BOLD_BRIGHT);
                    }
                }
                break;
            case "e":
                String newemail;
                while (true) {
                    text.printer("* Insert new Email address:", ConsoleColors.BLUE_BOLD);
                    System.out.println();
                    newemail = text.getText();
                    if (Register.Checker(newemail,2) && !(newemail.isEmpty()) &&
                    !(users.get(target).getEmail().equals(newemail))){
                        text.printer("Email address exist, choose another Email address",ConsoleColors.RED_BOLD_BRIGHT);
                        System.out.println();
                    }else if(newemail.isEmpty()) {
                        text.printer("Email address can't be empty!\n", ConsoleColors.RED_BOLD_BRIGHT);
                    }else if(users.get(target).getEmail().equals(newemail)) {
                        text.printer("New email address can't be old one!\n", ConsoleColors.RED_BOLD_BRIGHT);
                    }else{
                        break;
                    }
                }
                sub.editEmail(username,newemail);
                logger.info("System: Email address changed");
                text.printer("\nEmail address changed successfully!",ConsoleColors.GREEN_BOLD);
                break;
            case "f":

                int newNum;
                while (true) {
                    try {
                        String newnumber;
                        long intnum = 0;
                        while (true) {
                            text.printer("Insert new phone number address:", ConsoleColors.BLUE_BOLD);

                            newnumber = text.getText();
                            if (!newnumber.isEmpty()) {
                                intnum = Long.parseLong(newnumber);
                                System.out.println();
                                if (Register.Checker(newnumber, 3)) {
                                    text.printer("Phone number exist, choose another Phone number", ConsoleColors.RED_BOLD_BRIGHT);
                                    System.out.println();
                                } else if (users.get(target).getPhonenumber().equals(newnumber)) {
                                    text.printer("New phone number can't be old one!\n", ConsoleColors.RED_BOLD_BRIGHT);
                                } else {
                                    break;
                                }
                            }else break;
                        }
                        sub.editPhonenumber(username,newnumber);
                        logger.info("System: Phone number changed");
                        text.printer("Phone number changed successfully!",ConsoleColors.GREEN_BOLD);
                        break;
                    }catch (NumberFormatException ignored){
                        text.printer("Please insert a valid phone number!",ConsoleColors.RED_BOLD_BRIGHT);
                        System.out.println();
                    }
                }
                break;
            case "g":
                String newBio;
                while (true) {
                    text.printer("Insert new bio:", ConsoleColors.BLUE_BOLD);
                    System.out.println();
                   newBio = text.getText();
                    if (users.get(target).getBio().equals(newBio)) {
                        text.printer("New bio can't be old one!\n", ConsoleColors.RED_BOLD_BRIGHT);
                    }else{
                        break;
                    }
                }
                sub.editBio(username,newBio);
                logger.info("System: Bio changed");
                text.printer("Bio changed successfully!",ConsoleColors.GREEN_BOLD);
                break;

            case "h":
               new Home(username);
                break;
        }
        new editProfile(username);
    }
        public void Menu_command() {
            var text = new Text();
            System.out.println();
            text.printer("Which you want to edit:",ConsoleColors.CYAN_BOLD);
            text.printer("a-Name",ConsoleColors.BLACK_BOLD_BRIGHT);
            text.printer("b-Username",ConsoleColors.BLACK_BOLD_BRIGHT);
            text.printer("c-Password",ConsoleColors.BLACK_BOLD_BRIGHT);
            text.printer("d-Birthday date",ConsoleColors.BLACK_BOLD_BRIGHT);
            text.printer("e-Email address",ConsoleColors.BLACK_BOLD_BRIGHT);
            text.printer("f-Phone number",ConsoleColors.BLACK_BOLD_BRIGHT);
            text.printer("g-Biography",ConsoleColors.BLACK_BOLD_BRIGHT);
            text.printer("h-Go to home",ConsoleColors.BLACK_BOLD_BRIGHT);
            System.out.println();
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


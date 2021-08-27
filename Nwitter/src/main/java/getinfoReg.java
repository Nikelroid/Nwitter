

import java.util.Locale;
import java.util.Scanner;

public class getinfoReg {

    String inputtext;


    private String type(int num){
        return switch (num) {
            case 1 -> "shortname*";
            case 2 -> "lastname*";
            case 3 -> "username*";
            case 4 -> "password*";
            case 5 -> "password again*";
            case 6 -> "birthday (day)";
            case 7 -> "birthday (mounth)";
            case 8 -> "birthday (year)";
            case 9 -> "email addres*";
            case 10 -> "phone number";
            case 11 -> "bio";
            default -> null;
        };
    }

    private final String[] reg_info =new String[11];
    public String[] getReg_info() {
        var text = new Text();
        text.printer("* Fields are necessary",ConsoleColors.RED_BOLD_BRIGHT);
        for (int i = 1; i < 12; i++) {
            System.out.println();
            text.printer("Please insert your "+type(i)+" : ",ConsoleColors.BLUE_BOLD);
            inputtext=text.getText();
            if (i==5 && !inputtext.equals(reg_info[3])) {
                error(i);
                i -= 2;
            }else if(i==10 && inputtext.isEmpty()){
                inputtext="0";
            }else if(i==1 && inputtext.isEmpty()){
                text.printer("\nShortname can't be empty!",ConsoleColors.RED_BOLD_BRIGHT);
                i--;
            }else if(i==2 && inputtext.isEmpty()){
                text.printer("\nLastname can't be empty!",ConsoleColors.RED_BOLD_BRIGHT);
                i--;
            }else if(i==3 && inputtext.isEmpty()){
                text.printer("\nUsername can't be empty!",ConsoleColors.RED_BOLD_BRIGHT);
                i--;
            }else if(i==4 && inputtext.isEmpty()){
                text.printer("\nPassword can't be empty!",ConsoleColors.RED_BOLD_BRIGHT);
                i--;
            }else if(i==5 && inputtext.isEmpty()){
                text.printer("\nRe password can't be empty!",ConsoleColors.RED_BOLD_BRIGHT);
                i--;
            }else if(i==9 && inputtext.isEmpty()){
                text.printer("\nEmail can't be empty!",ConsoleColors.RED_BOLD_BRIGHT);
                i--;
            }else if(i==4 && inputtext.length()<6){
                error(i);
                i--;
            }else if(i==3 && inputtext.length()<3){
                error(-1);
                i--;
            }else if(i==4&&isstrong(inputtext)){
                text.printer("\nYour password is weak, use letters!",ConsoleColors.RED_BOLD_BRIGHT);
                i--;
            }else if(i==4&&(inputtext.toLowerCase(Locale.ROOT).equals(inputtext)||
                    inputtext.toUpperCase(Locale.ROOT).equals(inputtext))) {
                text.printer("\nUse letters in lower and uppercase!",ConsoleColors.RED_BOLD_BRIGHT);
                i--;
            }else if((i==6||i==7||i==8||i==10) && intchecker(inputtext) && !inputtext.isEmpty()){
                error(0);
                i--;
            }else if(i==3 && Register.Checker(inputtext,1)){
                error(i);
                i--;

            }else if(i==9 && Register.Checker(inputtext,2)){
                error(i);
                i--;
            }else if(i==10 && Register.Checker(inputtext,3)){
                error(i);
                i--;
            }else {
                this.reg_info[i-1]=inputtext;
            }
        }
        return reg_info;
    }
    private void error(int code){
        var text = new Text();
        String s = "";
        switch (code) {
            case -1 -> s="\nYour Username is too short, ";
            case 5 -> s="\nYour password doesn't mach with your repeat password, ";
            case 4 -> s="\nYour password is too short, ";
            case 3 -> s="\nYour Username already exists, ";
            case 9 -> s="\nYour Email address already exists, ";
            case 10 -> s="\nYour Phone number already exists, ";
            case 0 -> s="\nYour inserted number isn't valid, ";
            default -> s="\n";
        };
        text.printer(s+"try again!" , ConsoleColors.RED_BOLD_BRIGHT);

    }
    private boolean intchecker(String checkcase){
        try{
            Long.parseLong(checkcase);
            return false;
        }catch (NumberFormatException exception){
            return true;
        }
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

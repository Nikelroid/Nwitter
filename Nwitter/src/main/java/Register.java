import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class Register {
    public Register() {
    }

    private final boolean register_status = false;
    private static final Logger logger = LogManager.getLogger(Register.class);
    public void Registeruser() throws IOException {
        logger.info("System: user went to Register");
        System.out.println();
        var Get = new getinfoReg();
        var text = new Text();
        String[] reg_info = Get.getReg_info();
        var submit = new submitRegister();
        if (submit.Sub_regester(reg_info)) {
            logger.info("System: Registered");
            text.printer("\nRegistered successfully!", ConsoleColors.GREEN_BOLD);
        }else
            text.printer("\nAn error occurred  in registering, try again!", ConsoleColors.RED_BOLD);

        text.getText();
        new Login(true);
    }
    public static boolean Checker(String input , int type){
        File f = new File("Users.json");
        if (f.exists()) {
            var get_j =new jsonUsers();
            var text = new Text();
            List<objUsers> users = get_j.get();
            switch (type) {
                case 1:
                    for (objUsers user : users)
                        if (input.equalsIgnoreCase(user.getUsername()))
                            return true;
                    break;
                case 2:
                    for (objUsers user : users)
                        if (input.equals(user.getEmail()))
                            return true;
                    break;
                case 3:
                    for (objUsers user : users)
                        if (input.equals(user.getPhonenumber()))
                            return true;
                    break;
                }

            }

        return false;
        }
    public static boolean Checker(String pass , String username) {
        File f = new File("Users.json");
        if (f.exists()) {
            var get_j = new jsonUsers();
            var text = new Text();
            List<objUsers> users = get_j.get();
            for (int i = 0; i < users.size(); i++)
                if (users.get(i).getUsername().equals(username)&&
                        users.get(i).getPassword().equals(pass))
                    return true;
            }
        return false;
        }


}


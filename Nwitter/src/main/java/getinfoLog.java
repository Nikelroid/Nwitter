import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class getinfoLog {
    Scanner input = new Scanner(System.in);
    private final String[] log_info = new String[2];

    public getinfoLog() {

    }
    private static final Logger logger = LogManager.getLogger(getinfoLog.class);
    public void log_info(String log) throws IOException {
        logger.info("System: user went to LogInfo");
        var Get=new jsonUsers();
        log_info[0] = log;
        var text = new Text();
        text.printer("Please insert your password : ", ConsoleColors.BLACK_BOLD_BRIGHT);
        log_info[1] = text.getText();
        List<objUsers> users = Get.get();
        File f = new File("Users.json");
        if (f.exists()) {

            for(int i = 0; i < users.size(); i++) {
                if (log_info[0].equalsIgnoreCase(users.get(i).getUsername()) &&
                        log_info[1].equals(users.get(i).getPassword())) {
                    System.out.println();
                    logger.info("System: User logged in");
                    text.printer("You logged in Successfully!", ConsoleColors.GREEN_BOLD);
                    users.get(i).setLastseen("Online");
                    new jsonUsers(users);
                    new Feed(users.get(i).getUsername());
                    return;
                }
            }
                    new nextPage(1);

                    text.printer("Your Username or Password was wrong, please try again!", ConsoleColors.RED_BOLD_BRIGHT);
                    new Login(true);
                    return;



        }
        return;
    }
}


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class Login {
    List<objUsers> users;
    private boolean logged = true;
    private static final Logger logger = LogManager.getLogger(Login.class);
    public Login(boolean account) throws IOException {
        logger.info("System: user went to Login");
        var text = new Text();
        this.logged = account;
        File f = new File("Users.json");
        System.out.println();
        var Register = new Register();
        if (f.exists()) {
            text.printer("Insert your username:" +
                            " (if you don't have already an account, insert \"R\")",
                    ConsoleColors.BLACK_BOLD_BRIGHT);
            var Getinfo_log = new getinfoLog();
            String Unknown = text.getText();
            String[] log_info = new String[2];
            if (Unknown.equals("R")) Register.Registeruser();
            else {
                Getinfo_log.log_info(Unknown);
            }
        }else {
            text.printer("You don't have an account, please create one!", ConsoleColors.BLACK_BOLD_BRIGHT);
            Register.Registeruser();
        }
    }
}

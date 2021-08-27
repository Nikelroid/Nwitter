
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import java.util.Scanner;

public class Text extends ConsoleColors{
    private static final Logger logger = LogManager.getLogger(Text.class);


    private String response;


    Scanner input = new Scanner(System.in);

    public Text() {
    }

    public void printer(String text ) {

        System.out.println(ConsoleColors.RESET+text);
    }
    public <color> void printer(String text, Object color) {

        System.out.println(color+text);
    }

    public String getText() {
        String response;
        response = input.nextLine();
        logger.info("User Inserted : " + response);
        return response;
    }
    public int getInt() {
        return input.nextInt();
    }


}

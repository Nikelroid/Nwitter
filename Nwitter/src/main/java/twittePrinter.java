import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class twittePrinter {
    jsonTwittes Get = new jsonTwittes();
    List<objTwitte> twittes = Get.get();
    jsonUsers us = new jsonUsers();
    List<objUsers> users = us.get();
    Text text = new Text();
    private static final Logger logger = LogManager.getLogger(twittePrinter.class);
    public twittePrinter(int i, int counter) {
        logger.info("System: user went to twittePrinter");
        logger.info("Twitte "+twittes.get(i).getSerial()+" Printed");
            text.printer(counter + "- " + twittes.get(i).getText() +
                            " | Sender:" + twittes.get(i).getSender() +
                            " | Likes:" + (twittes.get(i).getLikes().size() - 1) +
                            " | Comment:" + (twittes.get(i).getComments().size() - 1) +
                            " | Retwittes:" + (twittes.get(i).getRetwittes().size() - 1) +
                            " | Data:" + twittes.get(i).getTime(),
                    ConsoleColors.BLUE_BOLD_BRIGHT);
    }
}

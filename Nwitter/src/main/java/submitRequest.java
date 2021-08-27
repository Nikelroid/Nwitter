import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.util.Collections;
import java.util.List;

public class submitRequest {
    jsonNotifs Not =new jsonNotifs();
    List<objNotifs> notifs= Not.get();
    jsonUsers get_j =new jsonUsers();
    Text text = new Text();
    List<objUsers> users= get_j.get();
    submitAction submit = new submitAction();
    File f = new File("Notifs.json");

    public submitRequest() {
    }
    private static final Logger logger = LogManager.getLogger(submitRequest.class);
    public void submitReq(String reqer, String requed) {
        logger.info("System: user went to submitRequest");

        if (f.exists()) {
            notifs.add(new objNotifs(8,reqer,requed));
        } else {
            notifs = Collections.singletonList(
                    new objNotifs(8,reqer,requed));
        }
        new jsonNotifs(notifs);
    }
    public void submitUnreq(String unreqer, String unreqed) {


        if (f.exists()) {
            for (int i = 0; i < notifs.size(); i++) {
                if (notifs.get(i).getUser1().equals(unreqer) &&
                        notifs.get(i).getUser2().equals(unreqed) &&
                        notifs.get(i).getType()==8){
                    notifs.remove(i);
                    break;
                }
            }
        }
        new jsonNotifs(notifs);
    }

    public void submitReject(String response, int[] mapper,int counter) {

        int numnotif=0;
        try {
            numnotif=Integer.parseInt(response.substring(1));
            if (numnotif>=counter)
                text.printer("\nPlease insert command as a valid format!",ConsoleColors.RED_BOLD_BRIGHT);
            else{
                if (f.exists()) {
                            notifs.remove(mapper[numnotif-1]);
                    new jsonNotifs(notifs);
                    logger.info("System: The request rejected");
                    text.printer("\nThe request rejected!",ConsoleColors.RED_BOLD_BRIGHT);
                    return;
                    }
                }

        }catch (NumberFormatException | StringIndexOutOfBoundsException e){
            text.printer("\nPlease insert command as a valid format!",ConsoleColors.RED_BOLD_BRIGHT);
        }
    }

    public void submitMuteAccept(String response, int[] mapper,int counter) {


        int numnotif=0;
        try {
            numnotif=Integer.parseInt(response.substring(1));
            if (numnotif>=counter)
                text.printer("\nPlease insert command as a valid format!",ConsoleColors.RED_BOLD_BRIGHT);
            else{
                if (f.exists()) {
                    submit.Submit_follow(notifs.get(mapper[numnotif-1]).getUser1(),
                            notifs.get(mapper[numnotif-1]).getUser2());
                    notifs.remove(mapper[numnotif-1]);
                    new jsonNotifs(notifs);
                    logger.info("System: The request Accepted mutely");
                    text.printer("\nThe request Accepted mutely!",ConsoleColors.YELLOW_BOLD);
                    followBack(mapper, numnotif);
                    new jsonNotifs(notifs);
                    return;
                }
            }

        }catch (NumberFormatException | StringIndexOutOfBoundsException e){
            text.printer("\nPlease insert command as a valid format!",ConsoleColors.RED_BOLD_BRIGHT);
        }
    }

    public void submitAccept(String response, int[] mapper,int counter) {


        int numnotif=0;
        try {
            numnotif=Integer.parseInt(response.substring(1));
            if (numnotif>=counter)
                text.printer("\nPlease insert command as a valid format!",ConsoleColors.RED_BOLD_BRIGHT);
            else{
                if (f.exists()) {
                    submit.Submit_follow(notifs.get(mapper[numnotif-1]).getUser1(),
                            notifs.get(mapper[numnotif-1]).getUser2());
                    notifs.add(new objNotifs(9,
                            notifs.get(mapper[numnotif-1]).getUser1(),
                            notifs.get(mapper[numnotif-1]).getUser2()));
                    notifs.remove(mapper[numnotif-1]);
                    new jsonNotifs(notifs);
                    logger.info("System: The request Accepted!");
                    text.printer("\nThe request Accepted!",ConsoleColors.GREEN_BOLD);
                    new jsonNotifs(notifs);
                    followBack(mapper, numnotif);
                }
                    return;
                }

        }catch (NumberFormatException | StringIndexOutOfBoundsException e){
            text.printer("\nPlease insert command as a valid format!",ConsoleColors.RED_BOLD_BRIGHT);
        }
    }

    private void followBack(int[] mapper, int numnotif) {
        text.printer("\nFollow him back? (y for yes)\n",ConsoleColors.RED_BOLD_BRIGHT);
        if (text.getText().equals("y")) {
            submit.Submit_follow(notifs.get(mapper[numnotif - 1]).getUser2(),
                    notifs.get(mapper[numnotif - 1]).getUser1());
            new jsonNotifs(notifs);
            logger.info("System: User followed back!");
            text.printer("\nUser followed back!\n", ConsoleColors.RED_BOLD_BRIGHT);
        }
        new jsonNotifs(notifs);
    }

}

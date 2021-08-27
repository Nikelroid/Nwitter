import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class submitRetwitte {
    jsonTwittes Get = new jsonTwittes();
    List<objTwitte> twittes = Get.get();
    jsonUsers get_j = new jsonUsers();
    List<objUsers> users = get_j.get();
    Text text = new Text();

    public submitRetwitte(){}
    private static final Logger logger = LogManager.getLogger(submitRetwitte.class);
    public submitRetwitte(String reter,int [] mapper,String response,int counter) {
        logger.info("System: user went to submitRetwitte");
        int numtwitte=0;
        try {
            numtwitte=Integer.parseInt(response.substring(1));
            if (numtwitte>=counter)
                text.printer("\nPlease insert command as a valid format!",ConsoleColors.RED_BOLD_BRIGHT);
            else{

                if(twittes.get(mapper[numtwitte - 1]).getRetwittes().contains(reter)){
                    twittes.get(mapper[numtwitte - 1]).getRetwittes().remove(reter);
                    logger.info("System: Nwitte Unretwitted");
                    text.printer("\nNwitte Unretwitted!", ConsoleColors.RED_BOLD_BRIGHT);
                }else {
                    twittes.get(mapper[numtwitte - 1]).getRetwittes().add(reter);
                    logger.info("System: Nwitte Retwitted");
                    text.printer("\nNwitte Retwitted!", ConsoleColors.GREEN_BOLD);
                }
                new jsonTwittes(twittes);
                return;
            }
        }catch (NumberFormatException | StringIndexOutOfBoundsException e){
            text.printer("\nPlease insert command as a valid format!",ConsoleColors.RED_BOLD_BRIGHT);
        }

    }
    public void list(int [] mapper,String response,int counter,String username){
        int numtwitte=0;
        int c=1;
        try {
            numtwitte=Integer.parseInt(response.substring(2));
            if (numtwitte>=counter)
                text.printer("\nPlease insert command as a valid format!",ConsoleColors.RED_BOLD_BRIGHT);
            else {
                if (twittes.get(mapper[numtwitte - 1]).getRetwittes().size()!=1){
                    text.printer("\nRenwitters:",ConsoleColors.BLUE_BOLD);
                    for (int i = twittes.get(mapper[numtwitte - 1]).getRetwittes().size()-1; i > 0; i--)
                        for (int j = 0; j < users.size(); j++)
                            if (users.get(j).getUsername().equals(twittes.get(mapper[numtwitte - 1]).getRetwittes().get(i))
                                    && !users.get(j).getBlocks().contains(username) && users.get(j).getIsEnable()) {
                        text.printer(c + "-" +twittes.get(mapper[numtwitte - 1]).getRetwittes().get(i),
                                ConsoleColors.RED_BOLD);
                        c++;
                    }
                    if (c==1) text.printer("\nNo body Renwitted this Nwitte!",ConsoleColors.RED_BOLD_BRIGHT);
                }
            }
        }catch (NumberFormatException | StringIndexOutOfBoundsException e){
            text.printer("\nPlease insert command as a valid format!",ConsoleColors.RED_BOLD_BRIGHT);
        }
    }
}

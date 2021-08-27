import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class submitLike {
    jsonTwittes Get = new jsonTwittes();
    List<objTwitte> twittes = Get.get();
    jsonUsers get_j = new jsonUsers();
    List<objUsers> users = get_j.get();

    Text text = new Text();

    public submitLike(){}
    private static final Logger logger = LogManager.getLogger(submitLike.class);
    public submitLike(String liker,int [] mapper,String response,int counter) {
        logger.info("System: user went to submitLike");

        int numtwitte=0;
        try {
            numtwitte=Integer.parseInt(response.substring(1));
            if (numtwitte>=counter)
                text.printer("\nPlease insert command as a valid format!",ConsoleColors.RED_BOLD_BRIGHT);
            else{

                if(twittes.get(mapper[numtwitte - 1]).getLikes().contains(liker)){
                    twittes.get(mapper[numtwitte - 1]).getLikes().remove(liker);
                    logger.info("System: unLiked!");
                    text.printer("\nNwitte unLiked!", ConsoleColors.RED_BOLD_BRIGHT);
                }else {
                    twittes.get(mapper[numtwitte - 1]).getLikes().add(liker);
                    logger.info("System: Liked!");
                    text.printer("\nNwitte Liked!", ConsoleColors.GREEN_BOLD);
                }
                new jsonTwittes(twittes);
                return;
            }
        }catch (NumberFormatException | StringIndexOutOfBoundsException e){
            text.printer("\nPlease insert command as a valid format!",ConsoleColors.RED_BOLD_BRIGHT);
        }

    }
    public void list(int [] mapper,String response,int counter,String username){
        logger.info("System: Like list opened");
        int numtwitte=0;
        int c=1;
        try {
            numtwitte=Integer.parseInt(response.substring(2));
            if (numtwitte>=counter)
                text.printer("\nPlease insert command as a valid format!",ConsoleColors.RED_BOLD_BRIGHT);
            else {
                if (twittes.get(mapper[numtwitte - 1]).getLikes().size() != 1) {
                    text.printer("\nLikers:", ConsoleColors.BLUE_BOLD);
                    for (int i = twittes.get(mapper[numtwitte - 1]).getLikes().size() - 1; i > 0; i--)
                        for (int j = 0; j < users.size(); j++)
                            if (users.get(j).getUsername().equals(twittes.get(mapper[numtwitte - 1]).getLikes().get(i))
                                    && !users.get(j).getBlocks().contains(username) && users.get(j).getIsEnable()) {
                                text.printer(c + "-" + twittes.get(mapper[numtwitte - 1]).getLikes().get(i),
                                        ConsoleColors.RED_BOLD);
                                c++;
                            }
                    if (c==1)
                        text.printer("\nNo body likes this Nwitte!", ConsoleColors.RED_BOLD_BRIGHT);
                }
            }
            }catch (NumberFormatException | StringIndexOutOfBoundsException e){
                text.printer("\nPlease insert command as a valid format!",ConsoleColors.RED_BOLD_BRIGHT);
            }
    }
}

import java.io.IOException;
import java.util.List;

public class submitComment {
    jsonTwittes Get = new jsonTwittes();
        List<objTwitte> twittes = Get.get();
        Text text = new Text();
        Newtwitte com = new Newtwitte();
        int serial;
        public submitComment(){}
        public submitComment(String commenter,int [] mapper,String response,int counter) {
            int numtwitte=0;
            try {
                numtwitte=Integer.parseInt(response.substring(1));
                if (numtwitte>=counter)
                    text.printer("\nPlease insert command as a valid format!",ConsoleColors.RED_BOLD_BRIGHT);
                else{
                    var com = new Newtwitte();
                    serial = com.newComment(commenter);
                    if (serial!=-1) {
                        twittes = Get.get();
                        twittes.get(mapper[numtwitte - 1]).getComments().add(serial);
                        new jsonTwittes(twittes);
                    }
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
                    if (twittes.get(mapper[numtwitte - 1]).getComments().size()==1){
                        text.printer("\nNo body Commented this Nwitte!",ConsoleColors.RED_BOLD_BRIGHT);
                    }else {
                        text.printer("\nComments:",ConsoleColors.YELLOW_BOLD);
                        new commentsPage(twittes.get(mapper[numtwitte - 1]).getComments(),username);
                    }
                }
            }catch (NumberFormatException | StringIndexOutOfBoundsException | IOException e){
                text.printer("\nPlease insert command as a valid format!",ConsoleColors.RED_BOLD_BRIGHT);
            }
        }
    }

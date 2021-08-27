import java.io.File;
import java.util.List;

public class submitDelet {
    jsonUsers get_j = new jsonUsers();
    jsonNotifs get_n = new jsonNotifs();
    Text text = new Text();
    List<objUsers> users = get_j.get();
    List<objTwitte> twittes;
    List<objNotifs> notifs = get_n.get();
    jsonTwittes Get = new jsonTwittes();
    File f = new File("Twittes.json");
    File n = new File("Notifs.json");

    public submitDelet(String username) {

        for (int i = 0; i < users.size(); i++)
            if (users.get(i).getUsername().equals(username)) {
                users.remove(i);
                break;
            }


        for (int i = 0; i < users.size(); i++) {
            for (int j = 0; j < users.get(i).getFollowers().size(); j++)
                if (users.get(i).getFollowers().get(j).equals(username)) {
                    users.get(i).getFollowers().remove(j);
                    break;
                }
            for (int j = 0; j < users.get(i).getFollowings().size(); j++)
                if (users.get(i).getFollowings().get(j).equals(username)) {
                    users.get(i).getFollowings().remove(j);
                    break;
                }
            for (int j = 0; j < users.get(i).getBlocks().size(); j++)
                if (users.get(i).getBlocks().get(j).equals(username)) {
                    users.get(i).getBlocks().remove(j);
                    break;
                }
            for (int j = 0; j < users.get(i).getMutes().size(); j++)
                if (users.get(i).getMutes().get(j).equals(username)) {
                    users.get(i).getMutes().remove(j);
                    break;
                }
        }
        new jsonUsers(users);


        if (f.exists()) {
            twittes = Get.get();
            for (int i = twittes.size()-1; i >=0; i--)
                if (twittes.get(i).getSender().equals(username)) {
                    for (int j = twittes.size()-1; j >=0 ; j--)
                        for (int k = twittes.get(j).getComments().size()-1; k>=0 ; k--)
                       if (twittes.get(j).getComments().get(k)==twittes.get(i).getSerial())
                           twittes.get(j).getComments().remove(k);

                    twittes.remove(i);
                }

            for (int i = twittes.size()-1; i >=0; i--) {
                for (int j = twittes.get(i).getLikes().size()-1; j >=0 ; j--)
                    if (twittes.get(i).getLikes().get(j).equals(username)) {
                        twittes.get(i).getLikes().remove(j);
                    }
                for (int j = twittes.get(i).getRetwittes().size()-1; j >=0 ; j--)
                    if (twittes.get(i).getRetwittes().get(j).equals(username)) {
                        twittes.get(i).getRetwittes().remove(j);
                    }
            }


            new jsonTwittes(twittes);
        }


        if (n.exists()) {
            notifs = get_n.get();

            for (int i = notifs.size()-1; i >=0; i--) {
                if (notifs.get(i).getUser1().equals(username)) {
                    notifs.remove(i);
                }
                if (notifs.get(i).getUser2().equals(username)) {
                    notifs.remove(i);
                }
            }

            new jsonNotifs(notifs);
        }


    }
}

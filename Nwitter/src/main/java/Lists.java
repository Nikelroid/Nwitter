import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.List;

public class Lists {
    int counter;
    String res;
    private static final Logger logger = LogManager.getLogger(Lists.class);
    public Lists(String username,String you) throws IOException {
        logger.info("System: user went to Lists");
        counter=1;
        var get_j = new jsonUsers();
        var text = new Text();
        int[] mapper = new int[10000];
        List<objUsers> users = get_j.get();
        new nextPage(1);
        text.printer("Lists:", ConsoleColors.CYAN_BOLD);
        text.printer("a- "+username+"s followings list", ConsoleColors.BLACK_BOLD_BRIGHT);
        text.printer("b- "+username+"s followers list", ConsoleColors.BLACK_BOLD_BRIGHT);
        text.printer("c- "+username+"s block list", ConsoleColors.BLACK_BOLD_BRIGHT);
        text.printer("d- "+username+"s mute list ", ConsoleColors.BLACK_BOLD_BRIGHT);
        text.printer("e-Go to Home", ConsoleColors.BLACK_BOLD_BRIGHT);
        text.printer("f-Go to Feed", ConsoleColors.BLACK_BOLD_BRIGHT);
        new nextPage(1);
        String response = text.getText();
        new nextPage(1);
        if (response.equals("e")){
            new Home(you);
            return;
        }else if(response.equals("f")){
            new Feed(you);
            return;
        }else {
            int target = 0;
            for (int i = 0; i < users.size(); i++)
                if (users.get(i).getUsername().equals(username))
                    target = i;

            switch (response) {
                case "a":
                    if (users.get(target).getFollowings().size() != 1){
                        text.printer(username + "s Followings:", ConsoleColors.CYAN_BOLD);
                    for (int j = 1; j < users.get(target).getFollowings().size(); j++)
                        if (!(users.get(target).getBlocks().contains(you)))
                            for (int i = 0; i < users.size(); i++)
                                if (users.get(i).getUsername().equals(users.get(target).getFollowings().get(j))
                                        && users.get(i).getIsEnable()){
                                    text.printer(counter + "- " + users.get(target).getFollowings().get(j),
                                            ConsoleColors.BLACK_BOLD_BRIGHT);
                        mapper[counter - 1] = j;
                        counter++;
                    }
            }
            if (counter == 1) {
                text.printer(username + " dont follow anybody!", ConsoleColors.RED_BOLD_BRIGHT);
            } else {
                text.printer("\nInsert users number:", ConsoleColors.PURPLE_BOLD);
                res = text.getText();

                try {
                    int resnum = Integer.parseInt(res);
                    if (resnum < counter) {
                        new userProfile(you, users.get(target).getFollowings().get(mapper[resnum - 1]));
                        return;
                    } else {
                        text.printer("Please insert valid number!", ConsoleColors.RED_BOLD_BRIGHT);
                        text.getText();
                        new Lists(username, you);
                        return;
                    }
                } catch (NumberFormatException exception) {
                    new Lists(username, you);
                    return;
                }
            }
                    new Lists(username, you);
                    return;
                case "b":
                    if (users.get(target).getFollowers().size() != 1) {
                        text.printer(username + "s Followers:", ConsoleColors.CYAN_BOLD);

                        for (int j = 1; j < users.get(target).getFollowers().size(); j++)
                            if (!(users.get(target).getBlocks().contains(you)))
                                for (int i = 0; i < users.size(); i++)
                                    if (users.get(i).getUsername().equals(users.get(target).getFollowers().get(j))
                                            && users.get(i).getIsEnable()){
                                        text.printer(counter + "- " + users.get(target).getFollowers().get(j),
                                                ConsoleColors.BLACK_BOLD_BRIGHT);
                            mapper[counter - 1] = j;
                            counter++;
                        }
                    }
                    if (counter == 1) {
                        text.printer("Nobody follows " + username + "!", ConsoleColors.RED_BOLD_BRIGHT);
                    } else {
                        text.printer("\nInsert users number:", ConsoleColors.PURPLE_BOLD);
                        res = text.getText();
                        try {
                            int resnum = Integer.parseInt(res);
                            if (resnum < counter) {
                                new userProfile(you, users.get(target).getFollowers().get(mapper[resnum - 1]));
                                return;
                            } else {
                                text.printer("Please insert valid number!", ConsoleColors.RED_BOLD_BRIGHT);
                                text.getText();
                                new Lists(username, you);
                                return;
                            }
                        } catch (NumberFormatException exception) {
                            new Lists(username, you);
                            return;
                        }
                    }
                    new Lists(username, you);
                    return;
                case "c":
                    if (users.get(target).getBlocks().size() != 1) {
                        text.printer(username + "s Block list:", ConsoleColors.CYAN_BOLD);
                        for (int j = 1; j < users.get(target).getBlocks().size(); j++)
                            if (!(users.get(target).getBlocks().contains(you)))
                                for (int i = 0; i < users.size(); i++)
                                    if (users.get(i).getUsername().equals(users.get(target).getBlocks().get(j))
                                            && users.get(i).getIsEnable()){
                                        text.printer(j + "- " + users.get(target).getBlocks().get(j),
                                                ConsoleColors.BLACK_BOLD_BRIGHT);
                            mapper[counter - 1] = j;
                            counter++;
                        }
                    }
            if (counter == 1) {
                text.printer(username + " dont block anybody!", ConsoleColors.RED_BOLD_BRIGHT);
            } else {
                text.printer("\nInsert users number:", ConsoleColors.PURPLE_BOLD);
                res = text.getText();
                try {
                    int resnum = Integer.parseInt(res);
                    if (resnum < counter) {
                        new userProfile(you, users.get(target).getBlocks().get(mapper[resnum - 1]));
                        return;
                    } else {
                        text.printer("Please insert valid number!", ConsoleColors.RED_BOLD_BRIGHT);
                        text.getText();
                        new Lists(username, you);
                        return;
                    }
                } catch (NumberFormatException exception) {
                    new Lists(username, you);
                    return;
                }
            }
                    new Lists(username, you);
                    return;
                    case "d":
                        if (users.get(target).getMutes().size()!=1) {
                            text.printer(username + "s Mute list:", ConsoleColors.CYAN_BOLD);
                            for (int j = 1; j < users.get(target).getMutes().size(); j++)
                                if (!(users.get(target).getBlocks().contains(you)))
                                    for (int i = 0; i < users.size(); i++)
                                        if (users.get(i).getUsername().equals(users.get(target).getMutes().get(j))
                                                && users.get(i).getIsEnable()){
                                            text.printer(j + "- " + users.get(target).getMutes().get(j),
                                                    ConsoleColors.BLACK_BOLD_BRIGHT);
                                mapper[counter - 1] = j;
                                counter++;
                            }
                        }
                        if(counter==1){
                            text.printer(username+" dont mute anybody!", ConsoleColors.RED_BOLD_BRIGHT);
                        }else {
                            text.printer("\nInsert users number:", ConsoleColors.PURPLE_BOLD);
                            res = text.getText();
                            try {
                                int resnum = Integer.parseInt(res);
                                if (resnum < counter) {
                                    new userProfile(you, users.get(target).getMutes().get(mapper[resnum - 1]));
                                    return;
                                } else {
                                    text.printer("Please insert valid number!", ConsoleColors.RED_BOLD_BRIGHT);
                                    text.getText();
                                    new Lists(username, you);
                                    return;
                                }
                            } catch (NumberFormatException exception) {
                                new Lists(username, you);
                                return;
                            }
                        }
                        new Lists(username, you);
                        return;
                    default:
                        text.printer("Please insert valid Command!", ConsoleColors.RED_BOLD_BRIGHT);
                        text.getText();
                        new Lists(username,you);
                        break;

                }
               new Lists(username,you);
            }
        }
    }


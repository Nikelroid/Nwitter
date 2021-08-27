import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class userProfile {
    int fll=0,bll=0,mll=0,frr=0;
    int target=0;
    submitRequest subreq = new submitRequest();
    jsonNotifs Not =new jsonNotifs();
    List<objNotifs> notifs= Not.get();
    boolean req=false;
    submitAction submit = new submitAction();
    File n = new File("Notifs.json");
    private static final Logger logger = LogManager.getLogger(userProfile.class);
    public userProfile(String username, String anouther_username) throws IOException {
        logger.info("System: user went to userProfile");
        if (n.exists()) {
            for (int i = 0; i < notifs.size(); i++) {
                if (notifs.get(i).getUser1().equals(username) &&
                        notifs.get(i).getUser2().equals(anouther_username) &&
                        notifs.get(i).getType()==8){
                    req = true;
                    break;
                }
            }
        }
        fll=0;
        bll=0;
        mll=0;
        frr=0;
        var get_j = new jsonUsers();
        var text = new Text();
        List<objUsers> users = get_j.get();
        text.printer("");
        if (!username.equals(anouther_username)) {
            for (int i = 0; i < users.size(); i++)
                if (users.get(i).getUsername().equals(anouther_username))
                    target = i;

        for (int i = 0; i < users.size(); i++)
            if (users.get(i).getUsername().equals(anouther_username)) {

                text.printer("Name: " + users.get(i).getName(), ConsoleColors.BLACK_BOLD_BRIGHT);
                text.printer("Username: " + users.get(i).getUsername(), ConsoleColors.BLACK_BOLD_BRIGHT);
                if (users.get(i).getPrivacy().get(1)==1 ||
                        (users.get(i).getPrivacy().get(1)==2 &&
                                users.get(i).getFollowings().contains(username)))
                text.printer("Birthday date: " + users.get(i).getBirthday(), ConsoleColors.BLACK_BOLD_BRIGHT);

                if (users.get(i).getPrivacy().get(2)==1 ||
                        (users.get(i).getPrivacy().get(2)==2 &&
                                users.get(i).getFollowings().contains(username)))
                text.printer("Email address: " + users.get(i).getEmail(), ConsoleColors.BLACK_BOLD_BRIGHT);

                if (users.get(i).getPrivacy().get(3)==1 ||
                        (users.get(i).getPrivacy().get(3)==2 &&
                                users.get(i).getFollowings().contains(username)))
                    text.printer("Phone number: " + users.get(i).getPhonenumber(), ConsoleColors.BLACK_BOLD_BRIGHT);

                text.printer("Bio: " + users.get(i).getBio(), ConsoleColors.BLACK_BOLD_BRIGHT);
                if (users.get(i).getPrivacy().get(0)==1 ||
                        (users.get(i).getPrivacy().get(0)==2 &&
                                users.get(i).getFollowings().contains(username))) {
                    if (users.get(i).getLastseen().equals("Online"))
                        text.printer("Online", ConsoleColors.GREEN_BOLD);
                    else
                        text.printer("Lastseen " + users.get(i).getLastseen(), ConsoleColors.BLACK_BOLD_BRIGHT);
                }else
                    text.printer("Last seen recently" , ConsoleColors.BLACK_BOLD_BRIGHT);

                System.out.print(ConsoleColors.BLACK_BOLD_BRIGHT+"Account Privacy: ");
                if (users.get(i).getAccount()) text.printer("Public", ConsoleColors.GREEN_BOLD);
                else text.printer("Private", ConsoleColors.RED_BOLD_BRIGHT);

                new nextPage(1);

            }

            for(int i = 0; i < users.size(); i++)
                if (users.get(i).getUsername().equals(anouther_username)) {
                        if (users.get(i).getFollowings().contains(username)) {
                            text.printer(anouther_username+" Follows you", ConsoleColors.BLUE_BOLD);
                            break;
                        }
                        if (users.get(i).getBlocks().contains(username)) {
                            text.printer(anouther_username+" Blockes you", ConsoleColors.BLUE_BOLD);
                            break;
                        }
                    if (users.get(i).getMutes().contains(username)) {
                            text.printer(anouther_username+" Mutes you", ConsoleColors.BLUE_BOLD);
                            break;
                        }

                }
            System.out.println();



            Menu_command(username,anouther_username);
            String response = text.getText();
            System.out.println();
            switch (response) {
                case "a":
                    for(int i = 0; i < users.size(); i++)
                        if (users.get(i).getUsername().equals(anouther_username))
                            if (!(users.get(i).getBlocks().contains(username))) {
                                if (fll == 0) {

                                    if (users.get(target).getAccount() ||
                                            users.get(target).getFollowers().contains(username)) {
                                        submit.Submit_unblock(username, anouther_username, true);
                                        submit.Submit_follow(username, anouther_username);
                                        logger.info("System: "+anouther_username + " followed!");
                                        text.printer(anouther_username + " followed!", ConsoleColors.GREEN_BOLD);
                                    } else {
                                        if (req) {
                                            subreq.submitUnreq(username,anouther_username);
                                            text.printer("Request to " + anouther_username + " deleted !", ConsoleColors.RED_BOLD_BRIGHT);
                                            logger.info("System: Request deleted");
                                        } else {
                                            submit.Submit_unblock(username, anouther_username, true);
                                            subreq.submitReq(username,anouther_username);
                                            logger.info("System: Request sent");
                                            text.printer("Request sent to " + anouther_username + "!", ConsoleColors.GREEN_BOLD);
                                        }
                                    }
                                } else {
                                    submit.Submit_unfollow(username, anouther_username,true);
                                    logger.info("System: "+anouther_username+" unfollowed!");
                                    text.printer(anouther_username+" unfollowed!", ConsoleColors.GREEN_BOLD);
                                }
                            }else{
                                for (int j = 0; j < users.size(); j++)
                                    if (users.get(j).getUsername().equals(anouther_username))
                                        text.printer("You have blocked already, you cant follow "
                                                +users.get(j).getName()+"!", ConsoleColors.RED_BOLD_BRIGHT);

                            }
                    text.getText();
                    new userProfile(username,anouther_username);
                    return;

                case "b":
                    if (bll == 0) {
                        submit.Submit_unfollow(username, anouther_username,false);
                        submit.Submit_unfollow(anouther_username, username,false);
                        submit.Submit_block(username, anouther_username);
                        text.printer(anouther_username+" blocked!", ConsoleColors.GREEN_BOLD);
                    } else {
                        submit.Submit_unblock(username, anouther_username,true);
                        text.printer(anouther_username+" unblocked!", ConsoleColors.GREEN_BOLD);
                    }
                    text.getText();
                    new userProfile(username,anouther_username);
                    return;
                case "c":
                    if (mll == 0) {
                        submit.Submit_mute(username, anouther_username);
                        logger.info("System: "+anouther_username+" muted!");
                        text.printer(anouther_username+" muted!", ConsoleColors.GREEN_BOLD);
                    } else {
                        submit.Submit_unmute(username, anouther_username);
                        logger.info("System: "+anouther_username+" unmuted!");
                        text.printer(anouther_username+" unmuted!", ConsoleColors.GREEN_BOLD);
                    }
                    text.getText();
                    new userProfile(username,anouther_username);
                    return;
                case "d":
                    new Feed(username);
                    return;
                case "p":
                    if (fll!=0) {
                        new chatPage(username, anouther_username);
                        return;
                    }
                case "e":
                    new Expelorer(username);
                    return;
                case "f":
                    if (frr != 0) {
                        submit.Submit_delete(username, anouther_username);
                        logger.info("System: "+anouther_username+" deleted!");
                        text.printer(anouther_username+" deleted!", ConsoleColors.GREEN_BOLD);
                    }
                    text.getText();
                    new userProfile(username,anouther_username);
                    return;
                case "t":
                    if (users.get(target).getAccount()||
                            users.get(target).getFollowers().contains(username)) {
                        new userTwittes(anouther_username, username);
                        return;
                    }
                    break;
                case "l":
                    if (users.get(target).getAccount()||
                            users.get(target).getFollowers().contains(username)) {
                        new Lists(anouther_username,username);
                        return;
                    }
                    break;
            }
            text.printer("Please insert a valid Command!", ConsoleColors.RED_BOLD_BRIGHT);
            text.getText();
            new userProfile(username,anouther_username);
            return;
        }else{
            new Info(username);
            return;
        }

    }
    public void Menu_command(String username,String anouther_username) {

        var text = new Text();

        var get_j =new jsonUsers();

        text.printer("Actions:",ConsoleColors.CYAN_BOLD);
        List<objUsers> users= get_j.get();
        for(int i = 0; i < users.size(); i++)
            if (users.get(i).getUsername().equals(username)) {
                for (int j = 0; j < users.get(i).getFollowings().size(); j++)
                    if (users.get(i).getFollowings().get(j).equals(anouther_username)) {
                        fll++;
                        break;
                    }
            }

        if(fll==0)
            if (users.get(target).getAccount()||users.get(target).getFollowers().contains(username)){
                text.printer("a-Follow",ConsoleColors.BLACK_BOLD_BRIGHT);
            }else{
                if (req)
                    text.printer("a-UnRequest",ConsoleColors.BLACK_BOLD_BRIGHT);
                else
                    text.printer("a-Request",ConsoleColors.BLACK_BOLD_BRIGHT);

            }else
            text.printer("a-Unfollow", ConsoleColors.BLACK_BOLD_BRIGHT);

        for(int i = 0; i < users.size(); i++)
            if (users.get(i).getUsername().equals(username)) {
                for (int j = 0; j < users.get(i).getBlocks().size(); j++)
                    if (users.get(i).getBlocks().get(j).equals(anouther_username)) {
                        bll++;
                        break;
                    }
            }

        if(bll==0)
            text.printer("b-Block",ConsoleColors.BLACK_BOLD_BRIGHT);
        else
            text.printer("b-Unblock", ConsoleColors.BLACK_BOLD_BRIGHT);


        for(int i = 0; i < users.size(); i++)
            if (users.get(i).getUsername().equals(username)) {
                for (int j = 0; j < users.get(i).getMutes().size(); j++)
                    if (users.get(i).getMutes().get(j).equals(anouther_username)) {
                        mll++;
                        break;
                    }
            }


        if(mll==0)
            text.printer("c-Mute",ConsoleColors.BLACK_BOLD_BRIGHT);
        else
            text.printer("c-Unmute",ConsoleColors.BLACK_BOLD_BRIGHT);
        if (users.get(target).getAccount()||users.get(target).getFollowers().contains(username)) {
            text.printer("t-" + anouther_username + "s Nwittes:", ConsoleColors.BLACK_BOLD_BRIGHT);
            text.printer("l-" + anouther_username + "s lists", ConsoleColors.BLACK_BOLD_BRIGHT);
        }
        for(int i = 0; i < users.size(); i++)
            if (users.get(i).getUsername().equals(username))
                if (users.get(i).getFollowers().contains(anouther_username)) {
                    frr++;
                    break;
                }
        if(fll!=0) text.printer("p-Send private message", ConsoleColors.BLACK_BOLD_BRIGHT);
        if(frr!=0)
            text.printer("f-Delete from followers", ConsoleColors.BLACK_BOLD_BRIGHT);
        text.printer("d-Go to Feed",ConsoleColors.BLACK_BOLD_BRIGHT);
        text.printer("e-Go to Explorer",ConsoleColors.BLACK_BOLD_BRIGHT);




        System.out.println();

    }
}

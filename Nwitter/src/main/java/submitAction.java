
import java.io.File;
import java.util.Collections;
import java.util.List;

public class submitAction {


    jsonNotifs Not =new jsonNotifs();
    List<objNotifs> notifs= Not.get();
    jsonUsers get_j =new jsonUsers();
    Text text = new Text();
    List<objUsers> users= get_j.get();
    File f = new File("Notifs.json");

    public submitAction() {
    }

    public void Submit_block(String blocker, String blocked) {


        for(int i = 0; i < users.size(); i++)
            if (users.get(i).getUsername().equals(blocker)) {
                users.get(i).getBlocks().add(blocked);
                users.set(i, users.get(i));
                break;
            }

        new jsonUsers(users);

        if (f.exists()) {
            notifs.add(new objNotifs(3,blocker,blocked));
        } else {
            notifs = Collections.singletonList(
                    new objNotifs(3,blocker,blocked));
        }
        new jsonNotifs(notifs);

    }

    public void Submit_delete(String deleter, String deleted) {

        for(int i = 0; i < users.size(); i++)
            if (users.get(i).getUsername().equals(deleter)) {
                users.get(i).getFollowers().remove(deleted);
                break;
            }

        for(int i = 0; i < users.size(); i++)
            if (users.get(i).getUsername().equals(deleted)) {
                users.get(i).getFollowings().remove(deleter);
                break;
            }

        new jsonUsers(users);

        if (f.exists()) {
            notifs.add(new objNotifs(7,deleter,deleted));
        } else {
            notifs = Collections.singletonList(
                    new objNotifs(7,deleter,deleted));
        }
        new jsonNotifs(notifs);
    }

    public void Submit_follow(String follower, String following) {

        for(int i = 0; i < users.size(); i++)
            if (users.get(i).getUsername().equals(follower)) {
                users.get(i).getFollowings().add(following);
                break;
            }

        for(int i = 0; i < users.size(); i++)
            if (users.get(i).getUsername().equals(following)) {
                users.get(i).getFollowers().add(follower);
                break;
            }

        new jsonUsers(users);


            if (f.exists()) {
                notifs.add(new objNotifs(1,follower,following));
            } else {
                notifs = Collections.singletonList(
                        new objNotifs(1,follower,following));
            }
            new jsonNotifs(notifs);

    }

    public void Submit_mute(String muter, String muted) {

        for(int i = 0; i < users.size(); i++)
            if (users.get(i).getUsername().equals(muter)) {
                users.get(i).getMutes().add(muted);
                break;
            }

        new jsonUsers(users);

        if (f.exists()) {
            notifs.add(new objNotifs(5,muter,muted));
        } else {
            notifs = Collections.singletonList(
                    new objNotifs(5,muter,muted));
        }
        new jsonNotifs(notifs);

    }

    public void Submit_unblock(String unblocker, String unblocked,boolean notf) {

        for(int i = 0; i < users.size(); i++)
            if (users.get(i).getUsername().equals(unblocker)) {
                users.get(i).getBlocks().remove(unblocked);
                break;
            }

        new jsonUsers(users);

            if (notf) {
                if (f.exists()) {
                    notifs.add(new objNotifs(4, unblocker, unblocked));
                } else {
                    notifs = Collections.singletonList(
                            new objNotifs(4, unblocker, unblocked));
                }
                new jsonNotifs(notifs);
            }
    }

    public void Submit_unfollow(String unfollower, String unfollowing,boolean notf) {

        for (int i = 0; i < users.size(); i++)
            if (users.get(i).getUsername().equals(unfollower)) {
                users.get(i).getFollowings().remove(unfollowing);
                break;
            }
        for (int i = 0; i < users.size(); i++)
            if (users.get(i).getUsername().equals(unfollowing)) {
                users.get(i).getFollowers().remove(unfollower);
                break;
            }

        new jsonUsers(users);
        if (notf) {

            if (f.exists()) {
                notifs.add(new objNotifs(2, unfollower, unfollowing));
            } else {
                notifs = Collections.singletonList(
                        new objNotifs(2, unfollower, unfollowing));
            }
            new jsonNotifs(notifs);
        }

    }

    public void Submit_unmute(String unmuter, String unmuted) {

        for(int i = 0; i < users.size(); i++)
            if (users.get(i).getUsername().equals(unmuter)) {
                users.get(i).getMutes().remove(unmuted);
                break;
            }

        new jsonUsers(users);

        if (f.exists()) {
            notifs.add(new objNotifs(6,unmuter,unmuted));
        } else {
            notifs = Collections.singletonList(
                    new objNotifs(6,unmuter,unmuted));
        }
        new jsonNotifs(notifs);
    }

}

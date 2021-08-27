import java.io.File;
import java.util.List;

public class submitEdit {

    jsonReports Rep = new jsonReports();
    List<objReport> report = Rep.get();
    jsonUsers get_j =new jsonUsers();
    jsonNotifs get_n =new jsonNotifs();
    Text text = new Text();
    List<objUsers> users= get_j.get();
    List<objTwitte> twittes ;
    List<objNotifs> notifs= get_n.get();
    jsonTwittes Get = new jsonTwittes();
    File f = new File("Twittes.json");
    File n = new File("Notifs.json");
    File r = new File("Notifs.json");
    public submitEdit() {
    }

    public boolean editStatus(String username){
        boolean en=true;
        for(int i = 0; i < users.size(); i++)
            if (users.get(i).getUsername().equals(username)) {
                if(users.get(i).getIsEnable()){
                    users.get(i).setEnable(false);
                   en =false;
                }else users.get(i).setEnable(true);
            }
        new jsonUsers(users);
        return en;
    }


    public boolean editPrivacy(String username){
        boolean en=true;
        for(int i = 0; i < users.size(); i++)
            if (users.get(i).getUsername().equals(username)) {
                if(users.get(i).getAccount()){
                    users.get(i).setAccount(false);
                    en =false;
                }else users.get(i).setAccount(true);
            }
        new jsonUsers(users);
        return en;
    }

    public void editName(String username, String newname){
        for(int i = 0; i < users.size(); i++)
            if (users.get(i).getUsername().equals(username)) {
                users.get(i).setName(newname);
                break;
            }

        new jsonUsers(users);
    }
    public void editUsername(String username, String newuser){
        for(int i = 0; i < users.size(); i++)
            if (users.get(i).getUsername().equals(username)) {
                users.get(i).setUsername(newuser);
                break;
            }
         


        for(int i = 0; i < users.size(); i++){
            for (int j = 0; j < users.get(i).getFollowers().size(); j++)
            if (users.get(i).getFollowers().get(j).equals(username))
                users.get(i).getFollowers().set(j,newuser);
            for (int j = 0; j < users.get(i).getFollowings().size(); j++)
                if (users.get(i).getFollowings().get(j).equals(username))
                    users.get(i).getFollowings().set(j,newuser);
            for (int j = 0; j < users.get(i).getBlocks().size(); j++)
                if (users.get(i).getBlocks().get(j).equals(username))
                    users.get(i).getBlocks().set(j,newuser);
            for (int j = 0; j < users.get(i).getMutes().size(); j++)
                if (users.get(i).getMutes().get(j).equals(username))
                    users.get(i).getMutes().set(j,newuser);
        }
        new jsonUsers(users);


        if (f.exists()) {
            twittes = Get.get();
            for (int i = 0; i < twittes.size(); i++)
                if(twittes.get(i).getSender().equals(username))
                    twittes.get(i).setSender(newuser);

            for(int i = 0; i < twittes.size(); i++){
                for (int j = 0; j < twittes.get(i).getLikes().size(); j++)
                    if (twittes.get(i).getLikes().get(j).equals(username))
                        twittes.get(i).getLikes().set(j,newuser);
                for (int j = 0; j < twittes.get(i).getRetwittes().size(); j++)
                    if (twittes.get(i).getRetwittes().get(j).equals(username))
                        twittes.get(i).getRetwittes().set(j,newuser);
            }

            new jsonTwittes(twittes);
        }


        if (n.exists()) {
            notifs = get_n.get();

            for(int i = 0; i < notifs.size(); i++){
                if (notifs.get(i).getUser1().equals(username))
                    notifs.get(i).setUser1(newuser);
                if (notifs.get(i).getUser2().equals(username))
                    notifs.get(i).setUser2(newuser);
            }

            new jsonNotifs(notifs);
        }

        if (r.exists()) {
            for (int i = 0; i < report.size(); i++) {
                if (report.get(i).getReporter().equals(username))
                    report.get(i).setReporter(newuser);
                if (report.get(i).getSender().equals(username))
                    report.get(i).setReporter(newuser);
            }
            new jsonReports(report);
        }
    }


    public void editPassword(String username, String newupass){
        for(int i = 0; i < users.size(); i++)

            if (users.get(i).getUsername().equals(username)) {
                users.get(i).setPassword(newupass);
                break;
            }

        new jsonUsers(users);
    }
    public void editBithdaydate(String username, String newbirthday){
        for(int i = 0; i < users.size(); i++)
            if (users.get(i).getUsername().equals(username)) {
                users.get(i).setBirthday(newbirthday);
                break;
            }

        new jsonUsers(users);
    }
    public void editEmail(String username, String newemail){
        for(int i = 0; i < users.size(); i++)
            if (users.get(i).getUsername().equals(username)) {
                users.get(i).setEmail(newemail);
                break;
            }

        new jsonUsers(users);
    }
    public void editPhonenumber(String username, String newnumber){
        for(int i = 0; i < users.size(); i++)
            if (users.get(i).getUsername().equals(username)) {
                users.get(i).setPhonenumber(newnumber);
                break;
            }

        new jsonUsers(users);
    }
    public void editBio(String username, String newbio){
        for(int i = 0; i < users.size(); i++)
            if (users.get(i).getUsername().equals(username)) {
                users.get(i).setBio(newbio);
                break;
            }

        new jsonUsers(users);

    }

}


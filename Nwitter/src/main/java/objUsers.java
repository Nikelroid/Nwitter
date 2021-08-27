
import java.util.List;

public class objUsers {

    private String name ;
    private String username;
    private String password;
    private String birthday;
    private String email;
    private String phonenumber;
    private String bio;
    private int ID;
    private boolean enable;
    private String lastseen;
    private List<String> followings;
    private List<String> followers;
    private List<String> blocks;
    private List<String> mutes;
    private List<Integer> privacy;
    private boolean account;
    private List<Integer> twittesaved;
    private List<Integer> pmsaved;
    private List<List<String>> categoiries;
    private objUsers() {
    }


    public void setCategoiries(List<List<String>> categoiries) {
        this.categoiries = categoiries;
    }

    public List<List<String>> getCategoiries() {
        return categoiries;
    }

    public void setTwittesaved(List<Integer> twittesaved) {
        this.twittesaved = twittesaved;
    }

    public void setPmsaved(List<Integer> pmsaved) {
        this.pmsaved = pmsaved;
    }

    public List<Integer> getTwittesaved() {
        return twittesaved;
    }

    public List<Integer> getPmsaved() {
        return pmsaved;
    }

    public boolean getAccount() {
        return account;
    }

    public List<String> getFollowings() {
        return followings;
    }

    public List<String> getFollowers() {
        return followers;
    }

    public List<String> getBlocks() {
        return blocks;
    }

    public List<String> getMutes() {
        return mutes;
    }

    public String getName() {
        return name;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getBirthday() {
        return birthday;
    }

    public String getEmail() {
        return email;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public String getBio() {
        return bio;
    }

    public int getID() {
        return ID;
    }

    public boolean getIsEnable() {
        return enable;
    }

    public String getLastseen() {
        return lastseen;
    }


    public List<Integer> getPrivacy() {
        return privacy;
    }

    public objUsers(String name, String username,
                    String password, String birthday,
                    String email, String phonenumber,
                    String bio, int ID, boolean enable,
                    String lastseen, List<String>followings,
                    List<String>followers,
                    List<String>blocks,
                    List<String>mutes,
                    List<Integer>privacy,
                    boolean privet,
                    List<Integer> twittesaved,
                    List<Integer> pmsaved ,List<List<String>> categoiries) {

        this.name = name;
        this.username = username;
        this.password = password;
        this.birthday = birthday;
        this.email = email;
        this.phonenumber = phonenumber;
        this.bio = bio;
        this.ID = ID;
        this.enable = enable;
        this.lastseen = lastseen;
        this.followings = followings;
        this.followers = followers;
        this.blocks = blocks;
        this.mutes = mutes;
        this.privacy = privacy;
        this.account =privet;
        this.twittesaved = twittesaved;
        this.pmsaved= pmsaved;
        this.categoiries=categoiries;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    public void setLastseen(String lastseen) {
        this.lastseen = lastseen;
    }

    public void setFollowings(List<String> followings) {
        this.followings = followings;
    }

    public void setFollowers(List<String> followers) {
        this.followers = followers;
    }

    public void setBlocks(List<String> blocks) {
        this.blocks = blocks;
    }

    public void setMutes(List<String> mutes) {
        this.mutes = mutes;
    }

    public void setPrivacy(List<Integer> privacy) {
        this.privacy = privacy;
    }

    public void setAccount(boolean account) {
        this.account = account;
    }
}

import java.util.List;
public class objNotifs {
    private int type ;
    private String user1;
    private String user2;
    public objNotifs(int type,String user1,String user2) {
        this.type = type;
        this.user1 = user1;
        this.user2 = user2;
    }
    public int getType() {
        return type;
    }
    public String getUser1() {
        return user1;
    }
    public String getUser2() {
        return user2;
    }
    public void setType(int type) {
        this.type = type;
    }
    public void setUser1(String user1) {
        this.user1 = user1;
    }
    public void setUser2(String user2) {
        this.user2 = user2;
    }

}

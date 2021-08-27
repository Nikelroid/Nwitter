import java.util.List;

public class objTwitte {

    public String text;
    public String sender;
    public List<String> likes;
    public List<Integer> comments;
    public List<String> retwittes;
    public int serial;
    public String time;

    public String getTime() {
        return time;
    }

    public String getText() {
        return text;
    }

    public String getSender() {
        return sender;
    }

    public List<String> getLikes() {
        return likes;
    }

    public List<Integer> getComments() {
        return comments;
    }

    public List<String> getRetwittes() {
        return retwittes;
    }

    public int getSerial() {
        return serial;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public void setLikes(List<String> likes) {
        this.likes = likes;
    }

    public void setComments(List<Integer> comments) {
        this.comments = comments;
    }

    public void setRetwittes(List<String> retwittes) {
        this.retwittes = retwittes;
    }

    public void setSerial(int serial) {
        this.serial = serial;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public objTwitte(String text, String sender,
                     List<String> likes, List<Integer> comments,
                     List<String> retwittes, int serial , String time) {

        this.text = text;
        this.sender = sender;
        this.likes = likes;
        this.comments = comments;
        this.retwittes = retwittes;
        this.serial=serial;
        this.time=time;
    }
}

public class objMessage {

    public String text;
    public String sender;
    public String receiver;
    public boolean seen;
    public int serial;
    public String time;

    public String getReceiver() {
        return receiver;
    }

    public boolean isSeen() {
        return seen;
    }

    public String getTime() {
        return time;
    }

    public String getText() {
        return text;
    }

    public String getSender() {
        return sender;
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

    public void setSerial(int serial) {
        this.serial = serial;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public void setSeen(boolean seen) {
        this.seen = seen;
    }

    public objMessage(String text, String sender,
                     String reciver, boolean seen,
                     int serial, String time) {

        this.text = text;
        this.sender = sender;
        this.receiver =reciver;
        this.seen=seen;
        this.serial=serial;
        this.time=time;

    }
}

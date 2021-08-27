public class objReport {

    private int serial;
    private String reporter;
    private String twitteText;
    private String sender;
    private String Reporttext;

    public objReport(int serial,String reporter, String twitteText, String sender, String Reporttext) {
        this.serial=serial;
        this.reporter = reporter;
        this.twitteText = twitteText;
        this.sender = sender;
        this.Reporttext = Reporttext;
    }

    public int getSerial() {
        return serial;
    }

    public String getReporter() {
        return reporter;
    }

    public String getTwitteText() {
        return twitteText;
    }

    public String getSender() {
        return sender;
    }

    public String getReporttext() {
        return Reporttext;
    }


    public void setSerial(int serial) {
        this.serial = serial;
    }

    public void setReporter(String reporter) {
        this.reporter = reporter;
    }

    public void setTwitteText(String twitteText) {
        this.twitteText = twitteText;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public void setReporttext(String Reporttext) {
        this.Reporttext = Reporttext;
    }



}

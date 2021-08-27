import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class submitMessage {
    private int serial;
    List<objMessage> messages;
    jsonMessage Get = new jsonMessage();
    File f = new File("Message.json");
    File id = new File("serialMess.txt");
    Gson twitteObj = new GsonBuilder().setPrettyPrinting().create();
    public submitMessage() {
    }
    private static final Logger logger = LogManager.getLogger(submitMessage.class);
    public int SubMess(String text, String sender, String reciver){
        logger.info("System: user went to submitMessage");
        try {
            if (id.createNewFile()) {
                serial=1000000;
                FileWriter myWriter = new FileWriter("serialMess.txt");
                myWriter.write(serial+"");
                myWriter.close();

            } else {

                Scanner myReader = new Scanner(id);
                String data = myReader.nextLine();
                serial = Integer.parseInt(data);
                serial++;
                FileWriter myWriter = new FileWriter("serialMess.txt");
                myWriter.write(serial+"");
                myWriter.close();
            }
        } catch (IOException ignored) {
        }

        messages = Get.get();
        adder(text,sender,reciver);
        return serial;
    }


    private void adder(String text, String sender,String receiver) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();

        if (f.exists()) {
            messages.add(
                    new objMessage(text, sender, receiver,
                            false,
                            serial,dtf.format(now)));


        } else {
            messages = Collections.singletonList(
                    new objMessage(text, sender, receiver, false, serial,dtf.format(now)));
        }
        new jsonMessage(messages);
    }

}

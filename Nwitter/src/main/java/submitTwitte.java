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

public class submitTwitte {
    private int serial;
    List<objTwitte> twittes;
    jsonTwittes Get = new jsonTwittes();
    File f = new File("Twittes.json");
    File id = new File("serial.txt");
    Gson twitteObj = new GsonBuilder().setPrettyPrinting().create();
    public submitTwitte() {
    }
    private static final Logger logger = LogManager.getLogger(submitTwitte.class);
        public int Sub_twitte(String text, String sender){
            logger.info("System: user went to submitTwitte");
            try {
            if (id.createNewFile()) {
                serial=1000000;
                FileWriter myWriter = new FileWriter("serial.txt");
                myWriter.write(serial+"");
                myWriter.close();

            } else {

                Scanner myReader = new Scanner(id);
                String data = myReader.nextLine();
                serial = Integer.parseInt(data);
                serial++;
                FileWriter myWriter = new FileWriter("serial.txt");
                myWriter.write(serial+"");
                myWriter.close();
            }
        } catch (IOException ignored) {
    }

            twittes = Get.get();
            adder(text,sender);
            return serial;
        }


    private void adder(String text, String sender) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();

            if (f.exists()) {
                twittes.add(
                        new objTwitte(text, sender,  Collections.singletonList("Likes:"),
                                Collections.singletonList(0),
                                Collections.singletonList("Retwittes:"),
                                serial,dtf.format(now)));


            } else {
                twittes = Collections.singletonList(
                        new objTwitte(text, sender, Collections.singletonList("Likes:"),
                                Collections.singletonList(0),
                                Collections.singletonList("Retwittes:"),
                                serial,dtf.format(now)));
            }
        new jsonTwittes(twittes);
    }

}

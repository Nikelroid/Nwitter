import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class submitRegister {
    int ID;
    List<Integer> privacy = new ArrayList<Integer>();
    List<Integer> tS = new ArrayList<Integer>();
    List<Integer> pS = new ArrayList<Integer>();
    jsonUsers Get = new jsonUsers();
    List<objUsers> users;
    File f = new File("Users.json");
    File id = new File("ID.txt");
    Gson registerObj = new GsonBuilder().setPrettyPrinting().create();
    public submitRegister() {

    }
    private static final Logger logger = LogManager.getLogger(submitRegister.class);
        public boolean Sub_regester(String[] reg_info){
            logger.info("System: user went to submitRegister");
        pS.add(0);
            tS.add(0);
            for (int i = 0; i < 4; i++)
            privacy.add(1);
            privacy.set(0,2);


            var Get = new jsonUsers();
        try {
            if (id.createNewFile()) {
                ID=100000;
                FileWriter myWriter = new FileWriter("ID.txt");
                myWriter.write(ID+"");
                myWriter.close();

            } else {

                Scanner myReader = new Scanner(id);
                String data = myReader.nextLine();
                ID = Integer.parseInt(data);
                ID++;
                FileWriter myWriter = new FileWriter("ID.txt");
                myWriter.write(ID+"");
                myWriter.close();
            }
        } catch (IOException e) {
            return false;
    }
           getter();
            adder(reg_info,ID);
            return true;
        }


    private void getter() {
        if (f.exists()) {
            users = Get.get();
        }
    }


    private void adder(String[] reg_info,int ID) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();

            if (f.exists()) {
                users.add(new objUsers(reg_info[0] + " " + reg_info[1],
                        reg_info[2],
                        reg_info[3],
                        reg_info[7] + "/" + reg_info[6] + "/" + reg_info[5],
                        reg_info[8],
                        reg_info[9],
                        reg_info[10],
                        ID, true, dtf.format(now),
                        Collections.singletonList("Followings:"),
                        Collections.singletonList("Followers:")
                        ,Collections.singletonList("Blocks:")
                        ,Collections.singletonList("Mutes:"),
                        privacy,true,
                        Collections.singletonList(0),
                        Collections.singletonList(0),
                        Collections.singletonList(Collections.singletonList("Categories:"))));
            }else{
                users = Collections.singletonList(new objUsers(reg_info[0] + " " + reg_info[1],
                        reg_info[2],
                        reg_info[3],
                        reg_info[7] + "/" + reg_info[6] + "/" + reg_info[5],
                        reg_info[8],
                        reg_info[9],
                        reg_info[10],
                        ID, true, dtf.format(now),
                        Collections.singletonList("Followings:"),
                        Collections.singletonList("Followers:")
                        ,Collections.singletonList("Blocks:")
                        ,Collections.singletonList("Mutes:"),
                        privacy,true,   Collections.singletonList(0),
                        Collections.singletonList(0),
                        Collections.singletonList(Collections.singletonList("Categories:"))));
            }
            new jsonUsers(users);
    }

}

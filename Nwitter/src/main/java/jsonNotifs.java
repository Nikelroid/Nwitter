import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

class jsonNotifs {
    public jsonNotifs() {
    }
    public jsonNotifs(List<objNotifs> users) {
        Gson userObj = new GsonBuilder().setPrettyPrinting().create();
        try {
            Writer writer = Files.newBufferedWriter(Paths.get("Notifs.json"));
            userObj.toJson(users, writer);
            writer.close();
        } catch (Exception ex) {

        }
    }
    List<objNotifs> notifs;
    public List<objNotifs> get() {
        try {
            Gson gson = new Gson();
            Reader reader = Files.newBufferedReader(Paths.get("Notifs.json"));
            notifs = new Gson().fromJson(
                    reader, new TypeToken<List<objNotifs>>() {
                    }.getType());
            reader.close();
        } catch (IOException ignored) {
        }
        return notifs;
    }
}

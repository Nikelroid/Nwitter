import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

class jsonTwittes {
    public jsonTwittes() {
    }
    List<objTwitte> twittes;
    public jsonTwittes(List<objTwitte> users) {
        Gson userObj = new GsonBuilder().setPrettyPrinting().create();
        try {
            Writer writer = Files.newBufferedWriter(Paths.get("Twittes.json"));
            userObj.toJson(users, writer);
            writer.close();
        } catch (Exception ex) {

        }

    }
    public List<objTwitte> get() {
        try {
            Gson gson = new Gson();
            Reader reader = Files.newBufferedReader(Paths.get("Twittes.json"));
            twittes = new Gson().fromJson(
                    reader, new TypeToken<List<objTwitte>>() {
                    }.getType());
            reader.close();
        } catch (IOException ignored) {
        }
        return twittes;
    }
}

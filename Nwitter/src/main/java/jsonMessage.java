import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

class jsonMessage {
    public jsonMessage() {
    }
    List<objMessage> messages;
    public jsonMessage(List<objMessage> users) {
        Gson messagesObj = new GsonBuilder().setPrettyPrinting().create();
        try {
            Writer writer = Files.newBufferedWriter(Paths.get("Message.json"));
            messagesObj.toJson(users, writer);
            writer.close();
        } catch (Exception ex) {

        }

    }
    public List<objMessage> get() {
        try {
            Gson gson = new Gson();
            Reader reader = Files.newBufferedReader(Paths.get("Message.json"));
            messages = new Gson().fromJson(
                    reader, new TypeToken<List<objMessage>>() {
                    }.getType());
            reader.close();
        } catch (IOException ignored) {
        }
        return messages;
    }
}

package parseJSON;

import com.google.gson.Gson;

import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;

public class BasicGson {
    public static void main(String[] args) throws Exception {
        try {
            Gson gson = new Gson();
            Reader reader = Files.newBufferedReader(Paths.get("Resorces/data.json"));

            // convert JSON file to map
            Map<?, ?> map = (Map<?, ?>) gson.fromJson(reader, Map.class);

            // print map entries
            for (Map.Entry<?, ?> entry : map.entrySet()) {
                if(entry.getKey().equals("email") || entry.getValue().equals("Sachin"))
                    System.out.println(entry.getKey() + "=" + entry.getValue());
            }

            // close reader
            reader.close();

        } catch (
                Exception ex) {
            ex.printStackTrace();
        }
    }
}

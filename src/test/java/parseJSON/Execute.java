package parseJSON;

import com.google.gson.Gson;

import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;

public class Execute {
    public static void main(String[] args) throws Exception {
//        byte[] data = readAllBytes(Paths.get("Resorces/data.json"));
//        ObjectMapper mapper = new ObjectMapper();
//        Person first = mapper.readValue(data, Person.class);
//        System.out.println(first.getName());
//        System.out.println(first.getEmail());
//        System.out.println(first.getAddress().getCity());
//        System.out.println(first.getAddress().getTown());
//        System.out.println(first.getAddress().getState().getTeh());
//        System.out.println(first.getAddress().getState().getDist());
//        System.out.println(first.getAddress().getState().getState());
        try {
            Gson gson = new Gson();
//        Person first = gson.fromJson("Resorces/data.json", Person.class);
            Reader reader = Files.newBufferedReader(Paths.get("Resorces/data.json"));

            // convert JSON file to map
            Map<?, ?> map = (Map<?, ?>) gson.fromJson(reader, Map.class);

            // print map entries
            for (Map.Entry<?, ?> entry : map.entrySet()) {
                if(entry.getKey().equals("email"))
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

//package parseJSON;
//
////import io.restassured.mapper.ObjectMapper;
//
//import io.restassured.mapper.ObjectMapper;
//
//import java.nio.file.Paths;
//
//import static java.nio.file.Files.*;
//
//public class Execute {
//    public static void main(String[] args) throws Exception{
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
//    }
//}

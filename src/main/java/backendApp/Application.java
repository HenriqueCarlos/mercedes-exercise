package backendApp;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
        /*System.out.println("teste");
        JSONParser parser = new JSONParser();


        try{
            Object obj = parser.parse(new FileReader("./src/main/resources/dataset.json"));
            JSONObject jsonObject = (JSONObject) obj;
            JSONArray dealers = (JSONArray) jsonObject.get("dealers");
            //System.out.println(dealers);
            for(Object dealer : dealers){
                JSONObject newDealer = (JSONObject) dealer;

                JSONArray cheicle = (JSONArray) newDealer.get("vehicles");
                for(Object oj : cheicle){
                    JSONObject qwe = (JSONObject) oj;
                    JSONObject obj2 = (JSONObject) qwe.get("availability");
                    for (Object key : obj2.keySet()){
                        String dayOfWeek = (String) key;
                        List<String> avail = (List<String>) obj2.get(dayOfWeek);
                        System.out.println("day:"+ dayOfWeek + "avail list:" + avail );
                    }

                }

            }


        } catch (ParseException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }*/
    }

}

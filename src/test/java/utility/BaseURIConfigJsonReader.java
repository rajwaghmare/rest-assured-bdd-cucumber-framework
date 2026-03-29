package utility;

import java.io.FileReader;
import java.io.InputStreamReader;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class BaseURIConfigJsonReader {

        static JSONObject jsonObject;

        static {
            try {
                JSONParser parser = new JSONParser();
                FileReader reader = new FileReader("src/test/resources/test_data/BaseURIConfig.json");
                jsonObject = (JSONObject) parser.parse(reader);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        public static String getBaseURI() {

            return (String) jsonObject.get("baseURI");
        }

    public static String getCreateUserEndpoint() {

        return (String) jsonObject.get("addUserEndpoint");

         }

    public static String getUpdateUserEndpoint() {

        return (String) jsonObject.get("updateUserEndpoint");

    }

    public static String getUserEndpoint() {

        return (String) jsonObject.get("getUserEndpoint");

    }
}



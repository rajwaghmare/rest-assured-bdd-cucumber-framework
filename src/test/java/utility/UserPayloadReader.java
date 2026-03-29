package utility;
import java.nio.file.Files;
import java.nio.file.Paths;

public class UserPayloadReader {


    public static String getCreateUserPayload() {

        try {

            return new String(Files.readAllBytes(
                    Paths.get("src/test/resources/payloads/UserConfig.json")));

        } catch (Exception e) {

            e.printStackTrace();
        }

        return null;
    }

    public static String getUpdateUserPayload() {

        try {

            return new String(Files.readAllBytes(
                    Paths.get("src/test/resources/payloads/UserConfig.json")));

        } catch (Exception e) {

            e.printStackTrace();
        }

        return null;
    }


}

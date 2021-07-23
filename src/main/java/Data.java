import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.io.FileReader;
import java.io.IOException;

public class Data {
    public static JSONParser jsonParser = new JSONParser();
    public static Object obj;

    static {
        try {
            obj = jsonParser.parse(new FileReader("src/main/resources/testData.json"));
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }

    public static JSONObject jsonObject = (JSONObject) obj;
    protected static String passwrd = (String) jsonObject.get("passwrd");
    protected static String name = (String) jsonObject.get("name");
    protected static String lastName = (String) jsonObject.get("lastName");
    protected static String newPasswrd = (String) jsonObject.get("newPasswrd");
    protected static String address = (String) jsonObject.get("address");
    protected static String city = (String) jsonObject.get("city");
    protected static String zipCode = (String) jsonObject.get("zipCode");
    protected static String phoneNumber = (String) jsonObject.get("phoneNumber");
    protected static String id_state = (String) jsonObject.get("id_state");
    protected static String date = (String) jsonObject.get("date");
    protected static String month = (String) jsonObject.get("month");
    protected static String year = (String) jsonObject.get("year");
    protected static String country = (String) jsonObject.get("country");

}

package testData;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;

public class DataReader {
    public static String passwrd;
    public static String name;
    public static String lastName;
    public static String newPasswrd;
    public static String address;
    public static String city;
    public static String zipCode;
    public static String phoneNumber;
    public static String id_state;
    public static String date;
    public static String month;
    public static String year;
    public static String country;

    public DataReader(String path) throws IOException, ParseException {
        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObject = (JSONObject) jsonParser.parse(new FileReader(path));
        passwrd = (String) jsonObject.get("passwrd");
        name = (String) jsonObject.get("name");
        lastName = (String) jsonObject.get("lastName");
        newPasswrd = (String) jsonObject.get("newPasswrd");
        address = (String) jsonObject.get("address");
        city = (String) jsonObject.get("city");
        zipCode = (String) jsonObject.get("zipCode");
        phoneNumber = (String) jsonObject.get("phoneNumber");
        id_state = (String) jsonObject.get("id_state");
        date = (String) jsonObject.get("date");
        month = (String) jsonObject.get("month");
        year = (String) jsonObject.get("year");
        country = (String) jsonObject.get("country");
    }
}

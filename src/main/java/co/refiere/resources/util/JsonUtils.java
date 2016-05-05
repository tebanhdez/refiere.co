package co.refiere.resources.util;

import java.io.StringReader;

import javax.json.Json;
import javax.json.JsonObject;

public class JsonUtils {

    public static JsonObject parseStringToJson(String input){
        return Json.createReader(new StringReader(input)).readObject();
    }
}

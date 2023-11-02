import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class JsonBuilder {
    public JsonBuilder() {}

    public String build(Response response,String model) {
        Gson gson = new Gson();
        JsonObject payload = new JsonObject();

        payload.addProperty("model", model);
        JsonObject[] messages = new JsonObject[1];

        JsonObject userMessage = new JsonObject();
        userMessage.addProperty("role", response.getRole());
        userMessage.addProperty("content", response.getContent());
        messages[0] = userMessage;

        payload.add("messages", gson.toJsonTree(messages));
        return payload.toString();
    }

    public String build(String name,String summary) {
        Gson gson = new Gson();

        JsonObject payload = new JsonObject();
        payload.addProperty("billName",name);
        payload.addProperty("summary",summary);
        return gson.toJson(payload);
    }

    public String getConversion(String conversion) {
        JsonObject jsonObject = JsonParser.parseString(conversion).getAsJsonObject();
        String x = jsonObject.getAsJsonArray("choices").get(0).getAsJsonObject().getAsJsonObject("message").get("content").getAsString();
        return x;
    }
}

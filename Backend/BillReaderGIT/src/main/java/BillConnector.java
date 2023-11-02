import java.net.HttpURLConnection;
import java.io.*;
import java.net.URL;

public class BillConnector {
    public String connect(String json) throws IOException {
        URL url = new URL("INSERT BILL DATABASE URL");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setRequestMethod("POST");
        connection.setDoOutput(true);

        try (OutputStream os = connection.getOutputStream()) {
            byte[] input = json.getBytes("utf-8");
            os.write(input, 0, input.length);
        }

        int code = connection.getResponseCode();
        StringBuffer response = new StringBuffer();
        System.out.println(code);
        if (code == HttpURLConnection.HTTP_OK) {
            InputStream inputStream = connection.getInputStream();
            BufferedReader in = new BufferedReader(new InputStreamReader(inputStream));
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            connection.disconnect();

        }
        return response.toString();
    }
}

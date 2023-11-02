
import javax.net.ssl.HttpsURLConnection;
import java.io.*;
import java.net.URL;
import java.util.Arrays;

public class AIConnector {
    private final String STARTER_PROMPT = "Summarize this bill as a paragraph with bullet points. The format should be " +
            "Purpose first, then key provisions,and then money expenditures. Give details about the purpose of the bill, what it " +
            "does, money expenditures of the bill if there are any. Also make sure to include specific " +
            "details in the summary. Include details that don't necessarily fall into the categories.\n" +
            "The bill: \n";
    private final int MAX_LINES = 400;
    private final String SMALL_BILLS_MODEL = "gpt-3.5-turbo";
    private final String BIG_BILLS_MODEL = "gpt-3.5-turbo-16k";

    public void addToDatabase(String[][] bills) throws IOException {
        DBConnector dbc = new DBConnector();
        JsonBuilder jb = new JsonBuilder();

        for (int i = 338 ; i < bills.length; i++) {
            if (bills[i][1] != null) {
                System.out.println("On Bill: " + i);
                String chatGPTResponse = responseGenerator(bills[i][1]);
                dbc.connect(jb.build(bills[i][0],chatGPTResponse));
            } else {
                System.out.println("On Bill: " + i);
                dbc.connect(jb.build(bills[i][0],"null"));
            }
            System.out.println("Finished Bill: " + i);
        }
    }

    private String responseGenerator(String bill) throws IOException {
        JsonBuilder jb = new JsonBuilder();
        String chatGPTResponse = "";
        String[] billArray = bill.split("\n");

        if (billArray.length <= MAX_LINES) {
            String postMessage = STARTER_PROMPT + bill;
            Response response = new Response("user",postMessage);
            System.out.println("USING SMALL");
            chatGPTResponse = connect(jb.build(response,SMALL_BILLS_MODEL));
            chatGPTResponse = jb.getConversion(chatGPTResponse);

        } else {
            System.out.println("BILL LENGTH: " + billArray.length);

            String[] brokenBill = billBreaker(billArray);
            for (String piece : brokenBill) {
                String postMessage = STARTER_PROMPT + piece;
                Response response = new Response("user",postMessage);
                System.out.println("USING BIG");
                String intermediaryString = connect(jb.build(response,BIG_BILLS_MODEL));
                chatGPTResponse += "\n" + jb.getConversion(intermediaryString);
            }
        }

        return chatGPTResponse;
    }


    private String connect(String json) throws IOException {
        URL url = new URL("https://api.openai.com/v1/chat/completions");
        HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setRequestMethod("POST");
        ApiKey apiKey = new ApiKey();
        String API_KEY = apiKey.getAPI_KEY();
        connection.setRequestProperty("Authorization", "Bearer " + API_KEY);
        connection.setDoOutput(true);

        try (OutputStream os = connection.getOutputStream()) {
            byte[] input = json.getBytes("utf-8");
            os.write(input, 0, input.length);
        }

        int code = connection.getResponseCode();
        StringBuffer response = new StringBuffer();
        if (code == HttpsURLConnection.HTTP_OK) {
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


    private String joinElements(String[] stringArray) {
        StringBuilder sb = new StringBuilder();
        for (String element : stringArray) {
            element = "\n" + element;
            sb.append(element);
        }
        return sb.toString();
    }

    private String[] billBreaker(String[] billArray) {
        double arrayLength = billArray.length;
        String[] brokenArray = new String[(int) Math.ceil(arrayLength / MAX_LINES)];
        int startingIndex = 0;

        for (int i = 0; i < (int) Math.ceil(arrayLength / MAX_LINES) - 1; i++) {
            brokenArray[i] = joinElements(Arrays.copyOfRange(billArray,startingIndex,startingIndex+MAX_LINES));
            startingIndex += MAX_LINES;
        }
        brokenArray[(int) Math.ceil(arrayLength / MAX_LINES) - 1] = joinElements(Arrays.copyOfRange(billArray,startingIndex,billArray.length-1));
        return brokenArray;
    }

}

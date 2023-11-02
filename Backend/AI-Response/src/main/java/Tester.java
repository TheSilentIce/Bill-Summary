import PostgresRetrieve.BillQuery;

import java.io.IOException;

public class Tester {
    public static void main(String[] args) throws IOException {
        AIConnector aic = new AIConnector();
        BillQuery bq = new BillQuery();
        String[][] bills = bq.connect();

        aic.addToDatabase(bills);
    }
}

package PostgresRetrieve;

import java.sql.*;
import java.util.ArrayList;

public class BillQuery {
    private final String jdbcUrl = "jdbc:postgresql://BILL DATABASE";
    private final String username = "USERNAME";
    private final String password = "PASSWORD";
    private ArrayList<Integer> list = new ArrayList<>();

    public String testConnect() {
        try {
            Connection connection = DriverManager.getConnection(jdbcUrl, username, password);
            Statement statement = connection.createStatement();
            String sqlQuery = "SELECT * FROM bill WHERE id = ";

            int i = 1;
            while (i < 3) {
                ResultSet resultSet = statement.executeQuery(sqlQuery + i);
                resultSet.next();
                System.out.println(resultSet.getInt("id") + " " + resultSet.getString("bill_name"));
                i++;
                String x = resultSet.getString("bill_summary");
                resultSet.close();
                return x;
            }
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "";
    }
    public String[][] connect() {
        String[][] bills = new String[564][2];

        try {
            Connection connection = DriverManager.getConnection(jdbcUrl, username, password);
            Statement statement = connection.createStatement();
            String sqlQuery = "SELECT * FROM bill WHERE id = ";

            int i = 1;
            while (i < 564) {
                ResultSet resultSet = statement.executeQuery(sqlQuery + i);
                resultSet.next();
                String billName = resultSet.getString("bill_name");
                String summary = resultSet.getString("bill_summary");

                bills[i-1][0] = billName;
                bills[i-1][1] = summary;
                String[] data = summary.split("\n");

                list.add(data.length);

                i++;
                resultSet.close();
            }
//            System.out.println("List length: " + Collections.max(list));
//            System.out.println("TOTAL LINES: " + totalLines);
//            System.out.println("BILLSLONG: " + billsLong);

            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bills;
    }
}

package PostgresRetrieve;

import java.sql.*;

public class AiResponseQuery {
    private final String jdbcUrl = "jdbc:postgresql://SUMMARY DATABASE"; //insert own database
    private final String username = "USERNAME";
    private final String password = "PASSWORD";

    public void connect() {
        try {
            Connection connection = DriverManager.getConnection(jdbcUrl, username, password);
            Statement statement = connection.createStatement();
            String sqlQuery = "SELECT * FROM response WHERE id = 1";

            ResultSet resultSet = statement.executeQuery(sqlQuery);
            resultSet.next();
            System.out.println(resultSet.getString("billname"));
            System.out.println(resultSet.getString("summary"));

//                ResultSet resultSet = statement.executeQuery(sqlQuery + i);
//                resultSet.next();
//                String billName = resultSet.getString("bill_name");
//                String summary = resultSet.getString("bill_summary");

//                resultSet.close();

            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

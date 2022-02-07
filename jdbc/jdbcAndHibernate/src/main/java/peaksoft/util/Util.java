package peaksoft.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {

    // реализуйте настройку соеденения с БД

    private static final String url = "jdbc:postgresql://localhost:5432/postgres";
    private static final String userName = "postgres";
    private static final String password = "mirba85";

    public static Connection connection() {
        Connection connect = null;
        try {
            connect = DriverManager.getConnection(url, userName, password);
            System.out.println("PostgresSQL ге ийгиликуу уландыныз. Азаматсыз!!! ");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connect;
    }
}
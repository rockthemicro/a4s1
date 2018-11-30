package hello;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.sql.*;
import java.util.concurrent.TimeUnit;

@SpringBootApplication
public class Application {
    static String db_server_name = "database";
    static String db_server_usedb = "sys";

    public static void main(String[] args) {
        for (;;) {
            try {
                Class.forName("com.mysql.jdbc.Driver");
                Connection con = DriverManager.getConnection(
                        "jdbc:mysql://" + db_server_name + ":3306/" + db_server_usedb, "root", "mama");

                Statement stmt = con.createStatement();
                boolean rs = stmt.execute("CREATE DATABASE IF NOT EXISTS flight_booking_system");

                con.close();

                break;
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();

                System.out.println("Connection to mysql server '" + db_server_name + "', db '" + db_server_usedb +
                        "' failed. Retrying in 10 seconds");
                try {
                    TimeUnit.SECONDS.sleep(10);
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
            }
        }

        SpringApplication.run(Application.class, args);
    }
}

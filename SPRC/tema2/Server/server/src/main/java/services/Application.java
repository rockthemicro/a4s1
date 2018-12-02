package services;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.sql.*;
import java.util.concurrent.TimeUnit;

@SpringBootApplication
public class Application {
    static String db_server_name = "mysqldatabase";
    static String db_server_init_usedb = "sys";
    static String db_server_usedb = "application";
    static String db_server_root_user = "root";
    static String db_server_root_pass = "root";

    /**
     * Incercam la inifinit sa ne conectam la baza de date. Dupa ce reusim, cream baza de date
     * pe care o vom folosi la runtime, adica cea din db_server_usedb.
     * @param args
     */
    public static void main(String[] args) {
        boolean done = false;

        do {
            try {
                Class.forName("com.mysql.jdbc.Driver");
                Connection con = DriverManager.getConnection(
                        "jdbc:mysql://" + db_server_name + ":3306/" + db_server_init_usedb, db_server_root_user,
                        db_server_root_pass);

                Statement stmt = con.createStatement();
                stmt.execute("CREATE DATABASE IF NOT EXISTS " + db_server_usedb);

                con.close();
                done = true;
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException e) {

                System.out.println("MySQL database is not ready yet... Will try again in 15 seconds");
                try {
                    TimeUnit.SECONDS.sleep(15);
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
            }
        } while (!done);

        SpringApplication.run(Application.class, args);
    }
}

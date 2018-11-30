package admin;

import java.sql.*;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Main {

    private static void displayTable(Connection con, String tableName) {
        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select * from " + tableName);

            while(rs.next()) {
                int n = rs.getMetaData().getColumnCount();
                StringBuilder result = new StringBuilder();
                for (int i = 1; i <= n; i++) {
                    result.append(rs.getMetaData().getColumnName(i) + "=" + rs.getString(i) + " ");
                }

                System.out.println(result);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Connection con = null;

        while (true) {

            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                //Class.forName("com.mysql.cj.jdbc.Driver");

                con = DriverManager.getConnection(
                        "jdbc:mysql://database:3306/flight_booking_system", "root", "mama");

                //displayTable(con, "flight");
                //displayTable(con, "ticket");

                break;
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                System.out.println("Database is not yet available! Retrying in 15 seconds");

                try {
                    TimeUnit.SECONDS.sleep(15);
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
            }
        }


        Scanner s = new Scanner(System.in);
        Boolean exit = false;
        while (!exit) {
            System.out.println();
            System.out.println("Available choices: ");
            System.out.println("Add Flight: 1");
            System.out.println("Cancel Flight: 2");
            System.out.println("Display Flight Table: 3");
            System.out.println("exit: -1");
            System.out.println();

            String line = s.nextLine();
            switch (line) {
                case "1":
                    System.out.println("Enter: ID Source Destination Departure_Day Departure_Hour Duration Seats");

                    line = s.nextLine();
                    String[] lineParts = line.split(" ");
                    if (lineParts.length != 7) {
                        System.out.println("Incorrect number of parameters! Must be 7");
                        break;
                    }

                    addFlight(con, lineParts);
                    break;

                case "2":
                    System.out.println("Enter: Flight_Id");

                    line = s.nextLine();
                    try {
                        Integer id = Integer.parseInt(line);
                        cancelFlight(con, id);
                    } catch (NumberFormatException e) {
                        System.out.println("Not a number!");
                    }

                    break;

                case "3":
                    displayTable(con, "flight");
                    break;

                case "-1":
                default:
                    exit = true;
                    break;
            }
        }

        try {
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private static void cancelFlight(Connection con, Integer id) {
        try {
            Statement stmt = con.createStatement();
            stmt.execute("DELETE FROM flight WHERE id=" + id);

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Failed to cancel a flight");
        }
    }

    private static void addFlight(Connection con, String[] lineParts) {
        try {
            Integer id = Integer.parseInt(lineParts[0]);
            String source = lineParts[1];
            String destination = lineParts[2];
            Integer departureDay = Integer.parseInt(lineParts[3]);
            Integer departureHour = Integer.parseInt(lineParts[4]);
            Integer duration = Integer.parseInt(lineParts[5]);
            Integer seats = Integer.parseInt(lineParts[6]);

            Statement stmt = con.createStatement();
            stmt.execute("INSERT INTO flight(id, departure_day, departure_hour, dst, duration, seats, source) VALUES("
                    + id + "," + departureDay + "," + departureHour + ",'" + destination + "'," + duration + ","
                    + seats + ",'" + source + "')");

        } catch(NumberFormatException e) {
            e.printStackTrace();
            System.out.println("Non-numeric value introduced for id/departure_hour/departure_day/duration/seats");

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Failed to add a flight");
        }

    }
}

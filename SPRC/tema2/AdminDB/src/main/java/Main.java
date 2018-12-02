import java.sql.*;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Main {

    private static final String db_host = "localhost";
    private static final String db_database = "application";
    private static final String db_user = "root";
    private static final String db_pass = "root";
    private static final String db_flight_table = "zbor";
    private static final String db_ticket_table = "rezervare";

    public static void main(String[] args) {
        /**
         * Asteptam ca baza de date sa fie disponibila, si obtinem o conexiune cu aceasta imediat ce devine
         * disponibila
         */
        Connection con = waitUntilDBAvailable();

        Scanner s = new Scanner(System.in);
        Boolean exit = false;
        while (!exit) {
            System.out.println();
            System.out.println("Enter one of the following choices: ");
            System.out.println("Add A Flight: 1");
            System.out.println("Cancel A Flight: 2");
            System.out.println("Display The Flights Table: 3");
            System.out.println("Display The Tickets Table: 4");
            System.out.println("Exit client: anything else");

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
                        cancelFlight(con, line);
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                    }

                    break;

                case "3":
                    selectAllTable(con, db_flight_table);
                    break;

                case "4":
                    selectAllTable(con, db_ticket_table);
                    break;

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

    /**
     * Reincearca la infinit stabilirea unei conexiuni la baza de date, iar dupa ce reuseste, returneaza
     * handlerul acesteia
     *
     * @return conexiune la baza de data
     */
    private static Connection waitUntilDBAvailable() {
        Connection con = null;

        while (true) {

            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                con = DriverManager.getConnection("jdbc:mysql://" + db_host + ":3306/" + db_database, db_user, db_pass);

                break;
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                System.out.println("Database is not ready yet... Will try again in 20 seconds");

                try {
                    TimeUnit.SECONDS.sleep(20);
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
            }
        }

        return con;
    }

    /**
     * Afiseaza la stdout continutul tabelului 'table'
     * @param con conexiune activa la baza de date
     * @param table numele tabelului al carui continut vrem sa-l afisam
     */
    private static void selectAllTable(Connection con, String table) {
        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select * from " + table);

            while(rs.next()) {
                int resultSetSize = rs.getMetaData().getColumnCount();
                StringBuilder result = new StringBuilder();
                for (int i = 1; i <= resultSetSize; i++) {
                    result.append(rs.getMetaData().getColumnName(i) + "=" + rs.getString(i) + " ");
                }

                System.out.println(result);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Sterge din tabelul ce tine evidenta zborurilor zborul cu id-ul 'id'
     * @param con conexiune activa la baza de date
     * @param id id-ul zborului pe care vrem sa-l stergem
     */
    private static void cancelFlight(Connection con, String id) {
        try {
            Statement stmt = con.createStatement();
            stmt.execute("DELETE FROM " + db_flight_table + " WHERE id=" + id);

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Could not cancel flight");
        }
    }

    /**
     * Adauga in tabelul ce tine evidenta zborurilor un nou zbor
     * @param con conexiune activa la baza de date
     * @param flightInfo informatiile noului zbor
     */
    private static void addFlight(Connection con, String[] flightInfo) {
        try {
            String id = flightInfo[0];
            String source = flightInfo[1];
            String destination = flightInfo[2];
            Integer departureDay = Integer.parseInt(flightInfo[3]);
            Integer departureHour = Integer.parseInt(flightInfo[4]);
            Integer duration = Integer.parseInt(flightInfo[5]);
            Integer seats = Integer.parseInt(flightInfo[6]);

            int daysSpent = duration / 24;
            int arrivalDay = departureDay + daysSpent;
            if (duration % 24 + departureHour >= 24) {
                arrivalDay += 1;
            }
            if (arrivalDay > 365) {
                System.out.println("Did not add this flight! Arrival day is later than 365");
                return;
            }

            Statement stmt = con.createStatement();
            stmt.execute("INSERT INTO " + db_flight_table
                    + "(id, departure_day, departure_hour, dst, duration, seats, source) VALUES("
                    + id + "," + departureDay + "," + departureHour + ",'" + destination + "'," + duration + ","
                    + seats + ",'" + source + "')");

        } catch(NumberFormatException e) {
            e.printStackTrace();
            System.out.println("Could not convert String to Integer from either of Departure Day, Departure Hour, "
                    + "Duration or Seats");

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Could not add flight");
        }
    }
}

package client;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.util.Scanner;
import java.net.*;
import java.util.concurrent.TimeUnit;
import java.io.InputStreamReader;


public class Main {
    private static String server_host = "localhost";
    private static String server_route_getoptimalroute = "route";
    private static String server_route_bookticket = "book";
    private static String server_route_buyticket = "buy";

    public static void main(String[] args) {

        waitUntilServerAvailable();

        /**
         * Pasii pe care ii facem in urmatorul block try-catch:
         * 1. Cream cate o conexiune (HttpURLConnection) pentru fiecare restful call posibil
         * 2. Cerem utilizatorului input, pentru a putea executa un apel
         * 3. In functie de apelul cerut de client, una din cele 3 conexiuni va primi null, iar cea de care avem
         * va fi preluata
         * 4. Cream apelul rest pe baza inputului, il executam si apoi inchidem conexiunea folosita
         * 5. Cream o noua conexiune pentru a fi pusa in locul celei folosite pentru apelul anterior
         */
        try {
            Boolean done = false;
            Scanner sc = new Scanner(System.in);

            String base = "http://" + server_host + ":8080/";

            URL url = new URL(base + server_route_getoptimalroute);
            HttpURLConnection conRoute = (HttpURLConnection) url.openConnection();
            conRoute.setRequestMethod("GET");
            conRoute.setDoOutput(true);
            conRoute.setConnectTimeout(5000);
            conRoute.setReadTimeout(10000);

            url = new URL(base + server_route_bookticket);
            HttpURLConnection conBook = (HttpURLConnection) url.openConnection();
            conBook.setRequestMethod("GET");
            conBook.setDoOutput(true);
            conBook.setConnectTimeout(5000);
            conBook.setReadTimeout(10000);


            url = new URL(base + server_route_buyticket);
            HttpURLConnection conBuy = (HttpURLConnection) url.openConnection();
            conBuy.setRequestMethod("GET");
            conBuy.setDoOutput(true);
            conBuy.setConnectTimeout(5000);
            conBuy.setReadTimeout(10000);

            HttpURLConnection con = null;

            while (!done) {
                System.out.println("Enter one of the following choices: ");
                System.out.println("Get The Optimal Route: route");
                System.out.println("Book A Ticket: book");
                System.out.println("Buy A Ticket: buy");
                System.out.println("Exit Client: anything else");

                StringBuffer arguments = new StringBuffer();
                String line = sc.nextLine();

                switch (line) {
                    case "route":
                        System.out.println("Enter: source destination maxFlights departureDay");
                        con = conRoute;
                        conRoute = null;
                        url = new URL(base + server_route_getoptimalroute);

                        String[] lineParts = sc.nextLine().split(" ");
                        arguments.append(URLEncoder.encode("from", "UTF-8"));
                        arguments.append("=");
                        arguments.append(URLEncoder.encode(lineParts[0], "UTF-8"));
                        arguments.append("&");

                        arguments.append(URLEncoder.encode("to", "UTF-8"));
                        arguments.append("=");
                        arguments.append(URLEncoder.encode(lineParts[1], "UTF-8"));
                        arguments.append("&");

                        arguments.append(URLEncoder.encode("flights", "UTF-8"));
                        arguments.append("=");
                        arguments.append(URLEncoder.encode(lineParts[2], "UTF-8"));
                        arguments.append("&");

                        arguments.append(URLEncoder.encode("day", "UTF-8"));
                        arguments.append("=");
                        arguments.append(URLEncoder.encode(lineParts[3], "UTF-8"));

                        break;

                    case "book":
                        System.out.println("Enter: flightID1 flightID2 ...");
                        con = conBook;
                        conBook = null;
                        url = new URL(base + server_route_bookticket);

                        String[] flightIDs = sc.nextLine().split(" ");

                        arguments.append(URLEncoder.encode("flightIDs", "UTF-8"));
                        arguments.append("=");

                        for (int i = 0; i < flightIDs.length - 1; i++) {
                            arguments.append(URLEncoder.encode(flightIDs[i], "UTF-8"));
                            arguments.append(",");
                        }
                        arguments.append(URLEncoder.encode(flightIDs[flightIDs.length - 1], "UTF-8"));

                        break;

                    case "buy":
                        System.out.println("Enter: reservationId creditCardInformation");

                        String[] reservation = sc.nextLine().split(" ");
                        if (reservation.length != 2) {
                            System.out.println("Incorrect number of parameters");
                            continue;
                        }

                        con = conBuy;
                        conBuy = null;
                        url = new URL(base + server_route_buyticket);

                        arguments.append(URLEncoder.encode("reservationID", "UTF-8"));
                        arguments.append("=");
                        arguments.append(URLEncoder.encode(reservation[0], "UTF-8"));
                        arguments.append("&");

                        arguments.append(URLEncoder.encode("cardInfo", "UTF-8"));
                        arguments.append("=");
                        arguments.append(URLEncoder.encode(reservation[1], "UTF-8"));

                        break;

                    default:
                        done = true;
                        break;
                }
                if (done) break;

                StringBuffer content = new StringBuffer();
                try {
                    DataOutputStream out = new DataOutputStream(con.getOutputStream());
                    out.writeBytes(arguments.toString());
                    out.flush();
                    out.close();

                    BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
                    String inputLine;
                    while ((inputLine = in.readLine()) != null) {
                        content.append(inputLine);
                    }
                    in.close();

                    con.disconnect();
                } catch (IOException e) {
                    e.printStackTrace();
                    System.out.println("There was an error with your request!");
                }
                con = (HttpURLConnection) url.openConnection();
                con.setRequestMethod("GET");
                con.setDoOutput(true);
                con.setConnectTimeout(5000);
                con.setReadTimeout(10000);

                if (conBook == null) {
                    conBook = con;
                } else if (conRoute == null) {
                    conRoute = con;
                } else if (conBuy == null) {
                    conBuy = con;
                }

                if (content.length() > 0) {
                    if (content.charAt(0) == '[') {
                        String[] resultVector = content.toString().split(",");

                        if (resultVector.length >= 2) {
                            System.out.println(resultVector[0].substring(1));
                            for (int i = 1; i < resultVector.length - 1; i++) {
                                System.out.println(resultVector[i]);
                            }
                            System.out.println(resultVector[resultVector.length - 1].
                                    substring(0, resultVector[resultVector.length - 1].length() - 1));

                        } else {
                            System.out.println(content);
                        }

                    } else {
                        System.out.println(content);
                    }
                }

                System.out.println();
            }

            conRoute.disconnect();
            conBook.disconnect();
            conBuy.disconnect();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * Reincearca la infinit stabilirea unei conexiuni la serverul ce asigura serviciile, pana reuseste o data
     */
    private static void waitUntilServerAvailable() {

        while (true) {

            try {
                String stringUrl = "http://" + server_host + ":8080/";
                URL url = new URL(stringUrl);

                HttpURLConnection con = (HttpURLConnection) url.openConnection();

                con.setConnectTimeout(7000);
                con.setDoOutput(true);

                DataOutputStream out = new DataOutputStream(con.getOutputStream());

                out.close();
                con.disconnect();

                break;

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (ProtocolException e) {
                e.printStackTrace();
            } catch (IOException e) {
                System.out.println("Server is not ready yet... Will try again in 20 seconds");
                try {
                    TimeUnit.SECONDS.sleep(20);
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
            }
        }
    }
}

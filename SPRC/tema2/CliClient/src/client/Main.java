package client;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Main {
    private static String host = "server";
    private static String c1 = "get_optimal_route";
    private static String c2 = "book_ticket";
    private static String c3 = "buy_ticket";

    public static void main(String[] args) {

        while (true) {

            try {
                String baseUrl = "http://" + host + ":8080/greeting";
                URL url = null;
                url = new URL(baseUrl);

                HttpURLConnection con = (HttpURLConnection) url.openConnection();
                con.setRequestMethod("GET");
                con.setDoOutput(true);
                con.setConnectTimeout(5000);
                con.setReadTimeout(5000);

                // asta cauzeaza exceptia daca nu merge url-ul
                DataOutputStream out = new DataOutputStream(con.getOutputStream());
                out.close();
                con.disconnect();

                break;

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (ProtocolException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("Client is not accepting connections! Retrying in 15 seconds...");
                try {
                    TimeUnit.SECONDS.sleep(15);
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
            }
        }


        try {
            Scanner s = new Scanner(System.in);
            Boolean exit = false;

            while (!exit) {
                StringBuilder parameter = new StringBuilder();

                System.out.println("Available choices: ");
                System.out.println("getOptimalRoute: 1");
                System.out.println("bookTicket: 2");
                System.out.println("buyTicket: 3");
                System.out.println("exit: -1");

                String baseUrl = "http://" + host + ":8080/";

                String line = s.nextLine();
                switch (line) {
                    case "1":
                        System.out.println("Enter: source destination maxFlights departureDay");
                        baseUrl += "get_optimal_route";

                        String[] lineParts = s.nextLine().split(" ");
                        parameter.append(URLEncoder.encode("src", "UTF-8"));
                        parameter.append("=");
                        parameter.append(URLEncoder.encode(lineParts[0], "UTF-8"));
                        parameter.append("&");

                        parameter.append(URLEncoder.encode("dst", "UTF-8"));
                        parameter.append("=");
                        parameter.append(URLEncoder.encode(lineParts[1], "UTF-8"));
                        parameter.append("&");

                        parameter.append(URLEncoder.encode("max", "UTF-8"));
                        parameter.append("=");
                        parameter.append(URLEncoder.encode(lineParts[2], "UTF-8"));
                        parameter.append("&");

                        parameter.append(URLEncoder.encode("day", "UTF-8"));
                        parameter.append("=");
                        parameter.append(URLEncoder.encode(lineParts[3], "UTF-8"));

                        break;

                    case "2":
                        System.out.println("Enter: [flightIds]");
                        String[] ids = s.nextLine().split(" ");

                        parameter.append(URLEncoder.encode("ids", "UTF-8"));
                        parameter.append("=");

                        for (int i = 0; i < ids.length - 1; i++) {
                            parameter.append(URLEncoder.encode(ids[i], "UTF-8"));
                            parameter.append(",");
                        }
                        parameter.append(URLEncoder.encode(ids[ids.length - 1], "UTF-8"));

                        baseUrl += "book_ticket";
                        break;

                    case "3":
                        System.out.println("Enter: reservationId creditCardInformation");
                        String[] reservation = s.nextLine().split(" ");

                        parameter.append(URLEncoder.encode("id", "UTF-8"));
                        parameter.append("=");
                        parameter.append(URLEncoder.encode(reservation[0], "UTF-8"));
                        parameter.append("&");

                        parameter.append(URLEncoder.encode("card", "UTF-8"));
                        parameter.append("=");
                        parameter.append(URLEncoder.encode(reservation[1], "UTF-8"));

                        baseUrl += "buy_ticket";
                        break;

                    case "-1":
                    default:
                        exit = true;
                        break;
                }
                if (exit) break;

                URL url = new URL(baseUrl);
                HttpURLConnection con = (HttpURLConnection) url.openConnection();
                con.setRequestMethod("GET");
                con.setDoOutput(true);
                con.setConnectTimeout(5000);
                con.setReadTimeout(5000);

                // asta cauzeaza exceptia daca nu merge url-ul
                DataOutputStream out = new DataOutputStream(con.getOutputStream());
                out.writeBytes(parameter.toString());
                out.flush();
                out.close();

                int status = con.getResponseCode();
                BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
                String inputLine;
                StringBuffer content = new StringBuffer();
                while ((inputLine = in.readLine()) != null) {
                    content.append(inputLine);
                }
                in.close();

                con.disconnect();

                System.out.println(status + " " + content);
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

package dbc;

import databaseObjects.Categorie;
import databaseObjects.Produs;
import databaseObjects.Tranzactie;
import oracle.jdbc.OracleTypes;

import java.sql.*;
import java.util.ArrayList;

public class OracleConnection {
    private Connection connection = null;
    private String username = null;
    private String password = null;
    private String host = null;
    private int port;
    private String schema = null;

    public static OracleConnection oc = null;

    public OracleConnection(String host, int port, String schema, String username, String password) {
        this.host = host;
        this.port = port;
        this.schema = schema;
        this.username = username;
        this.password = password;

        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void openConnection() {
        try {
            String connString = "jdbc:oracle:thin:@" + this.host + ":" + this.port + ":" + this.schema;

            this.connection = DriverManager.getConnection(connString, this.username, this.password);

            if (this.connection != null) {
                System.out.println("We are connected! :)");
                OracleConnection.oc = this;
            } else {
                System.out.println("Failed to establish a connection... :(");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void closeConnection() {
        try {
            this.connection.close();
            this.connection = null;

            if (OracleConnection.oc == this)
                OracleConnection.oc = null;

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Categorie> obtine_categorii() {
        ArrayList<Categorie> ret = new ArrayList<>();

        try {
            String call = "{call comenzi.obtine_categorii(?)}";
            CallableStatement cstmt = this.connection.prepareCall(call);
            cstmt.registerOutParameter("categorii", OracleTypes.CURSOR);
            cstmt.executeQuery();

            ResultSet rs = (ResultSet) cstmt.getObject("categorii");
            while (rs.next()) {
                Categorie categorie = new Categorie();
                categorie.id = rs.getInt("id");
                categorie.nume = rs.getString("nume");


                ret.add(categorie);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return ret;
    }

    public ArrayList<Produs> obtine_produse(String categorie) {
        ArrayList<Produs> ret = new ArrayList<>();

        try {
            String call = null;
            CallableStatement cstmt = null;
            ResultSet rs = null;
            switch (categorie) {
                case "laptopuri":
                    call = "{call comenzi.obtine_laptopuri(?)}";
                    cstmt = this.connection.prepareCall(call);
                    cstmt.registerOutParameter("laptopuri", OracleTypes.CURSOR);
                    cstmt.executeQuery();

                    rs = (ResultSet) cstmt.getObject("laptopuri");
                    break;

                case "telefoane":
                    call = "{call comenzi.obtine_telefoane(?)}";
                    cstmt = this.connection.prepareCall(call);
                    cstmt.registerOutParameter("telefoane", OracleTypes.CURSOR);
                    cstmt.executeQuery();

                    rs = (ResultSet) cstmt.getObject("telefoane");
                    break;

                case "monitoare":
                    call = "{call comenzi.obtine_monitoare(?)}";
                    cstmt = this.connection.prepareCall(call);
                    cstmt.registerOutParameter("monitoare", OracleTypes.CURSOR);
                    cstmt.executeQuery();

                    rs = (ResultSet) cstmt.getObject("monitoare");
                    break;

                default:
                    return ret;
            }

            while (rs.next()) {
                Produs produs = new Produs();
                produs.id = rs.getInt("id");
                produs.nume = rs.getString("nume");
                produs.description = rs.getString("description");
                produs.pret = rs.getInt("pret");
                produs.url_poza_profil = rs.getString("url_poza_profil");
                produs.cantitate = rs.getInt("cantitate");
                produs.id_categorie = rs.getInt("id_categorie");

                ret.add(produs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


        return ret;
    }

    public Integer adauga_tranzactie(Tranzactie tranzactie) {
        

        return 0;
    }
}

package dbc;

import databaseObjects.*;
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
        Integer newId = -1;
        try {
            String call = "{? = call comenzi.adauga_tranzactie(?, ?, ?, ?, ?, ?, ?)}";
            CallableStatement cstmt = this.connection.prepareCall(call);

            cstmt.registerOutParameter(1, oracle.jdbc.OracleTypes.NUMBER);
            cstmt.setInt(2, tranzactie.id_curier);
            cstmt.setInt(3, tranzactie.id_localitate);
            cstmt.setInt(4, tranzactie.id_produs);
            cstmt.setString(5, tranzactie.client_nume);
            cstmt.setString(6, tranzactie.client_prenume);
            cstmt.setString(7, tranzactie.client_telefon);
            cstmt.setString(8, tranzactie.adresa_livrare);
            cstmt.executeQuery();

            newId = cstmt.getInt(1);
        } catch(SQLException e) {
            e.printStackTrace();
        }

        return newId;
    }

    public ArrayList<Localitate> obtine_localitati() {
        ArrayList<Localitate> ret = new ArrayList<>();

        try {
            String call = "{call comenzi.obtine_localitati(?)}";
            CallableStatement cstmt = this.connection.prepareCall(call);
            cstmt.registerOutParameter("localitati", OracleTypes.CURSOR);
            cstmt.executeQuery();

            ResultSet rs = (ResultSet) cstmt.getObject("localitati");
            while (rs.next()) {
                Localitate localitate = new Localitate();
                localitate.id = rs.getInt("id");
                localitate.nume = rs.getString("nume");


                ret.add(localitate);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return ret;
    }

    public ArrayList<Livrare> obtine_livrari(String localitate) {
        ArrayList<Livrare> ret = new ArrayList<>();
        try {
            String call = "{call comenzi.obtine_curieri(?, ?)}";
            CallableStatement cstmt = this.connection.prepareCall(call);
            cstmt.setString("localitate", localitate);
            cstmt.registerOutParameter("curieri", OracleTypes.CURSOR);
            cstmt.executeQuery();

            ResultSet rs = (ResultSet) cstmt.getObject("curieri");
            while (rs.next()) {
                Livrare livrare = new Livrare();
                livrare.id = rs.getInt("id");
                livrare.id_curier = rs.getInt("id_curier");
                livrare.id_localitate = rs.getInt("id_localitate");
                livrare.taxa = rs.getInt("taxa");

                ret.add(livrare);
            }

            } catch (SQLException e) {
            e.printStackTrace();
        }

        return ret;
    }
}

package restservice;

import dbc.OracleConnection;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        OracleConnection oc = new OracleConnection("localhost", 1521, "xe", "system", "caca");
        oc.openConnection();

        SpringApplication.run(Application.class, args);
    }
}

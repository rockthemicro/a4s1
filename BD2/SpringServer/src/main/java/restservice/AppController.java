package restservice;

import databaseObjects.Produs;
import databaseObjects.Tranzactie;
import dbc.OracleConnection;
import oracle.jdbc.OracleTypes;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
public class AppController {

    @GetMapping("/categorii")
    public String[] categorii() {
        OracleConnection oc = OracleConnection.oc;
        var categorii = oc.obtine_categorii();

        var result = new String[categorii.size()];
        int i = 0;
        for (var categorie : categorii) {
            result[i] = categorie.nume;
            i++;
        }

        return result;
    }

    @GetMapping("/produse")
    public Produs[] obtine_produse(@RequestParam(value="categorie", defaultValue="laptopuri") String categorie) {
        OracleConnection oc = OracleConnection.oc;
        var produse = oc.obtine_produse(categorie);

        var result = new Produs[produse.size()];
        int i = 0;
        for (var produs : produse) {
            result[i] = produs;
            i++;
        }

        return result;
    }

    @PostMapping("/tranzactie")
    public Integer efectueaza_tranzactie(@RequestBody Tranzactie tranzactie) {
        OracleConnection oc = OracleConnection.oc;

        return oc.adauga_tranzactie(tranzactie);
    }
}

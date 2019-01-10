package restservice;

import databaseObjects.Livrare;
import databaseObjects.Localitate;
import databaseObjects.Produs;
import databaseObjects.Tranzactie;
import dbc.OracleConnection;
import oracle.jdbc.OracleTypes;
import org.springframework.web.bind.annotation.*;

@SuppressWarnings("Duplicates")
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

    @GetMapping("/localitati")
    public Localitate[] obtine_localitati() {
        OracleConnection oc = OracleConnection.oc;
        var localitati = oc.obtine_localitati();

        var result = new Localitate[localitati.size()];
        int i = 0;
        for (var localitate : localitati) {
            result[i] = localitate;
            i++;
        }

        return result;
    }

    @GetMapping("/curieri")
    public Livrare[] obtine_curieri(@RequestParam(value="localitate") String localitate) {
        OracleConnection oc = OracleConnection.oc;
        var livrari = oc.obtine_livrari(localitate);

        var result = new Livrare[livrari.size()];
        int i = 0;
        for (var livrare : livrari) {
            result[i] = livrare;
            i++;
        }

        return result;
    }
}

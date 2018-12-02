package services;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Rezervare {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;

    private String flightIDs;

    private Boolean bought = false;



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public String getFlightIDs() {
        return flightIDs;
    }

    public void setFlightIDs(String flightIDs) {
        this.flightIDs = flightIDs;
    }

    public Boolean getBought() {
        return bought;
    }

    public void setBought(Boolean bought) {
        this.bought = bought;
    }

}

package hello;

import javax.persistence.*;

@Entity
public class Flight {
    //@GeneratedValue(strategy=GenerationType.AUTO)
    @Id
    private Integer id; // Florin voia String la id

    private String source;
    private String dst;

    private Integer departure_hour;
    private Integer departure_day;

    private Integer duration;
    private Integer seats;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getDst() {
        return dst;
    }

    public void setDst(String dst) {
        this.dst = dst;
    }

    public Integer getDeparture_hour() {
        return departure_hour;
    }

    public void setDeparture_hour(Integer departure_hour) {
        this.departure_hour = departure_hour;
    }

    public Integer getDeparture_day() {
        return departure_day;
    }

    public void setDeparture_day(Integer departure_day) {
        this.departure_day = departure_day;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public Integer getSeats() {
        return seats;
    }

    public void setSeats(Integer seats) {
        this.seats = seats;
    }


}

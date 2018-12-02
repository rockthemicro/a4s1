package services;

public class TimeSpaceMoment {
    private String location;
    private Integer day;
    private Integer hour;

    public TimeSpaceMoment(String loc, Integer day, Integer hour) {
        this.location = loc;
        this.day = day;
        this.hour = hour;
    }

    /**
     * Creeaza un nou moment spatiu-timp care ne plaseaza la finalul zborului
     * @param zbor zborul pentru care simulam un moment spatiu-timp
     */
    public TimeSpaceMoment(Zbor zbor) {
        this.location = zbor.getDst();
        this.day = zbor.getDeparture_day();

        if (zbor.getDeparture_hour() + zbor.getDuration() >= 24) {
            this.day += 1;
            this.hour = (zbor.getDeparture_hour() + zbor.getDuration()) % 24;
        } else {
            this.hour = zbor.getDeparture_hour() + zbor.getDuration();
        }
    }

    /**
     * Calculeaza numarul de ore pe care-l petrecem asteptand pana la urmatorul zbor + numarul
     * de ore al urmatorului zbor
     * @param zbor zborul la care ne raportam
     * @return numarul de ore; -1 daca zborul se petrece inainte de momentul de timp actual
     */
    public Integer getCostOfFlight(Zbor zbor) {
        Integer result = 0;

        if (this.location.equalsIgnoreCase(zbor.getSource()) == false) {
            return -1;
        }

        if (this.day == zbor.getDeparture_day() && this.hour > zbor.getDeparture_hour()) {
            return -1;
        }

        if (this.day > zbor.getDeparture_day()) {
            return -1;
        }

        if (this.day == zbor.getDeparture_day()) {
            return zbor.getDeparture_hour() - this.hour + zbor.getDuration();
        }

        result = 24 - this.hour;
        int day = this.day + 1;
        result += 24 * (zbor.getDeparture_day() - day);
        result += zbor.getDeparture_hour();

        return result;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Integer getDay() {
        return day;
    }

    public void setDay(Integer day) {
        this.day = day;
    }

    public Integer getHour() {
        return hour;
    }

    public void setHour(Integer hour) {
        this.hour = hour;
    }
}

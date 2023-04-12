package ca.mcgill.ecse.snowshoetours.controller;

import java.sql.Date;

public class TOSnowShoeTourSeason {
    private Date startDate;
    private int nrWeeks;
    private int priceOfGuidePerWeek;

    public TOSnowShoeTourSeason(Date startDate, int nrWeeks, int priceOfGuidePerWeek) {
        this.startDate = startDate;
        this.nrWeeks = nrWeeks;
        this.priceOfGuidePerWeek = priceOfGuidePerWeek;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public int getNrWeeks() {
        return nrWeeks;
    }

    public void setNrWeeks(int nrWeeks) {
        this.nrWeeks = nrWeeks;
    }

    public int getPriceOfGuidePerWeek() {
        return priceOfGuidePerWeek;
    }

    public void setPriceOfGuidePerWeek(int priceOfGuidePerWeek) {
        this.priceOfGuidePerWeek = priceOfGuidePerWeek;
    }
}
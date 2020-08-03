package com.example.eveutopia;

public class OutputCoor {
    public String Latitude;
    public String Longitude;
    public Double price;
    public String[] slots_avail;
    public String[] slots_avail_2;
    public String[] slots_avail_3;

    public OutputCoor(){

    }

    public OutputCoor(String latitude, String longitude, Double price, String[] slots_avail, String[] slots_avail_2, String[] slots_avail_3) {
        Latitude = latitude;
        Longitude = longitude;
        this.price = price;
        this.slots_avail = slots_avail;
        this.slots_avail_2 = slots_avail_2;
        this.slots_avail_3 = slots_avail_3;
    }

    public String getLatitude() {
        return Latitude;
    }

    public void setLatitude(String latitude) {
        Latitude = latitude;
    }

    public String getLongitude() {
        return Longitude;
    }

    public void setLongitude(String longitude) {
        Longitude = longitude;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String[] getSlots_avail() {
        return slots_avail;
    }

    public void setSlots_avail(String[] slots_avail) {
        this.slots_avail = slots_avail;
    }

    public String[] getSlots_avail_2() {
        return slots_avail_2;
    }

    public void setSlots_avail_2(String[] slots_avail_2) {
        this.slots_avail_2 = slots_avail_2;
    }

    public String[] getSlots_avail_3() {
        return slots_avail_3;
    }

    public void setSlots_avail_3(String[] slots_avail_3) {
        this.slots_avail_3 = slots_avail_3;
    }
}
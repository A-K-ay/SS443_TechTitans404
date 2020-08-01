package com.example.eveutopia;

public class OutputCoor {
    public String Latitude;
    public String Longitude;

    public OutputCoor(){}

    public OutputCoor(String Latitude, String Longitude) {
        this.Latitude = Latitude;
        this.Longitude = Longitude;
    }

    public String getLatitude() {
        return Latitude;
    }

    public void setLatitude(String Latitude) {
        this.Latitude = Latitude;
    }

    public String getLongitude() {
        return Longitude;
    }

    public void setLongitude(String Longitude) {
        this.Longitude = Longitude;
    }
}
